import { reqAddNotice, reqGetNoticeList, reqDelNotice } from '@/api'



const state = {
    noticelist: [], // 通知列表
    notice_count: 0, // 通知数量
}

const mutations = {
    // 获取通知列表
    GETNOTICELIST(state, result) {
        state.noticelist = result.data
        state.notice_count = result.notice_count
    },
}

const actions = {
    // 发布通知
    async addNotice({ commit }, notice) {//eslint-disable-line no-unused-vars
        let result = await reqAddNotice(notice)
        result = result.data
        console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 获取通知列表
    async getNoticeList({ commit }, info) {
        let result = await reqGetNoticeList(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('GETNOTICELIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 删除通知
    async delNotice({ commit }, notice = {}) {//eslint-disable-line no-unused-vars
        let result = await reqDelNotice(notice)
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
    noticelist(state) {
        return state.noticelist || []
    },
    notice_count(state) {
        return state.notice_count || 0
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}