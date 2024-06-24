<template>
    <div class="PieChart maxWH" ref="PieCharts">
    </div>
</template>

<script>
import * as echarts from 'echarts'
export default {
    name: 'PieChart',
    props: ['echarts_info'],
    mounted() {
        const { one_floor_count, two_floor_count, three_floor_count, four_floor_count, five_floor_count } = this.echarts_info
        // 初始化echarts实例
        let PieCharts = echarts.init(this.$refs.PieCharts)
        // 配置数据
        PieCharts.setOption({
            color: ['#4F9AFF', '#5ADBDC', '#9AECDB', '#BDC581', '#B33771'],
            title: {
                text: '用户实验占比数'
            },
            series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: ['40%', '70%'],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 10,
                        borderColor: '#fff',
                        borderWidth: 2,
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c}'
                            },
                            labelLine: { show: true }
                        }
                    },
                    label: {
                        show: true,
                        position: 'center',
                        formatter: '{b} : {c}'
                    },
                    data: [
                        { value: one_floor_count, name: '用户1' },
                        { value: two_floor_count, name: '用户2' },
                        { value: three_floor_count, name: '用户3' },
                        { value: four_floor_count, name: '用户4' },
                        { value: five_floor_count, name: '用户5' }
                    ]
                },
            ]
        })
    },
}
</script>