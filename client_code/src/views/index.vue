<template>
	<div class="home">
		<div class="index_top">
			<div class="index_top_title"><span> Family Health and Life Management System for Obese Population</span></div>
			<div class="index_top_right">
				<div class="chat" style="cursor: pointer;position: relative;" @click="chatClick">
					<i class="iconfont icon-kefu3"></i>
					<span>Customer service chat</span>
				</div>
				<el-button v-if="!Token" class="login" type="success" icon="User" circle @click="loginClick">
					Login
				</el-button>
				<div class="user" v-if="Token">
					<el-dropdown class="avatar-container" trigger="hover">
						<div class="avatar-wrapper">
							<img class="user-avatar" :src="store.getters['user/avatar']" style="width: 50px">
							<div class="nickname">{{store.getters['user/username']}}</div>
							<el-icon class="el-icon-arrow-down">
								<arrow-down />
							</el-icon>
						</div>
						<template #dropdown>
							<el-dropdown-menu class="user-dropDown" slot="dropdown">
								<el-dropdown-item @click="menuHandler('center')" class="center">
									<span>Personal Center</span>
								</el-dropdown-item>
								<el-dropdown-item @click="loginOut" class="loginOut">
									<span>Log Out</span>
								</el-dropdown-item>
							</el-dropdown-menu>
						</template>
					</el-dropdown>
				</div>
			</div>
		</div>
		<el-scrollbar class="contain_view">
            <div class="menu-wrapper">
                <el-scrollbar wrap-class="scrollbar-wrapper" class="menu_scrollbar">
                    <el-menu :default-openeds="[]" :unique-opened="true" :default-active="menuIndex"
                         class="menu_view" mode="horizontal" :ellipsis="false" @select="menuChange" :key="menuIndex">
                        <el-menu-item class="first-item" index="/index/home" @click="menuHandler('/')">
                            <template #title>
                                <span>Index</span>
                            </template>
                        </el-menu-item>
                        <template v-for="(menu,index) in menuList" :key="menu.menu">
                            <el-sub-menu v-if="menu.child.length>1" :index="menu.name" class="first-item" :teleported="true">
                                <template #title>
                                    <span>{{ menu.name }}</span>
                                </template>
                                <el-menu-item class="second-item" v-for=" (child,index1) in menu.child" :key="index1"
                                              :index="child.url" @click="menuHandler(child.url)">{{ child.name }}
                                </el-menu-item>
                            </el-sub-menu>
                            <el-menu-item v-else :index="menu.child[0].url" class="first-item" @click="menuHandler(menu.child[0].url)">
                                <template #title>
                                    <span>{{menu.child[0].name}}</span>
                                </template>
                            </el-menu-item>
                        </template>
                    </el-menu>
                </el-scrollbar>
            </div>
			<div class="rotation_view" >
				<mySwiper :type="3" :data="rotationList" :autoHeight="false" :autoplay="true"
					:loop="false" :navigation="true" :pagination="false"
					:paginationType="1" :scrollbar="false" :slidesPerView="1"
					:spaceBetween="20" :centeredSlides="false"
					:freeMode="false" :effectType="1"
					:direction="horizontal">
					<template #default="scope">
						<img :style='{}' :src="scope.row.value?$config.url + scope.row.value:''" @click="carouselClick(scope.row.url)">
					</template>
				</mySwiper>
			</div>
			<router-view :key="routerKey"/>
			<el-backtop :right="100" :bottom="100" />
			<div class="bottom_view">
				<img class="bottom_img" src="http://chy2.gdnxeco.com/20230912/0d5ae5e141c14b06a52a7c4c2f8ea4cf.jpg" alt="">
				<div class="bottom_company"></div>
				<div class="bottom_record"></div>
				<div class="bottom_desc"></div>
			</div>
		</el-scrollbar>
		<el-dialog v-model="chatVisible" title="Customer service chat" width="70%" destroy-on-close @close="chatClose">
			<el-form ref="formRef" :model="chatForm" class="chat_form">
				<div class="chat-content">
					<div :key="item.id" v-for="item in chatList">
						<div class="chat_time" v-if="item.showTime">{{item.addtime}}</div>
						<div v-if="item.ask" class="right-content">
							<el-alert v-if="!item.img" class="text-content" :title="item.ask" :closable="false"
								type="success"></el-alert>
                            <video v-else-if="item.ask.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                                <source  :src="$config.url + item.ask">
                            </video>
							<el-image v-if="item.img" :src="$config.url + item.img" class="chat_img"
								fit="scale-down" :preview-src-list="[$config.url + item.img]"></el-image>
						</div>
						<div v-else class="left-content">
							<el-alert v-if="!item.img" class="text-content" :title="item.reply" :closable="false"
								type="warning"></el-alert>
                            <video v-else-if="item.reply.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                                <source  :src="$config.url + item.reply">
                            </video>
							<el-image v-if="item.img" :src="$config.url + item.img" class="chat_img"
								fit="scale-down" :preview-src-list="[$config.url + item.img]"></el-image>
						</div>
						<div class="clear-float"></div>
					</div>
				</div>
				<div class="input_box">
					<el-input class="input" v-model="chatForm.ask" placeholder="Enter" type="textarea" />
					<img src="http://chy2.gdnxeco.com/20230912/f60a0c39a6d34289b50c151949711b67.png" class="inp_add"
						@click="askShow=!askShow" alt="">
				</div>
				<div class="upload_box" v-if="askShow">
					<el-upload class="upload-demo" :action="action" :on-success="uploadSuccess" :show-file-list="false" accept="image/*,.mp4">
						<el-button class="upload_btn">Upload files</el-button>
					</el-upload>
				</div>
				<div class="save_box">
					<el-button class="save_btn" @click="askSave">Send</el-button>
				</div>
			</el-form>
		</el-dialog>
        <el-dialog v-model="remindVisibie" :title="remindForm.title" append-to-body width="60%">
            <div v-html="remindForm.content"></div>
        </el-dialog>
	</div>
