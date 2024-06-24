<template>
    <div class="home maxWH">
        <div class="back-home maxWH clearfix" v-if="identity === user_identity_admin">
            <!-- 头部 -->
            <div class="header flex-center">
                <!-- 左侧两个卡片 -->
                <div class="left-card flex-center">
                    <el-card class="box-card">
                        <div class="title flex-center">
                            <span>系统用户数</span>
                            <i class="el-icon-info"></i>
                        </div>
                        <div class="info">
                            {{ echarts_info.search_seat_count }}
                        </div>
                    </el-card>
                    <el-card class="box-card">
                        <div class="title flex-center">
                            <span>总计实验数</span>
                            <i class="el-icon-info"></i>
                        </div>
                        <div class="info">
                            {{ echarts_info.login_count }}
                        </div>
                    </el-card>
                </div>
                <!-- 右侧饼图 -->
                <div class="right-card">
                    <PieChart :echarts_info="echarts_info"></PieChart>
                </div>
            </div>
            <!-- 底部 -->
            <div class="floor flex-center">
                <!-- 左侧折线图: 座位使用次数占比 -->
                <div class="left-line">
                 
                </div>
                <!-- 右侧排名 -->
                <div class="right-ranking">
                    
                    
                </div>
            </div>
        </div>
        <div class="front-home maxWH w flex-center" v-if="(identity === user_identity_user)">
            <span class="title">算法实验管理展示系统</span>
            <div class="intro flex-center">
                <p>欢迎使用算法实验管理展示系统, 你可以在上方导航或下方按钮处新建算法实验</p>
                <p>可视化RL可以在上方导航或右上方个人中心处查看, 祝你使用愉快</p>
            </div>
            <div class="btn">
                <el-button type="primary" @click.native.prevent.stop="goReserveSeat">新建实验</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { user_identity_admin, user_identity_user } from '@/config'
import LineChart from './LineChart'
import PieChart from './PieChart'
export default {
    name: 'Home',
    components: { LineChart, PieChart },
    data() {
        return {
            user_identity_admin: user_identity_admin,
            user_identity_user: user_identity_user,
        }
    },
    computed: {
        ...mapGetters(['identity', 'echarts_info', 'get_time_arr', 'get_time_val_arr', 'get_time_dict'])
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
    },
}
</script>

<style lang="less" scoped>
.back-home {
    box-sizing: border-box;
    padding: .1rem;

    .header {
        width: 100%;
        height: 80%;
        justify-content: space-between;

        .left-card {
            width: 30%;
            height: 100%;
            flex-flow: column;
            justify-content: space-between;

            .box-card {
                width: 100%;
                height: 45%;

                .title {
                    font-size: .13rem;
                    color: rgb(160, 180, 180);
                    justify-content: space-between;
                }

                .info {
                    font-size: .22rem;
                }
            }
        }

        .right-card {
            width: 68%;
            height: 100%;
            border-radius: .05rem;
            box-shadow: 0 0 .1rem rgba(0, 0, 0, .1);
            box-sizing: border-box;
            padding: .1rem;
        }
    }

    .floor {
        width: 100%;
        height: 0%;
        margin-top: 2%;
        border-radius: .05rem;
        box-shadow: 0 0 .1rem rgba(0, 0, 0, .1);

        .left-line {
            width: 70%;
            height: 100%;
            box-sizing: border-box;
            padding: .1rem;
        }

        .right-ranking {
            width: 30%;
            height: 100%;
            box-sizing: border-box;
            padding: .1rem .1rem .1rem 0;

            .ranking-title {
                font-size: .1rem;
            }

            .ranking-list {
                box-sizing: border-box;
                padding-top: .1rem;
                width: 100%;

                .ranking-item {
                    width: 100%;
                    font-size: .1rem;
                    box-sizing: border-box;
                    padding-bottom: .1rem;

                    .item-num {
                        width: 15%;

                        span {
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            width: .13rem;
                            height: .13rem;
                        }

                        .radius {
                            color: #fff;
                            border-radius: 50%;
                            background-color: #333;
                        }
                    }

                    .item-floor {
                        width: 50%;
                    }

                    .item-count {
                        width: 35%;
                    }
                }
            }

        }
    }
}

.front-home {
    box-sizing: border-box;
    padding: 10px;
    flex-flow: column;
    // justify-content: space-evenly;

    .title {
        font-size: 50px;
        color: #555;
        font-weight: 900;
    }

    .intro {
        margin: 50px 0;
        font-size: 18px;
        flex-flow: column;
    }
}
</style>