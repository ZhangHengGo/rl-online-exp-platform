//package com.jhh.rl.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.jhh.rl.dto.response.env.EnvParamResponse;
//import com.jhh.rl.entity.EnvParam;
//import com.jhh.rl.entity.User;
//import com.jhh.rl.mapper.EnvParamMapper;
//import com.jhh.rl.service.EnvService;
//import com.jhh.rl.service.ExperimentService;
//import com.jhh.rl.utils.Result;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class EnvServiceImpl implements EnvService {
//
//    @Resource
//    private EnvParamMapper envParamMapper;
//
//    @Override
//    public Result<Map<String, Object>> getEnvParams(Integer envId) {
//
//        // 根据envId查询所有参数
//        QueryWrapper<EnvParam> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("env_id", envId);
//        List<EnvParam> envParams = envParamMapper.selectList(queryWrapper);
//        return Result.ok("查询环境参数成功", buildTree(envParams, null));
//    }
//
//    private Map<String, Object> buildTree(List<EnvParam> envParams, Integer parentId) {
//        Map<String, Object> tree = new HashMap<>();
//        for (EnvParam param : envParams) {
//            if ((parentId == null && param.getParentId() == null) ||
//                    (parentId != null && parentId.equals(param.getParentId()))) {
//                // 没有子节点
//                if(param.getType() != 3){
//                    EnvParamResponse dto = new EnvParamResponse();
//                    dto.setType(param.getType());
//                    if(param.getType() == 0){
//                        dto.setMin(param.getMin());
//                        dto.setMax(param.getMax());
//                    }
//                    else if(param.getType() == 1){
//                        String[] options = param.getOptions().split(",");
//                        dto.setOptions(options);
//                    }
//                    tree.put(param.getName(), dto);
//                }
//                // 有子节点
//                else{
//                    Map<String, Object> childTree = buildTree(envParams, param.getId());
//                    tree.put(param.getName(), childTree);
//                }
//            }
//        }
//        return tree;
//    }
//}
