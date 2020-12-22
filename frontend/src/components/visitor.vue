<template>
	<div class="visitorcontainer">
		<div class="adds_box">
			<el-form :model="form" label-position="left" label-width="80px" class="adds_form">
				<el-form-item label="姓名">
					<el-input v-model="form.name" placeholder="请输入姓名"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-radio-group v-model="form.sex" class="ras">
						<el-radio label="男"></el-radio>
						<el-radio label="女"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="身份证号">
					<el-input v-model="form.idcard" placeholder="请输入身份证号"></el-input>
				</el-form-item>
				<el-form-item label="手机号码">
					<el-input v-model="form.phone" placeholder="请输入11位手机号"></el-input>
				</el-form-item>
				<el-form-item label="体温">
					<el-input v-model="form.temperature" placeholder="请输入体温"></el-input>
				</el-form-item>
				<el-form-item label="原因">
					<el-input v-model="form.reason" placeholder="请输入原因"></el-input>
				</el-form-item>
				<el-form-item class="btns">
					<el-button size="medium" @click="addv()">登 记</el-button>
					<el-button size="medium" type="info" @click="relogin()">返 回</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		inject: ['reload'],
		data() {
			return {
				options: [{}],
				SinfoData: [{}],
				form: {
					idcard: '',
					name: '',
					sex: '',
					phone: '',
					temperature: '',
					reason:'',


				}
			}
		},
		created: async function() {
			console.log("adds created");
			// const {
			// 	data: res
			// } = await this.$http.post("getDmc");
			// // console.log(res.lo);
			// this.options = res.ld;
			// console.log("mc:", this.options);


		},

		methods: {

			addv: async function() {
				console.log("form:", this.form);

				const {
					data: res1
				} = await this.$http.post("addv", this.form);
				console.log("res1:", res1);
				if (res1.flag == "error") {
					this.$message.info("登记失败");
				} else {
					this.$message.success("登记成功");
				}
				this.reload();



			},
			relogin(){
				this.$router.push({
					path: "/login"
				});
				
			}
		}




	}
</script>

<style lang="less">
	.visitorcontainer{
		height: 100%;
		width: 100%;
		background: url("../assets/f8.png");
		background-size: 600px auto;
		background-position: 80% 50%;
		background-repeat: no-repeat;
	
	}
	.ras {
		margin-left: 0%;
	}

	.adds_form {
		position: absolute;
		top: 5%;
		left: 10%;
		right: 10%;
		bottom: 5%;
	}

	.adds_box {
		position: absolute;
		opacity:0.9;
		top: 200px;
		left: 25%;
		border-radius: 8px;
		background-color: #f4f7e7;
		background-size: cover;
		height: 460px;
		width: 350px;
	}
</style>