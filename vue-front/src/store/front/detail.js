import { reqAddTest, reqGetUserList,reqGetTestList, reqUpdateTestInfo, reqDelUser, reqFrontUpdateTestInfo,reqGetTestDetail} from '@/api'


//state对象，存储组件的状态
const state = {
    testdetail: [], // 实验列表
    detail_count: 2, // 实验数量
}
//唯一可以修改组件状态state值的方法，同步阻塞
const mutations = {
    // 获取用户列表 state为设置的state对象，result为传进来进行更新的数据
    GETDETAILLIST(state, result) {
        state.testdetail = result.data
        state.detail_count = result.detail_count
    },
}

const actions = {
    // 添加实验
    async addTest({ commit }, test) {//eslint-disable-line no-unused-vars
        
        let result = await reqAddTest(test)
        result = result.data
        // console.log(result)
        if (result.status === 0) {
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 获取实验列表
    async getDetailList({ commit }, info) {
        // console.log('hhahahahah')
    //    let result = await reqGetTestList(info)
        // let result = await reqGetUserList(info)
        let result = await reqGetTestDetail(info)

        result = result.data
        
        if (result.status === 0) {
            commit('GETDETAILLIST', result)
            return result.msg || 'ok'
        } else {
            return Promise.reject(new Error(result.msg || 'fail'))
        }
    },
    // 修改实验信息
    async updateTestInfo({ commit }, test) {
       
        let result = await reqUpdateTestInfo(test)
        result = result.data
       
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
    testdetail(state) {
        return state.testdetail || []
    },
    detail_count(state) {
        return state.detail_count || 0
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}