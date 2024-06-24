<template>
    <div :class="identity === user_identity_admin ? 'index maxWH' : 'index maxWH fixed-w'">
        <!-- 头部导航 -->
        <HeadNav></HeadNav>
        <!-- 左侧导航 -->
        <LeftMenu v-if="(identity === user_identity_admin)"></LeftMenu>
        <!-- 底部固定区域 -->
        <!-- <FootArea></FootArea> -->
        <!-- 内容 -->
        <div :class="identity === user_identity_admin ? 'right-container' : 'center-container'">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { user_identity_admin } from '@/config'
import HeadNav from '@/components/HeadNav'
import LeftMenu from '@/components/LeftMenu'
import FootArea from '@/components/FootArea'
export default {
    name: 'Index',
    components: { HeadNav, LeftMenu, FootArea },
    data() {
        return {
            user_identity_admin: user_identity_admin,
        }
    },
    computed: {
        ...mapGetters(['identity']),
    },
}
</script>

<style lang="less" scoped>
.index {
    overflow: hidden;
    position: relative;

    .right-container {
        position: relative;
        top: 60px;
        left: 180px;
        width: calc(100% - 180px);
        height: calc(100% - 60px);
        overflow: hidden;
    }

    .center-container {
        position: relative;
        top: 60px;
        width: 100%;
        height: calc(100% - 60px);
        overflow: hidden;
    }
}
.fixed-w{
    min-width: 1226px;
}
</style>