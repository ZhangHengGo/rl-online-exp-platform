import { reqFrontGetSeatList, reqFrontReserveSeat, reqFrontLeaveSeat } from '@/api'



const state = {
    front_seat_list: [], // 座位列表
}

const mutations = {
    // 获取座位列表
    FRONTGETSEATLIST(state, front_seat_list) {
        state.front_seat_list = front_seat_list
    },
    // 获取预约信息
    FRONTGETRESERVEINFO(state, front_reserve_info) {
        state.front_reserve_info = front_reserve_info
    },
}

const actions = {
    // 获取座位列表
    async frontGetSeatList({ commit }, seat = {}) {
        let result = await reqFrontGetSeatList(seat)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('FRONTGETSEATLIST', result.data)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 预约座位
    async frontReserveSeat({ commit }, info) {//eslint-disable-line no-unused-vars
        let result = await reqFrontReserveSeat(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 离开座位
    async frontLeaveSeat({ commit }, info) {//eslint-disable-line no-unused-vars
        let result = await reqFrontLeaveSeat(info)
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
    front_seat_list(state) {
        return state.front_seat_list || []
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}