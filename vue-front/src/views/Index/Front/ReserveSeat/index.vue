<template>
    <div class="reserve-seat maxWH clearfix">
        <!-- 头部 -->
        <div class="header flex">
            <span class="title"></span>
        </div>
        <!-- 主体 -->
        <main class="main">
            <!-- 已经有预约信息了 -->
            <div class="exist-reserve" v-show="(Object.keys(front_reserve_info).length > 0)">
                <el-button type="text" @click.native.prevent.stop="goReserveInfo">查看信息</el-button>
            </div>
            <!-- 添加 -->
        <el-button type="primary" @click.native.prevent.stop="userAddDialog = true"
            style="margin: 15px 0;">添加用户</el-button>
            <!-- 搜索 -->
            
            <div class="table">
                <el-table :data="front_seat_list" height="350" style="width: 100%">
                    <el-table-column prop="seat_floor" label="座位楼层">
                    </el-table-column>
                    <el-table-column prop="seat_no" label="座位编号">
                    </el-table-column>
                    <el-table-column prop="seat_status" label="座位状态">
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.seat_status === '空闲中' ? 'info' : 'warning'">{{
                                    scope.row.seat_status
                            }}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" header-align="center">
                        <template slot-scope="scope">
                            <div class="handler flex-center">
                                <el-button type="info"
                                    :disabled="scope.row.seat_status === '空闲中' && (Object.keys(front_reserve_info).length < 1) ? false : true"
                                    @click.native.prevent.stop="openReserveSeat(scope.$index, front_seat_list)">
                                    预约
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <!-- 预约的遮罩页 -->
            <el-dialog title="预约座位" :visible.sync="reserveSeatdDialog">
                <el-form ref="frontReserveSeatForm" :model="reserveSeatForm" label-width="110px" :rules="rules"
                    hide-required-asterisk>
                    <el-form-item label="座位楼层" prop="seat_floor">
                        <el-input disabled v-model.trim="reserveSeatForm.seat_floor" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="座位编号" prop="seat_no">
                        <el-input disabled v-model.trim="reserveSeatForm.seat_no" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="预约结束时间" prop="end_time">
                        <el-time-picker placeholder="选择时间" v-model="reserveSeatForm.end_time"
                            style="width: 100%;"></el-time-picker>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer" align="center">
                    <el-button type="primary" @click.native.prevent.stop="reserveSeat('frontReserveSeatForm')">确
                        定</el-button>
                    <el-button @click.native.prevent.stop="resetForm('frontReserveSeatForm')">重 置</el-button>
                </div>
            </el-dialog>
        </main>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import getCurTime from '@/utils/getCurTime'
