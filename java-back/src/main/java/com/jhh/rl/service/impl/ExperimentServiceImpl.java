package com.jhh.rl.service.impl;

import com.jhh.rl.controller.TestRequestClass.AddTest;
import com.jhh.rl.dto.request.experiment.UpdateExpInfo;
import com.jhh.rl.entity.Experiment;
import com.jhh.rl.mapper.ExperimentMapper;
import com.jhh.rl.service.ExperimentService;
import com.jhh.rl.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExperimentServiceImpl implements ExperimentService {
//    @Resource
//    private ExperimentMapper experimentMapper;
//    @Override
//    public Result addtest(AddTest addTest) {
//        if (addTest == null || addTest.getTest_status() == null || addTest.getAccount() == null
//                || addTest.getUsername() == null || addTest.getCreate_name() == null) {
//            return Result.fail("输入无效");
//        }
//
//
//        Experiment test = new Experiment();
//        test.setTestName(addTest.getAccount());
//        test.setTestStatus(addTest.getTest_status());
//        test.setCreateName(addTest.getCreate_name());
//
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//
//        test.setCreateTime(sdf.format(new Date()));
//
//        int i = experimentMapper.insert(test);
//
//        if(i==0) return Result.fail("创建实验失败");
//
//        return Result.ok("创建实验成功");
//
//
//    }
//
//    @Override
//    public Result<List<TestDTO>> gettestlist(TestList testList) {
//        if(testList==null) return Result.fail("查询实验列表失败");
//        IPage<Test> ipage=new Page<>(testList.getPage_no(), testList.getPage_size());
//        QueryWrapper<Test>queryWrapper=new QueryWrapper<>();
//
//        String account=testList.getAccount();
//        String username= testList.getUsername();
//        String test_status=testList.getTest_status();
//        String create_name=testList.getCreate_name();
//
//
//        if(test_status.equals("全部")||test_status.isEmpty())
//        {
//            queryWrapper.like("test_name",account)
//                    .like("user_name",username)
//                    .like("create_name",create_name);
//        }else
//        {
//            queryWrapper.like("test_name",account)
//                    .like("user_name",username)
//                    .like("create_name",create_name)
//                    .like("test_status",test_status);
//        }
//
//        queryWrapper.orderByDesc("id");
//
//        ipage=testMapper.selectPage(ipage,queryWrapper);
//        List<Test>lists=ipage.getRecords();
//
//        if(lists.isEmpty()) return Result.fail("查询实验列表失败");
//
//        List<TestDTO> testDTOS=new ArrayList<>();
//        for(Experiment list:lists){
//            TestDTO testList1 = new TestDTO();
//            testList1.setTest_status(list.getTestStatus());
//            testList1.setTest_name(list.getTestName());
//            testList1.setTest_id(list.getId());
//            testList1.setCreate_time(list.getCreateTime());
//            System.out.println(testList1);
//
//            testDTOS.add(testList1);
//
//        }
//
//        return Result.ok("查询实验列表成功",testDTOS,testDTOS.size());
//    }
//
//    @Override
//    public Result updatetestinfo(UpdateExpInfo updateTestInfo) {
////        if(updateTestInfo.getTest_id()==0) return Result.fail("无效输入");
////        Test test = testMapper.selectById(updateTestInfo.getTest_id());
////
////        test.setTestName(updateTestInfo.getTest_name());
////        test.setUserName(updateTestInfo.getUser_name());
////        test.setTestStatus(updateTestInfo.getTest_status());
////
////        int i = testMapper.updateById(test);
////        if(i==0) return Result.fail("实验信息修改失败");
//
//        return Result.ok("实验信息修改成功");
//
//    }
}
