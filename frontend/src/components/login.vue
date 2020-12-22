<template>
	<div class="login_container">
		<!-- <img src="../assets/logo.png" /> -->
		<!-- 登录框 -->
		<div class="login_box">
			<!-- 表单区域 -->
			<el-form label-position="right" ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login_form" label-width="80px">
				<!-- 用户名 -->
				<el-form-item label="用户名" prop="username">
					<el-input prefix-icon="el-icon-user" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
				</el-form-item>
				<!-- 密码 -->
				<el-form-item label="密码" prop="password">
					<el-input prefix-icon="el-icon-lock" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
				</el-form-item>
				<!-- 角色 -->
				<el-form-item label="角色">
				<el-radio-group v-model="loginForm.role" class="role" size="large">
				      <el-radio label="住户" class="r1"></el-radio>
				      <el-radio label="门卫" class="r2"></el-radio>
					  <el-radio label="审核人" class="r2"></el-radio>
				</el-radio-group>
				</el-form-item>
				<!-- 按钮 -->
				<el-form-item class="btns">
					<el-button size="medium" @click="login">登录</el-button>
					<el-button size="medium" type="info" @click="resetLoginForm">重置</el-button>
					<el-button size="medium" @click="visitor">访客</el-button>
				</el-form-item>

			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				loginForm: {
					username: "admin",
					password: "123",
					role:"住户",
				},
				loginRules: {
					username: [{
							required: true,
							message: '请输入用户名',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 10,
							message: '长度在 1 到 10 个字符',
							trigger: 'blur'
						} //验证长度
					],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 10,
							message: '长度在 1 到 10 个字符',
							trigger: 'blur'
						} //验证长度
					]

				},
			}
		},
		methods: {
			//重置表单内容
			resetLoginForm() {
				this.$refs.loginFormRef.resetFields();
				console.log("clear");
			},
			//处理登录方法
			login() {
				this.$refs.loginFormRef.validate(async valid => {
					console.log(valid);
					
					if (!valid) return;
					
					const {
						data: res
					} = await this.$http.post("login",this.loginForm);
					console.log(res);
					//根据不同角色跳转到不同页面
					if (res.flag == "resident") {
						this.$message.success("登录成功")
						this.$router.push({
							path: "/home"
						});
						console.log(res.user);
						window.sessionStorage.setItem("user",JSON.stringify(res.user)); //存储USER对象
					} 
					if (res.flag == "concierge") {
						this.$message.success("登录成功")
						this.$router.push({
							path: "/homeConcierge"
						});
						console.log(res.user);
						window.sessionStorage.setItem("user",JSON.stringify(res.user)); //存储USER对象
					} 
					if (res.flag == "visitor") {
						this.$message.success("登录成功")
						this.$router.push({
							path: "/homeTeacher"
						});
						console.log(res.user);
						window.sessionStorage.setItem("user",JSON.stringify(res.user)); //存储USER对象
					} 
					if (res.flag == "admin") {
						this.$message.success("登录成功")
						this.$router.push({
							path: "/homeAdmin"
						});
						console.log(res.user);
						window.sessionStorage.setItem("user",JSON.stringify(res.user)); //存储USER对象
					} 
					if(res.flag == "error") {
						this.$message.info("登录失败")

					}
				})

			},
			visitor(){
				console.log("visitor");
				
				this.$router.push({
					path: "/visitor"
				});
				
				console.log("visitor ed");
				
			}
		}


	}
</script>

<style lang="less" scoped>
	.login_container {
		height: 100%;
		width: 100%;
		background: url("../assets/login0.png");
		background-size: contain;
		background-repeat: no-repeat;
	}

	.login_box {
		position: absolute;
		background-color: ghostwhite;
		opacity: 0.9;
  	top: 15%;
		right: 10%;
		
		border-radius: 8px;
		height: 290px;
		width: 380px;
	}

	.login_form {
		position: absolute;
		top: 11%;
		left: 3%;
		right: 10%;
	}
</style>
