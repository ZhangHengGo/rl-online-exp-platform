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
      lineChart: null, // 存储 ECharts 实例
    };
  },
  mounted() {
    this.initChart(); // 初始化图表
    if (this.echarts_info && typeof this.echarts_info.learning_curve === 'string') {
      this.updateChartData(this.echarts_info.learning_curve); // 如果初始化时 learning_curve 已经可用，更新图表数据
    }
  },
  watch: {
    // 监听 echarts_info.learning_curve 的变化
    'echarts_info.policy_loss': {
      handler(newVal) {
        // if (newVal && typeof newVal === 'string') {
        //   this.updateChartData(newVal); // 在 learning_curve 更新时更新图表
        // }
        this.updateChartData(newVal);
      },
      immediate: true, // 立即以当前的值触发回调
      deep: true // 深度监听对象内部变化
    }
  },
  methods: {
    initChart() {
      // 初始化 ECharts 实例
      this.lineChart = echarts.init(this.$refs.lineCharts);
      this.lineChart.setOption({
        title: {
          text: '策略损失', // 主标题
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          type: 'line',
          data: [] // 初始化为空数据数组
        }]
      });
    },
    updateChartData(learning_curve) {
      // 根据新的数据更新图表
      const data = learning_curve; // 将字符串分割成数组并转换为数字
      this.lineChart.setOption({
        series: [{
          data: data // 更新系列数据
        }]
      });
    }
  }
};
</script>
