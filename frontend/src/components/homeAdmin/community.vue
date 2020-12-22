<template>
	<div>
		<div class="tt">
			<div>今日居民申请外出人次：{{n1}}/{{n2}} </div>
			<div class="tt2">今日访客进入人次：{{n3}}</div>
		</div>
		<el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" height="380"
		 style="width: 100%;margin-top: 20px" ref="multipleTable">
			<el-table-column label="居民姓名" prop="name">
			</el-table-column>
			<el-table-column label="性别" prop="sex">
			</el-table-column>
			<el-table-column label="手机号码" prop="phone">
			</el-table-column>
			<el-table-column label="家庭住址" prop="address">
			</el-table-column>
			<el-table-column label="今日体温" prop="temperature">
			</el-table-column>
		</el-table>

	</div>

</template>


<script>
	export default {
		inject: ['reload'],
		data() {
			return {
				tableData: [{}],
				search: '',
				n1: '',
				n2: '',
				n3: '',
			}
		},
		created: async function() {
			console.log("community created");
			const {
				data: res
			} = await this.$http.post("getCommunity");
			console.log(res)
			this.tableData = res.lr;
			console.log("table:", this.tableData)
			this.n1=res.n1;
			this.n2=res.n2;
			this.n3=res.n3;



		},
		methods: {},
	}
</script>

<style lang="less" scoped>
	.tt{
		// height: 160px;
	
		height: 200px;
		width: 100%;
		background: url("../../assets/f6.png");
		background-size: 180px auto;
		background-position: 30% 100%;
		background-repeat: no-repeat;
	
	}
	.tt2{
		position: flex;
		margin-top: -130px;
	}
</style>
