import Vue from 'vue'
import App from './App.vue'

// 引入element-UI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

// 引入尺寸适配
import 'amfe-flexible'

// 路由
import router from '@/router'
// store仓库
import store from '@/store'

// echart
import * as echarts from 'echarts'

Vue.config.productionTip = false // 在控制台禁用生产提示
Vue.prototype.$echarts = echarts 
new Vue({
  //render: h => h(App),等同于 render:fuction(createElement){return createElelment(App);}
  //这里的render相当于一个渲染函数，createElement就是创建节点，App就是一般情况下vue的html根文件，所以实质上就是将App这个html页面进行了渲染
  render: h => h(App),
  // 注册路由 通过注入路由器（router里面创建的实例），我们可以在任何组件内通过this.$router访问路由器，或者通过this.$routes访问当前路由
  router,
  // 注册仓库 之后我们可以在任何组件内通过this.$store property访问store 比如this.$store.commit('mutation里面定义的修改状态的方法')
  store,
  beforeCreate() {
    // 注册全局事件总线
    Vue.prototype.$bus = this
  },
}).$mount('#app')
