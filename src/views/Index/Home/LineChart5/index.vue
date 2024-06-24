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
      lineChart: null, // 存储 ECharts 实例的变量
    };
  },
  mounted() {
    this.initChart(); // 组件挂载时初始化图表
    if (this.echarts_info && typeof this.echarts_info.reward_signal === 'string') {
      this.updateChartData(this.echarts_info.reward_signal); // 如果初始化时reward_signal已经可用，更新图表数据
    }
  },
  watch: {
    'echarts_info.value_loss': {
      handler(newVal) {
        // if (newVal) {
        //   this.updateChartData(newVal); // 监视reward_signal变化，更新图表
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
          text: '价值损失', // 图表主标题
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          type: 'value' // Y轴数据类型
        },
        series: [{
          type: 'line', // 图表类型为折线图
          data: [] // 初始为空数据数组
        }]
      });
    },
    updateChartData(reward_signal) {
      // 根据新的数据更新图表
      const data = reward_signal; // 将字符串分割成数组并转换为数字
      this.lineChart.setOption({
        series: [{
          data: data // 更新系列数据
        }]
      });
    }
  }
};
</script>
