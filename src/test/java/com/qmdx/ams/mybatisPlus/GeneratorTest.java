package com.qmdx.ams.mybatisPlus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.qmdx.ams.QmdxAmsApplicationTests;
import org.junit.Test;

public class GeneratorTest {

    @Test
    public void generatorTest() {
        // 1.Global configuration
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)// Whether AR mode is supported
                .setAuthor("qmdx")// Set the author
                .setOutputDir("E:\\JAVAIdea\\qmdx-ams\\src\\main\\java")// Path is generated
                .setFileOverride(false)// The file cover
                .setIdType(IdType.AUTO) // The primary key strategy
                .setServiceName("%sService")
                .setBaseResultMap(true)// Generate the basic result set
                .setBaseColumnList(true);// Generate collection SQL fragments of basic columns for use
        // 2.Data source configuration
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)// Set database type MYSQL ORACLE etc
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/qmdx_ams?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("xh123");
        // 3.Policy configuration
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)// Global uppercase naming
                .setNaming(NamingStrategy.underline_to_camel);// Database tables map to entity naming policies
        // 4.Package name policy configuration
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.qmdx.ams")// Set the parent package so that you don't have to write the package name
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");
        // 5.Integrated configuration
        AutoGenerator ag = new AutoGenerator();
        ag.setTemplateEngine(new FreemarkerTemplateEngine()).
                setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        // 6.execute
        ag.execute();
    }

}
