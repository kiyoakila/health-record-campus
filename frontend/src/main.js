import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
//导入axios
import axios from 'axios'
import moment from 'moment'

// 设置本地时区
moment.locale('zh-cn')
Vue.prototype.$moment = moment

//挂载axios
Vue.prototype.$http = axios
//设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"



Vue.config.productionTip = false

new Vue({
	router,
	render: h => h(App)
}).$mount('#app')

router.beforeEach((to, from, next) => {
	window.document.title = to.meta.title == undefined ? '社区管理系统' : to.meta.title
	if (to.meta.requireAuth) {
		let token = Cookies.get('access_token');
		let anonymous = Cookies.get('user_name');
		if (token) {

			next({
				path: '/login',
				query: {
					redirect: to.fullPath
				}
			})

		}
	}
	next();
})
