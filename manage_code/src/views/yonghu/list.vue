<template>
	<div>
		<div class="center_view">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							User account：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.yonghuzhanghao" placeholder="User account"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">Search</el-button>
					</div>
				</el-form>
				<div class="btn_view">
					<el-button class="add_btn" type="success" @click="addClick" v-if="btnAuth('yonghu','新增')">
						<i class="iconfont icon-xinzeng4"></i>
						Add
					</el-button>
					<el-button class="del_btn" type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('yonghu','删除')">
						<i class="iconfont icon-shanchu4"></i>
						Delete
					</el-button>
                    <el-button class="statis_btn" type="warning" @click="echartClick1" v-if="btnAuth('yonghu','用户统计')">
                        <i class="iconfont icon-bar-chart--line"></i>
                        User statistics
                    </el-button>
				</div>
			</div>
			<el-table
				v-loading="listLoading"
				border
				:stripe='false'
				@selection-change="handleSelectionChange"
				ref="table"
				v-if="btnAuth('yonghu','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="left" type="selection" width="55" />
				<el-table-column label="Serial Number" width="70" :resizable='true' align="left" header-align="left">
					<template #default="scope">{{ (listQuery.page-1)*listQuery.limit+scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='true'
					align="left"
					header-align="left"
					prop="yonghuzhanghao"
					label="User account">
					<template #default="scope">
						{{scope.row.yonghuzhanghao}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='true'
					align="left"
					header-align="left"
					prop="yonghuxingming"
					label="User Name">
					<template #default="scope">
						{{scope.row.yonghuxingming}}
					</template>
				</el-table-column>
				<el-table-column label="Icon" min-width="140" width="120" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<div v-if="scope.row.touxiang">
							<el-image v-if="scope.row.touxiang.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.touxiang.split(',')[0]]"
								:src="scope.row.touxiang.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.touxiang.split(',')[0]]"
								:src="$config.url+scope.row.touxiang.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>No pictures</div>
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='true'
					align="left"
					header-align="left"
					prop="xingbie"
					label="gender">
					<template #default="scope">
						{{scope.row.xingbie}}
					</template>
				</el-table-column>
				<el-table-column min-width="140"
					:resizable='true'
					:sortable='true'
					align="left"
					header-align="left"
					prop="shoujihaoma"
					label="Phone number">
					<template #default="scope">
						{{scope.row.shoujihaoma}}
					</template>
				</el-table-column>
				<el-table-column label="operate" width="300" :resizable='true' :sortable='true' align="left" header-align="left">
					<template #default="scope">
						<el-button class="view_btn" type="info" v-if=" btnAuth('yonghu','查看')" @click="infoClick(scope.row.id)">
							<i class="iconfont icon-sousuo5"></i>
							View
						</el-button>
						<el-button class="edit_btn" type="primary" @click="editClick(scope.row.id)" v-if=" btnAuth('yonghu','修改')">
							<i class="iconfont icon-xiugai5"></i>
							Edit						</el-button>
						<el-button class="del_btn" type="danger" @click="delClick(scope.row.id)"  v-if="btnAuth('yonghu','删除')">
							<i class="iconfont icon-shanchu4"></i>
							Delete					</el-button>
                        <el-button class="operate_btn" v-if="btnAuth('yonghu','私信')" type="success" @click="chatClick(scope.row)">
                         Private message
                        </el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination
				background
				:layout="layouts.join(',')"
				:total="total"
				:page-size="listQuery.limit"
                v-model:current-page="listQuery.page"
				prev-text="Previous page"
				next-text="Next page"
				:hide-on-single-page="false"
				:style='{}'
				:page-sizes="[10, 20, 30, 40, 50, 100]"
				@size-change="sizeChange"
				@current-change="currentChange"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
		<!-- 统计图弹窗 -->
		<el-dialog v-model="echartVisible" title="Statistical chart" width="70%">
            <div  id="xingbieEchart1" style="width:100%;height:600px;"></div>
			<template #footer>
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="echartVisible=false">Cancel</el-button>
				</span>
			</template>
		</el-dialog>
        <el-dialog v-model="chatVisible" @close="clearChat" :title="nowname" :append-to-body="true">
            <div class="chat-content" id="chat-content">
                <div v-bind:key="item.id" v-for="(item,index) in chatList">
                    <div v-if="index>1&&moment(chatList[index-1].addtime).date()!=moment(item.addtime).date()||index==0"
                         style="font-size: 12px;text-align: center;margin: 4px 0;">
                        {{moment(item.addtime).format("MM-DD HH:mm")}}
                    </div>
                    <div v-if="item.uid==user.id" class="right-content">
                        <el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
                                  type="warning"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
                        <el-image v-else fit="cover" :src="item.content?$config.url + item.content:''"
                                  style="width: 100px;height: 100px;"
                                  :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
                        <img :src="avatar?$config.url + avatar:require('@/assets/img/avatar.png')" alt=""
                             style="width: 30px;border-radius: 50%;height: 30px;margin: 0 0 0 10px;flex-shrink: 0;object-fit: cover;" />
                    </div>
                    <div v-else class="left-content">
                        <img :src="nowfpic?$config.url + nowfpic:require('@/assets/img/avatar.png')" alt=""
                             style="width: 30px;border-radius: 50%;height: 30px;margin: 0 10px 0 0;flex-shrink: 0;object-fit: cover;" />
                        <el-alert v-if="item.format==1" class="text-content" :title="item.content" :closable="false"
                                  type="success"></el-alert>
                        <video v-else-if="item.content.endsWith('.mp4')" controls style="width: 200px;height: 160px">
                            <source  :src="$config.url + item.content">
                        </video>
                        <el-image v-else fit="cover" :src="item.content?$config.url + item.content:''"
                                  style="width: 100px;height: 100px;"
                                  :preview-src-list="[item.content?$config.url + item.content:'']"></el-image>
                    </div>
                    <div class="clear-float"></div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-input @keydown.enter.native="addChat" v-model="chatForm.content" placeholder="Enter content"
                          style="width: calc(100% - 180px);float: left;">
                </el-input>
                <el-button :disabled="chatForm.content?false:true" type="primary" @click="addChat">Send</el-button>
                <el-upload style="display: inline-block;margin: 0 0 0 6px;" class="upload-demo" :action="uploadUrl"
                           :on-success="uploadSuccess" :show-file-list="false" accept="image/*,.mp4">
                    <el-button type="success">Upload files</el-button>
                </el-upload>
            </div>
        </el-dialog>
	</div>
</template>
<script setup>
	import axios from 'axios'
    import moment from "moment"
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
		computed,
		inject
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useStore
	} from 'vuex';
	const store = useStore()
	const user = computed(()=>store.getters['user/session'])
	const avatar = ref(store.state.user.avatar)
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	//基础信息

	const tableName = 'yonghu'
	const formName = 'User'
	const route = useRoute()
    const router = useRouter()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 10,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			//table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.yonghuzhanghao&&searchQuery.value.yonghuzhanghao!=''){
			params['yonghuzhanghao'] = '%' + searchQuery.value.yonghuzhanghao + '%'
		}
		context.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`Do you want to delete the selected item${formName}`, 'Tip', {
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			type: 'warning',
		}).then(() => {
			context.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('Delete successfully', 'success',()=>{
					getList()
				})
			})
		}).catch(_ => {})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//Search
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'edit')
			return
		}
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}

	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('File does not exist','error')
		}
		window.open(context?.$config.url + file)
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('File does not exist','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
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
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}
    import '@/assets/js/echarts-theme'
	// 统计图1
	const echarts = inject("echarts")
	const echartVisible = ref(false)
	const echartClick1 = ()=>{
		echartVisible.value = true
		nextTick(()=>{
			var xingbieEchart1 = echarts.init(document.getElementById("xingbieEchart1"),'theme');
			context.$http({
				url: `${tableName}/group/xingbie`,
				method: 'get'
			}).then(res=>{
				let obj = res.data.data
				let xAxis = [];
				let yAxis = [];
				let dataList = []
				for(let i=0;i<obj.length;i++){
				    xAxis.push(obj[i].xingbie);
				    yAxis.push(parseFloat((obj[i].total)));
                    dataList.push({
				        value: parseFloat((obj[i].total)),
				        name: obj[i].xingbie
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
				// 使用刚指定的配置项和数据显示图表。
				xingbieEchart1.setOption(option);
				  //根据窗口的大小变动图表
				window.onresize = function() {
				    xingbieEchart1.resize();
				};
			})
		})
	}
    const chatVisible = ref(false)
    const nowfid = ref(0)
    const nowfpic = ref('')
    const nowname = ref('')
    const chatList = ref([])
    const chatForm = ref({
        content: ''
    })
    const chatTimer = ref(null)
    const uploadUrl = context?.$config.url + 'file/upload'
    const chatClick = (row) => {
        if (row.id == user.value.id) {
            context.$toolUtil.message('You cant message yourself!','error')
            return false
        }
        nowfid.value = row.id
        if (row.touxiang) {
            nowfpic.value = row.touxiang.split(',')[0]
        } else if (row.headportrait) {
            nowfpic.value = row.headportrait.split(',')[0]
        }
        if (row.yonghuzhanghao) {
            nowname.value = row.yonghuzhanghao
        }
        scrollFlag.value = true
        getChatList()
        chatVisible.value = true
    }
    const scrollFlag = ref(true)
    const getChatList = () => {
        context.$http.get('chatmessage/mlist', {
            params: {
                page: 1,
                limit: 1000,
                uid: user.value.id,
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
        getList()
    }
    const uploadSuccess = (res) => {
        if (res.code == 0) {
            clearTimeout(chatTimer.value)
            context.$http.get('chatfriend/page', {
                params: {
                    uid: user.value.id,
                    fid: nowfid.value,
                }
            }).then(obj => {
                if (obj.data && obj.data.code == 0) {
                    if (!obj.data.data.list.length) {
                        context.$http.post('chatfriend/add', {
                            uid: user.value.id,
                            fid: nowfid.value,
                            name: nowname.value,
                            picture: nowfpic.value,
                            type: 2,
                            tablename: 'yonghu',
                        }).then(res => {
                            context.$http.post('chatfriend/add', {
                                uid: nowfid.value,
                                fid: user.value.id,
                                type: 2,
                                tablename: 'yonghu',
                                name: context.$toolUtil.storageGet('adminName'),
                                picture: avatar.value,
                            }).then(res1 => {

                            })
                        })
                    }
                    context.$http.post('chatmessage/add', {
                        uid: user.value.id,
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
            })
        }
    }
    const addChat = () => {
        if(!chatForm.value.content.trim())return context.$message.error("The message content cannot be empty")
        clearTimeout(chatTimer.value)
        context.$http.get('chatfriend/page', {
            params: {
                uid: user.value.id,
                fid: nowfid.value,
            }
        }).then(obj => {
            if (obj.data && obj.data.code == 0) {
                if (!obj.data.data.list.length) {
                    context.$http.post('chatfriend/add', {
                        uid: user.value.id,
                        fid: nowfid.value,
                        name: nowname.value,
                        picture: nowfpic.value,
                        type: 2,
                        tablename:  'yonghu',
                    }).then(res => {
                        context.$http.post('chatfriend/add', {
                            uid: nowfid.value,
                            fid: user.value.id,
                            type: 2,
                            tablename: localStorage.getItem('sessionTable'),
                            name: context.$toolUtil.storageGet('adminName'),
                            picture: avatar.value,
                        }).then(res1 => {

                        })
                    })
                }
                context.$http.post('chatmessage/add', {
                    uid: user.value.id,
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
        })
    }
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>

	// operate盒子
	.list_search_view {
		// Search盒子
		.search_form {
			// 子盒子
			.search_view {
				// Searchlabel
				.search_label {
				}
				// Searchitem
				.search_box {
					// 输入框
					:deep(.search_inp) {
					}
				}
			}
			// Search按钮盒子
			.search_btn_view {
				// Search按钮
				.search_btn {
				}
				// Search按钮-悬浮
				.search_btn:hover {
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			// 其他
			:deep(.el-button--default){
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
			}
			// 新增
			:deep(.el-button--success){
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
			}
			// 删除
			:deep(.el-button--danger){
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
			}
			// 统计
			:deep(.el-button--warning){
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
			}
		}
	}
	// 表格样式
	.el-table {
		:deep(.el-table__header-wrapper) {
			thead {
				tr {
					th {
						.cell {
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				tr {
					td {
						.cell {
							// 编辑
							.el-button--primary {
							}
							// 编辑-悬浮
							.el-button--primary:hover {
							}
							// 详情
							.el-button--info {
							}
							// 详情-悬浮
							.el-button--info:hover {
							}
							// 删除
							.el-button--danger {
							}
							// 删除-悬浮
							.el-button--danger:hover {
							}
							// 跨表
							.el-button--success {
							}
							// 跨表-悬浮
							.el-button--success:hover {
							}
							// operate
							.el-button--warning {
							}
							// operate-悬浮
							.el-button--warning:hover {
							}
						}
					}
				}
				tr:hover {
					td {
					}
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// Previous page
		:deep(.btn-prev) {
		}
		// Next page
		:deep(.btn-next) {
		}
		// Previous page禁用
		:deep(.btn-prev:disabled) {
		}
		// Next page禁用
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
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {
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
