import { reqFrontGetNoticeList, reqFrontSetRead, reqFrontAllSetRead } from '@/api'



const state = {
    front_notice_list: [], // 通知列表
}

const mutations = {
    // 获取通知列表
    FRONTGETNOTICELIST(state, result) {
        state.front_notice_list = result.data
    },
}

const actions = {
    // 获取通知列表
    async frontGetNoticeList({ commit }, user = {}) {
        let result = await reqFrontGetNoticeList(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('FRONTGETNOTICELIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 设置通知状态为已读
    async frontSetRead({ commit }, info = {}) {//eslint-disable-line no-unused-vars
        let result = await reqFrontSetRead(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 设置该用户的所有通知为已读
    async frontAllSetRead({ commit }, user = {}) {//eslint-disable-line no-unused-vars
        let result = await reqFrontAllSetRead(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
}

const getters = {
    front_notice_list(state) {
        return state.front_notice_list || []
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}