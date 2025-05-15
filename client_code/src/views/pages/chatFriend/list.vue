<template>
	<div class="list-page" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="/" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="back_view" v-if="centerType">
                <el-button class="back_btn" @click="backClick" type="primary">Back</el-button>
            </div>
        </div>
		<el-form :inline="true" :model="searchQuery" class="list_search" @submit.native.prevent>
			<div class="search_view">
				<div class="search_label">
					Name:
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.name" placeholder="Name"
						clearable>
					</el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">Search</el-button>
				<el-button class="add_btn" type="success" v-if="btnAuth('chat_friend','新增')" @click="addClick">Add</el-button>
			</div>
		</el-form>
		<div class="page_list">
			<div class="data_box">
				<div class="data_view">
					<div class="data_item" v-for="(item,index) in list" :key="index" @click.stop="detailClick(item.id)" >
						<div class="data_content">
						</div>
					</div>
				</div>
				<el-pagination
					background 
					:layout="layouts.join(',')"
					:total="total" 
					:page-size="listQuery.limit"
                    v-model:current-page="listQuery.page"
					prev-text="Previous"
					next-text="Next"
					:hide-on-single-page="false"
					:style='{}'
					@size-change="sizeChange"
					@current-change="currentChange"/>
			</div>
		</div>
		<el-dialog v-model="preViewVisible" :title="'View Image'" width="40%" destroy-on-close>
            <div style="text-align:center">
                <img :src="preViewUrl" style="max-width: 100%;" alt="">
            </div>
		</el-dialog>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
        computed,
        inject,
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	const route = useRoute()
	// Basic Information
	const tableName = 'chatfriend'
	const formName = 'Friend List'
	// Basic Information
	const breadList = ref([{
		name: formName
	}])
	const list = ref([])
	const listQuery = ref({
		page: 1,
		limit: Number(9)
	})
	const total = ref(0)
	const listLoading = ref(false)
	// Permission Verification
	const btnAuth = (e,a)=>{
		if(centerType.value){
			return context?.$toolUtil.isBackAuth(e,a)
		}else{
			return context?.$toolUtil.isAuth(e,a)
		}
	}
	const addClick = () => {
		router.push('/index/chatFriendAdd')
	}
	// Check if redirected from personal center
	const centerType = ref(false)
	// Back
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	// Search
	const searchQuery = ref({})
	// Dropdown List
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	// Pagination
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	// Pagination
	// List
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if(searchQuery.value.name&&searchQuery.value.name!=''){
			params.name = '%' + searchQuery.value.name + '%'
		}
		context?.$http({
			url: `${tableName}/${centerType.value?'page':'list'}`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	const detailClick = (id) => {
		router.push(`${tableName}Detail?id=` + id + (centerType.value?'&&centerType=1':''))
	}
	// Download File
	const download = (file) =>{
		if(!file){
			context?.$toolUtil.message('File does not exist','error')
		}
		const a = document.createElement('a');
		a.style.display = 'none';
		a.setAttribute('target', '_blank');
		file && a.setAttribute('download', file);
		a.href = context?.$config.url + file;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	}
	// View Large Image
	const preViewUrl = ref('')
	const preViewVisible = ref(false)
	const preViewClick = (url) =>{
		preViewUrl.value = url
		preViewVisible.value = true
	}
	const init = async() => {
		if(route.query.centerType){
			centerType.value = true
		}
        if(context.$toolUtil.storageGet('frontToken') && !user.value.id){
            await store.dispatch("user/getSession")
        }
		getList()
	}
	init()
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
	// Category Box
	.category_view {
		// Category item
		.category {
		}
		// item-Hover
		.category:hover {
		}
		// item-Selected
		.categoryActive {
		}
	}

	// Search
	.list_search {
		.search_view {
			.search_label {
			}
			.search_box {
				// Input Box
				:deep(.search_inp) {
					.is-focus {
						box-shadow: none !important;
					}
				}
			}
		}
		.search_btn_view {
			// Search Button
			.search_btn {
			}
			// Search Button-Hover
			.search_btn:hover {
			}
			// Add Button
			.add_btn {
			}
			// Add Button-Hover
			.add_btn:hover {
			}
		}
	}

	// Data Box
	.page_list {
		// List
		.data_box {
			.data_view {
				.data_item:nth-of-type(2n - 1) {
					// Image Box
					.data_img_box {
						width: 100%;
						// Image
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// Content Box
					.data_content {
						// Price
						.data_price {
							color: #f00;
						}
						// Title1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
					}
				}
				.data_item:nth-of-type(2n) {
					// Image Box
					.data_img_box {
						width: 100%;
						// Image
						.data_img {
							object-fit: cover;
							width: 100%;
							height: 100%;
						}
					}
					// Content Box
					.data_content {
						// Price
						.data_price {
							color: #f00;
						}
						// Title1
						.data_title1 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title2
						.data_title2 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title3
						.data_title3 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title4
						.data_title4 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
						// Title5
						.data_title5 {
							margin: 0 0 10px;
							width: 100%;
							text-align: center;
						}
					}
				}
				.data_item:nth-of-type(2n - 1):hover {
					// Image Box
					.data_img_box {
						// Image
						.data_img {
						}
					}
					// Content Box
					.data_content {
						// Price
						.data_price {
						}
						// Title1
						.data_title1 {
						}
						// Title2
						.data_title2 {
						}
						// Title3
						.data_title3 {
						}
						// Title4
						.data_title4 {
						}
						// Title5
						.data_title5 {
						}
					}
				}
				.data_item:nth-of-type(2n):hover {
					// Image Box
					.data_img_box {
						// Image
						.data_img {
						}
					}
					// Content Box
					.data_content {
						// Price
						.data_price {
						}
						// Title1
						.data_title1 {
						}
						// Title2
						.data_title2 {
						}
						// Title3
						.data_title3 {
						}
						// Title4
						.data_title4 {
						}
						// Title5
						.data_title5 {
						}
					}
				}
			}
		}
	}

	// Paginator
	.el-pagination {
		// Total Pages
		:deep(.el-pagination__total) {
		}
		// Previous Page
		:deep(.btn-prev) {
		}
		// Next Page
		:deep(.btn-next) {
		}
		// Previous Page Disabled
		:deep(.btn-prev:disabled) {
		}
		// Next Page Disabled
		:deep(.btn-next:disabled) {
		}
		// Page Numbers
		:deep(.el-pager) {
			// Numbers
			.number {
			}
			// Numbers Hover
			.number:hover {
			}
			// Selected
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			.el-select {
				// Remove Default Styles
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;
						.is-focus {
							box-shadow: none !important;
						}
					}
				}
			}
		}
		// Jump Page
		:deep(.el-pagination__jump) {
			// Input Box
			.el-input {
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
	
	// Hot Information Box
	.hot_view {
		// Title
		.hot_title {
		}

		.hot_list {
			// item
			.hot {
				// Image Box
				.hot_img_view {
					// Image
					.hot_img {
					}
				}
				// Content Box
				.hot_content {
					// Name
					.hot_text {
					}
				}
			}
		}
	}
</style>