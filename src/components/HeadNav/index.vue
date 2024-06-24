<template>
    <div class="HeadNav">
        <div class="head-nav fixed" v-if="(identity === user_identity_admin)">
            <el-row class="flex">
                <el-col :span="12" class="logo-container flex">
                    <img src="../../assets/logo.png" alt="logo" class="logo">
                    <span class="title">算法实验展示系统</span>
                </el-col>
                <el-col :span="12" class="user flex">
                    <div class="userinfo flex-center">
                        <img src="../../assets/avatar.gif" alt="avatar" class="avatar">
                        <!-- 下拉菜单 -->
                        <span class="drop-down-list">
                            <el-dropdown>
                                <span class="el-dropdown-link" style="color: #fff;">
                                    <span class="username text-ellipsis">{{ getUsername }}</span>
                                    <i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item
                                        @click.native.prevent.stop="goBackMyInfo('bmyinfo')">个人信息</el-dropdown-item>
                                    <el-dropdown-item @click.native.prevent.stop="logout">退出</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </span>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div class="head-nav-f fixed" v-if="(identity === user_identity_user)">
            <el-row class="flex head-front">
                <el-col :span="6" class="logo-container flex">
                    <img src="../../assets/logo.png" alt="logo" class="logo">
                    <span class="title">算法实验展示系统</span>
                </el-col>
                <el-col :span="12" class="nav-fun">
                    <el-menu ref="frontHeadNav" default-active="0" class="el-menu-demo" mode="horizontal"
                        background-color="#324057" text-color="#fff" active-text-color="#409eff">
                        <el-menu-item index="0" @click.native.prevent.stop="$router.push('/')">
                            首页
                        </el-menu-item>
                        <el-submenu index="1">
                            <template slot="title">新建实验</template>
                            <el-menu-item index="1-1"
                                @click.native.prevent.stop="goPath('addtest')">新建实验</el-menu-item>
                            <el-menu-item index="1-2"
                                @click.native.prevent.stop="goPath('reserveinfo')">实验展示</el-menu-item>
                        </el-submenu>
                        <el-menu-item index="3" @click.native.prevent.stop="goPath('feedback')">问题反馈</el-menu-item>
                        <el-menu-item index="4" style="position: relative;"
                            @click.native.prevent.stop="goPath('notice')">
                            <i v-if="(readlist.length > 0)" class="el-icon-warning"
                                style="position: absolute;left: 90%;top: 30%;transform: translate(-90%,-30%);color: tomato;"></i>
                            <span slot="title">消息通知</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
                <el-col :span="6" class="user flex">
                    <div class="userinfo flex-center">
                        <img src="../../assets/avatar.gif" alt="avatar" class="avatar">
                        <!-- 下拉菜单 -->
                        <span class="drop-down-list">
                            <el-dropdown>
                                <span class="el-dropdown-link" style="color: #fff;">
                                    <span class="username text-ellipsis">{{ getUsername }}</span>
                                    <i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item
                                        @click.native.prevent.stop="goUserPath('fmyinfo')">个人信息</el-dropdown-item>
                                    <el-dropdown-item
                                        @click.native.prevent.stop="goReserveInfo('reserveinfo')">查看实验</el-dropdown-item>
                                    <el-dropdown-item @click.native.prevent.stop="logout">退出</el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </span>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { user_identity_admin, user_identity_user } from '@/config'
