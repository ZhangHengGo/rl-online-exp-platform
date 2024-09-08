//package com.jhh.rl.controller;
//
//
//import com.jhh.rl.service.impl.NoticeReadHistoryServiceImpl;
//import com.jhh.rl.utils.Result;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//public class NoticeReadController {
//
//    @Resource
//    private NoticeReadHistoryServiceImpl noticeReadService;
//
////    @GetMapping("/front/notice/set_read")
////    public Result<Void> setRead(Integer notice_id)
////    {
////        return noticeReadService.setRead(user_id, notice_id);
////    }
//
//    @GetMapping("/front/notice/all_set_read")
//    public Result<Void> allsetread(@RequestParam("user_id") Integer id){
//        return noticeReadService.setAllRead(id);
//    }
//}
