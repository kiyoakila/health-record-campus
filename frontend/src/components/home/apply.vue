<template>
	<div class="applycontainer">
		<div class="add_box">
			<el-form :model="form" label-position="left" label-width="80px" class="add_form">
				<div class="block">

						<span class="demonstration">外出时间</span>
						<el-date-picker v-model="value2" type="datetimerange" :picker-options="pickerOptions" range-separator="至"
						start-placeholder="开始日期" end-placeholder="结束日期" align="right">
						</el-date-picker>
					
				</div>
				<el-form-item label="外出原因">
					<el-input v-model="form.reason" placeholder="请输入外出原因"></el-input>
				</el-form-item>
				<el-form-item label="体温">
					<el-input v-model="form.temperature" placeholder="请输入体温"></el-input>
				</el-form-item>
				<el-form-item class="btns">
					<el-button size="medium" @click="adds()">添加</el-button>
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
				pickerOptions: {
				          shortcuts: [{
				            text: '最近一周',
				            onClick(picker) {
				              const end = new Date();
				              const start = new Date();
				              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
				              picker.$emit('pick', [start, end]);
				            }
				          }, {
				            text: '最近一个月',
				            onClick(picker) {
				              const end = new Date();
				              const start = new Date();
				              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
				              picker.$emit('pick', [start, end]);
				            }
				          }, {
				            text: '最近三个月',
				            onClick(picker) {
				              const end = new Date();
				              const start = new Date();
				              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
				              picker.$emit('pick', [start, end]);
				            }
				          }]
				        },
				        value2: '',
				options: [{}],
				SinfoData: [{}],
				form: {
					reason: '',
					temperature: '',
					in_time: '',
					out_time:'',
					username:'',
					password:'',


				}
			}
		},
		created: async function() {
			console.log("add_a created");
			var user = JSON.parse(window.sessionStorage.getItem('user'))
			this.form.username=user.username;
			this.form.password=user.password;
			console.log(this.form);
			

		},

		methods: {

			adds: async function() {
				console.log(this.value2)
				
				this.form.in_time=this.$moment(this.value2[1]).format("YYYY-MM-DD hh:mm:ss");
				this.form.out_time=this.$moment(this.value2[0]).format("YYYY-MM-DD hh:mm:ss");
				console.log("form:", this.form);

				const {
					data: res1
				} = await this.$http.post("addApplication", this.form);
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
  .applycontainer{
		height: 100%;
		width: 100%;
		background: url("../../assets/f7.png");
		background-size: 800px auto;
		background-position: 80% 100%;
		background-repeat: no-repeat;
	
	}
	.ras {
		margin-left: -50%;
	}


	.add_form {
		position: absolute;
		top: 5%;
		left: 50px;

	}
 .demonstration{
	 font-size: 14px;
	 padding-right: 24px;

 }

	.block{
	 padding-bottom: 0px;		
	}

	.add_box {
		position: absolute;
		top: 200px;
		left: 35%;
		border-radius: 8px;
		background-color: #f4f7e750;
		background-size: cover;
		height: 400px;
		width: 600px;

	}
</style>
