<template>
  <div class="home maxWH">
    <div class="back-home maxWH clearfix">
      <!-- 头部 -->
      <div class="head-nav-f fixed">
        <div class="userinfo flex-center">
          <!-- <img src="../../assets/avatar.gif" alt="avatar" class="avatar"> -->
          <!-- 下拉菜单 -->
          <span class="drop-down-list">
            <el-dropdown>
              <span class="el-dropdown-link" style="color: #fff">
                <span class="username text-ellipsis">{{ getUsername }}</span>

                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>

              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <!-- {{ testdetail[0].test_id }} -->
                  episode：{{ item }}
                </el-dropdown-item></el-dropdown-menu
              >
            </el-dropdown>
          </span>
        </div>
      </div>
      <div>
        轨迹总数：252条
      </div>
      <div>
        <el-input v-model="episodeId" placeholder="请输入内容" @change="getEchartsList"></el-input>
      </div>
      <div class="header flex-center">
        <!-- 左侧两个卡片 -->
        <div class="left-card flex-center">
          <div class="title flex-center">
            <span>环境状态</span>
          </div>
          <LineChart4 :echarts_info="echarts_list"></LineChart4>

          <div class="title flex-center">
            <span>目标Q值</span>
          </div>
          <ScatterMap :echarts_info="echarts_list"></ScatterMap>
        </div>
        <!-- 右侧两个卡片 -->
        <div class="right-card flex-center">
          <div class="title flex-center">
            <span>shap</span>
          </div>
          <ShadowChart :echarts_info="echarts_list"></ShadowChart>

          <div class="title flex-center">
            <span>熵</span>
          </div>
          <!-- <HeatMap :echarts_info="echarts_list"></HeatMap> -->
          <LineChart6 :echarts_info="echarts_list"></LineChart6>
        </div>
      </div>
      <!-- 底部 -->
      <div class="floor flex-center">
        <!--价值函数-->
        <div class="left-line">
          <LineChart1 :echarts_info="echarts_list"></LineChart1>
        </div>
        <!-- 奖励信号 -->
        <div class="left-line">
          <LineChart2 :echarts_info="echarts_list"></LineChart2>
        </div>
        <!-- 学习曲线 -->
        <div class="left-line">
          <LineChart3 :echarts_info="echarts_list"></LineChart3>
        </div>
        <div class="left-line">
          <LineChart5 :echarts_info="echarts_list"></LineChart5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { user_identity_admin, user_identity_user } from "@/config";
import LineChart1 from "../../Home/LineChart1";
import LineChart2 from "../../Home/LineChart2";
import LineChart3 from "../../Home/LineChart3";
import LineChart4 from "../../Home/LineChart4";
import LineChart5 from "../../Home/LineChart5";
import LineChart6 from "../../Home/LineChart6";
import PieChart from "../../Home//PieChart";
import HeatMap from "../../Home//HeatMap";
import ScatterMap from "../../Home//ScatterMap";
import ShadowChart from "../../Home//ShadowChart";
import { getExpEpisodeData } from "@/api";

export default {
  name: "Home",
  components: {
    LineChart1,
    PieChart,
    LineChart2,
    LineChart3,
    LineChart5,
    LineChart6,
    HeatMap,
    ScatterMap,
    ShadowChart,
    LineChart4,
  },
  data() {
    return {
      user_identity_admin: user_identity_admin,
      user_identity_user: user_identity_user,
      expId: 3,
      episodeId: 1,
    };
  },

  computed: {
    ...mapGetters([
      "identity",
      "echarts_info",
      "get_time_arr",
      "get_time_val_arr",
      "get_time_dict",
      "userinfo",
      "testdetail",
      "echarts_list",
    ]),
    getUsername() {
      return this.userinfo.username;
    },
    getEpisodes() {
      return this.userinfo.username;
    },
  },

  mounted() {
    this.getEchartsList();
  },
  created() {
    // this.getExpEpisodeData()
    // this.$watch(
    // () => this.$route.params,
    // (toParams, previousParams) => {
    //     // 当$route.params发生变化时，执行操作
    //     if (toParams !== previousParams) {
    //     // 执行this.$router.push
    //     // 执行this.getEchartsList()
    // this.getEchartsList();
    //     //   location.reload()
    //     }
    // }
    // );
  },
  methods: {
    // getExpEpisodeData(){
    //     getExpEpisodeData({expId: this.expId, episodeId: this.episodeId}).then( response => {
    //         console.log(response)
    //     })
    // },

    async getEchartsList() {
      if(this.episodeId > 252){
        alert("没有这么多轨迹了！");
        return;
      }
      let data = {};

      //   data.test_detail_id = this.$route.query.test_detail_id;
      //   data.episode_id = this.$route.query.episode_id;
      data.expId = this.expId;
      data.episodeId = this.episodeId;
      try {
        await this.$store.dispatch("getEchartsList", data);
      } catch (e) {
        console.warn(e.message);
      }
    },
  },
};
</script>

<style lang="less" scoped>
.back-home {
  box-sizing: border-box;
  padding: 0.1rem;

  .header {
    width: 100%;
    height: 100%;
    justify-content: space-between;

    .left-card {
      width: 40%;
      height: 100%;
      flex-flow: column;
      justify-content: space-between;

      .box-card {
        width: 100%;
        height: 100%;

        .title {
          font-size: 0.13rem;
          color: rgb(160, 180, 180);
          justify-content: space-between;
        }
      }
    }

    .right-card {
      width: 60%;
      height: 100%;
      flex-flow: column;
      justify-content: space-between;

      .box-card {
        width: 100%;
        height: 100%;

        .title {
          font-size: 0.13rem;
          color: rgb(160, 180, 180);
          justify-content: space-between;
        }

        .info {
          font-size: 0.22rem;
        }
      }
    }
  }

  .floor {
    width: 100%;
    height: 55%;
    margin-top: 2%;
    border-radius: 0.05rem;
    box-shadow: 0 0 0.1rem rgba(0, 0, 0, 0.1);

    .left-line {
      width: 70%;
      height: 100%;
      box-sizing: border-box;
      padding: 0.1rem;
    }

    .right-ranking {
      width: 30%;
      height: 100%;
      box-sizing: border-box;
      padding: 0.1rem 0.1rem 0.1rem 0;

      .ranking-title {
        font-size: 0.1rem;
      }

      .ranking-list {
        box-sizing: border-box;
        padding-top: 0.1rem;
        width: 100%;

        .ranking-item {
          width: 100%;
          font-size: 0.1rem;
          box-sizing: border-box;
          padding-bottom: 0.1rem;

          .item-num {
            width: 15%;

            span {
              display: flex;
              justify-content: center;
              align-items: center;
              width: 0.13rem;
              height: 0.13rem;
            }

            .radius {
              color: #fff;
              border-radius: 50%;
              background-color: #333;
            }
          }

          .item-floor {
            width: 50%;
          }

          .item-count {
            width: 35%;
          }
        }
      }
    }
  }
}

.front-home {
  box-sizing: border-box;
  padding: 10px;
  flex-flow: column;
  // justify-content: space-evenly;

  .title {
    font-size: 50px;
    color: #555;
    font-weight: 900;
  }

  .intro {
    margin: 50px 0;
    font-size: 18px;
    flex-flow: column;
  }
}
.head-nav-f {
  z-index: 1;
  width: 10%;
  height: 50px;
  min-width: 100px;
  box-sizing: border-box;
  padding: 5px 10px;
  background: #324057;
  color: #fff;
  // border-bottom: 1px solid #1f2d3d;
  overflow: hidden;
  margin-top: 61px;
}
</style>