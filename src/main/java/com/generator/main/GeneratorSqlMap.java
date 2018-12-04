package com.generator.main;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorSqlMap {
    public void generator() throws  Exception
    {
        List<String> warrings = new ArrayList<>();
        boolean overwrite = true ;
        File ConfigFile = new File("generatorConfig.xml");


        //解析类
        ConfigurationParser configurationParser = new ConfigurationParser(warrings);

        //配置解析类解析配置文件 并生成Configuration对象
        Configuration config = configurationParser.parseConfiguration(ConfigFile) ;

        //负责处理重复文件
        ShellCallback shellCallback = new DefaultShellCallback(overwrite) ;

        //逆向工程对象
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config ,shellCallback , warrings) ;

        myBatisGenerator.generate(null);
    }
    public static void main(String[] args) throws Exception
    {
        GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap() ;
        generatorSqlMap.generator();
    }

    }

