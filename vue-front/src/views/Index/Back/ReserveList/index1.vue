<template>
    <div class="reserve-list maxWH clearfix">
        <!-- 搜索 -->
        <div class="search">
            <el-form ref="searchReserveForm" class="flex maxWH search-form" :model="searchForm" label-width="80px">
                <el-form-item label="用户账号" prop="account">
                    <el-input v-model.trim="searchForm.account" placeholder="请输入想查询的用户账号"></el-input>
                </el-form-item>
                <el-form-item label="座位编号" prop="seat_no">
                    <el-input v-model.trim="searchForm.seat_no" placeholder="请输入想查询的座位编号"></el-input>
                </el-form-item>
                <el-form-item label="预约状态" prop="reserve_status">
                    <el-select v-model="searchForm.reserve_status" placeholder="请选择预约状态">
                        <el-option v-for="rs in reserve_status" :key="rs.value" :label="rs.label" :value="rs.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="flex" style="margin-left: -70px;overflow: hidden;">
                    <el-button type="primary" @click.native.prevent.stop="search">搜索</el-button>
                    <el-button @click.native.prevent.stop="resetSearchForm('searchReserveForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <el-button type="danger" :disabled="reserve_count<1" @click.native.prevent.stop="delAllReserve" style="margin: 15px 0;">移除所有超时记录</el-button>
        <!-- 列表显示预约信息 -->
        <el-table :data="reservelist" border style="width: 100%" max-height="250">
            <el-table-column prop="account" label="用户账号">
            </el-table-column>
            <el-table-column prop="seat_no" label="座位编号">
            </el-table-column>
            <el-table-column prop="begin_time" label="预约开始时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="end_time" label="预约结束时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="reserve_status" label="预约状态">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.reserve_status === '正常' ? 'info' : 'warning'">{{
                            scope.row.reserve_status
                    }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" header-align="center">
                <template slot-scope="scope">
                    <div class="handler flex-center">
                        <el-button :disabled="scope.row.reserve_status === '正常'"
                            @click.native.prevent="delReserve(scope.$index, reservelist)" type="danger">
                            移除
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page_no"
            :page-sizes="page_sizes" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper"
            :total="reserve_count" style="padding: 10px 0;">
        </el-pagination>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'ReserveList',
    data() {
        const validataTitle = (rule, value, callback) => {
            const reg = /^[0-9A-Za-z\u4e00-\u9fa5,!.*&^%#+=-_:;。，！「」——~、@]{3,12}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入标题'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('标题由3到12位的数字、字母、汉字或部分符号组成'))
            } else {
                callback()
            }
        }
        const validataDetail = (rule, value, callback) => {
            const reg = /^[0-9A-Za-z\u4e00-\u9fa5,!.*&^%#+=-_:;。，！…. ：；，「」——~、\s@]{15,100}$/
            if (value.trim().length <= 0) {
                callback(new Error('请输入通知详情'))
            } else if (!reg.test(value.trim())) {
                callback(new Error('通知详情由15到100位的数字、字母、汉字或部分符号组成'))
            } else {
                callback()
            }
        }
        return {
            // 当前页
            page_no: 1,
            // 初始化每页条目数
            page_size: 8,
            // 个数选择器
            page_sizes: [8, 16, 30, 50, 100],
            reserve_status: [
                { label: '全部', value: '0' },
                { label: '正常', value: '1' },
                { label: '超时', value: '2' },
            ],
            // 表单验证规则
            rules: {
                notice_title: [{ required: true, trigger: ['blur', 'change'], validator: validataTitle }],
                notice_detail: [{ required: true, trigger: 'blur', validator: validataDetail }],
            },
            // 搜索通知列表的表单
            searchForm: {
                account: '', // 用户账号
                seat_no: '', // 座位编号
                reserve_status: '' // 预约状态
            },
        }
    },
    computed: {
        ...mapGetters(['reservelist', 'reserve_count'])
    },
    mounted() {
        this.searchForm.reserve_status = this.reserve_status[0].value
        // 获取预约列表
        this.getReserveList()
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 重置搜索表单
        resetSearchForm(formname) {
            this.resetForm(formname)
            this.searchForm.reserve_status = this.reserve_status[0].value
        },
        // 每页多少条目
        handleSizeChange(val) {
            this.page_size = val
            this.page_no = 1
            this.getReserveList()
        },
        // 当前第几页
        handleCurrentChange(val) {
            this.page_no = val
            this.getReserveList()
        },
        // 搜索
        search() {
            this.page_no = 1
            this.page_size = this.page_sizes[0]
            this.getReserveList()
        },
        // 获取预约信息列表
        async getReserveList() {
            try {
                const { page_no, page_size } = this
                let account = this.searchForm.account
                const ac_reg = /^[0-9]{1,12}$/
                if (!ac_reg.test(account)) {
                    if (account !== '') {
                        account = '1111111111111111111111111111111111111'
                    }
                }
                let seat_no = this.searchForm.seat_no
                const reg = /^[0-9-]{1,5}$/
                if (!reg.test(seat_no)) {
                    if (seat_no !== '') {
                        seat_no = '1111111111111111111111111111111111111'
                    }
                }
                let reserve_status = Number.parseInt(this.searchForm.reserve_status) || 0
                if (reserve_status === 0) reserve_status = '全部'
                else if (reserve_status === 1) reserve_status = '正常'
                else if (reserve_status === 2) reserve_status = '超时'
                await this.$store.dispatch('getReserveList', JSON.stringify({ page_no, page_size, account, seat_no, reserve_status }))
            } catch (e) {
                console.warn(e.message)
            }
        },
        // 移除超时的预约信息
        async delReserve(index, rows) {
            await this.$confirm('确认移除该预约信息?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    const { reserve_id, seat_id } = rows[index]
                    await this.$store.dispatch('delReserve', { reserve_id, seat_id })
                        .then(async res => {
                            this.$message({ type: 'success', message: res })
                            // 重新获取预约信息列表
                            await this.getReserveList()
                        }).catch(err => this.$message({ type: 'warning', message: err.message }))
                } catch (e) {
                    console.warn(e.message)
                }
            }).catch(() => { this.$message({ type: 'info', message: '已取消' }) })
        },
        // 移除所有超时的预约记录
        async delAllReserve() {
            await this.$confirm('此操作将清空超时的预约记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    await this.$store.dispatch('clearTimeoutReserve')
                        .then(async res => {
                            this.$message({ type: 'success', message: res })
                            // 重新获取预约信息列表
                            await this.getReserveList()
                        }).catch(err => this.$message({ type: 'warning', message: err.message }))
                } catch (e) {
                    console.warn(e.message)
                }
            }).catch(() => { this.$message({ type: 'info', message: '已取消' }) })
        },
    },
}
</script>

<style lang="less" scoped>
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
</style>