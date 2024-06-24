<template>
    <div class="LineChart maxWH" ref="lineCharts">
    </div>
</template>

<script>
import * as echarts from 'echarts'
export default {
    name: 'LineChart',
    props: ['echarts_info'],
    mounted() {
        const { nine_time_count, ten_time_count, eleven_time_count, twelve_time_count, thirteen_time_count, fourteen_time_count, fifteen_time_count, sixteen_time_count, seventeen_time_count, eighteen_time_count, nineteen_time_count, twenty_time_count, twenty_one_time_count } = this.echarts_info
        // 初始化echarts实例
        let lineCharts = echarts.init(this.$refs.lineCharts)
        // 配置数据
        lineCharts.setOption({
            // 图表的标题
            title: {
                // 主标题
                text: '座位预约时间段',
            },
            xAxis: {
                data: ['9点', '10点', '11点', '12点', '13点', '14点', '15点', '16点', '17点', '18点', '19点', '20点', '21点'],
            },
            yAxis: {
                show: false
            },
            // 系列
            series: [
                {
                    type: 'line',
                    smooth: true,
                    // data: [10, 33, 56, 23, 8, 12, 33, 15, 9, 3, 2, 11, 20],
                    data: [
                        nine_time_count, ten_time_count, eleven_time_count, twelve_time_count, thirteen_time_count, fourteen_time_count, fifteen_time_count, sixteen_time_count, seventeen_time_count, eighteen_time_count, nineteen_time_count, twenty_time_count, twenty_one_time_count
                    ],
                    // 拐点的样式的设置
                    itemStyle: {
                        opacity: 0
                    },
                    // 线条的样式
                    lineStyle: {
                        color: 'purple',
                    },
                    // 填充颜色设置
                    areaStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [
                                {
                                    offset: 0,
                                    color: 'purple', // 0% 处的颜色
                                },
                                {
                                    offset: 1,
                                    color: '#fff', // 100% 处的颜色
                                },
                            ],
                            global: false, // 缺省为 false
                        },
                    },
                },
            ],
        })

    },
}
</script>