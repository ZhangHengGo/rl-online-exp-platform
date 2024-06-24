<template>
    <div class="user-list maxWH clearfix">
        <!-- 搜索 -->
        <div class="search">
            <el-form ref="searchTestForm" class="flex maxWH search-form" :model="searchForm" label-width="80px">
                <el-form-item label="账号" prop="account">
                    <el-input v-model.trim="searchForm.account" placeholder="请输入想查询的账号"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model.trim="searchForm.username" placeholder="请输入想查询的用户名"></el-input>
                </el-form-item>
                <el-form-item label="用户状态" prop="user_status">
                    <el-select v-model="searchForm.user_status" placeholder="请选择用户状态">
                        <el-option v-for="us in user_status" :key="us.value" :label="us.label" :value="us.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="flex" style="margin-left: -70px;overflow: hidden;">
                    <el-button type="primary" @click.native.prevent.stop="search">搜索</el-button>
                    <el-button @click.native.prevent.stop="resetSearchForm('searchUserForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 添加 -->
        <el-button type="primary" @click.native.prevent.stop="userAddDialog = true"
            style="margin: 15px 0;">添加用户</el-button>
        <!-- 列表显示用户 -->
        <el-table :data="userlist" border style="width: 100%" max-height="250">
            <el-table-column prop="account" label="账号" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="username" label="用户名">
            </el-table-column>
            <el-table-column prop="user_status" label="用户状态">
            </el-table-column>
            <el-table-column prop="create_time" label="创建时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="login_time" label="上次登录时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作" header-align="center">
                <template slot-scope="scope">
                    <div class="handler flex-center">
                        <el-button type="warning" @click.native.prevent.stop="openUpdateUser(scope.$index, userlist)">
                            修改
                        </el-button>
                        <el-button @click.native.prevent="delUser(scope.$index, userlist)" type="danger">
                            封禁
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page_no"
            :page-sizes="page_sizes" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper"
            :total="user_count" style="padding: 10px 0;">
        </el-pagination>
        <!-- dialog -->
        <!-- 添加用户 -->
        <el-dialog title="添加用户" :visible.sync="userAddDialog">
            <el-form ref="userAddForm" :model="userAddForm" label-width="80px" :rules="rules">
                <el-form-item label="账号" prop="account">
                    <el-input v-model.trim="userAddForm.account" autocomplete="off" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model.trim="userAddForm.username" autocomplete="off" placeholder="请输入用户名">
                    </el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model.trim="userAddForm.password" autocomplete="off"
                        placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="adduserpwd1">
                    <el-input type="password" v-model.trim="userAddForm.adduserpwd1" autocomplete="off"
                        placeholder="请确认密码"></el-input>
                </el-form-item>
                <el-form-item label="用户状态" prop="user_status">
                    <el-radio-group v-model="userAddForm.user_status">
                        <el-radio :label="user_status[1].label"></el-radio>
                        <el-radio :label="user_status[2].label"></el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click.native.prevent.stop="addUser('userAddForm')">确 定</el-button>
                <el-button @click.native.prevent.stop="resetForm('userAddForm')">重 置</el-button>
            </div>
        </el-dialog>
        <!-- 修改用户信息 -->
        <el-dialog title="修改用户信息" :visible.sync="userUpdateDialog">
            <el-form ref="userUpdateForm" :model="userUpdateForm" label-width="80px" :rules="rules"
                hide-required-asterisk>
                <el-form-item label="账号">
                    <el-input v-model.trim="userUpdateForm.account" disabled autocomplete="off"
                        placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model.trim="userUpdateForm.username" disabled autocomplete="off" placeholder="请输入用户名">
                    </el-input>
                </el-form-item>
                <el-form-item label="修改密码" prop="update_pwd">
                    <el-switch v-model.trim="userUpdateForm.update_pwd"></el-switch>
                </el-form-item>
                <el-form-item v-if="userUpdateForm.update_pwd" label="密码" prop="password">
                    <el-input type="password" v-model.trim="userUpdateForm.password"
                        :disabled="!userUpdateForm.update_pwd" autocomplete="off" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item v-if="userUpdateForm.update_pwd" label="确认密码" prop="updateuserpwd1">
                    <el-input type="password" v-model.trim="userUpdateForm.updateuserpwd1"
                        :disabled="!userUpdateForm.update_pwd" autocomplete="off" placeholder="请确认密码"></el-input>
                </el-form-item>
                <el-form-item label="用户状态" prop="user_status">
                    <el-radio-group v-model="userUpdateForm.user_status">
                        <el-radio :label="user_status[1].label"></el-radio>
                        <el-radio :label="user_status[2].label"></el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click.native.prevent.stop="updateUser('userUpdateForm')">确 定</el-button>
                <el-button @click.native.prevent.stop="resetForm('userUpdateForm')">重 置</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { user_status_true, user_status_false } from '@/config'
