<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="Expert account" prop="zhuanjiazhanghao">
							<el-input class="list_inp" v-model="form.zhuanjiazhanghao" placeholder="Expert account"
								type="text" :readonly="!isAdd||disabledForm.zhuanjiazhanghao?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Expert password" prop="zhuanjiamima">
							<el-input class="list_inp" v-model="form.zhuanjiamima" placeholder="Expert password"
								type="password" :readonly="!isAdd||disabledForm.zhuanjiamima?true:false" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Expert Name" prop="zhuanjiaxingming">
							<el-input class="list_inp" v-model="form.zhuanjiaxingming" placeholder="Expert Name"
                                type="text"
								:readonly="!isAdd||disabledForm.zhuanjiaxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item prop="touxiang"
									  label="Icon"
						>
							<uploads
								:disabled="!isAdd||disabledForm.touxiang?true:false"
								action="file/upload"
								tip="Please upload your profile picture"
								style="width: 100%;text-align: left;"
								:fileUrls="form.touxiang?form.touxiang:''" 
								@change="touxiangUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="gender" prop="xingbie">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.xingbie?true:false"
								v-model="form.xingbie" 
								placeholder="Please select gender"
								>
								<el-option v-for="(item,index) in xingbieLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Age" prop="nianling">
							<el-input class="list_inp" v-model="form.nianling" placeholder="Age"
                                type="text"
								:readonly="!isAdd||disabledForm.nianling?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Field" prop="shanzhanglingyu">
							<el-input v-model="form.shanzhanglingyu" placeholder="Field" type="textarea"
							:readonly="!isAdd||disabledForm.shanzhanglingyu?true:false"
							/>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Personal Profile" prop="gerenjianjie">
							<el-input v-model="form.gerenjianjie" placeholder="Personal Profile" type="textarea"
							:readonly="!isAdd||disabledForm.gerenjianjie?true:false"
							/>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">Cancel</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						Submit
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'jiankangzhuanjia'
	const formName = 'Health expert'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        zhuanjiazhanghao : false,
        zhuanjiamima : false,
        zhuanjiaxingming : false,
        touxiang : false,
        xingbie : false,
        nianling : false,
        shanzhanglingyu : false,
        gerenjianjie : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		zhuanjiazhanghao: [
		],
		zhuanjiamima: [
		],
		zhuanjiaxingming: [
		],
		touxiang: [
		],
		xingbie: [
		],
		nianling: [
		],
		shanzhanglingyu: [
		],
		gerenjianjie: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//头像上传回调
	const touxiangUploadSuccess=(e)=>{
		form.value.touxiang = e
	}
	//gender列表
	const xingbieLists = ref([])

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			zhuanjiazhanghao: '',
			zhuanjiamima: '',
			zhuanjiaxingming: '',
			touxiang: '',
			xingbie: '',
			nianling: '',
			shanzhanglingyu: '',
			gerenjianjie: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = 'Add' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = 'View' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = 'Edit' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
			// getInfo()
			for(let x in row){
				if(x=='zhuanjiazhanghao'){
					form.value.zhuanjiazhanghao = row[x];
					disabledForm.value.zhuanjiazhanghao = true;
					continue;
				}
				if(x=='zhuanjiamima'){
					form.value.zhuanjiamima = row[x];
					disabledForm.value.zhuanjiamima = true;
					continue;
				}
				if(x=='zhuanjiaxingming'){
					form.value.zhuanjiaxingming = row[x];
					disabledForm.value.zhuanjiaxingming = true;
					continue;
				}
				if(x=='touxiang'){
					form.value.touxiang = row[x];
					disabledForm.value.touxiang = true;
					continue;
				}
				if(x=='xingbie'){
					form.value.xingbie = row[x];
					disabledForm.value.xingbie = true;
					continue;
				}
				if(x=='nianling'){
					form.value.nianling = row[x];
					disabledForm.value.nianling = true;
					continue;
				}
				if(x=='shanzhanglingyu'){
					form.value.shanzhanglingyu = row[x];
					disabledForm.value.shanzhanglingyu = true;
					continue;
				}
				if(x=='gerenjianjie'){
					form.value.gerenjianjie = row[x];
					disabledForm.value.gerenjianjie = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
		})
		xingbieLists.value = "Man,Woman".split(',')
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
		if(form.value.touxiang!=null) {
			form.value.touxiang = form.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
		}
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = user.value.id
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`Operation successful`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`Operation successful`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('Please provide complete information')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
        if(type.value == 'cross'){
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {
				}
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
					}
				}
				// 长文本
				.el-textarea__inner {
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
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
