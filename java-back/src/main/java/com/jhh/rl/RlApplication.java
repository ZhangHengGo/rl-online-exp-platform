package com.jhh.rl; //声明了类所在的包名

//导入SpringBoot框架中的两个类
import org.springframework.boot.SpringApplication; //启动Spring应用程序
import org.springframework.boot.autoconfigure.SpringBootApplication; //标记主类

@SpringBootApplication //注解的作用：不直接影响程序的逻辑，但是会执行一些额外的操作 这里是声明成主类
public class RlApplication { //定义主类，类名和文件名相同
    //入口方法
    public static void main(String[] args) {
        //告诉框架启动RlApplication类，并传递命令行参数args
        SpringApplication.run(RlApplication.class, args);
    }

}
