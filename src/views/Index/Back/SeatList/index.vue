<template>
    <div class="seatlist maxWH clearfix">
        <!-- 搜索 -->
        <div class="search">
            <el-form ref="searchSeatForm" class="flex maxWH search-form" :model="searchForm" label-width="80px">
                <el-form-item label="座位编号" prop="seat_no">
                    <el-input v-model.trim="searchForm.seat_no" placeholder="请输入想查询的座位编号"></el-input>
                </el-form-item>
                <el-form-item label="座位状态" prop="seat_status">
                    <el-select v-model="searchForm.seat_status" placeholder="请选择座位状态">
                        <el-option v-for="ss in seat_status" :key="ss.value" :label="ss.label" :value="ss.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item class="flex" style="overflow: hidden;">
                    <el-button type="primary" @click.native.prevent.stop="search">搜索</el-button>
                    <el-button @click.native.prevent.stop="resetSearchForm('searchSeatForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 添加 -->
        <el-button type="primary" @click.native.prevent.stop="addSeat" style="margin: 15px 0;">添加座位</el-button>
        <!-- 列表显示座位 -->
        <el-table :data="seatlist" border style="width: 100%" max-height="250">
            <el-table-column prop="seat_no" label="座位编号">
            </el-table-column>
            <el-table-column prop="seat_status" label="座位状态">
            </el-table-column>
            <el-table-column prop="use_count" label="使用次数">
            </el-table-column>
            <el-table-column prop="create_time" label="创建时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作" header-align="center">
                <template slot-scope="scope">
                    <div class="handler flex-center">
                        <el-button type="warning" @click.native.prevent.stop="updateSeat(scope.$index, seatlist)">
                            修改
                        </el-button>
                        <el-button @click.native.prevent="delSeat(scope.$index, seatlist)" type="danger">
                            移除
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page_no"
            :page-sizes="page_sizes" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper"
            :total="seat_count" style="padding: 10px 0;">
        </el-pagination>
    </div>
</template>

<script>
import { seat_status_use, seat_status_free } from '@/config'
import { mapGetters } from 'vuex'
export default {
    name: 'SeatList',
    data() {
        return {
            seat_status: [
                { label: '全部', value: '0' },
                { label: seat_status_use, value: '1' },
                { label: seat_status_free, value: '2' },
            ],
            // 搜索座位列表的表单
            searchForm: {
                seat_no: '',
                seat_status: '',
            },
            // 添加座位信息的表单
            seatAddDialog: false,
            // 修改用户信息的表单
            seatUpdateDialog: false,
            // 当前页
            page_no: 1,
            // 初始化每页条目数
            page_size: 8,
            // 个数选择器
            page_sizes: [8, 16, 30, 50, 100],
        }
    },
    computed: {
        ...mapGetters(['seatlist', 'seat_count'])
    },
    mounted() {
        this.searchForm.seat_status = this.seat_status[0].value
        // 获取座位列表
        this.getSeatList()
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 重置搜索表单
        resetSearchForm(formname) {
            this.resetForm(formname)
            this.searchForm.seat_status = this.seat_status[0].value
        },
        // 添加座位
        async addSeat() {
            await this.$prompt('请输入座位编号', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^[1-5]-[1-9][0-9][1-9]$/,
                // 
                inputErrorMessage: '座位编号格式: 楼层-号码'
            }).then(async ({ value }) => {
                try {
                    const seat_no = value
                    await this.$store.dispatch('addSeat', JSON.stringify({ seat_no }))
                        .then(res => {
                            this.$message({ type: 'success', message: res })
                            this.getSeatList()
                        }).catch(err => { this.$message({ type: 'warning', message: err.message }) })
                } catch (e) {
                    this.$message({ type: 'info', message: e.message })
                }
            }).catch(() => { this.$message({ type: 'info', message: '取消输入' }) })
        },
        // 修改座位信息
        async updateSeat(index, rows) {
            const seat_id = rows[index].seat_id
            await this.$prompt('请输入座位编号', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^[1-5]-[1-9][0-9][1-9]$/,
                inputErrorMessage: '座位编号格式: 楼层-号码'
            }).then(async ({ value }) => {
                try {
                    const seat_no = value
                    await this.$store.dispatch('backUpdateSeatInfo', JSON.stringify({ seat_id, seat_no }))
                        .then(res => {
                            this.$message({ type: 'success', message: res })
                            this.getSeatList()
                        }).catch(err => { this.$message({ type: 'warning', message: err.message }) })
                } catch (e) {
                    this.$message({ type: 'warning', message: e.message })
                }
            }).catch(() => { this.$message({ type: 'info', message: '取消输入' }) })
        },
        // 移除座位
        async delSeat(index, rows) {
            const seat_no = rows[index].seat_no
            const seat_id = rows[index].seat_id
            await this.$confirm(`是否移除座位 [${seat_no}] ?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    await this.$store.dispatch('delSeat', { seat_id })
                        .then(res => {
                            this.$message({ type: 'success', message: res })
                            // 重新获取座位列表
                            this.getSeatList()
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
            this.getSeatList()
        },
        // 当前第几页
        handleCurrentChange(val) {
            this.page_no = val
            this.getSeatList()
        },
        // 获取座位列表
        async getSeatList() {
            try {
                const { page_no, page_size } = this
                let seat_no = this.searchForm.seat_no
                const reg = /^[0-9-]{1,5}$/
                if (!reg.test(seat_no)) {
                    if (seat_no !== '') {
                        seat_no = '1111111111111111111111111111111111111'
                    }
                }
                let seat_status = Number.parseInt(this.searchForm.seat_status) || 0
                if (seat_status === 0) seat_status = '全部'
                else if (seat_status === 1) seat_status = seat_status_use
                else if (seat_status === 2) seat_status = seat_status_free
                await this.$store.dispatch('getSeatList', JSON.stringify({ page_no, page_size, seat_no, seat_status }))
            } catch (e) {
                this.$message({ type: 'warning', message: e.message })
            }
        },
        // 搜索
        search() {
            this.page_no = 1
            this.page_size = this.page_sizes[0]
            this.getSeatList()
        },
    },
}
</script>

<style lang="less" scoped>
.seatlist {
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