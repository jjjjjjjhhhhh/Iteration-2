<template>
	<div>
		<div class="center_view edit_form">
			<el-form class="password_form" ref="passwordFormRef" :model="form" label-width="120px" :rules="rules">
				<el-form-item label="Password" prop="mima1">
					<el-input class="list_inp" v-model="form.mima1" type="password" show-password />
				</el-form-item>
				<el-form-item label="New Password" prop="mima">
					<el-input class="list_inp" v-model="form.mima" type="password" show-password />
				</el-form-item>
				<el-form-item label="Confirm password" prop="mima2">
					<el-input class="list_inp" v-model="form.mima2" type="password" show-password />
				</el-form-item>
				<span class="formModel_btn_box">
					<el-button class="confirm_btn" type="primary" @click="onSubmit">保存</el-button>
				</span>
					
			</el-form>
		</div>
	</div>
</template>

<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance
	} from 'vue'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const form = ref({})
	const user = ref({})
	const sessionTable = ref('')
	const passwordFormRef = ref(null)
	const rules = ref({
		mima1: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
		mima: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
		mima2: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
	})
	const onSubmit = async () => {
		passwordFormRef.value.validate(async (valid) => {
			if (valid) {
				if(sessionTable.value == 'users'){
					if (form.value.mima1 != user.value.password) {
						context?.$toolUtil.message('The original password is incorrect','error')
						return false
					}
					user.value.password = form.value.mima
				}else{
					if(sessionTable.value == 'jiankangzhuanjia'){
						var nowpassword = ''
						await context?.$http({
							url:'encrypt/md5?text=' + form.value.mima1,
							method: 'get'
						}).then(res=>{
							nowpassword = res.data.data
						})
						if(nowpassword != user.value.zhuanjiamima){
							context?.$toolUtil.message('The original password is incorrect', 'error')
							return false
						}
					}
				}
				if (form.value.mima2 != form.value.mima) {
					context?.$toolUtil.message('The two password inputs are inconsistent','error')
					return false
				}
                if (form.value.mima1 == form.value.mima) {
                    context?.$toolUtil.message('The new password cannot be the same as the original password','error')
                    return false
                }
				if(sessionTable.value == 'jiankangzhuanjia'){
					user.value.zhuanjiamima = form.value.mima
				}
				context?.$http({
					url: `${sessionTable.value}/update`,
					method: 'post',
					data: user.value
				}).then(res => {
					context?.$toolUtil.message('The modification was successful. The next login will use the new password','success')
				})
			}
		})

	}
	const getInfo = () => {
		sessionTable.value = context?.$toolUtil.storageGet('sessionTable')
		context?.$http({
			url: `${sessionTable.value}/session`,
			method: 'get'
		}).then(res => {
			user.value = res.data.data
		})
	}
	getInfo()
</script>

<style lang="scss" scoped>
	// 表单
	.password_form{
		// form item
		:deep(.el-form-item) {
			// 内容盒子
			.el-form-item__content{
				// 输入框
				.list_inp {
				}
			}
		}
		// 按钮盒子
		.formModel_btn_box {
			// 确定按钮
			.confirm_btn {
			}
			// 确定按钮-悬浮
			.confirm_btn:hover {
			}
		}
	}
</style>
