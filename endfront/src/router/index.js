// 和计算机网络中的路由不同,此处的路由指配置网页的地址,
// 而计网中指选择源节点到目标节点网络路径
import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '../components/LoginPage.vue'
import BackStagePage from "../components/BackStagePage.vue"
Vue.use(VueRouter)
// routes array, each one is a route.
const routes = [
  {
    path: '/', // relative to the root of the site.
    name: 'login',// name to the route
    component: LoginPage,
  },
  {
    path: '/back-stage',
    name: 'back-stage',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: BackStagePage,
  }
]
// 创建routes
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})
// 调试时关闭,应用时打开:通过重定向到登录页面, 禁止直接跳转到后台.
router.beforeEach((to, from, next) => {
  // 注意:无线循环问题,此处若跳转目的地为主页'/'则不能指定主页.
  if ((to.name == 'back-stage' && sessionStorage.getItem('IsLogged') == 'true') || to.name == 'login') {
    next()
  }
  else next({ name: "login" });
});
export default router
