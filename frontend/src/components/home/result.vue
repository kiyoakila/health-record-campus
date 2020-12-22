<template>
	<div>
		<div class="course">
			<el-table :data="tableData1" style="width: 100%;margin-top: 20px" ref="multipleTable">
				<el-table-column label="申请外出时间" prop="out_time">
				</el-table-column>
				<el-table-column label="申请回来时间" prop="in_time">
				</el-table-column>
				<el-table-column label="原因" prop="reason">
				</el-table-column>
				<el-table-column label="审核状态" prop="status">
				</el-table-column>


			</el-table>

		</div>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData1: [{}],

			}
		},
		created: async function() {
			console.log("kebiao created");
			var user = JSON.parse(window.sessionStorage.getItem('user'))
			const {
				data: res
			} = await this.$http.post("getApplication", user);
			// console.log(res.lo);
			this.tableData1 = res.la;
			console.log("table:", this.tableData1)
			
		
			var n = this.tableData1.length;
			console.log("n:",n);
			var i = 0;
			var ts;
			for (i; i < n; i++) {
			 	ts=this.tableData1[i].statu;
				console.log("ts:", ts);
				if(ts==0){
					this.tableData1[i].status="审核中"
				}
				if(ts==1){
					this.tableData1[i].status="已通过"
				}
				if(ts==2){
					this.tableData1[i].status="未通过"
				}
				
				 
			}
			
			console.log("table:", this.tableData1)

		



	},
	methods: {

	}
	}
</script>

<style lang="less">
	.el-table__header tr,
	.el-table__header th {
		padding: 0;
		height: 30px;
		line-height: 30px;
	}

	.el-table__body tr,
	.el-table__body td {
		padding: 0;
		height: 30px;
		line-height: 30px;
	}
</style>
