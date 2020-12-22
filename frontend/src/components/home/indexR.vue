<template>
	<div class="wen">
	<el-form class="fmo">
				<div>欢迎{{username}}！</div>
				请输入今日体温:
				<el-form class="fmi">
					<el-input size="medium" v-model="temperature"></el-input>
					<el-button size="medium" @click="saveTemperature()">保 存</el-button>
				</el-form>
	</el-form>




	</div>
</template>

<script>
	export default {
		inject: ['reload'],
		data() {
			return {
				username: '',
				temperature: '',
				form: {
					name:'',
					temperature: '',
				},
			}
		},
		created: async function() {
			console.log("indexR created");
			var user = JSON.parse(window.sessionStorage.getItem('user'))
			this.username = user.username
			this.form.name = this.username;
			console.log("f:",this.form);
			const {
				data: res
			} = await this.$http.post("getTemperature", this.form);
			console.log("res:",res);
			this.temperature=res;


		},

		methods: {

			saveTemperature: async function() {
				this.form.name = this.username;
				this.form.temperature = this.temperature;
				console.log("form:", this.form);

				const {
					data: res1
				} = await this.$http.post("updateRtemperature", this.form);
				console.log("res1:", res1);
				if (res1 == "error") {
					this.$message.info("保存失败");
				} else {
					this.$message.success("保存成功");
				}
				this.dialogVisible = false;
				this.reload();



			},
		}





	}
</script>

<style lang="less" scoped="">
	.wen {
		font-size: 20px;
		line-height: 40px;
	}
	.fmi{
				position: absolute;
		top: 100px;
		left: 43%;
		width: 80px;
		line-height: 50px;
	}
	.fmo{
		position: absolute;
		top: 200px;
		left: 35%;
		border-radius: 8px;
		background-color: #f4f7e750;
		background-size: cover;
		height: 250px;
		width: 600px;
	}
</style>
