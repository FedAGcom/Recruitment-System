package com.fedag.recruitmentSystem.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.fedag.recruitmentSystem")
@EnableAspectJAutoProxy
public class AopConfig {
}
