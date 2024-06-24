import { reqAddUser, reqGetUserList, reqBackUpdateUserInfo, reqDelUser } from '@/api'


//state对象，存储组件的状态
const state = {
    userlist: [], // 用户列表
    user_count: 0, // 用户数量
}
//唯一可以修改组件状态state值的方法，同步阻塞
const mutations = {
    // 获取用户列表 state为设置的state对象，result为传进来进行更新的数据
    GETUSERLIST(state, result) {
        state.userlist = result.data
        state.user_count = result.user_count
    },
}

const actions = {
    // 添加用户
    async addUser({ commit }, user) {//eslint-disable-line no-unused-vars
    
        let result = await reqAddUser(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 获取用户列表
    async getUserList({ commit }, info) {
        let result = await reqGetUserList(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('GETUSERLIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 修改用户信息
    async backUpdateUserInfo({ commit }, user) {//eslint-disable-line no-unused-vars
        let result = await reqBackUpdateUserInfo(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 封禁用户
    async delUser({ commit }, user = {}) {//eslint-disable-line no-unused-vars
        let result = await reqDelUser(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
}
//取值的方法 通过该方法获取组件的状态
const getters = {
    userlist(state) {
        return state.userlist || []
    },
    user_count(state) {
        return state.user_count || 0
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}