</template>
<script setup>
	import menu from '@/utils/menu'
	import axios from 'axios'
    import moment from "moment";
	import {
		ref,
		onBeforeUnmount,
		getCurrentInstance,
		nextTick,
		computed,
		watch,
        provide
	} from 'vue';
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const router = useRouter()
	const route = useRoute();
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const Token = ref('')
	const dateTimer = ref(null)
    const nowDate = ref(new Date())
	if(localStorage.getItem('frontToken') && !store.getters['user/session'].id){
		store.dispatch('user/getSession')
	}
	onBeforeUnmount(() => {
		clearInterval(dateTimer.value)
        clearInterval(chatTimer.value)
        clearTimeout(timesAll.value)
        clearTimeout(timesOnce.value)
        context.$notify.closeAll()
	})
	// 获取默认菜单index
	const setMenuIndex = (path)=>{
        if(path){
            menuIndex.value = path.replace('Detail','List')
        }else{
            menuIndex.value = ''
        }
	}
	// 默认菜单index
	const menuIndex = ref('')
    const routerKey = ref(Math.random())
	watch(() => router.currentRoute.value,(value, oldValue) => {
		Token.value = context?.$toolUtil.storageGet('frontToken')
        if(value.path==oldValue?.path){
            //改变路由query时重新加载router-view
            routerKey.value = Math.random()
        }
        if(value.query.centerType){
            setMenuIndex()
        }else{
            setMenuIndex(value.path)
        }
	},{
		immediate:true
	})
	const init = () => {
		// 获取菜单
		getMenu()
		// 赋值token
		Token.value = context?.$toolUtil.storageGet('frontToken')
		// 时间
		dateTimer.value = setInterval(() => {
            nowDate.value = new Date()
		}, 1000)
		// 轮播图
		getRotationList()
		if(Token.value){
			getSession()
            getRecommendAll()
            getRecommendOnce()
		}
	}
	// 切换菜单保存index
	const menuChange = (e)=>{
		if(e=='chat')return
	}
	// 轮播图
	const rotationList = ref([])
	const baseUrl = context?.$config.url
	const getRotationList = () => {
		context?.$http({
			url: 'config/list',
			method: 'get',
			params: {
				page: 1,
				limit: 3
			}
		}).then(res => {
			rotationList.value = res.data.data.list
		})
	}

    // 轮播图跳转
    const carouselClick = (url)=>{
        if (url) {
            if (url.startsWith('http')) {
                window.open(url)
            } else {
                context.$router.push(url)
            }
        }
    }
	const menuList = ref([])
	const role = ref('')
	const getMenu = () => {
		let params = {
			page: 1,
			limit: 1,
			sort: 'id',
		}
		context?.$http({
			url: "menu/list",
			method: "get",
			params: params
		}).then(res => {
			context?.$toolUtil.storageSet("menus", res.data.data.list[0].menujson);
		})
		menuList.value = context?.$config.menuList
	}
	const loginClick = () => {
		context?.$toolUtil.storageSet('toPath',window.history.state.current)
		router.push('/login')
	}
	const loginOut = () => {
		context?.$toolUtil.message('Exit successful', 'success')
		context?.$toolUtil.storageClear()
		router.replace('/index/home')
		Token.value = ''
        clearInterval(chatTimer.value)
	}
	//客服聊天
	const chatVisible = ref(false)
	const chatForm = ref({})
	const chatList = ref([])
    const scrollFlag = ref(true)
    const chatTimer = ref(null)
	//客服聊天弹窗
	const chatClick = () => {
		getChatList()
		chatVisible.value = true
		chatTimer.value = setInterval(() => {
			getChatList()
		}, 3000)
	}
	//客服聊天聊天列表
	const getChatList = () => {
		context?.$http({
			url: 'chat/page',
			method: 'get',
			params: {
				limit: 1000,
				sort: 'addtime',
				order: 'asc',
				userid: context?.$toolUtil.storageGet('userid')
			}
		}).then(res => {
			for (let x in res.data.data.list) {
				if (res.data.data.list[x].ask) {
					if (res.data.data.list[x].ask.split('/').length > 1) {
						if (res.data.data.list[x].ask.split('/')[0] == 'file') {
							res.data.data.list[x].img = res.data.data.list[x].ask
						}
					}
				}
				if (res.data.data.list[x].reply) {
					if (res.data.data.list[x].reply.split('/').length > 1) {
						if (res.data.data.list[x].reply.split('/')[0] == 'file') {
							res.data.data.list[x].img = res.data.data.list[x].reply
						}
					}
				}
				if(x==0){
					res.data.data.list[x].showTime = 1
				}else{
					let jian = new Date(res.data.data.list[x].addtime).getTime() - new Date(res.data.data.list[x-1].addtime).getTime()
					if(jian>18000){
						res.data.data.list[x].showTime = 1
					}else{
						res.data.data.list[x].showTime = 0
					}
				}
			}
			chatList.value = res.data.data.list
			nextTick(() => {
				let div = document.getElementsByClassName('chat-content')[0]
				setTimeout(() => {
                    if (div){
                        if(div.scrollTop+div.clientHeight==div.scrollHeight || scrollFlag.value){
                            div.scrollTop = div.scrollHeight
                            scrollFlag.value = false
                        }
                    }
				}, 100)
			})
		})
	}
	const chatClose = ()=>{
		setMenuIndex()
        clearInterval(chatTimer.value)
	}
	//图片上传
	const action = ref(`${context?.$config.name}/file/upload`)
	const uploadSuccess = (e) => {
		let params = {
			ask: 'file/' + e.file,
			userid: context?.$toolUtil.storageGet('userid')
		}
		context?.$http({
			url: 'chat/add',
			method: 'post',
			data: params
		}).then(res => {
			context?.$toolUtil.message('Successfully sent', 'success', () => {
				getChatList()
				chatForm.value.ask = ''
			})
		})
	}
	//操作显示
	const askShow = ref(false)
	//发送信息
	const askSave = () => {
		let params = JSON.parse(JSON.stringify(chatForm.value))
		params.userid = context?.$toolUtil.storageGet('userid')
		context?.$http({
			url: 'chat/add',
			method: 'post',
			data: params
		}).then(res => {
			context?.$toolUtil.message('Successfully sent', 'success')
			chatForm.value.ask = ''
			getChatList()
		})
	}
    const remindVisibie = ref(false)
    const remindForm = ref({})
    const timesAll = ref(null)
    const timesOnce = ref(null)

    const getRecommendAll = async ()=>{
        let res = await context.$http.get('popupremind/list',{params: {type:'全局'}})
        let list = res.data.data.list
        for (let x in list) {
            if (new Date()>new Date(list[x].remindTime)) {
                let obj = await context.$http.get('storeup/page',{params:{refid: list[x].id,tablename: 'popupremind',type: '61'}})
                if (!obj.data.data.list.length) {
                    context.$notify({
                        title: list[x].title,
                        dangerouslyUseHTMLString: true,
                        message: list[x].brief,
                        duration: 0,
                        position: 'bottom-right',
                        onClick() {
                            remindForm.value = list[x]
                            remindVisibie.value = true
                        }
                    })
                    context.$http.post('storeup/add',{
                        userid: store.getters['user/session'].id,
                        refid: list[x].id,
                        tablename: 'popupremind',
                        name: list[x].title,
                        type: '61',
                        picture:''
                    })
                }
            }
        }
        timesAll.value = setTimeout(() => {
            getRecommendAll()
        }, 10000)
    }
    const getRecommendOnce = async ()=>{
        if(!localStorage.getItem('frontToken'))return
        let res = await context.$http.get('popupremind/page',{params: {type:'私人'}})
        let list = res.data.data.list
        for (let x in list) {
            if (new Date()>new Date(list[x].remindTime)) {
                let obj = await context.$http.get('storeup/page',{params:{refid: list[x].id,tablename: 'popupremind',type: '61'}})
                if (!obj.data.data.list.length) {
                    context.$notify({
                        title: list[x].title,
                        dangerouslyUseHTMLString: true,
                        message: list[x].brief,
                        duration: 0,
                        position: 'top-right',
                        onClick() {
                            remindForm.value = list[x]
                            remindVisibie.value = true
                        }
                    })
                    context.$http.post('storeup/add',{
                        userid: store.getters['user/session'].id,
                        refid: list[x].id,
                        tablename: 'popupremind',
                        name: list[x].title,
                        type: '61',
                        picture:''
                    })
                }
            }
        }
        timesOnce.value = setTimeout(() => {
            getRecommendOnce()
        }, 10000)
    }
	//菜单跳转
	const menuHandler = (name) => {
		if(name=='chat')return chatClick()
		if (name == 'center') {
			name = `${context?.$toolUtil.storageGet('frontSessionTable')}Center`
			menuChange('center')
		}
		router.push(name)
	}
	// 获取用户信息
	const getSession = () =>{
		context?.$http({
			url: `${context?.$toolUtil.storageGet('frontSessionTable')}/session`,
			method:'get'
		}).then(res=>{
			context?.$toolUtil.storageSet('userid',res.data.data.id)
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet("frontName", res.data.data.yonghuzhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'yonghu'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'jiankangzhuanjia'){
				context?.$toolUtil.storageSet("frontName", res.data.data.zhuanjiazhanghao)
			}
			if(context?.$toolUtil.storageGet('frontSessionTable') == 'jiankangzhuanjia'){
				context?.$toolUtil.storageSet('headportrait',res.data.data.touxiang||'')
			}
		})
	}
	init()
