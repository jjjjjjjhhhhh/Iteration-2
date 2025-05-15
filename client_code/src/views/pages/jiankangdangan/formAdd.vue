<template>
	<div class="edit_view" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="/" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form ref="formRef" :model="form" class="add_form" label-width="120px" :rules="rules">
			<el-row>
				<el-col :span="12">
					<el-form-item label="User Account" prop="yonghuzhanghao">
						<el-input class="list_inp"
                                  v-model="form.yonghuzhanghao"
                                  placeholder="User Account"
                                  type="text"
							      :readonly="!isAdd||disabledForm.yonghuzhanghao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="User Name" prop="yonghuxingming">
						<el-input class="list_inp"
                                  v-model="form.yonghuxingming"
                                  placeholder="User Name"
                                  type="text"
							      :readonly="!isAdd||disabledForm.yonghuxingming?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Gender" prop="xingbie">
						<el-input class="list_inp"
                                  v-model="form.xingbie"
                                  placeholder="Gender"
                                  type="text"
							      :readonly="!isAdd||disabledForm.xingbie?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Avatar" prop="touxiang">
						<uploads
							:disabled="!isAdd||disabledForm.touxiang?true:false"
							action="file/upload" 
							tip="Please upload avatar"
							style="width: 100%;text-align: left;"
							:fileUrls="form.touxiang?form.touxiang:''" 
							@change="touxiangUploadSuccess">
						</uploads>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="Date of Birth" prop="chushengriqi">
						<el-date-picker
							class="list_date"
							v-model="form.chushengriqi"
							format="YYYY-MM-DD"
							value-format="YYYY-MM-DD"
							type="datetime"
							:readonly="!isAdd||disabledForm.chushengriqi?true:false"
							placeholder="Please select date of birth"
							style="width: 100%;" />
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="Age" prop="nianling">
						<el-input class="list_inp"
                                  v-model="form.nianling"
                                  placeholder="Age"
                                  type="text"
							      :readonly="!isAdd||disabledForm.nianling?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Height" prop="shengao">
						<el-input class="list_inp"
                                  v-model="form.shengao"
                                  placeholder="Height"
                                  type="text"
							      :readonly="!isAdd||disabledForm.shengao?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Weight" prop="tizhong">
						<el-input class="list_inp"
                                  v-model="form.tizhong"
                                  placeholder="Weight"
                                  type="text"
							      :readonly="!isAdd||disabledForm.tizhong?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Blood Type" prop="xuexing">
						<el-input class="list_inp"
                                  v-model="form.xuexing"
                                  placeholder="Blood Type"
                                  type="text"
							      :readonly="!isAdd||disabledForm.xuexing?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="12">
					<el-form-item label="Allergic Drugs" prop="guominyaowu">
						<el-input class="list_inp"
                                  v-model="form.guominyaowu"
                                  placeholder="Allergic Drugs"
                                  type="text"
							      :readonly="!isAdd||disabledForm.guominyaowu?true:false" />
					</el-form-item>
				</el-col>

				<el-col :span="24">
					<el-form-item label="Medical History" prop="jiwangbingshi">
						<el-input v-model="form.jiwangbingshi" placeholder="Medical History" type="textarea"
						:readonly="!isAdd||disabledForm.jiwangbingshi?true:false"
						/>
					</el-form-item>
				</el-col>
				<el-col :span="24">
					<el-form-item label="Family Medical History" prop="jiazubingshi">
						<el-input v-model="form.jiazubingshi" placeholder="Family Medical History" type="textarea"
						:readonly="!isAdd||disabledForm.jiazubingshi?true:false"
						/>
					</el-form-item>
				</el-col>
			</el-row>
			<div class="formModel_btn_box">
				<el-button class="formModel_cancel" @click="backClick">Cancel</el-button>
				<el-button class="formModel_confirm"
                           @click="save"
                           type="success"
				>
					Save
				</el-button>
			</div>
		</el-form>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import moment from "moment";
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'jiankangdangan'
	const formName = 'Health Record'
	//基础信息
	const breadList = ref([{
		name: formName
	}])
	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//form表单
	const form = ref({
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
	})
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
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
	const isAdd = ref(false)
	//表单验证
	const rules = ref({
		yonghuzhanghao: [
		],
		yonghuxingming: [
		],
		xingbie: [
			{required: true,message: 'Please input',trigger: 'blur'}, 
		],
		touxiang: [
		],
		chushengriqi: [
		],
		nianling: [
			{required: true,message: 'Please input',trigger: 'blur'}, 
		],
		shengao: [
			{required: true,message: 'Please input',trigger: 'blur'}, 
		],
		tizhong: [
			{required: true,message: 'Please input',trigger: 'blur'}, 
		],
		xuexing: [
			{required: true,message: 'Please input',trigger: 'blur'}, 
		],
		jiwangbingshi: [
		],
		jiazubingshi: [
		],
		guominyaowu: [
		],
	})
	//头像上传回调
	const touxiangUploadSuccess=(e)=>{
		form.value.touxiang = e
	}
	//methods

	//methods
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			form.value = res.data.data
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init = (formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null) => {
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
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
		}
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
			if(json.hasOwnProperty('yonghuzhanghao') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.yonghuzhanghao = json.yonghuzhanghao
				disabledForm.value.yonghuzhanghao = true;
			}
			if(json.hasOwnProperty('yonghuxingming') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.yonghuxingming = json.yonghuxingming
				disabledForm.value.yonghuxingming = true;
			}
			if(json.hasOwnProperty('xingbie') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.xingbie = json.xingbie
				disabledForm.value.xingbie = true;
			}
			if(json.hasOwnProperty('touxiang') && context?.$toolUtil.storageGet("frontRole")!="管理员"){
				form.value.touxiang = json.touxiang
				disabledForm.value.touxiang = true;
			}
            if (localStorage.getItem('autoSave')) {
                localStorage.removeItem('autoSave')
                save()
            }
		})
	}
	//初始化
	//取消
	const backClick = () => {
		history.back()
	}
	//提交
	const save=()=>{
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
					crossUserId = context?.$toolUtil.storageGet('userid')
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
							}).then(res=>{
                                context?.$toolUtil.message(`Operation successful`,'success')
                                history.back()
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(res=>{
                        context?.$toolUtil.message(`Operation successful`,'success')
                        history.back()
					})
				}
			}
		})
	}
	//修改跨表数据
	const changeCrossData=(row)=>{
        if(type.value == 'cross'){
            context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
	onMounted(()=>{
		type.value = route.query.type?route.query.type:'add'
		let row = null
		let table = null
		let statusColumnName = null
		let tips = null
		let statusColumnValue = null
		if(type.value == 'cross'){
			row = context?.$toolUtil.storageGet('crossObj')?JSON.parse(context?.$toolUtil.storageGet('crossObj')):{}
			table = context?.$toolUtil.storageGet('crossTable')
			statusColumnName = context?.$toolUtil.storageGet('crossStatusColumnName')
			tips = context?.$toolUtil.storageGet('crossTips')
			statusColumnValue = context?.$toolUtil.storageGet('crossStatusColumnValue')
		}
		init(route.query.id?route.query.id:null, type.value,'', row, table, statusColumnName, tips, statusColumnValue)
	})
    onUnmounted(()=>{
        Object.keys(localStorage).map(item=>{
            if(item.startsWith('cross')){
                localStorage.removeItem(item)
            }
        })
    })
</script>
<style lang="scss" scoped>
	// 面包屑盒子
	.bread_view {
		:deep(.breadcrumb) {
			.el-breadcrumb__separator {
			}
			.first_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
			.second_breadcrumb {
				.el-breadcrumb__inner {
				}
			}
		}
	}
	// 表单
	.add_form{
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
		.formModel_cancel {
		}
		.formModel_cancel:hover {
		}
		
		.formModel_confirm {
		}
		.formModel_confirm:hover {
		}
	}
</style>
<style lang="scss">
.edit_view {
    width: 1200px;
    margin: 20px auto;
    padding: 0px ;
    background: #fff;
    overflow: hidden;
    font-size: 16px;
    color:#666;
}
.edit_view .add_form{
    width: 100%;
    padding: 30px;
    border:0px solid #eee
}
.edit_view .add_form .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.edit_view .add_form .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: rgb(51, 51, 51);
    font-weight: 500;
}
.edit_view .add_form .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.edit_view .add_form .el-form-item .el-form-item__content .list_inp{
    height: 36px;
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    padding: 0px 10px;
    width: 100%;
    box-sizing: border-box;
    background: rgb(255, 255, 255);
    font-size: 16px;
}


.edit_view .add_form .el-form-item .el-form-item__content .list_date{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: 100%;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}




.edit_view .add_form .el-form-item .el-form-item__content .el-textarea{
}
.edit_view .add_form .el-form-item .el-form-item__content .el-textarea .el-textarea__inner{
    width: 100%;
    min-height: 150px;
    padding: 12px;
    border: 1px solid rgb(221, 221, 221);
    border-radius: 0px;
    color: #666;
    font-size: 16px;
}


.edit_view .add_form .el-form-item .el-form-item__content .el-upload--picture-card{
    background-color: rgb(255, 255, 255);
    width: 100px;
    height: 90px;
    line-height: 100px;
    text-align: center;
    border: 1px solid rgb(221, 221, 221);
    border-radius: 0px;
    cursor: pointer;
}

.edit_view .add_form .el-form-item .el-form-item__content .el-upload--picture-card .el-icon{
    font-size: 32px;
    color: #999;
}

.edit_view .add_form .el-form-item .el-form-item__content .img-uploader .el-upload__tip{
    font-size: 15px;
    color: #666;
    margin: 0;
}



</style>