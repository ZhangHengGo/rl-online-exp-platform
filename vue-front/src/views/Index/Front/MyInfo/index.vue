<template>
    <div class="myinfo maxWH">
        <!-- 头部 -->
        <div class="header flex">
            <span class="title">个人信息</span>
            <p class="info flex">查看/修改账户的基本信息</p>
        </div>
        <!-- 主体 -->
        <main class="main">
            <!-- 导航 -->
            <el-row class="tab">
                <el-col :span="6">
                    <el-menu default-active="0" class="el-menu-vertical-demo">
                        <el-menu-item index="0" @click.native.prevent.stop="showInfo">基础信息</el-menu-item>
                        <el-menu-item index="1" @click.native.prevent.stop="showPwd">修改密码</el-menu-item>
                    </el-menu>
                </el-col>
                <el-col :span="12" class="content">
                    <!-- 基本信息 -->
                    <el-form ref="frontMyInfoForm" :model="frontMyInfoForm" label-width="80px" :rules="rules"
                        hide-required-asterisk v-show="info_path">
                        <el-form-item label="账号">
                            <el-input disabled v-model.trim="userinfo.account"></el-input>
                        </el-form-item>
                        <el-form-item label="用户名">
                            <el-input disabled v-model.trim="userinfo.username"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email">
                            <el-input v-model.trim="frontMyInfoForm.email"></el-input>
                        </el-form-item>
                        <el-form-item align="center">
                            <el-button type="primary" style="margin-left: -40px;"
                                @click.native.prevent.stop="setEmail('frontMyInfoForm')">提 交</el-button>
                            <el-button @click.native.prevent.stop="resetForm('frontMyInfoForm')">重 置</el-button>
                        </el-form-item>
                    </el-form>
                    <!-- 修改密码 -->
                    <el-form ref="frontPwdForm" :model="pwdForm" :rules="pwdRules" align="center" label-width="80px"
                        label-position="right" hide-required-asterisk v-show="pwd_path">
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
                            <el-button type="primary" @click.native.prevent.stop="updatePwd('frontPwdForm')"
                                style="margin-left: -40px;">修 改</el-button>
                            <el-button @click.native.prevent.stop="resetForm('frontPwdForm')">重 置</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </main>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'MyInfo',
    data() {
        const validateOldpwd = (rule, value, callback) => {
            const reg = /^[a-zA-Z0-9_]{8,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入旧密码'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('密码格式错误'))
            } else {
                callback()
            }
        }
        const validateNewpwd = (rule, value, callback) => {
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
        const validateNewpwd1 = (rule, value, callback) => {
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
            // 邮箱表单
            frontMyInfoForm: {
                email: ''
            },
            // 修改密码
            pwdForm: {
                oldpwd: '',
                newpwd: '',
                newpwd1: '',
            },
            // 表单验证规则
            pwdRules: {
                oldpwd: [{ required: true, trigger: ['blur', 'change'], validator: validateOldpwd }],
                newpwd: [{ required: true, trigger: ['blur', 'change'], validator: validateNewpwd }],
                newpwd1: [{ required: true, trigger: ['blur', 'change'], validator: validateNewpwd1 }],
            },
            rules: {
                email: [{ type: 'email', required: true, trigger: 'blur', message: '邮箱格式不正确' }]
            },
            // 显示哪个
            info_path: true,
            pwd_path: false,
        }
    },
    computed: {
        ...mapGetters(['userinfo'])
    },
    mounted() {
        this.getUserEmail()
    },
    methods: {
        // 获取用户信息
        async getUserInfo(user_id) {
            try {
                await this.$store.dispatch('getUserInfo', { user_id })
            } catch (e) {
                console.warn(e.message)
            }
        },
        // 初始化邮箱
        getUserEmail() {
            if (this.userinfo.email === '未绑定邮箱') {
                this.frontMyInfoForm.email = ''
            } else {
                this.frontMyInfoForm.email = this.userinfo.email
            }
        },
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 显示基本信息
        showInfo() {
            this.info_path = true
            this.pwd_path = false
            this.resetForm('frontMyInfoForm')
            this.resetForm('frontPwdForm')
            this.getUserEmail()
        },
        // 显示修改密码
        showPwd() {
            this.info_path = false
            this.pwd_path = true
            this.resetForm('frontMyInfoForm')
            this.resetForm('frontPwdForm')
            this.getUserEmail()
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
                                            .then(res => this.$router.push('/login'))
                                    }, 1000)
                                }).catch(err => this.$message({ type: 'warning', message: err.message }))
                        } catch (e) {
                            this.$message({ type: 'warning', message: e.message })
                        }
                    }).catch(() => { this.$message({ type: 'info', message: '已取消' }) })
                }
            })
        },
        // 设置邮箱
        async setEmail(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    await this.$confirm('确定提交?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(async () => {
                        try {
                            const form = { user_id: this.userinfo.user_id, email: this.frontMyInfoForm.email }
                            await this.$store.dispatch('setEmail', JSON.stringify(form))
                                .then(res => {
                                    this.$message({ type: 'success', message: res })
                                    // window.location.reload()
                                    this.getUserInfo(this.userinfo.user_id)
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
        padding: 20px 20%;

        .tab {
            .content {
                box-sizing: border-box;
                padding-top: 30px;
            }
        }
    }
}

.el-menu {
    border-right: 0 !important;
}
</style>