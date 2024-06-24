// 重写Storage的set和get方法，使得本地存储有时效

// 有效期：1天
export const expire = 1000 * 60 * 60 * 24

/**
 * 将数据有期限的保存到本地
 * @params String {key} 键
 * @params String {value} 值
 * @params Number {expire} 有效期
 */
// Storage包括localStorage和sessionStorage
// 通过这种方式，setExpire函数不仅在Storage的所有实例上可用，同时也可以在其他模块中直接通过导入来使用
export const setExpire = Storage.prototype.setExpire = (key, value, expire) => {
    let obj = {
        data: value,
        time: Date.now(),
        expire
    }
    localStorage.setItem(key, JSON.stringify(obj))
}

/**
 * 获取本地数据, 如果过期或不存在, 则返回null
 * @params String {key} 键
 * @returns String {value} 值
 */
export const getExpire = Storage.prototype.getExpire = key => {
    let val = localStorage.getItem(key)
    if (!val) return val
    val = JSON.parse(val)
    if (Date.now() - val.time > val.expire) {
        localStorage.removeItem(key)
        return null
    }
    return val.data
}