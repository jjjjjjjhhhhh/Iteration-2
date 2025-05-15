<template>
	<div class="home_view">
		<div class="projectTitle">Welcome to use {{projectName}}</div>
		<div class="count_list">
			<el-collapse-transition v-if="btnAuth('yonghu','首页总数')">
				<el-card v-show="countTypeList.closeyonghuCountType" class="card_view count-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								User
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenyonghuCountType')" class="showIcons"
									:class="countTypeList.hiddenyonghuCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closeyonghuCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenyonghuCountType">
							<div class="count_title">Total number of users</div>
							<div class="count_num">{{yonghuCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('jiankangzhishi','首页总数')">
				<el-card v-show="countTypeList.closejiankangzhishiCountType" class="card_view count-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								Health knowledge
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenjiankangzhishiCountType')" class="showIcons"
									:class="countTypeList.hiddenjiankangzhishiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closejiankangzhishiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenjiankangzhishiCountType">
							<div class="count_title">Total amount of health knowledge</div>
							<div class="count_num">{{jiankangzhishiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('yonghu','首页统计')">
				<el-card v-show="cardTypeList.closeyonghuChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								User
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenyonghuChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenyonghuChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closeyonghuChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenyonghuChartType1">
							<div id="yonghuxingbieEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('jiankangzhishi','首页统计')">
				<el-card v-show="cardTypeList.closejiankangzhishiChartType1" class="card_view chart-item">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								Health knowledge
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenjiankangzhishiChartType1')" class="showIcons"
										 :class="cardTypeList.hiddenjiankangzhishiChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closejiankangzhishiChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenjiankangzhishiChartType1">
							<div id="jiankangzhishistoreupNumberEchart1" class="Echart" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closeyonghuCountType = true
		countTypeList.value.hiddenyonghuCountType = true
		if(btnAuth('yonghu','首页总数')){
			getyonghuCount()
		}
		countTypeList.value.closejiankangzhishiCountType = true
		countTypeList.value.hiddenjiankangzhishiCountType = true
		if(btnAuth('jiankangzhishi','首页总数')){
			getjiankangzhishiCount()
		}
	}
	const yonghuCount = ref(0)
	const getyonghuCount = () => {
		context?.$http({
			url:'yonghu/count',
			method: 'get'
		}).then(res=>{
			yonghuCount.value = res.data.data
		})
	}
	const jiankangzhishiCount = ref(0)
	const getjiankangzhishiCount = () => {
		context?.$http({
			url:'jiankangzhishi/count',
			method: 'get'
		}).then(res=>{
			jiankangzhishiCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closeyonghuChartType1: true,
		hiddenyonghuChartType1: true,
		closejiankangzhishiChartType1: true,
		hiddenjiankangzhishiChartType1: true,
	})
	const getCardList = () => {
		if(btnAuth('yonghu','首页统计')){
			getyonghuChart1()
		}
		if(btnAuth('jiankangzhishi','首页统计')){
			getjiankangzhishiChart1()
		}
	}
	import '@/assets/js/echarts-theme'
	const getyonghuChart1 = () => {
		nextTick(()=>{
			var xingbieEchart1 = echarts.init(document.getElementById("yonghuxingbieEchart1"),'theme');
			context?.$http({
				url: "yonghu/group/xingbie",
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].xingbie);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].xingbie
				    })
				}
				var option = {};
				option = {
    title:{
        text: 'User statistics',
        left: 'center'
    },
    legend: {
        orient: 'vertical',
        left: 'left'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c} ({d}%)'
    },
    series: [
        {
            left: '20%',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: dataList,
            emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
}
				xingbieEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				//根据窗口的大小变动图表
				xingbieEchart1.resize();
			})
		})
	}
	const getjiankangzhishiChart1 = () => {
		nextTick(()=>{
			var storeupNumberEchart1 = echarts.init(document.getElementById("jiankangzhishistoreupNumberEchart1"),'theme');
			context?.$http({
				url: `jiankangzhishi/value/biaoti/storeupNumber?order=desc`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].biaoti);
				    yAxis.push(parseFloat((res[i].total)));
					dataList.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].biaoti
				    })
				}
				var option = {};
				option = {
    title: {
        text: 'Popular Health Knowledge Statistics',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b} : {c}'
    },
    xAxis: {
        data: xAxis,
        type: 'category',
        axisLabel: {
        interval: 0,
        rotate: 30
        }
    },
    yAxis: {
        type: 'value',
        minInterval: 1
    },
    series:{
        data: yAxis,
        type: 'bar',
        barWidth:'20px',
        itemStyle: {
            borderRadius:[4, 4, 4, 4]
        }
      
    }
}

				storeupNumberEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				storeupNumberEchart1.setOption(option);
				//根据窗口的大小变动图表
				storeupNumberEchart1.resize();
			})
		})
	}

	const init=()=>{
		getCountList()
		getCardList()
	}
	init()
