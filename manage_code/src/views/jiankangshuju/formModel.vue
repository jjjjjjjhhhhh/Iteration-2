<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="50%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row >
					<el-col :span="24">
						<el-form-item label="Record date" prop="jiluriqi">
							<el-date-picker
								class="list_date"
								v-model="form.jiluriqi"
								format="YYYY / MM / DD "
								value-format="YYYY-MM-DD"
								type="datetime"
								:readonly="!isAdd||disabledForm.jiluriqi?true:false"
								placeholder="Please select the record date" />
						</el-form-item>
					</el-col>
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
							<el-input class="list_inp" v-model="form.xingbie" placeholder="gender"
                                type="text"
								:readonly="!isAdd||disabledForm.xingbie?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="High" prop="tizhong">
							<el-input class="list_inp" v-model="form.tizhong" placeholder="High"
                                type="text"
								:readonly="!isAdd||disabledForm.tizhong?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Blood pressure" prop="xueya">
							<el-input class="list_inp" v-model="form.xueya" placeholder="Blood pressure"
                                type="text"
								:readonly="!isAdd||disabledForm.xueya?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Blood sugar" prop="xuetang">
							<el-input class="list_inp" v-model="form.xuetang" placeholder="Blood sugar"
                                type="text"
								:readonly="!isAdd||disabledForm.xuetang?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Heart rate" prop="xinlv">
							<el-input class="list_inp" v-model="form.xinlv" placeholder="Heart rate"
                                type="text"
								:readonly="!isAdd||disabledForm.xinlv?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Cholesterol" prop="danguchun">
							<el-input class="list_inp" v-model="form.danguchun" placeholder="Cholesterol"
                                type="text"
								:readonly="!isAdd||disabledForm.danguchun?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="Sleep quality" prop="shuimianzhiliang">
							<el-input class="list_inp" v-model="form.shuimianzhiliang" placeholder="Sleep quality"
                                type="text"
								:readonly="!isAdd||disabledForm.shuimianzhiliang?true:false" />
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
	const tableName = 'jiankangshuju'
	const formName = 'Health data'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
        jiluriqi : false,
        yonghuzhanghao : false,
        yonghuxingming : false,
        touxiang : false,
        xingbie : false,
        tizhong : false,
        xueya : false,
        xuetang : false,
        xinlv : false,
        danguchun : false,
        shuimianzhiliang : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
    
	const rules = ref({
		jiluriqi: [
		],
		yonghuzhanghao: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		yonghuxingming: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		touxiang: [
		],
		xingbie: [
		],
		tizhong: [
			{required: true,message: 'Enter',trigger: 'blur'}, 
		],
		xueya: [
		],
		xuetang: [
		],
		xinlv: [
		],
		danguchun: [
		],
		shuimianzhiliang: [
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
			jiluriqi: '',
			yonghuzhanghao: '',
			yonghuxingming: '',
			touxiang: '',
			xingbie: '',
			tizhong: '',
			xueya: '',
			xuetang: '',
			xinlv: '',
			danguchun: '',
			shuimianzhiliang: '',
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
			form.value.jiluriqi = context?.$toolUtil.getCurDate()
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
				if(x=='jiluriqi'){
					form.value.jiluriqi = row[x];
					disabledForm.value.jiluriqi = true;
					continue;
				}
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
				if(x=='tizhong'){
					form.value.tizhong = row[x];
					disabledForm.value.tizhong = true;
					continue;
				}
				if(x=='xueya'){
					form.value.xueya = row[x];
					disabledForm.value.xueya = true;
					continue;
				}
				if(x=='xuetang'){
					form.value.xuetang = row[x];
					disabledForm.value.xuetang = true;
					continue;
				}
				if(x=='xinlv'){
					form.value.xinlv = row[x];
					disabledForm.value.xinlv = true;
					continue;
				}
				if(x=='danguchun'){
					form.value.danguchun = row[x];
					disabledForm.value.danguchun = true;
					continue;
				}
				if(x=='shuimianzhiliang'){
					form.value.shuimianzhiliang = row[x];
					disabledForm.value.shuimianzhiliang = true;
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
			if(json.hasOwnProperty('touxiang')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.touxiang = json.touxiang
				disabledForm.value.touxiang = true;
			}
			if(json.hasOwnProperty('xingbie')&& context?.$toolUtil.storageGet("role")!="管理员"){
				form.value.xingbie = json.xingbie
				disabledForm.value.xingbie = true;
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
