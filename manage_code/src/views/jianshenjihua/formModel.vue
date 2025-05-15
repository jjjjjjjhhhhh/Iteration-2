<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="User account" prop="yonghuzhanghao">
							<el-input class="list_inp" v-model="form.yonghuzhanghao" placeholder="User account"
                                type="text"
								:readonly="!isAdd||disabledForm.yonghuzhanghao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="User Name" prop="yonghuxingming">
							<el-input class="list_inp" v-model="form.yonghuxingming" placeholder="User Name"
                                type="text"
								:readonly="!isAdd||disabledForm.yonghuxingming?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Plan Title" prop="jihuabiaoti">
							<el-input class="list_inp" v-model="form.jihuabiaoti" placeholder="Plan Title"
                                type="text"
								:readonly="!isAdd||disabledForm.jihuabiaoti?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Start date" prop="kaishiriqi">
							<el-date-picker
								class="list_date"
								v-model="form.kaishiriqi"
								format="YYYY / MM / DD "
								value-format="YYYY-MM-DD"
								type="datetime"
								:readonly="!isAdd||disabledForm.kaishiriqi?true:false"
								placeholder="Please select a start date" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="End Date" prop="jieshuriqi">
							<el-date-picker
								class="list_date"
								v-model="form.jieshuriqi"
								format="YYYY / MM / DD "
								value-format="YYYY-MM-DD"
								type="datetime"
								:readonly="!isAdd||disabledForm.jieshuriqi?true:false"
								placeholder="Please select an end date" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Scheduled time" prop="jihuashijian">
							<el-date-picker
								class="list_date"
								v-model="form.jihuashijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.jihuashijian?true:false"
								placeholder="Please select a scheduled time" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="notes" prop="beizhu">
							<el-input class="list_inp" v-model="form.beizhu" placeholder="notes"
                                type="text"
								:readonly="!isAdd||disabledForm.beizhu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Plan content" prop="jihuaneirong">
							<editor :value="form.jihuaneirong" placeholder="Enter Plan content" :readonly="!isAdd||disabledForm.jihuaneirong?true:false"
								class="list_editor" @change="(e)=>editorChange(e,'jihuaneirong')"></editor>
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
	const tableName = 'jianshenjihua'
	const formName = 'Fitness plan'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        yonghuzhanghao : false,
        yonghuxingming : false,
        jihuabiaoti : false,
        kaishiriqi : false,
        jieshuriqi : false,
        jihuaneirong : false,
        jihuashijian : false,
        beizhu : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		yonghuzhanghao: [
		],
		yonghuxingming: [
		],
		jihuabiaoti: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		kaishiriqi: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		jieshuriqi: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		jihuaneirong: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		jihuashijian: [
		],
		beizhu: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yonghuzhanghao: '',
			yonghuxingming: '',
			jihuabiaoti: '',
			kaishiriqi: '',
			jieshuriqi: '',
			jihuaneirong: '',
			jihuashijian: '',
			beizhu: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.jihuaneirong = res.data.data.jihuaneirong?(res.data.data.jihuaneirong.replace(reg,'../../../cl0184941100/file')):'';
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
			form.value.jihuashijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='yonghuzhanghao'){
					form.value.yonghuzhanghao = row[x];
					disabledForm.value.yonghuzhanghao = true;
					continue;
				}
				if(x=='yonghuxingming'){
					form.value.yonghuxingming = row[x];
					disabledForm.value.yonghuxingming = true;
					continue;
				}
				if(x=='jihuabiaoti'){
					form.value.jihuabiaoti = row[x];
					disabledForm.value.jihuabiaoti = true;
					continue;
				}
				if(x=='kaishiriqi'){
					form.value.kaishiriqi = row[x];
					disabledForm.value.kaishiriqi = true;
					continue;
				}
				if(x=='jieshuriqi'){
					form.value.jieshuriqi = row[x];
					disabledForm.value.jieshuriqi = true;
					continue;
				}
				if(x=='jihuaneirong'){
					form.value.jihuaneirong = row[x];
					disabledForm.value.jihuaneirong = true;
					continue;
				}
				if(x=='jihuashijian'){
					form.value.jihuashijian = row[x];
					disabledForm.value.jihuashijian = true;
					continue;
				}
				if(x=='beizhu'){
					form.value.beizhu = row[x];
					disabledForm.value.beizhu = true;
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
			if(json.hasOwnProperty('yonghuzhanghao')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yonghuzhanghao = json.yonghuzhanghao
				disabledForm.value.yonghuzhanghao = true;
			}
			if(json.hasOwnProperty('yonghuxingming')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.yonghuxingming = json.yonghuxingming
				disabledForm.value.yonghuxingming = true;
			}
		})
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
				//日期选择器
				.list_date {
				}
				// 富文本
				.list_editor {
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
