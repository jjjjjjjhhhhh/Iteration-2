<template>
	<div>
		<div class="center_view edit_form">
			<el-form class="userinfo_form" ref="userinfoFormRef" :model="form">
				<el-row>
					<el-col :span="24">
						<el-form-item label="User account" prop="yonghuzhanghao">
							<el-input class="list_inp" v-model="user.yonghuzhanghao" readonly placeholder="User account" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="User Name" prop="yonghuxingming">
							<el-input class="list_inp" v-model="user.yonghuxingming"  placeholder="User Name" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Icon" prop="touxiang">
							<uploads
								action="file/upload" 
								tip="Please upload your profile picture"
								style="width: 100%;text-align: left;"
								:fileUrls="user.touxiang?user.touxiang:''" 
								@change="yonghutouxiangUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="gender" prop="xingbie">
							<el-select 
								class="list_sel" 
								v-model="user.xingbie" 
								placeholder="Please select gender"
								>
								<el-option v-for="item in yonghuxingbieLists" :label="item" :value="item"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Phone number" prop="shoujihaoma">
							<el-input class="list_inp" v-model="user.shoujihaoma"  placeholder="Phone number" clearable />
						</el-form-item>
					</el-col>
					<span class="formModel_btn_box">
						<el-button class='confirm_btn' type="primary" @click="onSubmit">Save</el-button>
					</span>
				</el-row>
			</el-form>
		</div>
	</div>
</template>

<script setup>
	import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/toolUtil";
	import {
		reactive,
		ref,
		getCurrentInstance
	} from 'vue'
	import { useStore } from 'vuex'
	const store = useStore()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const tableName = ref('yonghu')
	const user = ref({})
	const yonghuxingbieLists = ref([])
	const init = () => {
		yonghuxingbieLists.value = "Man,Woman".split(',')
	}
	const yonghutouxiangUploadSuccess=(fileUrls)=> {
	    user.value.touxiang = fileUrls;
	}
	const onSubmit = () => {
		if((!user.value.yonghuzhanghao)){
			context?.$toolUtil.message(`User account cannot be empty`,'error')
			return false
		}
		if((!user.value.yonghumima)){
			context?.$toolUtil.message(`User password cannot be empty`,'error')
			return false
		}
		if((!user.value.yonghuxingming)){
			context?.$toolUtil.message(`User name cannot be empty`,'error')
			return false
		}
		if(user.value.touxiang!=null){
			user.value.touxiang = user.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if((user.value.shoujihaoma)&&(!context?.$toolUtil.isMobile(user.value.shoujihaoma))){
			context?.$toolUtil.message(`The phone number should be entered in the phone format`,'error')
			return false
		}
		store.dispatch('user/update',user.value).then(res=>{
			if(res?.data&&res.data.code==0)context?.$toolUtil.message('Modified successfully','success')
		})

	}
	const getInfo = () => {
		context?.$http({
			url: `${tableName.value}/session`,
			method: 'get'
		}).then(res => {
			user.value = res.data.data
			init()
		})
	}
	getInfo()
</script>

<style lang="scss" scoped>
	// 表单
	.userinfo_form {
		// form item
		:deep(.el-form-item) {
			// 内容盒子
			.el-form-item__content{
			}
			// 输入框
			.list_inp {
			}
			//下拉框样式
			.list_sel {
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
					}
				}
			}
			//图片上传样式
			.el-upload-list  {
				//提示语
				.el-upload__tip {
				}
				//外部盒子
				.el-upload--picture-card {
					//图标
					.el-icon{
					}
				}
				.el-upload-list__item {
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
