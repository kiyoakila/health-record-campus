<template>
	<!-- 	引入container布局 -->
	<el-container class="home-container">
		<!-- 		头部 -->
		<el-header>
			<div>
				<img src="../assets/医疗中心.png" alt="" />
				<span>社区管理系统</span>
			</div>
			<el-button type="info" icon="el-icon-close" circle class="btn" @click="logout"></el-button>
		</el-header>
		<!-- 	  主体 -->
		<el-container>
			<!-- 		  侧边栏 -->
			<el-aside width="200px">
				<el-menu background-color="#d8e1a62a" active-text-color="#6fb0be" :router="true">
					<el-menu-item index="/apply">	
						<span slot="title"><img src="../assets/添加.png" class="ico">申请外出</span>
					</el-menu-item>
					<el-menu-item index="/result">
						<span slot="title"><img src="../assets/表格.png" class="ico">申请结果</span>
					</el-menu-item>
					
					<el-menu-item index="/confirm">
						<span slot="title"><img src="../assets/确认.png" class="ico">确认外出</span>
					</el-menu-item>
					
					<el-submenu index="4">
						<template slot="title">
							<span><img src="../assets/用户.png" class="ico">个人信息</span>
						</template>
						<el-menu-item-group>
							<el-menu-item index="/rinfo"><img src="../assets/信息.png" class="ico">个人信息</el-menu-item>
							<el-menu-item index="/rpwd"><img src="../assets/设置.png" class="ico">修改密码</el-menu-item>
						</el-menu-item-group>
					</el-submenu>
				</el-menu>

			</el-aside>
			<!-- 		主体内容 -->
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default {
		data(){
			return{
				menulist:[],
			}
		},
		created(){
			//查询menulist
			this.getMenuList();
			
		},
		methods: {
			logout() {
				window.sessionStorage.clear(); //清除SESSION
				this.$router.push("/login"); //回到首页
			},
			//获取导航菜单方法
			getMenuList(){
				console.log("getMenuList");
			},
		}

	}
</script>

<style lang='less' scoped>
	//布局器样式
	.home-container {
		height: 100%;

	}

	//头部样式
	.el-header {
//		background-color: #d8e1a683;
		background-color: #d8e1a6c5;
		display: flex;
		justify-content: space-between; //左右贴边
		color: rgba(10, 7, 7, 0.644); //字体颜色
		font-size: 20px; //字体大小
		padding-left: 1.5%;
		div {
			display: flex;
			align-items: center;
		}
		span {
			margin-left:8px;
		}
	}

	//侧边栏 样式
	.el-aside {
		background-color: #d8e1a646;
		color: #d8e1a62a;
	//	text-align: left;
		line-height: 200px;

	}

	//主体样式
	.el-main {
		background-color: #d8e1a60e;
		color: #4d595f;
		text-align: center;
		line-height: 160px;
	}

	img {
		width: 35px;
		//height: 58px;
		margin-top: 0%;
		//margin-left: 5%; 
		//border-radius: 25px;
	}

	.ico {
		width: 20px;
		//height: 58px;
		margin-right: 4%;
		//margin-left: 0%; 
		//border-radius: 25px;
	}

	.btn {
		//color: #B3C0D1;
		color: #5d7a8b;
		background-color: #ffffff00;
		margin-right: 0.5%;
		height: 70%;
		margin-top: 0.5%;
		border-color: #ffffff00;
		/* B3C0D1 */
	}
</style>
