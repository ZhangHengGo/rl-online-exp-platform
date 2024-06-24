import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

// 引入小仓库
import login from './login'
// 后台管理 vuex使用的注释见./back/user
import backuser from './back/user'
import backseat from './back/seat'
import backnotice from './back/notice'
import backreserve from './back/reserve'
// 前台显示
import frontnotice from './front/notice'
import frontseat from './front/seat'
import fronttest from './front/test'

import frontecharts from './front/echarts'
import frontdetail from './front/detail'
// 
export default new Vuex.Store({
    modules: {
        login,
        // 后台管理
        backuser,
        backseat,
        backnotice,
        backreserve,
        // 前台显示
        frontnotice,
        frontseat,
        fronttest,
        frontecharts,
        frontdetail,
    }
})