<template>
	<div>
		<div class="forget_view">
			<el-form :model="forgetForm" class="forget_box">
				<div class="forget_title">Health Life Information Management System{{pageType==1?'Enter Account':pageType==2?'Enter Security Question':'Reset Password'}}</div>
				<div class="tab_line">
					<div class="line"></div>
					<div class="num_line">
						<div class="line_number" :class="pageType==1?'line_number1':'',pageType>1?'line_number2':''"><div class="number" v-if="pageType<2">1</div><el-icon v-else><Check /></el-icon></div>
						<div class="line_number" :class="pageType==2?'line_number1':'',pageType>2?'line_number2':''"><div class="number" v-if="pageType<3">2</div><el-icon v-else><Check /></el-icon></div>
						<div class="line_number" :class="pageType==3?'line_number1':''"><div class="number">3</div></div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script setup>
	import {
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
	} from 'vue';
    import { useRoute } from 'vue-router'
    const route = useRoute()
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	onMounted(()=>{
	})
	const pageType = ref(1)
	const forgetForm = ref({})
	const userForm = ref({})
	// Return to Login
	const close = () => {
		context?.$router.push({
			path: "/login"
		});
	}
</script>

<style lang="scss" scoped>
	.forget_view {
        background-image: url("http://clfile.zggen.cn/20241022/8078e310b766407e990e16bc4492e5ae.jpg")!important;
		// Form Box
		.forget_box {
			// Title
			.forget_title {
				padding: 0 0 30px;
				color: #333;
				font-weight: 600;
				width: 100%;
				font-size: 22px;
				text-align: center;
			}
			// Tab
			// Box
			.tab_line{
				padding: 30px 0;
				flex-direction: column;
				display: flex;
				width: 100%;
				align-items: center;
				// Middle Line
				.line{
				background: #ccc;
				width: 80%;
				height: 1px;
				}
				// Item Box
				.num_line{
					margin: -20px 0 0;
					display: flex;
					width: 80%;
					justify-content: space-between;
					align-items: center;
					height: 40px;
					// Default Style
					.line_number{
						border: 4px solid #ddd;
						border-radius: 50%;
						color: #aaa;
						background: #eee;
						display: flex;
						width: 40px;
						font-size: 18px;
						justify-content: center;
						align-items: center;
						height: 40px;
					}
					// Selected Style
					.line_number1{
						color: var(--theme);
						background: #fff;
						font-size: 22px;
						border-color: var(--theme);
					}
					// Completed Style
					.line_number2{
						color: #339933;
						background: #fff;
						border-color: #339933;
					}
				}
			}
			// Item
			.list_item {
				// Label
				.item_label {
				}
				// Input Box
				:deep(.list_inp) {
				}
				// Dropdown Style
				:deep(.list_sel) {
					// Remove Default Style
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
					}
				}
			}
			// Button Box
			.list_btn {
				// Get Security Question
				:deep(.el-button--success) {
				}
				// Get Security Question Hover
				:deep(.el-button--success:hover) {
				}
				// Confirm Security Question
				:deep(.el-button--primary) {
				}
				// Confirm Security Question Hover
				:deep(.el-button--primary:hover) {
				}
				// Reset Password
				:deep(.el-button--warning) {
				}
				// Reset Password Hover
				:deep(.el-button--warning:hover) {
				}
				.r-login {
				}
			}
		}
	}
</style>
<style lang="scss">

.forget_view{
    min-height: 100vh;
    position: relative;
    background: url(http://clfile.zggen.cn/20241022/8078e310b766407e990e16bc4492e5ae.jpg) no-repeat center center / 100% 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.forget_view .forget_box{
    width: 750px;
    box-shadow: rgb(187, 187, 187) 0px 4px 9px;
    padding: 50px 60px 50px 20px;
    margin:0 0 0 40%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    border-radius: 10px;
    background: rgba(255, 255, 255,1);
    margin-bottom:20px;
    box-shadow: none;
}


.forget_view .outTitle_view{
    display: flex;
    align-items: center;
    padding: 0px 0px 60px;
    position:absolute;
    left:40px;
    top:20px;
}
.forget_view .outTitle_view .outTilte{
    color: #2dc6cc;
    font-size: 24px;
    font-weight: 600;
}


.forget_view .forget_box .tab_view{
    width: 100%;
    display: flex;
    align-items: center;
    padding: 0px 0px 20px;
}

.forget_view .forget_box .tab_view .tab{
    width: calc(33.3333%);
    padding: 16px 0px;
    box-sizing: border-box;
    text-align: center;
    font-size: 16px;
}
.forget_view .forget_box .tab_view .tab.tab_active{
    width: calc(33.3333%);
    padding: 16px 0px;
    box-sizing: border-box;
    text-align: center;
    font-size: 16px;
    clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
    background: rgb(254, 176, 67);
    color: rgb(255, 255, 255);
}


.forget_view .forget_box .list_item{
display: flex;
    align-items: center;
    width: 100%;
    justify-content: flex-start;
    margin: 0px 0px 20px;
}
.forget_view .forget_box .list_item .item_label{
    width: 120px;
    text-align: right;
    box-sizing: border-box;
    font-size: 16px;
}
.forget_view .forget_box .list_item .list_inp{
    height: 40px;
    line-height: 40px;
    border:none;
    border: 1px solid #ddd;
    padding: 0px 10px;
    width: calc(100% - 120px);
    box-sizing: border-box;
    font-size: 15px;
}
.forget_view .forget_box .list_item .list_sel{
    height: 40px;
    line-height: 40px;
    border:none;
    border: 1px solid #ddd;
    box-sizing: border-box;
    width: calc(100% - 120px);
    padding: 0px 10px;
    border-radius: 0px;
}
.forget_view .forget_box .list_item .list_sel .el-input__inner{
    font-size: 15px;
    color:#666;
}


.forget_view .forget_box .list_btn{
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    width: 100%;
    margin: 0px;
    padding: 20px 0px 0px 120px;
}

.forget_view .forget_box .list_btn .get_btn{
    margin: 0px 0px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 40px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border: 0px;
    border-radius: 0;
    cursor: pointer;
    background: var(--theme);
    min-width:120px;
}

.forget_view .forget_box .list_btn .valid_btn{
    margin: 0px 0px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 40px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border: 0px;
    border-radius: 0;
    cursor: pointer;
    background: var(--theme);
    min-width:120px;
}

.forget_view .forget_box .list_btn .update_btn{
    margin: 0px 0px 0px 0px;
    padding: 0px 24px;
    width: auto;
    height: 40px;
    font-size: 16px;
    color: rgb(255, 255, 255);
    border: 0px;
    border-radius: 0;
    cursor: pointer;
    background:var(--theme);
    min-width:120px;
}

.forget_view .forget_box .list_btn .r-login{
    width: 100%;
    text-align: right;
    cursor: pointer;
    padding: 20px 0px 0px;
    font-size: 15px;
    color: #666;
}
.forget_view .forget_box .list_btn .r-login:hover{
    text-decoration:underline;
}

</style>
