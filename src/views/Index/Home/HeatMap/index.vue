<template>
  <div class="LineChart maxWH" ref="lineCharts"></div>
</template>

<script>
import * as echarts from "echarts";

export default {
  name: "LineChart",
  props: ["echarts_info"],
  data() {
    return {
      lineChart: null, // 存储 ECharts 实例
    };
  },
  mounted() {
    this.initChart();
  },
  watch: {
    // 监听 echarts_info 的变化，包括它的深度变化
    echarts_info: {
      deep: true,
      immediate: true,
      handler(newVal) {
        if (newVal && newVal.qvalue) {
          this.updateChart(newVal);
        } else {
          console.error("echarts_info 未正确初始化");
          console.error(newVal);
        }
      }
    }
  },
  methods: {
    initChart() {
      // 初始化 ECharts 实例
      this.lineChart = echarts.init(this.$refs.lineCharts);
    },
    updateChart(data) {
      const steps = [
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"
      ];
      const actions = ["0", "1"];
      
      let parsedData = JSON.parse("[" + data.qvalue.replace(/\]\[/g, "],[") + "]").map(
        function (item) {
          return [item[1], item[0], item[2] || "-"];
        }
      );

      this.lineChart.setOption({
        tooltip: {
          position: "top",
        },
        grid: {
          height: "50%",
          top: "10%",
        },
        xAxis: {
          type: "category",
          data: steps,
          splitArea: {
            show: true,
          },
        },
        yAxis: {
          type: "category",
          data: actions,
          splitArea: {
            show: true,
          },
        },
        visualMap: {
          min: 0,
          max: 1,
          calculable: true,
          orient: "horizontal",
          left: "center",
        },
        series: [
          {
            name: "Punch Card",
            type: "heatmap",
            data: parsedData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
    }
  }
};
</script>
