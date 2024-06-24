<template>
    <div class="login maxWH flex-center">
        <el-form ref="loginForm" :model="loginForm" label-color="#fff" label-width="80px" :rules="rules"
            class="login-form" hide-required-asterisk @keyup.native.enter="login('loginForm')">
            <el-form-item prop="account" class="login-item">
                <span slot="label"><i class="el-icon-user"></i>账号</span>
                <el-input v-model.trim="loginForm.account" autocomplete="off" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item prop="password" class="login-item">
                <span slot="label"><i class="el-icon-unlock"></i>密码</span>
                <el-input type="password" v-model.trim="loginForm.password" autocomplete="off"
                    placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item align="center" style="box-sizing: border-box;padding-right: 80px;">
                <el-button type="primary" @click.native.prevent.stop="login('loginForm')">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import { getAccount } from '@/utils/account'
export default {
    name: 'Login',
    data() {
        const validataAccount = (rule, value, callback) => {
            // const reg = /^[0-9]{6,12}$/
            // if (value.trim().length <= 0) {
            //     callback(new Error('请输入账号'))
            // } else if (!reg.test(value.trim())) {
            //     callback(new Error('账号格式错误'))
            // } else {
            //     callback()
            // }
            if (value.trim().length <= 0) {
                callback(new Error('请输入账号'))
            } else {
                callback()
            }
        }
        const validataPassword = (rule, value, callback) => {
            const reg = /^[a-zA-Z0-9_]{8,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入密码'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('密码格式错误'))
            } else {
                callback()
            }
        }
        return {
            rules: {
                account: [{ required: true, trigger: 'blur', validator: validataAccount }],
                password: [{ required: true, trigger: 'blur', validator: validataPassword }]
            },
            loginForm: {
                account: getAccount() || '',
                password: ''
            },
        }
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 用户登录
        // 使用 async/await 改写登录逻辑
        async login(formname) {
            try {
                // 使用 Element UI validate 方法的返回值必须经过改造才能这样使用，或者你可以使用 Promise 封装原有的回调形式
                const valid = await new Promise((resolve, reject) => {
                    this.$refs[formname].validate((valid) => {
                        if (valid) resolve(valid);
                        else reject(new Error('表单验证失败'));
                    });
                });

                // 如果验证通过，继续执行登录
                const res = await this.$store.dispatch('userLogin', this.loginForm); // dispatch用来触发store中的action方法
                this.resetForm(formname);
                // this.$message({ type: 'success', message: res });  // 成功消息，根据需要开启
                this.$router.push('/');  // 导航到首页
            } catch (err) {
                this.$message({ type: 'warning', message: err.message });  // 统一错误处理
            }
        },
    },
}
</script>

<style lang="less" scoped>
.login {
    background: url('../../assets/bg.png') no-repeat;
    background-size: cover;

    .login-form {
        width: 330px;
        height: 230px;
        background-color: rgba(255, 255, 255);
        box-sizing: border-box;
        padding-top: 30px;
        border-radius: 10px;
    }
}
</style>