</script>
<style lang="scss" scoped>
	/* 头部 */
	.index_top {
		.index_top_title {
		}
		.index_top_right {
			line-height: 1;
			.weather_time_view {
				display: flex;
				align-items: center;
				.weather {
					padding: 0 10px;
					flex-direction: row;
					display: flex;
					font-size: 16px;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.city {
						padding: 0;
						margin: 0 10px 0 0;
					}
					.wea {
					}
				}
				.time {
					padding: 0 20px;
					flex-direction: row;
					display: flex;
					font-size: 16px;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.date {
						padding: 0;
						margin: 0 10px 0 0;
					}
					.timer {
						padding: 0;
						margin: 0;
					}
				}
			}
		}
		// 登录按钮
		:deep(.el-button--success) {
			border-radius: 4px;
			padding: 10px;
			margin: 0 10px 0 0;
			color: #333;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--success:hover) {
		}
		// 登出按钮
		:deep(.el-button--danger) {
			border-radius: 4px;
			padding: 10px;
			color: #333;
			background: none;
			font-size: 16px;
			border-color: transparent;
		}
		:deep(.el-button--danger:hover) {
		}
	}
	// 轮播盒子
	.rotation_view {
	}
	// 底部
	.bottom_view {
		// 图片
		.bottom_img {
			border-radius: 100%;
			object-fit: cover;
			display: none;
			width: 44px;
			height: 44px;
		}
		// 公司
		.bottom_company {
			margin: 10px 0 0;
		}
		// 备案号
		.bottom_record {
			margin: 10px 0 0;
		}
		// 联系方式
		.bottom_desc {
			margin: 10px 0 0;
		}
	}
	
	
	

	.contain_view {
		margin: 0;
		background: #fff;
		position: relative;
		height: calc(100% - 60px);
	}
	.el-aside {
		transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-moz-transition: width 0.15s;
		-webkit-transition: width 0.15s;
		-o-transition: width 0.15s;
	}
	.el-sub-menu__hide-arrow{
		display: block !important;
	}
	.menu_scrollbar {
	}
	// 总盒子
	.menu_view {
		// 一级菜单
		:deep(.first-item) {

			// 图标
		
			// 标题
			.el-sub-menu__title,
			span {
			}
		
			//箭头
			.el-sub-menu__icon-arrow {
			}
		}
		// 选中
		:deep(.is-active) {
			color: #fff !important;
		}
		
		// 悬浮
		:deep(.first-item:hover) {
			color: #fff !important;
		}
	}
	// 聊天总盒子
	.chat_form {
		// 聊天内容盒子
		.chat-content {
			// 聊天时间
			.chat_time{
			}
			// 提问盒子
			.left-content {
				// 文字样式
				:deep(.el-alert--success){
					.el-alert__title {
						font-size: inherit;
					}
				}
				// 聊天图片
				.chat_img {
				}
			}
			// 回复盒子
			.right-content {
				// 文字样式
				:deep(.el-alert--warning){
					.el-alert__title {
						font-size: inherit;
					}
				}
				// 聊天图片
				.chat_img {
				}
			}
		}
		// 输入内容盒子
		.input_box{
			// 输入框
			:deep(.el-textarea__inner) {
			}
			// 操作按钮
			.inp_add {
			}
		}
		// 操作盒子
		.upload_box {
			:deep(.upload-demo) {
				// 上传按钮
				.upload_btn {
				}
				// 上传按钮-悬浮
				.upload_btn:hover {
				}
			}
		}
		// 发送盒子
		.save_box {
			// 发送按钮
			.save_btn {
			}
			// 发送按钮-悬浮
			.save_btn:hover {
			}
		}
		.clear-float {
			clear: both;
		}
	}
