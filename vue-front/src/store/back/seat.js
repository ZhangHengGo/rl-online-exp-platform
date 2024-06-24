import { reqAddSeat, reqGetSeatList, reqBackUpdateSeatInfo, reqDelSeat } from '@/api'



const state = {
    seatlist: [], // 座位列表
    seat_count: 0, // 座位数量
}

const mutations = {
    // 获取座位列表
    GETSEATLIST(state, result) {
        state.seatlist = result.data
        state.seat_count = result.seat_count
    },
}

const actions = {
    // 添加座位
    async addSeat({ commit }, seat) {//eslint-disable-line no-unused-vars
        let result = await reqAddSeat(seat)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 获取座位列表
    async getSeatList({ commit }, info) {
        let result = await reqGetSeatList(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('GETSEATLIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 修改座位信息
    async backUpdateSeatInfo({ commit }, seat) {//eslint-disable-line no-unused-vars
        let result = await reqBackUpdateSeatInfo(seat)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 移除座位
    async delSeat({ commit }, seat = {}) {//eslint-disable-line no-unused-vars
        let result = await reqDelSeat(seat)
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
    seatlist(state) {
        return state.seatlist || []
    },
    seat_count(state) {
        return state.seat_count || 0
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}