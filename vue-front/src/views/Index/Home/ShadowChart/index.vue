<template>
  <div class="LineChart maxWH" ref="lineCharts"></div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "LineChart",
  props: ["echarts_info"],
  watch: {
    echarts_info: {
      deep: true,
      immediate: true,
      handler(newValue) {
        if (newValue && newValue.shap) {
          this.updateChart(newValue);
        }
      },
    },
  },

  methods: {
    updateChart(data) {
      const lineCharts = echarts.init(this.$refs.lineCharts);
      lineCharts.setOption({
        // 配置数据
        yAxis: {
          type: "category",
          data: [
            "learning_rate",
            "n_episode",
            "n_time_step",
            "seed",
            "Q_value",
            "other",
          ],
        },
        xAxis: {
          type: "value",
        },
        series: [
          {
            data: data.shap.split(","),
            type: "bar",
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
          },
        ],
      });
    },
  },
};
</script>