import { mapGetters } from 'vuex'
export default {
    name: 'UserList',
    data() {
        const validataAccount = (rule, value, callback) => {
            const reg = /^[0-9]{6,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入账号'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('账号由6到12位的数字组成'))
            } else {
                callback()
            }
        }
        const validataUsername = (rule, value, callback) => {
            const reg = /^[\u4e00-\u9fa5]{2,6}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入用户名'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('用户名由2到6位的汉字组成'))
            } else {
                callback()
            }
        }
        const validataPassword = (rule, value, callback) => {
            const reg = /^[a-zA-Z0-9_]{8,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入密码'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('密码由8到12位的字母、数字或下划线组成'))
            } else {
                callback()
            }
        }
        const validataAddUserPassword1 = (rule, value, callback) => {
            if (this.userAddForm.password.trim() === '') {
                callback(new Error('请先输入密码'))
            } else if (value.trim().length <= 0) {
                callback(new Error('确认密码不得为空'))
            } else if (value.trim() !== this.userAddForm.password) {
                callback(new Error('两次输入密码不一致'))
            } else {
                callback()
            }
        }
        const validataUpdateUserPassword1 = (rule, value, callback) => {
            if (this.userUpdateForm.password.trim() === '') {
                callback(new Error('请先输入密码'))
            } else if (value.trim().length <= 0) {
                callback(new Error('确认密码不得为空'))
            } else if (value.trim() !== this.userUpdateForm.password) {
                callback(new Error('两次输入密码不一致'))
            } else {
                callback()
            }
        }
        return {
            // 表单验证规则
            rules: {
                account: [{ required: true, trigger: ['blur', 'change'], validator: validataAccount }],
                username: [{ required: true, trigger: ['blur', 'change'], validator: validataUsername }],
                password: [{ required: true, trigger: ['blur', 'change'], validator: validataPassword }],
                adduserpwd1: [{ required: true, trigger: ['blur', 'change'], validator: validataAddUserPassword1 }],
                updateuserpwd1: [{ required: true, trigger: ['blur', 'change'], validator: validataUpdateUserPassword1 }],
                user_status: [{ required: true, message: '请选择用户状态', trigger: 'change' }],
            },
            user_status: [
                { label: '全部', value: '0' },
                { label: user_status_true, value: '1' },
                { label: user_status_false, value: '2' },
            ],
            // 搜索用户列表的表单
            searchForm: {
                account: '',
                username: '',
                user_status: ''
            },
            // 当前页
            page_no: 1,
            // 初始化每页条目数
            page_size: 8,
            // 个数选择器
            page_sizes: [8, 16, 30, 50, 100],
            // 添加用户信息的表单
            userAddDialog: false,
            // 修改用户信息的表单
            userUpdateDialog: false,
            // 添加用户信息表单
            userAddForm: {
                account: '',
                username: '',
                password: '',
                adduserpwd1: '',
                user_status: user_status_true,
            },
            // 修改用户信息表单
            userUpdateForm: {
                user_id: 0, // 用户id
                account: '', // 账号
                username: '', // 用户名
                update_pwd: false, // 是否修改密码
                password: '', // 密码
                updateuserpwd1: '', // 确认密码
                user_status: user_status_true, // 用户状态
            },
        }
    },
    watch: {
        // 修改用户信息时监听是否需要修改密码
        'userUpdateForm.update_pwd': {
            deep: true,
            handler(val) {
                if (!val) {
                    this.userUpdateForm.password = ''
                    this.userUpdateForm.updateuserpwd1 = ''
                }
            },
        },
    },
    computed: {
        ...mapGetters(['user_id', 'userlist', 'user_count'])
    },
    mounted() {
        this.searchForm.user_status = this.user_status[0].value
        // 获取用户列表
        this.getUserList()
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 添加用户信息
        async addUser(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    try {
                        await this.$store.dispatch('addUser', JSON.stringify(this.userAddForm))
                            .then(res => {
                                this.resetForm(formname)
                                this.userAddDialog = false
                                this.$message({ type: 'success', message: res })
                                // 重新获取用户列表
                                this.getUserList()
                            }).catch(err => this.$message({ type: 'warning', message: err.message }))
                    } catch (e) {
                        this.$message({ type: 'warning', message: e.message })
                    }
                }
            })
        },
        // 打开修改用户信息的遮罩页
        openUpdateUser(index, rows) {
            this.userUpdateDialog = true
            const userinfo = rows[index]
            // 获取当前用户的信息
            this.userUpdateForm = {
                user_id: userinfo.user_id,
                account: userinfo.account,
                username: userinfo.username,
                update_pwd: false,
                password: '',
                updateuserpwd1: '',
                user_status: userinfo.user_status,
            }
            // console.log(userinfo)
        },
        // 修改用户信息
        async updateUser(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    try {
                        const userinfo = {
                            user_id: this.userUpdateForm.user_id,
                            password: this.userUpdateForm.password,
                            user_status: this.userUpdateForm.user_status
                        }
                        await this.$store.dispatch('backUpdateUserInfo', JSON.stringify(userinfo))
                            .then(res => {
                                this.resetForm(formname)
                                this.userUpdateDialog = false
                                this.$message({ type: 'success', message: res })
                                // 重新获取用户列表
                                this.getUserList()
                            }).catch(err => this.$message({ type: 'warning', message: err.message }))
                    } catch (e) {
                        this.$message({ type: 'warning', message: e.message })
                    }
                }
            })
        },
        // 封禁用户
        async delUser(index, rows) {
            const username = rows[index].username
            const user_id = rows[index].user_id
            await this.$confirm(`是否封禁用户 [${username}] ?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    await this.$store.dispatch('delUser', { user_id })
                        .then(res => {
                            this.$message({ type: 'success', message: res })
                            // 重新获取用户列表
                            this.getUserList()
                        }).catch(err => this.$message({ type: 'warning', message: err.message }))
                } catch (e) {
                    this.$message({ type: 'warning', message: e.message })
                }
            }).catch(() => {
                this.$message({ type: 'info', message: '已取消' })
            })
        },
        // 每页多少条目
        handleSizeChange(val) {
            this.page_size = val
            this.page_no = 1
            this.getUserList()
        },
        // 当前第几页
        handleCurrentChange(val) {
            this.page_no = val
            this.getUserList()
        },
        // 获取用户列表
        async getUserList() {
            try {
                const { page_no, page_size } = this
                let account = this.searchForm.account
                const reg = /^[0-9]{1,12}$/
                if (!reg.test(account)) {
                    if (account !== '') {
                        account = '1111111111111111111111111111111111111'
                    }
                }
                let username = this.searchForm.username
                const name_reg = /^[\u4e00-\u9fa50-9a-zA-Z]{1,6}$/
                if (!name_reg.test(username)) {
                    if (username !== '') {
                        username = '1111111111111111111111111111111111111'
                    }
                }
                let user_status = Number.parseInt(this.searchForm.user_status) || 0
                if (user_status === 0) user_status = '全部'
                else if (user_status === 1) user_status = user_status_true
                else if (user_status === 2) user_status = user_status_false
                await this.$store.dispatch('getUserList', JSON.stringify({ page_no, page_size, account, username, user_status }))
            } catch (e) {
                this.$message({ type: 'warning', message: e.message })
            }
        },
        // 重置搜索表单
        resetSearchForm(formname) {
            this.resetForm(formname)
            this.searchForm.user_status = this.user_status[0].value
        },
        // 搜索
        search() {
            this.page_no = 1
            this.page_size = this.page_sizes[0]
            this.getUserList()
        },
    },
}
</script>

<style lang="less" scoped>
.user-list {
    .search {
        width: 100%;
        height: 80px;
        box-sizing: border-box;
        border: 1px solid rgb(220, 220, 220);
        padding-top: 15px;
        overflow: hidden;

        .search-form {
            min-width: 400px;
            justify-content: center;
        }
    }
}
</style>