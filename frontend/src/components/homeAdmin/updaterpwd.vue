<template>
	<div class="spwd_container">
		<!-- <img src="../assets/logo.png" /> -->
		<!-- 登录框 -->
		<div class="spwd_box">
			<!-- 表单区域 -->
			<el-form :model="ruleForm" label-position="left" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="tForm">
				<el-form-item label="住户姓名" prop="username">
					<el-input v-model="ruleForm.username" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="新密码" prop="password">
					<el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="确认密码" prop="checkPass">
					<el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
				</el-form-item>

				<el-form-item>
					<el-button size="medium" @click="updatepwd()">保 存</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			var validatePass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入密码'));
				} else {
					if (this.ruleForm.checkPass !== '') {
						this.$refs.ruleForm.validateField('checkPass');
					}
					callback();
				}
			};
			var validatePass2 = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请再次输入密码'));
				} else if (value !== this.ruleForm.password) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			return {
				ruleForm: {
					password: '',
					checkPass: '',
					username: ''
				},
				rules: {
					password: [{
						validator: validatePass,
						trigger: 'blur'
					}],
					checkPass: [{
						validator: validatePass2,
						trigger: 'blur'
					}],
				}
			};
		},
		methods: {
			//修改密码
			updatepwd() {
				this.$refs.ruleForm.validate(async valid => {
					console.log(valid);

					if (!valid) return;
					console.log(this.ruleForm);

					const {
						data: res
					} = await this.$http.post("uprpwd", this.ruleForm);
					console.log("res:",res);
					if (res) {
						this.$message.success("修改密码成功");

					} else {
						this.$message.info("修改密码失败");

					}
					this.$refs.ruleForm.resetFields();
				})

			}
		}


	}
</script>

<style lang="less" scoped>
  .spwd_container{
		height: 100%;
		width: 100%;
		background: url("../../assets/f3.png");
		background-size: 600px auto;
		background-position: 80% 100%;
		background-repeat: no-repeat;
	
	}

	.spwd_box {
		position: absolute;
		top: 200px;
		left: 40%;
		border-radius: 8px;
		background-color: #f4f7e750;
		background-size: cover;
		height: 320px;
		width: 380px;
	}

	.tForm {
		position: absolute;
		top: 50px;
		left: 10%;
		right: 10%;
		bottom: 25px;
	}
</style>