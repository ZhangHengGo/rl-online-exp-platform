<template>
    <el-row class="left-menu">
        <el-col v-if="(identity === user_identity_admin)">
            <el-menu ref="backLeftMenu" class="el-menu-vertical-demo" background-color="#324057" text-color="#fff"
                active-text-color="#409eff" default-active="0">
                <el-menu-item index="0" @click.native.prevent.stop="goHome">
                    <i class="fa el-icon-s-home"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                <template v-for="bitem in backitems">
                    <el-submenu v-if="bitem.children" :index="bitem.path" :key="bitem.path">
                        <template slot="title">
                            <i :class="'fa ' + bitem.icon"></i>
                            <span slot="title">{{ bitem.name }}</span>
                        </template>
                        <!-- <router-link> 默认会被渲染成一个a标签 ，通过'to'属性指定链接' -->
                        <router-link v-for="(cbitem, cbindex) in bitem.children" :to="getBackPath(cbitem.path)"
                            :key="cbindex">
                            <el-menu-item :index="cbitem.path">
                                <span slot="title">{{ cbitem.name }}</span>
                            </el-menu-item>
                        </router-link>
                    </el-submenu>
                </template>
            </el-menu>
        </el-col>
    </el-row>
</template>

<script>
import { user_identity_admin } from '@/config'
import { mapGetters } from 'vuex'
export default {
    name: 'LeftMenu',
    data() {
        return {
            // 身份为管理员
            user_identity_admin: user_identity_admin,
            // 管理员导航
            backitems: [
                {
                    icon: 'el-icon-s-tools',
                    name: '用户管理',
                    path: 'user',
                    children: [
                        // { path: 'bmyinfo', name: '个人信息' },
                        { path: 'userlist', name: '用户列表' }
                    ],
                },
                {
                    icon: 'el-icon-message-solid',
                    name: '111',
                    path: 'plot',
                    children: [
                        { path: 'plot', name: '222' }
                    ]
                },
                {
                    icon: 'el-icon-s-claim',
                    name: '实验管理',
                    path: 'reserve',
                    children: [
                        { path: 'reservelist', name: '实验列表' }
                    ],
                },
                {
                    icon: 'el-icon-s-claim',
                    name: '算法管理',
                    path: 'alg',
                    children: [
                        { path: 'alg', name: '算法管理' }
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
            // 用户导航
            items: []
        }
    },
    computed: {
        ...mapGetters(['identity', 'userinfo'])
    },
    mounted() {
        this.$bus.$on('changeBackMenuCheck', (path) => {
            this.$refs.backLeftMenu.activeIndex = path
        })
    },
    beforeDestroy() {
        this.$bus.$off('changeBackMenuCheck')
    },
    methods: {
        // 获取用户信息
        async getUserInfo() {
            try {
                await this.$store.dispatch('getUserInfo', { user_id: this.userinfo.user_id })
            } catch (e) {
                console.warn(e.message)
            }
        },
        getBackPath(path) {
            let href = this.$route.path
            if (href.includes('back')) {
                return path
            } else {
                return 'back/' + path
            }
        },
        async goHome() {
            await this.getUserInfo()
            this.$router.push('/')
        },
    },
}
</script>

<style lang="less" scoped>
.left-menu {
    position: fixed;
    left: 0;
    top: 60px;
    z-index: 1;
    width: 180px;
    height: calc(100% - 60px);
    background-color: #324057;
    overflow-x: hidden;
    overflow-y: auto;

    .el-menu {
        border: none;
    }

    .fa {
        margin-right: 5px;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 180px;
        min-height: 400px;
    }

    .el-menu-vertical-demo {
        width: 35px;
    }

    .el-submenu .el-menu-item {
        min-width: 180px;
    }
}


</style>