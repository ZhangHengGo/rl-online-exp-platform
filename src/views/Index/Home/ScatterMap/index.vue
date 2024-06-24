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
      lineChart: null  // 存储 ECharts 实例
    };
  },
  mounted() {
    this.initChart();  // 初始化图表实例
  },
  watch: {
    // 监视 echarts_info，包括深度监视其内部的变化
    echarts_info: {
      deep: true,
      immediate: true,
      handler(newVal) {
        // if (newVal && newVal.movement_decision && typeof newVal.movement_decision === 'string') {
        //   this.updateChart(newVal);
        // } else {
        //   console.error('echarts_info 或 movement_decision 未正确初始化');
        //   console.error(this.echarts_info);
        // }
        this.updateChart(newVal);
      }
    }
  },
  methods: {
    initChart() {
      // 确保只初始化一次
      if (!this.lineChart) {
        this.lineChart = echarts.init(this.$refs.lineCharts);
      }
    },
    updateChart(data) {
      // 更新或重新配置图表
      this.lineChart.setOption({
        title: {
          // 主标题配置
        },
        xAxis: {
          type: 'category',
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: data.targetsQvalue,
          type: 'line',
          lineStyle: {
            // width: 0,  // 线宽设为0，不显示线
            type: 'none'  // 线型设为'none'
          }
        }]
      });
    }
  }
}
</script>