</script>
<style lang="scss" scoped>
	// 首页盒子
	.home_view {
        background-image: url("http://clfile.zggen.cn/20240915/2ba09c686491481982bda72452d5ca8f.webp")!important;
	}
	.home_view .projectTitle{
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		// 总数card
		.card_view {
			// card头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon {
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.count_item{
					// 总数标题
					.count_title{
					}
					// 总数数字
					.count_num{
					}
				}
			}
		}
	}

	// 统计图盒子
	.card_list {
		// 统计图card
		.card_view {
			// 头部
			.el-card__header {
				// 头部盒子
				.index_card_head {
					// 标题
					.card_head_title {
					}
					// 按钮盒子
					.card_head_right {
						// 按钮
						.el-icon{
						}
					}
				}
			}
			// body
			.el-card__body {
				// body盒子
				.card_item{
				}
			}
		}
	}
</style>
<style>
/*总盒子*/
.home_view{
    padding: 20px 30px 20px 40px;
    margin: 0px;
    height: auto;
    min-height: 100vh;
    background: url() no-repeat center top / cover !important;
    width: 100%;
    border-radius: 0px;
    display:flex;
    flex-wrap:wrap;
    align-items:flex-start;
}
.home_view .projectTitle{
    width: 100%;
    font-size: 26px;
    font-weight: bold;
    padding: 40px 0px 20px;
    height: auto;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px 0px 20px;
    color: rgb(55, 55, 55);
}

/*总数*/
/*总盒子*/
.home_view .count_list {
    display:flex;
    flex-wrap:wrap;
    justify-content:center;
    padding:0px 0px 20px;
    width:100%;
    align-items:flex-start;
}
/*卡片盒子*/
.home_view .count_list .card_view {
    width:23%;
    height:auto;
    margin:0px 1% 20px;
    box-sizing:border-box;
    background:var(--theme10);
    border:1px solid var(--theme30);
    border-radius:30px;
    flex: 1;
    padding:0;
    box-shadow: none;
}

.home_view .count_list .card_view:nth-child(1) {
}
.home_view .count_list .card_view:nth-child(2) {
}
.home_view .count_list .card_view:nth-child(3) {
}
.home_view .count_list .card_view:nth-child(4) {
}
.home_view .count_list .card_view:nth-child(2n+5) {
}
.home_view .count_list .card_view:nth-child(2n+6) {
}
.home_view .count_list .card_view:nth-child(1):hover {
}
.home_view .count_list .card_view:nth-child(2):hover {
}
.home_view .count_list .card_view:nth-child(3):hover {
}
.home_view .count_list .card_view:nth-child(4):hover {
}
.home_view .count_list .card_view:nth-child(2n+5):hover {
}
.home_view .count_list .card_view:nth-child(2n+6):hover {
}

