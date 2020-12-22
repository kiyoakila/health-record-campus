<template>
	<div class="rinfocontainer">
		<div class="box">
			<tr>
				<td>姓名</td>
				<td>{{SinfoData.name}}</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>{{SinfoData.sex}}</td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td>{{SinfoData.idcard}}</td>
			</tr>
			<tr>
				<td>手机</td>
				<td>{{SinfoData.phone}}</td>
			</tr>
			<tr>
				<td>地址</td>
				<td>{{SinfoData.address}}</td>
			</tr>
		</div>
		<div class="mes">
			<el-button size="medium" @click="dialogVisible = true" class="but">修改信息</el-button>
			<el-dialog :visible.sync="dialogVisible" width="330px">
				<el-form class="form" :model="form" label-width="70px" label-position="left">
					<div class="tre">
						<tr>
							<td>姓名</td>
							<td>{{SinfoData.name}}</td>
						</tr>
						<tr>
							<td>身份证号</td>
							<td>{{SinfoData.idcard}}</td>
						</tr>
					</div>
					<el-form-item label="性别">
						<el-radio-group v-model="form.sex" class="ras">
							<el-radio label="男"></el-radio>
							<el-radio label="女"></el-radio>
						</el-radio-group>
					</el-form-item>
					<el-form-item label="家庭住址">
						<el-input v-model="form.address"></el-input>
					</el-form-item>
					<el-form-item label="手机号码">
						<el-input v-model="form.phone"></el-input>
					</el-form-item>
				</el-form>
				<span slot="footer" class="dialog-footer">
					<el-button size="medium" type="info" @click="dialogVisible = false">取 消</el-button>
					<el-button size="medium"  @click="updateSinfo()">确 定</el-button>
				</span>
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		inject:['reload'],
		data() {
			return {
				SinfoData: {},
				dialogVisible: false,
				form: {
					idcard: '',
					address: '',
					phone: '',
					sex: '',
					name:''


				}
			}
		},
		created: async function() {
			console.log("rinfo created");
			var user = JSON.parse(window.sessionStorage.getItem('user'))
			const {
				data: res
			} = await this.$http.post("rinfo",user);
			// console.log(res.lo);
			this.SinfoData = res.r;
			console.log("sd:", this.SinfoData);
			this.form = this.SinfoData;
			




		},
		methods: {

			updateSinfo: async function() {
				console.log("form:",this.form);

				const {
					data: res1
				} = await this.$http.post("updateRinfo", this.form);
				console.log("res1:", res1);
				if (res1 == "error") {
					this.$message.info("修改失败");
				} else {
					this.$message.success("修改成功");
				}
				this.dialogVisible = false;
				this.reload();



			},
		}




	}
</script>

<style lang="less" scoped>
	.rinfocontainer{
		height: 100%;
		width: 100%;
		background: url("../../assets/f4.jpg");
		background-size: 400px auto;
		background-position: 20% 0%;
		background-repeat: no-repeat;
	
	}
	.box {
		margin-top: 100px;
		margin-left: 50%;
	}
	.but{
		margin-left: 10%;
	}
	tr {
		line-height: 70px;
	}

	.form {
		margin-top: -30px;
		margin-bottom: -100px;

	}

	.tre {
		position: relative;
		padding-left: 0px;
	}

	.ras {
		margin-left: -60%;
	}
</style>