</style>
<style lang="scss">
	// 二级
	.menu_popper {
		border:none !important;
		background: none !important;

		// 二级总盒子
		.el-menu--popup {
		}

		// 二级菜单
		.second-item {
		}
		
		// 选中
		.is-active {
		}
		
		// 悬浮
		.second-item:hover {
		}
	}
</style>
<style lang="scss">

.index_top {
    box-sizing: border-box;
    width: 100%;
    display: flex;
    align-items: center;
    height: 44px;
    background: #fff;
    color: #333;
    justify-content: space-between;
    padding: 0px calc((100% - 1200px) / 2);
    z-index: 1002;
    position: relative;
}

.index_top .index_top_title{
    font-size: 20px;
    color: inherit;
    font-weight: bold;
    margin-left:20px;
}

.index_top .index_top_right{
    display: flex;
    align-items: center;
    margin-right:20px;
    color: inherit;
}

.index_top .notice-btn{
    background: none;
    border: 0px solid #dcdfe6;
    color: inherit;
    margin: 0;
    padding: 10px;
    font-size: 16px;
    border-radius: 0px;
}
.index_top .notice-btn:hover{
    color: #333;
}

.index_top .notice-btn .iconfont{
    font-size: 16px;
    color: inherit;
}


.index_top .index_top_right .chat{
    margin-right:10px;
}
.index_top .index_top_right .chat:hover{
    color: #333;
}
.index_top .index_top_right .chat .iconfont{
    font-size: 16px;
}
.index_top .index_top_right .chat span{
    font-size: 16px;
}

