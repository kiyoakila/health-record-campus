<template>
<div class="container">
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
			<el-form-item label="家庭住址">
				<el-input v-model="form.address" placeholder="1栋2单元502"></el-input>
			</el-form-item>
			<el-form-item label="手机号码">
				<el-input v-model="form.phone" placeholder="请输入11位手机号"></el-input>
			</el-form-item>
			<el-form-item label="今日体温">
				<el-input v-model="form.temperature" placeholder="请输入您的体温"></el-input>
			</el-form-item>
			<el-form-item class="btns">
				<el-button size="medium" @click="adds()">添 加</el-button>
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
					address: '',
					temperature:'',


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

			adds: async function() {
				console.log("form:", this.form);

				const {
					data: res1
				} = await this.$http.post("addr", this.form);
				console.log("res1:", res1);
				if (res1 == "error") {
					this.$message.info("添加失败");
				} else {
					this.$message.success("添加成功");
				}
				this.reload();



			},
		}




	}
</script>

<style lang="less">
	.ras {
		margin-left: 0%;
	}

	.container{
		height: 100%;
		width: 100%;
		background: url("../../assets/f1.png");
		background-size: 600px auto;
		background-position: 90% 95%;
		background-repeat: no-repeat;
	}

	.adds_form {
		position: absolute;
		top: 7%;
		left: 10%;
		right: 10%;
		bottom: 5%;
	}

	.adds_box {
		opacity: 0.95;
		position: absolute;
		top: 200px;
		left: 40%;
		border-radius: 8px;
		background-color: #3f422f;
		background-size: cover;
		height: 400px;
		width: 350px;
	}
</style>
