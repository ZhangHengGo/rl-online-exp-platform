import { expire, setExpire, getExpire } from './storage'

/**
 * 存储user_id到本地, 有效期为3天
 * @params Number {value} 值
 */
export const setUserId = (user_id) => {
    setExpire('LIBRARY-USERID', user_id, expire * 3)
}

/**
 * 获取本地存储的user_id, 不存在则返回null
 * @returns Number {value} 值
 */
export const getUserId = () => {
    return getExpire('LIBRARY-USERID')
}

/**
 * 清除本地存储的user_id
 */
export const removeUserId = () => {
    localStorage.removeItem('LIBRARY-USERID')
}