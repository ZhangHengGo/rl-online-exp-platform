"use strict";(self["webpackChunkvue_library"]=self["webpackChunkvue_library"]||[]).push([[749],{3749:function(e,t,r){r.r(t),r.d(t,{default:function(){return h}});var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"reserve-seat maxWH clearfix"},[e._m(0),t("main",{staticClass:"main"},[t("div",{directives:[{name:"show",rawName:"v-show",value:Object.keys(e.front_reserve_info).length>0,expression:"(Object.keys(front_reserve_info).length > 0)"}],staticClass:"exist-reserve"},[t("el-button",{attrs:{type:"text"},nativeOn:{click:function(t){return t.preventDefault(),t.stopPropagation(),e.goReserveInfo.apply(null,arguments)}}},[e._v("查看信息")])],1),t("el-button",{staticStyle:{margin:"15px 0"},attrs:{type:"primary"},nativeOn:{click:function(t){t.preventDefault(),t.stopPropagation(),e.userAddDialog=!0}}},[e._v("添加用户")]),t("div",{staticClass:"table"},[t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.front_seat_list,height:"350"}},[t("el-table-column",{attrs:{prop:"seat_floor",label:"座位楼层"}}),t("el-table-column",{attrs:{prop:"seat_no",label:"座位编号"}}),t("el-table-column",{attrs:{prop:"seat_status",label:"座位状态"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("el-tag",{attrs:{type:"空闲中"===r.row.seat_status?"info":"warning"}},[e._v(e._s(r.row.seat_status))])]}}])}),t("el-table-column",{attrs:{label:"操作","header-align":"center"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("div",{staticClass:"handler flex-center"},[t("el-button",{attrs:{type:"info",disabled:!("空闲中"===r.row.seat_status&&Object.keys(e.front_reserve_info).length<1)},nativeOn:{click:function(t){return t.preventDefault(),t.stopPropagation(),e.openReserveSeat(r.$index,e.front_seat_list)}}},[e._v(" 预约 ")])],1)]}}])})],1)],1),t("el-dialog",{attrs:{title:"预约座位",visible:e.reserveSeatdDialog},on:{"update:visible":function(t){e.reserveSeatdDialog=t}}},[t("el-form",{ref:"frontReserveSeatForm",attrs:{model:e.reserveSeatForm,"label-width":"110px",rules:e.rules,"hide-required-asterisk":""}},[t("el-form-item",{attrs:{label:"座位楼层",prop:"seat_floor"}},[t("el-input",{attrs:{disabled:"",autocomplete:"off"},model:{value:e.reserveSeatForm.seat_floor,callback:function(t){e.$set(e.reserveSeatForm,"seat_floor","string"===typeof t?t.trim():t)},expression:"reserveSeatForm.seat_floor"}})],1),t("el-form-item",{attrs:{label:"座位编号",prop:"seat_no"}},[t("el-input",{attrs:{disabled:"",autocomplete:"off"},model:{value:e.reserveSeatForm.seat_no,callback:function(t){e.$set(e.reserveSeatForm,"seat_no","string"===typeof t?t.trim():t)},expression:"reserveSeatForm.seat_no"}})],1),t("el-form-item",{attrs:{label:"预约结束时间",prop:"end_time"}},[t("el-time-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"选择时间"},model:{value:e.reserveSeatForm.end_time,callback:function(t){e.$set(e.reserveSeatForm,"end_time",t)},expression:"reserveSeatForm.end_time"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer",align:"center"},slot:"footer"},[t("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),t.stopPropagation(),e.reserveSeat("frontReserveSeatForm")}}},[e._v("确 定")]),t("el-button",{nativeOn:{click:function(t){return t.preventDefault(),t.stopPropagation(),e.resetForm("frontReserveSeatForm")}}},[e._v("重 置")])],1)],1)],1)])},a=[function(){var e=this,t=e._self._c;return t("div",{staticClass:"header flex"},[t("span",{staticClass:"title"})])}],o=(r(7658),r(3822));function n(e=""){const t=new Date(e);let r=t.getFullYear(),s=t.getMonth()+1<10?`0${t.getMonth()+1}`:`${t.getMonth()+1}`,a=t.getDate()<10?`0${t.getDate()}`:`${t.getDate()}`,o=t.getHours()<10?`0${t.getHours()}`:`${t.getHours()}`,n=t.getMinutes()<10?`0${t.getMinutes()}`:`${t.getMinutes()}`,i=t.getSeconds()<10?`0${t.getSeconds()}`:`${t.getSeconds()}`,l=`${r}-${s}-${a} ${o}:${n}:${i}`;return l}var i=n,l={name:"ReserveSeat",data(){const e=(e,t,r)=>{const s=/^(\d{4})-(\d{2})-(\d{2})[\s](\d{2}):(\d{2}):(\d{2})$/;t=this.getDateStr(t),t=i(t);let a=(new Date).getTime()+9e5,o=new Date(t);o=o.getHours()<10?`0${o.getHours()}`:`${o.getHours()}`,o=Number.parseInt(o),t.trim().length<=0?r(new Error("请选择结束时间")):s.test(t)?isNaN(o)?r(new Error("时间格式不对")):o<9?r(new Error("不能小于早上9点")):o>=22?r(new Error("不能大于晚上22点")):new Date(t).getTime()<a?r(new Error("预约时间应不少于15分钟")):r():r(new Error("时间格式不对"))};return{seat_floor:[{label:"全部",value:"0"},{label:"1楼",value:"1"},{label:"2楼",value:"2"},{label:"3楼",value:"3"},{label:"4楼",value:"4"},{label:"5楼",value:"5"}],searchForm:{seat_floor:""},reserveSeatdDialog:!1,rules:{end_time:[{required:!0,trigger:["blur","change"],validator:e}]},reserveSeatForm:{seat_id:0,seat_floor:"",seat_no:"",end_time:""}}},computed:{...(0,o.Se)(["front_seat_list","userinfo","front_reserve_info"])},mounted(){this.searchForm.seat_floor=this.seat_floor[0].value,this.getSeatlist(),this.getUserInfo()},methods:{getDateStr(e){let t=new Date(e);return e=t.getFullYear()+"-"+(t.getMonth()+1)+"-"+t.getDate()+" "+t.getHours()+":"+t.getMinutes()+":"+t.getSeconds(),e=e.toString(),e},async getSeatlist(){try{let e=isNaN(Number.parseInt(this.searchForm.seat_floor))?0:Number.parseInt(this.searchForm.seat_floor);await this.$store.dispatch("frontGetSeatList",{seat_floor:e})}catch(e){console.warn(e.message)}},resetForm(e){this.$refs[e].resetFields()},resetSearchForm(e){this.resetForm(e),this.searchForm.seat_floor=this.seat_floor[0].value},getFrontPath(e){let t=this.$route.path;return t.includes("front")?e:"front/"+e},goPath(e){this.$router.push(this.getFrontPath(e))},openReserveSeat(e,t){this.reserveSeatdDialog=!0;const r=t[e];this.reserveSeatForm={seat_id:r.seat_id,seat_floor:r.seat_floor,seat_no:r.seat_no,end_time:""}},async reserveSeat(e){await this.$refs[e].validate((async t=>{if(t)try{const t={user_id:this.userinfo.user_id,seat_id:this.reserveSeatForm.seat_id,end_time:i(this.getDateStr(this.reserveSeatForm.end_time))};await this.$store.dispatch("frontReserveSeat",JSON.stringify(t)).then((async t=>{await this.resetForm(e),await this.getUserInfo(),this.$notify({title:"成功",type:"success",message:t}),this.reserveSeatdDialog=!1,this.$bus.$emit("switchCheckNav","1-2"),this.goPath("reserveinfo")})).catch((e=>this.$message({type:"warning",message:e.message})))}catch(r){console.warn(r.message)}}))},goReserveInfo(){this.$bus.$emit("switchCheckNav","1-2"),this.goPath("reserveinfo")},async getUserInfo(){try{await this.$store.dispatch("getUserInfo",{user_id:this.userinfo.user_id})}catch(e){console.warn(e.message)}}}},u=l,c=r(1001),f=(0,c.Z)(u,s,a,!1,null,"10f3e4c0",null),h=f.exports}}]);
//# sourceMappingURL=749.1c030ce9.js.map