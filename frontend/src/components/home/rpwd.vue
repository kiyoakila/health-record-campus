<template>
	<div class="spwd_container">
		<!-- <img src="../assets/logo.png" /> -->
		<!-- 登录框 -->
		<div class="spwd_box">
			<!-- 表单区域 -->
			<el-form :model="ruleForm" label-position="left" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
				<el-form-item label="旧密码" prop="oldpass">
					<el-input type="password" v-model="ruleForm.oldpass" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="新密码" prop="pass">
					<el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
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
			var validateOldPass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入密码'));
				} else {
					if (this.ruleForm.checkOldPass !== '') {
						this.$refs.ruleForm.validateField('pass');
					}
					callback();
				}
			};
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
				} else if (value !== this.ruleForm.pass) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			return {
				ruleForm: {
					pass: '',
					checkPass: '',
					oldpass: '',
					username:'',
	
				},
				rules: {
					oldpass: [{
						validator: validateOldPass,
						trigger: 'blur'
					}],
					pass: [{
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
					var user = JSON.parse(window.sessionStorage.getItem('user'))
					this.ruleForm.username=user.username
					if (!valid) return;

					const {
						data: res
					} = await this.$http.post("updateRpwd", this.ruleForm);
					console.log("res:",res);
					if (res) {
						this.$message.success("修改密码成功");

					} else {
						this.$message.info("请输入正确的密码");

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

	.demo-ruleForm {
		position: absolute;
		top: 50px;
		left: 10%;

	}
</style>