.index_top .index_top_right .cart{
    margin-right:10px;
    font-size: 16px;
}
.index_top .index_top_right .cart:hover{
    color: #333;
}
.index_top .index_top_right .cart .iconfont{
    font-size: 16px;
    margin-right:5px;
}


.index_top .user .el-dropdown{
    color: inherit;
}
.index_top .user .avatar-wrapper{
    display: flex;
    align-items: center;
    cursor:pointer;
    color: inherit;
}

.index_top .user .avatar-wrapper .user-avatar{
    width: 36px !important;
    height: 36px;
    border-radius: 100%;
    margin-right:5px;
}

.index_top .user .avatar-wrapper .nickname{
    font-size: 16px;
    margin-right:5px;
    color: inherit;
}

.index_top .user .avatar-wrapper .el-icon-arrow-down{
    color: inherit;
}


.user-dropDown{
    padding: 10px 0;
    margin: 5px 0;
    background: #fff;
    border: 1px solid #e6ebf5;
    border-radius: 4px;
    -webkit-box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
}
.user-dropDown li{
    padding: 0 20px !important;
    line-height: 36px !important;
    font-size: 14px !important;
    color: #606266 !important;
}
.user-dropDown li:hover{
    color: #0076ca !important;
    background: none !important;
}

.user-dropDown li.loginOut{
    background: none !important;
    border-color: none !important;
    color: #666 !important;
}
.user-dropDown li.loginOut:hover{
    border-radius: 0px !important;
    background: none !important;
    color: #0076ca !important;
}
.bottom_view {
    width: 100%;
    margin:20px auto 0;
    background: #828bd6;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 40px 0px 20px ;
    min-height: 108px;
    border-width: 0px 0px 0px;
    border-style: solid;
    border-color: rgb(254, 176, 67);
    font-size: 16px;
    color: rgb(51, 51, 51);
    line-height: 1.8;
}


