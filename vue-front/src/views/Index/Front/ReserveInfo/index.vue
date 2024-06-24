<template>
    <div class="reserve-info maxWH">
        <!-- 头部 -->
        <div class="header flex">
            <span class="title"></span>
            <p class="info flex"></p>
        </div>
        <!-- 主体 -->
        <main class="main">
            <!-- 预约信息 -->
            <el-row class="reserve-detail" v-show="(Object.keys(front_reserve_info).length > 0)">
                <el-col class="reserve-detail-item">{{ reserveInfo.title }}</el-col><br>
                <el-divider></el-divider>
                <el-col class="reserve-detail-item">{{ reserveInfo.floor }}</el-col>
                <el-col class="reserve-detail-item">{{ reserveInfo.seat_no }}</el-col>
                <el-col class="reserve-detail-item">{{ reserveInfo.leave_tiem }}</el-col>
                <div class="leave-seat flex-center">
                    <el-button type="info" @click.native.prevent.stop="leaveSeat">离开座位</el-button>
                </div>
            </el-row>
            <!-- 没有预约信息时的显示 -->
            <div class="no-more maxWH" v-show="(Object.keys(front_reserve_info).length < 1)">
                <p class="info flex-center">你现在还没有已经建立的实验</p>
                <div class="btn">
                    <el-button type="primary" @click.native.prevent.stop="goReserveSeat">新建实验</el-button>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'ReserveInfo',
    data() {
        return {
            reserveInfo: {
                title: '',
                floor: '',
                seat_no: '',
                leave_tiem: ''
            },
        }
    },
    computed: {
        ...mapGetters(['front_reserve_info', 'userinfo']),
    },
    mounted() {
        // 显示预约信息
        this.setReserveInfo()
        // 获取用户信息
        this.getUserInfo()
    },
    methods: {
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
        // 去预约座位页
        goReserveSeat() {
            this.$bus.$emit('switchCheckNav', '1-1')
            this.goPath('reserveseat')
        },
        // 如果存在预约信息, 显示
        setReserveInfo() {
            if (Object.keys(this.front_reserve_info).length > 0) {
                this.reserveInfo = {
                    title: `${this.userinfo.username}同学, 你于 ${this.front_reserve_info.begin_time.substring(11, 19)} 预约了座位`,
                    floor: `座位楼层: ${this.front_reserve_info.seat_floor}`,
                    seat_no: `座位编号: ${this.front_reserve_info.seat_no}`,
                    leave_tiem: `离座时间: ${this.front_reserve_info.end_time.substring(11, 19)}`
                }
            }
        },
        // 获取用户信息
        async getUserInfo() {
            try {
                await this.$store.dispatch('getUserInfo', { user_id: this.userinfo.user_id })
            } catch (e) {
                console.warn(e.message)
            }
        },
        // 离开座位
        async leaveSeat() {
            if (Object.keys(this.front_reserve_info).length > 0) {
                await this.$confirm('确定离开座位?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    try {
                        await this.$store.dispatch('frontLeaveSeat', { user_id: this.userinfo.user_id, seat_id: this.front_reserve_info.seat_id })
                            .then(async res => {
                                this.$notify({ title: '成功', type: 'success', message: res })
                                await this.getUserInfo()
                            })
                    } catch (e) {
                        console.warn(e.message)
                    }
                }).catch(() => { })
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

    .info {
        margin-left: 50px;
        flex-flow: column;
        justify-content: center;
        color: #fff;
    }
}

.main {
    width: 100%;
    height: calc(100% - 100px);
    box-sizing: border-box;
    padding: 80px 30% 0;

    .reserve-detail {
        .reserve-detail-item {
            font-size: 20px;
        }

        .leave-seat {
            width: 100%;
            padding-top: 30px;
        }
    }

    .no-more {
        .info {
            width: 100%;
            font-size: 25px;
            color: rgb(160, 160, 160);
            padding-bottom: 30px;
        }

        .btn {
            width: 100%;
            text-align: center;
        }
    }
}
</style>