export default {
    name: 'HeadNav',
    data() {
        return {
            user_identity_admin: user_identity_admin,
            user_identity_user: user_identity_user,
            // 用户导航
            frontitems: [
                {
                    icon: 'el-icon-s-tools',
                    name: '用户管理',
                    path: 'user',
                    children: [
                        { path: 'myinfo', name: '个人信息' },
                        { path: 'userlist', name: '用户列表' }
                    ],
                },
                {
                    icon: 'el-icon-s-claim',
                    name: '实验管理',
                    path: 'reserve',
                    children: [
                        { path: 'reservelist', name: '实验列表' }
                    ],
                },
                // {
                //     icon: 'el-icon-s-ticket',
                //     name: '座位管理',
                //     path: 'seat',
                //     children: [
                //         { path: 'seatlist', name: '座位列表' }
                //     ]
                // },
                {
                    icon: 'el-icon-message-solid',
                    name: '通知管理',
                    path: 'notice',
                    children: [
                        { path: 'noticelist', name: '通知列表' }
                    ]
                },
            ],
        }
    },
    computed: {
        ...mapGetters(['userinfo', 'identity', 'readlist']),
        getUsername() {
            return this.userinfo.username
        },
    },
    mounted() {
        this.$bus.$on('switchCheckNav', (nav) => {
            this.$refs.frontHeadNav.activeIndex = nav
        })
    },
    beforeDestroy() {
        this.$bus.$off('switchCheckNav')
    },
    methods: {
        // 退出登录
        logout() {
            this.$store.dispatch('userLogout')

                .then(res => {//eslint-disable-line no-unused-vars
                    this.$router.push('login')
                    // 导航到不同的URL,使用router.push方法，该方法会向history栈添加一个新的记录，所以当用户点击浏览器后退按钮时，则回到之前的URL
                })
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
        // 去个人信息页
        goUserPath(path) {
            this.$refs.frontHeadNav.activeIndex = null
            this.goPath(path)
        },
        // 去预约查看页
        goReserveInfo(path) {
            this.$refs.frontHeadNav.activeIndex = '1-2'
            this.goPath(path)
        },
        // 后台管理路由判断
        goBackPath(path) {
            let href = this.$route.path
            if (href.includes('back')) {
                return path
            } else {
                return 'back/' + path
            }
        },
        // 去后台个人信息页
        goBackMyInfo(path) {
            this.$bus.$emit('changeBackMenuCheck', 'bmyinfo')
            this.$router.push(this.goBackPath(path))
        },
    },
}
</script>

<style lang="less" scoped>
.HeadNav {
    width: 100%;
}

.head-nav {
    z-index: 1;
    width: 100%;
    height: 60px;
    min-width: 600px;
    box-sizing: border-box;
    padding: 5px 10px;
    background: #324057;
    color: #fff;
    border-bottom: 1px solid #1f2d3d;
    overflow: hidden;

    .logo-container {
        min-width: 400px;
        height: 49px;
        align-items: center;

        .logo {
            width: 49px;
            height: 49px;
        }

        .title {
            font-size: 22px;
            letter-spacing: 3px;
        }
    }

    .user {
        height: 49px;
        justify-content: flex-end;

        .userinfo {
            margin-right: 30px;

            .avatar {
                width: 39px;
                height: 39px;
                margin-right: 10px;
                border-radius: 10px;
            }
        }
    }
}

.head-nav-f {
    z-index: 1;
    width: 100%;
    height: 60px;
    min-width: 600px;
    box-sizing: border-box;
    padding: 5px 10px;
    background: #324057;
    color: #fff;
    // border-bottom: 1px solid #1f2d3d;
    overflow: hidden;

    

    .head-front {
        margin: 0 auto;
        width: 1226px;
        height: 60px;


        .logo-container {
            min-width: 400px;
            height: 49px;
            align-items: center;

            .logo {
                width: 49px;
                height: 49px;
            }

            .title {
                font-size: 22px;
                letter-spacing: 3px;
            }
        }

        .nav-fun {
            min-width: 600px;
            height: 49px;
        }

        .user {
            height: 49px;
            justify-content: flex-end;

            .userinfo {
                margin-right: 30px;

                .avatar {
                    width: 39px;
                    height: 39px;
                    margin-right: 10px;
                    border-radius: 10px;
                }
            }
        }
    }
    @media screen and (min-width: 1820px) {
        .head-front{
            width: 7rem;
        }
    }


}

// .el-dropdown-menu{
//         background-color: #324057 !important;
//         border: none !important;
//         margin-top: 25px !important;
//     }
//     .el-dropdown-menu__item{
//         color:#fff !important;
//     }
//     .el-dropdown-menu__item:hover{
//         color:#fff !important;
//         background-color: #1f2d3d !important;
//     }
//     .el-popper /deep/ .popper__arrow{
//         visibility: hidden !important;
//     }
</style>