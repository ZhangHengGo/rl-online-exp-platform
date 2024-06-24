import { reqGetReserveList, reqDelReserve, reqClearTimeoutReserve } from '@/api'



const state = {
    reservelist: [], // 预约信息列表
    reserve_count: 0, // 预约信息数量
}

const mutations = {
    // 获取预约信息列表
    GETRESERVELIST(state, result) {
        state.reservelist = result.data
        state.reserve_count = result.reserve_count
    },
}

const actions = {
    // 获取预约信息列表
    async getReserveList({ commit }, reserve) {
        let result = await reqGetReserveList(reserve)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('GETRESERVELIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 删除超时的预约信息
    async delReserve({ commit }, info = {}) {//eslint-disable-line no-unused-vars
        let result = await reqDelReserve(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 清空超时的预约记录
    async clearTimeoutReserve({commit},_){//eslint-disable-line no-unused-vars
        let result = await reqClearTimeoutReserve()
        result = result.data
        console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        } 
    },
}

const getters = {
    reservelist(state) {
        return state.reservelist || []
    },
    reserve_count(state) {
        return state.reserve_count || 0
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}