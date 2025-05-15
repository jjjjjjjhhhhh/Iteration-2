<template>
	<div class="detail-page" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="/" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view">
                <el-button class="back_btn" @click="backClick" type="primary">Back</el-button>
            </div>
        </div>
		<div class="detail_view">

			<div class="info_view">
				<div class="title_view">
					<div class="detail_title">
					</div>
				</div>
				<div class="info_item">
					<div class="info_label">User ID</div>
					<div  class="info_text" >{{detail.uid}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">Friend ID</div>
					<div  class="info_text" >{{detail.fid}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">Content</div>
					<div  class="info_text" >{{detail.content}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">Format (1:Text, 2:Image)</div>
					<div  class="info_text" >{{detail.format}}</div>
				</div>
				<div class="info_item">
					<div class="info_label">Message Read (0:Unread, 1:Read)</div>
					<div  class="info_text" >{{detail.isRead}}</div>
				</div>
				<div class="btn_view">
					<el-button class="edit_btn" v-if="centerType&&btnAuth('chat_message','修改')" type="primary" @click="editClick">Edit</el-button>
					<el-button class="del_btn" v-if="centerType&&btnAuth('chat_message','删除')" type="danger" @click="delClick">Delete</el-button>
				</div>
			</div>
		</div>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import moment from 'moment'
	import {
		ref,
		getCurrentInstance,
		watch,
		onUnmounted,
		onMounted,
		nextTick,
		computed,
        inject
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const userAvatar = computed(()=>store.getters['user/avatar'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const route = useRoute()
	const router = useRouter()
	// Basic Information
	const tableName = 'chatmessage'
	const formName = 'Message List'
	// Basic Information
	const breadList = ref([{
		name: formName
	}])
	// Permission Verification
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	// View Permission Verification
	const btnFrontAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isFrontAuth(e,a)
		}
	}
	// Back
	const backClick = () =>{
		history.back()
	}
	// Carousel
	const bannerList = ref([])
	// Details
	const title = ref('')
	const detail = ref({})
    const activeName = ref('false')
	const getDetail = () => {
		context?.$http({
			url: `${tableName}/detail/${route.query.id}`,
			method: 'get'
		}).then(res => {
			detail.value = res.data.data
		})
	}
	// Download File
	const downClick = (file) => {
		if(!file){
			context?.$toolUtil.message('File does not exist','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('frontToken')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// Below is compatible with Firefox
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
	// Check if redirected from personal center
	const centerType = ref(false)
	const init = () => {
		if(route.query.centerType){
			centerType.value = true
		}
		getDetail()
	}
	// Edit
	const editClick = () => {
		router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
	}
	// Delete
	const delClick = () => {
		ElMessageBox.confirm(`Are you sure you want to delete this ${formName}?`, 'Prompt', {
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			type: 'warning',
		}).then(()=>{
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: [detail.value.id]
			}).then(res=>{
				context?.$toolUtil.message('Delete successful','success',()=>{
					history.back()
				})
			})

		}).catch(_ => {})
	}
	onMounted(()=>{
		init()
	})
</script>
<style lang="scss" scoped>
	// Back Box
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// Back Button
		.back_btn {
			cursor: pointer;
			border-radius: 0px;
			padding: 0 20px;
			color: #828bd6;
			background: #fff;
			width: auto;
			font-size: 14px;
			border-color: #828bd6;
			border-width: 1px 0;
			border-style: solid;
			height: 36px;
		}
		// Back Button-Hover
		.back_btn:hover {
			color: #fff;
			background: #828bd6;
		}
	}
	// Breadcrumb Box
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

	.detail_view{
		// Carousel
		.swiper_view {
		}
		// Text Area
		.info_view {

			.title_view {

				.detail_title {
				}
				.collect_view {
					border: 0px solid #eee;
					cursor: pointer;
					border-radius: 10px;
					padding: 10px 0px;
					color: #999;
					background: none;
					display: flex;
					width: auto;
					line-height: 1;
					justify-content: center;
					align-items: center;
					.iconfont {
						margin: 0 5px 0 0;
						color: #fff;
					}
					.iconfontActive {
						margin: 0 5px 0 0;
						color: #fcf197;
					}
					span {
						color: #fff;
					}
					.textActive {
						color: #fcf197;
					}
				}
				.collect_view:hover {
				}
				.collect_view:active {
					transform: scale(0.8);
				}
			}

			.info_item {

				.info_label {
				}
				.info_text {
				}
			}
			.btn_view {
				// Edit-Button
				.edit_btn {
				}
				// Hover
				.edit_btn:hover {
				}
				// Delete-Button
				.del_btn {
				}
				// Hover
				.del_btn:hover {
				}
			}
		}
	}

	// Bottom Box
	.tabs_view {
		:deep(.el-tabs__header) {
			background: transparent;
			border: none;
		}
		// Header
		:deep(.el-tabs__nav-scroll) {
			.el-tabs__nav {
				.el-tabs__item {
				}
				.el-tabs__item:hover {
				}
				.is-active {
				}
			}
		}
		// Content Area
		:deep(.el-tabs__content) {
		}
	}
</style>