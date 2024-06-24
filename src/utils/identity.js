import { expire, setExpire, getExpire } from './storage'

/**
 * 存储identity到本地, 有效期为3天
 * @params String {value} 值
 */
export const setIdentity = (identity) => {
    setExpire('LIBRARY-IDENTITY', identity, expire * 3)
}

/**
 * 获取本地存储的identity, 不存在则返回null
 * @returns String {value} 值
 */
export const getIdentity = () => {
    return getExpire('LIBRARY-IDENTITY')
}

/**
 * 清除本地存储的identity
 */
export const removeIdentity = () => {
    localStorage.removeItem('LIBRARY-IDENTITY')
}