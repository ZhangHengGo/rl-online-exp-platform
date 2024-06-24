// 获取当前时间

function getCurTime(date = '') {
    const time = new Date(date)
    let year = time.getFullYear()
    let month = (time.getMonth() + 1) < 10 ? `0${time.getMonth() + 1}` : `${time.getMonth() + 1}`
    let day = (time.getDate()) < 10 ? `0${time.getDate()}` : `${time.getDate()}`
    let hours = (time.getHours()) < 10 ? `0${time.getHours()}` : `${time.getHours()}`
    let minutes = (time.getMinutes()) < 10 ? `0${time.getMinutes()}` : `${time.getMinutes()}`
    let seconds = (time.getSeconds()) < 10 ? `0${time.getSeconds()}` : `${time.getSeconds()}`
    let current_day = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
    return current_day
}

export default getCurTime