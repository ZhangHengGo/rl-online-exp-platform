// 当前整个模块: API进行统一管理
import axios from './axios'

// 用户登录
export const reqUserLogin = (data) => axios({
    url: '/user/login',
    method: 'post',
    headers: {
        'Content-Type': 'application/json'
    },
    data: JSON.stringify(data)
});






// 获取用户基本信息
export const reqGetUserInfo = ({ user_id }) => axios({ url: `/user/getuserinfo/${user_id}`, method: 'get' })
// 修改密码
export const reqUpdatePwd = (data) => axios({ url: '/user/updatepwd', data, method: 'post' })
// 设置邮箱
export const reqSetEmail = (data) => axios({ url: '/user/setemail', data, method: 'post' })
// 发送问题反馈
export const reqSendFeedback = (data) => axios({ url: '/user/sendfeedback', data, method: 'post' })


// 后台管理
// 添加用户
export const reqAddUser = (data) => axios({ url: '/back/user/adduser', data, method: 'post' })

// 获取用户列表
export const reqGetUserList = (data) => axios({ url: '/back/user/getuserlist', data, method: 'post' })

// 修改用户信息
export const reqBackUpdateUserInfo = (data) => axios({ url: '/back/user/updateuserinfo', data, method: 'post' })

// 封禁用户
export const reqDelUser = ({ user_id }) => axios({ url: `/back/user/deluser/${user_id}`, method: 'get' })

// 添加座位
export const reqAddSeat = (data) => axios({ url: '/back/seat/addseat', data, method: 'post' })

// 获取座位列表
export const reqGetSeatList = (data) => axios({ url: '/back/seat/getseatlist', data, method: 'post' })

// 修改座位信息
export const reqBackUpdateSeatInfo = (data) => axios({ url: '/back/seat/updateseatinfo', data, method: 'post' })

// 移除座位
export const reqDelSeat = ({ seat_id }) => axios({ url: `/back/seat/delseat/${seat_id}`, method: 'get' })

// 发布通知
export const reqAddNotice = (data) => axios({ url: '/back/notice/addnotice', data, method: 'post' })

// 获取通知列表
export const reqGetNoticeList = (data) => axios({ url: '/back/notice/getnoticelist', data, method: 'post' })

// 删除通知
export const reqDelNotice = ({ notice_id }) => axios({ url: `/back/notice/delnotice/${notice_id}`, method: 'get' })

// 获取预约信息列表
export const reqGetReserveList = (data) => axios({ url: '/back/reserve/getreservelist', data, method: 'post' })

// 根据id删除超时的预约信息
export const reqDelReserve = ({ reserve_id, seat_id }) => axios({ url: `/back/reserve/delreserve/${reserve_id}/${seat_id}`, method: 'get' })

// 清空超时记录
export const reqClearTimeoutReserve = () => axios({ url: '/back/reserve/delallreserve', method: 'get' })







// 前台显示
// 获取通知列表
export const reqFrontGetNoticeList = ({ user_id }) => axios({ url: `/front/notice/getnoticelist/${user_id}`, method: 'get' })

// 设置阅读状态为已读
export const reqFrontSetRead = ({ user_id, notice_id }) => axios({ url: `/front/notice/set_read/${user_id}/${notice_id}`, method: 'get' })

// 设置该用户的所有通知为已读
export const reqFrontAllSetRead = ({ user_id }) => axios({ url: `/front/notice/all_set_read/${user_id}`, method: 'get' })

// 获取座位列表
export const reqFrontGetSeatList = ({ seat_floor }) => axios({ url: `/front/seat/getseatlist/${seat_floor}`, method: 'get' })

// 预约座位
export const reqFrontReserveSeat = (data) => axios({ url: '/front/reserve/reserveseat', data, method: 'post' })

// 获取预约信息
export const reqFrontGetReserveInfo = ({ user_id }) => axios({ url: `/front/reserve/getreserveinfo/${user_id}`, method: 'get' })

// 离开座位
export const reqFrontLeaveSeat = ({ user_id, seat_id }) => axios({ url: `/front/reserve/leaveseat/${user_id}/${seat_id}`, method: 'get' })
//添加实验
export const reqAddTest = (data) => axios({ url: '/front/test/addtest', data, method: 'post' })
// 获取实验列表
export const reqGetTestList = (data) => axios({ url: '/front/test/gettestlist', data, method: 'post' })
// 修改实验信息
export const reqUpdateTestInfo = (data) => axios({ url: '/front/test/updatetestinfo', data, method: 'post' })







export const reqGetAlgDatas = (algId) => axios({
    url: `/chart/getalgdatas/${algId}`,
    method: 'get'
})

export const reqGetExpsData = ({ expIdList, algDataId }) => axios({
    url: `/chart/getexpsdata/${expIdList}/${algDataId}`,
    method: 'get'
})


export const reqGetExpList = ({ expId, algId }) => axios({
    url: `/chart/getexplist/${expId}/${algId}`,
    method: 'get'
})


export const reqGetExpDatas = (data) => axios({
    url: '/chart/getexpdatas',
    method: 'post',
    headers: {
        'Content-Type': 'application/json'
    },
    data: JSON.stringify(data)
});

export const reqGetContainerList = () => axios({
    url: `/alg/getcontainerlist`,
    method: 'get'
})

export const getExpEpisodeData = ({expId, episodeId}) => axios({
    url: `/chart/getexpepisodedata/${expId}/${episodeId}`,
    method: 'get'
})



export const reqGetEchartsList = ({expId, episodeId}) => axios({
    url: `/chart/test/${expId}/${episodeId}`,
    method: 'get'
})