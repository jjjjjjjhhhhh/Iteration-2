<template>
	<div class="list-page news-page" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="/" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">Home</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form inline :model="searchQuery" class="list_search" @submit.native.prevent>
			<div class="search_view">
				<div class="search_label">
					Title:
				</div>
				<div class="search_box">
					<el-input class="search_inp" v-model="searchQuery.title" placeholder="Title" clearable></el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">Search</el-button>
			</div>
		</el-form>
		<div class="news_list_one">
			<div class="list-item animation_box" v-for="(item,index) in list" :key="index" @click="newsDetail(item.id)">
				<div class="news_img_box">
					<img class="news_img" :src="item.picture?$config.url + item.picture.split(',')[0] :''" alt="">
				</div>
				<div class="news_content">
					<div class="news_title"><span>{{item.title}}</span></div>
					<div class="news_intro">{{item.introduction}}</div>
					<div class="news_time">{{item.addtime.split(' ')[0]}}</div>
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
		
		
		<formModel ref="formModelRef"></formModel>
	</div>
</template>

<script setup>
	import moment from 'moment'
	import formModel from './formModel'
	import {
		ref,
		nextTick,
		getCurrentInstance
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//基础信息
	const tableName = 'news'
	const formName = 'News'
	const router = useRouter()
	const route = useRoute()
	//基础信息
    const breadList = ref([{
        name: formName
    }])
	//权限验证
	const btnAuth = (e, a) => {
		return context?.$toolUtil.isAuth(e, a)
	}
	const list = ref([])
	const listLoading = ref(false)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'addtime',
		order: 'desc'
	})
	const searchQuery = ref({})
	//分页
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const total = ref(0)
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if (searchQuery.value.title && searchQuery.value.title != '') {
			params['title'] = `%${searchQuery.value.title}%`
		}
		context?.$http({
			url: `news/list`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = res.data.data.total
		})
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	const init = () => {
		if (route.query.centerType) {
			centerType.value = true
		}
		getList()
        nextTick(()=>{
            if(route.query.id){
                newsDetail(route.query.id)
            }
        })
	}
	//定义弹窗
	const formModelRef = ref(null)
	const newsDetail = (id = null) => {
		if (id) {
			formModelRef.value.init(id)
		}
	}
	init()
</script>

<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// 返回按钮
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
		// 返回按钮-悬浮
		.back_btn:hover {
			color: #fff;
			background: #828bd6;
		}
	}
	//搜索
	.list_search {
		.search_view {
			.search_label {
			}
			.search_box {
				// 输入框
				:deep(.search_inp) {

				}
			}
		}
		.search_btn_view {
			// 搜索按钮
			.search_btn {
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
			}
		}
	}
	.news_list_one {
		.list-item {
			.news_img_box {
				.news_img {
				}
			}
			.news_content {
				.news_title {
				}
				.news_intro {
				}
				.news_time {
				}
			}
		}
	}
	// animation
	.animation_box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box:hover {
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.6s;
	}
	.animation_box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box img:hover {
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.6s;
	}
	// animation
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// 上一页
		:deep(.btn-prev) {
		}
		// 下一页
		:deep(.btn-next) {
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
		}
		// 页码
		:deep(.el-pager) {
			// 数字
			.number {
			}
			// 数字悬浮
			.number:hover {
			}
			// 选中
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			.el-select {
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;

					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {

			}
		}
	}
</style>
<style lang="scss">

.news-page {
    width: 1200px;
    margin: 20px auto;
    padding: 20px 20px;
    background: #fff;
    overflow: hidden;
    font-size: 16px;
    display: flex;
    flex-wrap: wrap;
    align-items: flex-start;
}


