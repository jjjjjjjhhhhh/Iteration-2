<template>
	<div class="center_view" :style='{}'>
		<div class="section_title">
			<span>{{formName}}</span>
		</div>
		<div class="usersView">
			<div class="usersTabView">
				<div class="usersTab" :class="tabIndex=='center'?'usersTabActive':''" @click="tabClick({tableName:'center'})">Personal Center</div>
				<div class="usersTab " :class="tabIndex=='updatePassword'?'usersTabActive':''" @click="tabClick({tableName:'updatePassword'})">Change Password</div>
                <div class="usersTab " :class="tabIndex=='chat'?'usersTabActive':''" @click="tabClick({tableName:'chat'})">Chat History</div>
				<template v-for="(item,index) in menuList">
					<div v-if="item.child.length>1" class="usersTab" @mouseenter="usersTabHover(index)"
						 @mouseleave="usersTabLeave">
						{{item.menu}}
						<el-collapse-transition>
							<div class="usersTabHoverView" v-if="usersTabIndex==index">
								<div class="usersTabHoverTab" v-for="(items,indexs) in item.child" @click="tabClick(items)">
									{{items.menu}}
                                </div>
							</div>
						</el-collapse-transition>
					</div>
					<div v-else-if="hasBack(item.child[0]) && item.child[0].tableName!='chat'" class="usersTab" @click="tabClick(item.child[0])">
						{{item.child[0].menu}}
					</div>
				</template>
                <div class="usersTab" v-if="btnAuth('storeup','查看')" @click="tabClick({tableName:'storeup',type:1})">My Collections</div>
			</div>
			<div class="usersBox updateInfo" v-if="tabIndex=='center'">
				<el-form class="usersForm" ref="userFormRef" :model="userForm" label-width="120px" :rules="rules">
					<el-row>
						<el-col :span="12">
							<el-form-item prop="zhuanjiazhanghao" label="Expert Account">
								<el-input class="list_inp" v-model="userForm.zhuanjiazhanghao" placeholder="Expert Account" readonly></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item prop="zhuanjiaxingming" label="Expert Name">
								<el-input class="list_inp" v-model="userForm.zhuanjiaxingming" placeholder="Expert Name" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item prop="touxiang" label="Avatar">
								<uploads
									action="file/upload" 
									tip="Please upload avatar"
									style="width: 100%;text-align: left;"
									:fileUrls="userForm.touxiang?userForm.touxiang:''" 
									@change="touxiangUploadSuccess">
								</uploads>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="Gender" prop="xingbie">
								<el-select
									class="list_sel"
									v-model="userForm.xingbie" 
									placeholder="Please select gender"
									style="width:100%;"
									>
									<el-option v-for="(item,index) in xingbieLists" :label="item" :value="item">
									</el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item prop="nianling" label="Age">
								<el-input class="list_inp" v-model="userForm.nianling" placeholder="Age" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="Expertise" prop="shanzhanglingyu">
								<el-input class="list_textarea" v-model="userForm.shanzhanglingyu" placeholder="Expertise" type="textarea"
								/>
							</el-form-item>
						</el-col>
						<el-col :span="24">
							<el-form-item label="Personal Profile" prop="gerenjianjie">
								<el-input class="list_textarea" v-model="userForm.gerenjianjie" placeholder="Personal Profile" type="textarea"
								/>
							</el-form-item>
						</el-col>
					</el-row>
					<div class="formModel_btn_box">
						<el-button class="formModel_confirm" @click="updateSession">Update Info</el-button>
						<el-button class="formModel_cancel" @click="loginout" type="danger">Logout</el-button>
					</div>
				</el-form>
			</div>
			<div class="usersBox updatePassword" v-if="tabIndex=='updatePassword'">
				<el-form class="usersForm" ref="passwordFormRef" :model="passwordForm" label-width="120px"
					:rules="passwordRules">
					<el-row>
						<el-col :span="12">
							<el-form-item label="Original Password" prop="mima">
								<el-input class="list_inp" v-model="passwordForm.mima" placeholder="Original Password"
									type="password"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="New Password" prop="newmima">
								<el-input class="list_inp" v-model="passwordForm.newmima" placeholder="New Password"
									type="password"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="Confirm Password" prop="newmima2">
								<el-input class="list_inp" v-model="passwordForm.newmima2" placeholder="Confirm Password"
									type="password"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<div class="formModel_btn_box">
						<el-button class="formModel_confirm" @click="updatePassword">Change Password</el-button>
					</div>
				</el-form>
			</div>
			<div class="usersBox chatRecord" v-if="tabIndex=='chat'">
				<div class="z-box" :style='{"width":"70%","padding":"20px","margin":"0 auto"}'>
					<div class="section-content" v-for="item in recordList" :key="item.id" @click="chatClick(item)">
						<img :src="item.picture?$config.url + item.picture:require('@/assets/avatar.png')" style="width: 60px;height:60px;border-radius: 50%;object-fit: cover;"
							 alt="">
						<div style="margin: 0 0 0 10px;display: flex;flex-direction: column;align-items: flex-start;">
							<div :style='{"fontSize":"14px","color":"#000","flex":"1","fontWeight":"bold"}'
								 class="item-style">{{item.name}}</div>
							<div :style='{"color":"#666","flex":"1","fontSize":"14px","lineHeight":"1.5","display":"flex","alignItems":"center"}'
								 class="item-style">
								<div v-if="item.notreadnum" style="padding: 0 5px;height: 16px;border-radius: 50%;text-align: center;line-height: 16px;font-size: 12px;background: #f00;color:#fff;width: auto;margin: 0 2px 0 0">{{item.notreadnum}}</div>
								{{item.content.split('/').length>1&&item.content.split('/')[0]=='upload'?'[图片]':item.content}}
							</div>
						</div>
					</div>
					<div class="noList" v-if="!recordList.length">
						No chat history
					</div>
				</div>
			</div>
		</div>
		<el-dialog v-model="chatVisible" @close="clearChat" :title="nowname">
			<div class="chat-content" id="chat-content">
				<div v-bind:key="item.id" v-for="(item,index) in chatList">
                    <div v-if="index>1&&moment(chatList[index-1].addtime).date()!=moment(item.addtime).date()||index==0"
                         style="font-size: 12px;text-align: center;margin: 4px 0;">
                        {{moment(item.addtime).format("MM-DD HH:mm")}}
                    </div>
					<div v-if="item.uid==userForm.id" class="right-content">
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="warning"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
						<img :src="mypic?$config.url + mypic:require('@/assets/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;flex-shrink: 0;object-fit: cover;" />
					</div>
					<div v-else class="left-content">
						<img :src="nowfpic?$config.url + nowfpic:require('@/assets/avatar.png')" alt="" style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;flex-shrink: 0;object-fit: cover;" />
						<el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
								  type="success"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
						<el-image v-else fit="cover" :src="item.content?$config.url + item.content:''" style="width: 100px;height: 100px;" :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
					</div>
					<div class="clear-float"></div>
				</div>
			</div>
			<div slot="footer" class="dialog-footer">
				<el-input @keydown.enter.native="addChat" v-model="chatForm.content" placeholder="Please enter content" style="width: calc(100% - 180px);float: left;border: 1px solid #ccc;margin-right: 10px;">
				</el-input>
				<el-button :disabled="chatForm.content?false:true" type="primary" @click="addChat">Send</el-button>
				<el-upload style="display: inline-block;margin: 0 0 0 6px;" class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess"
						   :show-file-list="false" accept="image/*,.mp4">
					<el-button type="success">Upload File</el-button>
				</el-upload>
			</div>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		ElMessageBox
	} from 'element-plus'
	import moment from 'moment'
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import { useStore } from 'vuex'
	const store = useStore()
	import menu from '@/utils/menu'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	//基础信息
	const tableName = 'jiankangzhuanjia'
	const formName = 'Personal Center'
	//基础信息
	const uploadUrl = context.$config.url + 'file/upload'
	//个人信息
	const userFormRef = ref(null)
	const userForm = ref({})
    //权限验证
    const btnAuth = (e, a) => {
        return context?.$toolUtil.isBackAuth(e, a)
    }
	//修改密码
	const passwordFormRef = ref(null)
	const passwordForm = ref({
		mima: '',
		newmima: '',
		newmima2: ''
	})
	const passwordRules = ref({
		mima: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
		newmima: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
		newmima2: [{
			required: true,
			message: 'Enter',
			trigger: 'blur'
		}, ],
	})
	//验证规则
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
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			context?.$toolUtil.storageSet("frontName", res.data.data.zhuanjiazhanghao)
			context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			userForm.value = res.data.data
		})
	}
	//菜单跳转
	const tabIndex = ref('center')
	const tabClick = (item) => {
		if (item.tableName == 'center') {
			tabIndex.value = 'center'
			return
		}
		if (item.tableName == 'updatePassword') {
			passwordForm.value = {
				mima: '',
				newmima: '',
				newmima2: ''
			}
			tabIndex.value = 'updatePassword'
			return
		}
		if (item.tableName == 'chat') {
			getRecordList()
			tabIndex.value = 'chat'
			return
		}
		if(item.tableName=='examrecord'&&item.menuJump=='22'){
			router.push(`/index/examfailrecord?centerType=1`)
			return
		}
		if(item.tableName=='forum'&&item.menuJump=='14'){
			router.push(`/index/forumList?centerType=1&myType=1`)
			return
		}
        if(item.tableName=='storeup'){
            router.push(`/index/storeupList?centerType=1&type=${item.type}`)
            return;
        }
        router.push(`/index/${item.classname||item.tableName}List?centerType=1${item.menuJump?'&menuJump='+item.menuJump:''}`)
	}
    const hasBack = (menu)=>{
        if(menu.tableName=='storeup'){
            return false
        }
        return true
    }
	// 修改密码
	const updatePassword = async ()=>{
		passwordFormRef.value.validate(async (valid) => {
			if (valid) {
				var nowpassword = ''
				await context?.$http({
					url:'encrypt/md5?text=' + passwordForm.value.mima,
					method: 'get'
				}).then(res=>{
					nowpassword = res.data.data
				})
				if(nowpassword != userForm.value.zhuanjiamima){
					context?.$toolUtil.message('The original password is incorrect', 'error')
					return false
				}
				if(passwordForm.value.newmima != passwordForm.value.newmima2){
					context?.$toolUtil.message('The password entered twice is inconsistent', 'error')
					return false
				}
                if(passwordForm.value.mima==passwordForm.value.newmima){
                    context?.$toolUtil.message('The new password cannot be the same as the original password', 'error')
                    return false
                }
				userForm.value.zhuanjiamima = passwordForm.value.newmima
				store.dispatch('user/update',userForm.value).then(res=>{
					if(res?.data&&res.data.code==0){
						context?.$toolUtil.message('update success','success')
						passwordForm.value = {
							mima: '',
							newmima: '',
							newmima2: ''
						}
						getSession()
					}
				})
			}
		})
	}
	//菜单
	const menuList = ref([])
	const role = ref('')
	//头像上传回调
	const touxiangUploadSuccess=(e)=>{
		userForm.value.touxiang = e
	}
	//性别列表
	const xingbieLists = ref([])
	//初始化
	const init = () => {
		const menus = menu.list()
		let arr = []
		if (menus) {
			menuList.value = menus
		}
		role.value = context?.$toolUtil.storageGet('frontRole')
		for (let i = 0; i < menuList.value.length; i++) {
			if (menuList.value[i].roleName == role.value) {
				arr = menuList.value[i].backMenu
				break;
			}
		}
		menuList.value = arr
		xingbieLists.value = "Man,Woman".split(',')
		getSession()
	}
	//菜单悬浮的显示与隐藏
	const usersTabIndex = ref(-1)
	const usersTabHover = (index) => {
		usersTabIndex.value = index
	}
	const usersTabLeave = () => {
		usersTabIndex.value = -1
	}
	//富文本
	const editorChange = (e,name) =>{
		userForm.value[name] = e
	}
	//保存
	const updateSession = () => {
		userFormRef.value.validate((valid)=>{
			if(valid){
				if(userForm.value.touxiang!=null){
					userForm.value.touxiang = userForm.value.touxiang.replace(new RegExp(context?.$config.url,"g"),"");
				}
				store.dispatch('user/update',userForm.value).then(res=>{
					if(res?.data&&res.data.code==0){
						context?.$toolUtil.message('update success','success')
						getSession()
					}
				})
			}
		})
	}
	//退出登录
	const loginout = () => {
		context?.$toolUtil.message('Exit successful', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
	}
	const recordList = ref([])
	const chatVisible = ref(false)
	const nowfid = ref(0)
	const nowfpic = ref('')
	const nowname = ref('')
	const mypic = ref(localStorage.getItem('headportrait'))
	const chatList = ref([])
	const chatForm = ref({
		content: ''
	})
	const chatTimer = ref(null)
	const getRecordList = () => {
		context.$http.get('chatfriend/page2', {
			params: {
				uid: Number(localStorage.getItem('userid')),
				type: 2
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				recordList.value = res.data.data.list
			}
		})
	}
	const chatClick = (row) => {
		nowfid.value = row.fid
		nowfpic.value = row.picture
		nowname.value = row.name
		getChatList()
		chatVisible.value = true
	}
    const scrollFlag = ref(true)
	const getChatList = () => {
		context.$http.get('chatmessage/mlist', {
			params: {
				page: 1,
				limit: 1000,
				uid: Number(localStorage.getItem('userid')),
				fid: nowfid.value
			}
		}).then(res => {
			if (res.data && res.data.code == 0) {
				chatList.value = res.data.data.list
				let div = document.getElementsByClassName('chat-content')[0]
				setTimeout(() => {
                    if (div){
                        if(div.scrollTop+div.clientHeight==div.scrollHeight || scrollFlag.value){
                            div.scrollTop = div.scrollHeight
                            scrollFlag.value = false
                        }
                    }
				}, 0)
				chatTimer.value = setTimeout(() => {
					getChatList()
				}, 5000)
			}
		})
	}
	const clearChat = () => {
		clearTimeout(chatTimer.value)
		chatList.value = []
		getRecordList()
	}
	const uploadSuccess = (res) => {
		if (res.code == 0) {
			clearTimeout(chatTimer.value)
			context.$http.post('chatmessage/add', {
				uid: Number(localStorage.getItem('userid')),
				fid: nowfid.value,
				content: 'file/' + res.file,
				format: 2
			}).then(res2 => {
				chatForm.value = {
					content: ''
				}
                scrollFlag.value = true
				getChatList()
			})
		}
	}
	const addChat = () => {
        if(!chatForm.value.content.trim())return context.$message.error("The message content cannot be empty")
		clearTimeout(chatTimer.value)
		context.$http.post('chatmessage/add', {
			uid: Number(localStorage.getItem('userid')),
			fid: nowfid.value,
			content: chatForm.value.content,
			format: 1
		}).then(res2 => {
			chatForm.value = {
				content: ''
			}
            scrollFlag.value = true
			getChatList()
		})
	}
	init()
</script>

<style lang="scss" scoped>
	.usersView {

		.usersTabView {

			.usersTab {

				.usersTabHoverView {
					padding: 10px 0px;
					z-index: 999;
					clip-path: polygon(10% 0, 100% 0%, 90% 100%, 0% 100%);
					top: 0px;
					left: -200px;
					background: #fac12c;
					display: block;
					width: 200px;
					position: absolute;
					.usersTabHoverTab {
						color: #fff;
						line-height: 40px;
					}
					.usersTabHoverTab:hover {
						color: #ff0;
					}
				}
			}
			.usersTab:hover {
			}
			.usersTabActive {
			}
		}

		.usersBox {
			.usersForm {
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
	}
	.section-content {
		cursor: pointer;
		padding: 20px;
		box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.3);
		margin: 0 0 20px;
		color: #333;
		background: #fff;
		display: flex;
		width: 100%;
		border-color: #efefef;
		border-width: 0;
		align-items: center;
		border-style: solid;
		position: relative;
	}

	.section-content:hover {
		color: #fff;
		background: #DF847F10;
	}
	.chat-content {
		padding-bottom: 20px;
		width: 100%;
		margin-bottom: 10px;
		max-height: 300px;
		height: 300px;
		overflow-y: scroll;
		border: 1px solid #eeeeee;
		background: #fff;

		.left-content {
			float: left;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}

		.right-content {
			float: right;
			margin-bottom: 10px;
			padding: 10px;
			max-width: 80%;
			display: flex;
			align-items: center;
		}
	}

	.clear-float {
		clear: both;
	}
	.noList {
		color: #9e9e9e;
		width: 100%;
		text-align: center;
		padding: 60px 0;
	}
</style>
<style lang="scss">
/**总盒子**/
.center_view {
    margin: 20px auto;
    width: 1200px;
    position: relative;
    background: rgb(255, 255, 255);
    font-size:16px;
    color:#666;
}
/**标题**/
.center_view .section_title{
    width: 1200px;
    margin:0px auto;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
}
.center_view .section_title span{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;
}

/**内容区**/
.center_view .usersView{
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    margin: 20px auto;
}

/**右部 总盒子**/
.center_view .usersView .usersBox{
    width: calc(100% - 240px);
    background: #fff;
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    box-shadow: none;
    border: 0px solid #eee;
}
/**form盒子**/
.center_view .usersView .usersBox .usersForm{
    width: 100%;
    padding: 30px;
}
.center_view .usersView .usersBox .usersForm .el-form-item{
    margin: 0px 0px 20px;
    display: flex;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__label{
    width: 150px;
    background: none;
    text-align: right;
    display: block;
    font-size: 16px;
    color: rgb(51, 51, 51);
    font-weight: 500;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% - 120px);
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_inp{
    height: 36px;
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    padding: 0px 10px;
    width: 100%;
    box-sizing: border-box;
    background: rgb(255, 255, 255);
    font-size: 16px;
}

/**会员**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item{
    display: flex;
    align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item .vip_inp{
    height: 36px;
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    padding: 0px 10px;
    width: 80%;
    box-sizing: border-box;
    background: rgb(255, 255, 255);
    font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .vip_item .vip_btn{
    background: var(--theme);
    color: rgb(255, 255, 255);
    height: 36px;
    line-height: 36px;
    padding: 0px 20px;
    border-radius: 0px;
    margin: 0px 0px 0px 5px;
    border-color: var(--theme);
}
/**end**/

/**下拉选择**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_sel{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: calc(100% - 120px);
    padding: 0px 10px;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}
/**end**/

/**日期选择**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_date{
    line-height: 36px;
    border: 1px solid rgb(221, 221, 221);
    box-sizing: border-box;
    width: 100%;
    border-radius: 0px;
    background: rgb(255, 255, 255);
    font-size: 16px;
}
/**end**/

/**radio**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio{
    display: flex;
    width: calc(100% - 120px);
    align-items: center;
    flex-wrap: wrap;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio{
    width: 30%;
    margin: 0px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio .el-radio__inner{
    border-color: #999;
    background: #fff;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio .el-radio__label{
    color: #999;
    font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio.is-checked .el-radio__inner{
    border-color: var(--theme);
    background: var(--theme);
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_radio .el-radio.is-checked .el-radio__label{
    color: var(--theme);
    font-size: 16px;
}
/**end**/

/**checkbox**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox{
    display: flex;
    width: calc(100% - 120px);
    flex-wrap: wrap;
    align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox{
    width: 20%;
    margin: 0px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox .el-checkbox__inner{
    border-color: #999;
    background: #fff;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox .el-checkbox__label{
    color: #999;
    font-size: 16px;
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox.is-checked .el-checkbox__inner{
    border-color: var(--theme);
    background: var(--theme);
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_checkbox .el-checkbox.is-checked .el-checkbox__label{
    color: var(--theme);
    font-size: 16px;
}
/**end**/

/**textarea**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_textarea{
}
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_textarea .el-textarea__inner{
    width: 100%;
    min-height: 150px;
    padding: 12px;
    border: 1px solid rgb(221, 221, 221);
    border-radius: 0px;
    color: #666;
    font-size: 16px;
}
/**end**/

/**图片上传**/
/* 盒子 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--picture-card{
    background-color: rgb(255, 255, 255);
    width: 100px;
    height: 90px;
    line-height: 100px;
    text-align: center;
    border: 1px solid rgb(221, 221, 221);
    border-radius: 0px;
    cursor: pointer;
}
/* 图标 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--picture-card .el-icon{
    font-size: 32px;
    color: #999;
}
/* 提示语 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .img-uploader .el-upload__tip{
    font-size: 15px;
    color: #666;
    margin: 0;
}
/**end**/

/**文件上传**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger{
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(221, 221, 221);
    border-radius: 0px;
    box-sizing: border-box;
    width: 360px;
    height: 180px;
    text-align: center;
    cursor: pointer;
    overflow: hidden;
}
/* 图标 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger .el-icon-upload{
    font-size: 67px;
    color: var(--theme);
    line-height: 50px;
}
/* 提示语 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .upload-demo .el-upload__tip{
    font-size: 15px;
    color: #666;
    margin: 0px 0 0;
    line-height:1;
}
/* 点击上传 */
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .el-upload--text .el-upload-dragger em{
    color: var(--theme);
    font-size: 15px;
}
/**end**/

/**富文本**/
.center_view .usersView .usersBox .usersForm .el-form-item .el-form-item__content .list_editor{
    width: 100%;
    height: auto;
    margin: 0px;
    padding: 0px;
    border-radius: 0px;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(204, 204, 204);
    background-color: rgb(255, 255, 255);
}
/**end**/

/**按钮**/
.center_view .formModel_btn_box{
    display: flex;
    align-items: center;
    justify-content:center;
    width: 100%;
    padding: 30px 0px 10px 150px;
    margin: 20px 0px 0px;
}
/**更新信息**/
.center_view .formModel_btn_box .formModel_confirm{
    padding: 0px 40px;
    width: auto;
    height: 42px;
    border-radius: 0px;
    border: 1px solid #828BD6;
    border-width:1px 0;
    background: none;
    cursor: pointer;
    color: #828BD6;
    font-size: 16px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, #828BD6, #828BD6);
    background-image: linear-gradient(to left, #828BD6, #828BD6);
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(10% 0, 100% 0, 90% 100%, 0% 100%);
}
.center_view .formModel_btn_box .formModel_confirm:hover{
    background-size:100% 100%;
    color: #fff;
    border-color:#828BD6;
}
/**退出登陆**/
.center_view .formModel_btn_box .formModel_cancel{
    margin: 0px 10px;
    padding: 0px 40px;
    width: auto;
    height: 42px;
    border-radius: 0px;
    border: 1px solid var(--theme);
    border-width:1px 0;
    background: none;
    cursor: pointer;
    color: var(--theme);
    font-size: 16px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(10% 0, 100% 0, 90% 100%, 0% 100%);
}
.center_view .formModel_btn_box .formModel_cancel:hover{
    background-size:100% 100%;
    color: #fff;
    border-color:var(--theme);
}

/**tab总盒子**/
.center_view .usersView .usersTabView{
    width: 220px;
    background: none;
    padding: 0px 0px;
    box-sizing: border-box;
    order:2;
}
/**item**/
.center_view .usersView .usersTabView .usersTab{
    position: relative;
    width:100%;
    padding: 0px 10px;
    margin: 0 0 20px;
    box-sizing: border-box;
    cursor: pointer;
    border: 0px dashed #ccc;
    border-radius: 0px;
    background: url(http://clfile.zggen.cn/20241021/d1a1e5952f654080b0ebc08ac9e37db0.png) no-repeat left center / 100% 100%,#fac12c;
    color: #fff;
    text-align: center;
    height:50px;
    line-height:50px;
}
.center_view .usersView .usersTabView .usersTab:hover{
    color: rgb(255, 255, 255) !important;
    border-color: rgba(0, 118, 202,1) !important;
    background: url(http://clfile.zggen.cn/20241021/65b8a1bdcadc4ee29d64031450909c38.png) no-repeat left center / 100% 100%;
}
.center_view .usersView .usersTabView .usersTab.usersTabActive{
    color: rgb(255, 255, 255) !important;
    border-color: rgba(0, 118, 202,1) !important;
    background: url(http://clfile.zggen.cn/20241021/65b8a1bdcadc4ee29d64031450909c38.png) no-repeat left center / 100% 100%;
}
</style>