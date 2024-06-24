import { reqGetEchartsList } from '@/api'


//state对象，存储组件的状态
const state = {
    echarts_list: [], // 用户列表
    // user_count: 0, // 用户数量
}
//唯一可以修改组件状态state值的方法，同步阻塞
const mutations = {
    // 获取用户列表 state为设置的state对象，result为传进来进行更新的数据
    GETECHARTSLIST(state, result) {
        state.echarts_list = result.data
        // state.user_count = result.user_count
    },
}

const actions = {
   
    // 获取echarts列表
    async getEchartsList({ commit }, info) {
        // console.log(JSON.stringify(info))
        // let info1 = JSON.stringify(info)
        let result = await reqGetEchartsList(info)
        result = result.data
        console.log(result)
        // commit('GETECHARTSLIST', result)
        if (result.status === 0) {
            commit('GETECHARTSLIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
   
}
//取值的方法 通过该方法获取组件的状态
const getters = {
    echarts_list(state) {
        return state.echarts_list || ['娃娃']
        // return ['hhhhhh']
    },
    // user_count(state) {
    //     return state.user_count || 0
    // },
}

export default {
    state,
    mutations,
    actions,
    getters
}