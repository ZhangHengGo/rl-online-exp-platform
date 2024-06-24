<template>
    <div class="myinfo maxWH flex">
        <!-- 个人信息 -->
        <el-form inline label-width="80px" label-position="right" style="width: 100%;min-width: 1000px;text-align: center;">
            <el-form-item label="账号">
                <el-input v-model.trim="userinfo.account" disabled></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model.trim="userinfo.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="身份">
                <el-input v-model.trim="userinfo.identity" disabled></el-input>
            </el-form-item>
        </el-form>
        <!-- 修改密码 -->
        <el-form ref="backPwdForm" :model="pwdForm" :rules="rules" align="center" label-width="80px"
            label-position="right" hide-required-asterisk style="width: 40%;min-width: 400px;margin-top: 100px;">
            <el-form-item label="旧密码" prop="oldpwd">
                <el-input type="password" v-model.trim="pwdForm.oldpwd"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newpwd">
                <el-input type="password" v-model.trim="pwdForm.newpwd"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="newpwd1">
                <el-input type="password" v-model.trim="pwdForm.newpwd1"></el-input>
            </el-form-item>
            <el-form-item align="center">
                <el-button type="primary" @click.native.prevent.stop="updatePwd('backPwdForm')"
                    style="margin-left: -40px;">修 改</el-button>
                <el-button @click.native.prevent.stop="resetForm('backPwdForm')">重 置</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'BMyInfo',
    data() {
        const validataOldpwd = (rule, value, callback) => {
            const reg = /^[a-zA-Z0-9_]{8,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入旧密码'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('密码格式错误'))
            } else {
                callback()
            }
        }
        const validataNewpwd = (rule, value, callback) => {
            const reg = /^[a-zA-Z0-9_]{8,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入新密码'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('密码格式错误'))
            } else if (value.trim() === this.pwdForm.oldpwd) {
                callback(new Error('新密码不能与旧密码相同'))
            } else {
                callback()
            }
        }
        const validataNewpwd1 = (rule, value, callback) => {
            if (this.pwdForm.newpwd.trim() === '') {
                callback(new Error('请先输入新密码'))
            } else if (value.trim().length <= 0) {
                callback(new Error('确认密码不得为空'))
            } else if (value.trim() !== this.pwdForm.newpwd) {
                callback(new Error('两次输入密码不一致'))
            } else {
                callback()
            }
        }
        return {
            // 表单验证规则
            rules: {
                oldpwd: [{ required: true, trigger: ['blur', 'change'], validator: validataOldpwd }],
                newpwd: [{ required: true, trigger: ['blur', 'change'], validator: validataNewpwd }],
                newpwd1: [{ required: true, trigger: ['blur', 'change'], validator: validataNewpwd1 }],
            },
            // 修改密码
            pwdForm: {
                oldpwd: '',
                newpwd: '',
                newpwd1: '',
            },
        }
    },
    computed: {
        ...mapGetters(['userinfo'])
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 修改密码
        async updatePwd(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    await this.$confirm('确定修改?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(async () => {
                        try {
                            const pwdForm = { user_id: this.userinfo.user_id, oldpwd: this.pwdForm.oldpwd, newpwd: this.pwdForm.newpwd }
                            await this.$store.dispatch('updatePwd', JSON.stringify(pwdForm))
                                .then(res => {
                                    this.$message({ type: 'success', message: res })
                                    this.resetForm(formname)
                                    setTimeout(() => {
                                        // 退出登录
                                        this.$store.dispatch('userLogout')
                                            .then(res => this.$router.push('/login'))//eslint-disable-line no-unused-vars
                                    }, 1000)
                                }).catch(err => this.$message({ type: 'warning', message: err.message }))
                        } catch (e) {
                            this.$message({ type: 'warning', message: e.message })
                        }
                    }).catch(() => { this.$message({ type: 'info', message: '已取消' }) })
                }
            })
        },
    },
}
</script>

<style lang="less" scoped>
.myinfo {
    flex-flow: column;
    align-items: center;
    overflow: hidden;
}
</style>