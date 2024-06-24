<template>
    <div class="feedback maxWH">
        <!-- 头部 -->
        <div class="header flex">
            <span class="title">问题反馈</span>
        </div>
        <!-- 主体 -->
        <main class="main">
            <!-- 介绍 -->
            <div class="intro">
                <p class="flex-center">在使用本系统中遇到了什么问题, 或者对于本系统有什么建议,</p>
                <p class="flex-center">又或者有什么想对开发者说的话, 等等, 都可以在这里提出来</p>
            </div>
            <!-- 表单 -->
            <el-form class="from" ref="feedbackFrom" :model="feedbackFrom" label-width="80px" label-position="top"
                hide-required-asterisk>
                <el-form-item label="问题反馈" prop="info">
                    <el-input type="textarea" v-model="feedbackFrom.info" :rows="5"></el-input>
                </el-form-item>
                <el-form-item align="center">
                    <el-button type="primary" @click.native.prevent.stop="handlerSubmit">提交</el-button>
                    <el-button @click.native.prevent.stop="resetForm('feedbackFrom')">重置</el-button>
                </el-form-item>
            </el-form>
        </main>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'Feedback',
    data() {
        return {
            feedbackFrom: {
                info: ''
            },
        }
    },
    computed: {
        ...mapGetters(['userinfo'])
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
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 提交
        async handlerSubmit() {
            const feedback_info = this.feedbackFrom.info
            const reg = /^[\s\S]{5,100}$/
            if (feedback_info.trim().length < 1) return
            if (feedback_info.trim().length > 0 && !reg.test(feedback_info)) {
                return this.$message({ type: 'warning', message: '请将反馈内容控制在5到100给个字符之间' })
            }
            // 提交
            await this.$confirm('确认提交?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    const reqinfo = { account: this.userinfo.account, username: this.userinfo.username, feedback_info }
                    await this.$store.dispatch('sendFeedback', JSON.stringify(reqinfo))
                        .then(res => {
                            this.$notify({ title: '提交成功', type: 'success', message: res })
                            this.resetForm('feedbackFrom')
                        })
                        .catch(err => this.$message({ type: 'warning', message: err.message }))
                } catch (e) {
                    console.warn(e.message)
                }
            }).catch(() => { })
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
    padding: 50px 20% 0;

    .intro {
        p {
            font-size: 18px;
        }
    }

    .from {
        box-sizing: border-box;
        padding: 50px 20% 0;
    }
}
</style>