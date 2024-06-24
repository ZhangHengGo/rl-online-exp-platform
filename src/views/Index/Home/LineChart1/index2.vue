<template>
    <!-- <div class="LineChart maxWH" ref="lineCharts">
    </div> -->
    <!-- <div class="parent" :style="{backgroundImage:'url' + bgImageUrl + ')'}"> -->
      <!-- {{ bgImageUrl }} -->
      <div class="LineChart maxWH" :style="{backgroundImage:'url' + bgImageUrl + ')'}">
        <!-- <img :src="bgImageUrl" >  -->
      </div>
       
      <!-- <img src="../../../../assets/picture/1.jpg" >  -->
      <!-- <transition name="fade">
        <img v-bind:src="imageUrl">
      </transition> -->
    <!-- </div> -->
     
    
    
       
       

</template>

<script>

// import * as echarts from 'echarts'


export default {
    
    data(){
      return{
        bgImageUrl:'../../../../assets/picture/1.jpg',
        bgImageIndex:0,
        bgImages:['1.jpg','2.jpg','3.jpg','4.jpg']
      }
    },
    mounted() {
      setInterval(() =>{
        this.bgImageIndex = (this.bgImageIndex+1)% this.bgImages.length
        this.bgImageUrl = require('../../../../assets/picture/'+this.bgImages[this.bgImageIndex])
        
          }, 5000);
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
  position: relative;
  max-width: 100%;
  max-height: 100%;
}

</style>
