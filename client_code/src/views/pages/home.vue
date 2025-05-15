<template>
	<div>
		<div class="home_box">
			<!-- System Introduction -->
			<div class="systemInfo_view">
				<div class="title-box">
					<div class="systemInfo_title"><span>Website Introduction</span></div>
					<div class="systemInfo_subtitle"><span>{{systemInfoDetail.subtitle}}</span></div>
				</div>
				<div class="systemInfo_content"><div v-html="systemInfoDetail.content"></div></div>
				<div class="systemInfo_img_box">
					<img class="systemInfo_img1" :src="systemInfoDetail.picture1?$config.url + systemInfoDetail.picture1:''" alt="">
					<img class="systemInfo_img2" :src="systemInfoDetail.picture2?$config.url + systemInfoDetail.picture2:''" alt="">
					<img class="systemInfo_img3" :src="systemInfoDetail.picture3?$config.url + systemInfoDetail.picture3:''" alt="">
				</div>
				<div class="more" @click="router.push(`/index/systemintroDetail?id=${systemInfoDetail.id}`)" style="cursor: pointer">View More +</div>
				<div class="systemInfo_default1"></div>
				<div class="systemInfo_default2"></div>
				<div class="systemInfo_default3"></div>
				<div class="systemInfo_default4"></div>
			</div>
			<!-- Healthy Recipe Recommendations -->
			<div class="recomList_view">
				<div class="recomList_title"><span>Healthy Recipe Recommendations</span></div>

				<div class="tjlist">
					
					<div class="item" v-for="(item,index) in jiankangshipuRecomList" @click="detailClick('jiankangshipu',item.id)">
						<div class="imgbox"><img :src="item.imgUrls[0]" alt=""></div>
						
						<div class="infobox">
							<div class="titles"><div class="title">Recipe Name: {{item.shipumingcheng}}</div>
							<div class="title">{{item.suoxushicai}}</div>
							</div>
							<div class="price"></div>
							<div class="xys">
								<div class="like">
									<span class="iconfont icon-thumb-up-line1"></span>
									<div class="num">{{item.thumbsupNumber}}</div>
								</div>

								<div class="collect">
									<span class="iconfont icon-likeline4"></span>
									<div class="num">{{item.storeupNumber}}</div>
								</div>

								
							</div>
						</div>
						
					</div>
					
				</div>
				<div class="homeList_more_text" @click="moreClick('jiankangshipu')">
					View More +
				</div>
			</div>
			<!-- Health Knowledge Homepage Display -->
			<div class="homeList_view">
				<div class="homeList_title">
					<span>Health Knowledge</span>
				</div>
				<div class="homeList">
					<div class="item" v-for="(item,index) in jiankangzhishiHomeList" :key="index" @click="detailClick('jiankangzhishi',item.id)">
						<div class="img-box">
							<img v-if="isHttp(item.tupian)" :src="item.tupian.split(',')[0]" alt="">
							<img v-else :src="item.tupian?$config.url + item.tupian.split(',')[0]:''" alt="">
						</div>
						<div class="content-box">
							<div class="title">
								{{item.biaoti}}
							</div>
							<div class="statistic">
								<div class="collect">
									<span class="iconfont icon-likeline4"></span>
									<div class="num">{{item.storeupNumber}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="homeList_more_view" @click="moreClick('jiankangzhishi')">
					<span class="homeList_more_text">View More +</span>
				</div>
			</div>
			<!-- News Information -->
			<div class="newsList_view">
				<div class="ntitle"> <div class="n2">NEWS INFORMATION</div> <div class="n1">News Information</div> </div>
				
				<div class="nlist">
					<ul> 
						<li v-for="(item,index) in newsList" :key="index" @click="newsDetailClick(item)">
						
							<div class="infobox">
								<div class="nam">{{item.title}}</div>
								<div class="info">{{item.introduction}}</div>
								<div class="tim">{{moment(item.addtime).format('YYYY-MM-DD')}}</div>
							</div>
							
							<div class="imgbox">
								<img :src="item.imgUrl" >
							</div>
							
						</li>
					</ul> 
				</div>

				<div class="nmore" @click="moreClick('news')" style="cursor: pointer">View More +</div>
			</div>

			<!-- Online Message -->
			<div class="onlineMessage">
				<div class="box">
					<div class="info-wrapper">
						<div class="title">Online Message</div>
						<div class="subtitle">Online Message</div>
						<div class="describe1"></div>
						<div class="describe2"></div>
						<div class="phone">
							<span class="icon iconfont icon-phone-fill"></span>
							<span class="text"></span>
						</div>
					</div>
					<div class="form-wrapper">
						<el-form ref="messageForm_ref" autocomplete="off" :model="messageForm" :rules="messageRules">
							<el-form-item class="name" prop="name">
								<el-input type="text" v-model="messageForm.name" placeholder="Please enter your name" />
							</el-form-item>
							<el-form-item class="phone" prop="phone">
								<el-input type="text" v-model="messageForm.phone" placeholder="Please enter your phone" />
							</el-form-item>
							<el-form-item class="content" prop="content">
								<el-input type="textarea" v-model="messageForm.content" placeholder="Please enter your message" />
							</el-form-item>
							<el-form-item class="submit">
								<el-button @click="messageSave">Submit</el-button>
							</el-form-item>
						</el-form>
					</div>
				</div>
			</div>
		</div>
		<formModel ref="newsFormModelRef"></formModel>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	// System Introduction
	const systemInfoDetail = ref({})
	const getSystemInfo = () => {
		context?.$http({
			url: 'systemintro/detail/1',
			method: 'get',
		}).then(res=>{
			systemInfoDetail.value = res.data.data
		})
	}
	// Healthy Recipe Recommendations
	const jiankangshipuRecomList = ref([])
	const getjiankangshipuRecomList = () => {
		let autoSortUrl = 'jiankangshipu/autoSort'
		if(context?.$toolUtil.storageGet('frontToken')){
			autoSortUrl = "jiankangshipu/autoSort2"
		}
		let params = {
			page: 1,
			limit: 8
		}
		context?.$http({
			url: autoSortUrl,
			method: 'get',
			params: params
		}).then(res => {
			jiankangshipuRecomList.value = res.data.data.list
			jiankangshipuRecomList.value.forEach(item=>{
				if(!isHttp(item.fengmian)){
					item.imgUrls = item.fengmian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	// Health Knowledge Homepage Display
	const jiankangzhishiHomeList = ref([])
	const getjiankangzhishiHomeList = () => {
		let params = {
			page: 1,
			limit: 6,
			sort:'id',
			order:'desc',
		}
		context?.$http({
			url: 'jiankangzhishi/list',
			method: 'get',
			params: params
		}).then(res => {
			jiankangzhishiHomeList.value = res.data.data.list
			jiankangzhishiHomeList.value.forEach(item=>{
				if(!isHttp(item.tupian)){
					item.imgUrls = item.tupian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	// News Information Popup
	import formModel from './news/formModel'
	const newsFormModelRef = ref(null)
	// News Information
	const newsList = ref([])
	const getNewsList = () => {
		context?.$http({
			url: 'news/list',
			method: 'get',
			params:{
				page:1,
				limit: 4,
				sort:'id',
				order:'desc',
			}
		}).then(res=>{
			newsList.value = res.data.data.list
			newsList.value.forEach(item=>{
				if(!isHttp(item.picture)){
					item.imgUrl=context.$config.url+item.picture.split(',')[0]
				}
			})
		})
	}
	const newsDetailClick = (item) => {
		if (item && item.id){
			newsFormModelRef.value.init(item.id)
		}
	}
	const messageForm_ref = ref(null)
	const messageForm = ref({})
	const messageRules = ref({
		name:[{ required: true, message: 'Name cannot be empty', trigger: 'blur' },],
		phone:[{ required: true, message: 'Phone number cannot be empty', trigger: 'blur' },{ validator: context.$toolUtil.validator.mobile, trigger: 'blur' }],
		content:[{ required: true, message: 'Message content cannot be empty', trigger: 'blur' },],
	})
	const messageSave = ()=>{
		messageForm_ref.value.validate(valid => {
			if(valid) {
				context.$http.post('onlinemessage/save', messageForm.value).then(res => {
					if(res.data.code == 0) {
					context?.$toolUtil.message('Submitted successfully','success')
						messageForm.value = {}
					}
				})
			}
		})
	}
	// Check if image link contains http
	const isHttp = (str) => {
		return str && str.substr(0,4)=='http';
	}
	// Jump to details
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		getSystemInfo()
		// Healthy Recipe Recommendations
		getjiankangshipuRecomList()
		// Health Knowledge Homepage Display
		getjiankangzhishiHomeList()
		// News Information
		getNewsList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
	}

	.systemInfo_view {
		.systemInfo_content {
		}
		.systemInfo_img_box {
			.systemInfo_img1 {
			}

			.systemInfo_img2 {
			}

			.systemInfo_img3 {
			}
		}
		// Custom Box One
		.systemInfo_default1 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// Custom Box Two
		.systemInfo_default2 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// Custom Box Three
		.systemInfo_default3 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
		// Custom Box Four
		.systemInfo_default4 {
			margin: 0 auto;
			background: #d5a1b1;
			display: none;
			width: 100px;
			height: 100px;
		}
	}
	// Recommend
	.recomList_view {
		.recomList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// More
		.recommend_more_view {
			cursor: pointer;
			top: 60px;
			position: absolute;
			right: calc((100% - 1180px)/2);
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_one {
			display: flex;
			flex-wrap: wrap;
			.news_item {
				box-shadow: 0 4px 6px rgba(0,0,0,.3);
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(33% - 20px);
				align-items: center;
				.news_img_box {
					width: 40%;
					font-size: 0;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 200px;
					}
				}
				.news_content {
					margin: 0 0 0 20px;
					width: calc(60% - 20px);
					.news_title {
						font-weight: bold;
						font-size: 20px;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #999;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// More
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// More
		.homeList_more_view {
			cursor: pointer;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>
<style lang="scss">
.home_box{
    width: 100%;
    margin: 0px auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.home_box .appendBox1{
    width: 100%;
    height:299px;
    background:url(http://clfile.zggen.cn/20240927/708c7343aeeb4d728265133426b664a4.jpg) no-repeat center top / 100% 100%;
    order:5;
}
/* 总盒子 */
.systemInfo_view {
    width: 1200px;
    margin:120px auto 40px;
    padding: 0px;
    background:url() #fff no-repeat center bottom;
    position:relative;
    order: 6;
    display: block;
    border:0px solid var(--theme)80;
}
.systemInfo_view .title-box{
    width: 1200px;
    margin:0px auto;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
    position:absolute;
    top:-84px;
    left:-4px;
}
.systemInfo_view .title-box .systemInfo_title{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;

}

.systemInfo_view .title-box .systemInfo_subtitle{
    display:none;
    font-size: 16px;
    line-height:30px;
    color:#333;
    margin:10px 0 0;
    order:2;
    border-bottom:1px solid #ddd;
    paddig:5px;
    clear:both;
}

/* 内容 */
.systemInfo_view .systemInfo_content{
    width: calc(100% - 440px);
    padding: 0px;
    font-size: 15px;
    color: rgb(102, 102, 102);
    line-height: 30px;
    height: 210px;
    overflow:hidden;
    float:right;
    margin:0 0 10px;
    background: none;
}
.systemInfo_view .systemInfo_img_box{
    display: inline-block;
    width: 400px; 
    height: 210px;
    float:left;
    margin: 0px 0 0;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.systemInfo_view .systemInfo_img_box .systemInfo_img1{
    width: 100%; 
    height: 100%;
    object-fit:cover;
    border-radius:0;
}
.systemInfo_view .systemInfo_img_box .systemInfo_img2{
   display: none;
   width: 30%; 
   height: 160px;
   object-fit:cover;
   position:absolute;
   left:calc(0% + 450px);
   bottom:10px;
   border-radius:5%;
}
.systemInfo_view .systemInfo_img_box .systemInfo_img3{
   display: none;
   width: 30%; 
   height: 160px;
   object-fit:cover;
   position:absolute;
   right:10px;
   bottom:10px;
   border-radius:5%;
}
/* 自定义盒子 */
.systemInfo_view .systemInfo_default1{
    width: 100px;
    height: 100px;
    background: rgb(213, 161, 177);
    margin: 0px auto;
    display: none;
}
/* 更多 */
.systemInfo_view .more{
    position: absolute;
    top:-80px;
    right: calc((100% - 1200px) / 2);
    cursor: pointer;
    padding:10px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.systemInfo_view .more:hover{
    background-size: 100% 100%;
    color:#fff;
}

/* 总盒子 */
.recomList_view {
    width: 100%;
    margin:0;
    padding: 20px 0 50px;
    background: url() fixed no-repeat center top / cover,#fff;
    overflow: hidden;
    position:relative;
    order: 1;
    display: block;
}
/* 标题 */
.recomList_view .recomList_title{
    width: 1200px;
    margin:30px auto;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
}
.recomList_view .recomList_title span{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;

 }
/* 分类 */
.recomList_view .categoryList{
    display: none;
    flex-wrap: wrap;
}
.recomList_view .categoryList .item{
}

/* 自定义盒子 */
.recomList_view .tjlist{
  max-width:1200px;
  margin:0 auto;
  display: grid;
  grid-template-areas:
  ' . a1 a1. .'
  '. . . a8 a8';
  grid-gap: 20px;
  padding: 0px;
  border:0px solid var(--theme)80;
  background:#fff;
}
.recomList_view .tjlist .item:first-child {
   grid-area: a1;
   clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
}
.recomList_view .tjlist .item:last-child {
   grid-area: a8;
   clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
}

.recomList_view .tjlist .item{
    width: auto;
    margin: 0;
    background: none;
    cursor: pointer;
    height: 250px;
    overflow:hidden;
    position:relative;
    border-radius:0;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.recomList_view .tjlist .item .imgbox{
    width: 100%;
    height: 100%;
    overflow: hidden;
}
.recomList_view .tjlist .item .imgbox img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.recomList_view .tjlist .item .infobox{
    cursor:pointer;  overflow:hidden; transition:0.3s; position:absolute; left:0px; bottom:-100%; width:100%; display:flex; flex-wrap:wrap; align-items:center; justify-content:center;flex-direction:column; background:none; 
}
.recomList_view .tjlist .item:hover .infobox{  padding:0px; bottom:0; height:100%; border-radius:0;background:rgba(0,0,0,.3);   }

.recomList_view .tjlist .item .infobox .titles{

}
.recomList_view .tjlist .item .infobox .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    text-align:center;
}
.recomList_view .tjlist .item:hover .infobox .title{
    color: #fff;
}
.recomList_view .tjlist .item .infobox .price{
    width: 100%;
    text-align:center;
    color: #f00;
    font-size:16px;
}
.recomList_view .tjlist .item:hover .infobox .price{

}
.recomList_view .tjlist .item .infobox .xys{
    width: 100%;
    margin:10px 0 0;
}
.recomList_view .tjlist .item:hover .infobox .xys{
    color: #ccc;
    text-align:center;
}
.recomList_view .tjlist .item .infobox .xys div{
    display:inline-block !important;
}
.recomList_view .tjlist .item .infobox .xys .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
    display:inline-block;
}
.recomList_view .tjlist .item .infobox  .xys .num{
    display:inline-block;
    color: inherit;
}
.recomList_view .tjlist .item .infobox .xys .like{
    display: inline-block;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.recomList_view .tjlist .item .infobox .xys .collect{
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.recomList_view .tjlist .item .infobox .xys .clickNum{
    display: inline-block;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.recomList_view .homeList_more_text{
    position: absolute;
    top: 50px;
    right: calc((100% - 1200px) / 2);
    cursor: pointer;
    padding:10px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.recomList_view .homeList_more_text:hover{
    background-size: 100% 100%;
    color: #fff;
}

/* 总盒子 */
.homeList_view{
    width: 100%;
    margin:0;
    padding: 20px calc((100% - 1200px)/2) 50px;
    background: #fff ;
    overflow: hidden;
    position:relative;
    order: 8;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
}
/* 标题 */
.homeList_view .homeList_title{
    width: 101%;
    margin:30px auto;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
}
.homeList_view .homeList_title span{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;

}

/* 分类 */
.homeList_view .categoryList{
    width: 240px;
    display: block;
    flex-wrap: wrap;
    margin-left:50px;
    order:4;
}
.homeList_view .categoryList .item{
    background: var(--theme);
    line-height:50px;
    color:#fff;
    text-align: center;
    margin:0 0 15px;
    cursor:pointer;
    font-size: 16px;
    background-repeat: no-repeat;
    background-position: left;
    background-image: -webkit-linear-gradient(right, #828BD6, #828BD6);
    background-image: linear-gradient(to right, #828BD6, #828BD6);
    background-size: 0 100%;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.homeList_view .categoryList .item:hover{
    background-size: 100% 100%;
    color:#fff;
}

/* 样式七 总盒子 */
.homeList_view .homeList{
    width: calc(100% - 320px);
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content:space-between;
    order:2;
    flex:1;
}
.homeList_view .homeList .item{
    width: calc(32% - 0px);
    margin: 0px 0px 20px;
    background: none;
    cursor: pointer;
    overflow:hidden;
    position:relative;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.homeList_view .homeList .item .img-box{
    width: 100%;
    height: 250px;
    overflow: hidden;
    margin: 0px;
    text-align:center;
}
.homeList_view .homeList .item .img-box img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.homeList_view .homeList .item .content-box{
    width: 100%;
    position:absolute; left:0px; bottom:-100%; background:rgba(255, 255, 255, .8);  padding:0px 0px; transition:all 0.5s; display:flex; flex-wrap:wrap; align-items:center; justify-content:center;flex-direction:column; text-align:center; 
}
.homeList_view .homeList .item:hover .content-box{ bottom:0px; background:rgba(0, 0, 0, .5); height:250px; }

.homeList_view .homeList .item .content-box .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.homeList_view .homeList .item:hover .content-box .title{
    color: #eee;
}
.homeList_view .homeList .item .content-box .price{
    width: 100%;
    text-align:center;
    color: #f00;
    font-size:16px;
}
.homeList_view .homeList .item:hover .content-box .price{
}
.homeList_view .homeList .item .content-box .statistic{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin:10px 0 0;
}
.homeList_view .homeList .item:hover .content-box .statistic{
    color: #ccc;
}
.homeList_view .homeList .item .content-box .statistic .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .num{
    color: inherit;
}
.homeList_view .homeList .item .content-box .statistic .like{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .collect{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .clickNum{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}

/* 查看更多 */
.homeList_view .homeList_more_view{
    position: absolute;
    top:50px;
    right: calc((100% - 1200px) / 2);
    cursor: pointer;
    padding:10px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.homeList_view .homeList_more_view:hover{
    background-size: 100% 100%;
    color: #fff;
}

/* 总盒子 */
.newsList_view {
    width: 100%;
    margin:0;
    padding: 40px 0 60px;
    background: url(http://clfile.zggen.cn/20241022/77f8f685f76e429aa7449bb4230906e0.jpg) fixed no-repeat center top / cover;
    overflow: hidden;
    position:relative;
    order: 6;
    display:block;
    text-align:center;
}
/* 自定义 start*/
.newsList_view .ntitle{
    width: 1200px;
    margin:30px auto;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
}
.newsList_view .ntitle .n1{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;

}
.newsList_view .ntitle .n2{
    display:none;
    font-size: 18px;
    line-height:30px;
    color:#ccc;
    margin-left:5px;
}
.newsList_view .nlist{
    width: 1200px;
    margin:30px auto 10px;
}
.newsList_view .nlist ul{
    margin:0;
    padding:0;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
}
.newsList_view .nlist ul li{
    width: 49%;
    text-align: left;
    background:none;
    border: 0px solid #fff;
    cursor:pointer;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    margin:0 0 30px;
    padding:0px;
}
.newsList_view .nlist ul li:hover{
    border: 0px solid var(--theme);
}

.newsList_view .nlist ul li:last-child{
}
.newsList_view .nlist ul li .imgbox{
    width: 180px;
    height: 150px;
    margin-left:20px;
    clip-path: polygon(5% 0, 100% 0, 95% 100%, 0% 100%);
}
.newsList_view .nlist ul li .imgbox img{
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius:0;
}

.newsList_view .nlist ul li .infobox {
    width: calc(100% - 200px);
    flex: 1;
    background:#fff;
    padding:0 20px;
    clip-path: polygon(2% 0, 100% 0, 98% 100%, 0% 100%);
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
}
.newsList_view .nlist ul li:hover .infobox{
    background-size: 100% 100%;
    color: #fff;
}
.newsList_view .nlist ul li .infobox .nam {
    line-height: 40px;
    color: #333;
    font-size: 16px;
    font-weight: 600;
}
.newsList_view .nlist ul li:hover .infobox .nam{
    color: #fff;
}
.newsList_view .nlist ul li .infobox .info{
    color: #666;
    font-size: 14px;
    line-height: 24px;
    height:72px;
    overflow:hidden;
    margin-top: 5px;
}
.newsList_view .nlist ul li:hover .infobox .info{
    color: #fff;
}
.newsList_view .nlist ul li .infobox .tim{
    line-height: 40px;
    color: #999;
    font-size: 15px;
    text-align:right;
}
.newsList_view .nlist ul li:hover .infobox .tim{
    color: #fff;
}

/* 更多 */
.newsList_view .nmore{
    position: absolute;
    top: 60px;
    color: #fff;
    right: calc((100% - 1200px) / 2);
    cursor: pointer;
    padding:10px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, var(--theme), var(--theme));
    background-image: linear-gradient(to left, var(--theme), var(--theme));
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.newsList_view .nmore:hover{
    background-size: 100% 100%;
    color: #fff;
}
/* 自定义 end*/

/* 在线留言 */
.onlineMessage{
    width: 100%;
    margin:0px 0px 0;
    padding: 40px calc((100% - 1220px)/2);
    background: url() no-repeat center top,#f0f0f0;
    overflow: hidden;
    position:relative;
    order: 10;
}
.onlineMessage .box{
    width:100%;
    margin:0 auto;
}
.onlineMessage .box .info-wrapper{
    width: 1200px;
    margin:20px auto 30px;
    background: none;
    text-align: left;
    border-bottom:2px solid var(--theme);
}
.onlineMessage .box .info-wrapper .title{
    display: inline-block;
    background: var(--theme);
    padding: 10px 40px 10px 40px;
    font-size: 22px;
    color:#fff;

}
.onlineMessage .box .info-wrapper .subtitle{
    display: none;
    font-size: 18px;
    line-height: 30px;
    color: #888;
    margin:0 0 20px;
}
.onlineMessage .box .info-wrapper .phone{
    display: none;
}
.onlineMessage .box .el-form{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    flex-direction: row;
    margin-top:10px;
    padding:280px 0 0;
    background: url(http://clfile.zggen.cn/20241021/8f358c8c0e0f4896a69a52134b9d38dd.jpg) no-repeat center top;
}
.onlineMessage .box .el-form .el-form-item{
    width: 100%;
    margin-bottom:20px;
    margin-right:0px;
}
.onlineMessage .box .el-form .el-form-item .el-input{
    border:none;
}

.onlineMessage .box .el-form .name{
    width: 49%;
}
.onlineMessage .box .el-form .name .el-input__inner{
    width: 100%;
    background: #fff;
    border:0px solid #ddd;
    border-radius: 0px;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 42px;
    line-height: 42px;
    padding: 0 20px;
    clip-path: polygon(1% 0, 100% 0, 99% 100%, 0% 100%);
}
.onlineMessage .box .el-form .name input::placeholder {
  color: #888; 
}
.onlineMessage .box .el-form .phone{
    width: 49%;
}
.onlineMessage .box .el-form .phone .el-input__inner{
    width: 100%;
    background: #fff;
    border:0px solid #ddd;
    border-radius: 0px;
    color: #555;
    display: inline-block;
    font-size: inherit;
    height: 42px;
    line-height: 42px;
    padding: 0 20px;
    clip-path: polygon(1% 0, 100% 0, 99% 100%, 0% 100%);
}
.onlineMessage .box .el-form .phone input::placeholder {
  color: #888; 
}
.onlineMessage .box .el-form .content{
    width: 100%;
}
.onlineMessage .box .el-form .content .el-textarea__inner{
    display: block;
    resize: vertical;
    padding: 0px 20px;
    line-height: 1.5;
    width: 100%;
    font-size: inherit;
    color: #555;
    background: #fff;
    border:0px solid #ddd;
    border-radius: 0px;
    height: 120px;
    line-height: 38px;
    min-height: auto !important;
    clip-path: polygon(1% 0, 100% 0, 99% 100%, 0% 100%);
}
.onlineMessage .box .el-form .content textarea::placeholder{
  color: #888; 
}
.onlineMessage .box .el-form .submit{
    width: 100%;
    text-align: center;
}
.onlineMessage .box .el-form .submit .el-button{
    background: var(--theme);
    border: 0px solid #dcdfe6;
    border-radius: 0px;
    color: #fff; 
    font-size:16px;
    min-width:120px;
    background-repeat: no-repeat;
    background-position: bottom;
    background-image: -webkit-linear-gradient(left, #828BD6, #828BD6);
    background-image: linear-gradient(to left, #828BD6, #828BD6);
    background-size: 100% 0;
    transition: all .5s;
    clip-path: polygon(5% 0, 100% 0%, 95% 100%, 0% 100%);
}
.onlineMessage .box .el-form .submit .el-button:hover{
    background-size:100% 100%;
    color: #fff; 
}

</style>