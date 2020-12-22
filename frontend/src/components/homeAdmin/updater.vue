<template>
	<div>
	<div>
		<el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" height="380"
		 style="width: 100%;margin-top: 20px" ref="multipleTable">

			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column label="姓名" prop="name">
			</el-table-column>
			<el-table-column label="性别" prop="sex">
			</el-table-column>
			<el-table-column label="身份证号" prop="idcard">
			</el-table-column>
			<el-table-column label="家庭住址" prop="address">
			</el-table-column>
			<el-table-column label="电话" prop="phone">
			</el-table-column>


			<el-table-column align="right">
				<template slot="header" slot-scope="scope">
					<el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
				</template>
			</el-table-column>
		</el-table>
		<el-button size="medium" @click="openUp()">修改信息</el-button>
		<el-button size="medium" type="info" @click="Clear()">取消选择</el-button>
	</div>
	<div class="mes">
		<el-dialog :visible.sync="dialogVisible" width="330px" >
			<el-form class="form" :model="form" label-width="70px" label-position="left">
				<div class="tre">
					<tr>
						<td>身份证号：</td>
						<td>{{form.idcard}}</td>
					</tr>
				</div>
				<el-form-item label="姓名">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
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
				<el-button size="medium" @click="dialogVisible = false">取 消</el-button>
				<el-button size="medium" type="info" @click="updateSinfo()">确 定</el-button>
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
				Sxh:'',
				tableData: [{}],
				SinfoData: [{}],
				search: '',
				dialogVisible: false,
				form: {
				},
			}
		},
		created:async function(){
				console.log("us created");
				const {
					data: res
				} = await this.$http.post("lr");
				// console.log(res.lo);
				this.tableData=res.lr;
				console.log("table:",this.tableData)
				

			
		},
		methods: {
			
			updateSinfo: async function() {
				
				console.log("form:",this.form);
			
				const {
					data: res1
				} = await this.$http.post("upr", this.form);
				console.log("res1:", res1);
				if (res1 == "error") {
					this.$message.info("修改失败");
				} else {
					this.$message.success("修改成功");
				}
				this.dialogVisible = false;
				this.reload();
			
			
			
			},
			openUp(){
				console.log("open up");
				var data1 = this.$refs.multipleTable.selection;
				console.log(data1[0]);
				this.form=data1[0];
				console.log("form:",this.form);
				
				
				this.dialogVisible = true;
				
			},
			Clear(){
				this.$refs.multipleTable.clearSelection();
			}
		},
	}
</script>

<style lang="less" scoped>
	tr {
		line-height: 75px;
		padding-left: 0px;
	}
	
	.form {
		margin-top: -30px;
		margin-bottom: -100px;
	}
	
	.tre {
		padding-left: 0px;
	}
</style>