.menu_scrollbar {
    width: 100%;
    background:#ededed;
    border-width: 0px;
    border-style: solid;
    border-color: rgb(239, 239, 239);
}
.menu_scrollbar .el-scrollbar__view {
    padding-bottom: 0px;
}

.menu_scrollbar .menu_view {
    background: transparent;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    border-bottom:none;
    padding:0px 0;
}

.menu_scrollbar .menu_view .el-menu-item,.menu_scrollbar .menu_view .el-sub-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title{
    height: 100px;
    line-height: 100px;
    border: 0px;
    color: #333;
    font-size: 16px;
    padding: 0px 10px;
    cursor: pointer;
    white-space: nowrap;
    list-style: none;
    min-width: 80px;
    text-align: center;
    position: relative;
    background:none;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.menu_scrollbar .menu_view .el-menu-item:hover,.menu_scrollbar .menu_view .el-sub-menu:hover{
    background-size: 100% 100%;
    color: #fff !important;
}
.menu_scrollbar .menu_view .el-menu-item.is-active,.menu_scrollbar .menu_view .el-sub-menu.is-active{
    height: 100px;
    line-height: 100px;
    font-size: 16px;
    border-bottom:none;
    background-size: 100% 100%;
    color: #fff !important;
}

.menu_scrollbar .menu_view .el-sub-menu{
    transition: all 0s;
}
.menu_scrollbar .menu_view .el-sub-menu:hover .el-sub-menu__title{
    color: #fff !important;
}
.menu_scrollbar .menu_view .el-sub-menu.is-active .el-sub-menu__title{
    color: #fff !important;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-icon-menu,.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .iconfont{
    vertical-align: middle;
    margin: 0px 3px;
    width: 34px;
    text-align: center;
    font-size: 18px;
    color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title span{
    font-size: 16px;
    vertical-align: middle;
    color: inherit;
}

.menu_scrollbar .menu_view .el-sub-menu .el-sub-menu__title .el-sub-menu__icon-arrow{
    position: static;
    margin: -3px 0px 0px 8px;
    font-size: 12px;
    vertical-align: middle;
    color: inherit;
}


.el-menu--horizontal .el-menu{
    border: none;
}
.el-menu--horizontal .el-menu .el-menu-item{
    color: rgb(102, 102, 102);
    height: 40px;
    line-height: 40px;
    padding: 0px 20px;
    background-image: linear-gradient(to right, var(--theme), var(--theme));
    transition: all 0s;
    background-size: 0% 100%;
}
.el-menu--horizontal .el-menu .el-menu-item:hover{
    background-size:100% 100%;
    color: #fff !important;
}
.el-menu--horizontal .el-menu .el-menu-item.is-active{
    background-size:100% 100%;
    color: #fff !important;
}


.rotation_view{
    width: 100%;
    margin: 0px auto;
}

.rotation_view .swiper-container{
    width: 100%;
    height: 100%;
    border:0px solid #fff;
}
.rotation_view .swiper .swiper-wrapper{
    transition-duration: 0ms;
    transform: translate3d(0px, 0px, 0px);
    height: 400px;
}
.rotation_view .swiper .swiper-wrapper .swiper-slide{
}
.rotation_view .swiper .swiper-wrapper .swiper-slide img{
    width: 100%;
    height: 100%;
    object-fit:cover;
}

.rotation_view .swiper .swiper-button-prev{
    margin-left:calc((100% - 1180px)/2);
    color:#828bd6;
}

.rotation_view .swiper .swiper-button-next{
    margin-right:calc((100% - 1180px)/2);
    color:#828bd6;
}


.bread_view {
    width:100%;
    background: none;
    color: rgb(51, 51, 51);
    text-align:center;
    font-size:20px;
    font-weight:600;
    border:0px solid #eee;
    padding:10px 0px 10px 0px;
    background: #fff;
}

.bread_view .el-breadcrumb{
    font-size: 16px;
    line-height: 1;
}

.bread_view .el-breadcrumb .el-breadcrumb__separator{
    margin: 0px 9px;
    font-weight: 500;
    color: rgb(204, 204, 204);
}

.bread_view .el-breadcrumb .first_breadcrumb span a{
    color: #333;
    display: inline-block;
}

.bread_view .el-breadcrumb .second_breadcrumb .el-breadcrumb__inner{
    color: #666;
    display: inline-block;
}


.bread_view{
    width: 100%;
    background: none;
    color: rgb(51, 51, 51);
    text-align: center;
    font-size: 20px;
    font-weight: 600;
    border: 0px solid #eee;
    padding: 10px 0px 10px 0px;
    background: #fff;
}


.chat_form {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin: 20px auto;
}

.chat_form .chat-content{
    margin: 0px 0px 30px;
    padding: 0px 0px 30px;
    height: 300px;
    overflow-y: scroll;
    border: 1px solid rgb(221, 221, 221);
    background: #fff !important;
}

.chat_form .chat-content .chat_time{
    width: 100%;
    text-align: center;
    font-size: 16px;
    color: rgb(153, 153, 153);
    padding: 6px 0px;
}

.chat_form .chat-content .right-content{
    float: left !important;
    margin: 0px 0px 10px;
    padding: 10px;
}
.chat_form .chat-content .right-content .text-content{
    background: rgba(80,200,100,.3);
    color: #333;
    margin: 0px 0px 0 10px;
}
.chat_form .chat-content .right-content .chat_img{
    width: 150px;
    margin: 10px;
}

.chat_form .chat-content .left-content{
    float: right !important;
    margin: 0px 0px 10px;
    padding: 10px;
    width: auto;
}
.chat_form .chat-content .left-content .text-content{
    background: rgba(240,180,60,.3);
    color: #333;
}
.chat_form .chat-content .left-content .chat_img{
    width: 150px;
    margin: 10px;
}


.chat_form .input_box{
    display: flex;
    align-items: center;
}

.chat_form .input_box .el-textarea .el-textarea__inner{
    width: 100%;
    min-height: 120px;
    padding: 12px;
    font-size: 14px;
    color: rgb(51, 51, 51);
    border: 1px solid rgb(221, 221, 221);
}

.chat_form .input_box .inp_add{
    width: 36px;
    cursor: pointer;
    margin: 0px 0px 0px 10px;
}


.chat_form .upload_box{
    display: flex;
    align-items: center;
    width: 100%;
    margin: 20px 0px 0px;
    justify-content:center;
}

.chat_form .upload_box .change_btn{
    margin: 0px 10px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 38px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border-radius: 0px;
    border: 0px;
    background: #00caa2;
    cursor: pointer;
}

.chat_form .upload_box .upload_btn{
    margin: 0px 10px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 38px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border-radius: 0px;
    border: 0px;
    background: rgba(241,194,50,1);
    cursor: pointer;
}

.chat_form .save_box{
    width: 100%;
    display: flex;
    justify-content: flex-end;
    margin: 30px 0px 0px;
}
.chat_form .save_box .save_btn{
    margin: 0px 10px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 38px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border-radius: 0px;
    border: 0px;
    background: #828bd6;
    cursor: pointer;
    min-width: 100px;
}

</style>