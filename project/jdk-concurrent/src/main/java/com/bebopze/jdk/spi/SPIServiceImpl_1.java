package com.bebopze.jdk.spi;

/**
 * SPI 实现1                          - 配置于  META-INF/services/
 *
 * @author bebopze
 * @date 2020/9/8
 */
public class SPIServiceImpl_1 implements SPIService {


    @Override
    public void execute() {
        System.out.println("spi_1 execute ...");
    }
}
