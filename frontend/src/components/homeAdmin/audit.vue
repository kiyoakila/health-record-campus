<template>
	<div>
		<el-table :data="tableData" height="380"
		 style="width: 100%;margin-top: 20px" ref="multipleTable">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column label="住户名" prop="username">
			</el-table-column>
			<el-table-column label="申请外出时间" prop="out_time">
			</el-table-column>
			<el-table-column label="申请回来时间" prop="in_time">
			</el-table-column>
			<el-table-column label="原因" prop="reason">
			</el-table-column>
			<el-table-column label="体温" prop="temperature">
			</el-table-column>

		</el-table>
		<el-button size="medium" @click="Confirmed()">同 意</el-button>
		<el-button size="medium" type="info" @click="Disagree()">拒 绝</el-button>
	</div>

</template>


<script>
	export default {
		inject:['reload'],
		data() {
			return {
				tableData: [{}],
			}
		},
		created:async function(){
				console.log("confirm created");
				const {
					data: res
				} = await this.$http.post("audit");
				console.log(res)
				this.tableData=res.la;
				console.log("table:",this.tableData)
				
				
				

			
		},
		methods: {
			
				Confirmed:async function(){			
				var data1 = this.$refs.multipleTable.selection;
				console.log(data1[0]);

				const {
					data: res1
				} = await this.$http.post("upaa",data1[0]);
				console.log("res1:",res1.flag);

				if(res1.flag == "error"){
					this.$message.info("审核失败");
				}
				else{
					this.$message.success("审核成功");
				}
				this.$refs.multipleTable.clearSelection();
				this.reload();
				

				
			},
			Disagree:async function(){
				var data1 = this.$refs.multipleTable.selection;
				console.log(data1[0]);
			
				const {
					data: res1
				} = await this.$http.post("upad",data1[0]);
				console.log("res1:",res1.flag);
			
				if(res1.flag == "error"){
					this.$message.info("审核失败");
				}
				else{
					this.$message.success("审核成功");
				}
				this.$refs.multipleTable.clearSelection();
				this.reload();
				
			
				
			},

		},
	}
</script>

<style lang="less" scoped>
</style>


