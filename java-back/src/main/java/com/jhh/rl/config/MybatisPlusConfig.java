package com.jhh.rl.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor; //拦截器
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //配置类注解
public class MybatisPlusConfig {
    @Bean //创建一个Bean，生命周期由Spring管理，使用@Autowired注入
    //构建分页查询
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor(); //实例化一个拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //添加分页拦截器，指定数据库类型为MySQL
        return interceptor;
    }
}
