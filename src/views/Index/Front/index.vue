<template>
    <div class="front maxWH">
        <!-- 前端页面路由匹配到的组件将渲染在这里 -->
        <router-view v-if="(identity === user_identity_user)"></router-view>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { user_identity_user } from '@/config'
export default {
    name: 'Back',
    data() {
        return {
            user_identity_user: user_identity_user,
        }
    },
    computed: {
        ...mapGetters(['identity']),
    },
}
</script>

<style lang="less" scoped>
.front {
    overflow: auto;
}
</style>