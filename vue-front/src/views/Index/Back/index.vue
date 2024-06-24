<template>
    <div class="back maxWH">
         <!-- 后端页面路由匹配到的组件将渲染在这里 -->
        <router-view v-if="identity === user_identity_admin"></router-view>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { user_identity_admin } from '@/config'
export default {
    name: 'Back',
    data() {
        return {
            user_identity_admin: user_identity_admin,
        }
    },
    computed: {
        ...mapGetters(['identity']),
        getUserIdentity(){
            return this.userinfo.identity
        }
    },
}
</script>

<style lang="less" scoped>
.back {
    box-sizing: border-box;
    padding: 30px 15px 10px;
    overflow: auto;
}
</style>