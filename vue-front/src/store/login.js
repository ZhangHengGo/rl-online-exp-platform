import { reqUserLogin, reqGetUserInfo, reqUpdatePwd, reqSetEmail, reqSendFeedback } from '@/api'
import { setAccount } from '@/utils/account'
import { getIdentity, setIdentity, removeIdentity } from '@/utils/identity'
import { getUserId, setUserId, removeUserId } from '@/utils/user_id'



const state = {
    user_id: getUserId(), // 用户id
    identity: getIdentity(), // 用户身份
    userinfo: {}, // 用户的基本信息
    readlist: [], // 用户的未读通知列表
    front_reserve_info: {}, // 预约信息
    echarts_info: {}, // 图表信息
    get_time_dict: {}, // 预约时间段次数, 用于排序
    get_time_arr: [],
    get_time_val_arr: [], // 预约时间段次数数组的数字
}

const mutations = {
    // 用户登录
    USERLOGIN(state, userinfo) {
        state.user_id = userinfo.user_id
        state.identity = userinfo.identity
    },
    // 获取用户基本信息
    GETUSERINFO(state, result) {
        state.userinfo = result.data
        state.readlist = result.readlist
    },
    // 退出登录
    CLEAR(state, _) {//eslint-disable-line no-unused-vars
        state.userinfo = {}
        state.user_id = 0
        state.identity = ''
        removeUserId()
        removeIdentity()
    },
    // 
    GETTIMEVALUEARR(state, result) {
        state.get_time_dict = result.data
        // 排序
        let keyArr = Object.keys(state.get_time_dict).sort(function (a, b) {
            return state.get_time_dict[b] - state.get_time_dict[a]
        })
        let time_arr = []
        let time_val_arr = []
        for (let key of keyArr) {
            if (key === 'nine_time_count') key = '9点'
            else if (key === 'ten_time_count') key = '10点'
            else if (key === 'eleven_time_count') key = '11点'
            else if (key === 'twelve_time_count') key = '12点'
            else if (key === 'thirteen_time_count') key = '13点'
            else if (key === 'fourteen_time_count') key = '14点'
            else if (key === 'fifteen_time_count') key = '15点'
            else if (key === 'sixteen_time_count') key = '16点'
            else if (key === 'seventeen_time_count') key = '17点'
            else if (key === 'eighteen_time_count') key = '18点'
            else if (key === 'nineteen_time_count') key = '19点'
            else if (key === 'twenty_time_count') key = '20点'
            else if (key === 'twenty_one_time_count') key = '21点'
            time_arr.push(`${key}:${state.get_time_dict[key]}`)
            time_val_arr.push(state.get_time_dict[key])
        }
        state.get_time_arr = time_arr.slice(0, 7)
        state.get_time_val_arr = time_val_arr
    },
}

const actions = {
    // 用户登录
    async userLogin({ commit }, user) {
        let result = await reqUserLogin(user) //发送登录请求
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('USERLOGIN', result.data)
            setUserId(result.data.user_id)
            setIdentity(result.data.identity)
            setAccount(result.data.account)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 退出登录
    userLogout({ commit }, _) {//eslint-disable-line no-unused-vars
        commit('CLEAR')
    },
    // 修改密码
    async updatePwd({ commit }, user) {//eslint-disable-line no-unused-vars
        let result = await reqUpdatePwd(user)
        result = result.data
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 设置邮箱
    async setEmail({ commit }, user) {//eslint-disable-line no-unused-vars
        let result = await reqSetEmail(user)
        result = result.data
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 发送问题反馈
    async sendFeedback({ commit }, info) {//eslint-disable-line no-unused-vars
        let result = await reqSendFeedback(info)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 获取用户信息
    async getUserInfo({ commit }, user = {}) {
        let result = await reqGetUserInfo(user)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            commit('GETUSERINFO', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
}

const getters = {
    userinfo(state) {
        return state.userinfo || {}
    },
    user_id(state) {
        return state.user_id || 0
    },
    identity(state) {
        return state.identity || ''
    },
    readlist(state) {
        return state.readlist || []
    },
    front_reserve_info(state) {
        return state.front_reserve_info || {}
    },
    echarts_info(state) {
        return state.echarts_info || {}
    },
    get_time_arr(state) {
        return state.get_time_arr || []
    },
    get_time_val_arr(state) {
        return state.get_time_val_arr || []
    },
    get_time_dict(state){
        return state.get_time_dict || {}
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}