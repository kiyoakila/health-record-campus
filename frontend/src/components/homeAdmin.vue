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

					<el-submenu index="1">
						<template slot="title">
							<img src="../assets/用户.png" class="ico">
							<span>住户信息管理</span>
						</template>
						<el-menu-item-group>
							<el-menu-item index="/addr">
							<img src="../assets/添加.png" class="ico">添加住户</el-menu-item>
							<el-menu-item index="/deleter">
							<img src="../assets/删除.png" class="ico">删除住户</el-menu-item>
							<el-menu-item index="/updater">
							<img src="../assets/修改.png" class="ico">修改住户信息</el-menu-item>
							<el-menu-item index="/updaterpwd">
							<img src="../assets/密码.png" class="ico">修改住户密码</el-menu-item>
						</el-menu-item-group>
					</el-submenu>
					
					<el-menu-item index="/audit">
						<template slot="title">
							<img src="../assets/审核.png" class="ico">
							<span>审核申请表</span>
						</template>
					</el-menu-item>
					
					<el-menu-item index="/community">
						<template slot="title">
							<img src="../assets/表格.png" class="ico">
							<span>查看社区情况</span>
						</template>
					</el-menu-item>

					
					<el-menu-item index="/adminpwd">
						<img src="../assets/设置.png" class="ico">
						<span slot="title">修改密码</span>
					</el-menu-item>

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
		data() {
			return {
				menulist: [],
			}
		},
		created() {
			//查询menulist
			this.getMenuList();

		},
		methods: {
			logout() {
				window.sessionStorage.clear(); //清除SESSION
				this.$router.push("/login"); //回到首页
			},
			//获取导航菜单方法
			getMenuList() {
				console.log("getMenuList");
			},
		}

	}
</script>

<style lang='less' scoped>

	//布局器样式
	.home-container {
		height: 100%;
/*		width: 100%;
		background: url("../assets/f1.png");
		background-size: contain;
		background-position: 30% 70%;
		background-repeat: no-repeat; */

	
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