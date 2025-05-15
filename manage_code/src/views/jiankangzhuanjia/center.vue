<template>
	<div>
		<div class="center_view edit_form">
			<el-form class="userinfo_form" ref="userinfoFormRef" :model="form">
				<el-row>
					<el-col :span="24">
						<el-form-item label="Expert account" prop="zhuanjiazhanghao">
							<el-input class="list_inp" v-model="user.zhuanjiazhanghao" readonly placeholder="Expert account" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Expert Name" prop="zhuanjiaxingming">
							<el-input class="list_inp" v-model="user.zhuanjiaxingming"  placeholder="Expert Name" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Icon" prop="touxiang">
							<uploads
								action="file/upload" 
								tip="Please upload your profile picture"
								style="width: 100%;text-align: left;"
								:fileUrls="user.touxiang?user.touxiang:''" 
								@change="jiankangzhuanjiatouxiangUploadSuccess">
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
								<el-option v-for="item in jiankangzhuanjiaxingbieLists" :label="item" :value="item"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Age" prop="nianling">
							<el-input class="list_inp" v-model="user.nianling"  placeholder="Age" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Field" prop="shanzhanglingyu">
							<el-input class="list_inp" v-model="user.shanzhanglingyu"  placeholder="Field" clearable />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Personal Profile" prop="gerenjianjie">
							<el-input class="list_inp" v-model="user.gerenjianjie"  placeholder="Personal Profile" clearable />
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
	const tableName = ref('jiankangzhuanjia')
	const user = ref({})
	const jiankangzhuanjiaxingbieLists = ref([])
	const init = () => {
		jiankangzhuanjiaxingbieLists.value = "Man,Woman".split(',')
	}
	const jiankangzhuanjiatouxiangUploadSuccess=(fileUrls)=> {
	    user.value.touxiang = fileUrls;
	}
	const onSubmit = () => {
		if(user.value.touxiang!=null){
			user.value.touxiang = user.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
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
