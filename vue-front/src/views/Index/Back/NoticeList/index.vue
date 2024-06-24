<template>
    <div class="notice-list maxWH clearfix">
        <!-- 搜索 -->
        <div class="search">
            <el-form ref="searchNoticeForm" class="flex maxWH search-form" :model="searchForm" label-width="80px">
                <el-form-item label="标题" prop="notice_title">
                    <el-input v-model.trim="searchForm.notice_title" placeholder="请输入想查询的通知标题"></el-input>
                </el-form-item>
                <el-form-item class="flex" style="overflow: hidden;">
                    <el-button type="primary" @click.native.prevent.stop="search">搜索</el-button>
                    <el-button @click.native.prevent.stop="resetForm('searchNoticeForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 添加 -->
        <el-button type="primary" @click.native.prevent.stop="noticeAddDialog = true"
            style="margin: 15px 0;">发布通知</el-button>
        <!-- 列表显示通知 -->
        <el-table :data="noticelist" border style="width: 100%" max-height="250">
            <el-table-column prop="notice_title" label="标题" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="详情" header-align="center" align="center">
                <template slot-scope="scope">
                    <div class="handler">
                        <el-button @click.native.prevent="showNoticeDetail(scope.$index, noticelist)" type="info">
                            查看
                        </el-button>
                    </div>
                </template>
            </el-table-column>
            <el-table-column prop="create_time" label="创建时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作" header-align="center" align="center">
                <template slot-scope="scope">
                    <div class="handler">
                        <el-button @click.native.prevent="delNotice(scope.$index, noticelist)" type="danger">
                            删除
                        </el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page_no"
            :page-sizes="page_sizes" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper"
            :total="notice_count" style="padding: 10px 0;">
        </el-pagination>
        <!-- 发布通知 -->
        <el-dialog title="发布通知" :visible.sync="noticeAddDialog">
            <el-form ref="noticeAddForm" :model="noticeAddForm" label-width="80px" :rules="rules">
                <el-form-item label="标题" prop="notice_title">
                    <el-input v-model.trim="noticeAddForm.notice_title" autocomplete="off"
                        placeholder="请输入标题"></el-input>
                </el-form-item>
                <el-form-item label="详情" prop="notice_detail">
                    <el-input :rows="5" type="textarea" v-model.trim="noticeAddForm.notice_detail" autocomplete="off"
                        placeholder="请输入通知详情">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click.native.prevent.stop="addNotice('noticeAddForm')">确 定</el-button>
                <el-button @click.native.prevent.stop="resetForm('noticeAddForm')">重 置</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'NoticeList',
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
            // 表单验证规则
            rules: {
                notice_title: [{ required: true, trigger: ['blur', 'change'], validator: validataTitle }],
                notice_detail: [{ required: true, trigger: 'blur', validator: validataDetail }],
            },
            // 搜索通知列表的表单
            searchForm: {
                notice_title: '', // 通知的标题
            },
            noticeAddDialog: false, // 发布新通知的表单
            page_no: 1, // 当前页
            page_size: 8, // 初始化每页条目数
            page_sizes: [8, 16, 30, 50, 100], // 个数选择器
            // 发布通知的表单
            noticeAddForm: {
                notice_title: '',
                notice_detail: '',
            },
        }
    },
    computed: {
        ...mapGetters(['noticelist', 'notice_count'])
    },
    mounted() {
        this.getNoticeList()
    },
    methods: {
        // 重置表单
        resetForm(formname) {
            this.$refs[formname].resetFields()
        },
        // 查看详情
        showNoticeDetail(index, rows) {
            const notice_title = rows[index].notice_title
            const notice_detail = rows[index].notice_detail
            this.$alert(notice_detail, notice_title, {
                confirmButtonText: '确定',
                callback: action => {//eslint-disable-line no-unused-vars
                    return 'ok'
                }
            })
        },
        // 搜索
        search() {
            this.page_no = 1
            this.page_size = this.page_sizes[0]
            this.getNoticeList()
        },
        // 每页多少条目
        handleSizeChange(val) {
            this.page_size = val
            this.page_no = 1
            this.getNoticeList()
        },
        // 当前第几页
        handleCurrentChange(val) {
            this.page_no = val
            this.getNoticeList()
        },
        // 发布
        async addNotice(formname) {
            await this.$refs[formname].validate(async vaild => {
                if (vaild) {
                    try {
                        await this.$store.dispatch('addNotice', JSON.stringify(this.noticeAddForm))
                            .then(res => {
                                this.resetForm(formname)
                                this.noticeAddDialog = false
                                this.$message({ type: 'success', message: res })
                                // 重新获取通知列表
                                this.getNoticeList()
                            }).catch(err => this.$message({ type: 'warning', message: err.message }))
                    } catch (e) {
                        this.$message({ type: 'warning', message: e.message })
                    }
                }
            })
        },
        // 获取通知列表
        async getNoticeList() {
            try {
                const { page_no, page_size } = this
                let notice_title = this.searchForm.notice_title
                const reg = /^[0-9A-Za-z\u4e00-\u9fa5,!.*&^%#+=-_:;。，！「」——~、@]{1,12}$/
                if (!reg.test(notice_title)) {
                    if (notice_title !== '') {
                        notice_title = '1111111111111111111111111111111111111'
                    }
                }
                await this.$store.dispatch('getNoticeList', JSON.stringify({ page_no, page_size, notice_title }))
            } catch (e) {
                this.$message({ type: 'warning', message: e.message })
            }
        },
        // 删除通知
        async delNotice(index, rows) {
            const title = rows[index].notice_title
            const notice_id = rows[index].notice_id
            await this.$confirm(`是否删除通知 [${title}] ?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                try {
                    await this.$store.dispatch('delNotice', { notice_id })
                        .then(res => {
                            this.$message({ type: 'success', message: res })
                            // 重新获取通知列表
                            this.getNoticeList()
                        }).catch(err => this.$message({ type: 'warning', message: err.message }))
                } catch (e) {
                    this.$message({ type: 'warning', message: e.message })
                }
            }).catch(() => {
                this.$message({ type: 'info', message: '已取消' })
            })
        },
    },
}
</script>

<style lang="less" scoped>
.notice-list {

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