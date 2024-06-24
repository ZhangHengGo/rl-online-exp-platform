"use strict";(self["webpackChunkvue_library"]=self["webpackChunkvue_library"]||[]).push([[635],{9635:function(t,e,a){a.r(e),a.d(e,{default:function(){return c}});var s=function(){var t=this,e=t._self._c;return e("div",[e("el-container",[e("el-header",[t._v(" "+t._s(this.expName)+" "),e("div",[e("el-popover",{attrs:{placement:"bottom",title:"标题",width:"200",trigger:"click"}},[e("div",[t._v(" 对比实验 "),e("el-select",{attrs:{placeholder:"请选择"},on:{change:t.handleSeriesTypeChange},model:{value:t.chartSeriesType,callback:function(e){t.chartSeriesType=e},expression:"chartSeriesType"}},t._l(t.chartSeriesTypes,(function(t){return e("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1),e("el-button",{attrs:{slot:"reference"},slot:"reference"},[t._v("添加")])],1),e("div",[t._v(" 所有实验 "),e("el-select",{attrs:{placeholder:"请选择"},model:{value:t.addExpId,callback:function(e){t.addExpId=e},expression:"addExpId"}},t._l(t.expList,(function(t){return e("el-option",{key:t.expId,attrs:{label:t.expName,value:t.expId,disabled:t.disabled}})})),1),e("el-button",{attrs:{slot:"reference"},on:{click:t.addExpDatas},slot:"reference"},[t._v("添加")])],1),e("el-button",{attrs:{slot:"reference"},slot:"reference"},[t._v("添加实验")])],1),t._l(t.showedExp,(function(a,s){return e("div",{key:s},[t._v(" "+t._s(a.expName)+" ")])}))],2)]),e("el-container",[e("el-aside",{attrs:{width:"200px"}},[e("div",[t._v("当前图表："+t._s(this.chartIndex))]),e("div",[t._v(" 选择数据 "),e("el-select",{attrs:{placeholder:"请选择"},on:{change:t.handleDataChange},model:{value:t.algDataId,callback:function(e){t.algDataId=e},expression:"algDataId"}},t._l(t.dataOptions,(function(t){return e("el-option",{key:t.id,attrs:{label:t.dataName,value:t.id}})})),1)],1),e("div",[t._v(" 修改标题 "),e("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.chartOptions[t.chartIndex].title.text,callback:function(e){t.$set(t.chartOptions[t.chartIndex].title,"text",e)},expression:"chartOptions[chartIndex].title.text"}}),e("el-button",{on:{click:t.handleTitleChange}},[t._v("确认")])],1),e("div",[t._v(" 图表样式 "),t._l(t.showedExp,(function(a,s){return e("div",{key:s},[t._v(" "+t._s(a.expName)+" "),e("el-select",{attrs:{placeholder:"请选择"},on:{change:function(e){return t.handleSeriesTypeChange(s)}},model:{value:t.chartOptions[t.chartIndex].series[s].type,callback:function(e){t.$set(t.chartOptions[t.chartIndex].series[s],"type",e)},expression:"chartOptions[chartIndex].series[index].type"}},t._l(t.chartSeriesTypes,(function(t){return e("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1)}))],2)]),e("el-main",[e("el-row",{attrs:{gutter:0}},[e("el-col",{attrs:{span:6}},[e("div",{ref:"chart0",staticClass:"grid-content bg-purple"}),e("el-button",{on:{click:function(e){return t.handleSelectChart(0)}}},[t._v(" 设置 ")])],1),e("el-col",{attrs:{span:6}},[e("div",{ref:"chart1",staticClass:"grid-content bg-purple"}),e("el-button",{on:{click:function(e){return t.handleSelectChart(1)}}},[t._v(" 设置 ")])],1)],1),e("el-row",{attrs:{gutter:0}},[e("el-col",{attrs:{span:6}},[e("div",{ref:"chart2",staticClass:"grid-content bg-purple"}),e("el-button",{on:{click:function(e){return t.handleSelectChart(2)}}},[t._v(" 设置 ")])],1),e("el-col",{attrs:{span:6}},[e("div",{ref:"chart3",staticClass:"grid-content bg-purple"}),e("el-button",{on:{click:function(e){return t.handleSelectChart(3)}}},[t._v(" 设置 ")])],1)],1)],1)],1)],1)],1)},i=[],l=(a(7658),a(6811)),n={name:"Chart",data(){return{chartsNum:4,algId:1,expId:1,expName:"实验1",chartIndex:1,algDataId:2,showedExp:[],dataOptions:[],addExpId:null,showedAlgData:[],expList:[],chartOptions:[{legend:{x:"center",y:"top"},title:{text:""},xAxis:{type:"category",data:["A","B","C","D","E"]},yAxis:{type:"value"},series:[]},{legend:{x:"center",y:"top"},title:{text:""},xAxis:{type:"category",data:["A","B","C","D","E"]},yAxis:{type:"value"},series:[]},{legend:{x:"center",y:"top"},title:{text:""},xAxis:{type:"category",data:["A","B","C","D","E"]},yAxis:{type:"value"},series:[]},{legend:{x:"center",y:"top"},title:{text:""},xAxis:{type:"category",data:["A","B","C","D","E"]},yAxis:{type:"value"},series:[]}],charts:[],chartSeriesType:"",chartSeriesTypes:[{label:"折线图",value:"line"},{label:"柱状图",value:"bar"},{label:"饼状图",value:"pie"},{label:"散点图",value:"scatter"}],titleText:""}},computed:{},created(){this.getExpList(),this.chartIndex=0},mounted(){this.initializeCharts(),this.getAlgDatas()},methods:{initializeCharts(){for(let t=0;t<4;t++){const e=this.$refs["chart"+t];if(e){const t=this.$echarts.init(e);this.charts.push(t)}}},handleDataChange(){var t=this.showedExp.map((t=>t.expId));(0,l.Gc)({expIdList:t,algDataId:this.algDataId}).then((t=>{if(1==t.data.status)alert(t.data.msg);else if(0==t.data.status){for(let e=0;e<t.data.data.length;e++)this.chartOptions[this.chartIndex].series[e].data=t.data.data[e];this.showedAlgData[this.chartIndex]=this.dataOptions.find((t=>t.id===this.algDataId)),this.chartOptions[this.chartIndex].title.text=this.showedAlgData[this.chartIndex].dataName,this.handleChartContentChange()}}))},handleSelectChart(t){this.chartIndex=t},handleTitleChange(){this.handleChartContentChange()},handleChartContentChange(){this.charts[this.chartIndex].setOption(this.chartOptions[this.chartIndex])},handleAllChartContentChange(){for(let t=0;t<4;t++)this.charts[t].setOption(this.chartOptions[t])},handleSeriesTypeChange(){this.handleChartContentChange()},getCEList(){},getExpList(){(0,l.Vh)({expId:this.expId,algId:this.algId}).then((t=>{this.expList=t.data.data}))},addExpDatas(){let t=this.showedAlgData.map((t=>t.id));(0,l.T8)({expId:this.addExpId,showedAlgDataIds:t}).then((t=>{if(this.addExpId==this.expId)var e=this.expName;else e=this.expList.find((t=>t.expId===this.addExpId)).expName;this.showedExp.push({expId:this.addExpId,expName:e});const a=t.data.data;for(let s=0;s<this.chartsNum;s++)this.chartOptions[s].series.push({name:e,type:"line",data:a[s]}),this.addExpId==this.expId&&(this.chartOptions[s].title.text=this.showedAlgData[s].dataName);this.addExpId!=this.expId&&(this.expList.find((t=>t.expId==this.addExpId)).disabled=!0),this.setxAxisData(!1),this.handleAllChartContentChange(),this.addExpId=null}))},getAlgDatas(){(0,l.DH)(this.algId).then((t=>{this.dataOptions=t.data.data;for(let e=0;e<this.chartsNum;e++)this.showedAlgData.push(this.dataOptions[e%this.dataOptions.length]);this.addExpId=this.expId,this.addExpDatas()}))},setxAxisData(t){if(t){var e=this.chartOptions[this.chartIndex].series.map((t=>t.data.length)),a=Math.max(e);this.chartOptions[this.chartIndex].xAxis.data=Array(a).fill().map(((t,e)=>e))}else for(let s=0;s<this.chartsNum;s++){e=this.chartOptions[s].series.map((t=>t.data.length)),a=Math.max(...e);this.chartOptions[s].xAxis.data=Array(a).fill().map(((t,e)=>e))}}}},r=n,h=a(1001),d=(0,h.Z)(r,s,i,!1,null,null,null),c=d.exports}}]);
//# sourceMappingURL=635.a0b0885f.js.map