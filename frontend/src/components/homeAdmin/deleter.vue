<template>
	<div>
		<el-table :data="tableData" height="380"
		 style="width: 100%;margin-top: 20px" ref="multipleTable">
			<el-table-column type="selection" width="55">
			</el-table-column>

			<el-table-column label="姓名" prop="name">
			</el-table-column>
			<el-table-column label="性别" prop="sex">
			</el-table-column>
			<el-table-column label="身份证号" prop="idcard">
			</el-table-column>
			<el-table-column label="地址" prop="address">
			</el-table-column>
			<el-table-column label="手机号码" prop="phone">
			</el-table-column>

		</el-table>
		<el-button size="medium" @click="QuitCourse()">删 除</el-button>
		<el-button size="medium" type="info" @click="Clear()">取消选择</el-button>
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
				console.log("dr created");
				const {
					data: res
				} = await this.$http.post("lr");
				console.log(res)
				this.tableData=res.lr;
				console.log("table:",this.tableData)
				

			
		},
		methods: {
			
				QuitCourse:async function(){			
				var data1 = this.$refs.multipleTable.selection;
				console.log(data1[0]);

				const {
					data: res1
				} = await this.$http.post("deleter",data1[0]);
				console.log("res1:",res1);

				if(res1 == "error"){
					this.$message.info("删除失败");
				}
				else{
					this.$message.success("删除成功");
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

