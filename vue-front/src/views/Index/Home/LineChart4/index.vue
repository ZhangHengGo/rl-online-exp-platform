<template>
    <!-- <div class="LineChart maxWH" ref="lineCharts">
    </div> -->
    <!-- <div class="parent" :style="{backgroundImage:'url' + bgImageUrl + ')'}"> -->
      <!-- {{ bgImageUrl }} -->
      
      <!-- <img :src="bgImageUrl" >  -->
       <!-- <img :src="'../../../../assets/picture/'+images[currentIndex]" alt="image" />  -->
       <!-- <img :src="images[currentIndex]" alt="image" />  -->
       <!-- <img :src="imageUrl + images[currentIndex]" alt="image" /> -->
      <!-- <img src="../../../../assets/picture/1.jpg" >  -->
      <!-- <transition name="fade">
        <img v-bind:src="imageUrl">
      </transition> -->
    <!-- </div> -->
     <div class="container">

      <img :src="currentImageUrl" alt="image" />
        <!-- <p>111</p> -->
       <!-- {{ currentImageUrl }} -->
     </div>
    
       

</template>

<script>

// import * as echarts from 'echarts'


// export default {
    
//     data(){
//       return{
//         bgImageUrl:'../../../../assets/picture/1.jpg',
//         bgImageIndex:0,
//         bgImages:['1.jpg','2.jpg','3.jpg','4.jpg']
//       }
//     },
//     mounted() {
//       setInterval(() =>{
//         this.bgImageIndex = (this.bgImageIndex+1)% this.bgImages.length
//         this.bgImageUrl = require('../../../../assets/picture/'+this.bgImages[this.bgImageIndex])
        
//           }, 5000);
// }
    
// }
export default {
  data() {
    return {
      images: ["1.jpg", "2.jpg", "3.jpg","4.jpg"],
      // images1: ["0", "10", "20","30","40","50","60","70","80","90","100","110","120","130","140","150","160","170","180","190","200"],
      currentIndex: 0,
      index:0,
      intervalId: null,
      delay: 3000, // 图片切换时间间隔，单位毫秒
      imageUrl: "../../../../assets/picture/" // 图片路径的共同部分
    };
  },
  computed: {
    currentImageUrl() {
      // return require(`${this.imageUrl}${this.images[this.currentIndex]}`);
      // console.log('cartpole'+this.images[this.currentIndex])
      // console.log('cartpole'+this.index+'.gif')
      // return require('../../../../assets/picture/1'+'cartpole'+this.images1[this.currentIndex]+'.gif')
      // return require('../../../../assets/picture/1/'+'cartpole'+this.index+'.gif')
      return require('../../../../assets/picture/2023/02/11/01/'+'cartpole'+this.index+'.gif')


      // return require('../../../../assets/picture/'+this.images[this.currentIndex])
      
    }
  },
  mounted() {
    this.intervalId = setInterval(() => {
      // this.currentIndex = (this.currentIndex + 1) % this.images.length;
      this.index = (this.index + 10) % 1400;
    }, this.delay);
  },
  beforeDestroy() {
    clearInterval(this.intervalId);
  }
};
   

</script>

<style lang="less" scoped>

// img{
// width:562; 
// height:274px;
// position: relative;

// }
.container {
  width: 100%;
  height: 100%;
  overflow: hidden; 
}

.container img {
  max-width: 100%;
  max-height: 100%;
}

</style>
