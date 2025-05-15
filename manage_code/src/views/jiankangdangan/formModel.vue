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
						<el-form-item label="gender" prop="xingbie">
							<el-input class="list_inp" v-model="form.xingbie" placeholder="gender"
                                type="text"
								:readonly="!isAdd||disabledForm.xingbie?true:false" />
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
						<el-form-item label="Date of Birth" prop="chushengriqi">
							<el-date-picker
								class="list_date"
								v-model="form.chushengriqi"
								format="YYYY / MM / DD "
								value-format="YYYY-MM-DD"
								type="datetime"
								:readonly="!isAdd||disabledForm.chushengriqi?true:false"
								placeholder="Please select your date of birth" />
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
						<el-form-item label="High" prop="shengao">
							<el-input class="list_inp" v-model="form.shengao" placeholder="High"
                                type="text"
								:readonly="!isAdd||disabledForm.shengao?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="weight" prop="tizhong">
							<el-input class="list_inp" v-model="form.tizhong" placeholder="weight"
                                type="text"
								:readonly="!isAdd||disabledForm.tizhong?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="blood type" prop="xuexing">
							<el-input class="list_inp" v-model="form.xuexing" placeholder="blood type"
                                type="text"
								:readonly="!isAdd||disabledForm.xuexing?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Allergy medication" prop="guominyaowu">
							<el-input class="list_inp" v-model="form.guominyaowu" placeholder="Allergy medication"
                                type="text"
								:readonly="!isAdd||disabledForm.guominyaowu?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Past medical history" prop="jiwangbingshi">
							<el-input v-model="form.jiwangbingshi" placeholder="Past medical history" type="textarea"
							:readonly="!isAdd||disabledForm.jiwangbingshi?true:false"
							/>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="Family medical history" prop="jiazubingshi">
							<el-input v-model="form.jiazubingshi" placeholder="Family medical history" type="textarea"
							:readonly="!isAdd||disabledForm.jiazubingshi?true:false"
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
	const tableName = 'jiankangdangan'
	const formName = 'Health records'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        yonghuzhanghao : false,
        yonghuxingming : false,
        xingbie : false,
        touxiang : false,
        chushengriqi : false,
        nianling : false,
        shengao : false,
        tizhong : false,
        xuexing : false,
        jiwangbingshi : false,
        jiazubingshi : false,
        guominyaowu : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		yonghuzhanghao: [
		],
		yonghuxingming: [
		],
		xingbie: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		touxiang: [
		],
		chushengriqi: [
		],
		nianling: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		shengao: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		tizhong: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		xuexing: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		jiwangbingshi: [
		],
		jiazubingshi: [
		],
		guominyaowu: [
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

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			yonghuzhanghao: '',
			yonghuxingming: '',
			xingbie: '',
			touxiang: '',
			chushengriqi: '',
			nianling: '',
			shengao: '',
			tizhong: '',
			xuexing: '',
			jiwangbingshi: '',
			jiazubingshi: '',
			guominyaowu: '',
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
				if(x=='xingbie'){
					form.value.xingbie = row[x];
					disabledForm.value.xingbie = true;
					continue;
				}
				if(x=='touxiang'){
					form.value.touxiang = row[x];
					disabledForm.value.touxiang = true;
					continue;
				}
				if(x=='chushengriqi'){
					form.value.chushengriqi = row[x];
					disabledForm.value.chushengriqi = true;
					continue;
				}
				if(x=='nianling'){
					form.value.nianling = row[x];
					disabledForm.value.nianling = true;
					continue;
				}
				if(x=='shengao'){
					form.value.shengao = row[x];
					disabledForm.value.shengao = true;
					continue;
				}
				if(x=='tizhong'){
					form.value.tizhong = row[x];
					disabledForm.value.tizhong = true;
					continue;
				}
				if(x=='xuexing'){
					form.value.xuexing = row[x];
					disabledForm.value.xuexing = true;
					continue;
				}
				if(x=='jiwangbingshi'){
					form.value.jiwangbingshi = row[x];
					disabledForm.value.jiwangbingshi = true;
					continue;
				}
				if(x=='jiazubingshi'){
					form.value.jiazubingshi = row[x];
					disabledForm.value.jiazubingshi = true;
					continue;
				}
				if(x=='guominyaowu'){
					form.value.guominyaowu = row[x];
					disabledForm.value.guominyaowu = true;
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
			if(json.hasOwnProperty('xingbie')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.xingbie = json.xingbie
				disabledForm.value.xingbie = true;
			}
			if(json.hasOwnProperty('touxiang')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.touxiang = json.touxiang
				disabledForm.value.touxiang = true;
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
				//日期选择器
				.list_date {
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
