<template>
  <div>
    <el-container>
      <el-header>
        {{ this.expName }}
        <div>
          <el-popover
            placement="bottom"
            title="标题"
            width="200"
            trigger="click"
          >
            <div>
              对比实验
              <el-select
                v-model="chartSeriesType"
                placeholder="请选择"
                @change="handleSeriesTypeChange"
              >
                <el-option
                  v-for="item in chartSeriesTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
              <el-button slot="reference">添加</el-button>
            </div>
            <div>
              所有实验
              <el-select
                v-model="addExpId"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in expList"
                  :key="item.expId"
                  :label="item.expName"
                  :value="item.expId"
                  :disabled="item.disabled"
                >
                </el-option>
              </el-select>
              <el-button @click="addExpDatas" slot="reference">添加</el-button>
            </div>
            <el-button slot="reference">添加实验</el-button>
          </el-popover>
          <div v-for="(item, index) in showedExp" :key="index">
            {{ item.expName }}
          </div>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <div>当前图表：{{ this.chartIndex }}</div>
          <div>
            选择数据
            <el-select
              v-model="algDataId"
              placeholder="请选择"
              @change="handleDataChange"
            >
              <el-option
                v-for="item in dataOptions"
                :key="item.id"
                :label="item.dataName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </div>
          <div>
            修改标题
            <el-input v-model="chartOptions[chartIndex].title.text" placeholder="请输入内容"></el-input>
            <el-button @click="handleTitleChange">确认</el-button>
          </div>
          <div>
            图表样式
            <div
              v-for="(item, index) in showedExp"
              :key="index"
            >
              {{ item.expName }}
              <el-select
                v-model="chartOptions[chartIndex].series[index].type"
                placeholder="请选择"
                @change="handleSeriesTypeChange(index)"
              >
                <el-option
                  v-for="item in chartSeriesTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
          </div>
        </el-aside>
        <el-main>
          <el-row :gutter="0">
            <el-col :span="6"
              ><div
                class="grid-content bg-purple"
                :ref="'chart' + 0"
              ></div
            >
            <el-button @click="handleSelectChart(0)"> 设置 </el-button>
            </el-col>
            <el-col :span="6"
              ><div
                class="grid-content bg-purple"
                :ref="'chart' + 1"
              ></div
            ><el-button @click="handleSelectChart(1)"> 设置 </el-button></el-col>
          </el-row>
          <el-row :gutter="0">
            <el-col :span="6"
              ><div
                class="grid-content bg-purple"
                :ref="'chart' + 2"
              ></div
            ><el-button @click="handleSelectChart(2)"> 设置 </el-button></el-col>
            <el-col :span="6"
              ><div
                class="grid-content bg-purple"
                :ref="'chart' + 3"
              ></div
            ><el-button @click="handleSelectChart(3)"> 设置 </el-button></el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import {
  reqGetAlgDatas,
  reqGetExpsData,
  reqGetExpList,
  reqGetExpDatas,
} from "@/api";
export default {
  name: "Chart",

  data() {
    return {
      chartsNum: 4,
      // 算法id
      algId: 1,
      // 实验id
      expId: 1,
      // 实验名称
      expName: "实验1",
      // 目前操作的图表编号
      chartIndex: 1,
      // 目前图表展示的数据
      algDataId: 2,
      // 正在展示的实验
      showedExp: [],
      dataOptions: [],
      addExpId: null,
      showedAlgData: [],

      expList: [],

      chartOptions: [
        {
          legend: { x: "center", y: "top"},
          title: { text: "" },
          xAxis: { type: "category", data: ["A", "B", "C", "D", "E"] },
          yAxis: { type: "value" },
          series: [],
        },
        {
          legend: { x: "center", y: "top"},
          title: { text: "" },
          xAxis: { type: "category", data: ["A", "B", "C", "D", "E"] },
          yAxis: { type: "value" },
          series: [],
        },
        {
          legend: { x: "center", y: "top"},
          title: { text: "" },
          xAxis: { type: "category", data: ["A", "B", "C", "D", "E"] },
          yAxis: { type: "value" },
          series: [],
        },
        {
          legend: { x: "center", y: "top"},
          title: { text: "" },
          xAxis: { type: "category", data: ["A", "B", "C", "D", "E"] },
          yAxis: { type: "value" },
          series: [],
        },
      ],

      charts: [],

      chartSeriesType: "",
      chartSeriesTypes: [
        {
          label: "折线图",
          value: "line",
        },
        {
          label: "柱状图",
          value: "bar",
        },
        {
          label: "饼状图",
          value: "pie",
        },
        {
          label: "散点图",
          value: "scatter",
        },
      ],

      titleText: "",

    };
  },

  computed: {
  },
  created() {
    this.getExpList();
    this.chartIndex = 0
  },
  mounted() {
    this.initializeCharts();
    this.getAlgDatas();
  },

  methods: {

    // 初始化四个表格
    initializeCharts() {
      for (let i = 0; i < 4; i++) {
        const chartContainer = this.$refs["chart" + i]; // 获取DOM元素
        if (chartContainer) {
          const chart = this.$echarts.init(chartContainer); // 将DOM初始化为图表
          this.charts.push(chart);
        }
      }
    },

    // 改变表格中的数据
    handleDataChange() {
      var expIdList = this.showedExp.map(item => item.expId)


      // 请求数据
      reqGetExpsData({ expIdList: expIdList, algDataId: this.algDataId }).then(
        (response) => {
          if (response.data.status == 1) {
            alert(response.data.msg);
          } else if (response.data.status == 0) {
            for(let i = 0;i<response.data.data.length;i++){
              this.chartOptions[this.chartIndex].series[i].data =response.data.data[i];
            }
            this.showedAlgData[this.chartIndex] = this.dataOptions.find(item => item.id === this.algDataId);
            this.chartOptions[this.chartIndex].title.text = this.showedAlgData[this.chartIndex].dataName
            //this.setxAxisData(true)
            this.handleChartContentChange();
          }
        }
      );
    },

    // 切换图表
    handleSelectChart(chartIndex) {
      this.chartIndex = chartIndex;
    },

    // 修改图表标题
    handleTitleChange() {
      this.handleChartContentChange();
    },

    // 更新图表内容
    handleChartContentChange() {
      this.charts[this.chartIndex].setOption(
        this.chartOptions[this.chartIndex]
      );
    },

    handleAllChartContentChange() {
      for(let i=0;i<4;i++){
        this.charts[i].setOption(
          this.chartOptions[i]
        );
      }
    },

    // 改变图表样式
    handleSeriesTypeChange() {
      this.handleChartContentChange();
    },

    // 获取对比实验列表
    getCEList() {},

    // 获取实验列表
    getExpList() {
      reqGetExpList({ expId: this.expId, algId: this.algId }).then(
        (response) => {
          this.expList = response.data.data;
        }
      );
    },

    addExpDatas() {
      let showedAlgDataIds = this.showedAlgData.map(item => item.id);
      reqGetExpDatas({ expId: this.addExpId, showedAlgDataIds: showedAlgDataIds }).then(
        (response) => {
          // 初始化的时候
          if(this.addExpId == this.expId){
            var expName = this.expName
          }
          // 添加对比试验的时候
          else{
            var expName = this.expList.find(exp => exp.expId === this.addExpId).expName;
          }
          this.showedExp.push({expId: this.addExpId, expName: expName})
          const datas = response.data.data
          for(let i = 0; i<this.chartsNum; i++){
            this.chartOptions[i].series.push({name:expName, type: "line", data:datas[i]})
            if(this.addExpId == this.expId){
              this.chartOptions[i].title.text = this.showedAlgData[i].dataName
            }
          }
          if(this.addExpId != this.expId){
            this.expList.find(item => item.expId == this.addExpId).disabled = true
          }
          this.setxAxisData(false)
          this.handleAllChartContentChange()
          this.addExpId = null
        }
      );
    },

    // 
    getAlgDatas() {
      reqGetAlgDatas(this.algId).then((response) => {
        this.dataOptions = response.data.data;
        for(let i = 0;i<this.chartsNum;i++){
          this.showedAlgData.push(this.dataOptions[i % this.dataOptions.length])
        }
        this.addExpId = this.expId
        this.addExpDatas()
      });
    },

    setxAxisData(single) {
      if(single){
        var lengths = this.chartOptions[this.chartIndex].series.map(item => item.data.length)
        var max = Math.max(lengths)
        this.chartOptions[this.chartIndex].xAxis.data = Array(max).fill().map((_, idx) => idx);
      } else{
        for(let i = 0;i<this.chartsNum;i++){
          var lengths = this.chartOptions[i].series.map(item => item.data.length)
          var max = Math.max(...lengths)
          this.chartOptions[i].xAxis.data = Array(max).fill().map((_, idx) => idx);
        }
      }
    }
  },
};
</script>

<style>
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  width: 500px;
  height: 400px;
  margin: 20px auto;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>