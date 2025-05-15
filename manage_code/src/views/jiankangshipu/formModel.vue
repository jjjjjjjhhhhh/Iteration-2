<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="Recipe Name" prop="shipumingcheng">
							<el-input class="list_inp" v-model="form.shipumingcheng" placeholder="Recipe Name"
                                type="text"
								:readonly="!isAdd||disabledForm.shipumingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item prop="fengmian"
									  label="cover"
						>
							<uploads
								:disabled="!isAdd||disabledForm.fengmian?true:false"
								action="file/upload"
								tip="Please upload the cover"
								style="width: 100%;text-align: left;"
								:fileUrls="form.fengmian?form.fengmian:''" 
								@change="fengmianUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Required ingredients" prop="suoxushicai">
							<el-input class="list_inp" v-model="form.suoxushicai" placeholder="Required ingredients"
                                type="text"
								:readonly="!isAdd||disabledForm.suoxushicai?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Recipe efficacy" prop="shipugongxiao">
							<el-input class="list_inp" v-model="form.shipugongxiao" placeholder="Recipe efficacy"
                                type="text"
								:readonly="!isAdd||disabledForm.shipugongxiao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Crowd" prop="shiherenqun">
							<el-input class="list_inp" v-model="form.shiherenqun" placeholder="Crowd"
                                type="text"
								:readonly="!isAdd||disabledForm.shiherenqun?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Release time" prop="fabushijian">
							<el-date-picker
								class="list_date"
								v-model="form.fabushijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.fabushijian?true:false"
								placeholder="Please choose a publishing time" />
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Production method" prop="zhizuofangfa">
							<el-input v-model="form.zhizuofangfa" placeholder="Production method" type="textarea"
							:readonly="!isAdd||disabledForm.zhizuofangfa?true:false"
							/>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Details and Introduction" prop="xiangqingjianjie">
							<editor :value="form.xiangqingjianjie" placeholder="Enter Details and Introduction" :readonly="!isAdd||disabledForm.xiangqingjianjie?true:false"
								class="list_editor" @change="(e)=>editorChange(e,'xiangqingjianjie')"></editor>
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
	const tableName = 'jiankangshipu'
	const formName = 'Healthy recipes '
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        shipumingcheng : false,
        fengmian : false,
        suoxushicai : false,
        shipugongxiao : false,
        shiherenqun : false,
        zhizuofangfa : false,
        fabushijian : false,
        xiangqingjianjie : false,
        thumbsupNumber : false,
        crazilyNumber : false,
        storeupNumber : false,
        discussNumber : false,
        clicktime : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		shipumingcheng: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		fengmian: [
		],
		suoxushicai: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		shipugongxiao: [
		],
		shiherenqun: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		zhizuofangfa: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		fabushijian: [
		],
		xiangqingjianjie: [
		],
		thumbsupNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		crazilyNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		storeupNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		discussNumber: [
			{ validator: context.$toolUtil.validator.intNumber, trigger: 'blur' },
		],
		clicktime: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//封面上传回调
	const fengmianUploadSuccess=(e)=>{
		form.value.fengmian = e
	}

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			shipumingcheng: '',
			fengmian: '',
			suoxushicai: '',
			shipugongxiao: '',
			shiherenqun: '',
			zhizuofangfa: '',
			fabushijian: '',
			xiangqingjianjie: '',
			thumbsupNumber: '',
			crazilyNumber: '',
			storeupNumber: '',
			discussNumber: '',
			clicktime: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.xiangqingjianjie = res.data.data.xiangqingjianjie?(res.data.data.xiangqingjianjie.replace(reg,'../../../cl0184941100/file')):'';
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
			form.value.fabushijian = context?.$toolUtil.getCurDateTime()
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
				if(x=='shipumingcheng'){
					form.value.shipumingcheng = row[x];
					disabledForm.value.shipumingcheng = true;
					continue;
				}
				if(x=='fengmian'){
					form.value.fengmian = row[x];
					disabledForm.value.fengmian = true;
					continue;
				}
				if(x=='suoxushicai'){
					form.value.suoxushicai = row[x];
					disabledForm.value.suoxushicai = true;
					continue;
				}
				if(x=='shipugongxiao'){
					form.value.shipugongxiao = row[x];
					disabledForm.value.shipugongxiao = true;
					continue;
				}
				if(x=='shiherenqun'){
					form.value.shiherenqun = row[x];
					disabledForm.value.shiherenqun = true;
					continue;
				}
				if(x=='zhizuofangfa'){
					form.value.zhizuofangfa = row[x];
					disabledForm.value.zhizuofangfa = true;
					continue;
				}
				if(x=='fabushijian'){
					form.value.fabushijian = row[x];
					disabledForm.value.fabushijian = true;
					continue;
				}
				if(x=='xiangqingjianjie'){
					form.value.xiangqingjianjie = row[x];
					disabledForm.value.xiangqingjianjie = true;
					continue;
				}
				if(x=='thumbsupNumber'){
					form.value.thumbsupNumber = row[x];
					disabledForm.value.thumbsupNumber = true;
					continue;
				}
				if(x=='crazilyNumber'){
					form.value.crazilyNumber = row[x];
					disabledForm.value.crazilyNumber = true;
					continue;
				}
				if(x=='storeupNumber'){
					form.value.storeupNumber = row[x];
					disabledForm.value.storeupNumber = true;
					continue;
				}
				if(x=='discussNumber'){
					form.value.discussNumber = row[x];
					disabledForm.value.discussNumber = true;
					continue;
				}
				if(x=='clicktime'){
					form.value.clicktime = row[x];
					disabledForm.value.clicktime = true;
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
		if(form.value.fengmian!=null) {
			form.value.fengmian = form.value.fengmian.replace(new RegExp(context?.$config.url,"g"),"");
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
				//日期选择器
				.list_date {
				}
				// 富文本
				.list_editor {
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
