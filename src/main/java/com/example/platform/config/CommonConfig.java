//package com.example.platform.config;
//
//import com.example.platform.pojo.Country;
//import com.example.platform.pojo.Man;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CommonConfig {
//    @Bean
//    public Man man(@Value("${man.name}") String name){
//        Man man = new Man();
//        man.setName(name);
//        return man;
//    }
//    @Bean
//    public Country country(Man man){
//        System.out.println("man:"+man);
//        return new Country();
//    }
////    @Bean("aa")用这种方法可以修改对象的名字
//}