/*head 总盒子*/
.home_view .count_list .card_view .el-card__header{
     width: 100%;
    border: 0px solid rgb(238, 238, 238);
    display: none;
}
/*item*/
.home_view .count_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 0px;
}
/*标题*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_title{
    font-size: 14px;
}
/*按钮盒子*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right{
    display: flex;
    align-items: center;
}
/*按钮*/
.home_view .count_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(102, 102, 102);
    font-size: 20px;
    cursor: pointer;
}
/*body 总盒子*/
.home_view .count_list .card_view .el-card__body{
    padding: 30px;
    background: none;
}
/*item*/
.home_view .count_list .card_view .el-card__body .count_item{
    padding: 0px;
    text-align: left;
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    flex-direction: column;
}
/*标题*/
.home_view .count_list .card_view .el-card__body .count_item .count_title{
    font-size: 16px;
    line-height: 2;
    display: inline-block;
    margin: 0px 10px 0px 0px;
    font-weight: 500;
    width: 100%;
    text-align:center;
    color: #333;
    background:var(--theme30);
    padding: 5px;
    border-radius:20px;
}
/*数字*/
.home_view .count_list .card_view .el-card__body .count_item .count_num{
    width: 100%;
    font-size: 30px;
    color: var(--theme);
    line-height: 2;
    display: inline-block;
    font-weight: 500;
    text-align:center;
}

/*图表*/
/*总盒子*/
.home_view .card_list{
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    padding: 0px 0px 20px;
    align-items: flex-start;
    order:11;
}
/*卡片 总盒子*/
.home_view .card_list .card_view{
    width: 48%;
    height: auto;
    margin: 0px 1% 30px;
    box-sizing: border-box;
    border:1px solid var(--theme30);
    border-radius: 0px;
    padding: 20px 0px 0px;
    box-shadow: 0 0px 0px 0 rgba(0,0,0,.1);
    border-radius:30px;
}
/*head 总盒子*/
.home_view .card_list .card_view .el-card__header{
    width: 100%;
    border: 0px solid rgb(238, 238, 238);
    background: rgb(255, 255, 255);
    display: none;
}
/*item*/
.home_view .card_list .card_view .el-card__header .index_card_head{
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 10px;
}
/*标题*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_title{
    color: rgb(33, 33, 33);
}
/*按钮盒子*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right{
    display: none;
}
/*按钮*/
.home_view .card_list .card_view .el-card__header .index_card_head .card_head_right .showIcons{
    color: rgb(238, 238, 238);
    font-size: 20px;
    cursor: pointer;
}

/*body 总盒子*/
.home_view .card_list .card_view .el-card__body{
    padding: 0px;
    background: rgb(255, 255, 255);
}
.home_view .card_list .card_view .el-card__body .card_item{
    padding: 10px;
    text-align: center;
}

.home-calendar{
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: #fff;
  width: calc(100% - 30px);
  font-size: inherit;
  height: auto;
  padding:0 0 10px;
  order:2;
}
.home-calendar .header{
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  height: auto;
  padding: 17px 40px;
  background:var(--theme10);
  border:1px solid var(--theme30);
  border-radius:30px 30px 0 0;
}
.home-calendar .header .btn{
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: #333;
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: 16px;
  justify-content: center;
}
.home-calendar .header .title{
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: #333;
}
.home-calendar table{
  width: 100%;
  background: none;
  padding: 0px 0px 20px;
  height: auto;
  border:1px solid var(--theme30);
  border-radius:0 0 30px 30px;
}
.home-calendar tbody,thead{
  width: 100%;
}
.home-calendar tr{
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th{
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td{
  cursor: pointer;
  padding: 6px 12px 6px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar td.today .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: var(--theme50) !important;
  color:var(--theme);
  border-radius:0px;
}
.home-calendar td.festival .text{
  width: 80%;
  height: 80%;
  padding:5px 0;
  background: var(--theme20);
  border-radius:0px;
}

</style>