export default {
    name: 'ReserveSeat',
    data() {
        const validateEndTime = (rule, value, callback) => {
            const reg = /^(\d{4})-(\d{2})-(\d{2})[\s](\d{2}):(\d{2}):(\d{2})$/
            value = this.getDateStr(value) // 将时间转化为字符串
            value = getCurTime(value) // 格式化
            // 获取当前时间加上15分钟的 时间戳
            let now = new Date().getTime() + 15 * 60 * 1000
            // 获取选择时间的小时
            let hour = new Date(value)
            hour = (hour.getHours()) < 10 ? `0${hour.getHours()}` : `${hour.getHours()}`
            hour = Number.parseInt(hour)
            if (value.trim().length <= 0) { // 不能为空
                callback(new Error('请选择结束时间'))
            } else if (!reg.test(value)) { // 控制格式: yyyy-mm-dd hh:mm:ss
                callback(new Error('时间格式不对'))
            } else if (isNaN(hour)) {
                callback(new Error('时间格式不对'))
            } else if (hour < 9) { // 不能小于9点
                callback(new Error('不能小于早上9点'))
            } else if (hour >= 22) { // 不能大于22点
                callback(new Error('不能大于晚上22点'))
            } else if (new Date(value).getTime() < now) { // 不能少于15分钟
                callback(new Error('预约时间应不少于15分钟'))
            } else {
                callback()
            }
        }
        return {
            // 楼层的选择列表
            seat_floor: [
                { label: '全部', value: '0' },
                { label: '1楼', value: '1' },
                { label: '2楼', value: '2' },
                { label: '3楼', value: '3' },
                { label: '4楼', value: '4' },
                { label: '5楼', value: '5' },
            ],
            // 搜索座位列表的表单
            searchForm: {
                seat_floor: '',
            },
            // 预约座位的遮罩页
            reserveSeatdDialog: false,
            // 验证规则
            rules: {
                end_time: [{ required: true, trigger: ['blur', 'change'], validator: validateEndTime }],
            },
            
            // 预约座位的表单
            reserveSeatForm: {
                seat_id: 0,
                seat_floor: '', // 座位楼层
                seat_no: '', // 座位编号
                end_time: '', // 预约结束时间
            },
        }
    },
    computed: {
        ...mapGetters(['front_seat_list', 'userinfo', 'front_reserve_info'])
    },
    mounted() {
        this.searchForm.seat_floor = this.seat_floor[0].value
        // 获取座位列表
        this.getSeatlist()
        // 获取用户信息
        this.getUserInfo()
    },
    methods: {
        // 将 中国标准时间 转化为时间字符串 yyyy-mm-dd hh:mm:ss
        getDateStr(date) {
            let d = new Date(date)
            date = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
            date = date.toString()
            return date
        },
        // 获取座位列表
        async getSeatlist() {
            try {
                let seat_floor = isNaN(Number.parseInt(this.searchForm.seat_floor)) ? 0 : Number.parseInt(this.searchForm.seat_floor)
                await this.$store.dispatch('frontGetSeatList', { seat_floor })
            } catch (e) {
                console.warn(e.message)
            }
        },
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 重置搜索表单
        resetSearchForm(formname) {
            this.resetForm(formname)
            this.searchForm.seat_floor = this.seat_floor[0].value
        },
        // 路由判断
        getFrontPath(path) {
            let href = this.$route.path
            if (href.includes('front')) {
                return path
            } else {
                return 'front/' + path
            }
        },
        // 去哪个页面
        goPath(path) {
            this.$router.push(this.getFrontPath(path))
        },
        // 打开预约座位的遮罩页
        openReserveSeat(index, rows) {
            this.reserveSeatdDialog = true
            const seat = rows[index]
            // console.log(seat)
            this.reserveSeatForm = {
                seat_id: seat.seat_id,
                seat_floor: seat.seat_floor,
                seat_no: seat.seat_no,
                end_time: '',
            }
        },
        // 预约座位
        async reserveSeat(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    try {
                        const info = {
                            user_id: this.userinfo.user_id,
                            seat_id: this.reserveSeatForm.seat_id,
                            end_time: getCurTime(this.getDateStr(this.reserveSeatForm.end_time))
                        }
                        await this.$store.dispatch('frontReserveSeat', JSON.stringify(info))
                            .then(async res => {
                                await this.resetForm(formname)
                                await this.getUserInfo()
                                this.$notify({ title: '成功', type: 'success', message: res })
                                this.reserveSeatdDialog = false
                                this.$bus.$emit('switchCheckNav', '1-2')
                                this.goPath('reserveinfo')
                            }).catch(err => this.$message({ type: 'warning', message: err.message }))
                    } catch (e) {
                        console.warn(e.message)
                    }
                }
            })
        },
        // 去预约查看页
        goReserveInfo() {
            this.$bus.$emit('switchCheckNav', '1-2')
            this.goPath('reserveinfo')
        },
        // 获取用户信息
        async getUserInfo() {
            try {
                await this.$store.dispatch('getUserInfo', { user_id: this.userinfo.user_id })
            } catch (e) {
                console.warn(e.message)
            }
        },
    },
}
</script>

<style lang="less" scoped>
.header {
    width: 100%;
    height: 100px;
    background-color: #324057;
    box-sizing: border-box;
    padding: 50px 20% 10px;
    overflow: hidden;

    .title {
        font-size: 25px;
        color: #fff;
    }
}

.main {
    width: 100%;
    height: calc(100% - 100px);
    box-sizing: border-box;
    padding: 20px 10%;

    .exist-reserve {
        width: 100%;
        box-sizing: border-box;
        padding: 0 20%;
    }

    .search {
        width: 100%;
        height: 70px;
        overflow: hidden;

        .search-form {
            min-width: 400px;
            justify-content: center;
        }
    }

    .table {
        width: 100%;
        box-sizing: border-box;
        padding: 0 20%;
    }
}
</style>