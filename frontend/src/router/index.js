import Vue from 'vue'
import VueRouter from 'vue-router'
//引入Login组件
import Login from '../components/login.vue'

//引入住户组件
import Apply from '../components/home/apply.vue'
import Home from '../components/home.vue'
import Result from '../components/home/result.vue'
import Confirm from '../components/home/confirm.vue'
import Rinfo from '../components/home/rinfo.vue'
import Rpwd from '../components/home/rpwd.vue'
import IndexR from '../components/home/indexR.vue'

//引入管理员组件
import HomeAdmin from '../components/homeAdmin.vue'
import Addr from '../components/homeAdmin/addr.vue'
import Deleter from '../components/homeAdmin/deleter.vue'
import Updater from '../components/homeAdmin/updater.vue'
import Updaterpwd from '../components/homeAdmin/updaterpwd.vue'
import Audit from '../components/homeAdmin/audit.vue'
import Community from '../components/homeAdmin/community.vue'
import Adminpwd from '../components/homeAdmin/adminpwd.vue'
import IndexAdmin from '../components/homeAdmin/indexAdmin.vue'

//引入门卫组件
import HomeConcierge from '../components/homeConcierge.vue'
import VisitorOut from '../components/homeConcierge/visitorOut.vue'
import ResidentIn from '../components/homeConcierge/residentIn.vue'
import Cpwd from '../components/homeConcierge/cpwd.vue'
import IndexC from '../components/homeConcierge/indexC.vue'

import Visitor from '../components/visitor.vue'

Vue.use(VueRouter)

const routes = [{
		path: "/",
		redirect: "/login"
	},
	{
		path: "/login",
		component: Login,
		meta: {
			title: '登录页',
			keepAlive: true, // 需要被缓存
		}

	},
	{
		path: "/visitor",
		component: Visitor,
		meta: {
			title: '访客登记',
			keepAlive: true, // 需要被缓存
		}
	
	},

	{
		path: "/home",
		component: Home,
		redirect: "/indexR",
		children: [{
				path: "/indexR",
				component: IndexR
			},
			{
				path: "/apply",
				component: Apply
			},
			{
				path: "/result",
				component: Result
			},
			{
				path: "/confirm",
				component: Confirm
			},
			{
				path: "/rinfo",
				component: Rinfo
			},
			{
				path: "/rpwd",
				component: Rpwd
			},
		]
	},
	{
		path: "/homeConcierge",
		component: HomeConcierge,
		redirect: "/indexC",
		children: [{
				path: "/indexC",
				component: IndexC
			},
			{
				path: "/visitorOut",
				component: VisitorOut
			},
			{
				path: "/residentIn",
				component: ResidentIn
			},
			{
				path: "/cpwd",
				component: Cpwd
			},
			
		],
		meta: {
			title: '社区管理系统',
			keepAlive: true, // 需要被缓存
		}

	},
	{
		path: "/homeAdmin",
		component: HomeAdmin,
		redirect: "/indexAdmin",
		children: [{
				path: "/indexAdmin",
				component: IndexAdmin
			},
			{
				path: "/adminpwd",
				component: Adminpwd
			},
			{
				path: "/addr",
				component: Addr
			},
			{
				path: "/deleter",
				component: Deleter
			},
			{
				path: "/updater",
				component: Updater
			},
			{
				path: "/updaterpwd",
				component: Updaterpwd
			},
			{
				path: "/audit",
				component: Audit
			},
			{
				path: "/community",
				component: Community
			},
		],
		meta: {
			title: '社区管理系统',
			keepAlive: true, // 需要被缓存
		}


	},

]

const router = new VueRouter({
	routes
})

/*
// 出现问题时使用
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
	if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
	return originalPush.call(this, location).catch(err => err)
}





//挂载路由导航守卫
router.beforeEach((to, from, next) => {
	//to 将要访问
	//from 从哪里访问
	//next 接着干 next(url) 重定向到url，空的话继续访问 to 的路径
	if(to.meta.title)
	document.title=to.meta.title;
	if (to.path == '/login') return next();
	if (to.path == '/visitor') return next();
	// 获取user
	const userFlag = window.sessionStorage.getItem("user"); //取出当前用户
	if (!userFlag) return next('/login'); //无值，返回登录页
	next(); //符合要求 放行
}) */

export default router
