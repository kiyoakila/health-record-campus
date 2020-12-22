<template>
	<div>
		<el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" height="380"
			 style="width: 100%;margin-top: 20px" ref="multipleTable">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column label="访客名" prop="name">
			</el-table-column>
			<el-table-column label="性别" prop="sex">
			</el-table-column>
			<el-table-column label="手机" prop="phone">
			</el-table-column>
			<el-table-column label="身份证" prop="idcard">
			</el-table-column>
			<el-table-column label="进入时间" prop="in_time">
			</el-table-column>
			<el-table-column label="原因" prop="reason">
			</el-table-column>
			<el-table-column label="体温" prop="temperature">
			</el-table-column>
	

		</el-table>
		<el-button size="medium" @click="Confirmed()">确认离开</el-button>
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
				} = await this.$http.post("getVisitorC");
				console.log(res)
				this.tableData=res.lv;
				console.log("table:",this.tableData)
				

			
		},
		methods: {
			
				Confirmed:async function(){			
				var data1 = this.$refs.multipleTable.selection;
				console.log(data1[0]);

				const {
					data: res1
				} = await this.$http.post("upvc",data1[0]);
				console.log("res1:",res1);

				if(res1.flag == "error"){
					this.$message.info("确认失败");
				}
				else{
					this.$message.success("确认离开成功");
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