.news-page .list_search{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 20px auto;
    background: none;
}
.news-page .list_search .search_view{
    display: flex;
    align-items: center;
    justify-content: center;
    font-size:16px;
}
.news-page .list_search .search_view .search_label{
    white-space:nowrap;
}
.news-page .list_search .search_view .search_box .el-input{
    border:none;
}
.news-page .list_search .search_view .search_box .el-input .el-input__inner{
    border:0px solid #2dc6cc50;
    padding:0 20px;
    height:42px;
    line-height:42px;
    border-radius: 0px;
    min-width:500px;
    background: #eee;
    clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
}
.news-page .list_search .search_btn_view{
    margin-left:10px;
}
.news-page .list_search .search_btn_view .search_btn{
    padding: 0px 30px;
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
.news-page .list_search .search_btn_view .search_btn:hover{
    background-size:100% 100%;
    color: #fff;
    border-color:#828BD6;
}


.news-page .categorys{
    width:220px;
    display: flex;
    flex-wrap: wrap;
    background: none;
    padding: 0px 10px;
    margin: 20px 20px 20px 0;
    order:2;
}

.news-page .categorys .category{
    width:100%;
    padding: 15px 10px;
    margin: 0 0 20px;
    box-sizing: border-box;
    cursor: pointer;
    border: 0px dashed #ccc;
    border-radius: 0px;
    background: var(--theme);
    color: #fff;
    text-align: center;
    background-repeat: no-repeat;
    background-position: left;
    background-image: -webkit-linear-gradient(right, #828BD6, #828BD6);
    background-image: linear-gradient(to right, #828BD6, #828BD6);
    background-size: 0 100%;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}

.news-page .categorys .category:hover{
    color: rgb(255, 255, 255) !important;
    border-color: rgba(0, 118, 202,1) !important;
    background-size: 100% 100% !important;
}

.news-page .categorys .category.categoryActive{
    color: rgb(255, 255, 255) !important;
    border-color: rgba(0, 118, 202,1) !important;
    background-size: 100% 100% !important;
}



.news-page .news_list_one{
    padding: 0 10px;
    width: calc(100% - 240px);
    margin: 20px 0 0;
    background:#fff;
    order:3;
    flex:1;
}
.news-page .news_list_one .list-item{
    width: 100%;
    display: flex;
    margin: 0px;
    height: auto;
    background: rgb(255, 255, 255);
    justify-content: space-between;
    padding: 0 0 20px 15px;
    cursor: pointer;
    background: none;
    border-bottom: 0px dashed #aaa;
    margin: 0 0 20px;
}

.news-page .news_list_one .list-item .news_img_box{
    width: 200px;
    height: 150px;
    display: block;
    overflow:hidden;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.news-page .news_list_one .list-item .news_img_box .news_img{
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition:all 0.6s;
}
.news-page .news_list_one .list-item:hover .news_img_box .news_img{
    transform:scale(1.05);
}

.news-page .news_list_one .list-item:nth-child(2n) .news_img_box{
    order:2;
}


.news-page .news_list_one .list-item .news_content{
    width: calc(100% - 220px);
    position: relative;
    display:flex;
    flex-wrap:wrap;
    flex-direction: column;
    background:#f6f6f6;
    padding:5px 20px 0 30px;
    clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
}
.news-page .news_list_one .list-item:hover .news_content{
    background-size: 100% 100%;
}
.news-page .news_list_one .list-item .news_content .news_title{
    padding: 0px;
    line-height: 30px;
    color: #333;
    font-size: 15px;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-weight: 600;
}
.news-page .news_list_one .list-item .news_content .news_intro{
    padding: 0px;
    line-height: 24px;
    color: #5A5A5A;
    font-size: 14px;
    height: 72px;
    overflow: hidden;
    display: block;
    order:3;
}
.news-page .news_list_one .list-item .news_content .news_time{
    padding: 0;
    line-height: 24px;
    color: rgb(153, 153, 153);
    font-size: 13px;
    width: 100%;
    text-align: left;
    box-sizing: border-box;
}

.news-page .news_list_one .list-item:hover{
}
.news-page .news_list_one .list-item:nth-child(2n){
}
.news-page .news_list_one .list-item:nth-child(2n):hover{
}
.news-page .news_list_one .list-item:hover .news_content .news_title{
    color:#fff;
}
.news-page .news_list_one .list-item:hover .news_content .news_intro{
    color:#fff;
}
.news-page .news_list_one .list-item:hover .news_content .news_time{
    color:#fff;
}


.news-page .news_detail{
    width: 100%;
}
.news-page .news_detail .news_detail_title{
    font-size: 18px;
    font-weight: 500;
    color: rgb(0, 0, 0);
    text-align: center;
}
.news-page .news_detail .news_detail_time{
    text-align: right;
    font-size: 15px;
    color: #888;
    padding: 20px 0px 0px;
}
.news-page .news_detail .news_detail_content{
    margin-top:20px;
    text-align: left;
    font-size: 15px;
    color: #888;
    line-height:1.8;
    text-indent:2em;
}


.news-page .hot_view{
    width:100%;
    margin: 0px auto 20px ;
    order:20;
}
.news-page .hot_view .hot_title{
    width: 100%;
    margin: 20px 0 0;
    font-size: 22px;
    line-height:50px;
    color: #fff;
    font-weight:600;
    text-align: left;
    border:none;
    padding: 0 0 0 40px;
    background:url(http://clfile.zggen.cn/20241022/b4bc974330584ea583c94466f61f1668.png) no-repeat left bottom;
}
.news-page .hot_view .hot_list{
    display: flex;
    flex-wrap:wrap;
    align-items: center;
    font-size:16px;
    margin: 0px;
    border:0px solid #eee;
    border-top:none;
    padding-top: 20px;
}

.news-page .hot_view .hot_list .hot{
    width: calc(25% - 20px);
    margin: 0px 10px 20px;
    background: none;
    padding: 0px;
    box-sizing: border-box;
    cursor: pointer;
}
.news-page .hot_view .hot_list .hot .hot_img{
    width:100%;
    height:150px;
    overflow:hidden;
    clip-path: polygon(2% 0, 100% 0%, 98% 100%, 0% 100%);
}
.news-page .hot_view .hot_list .hot .hot_img .el-image__inner{
    width:100%;
    height:100%;
    object-fit:contain;
    border:1px solid #eee;
}
.news-page .hot_view .hot_list .hot .hot_content{

}
.news-page .hot_view .hot_list .hot .hot_content .hot_text{
    width:100%;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    font-size:15px;
    color:#666;
    line-height:30px;
}
.news-page .hot_view .hot_list .hot .hot_content .hot_date{
    width:100%;
    line-height:24px;
    text-align: right;
    font-size:14px;
    color:#999;
}

.news-page .hot_view .hot_list .hot:hover{
}
.news-page .hot_view .hot_list .hot:hover .hot_content .hot_text{
}
.news-page .hot_view .hot_list .hot:hover .hot_content .hot_date{
}


</style>