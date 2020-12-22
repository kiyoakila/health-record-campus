<template>
	<div>
		<el-table :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" height="380"
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

		</el-table>
		<el-button size="medium" @click="Confirmed()">确认返回</el-button>
		<el-button size="medium" type="info" @click="Clear()">取消选择</el-button>
	</div>

</template>


<script>
	export default {
		inject:['reload'],
		data() {
			return {
				tableData: [{}],
				search: '',
			}
		},
		created:async function(){
				console.log("rin created");
				const {
					data: res
				} = await this.$http.post("getApplicationC");
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
				} = await this.$http.post("upac",data1[0]);
				console.log("res1:",res1);

				if(res1 == "error"){
					this.$message.info("确认失败");
				}
				else{
					this.$message.success("确认回家成功");
				}
				this.$refs.multipleTable.clearSelection();
				this.reload();
				

				
			},
			Clear(){
				this.$refs.multipleTable.clearSelection();
			}
		},
	}
</script>

<style lang="less" scoped>
</style>


