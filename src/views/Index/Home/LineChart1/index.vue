<template>
  <div class="LineChart maxWH" ref="lineCharts"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'LineChart',
  props: ['echarts_info'],
  data() {
    return {
      lineChart: null  // 用于存储 ECharts 图表实例
    };
  },
  mounted() {
    this.initChart();  // 初始化图表
    if (this.echarts_info && typeof this.echarts_info.value_function === 'string') {
      this.updateChart();  // 初始更新图表
    }
  },
  watch: {
    // 监听 echarts_info 的 value_function 属性
    'echarts_info.value_function': {
      handler(newValue) {
        // if (newValue && typeof newValue === 'string') {
        //   this.updateChart();
        // }
        this.updateChart();
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    initChart() {
      // 初始化 ECharts 实例
      this.lineChart = echarts.init(this.$refs.lineCharts);
    },
    updateChart() {
      // 更新或重新配置图表
      this.lineChart.setOption({
        title: {
          text: '序列回报',  // 主标题
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: this.echarts_info.episodeReward,
          type: 'line'
        }]
      });
    }
  }
};
</script>
