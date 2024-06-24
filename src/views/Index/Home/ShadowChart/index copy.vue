<template>
  <!-- shap图 -->

   <div class="LineChart maxWH" ref="lineCharts">
   
   </div>   
    <!-- <div>{{echarts_list.shap}}</div> -->
   
</template>

<script>

import * as echarts from 'echarts'

import { mapGetters } from 'vuex'
export default {
 
  created(){
    this.option={
      xAxis: {
          type: 'value',
  },
        yAxis: {
          type: 'category',
          data: ['learning_rate', 'n_episode', 'n_time_step', 'seed', 'Q_value', 'other']
  },
        series: [
         {
          // data: [120,110,23,45,67,68],
          data:'',
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)' }
         }
  ]
      
    }
  },
  name: 'LineChart',
  props: ['echarts_info'],
  computed: {
        ...mapGetters(['echarts_list']),
        
        
    },
  mounted() {
    // this.getEchartsList();
    
    this.initEcharts();

    this.btnRefresh();
    this.$nextTick(() => {
    this.btnRefresh();
  });
   

  },
  
  methods: {
        // 获取用户信息
        // async getEchartsList(user_id) {
        //     try {
        //         await this.$store.dispatch('getEchartsList', 1)
                
        //     } catch (e) {
        //         console.warn(e.message)
        //     }
        // },

         async initEcharts(){
             // 初始化echarts实例
          let lineCharts = echarts.init(this.$refs.lineCharts)
            // 配置数据
          lineCharts.setOption(this.option)
         
          if (this.echarts_list && this.echarts_list.shap) {
          //  console.log(this.echarts_list.shap)
            var str = this.echarts_list.shap;
            
            this.option.series[0].data = str.split(',');
            console.log(this.option.series)
            
        }
           
          // this.option.series[0].data =  [120,110,23,45,67,68]
           
          // 配置数据
          lineCharts.setOption(this.option)

         
         },


         async btnRefresh(){
          if (this.echarts_list && this.echarts_list.shap) {
            this.option.series[0].data = this.echarts_list.shap;
            
            // console.log(this.option.series)
          }
    

         }

      }

  
  

}
</script>

<style lang="less" scoped>

// img{
// width:562; 
// height:274px;
// position: relative;

// }
.parent {
width: 562px;
height: 600px;
display: flex;
justify-content: center;
align-items: center;
}

img {
max-width: 100%;
max-height: 100%;
}

</style>
