package com.buddha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * 
 * 	代码生成器入口:
 * 	后台生成代码：pojo,param,service,mapper,controller
 * 	前台生成代码：api,views
 * 	声明：本套代码生成器基于MyBatis-Plus设计，进一步的结合实际开发，定制的前后台代码生成模板！
 * @作者  大花猫
 * @时间 2018年11月14日
 * @版权  开源技术
 * @邮箱 1003632308@qq.com
 * @QQ技术交流群  327947585
 * @微信号  javawww
 */
public class MpGenerator {

	private static final String dataBaseName = "bigcat_loan";
	
	private static final String[] tableName = new String[] { "loan_apply_product"};

	private static final String basePackage = "com.buddha";
	private static final String modelPackage = ".loan"; // 业务模块包

	private static final String dir = "D:/entity";

	// db config
	private static final String ipAndPort = "127.0.0.1:3306";

	private static final String userName = "root";

	private static final String passWord = "root";

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(dir);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor("大花猫");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		// gc.setServiceName("MP%sService");
		gc.setServiceImplName("%sService");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {

			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(userName);
		dsc.setPassword(passWord);
		dsc.setUrl("jdbc:mysql://" + ipAndPort + "/" + dataBaseName + "?characterEncoding=utf8");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		// strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });//
		// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(tableName); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		 strategy.setSuperEntityClass("com.buddha.component.mybatis.PojoModel");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		 strategy.setSuperControllerClass("com.buddha.controller.WebBaseController");
		 strategy.setRestControllerStyle(true); // REST风格
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(basePackage);
		pc.setController("controller" + modelPackage);
		pc.setMapper("mapper" + modelPackage);
		pc.setServiceImpl("service" + modelPackage);
		pc.setEntity("pojo" + modelPackage);
		// pc.setModuleName("test");
		mpg.setPackageInfo(pc);
		
		InjectionConfig cfg = new InjectionConfig() {

			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
                map.put("dataBaseName", dataBaseName);
                map.put("well", "#"); //# 符号
                map.put("dollor", "$"); //$ 符号
                map.put("proxy", "miracle-api"); // 跨域
                this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		// 1 调整 service 生成目录
		focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {

			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/service/" + tableInfo.getEntityName() + "Service.java";
			}
		});
		// 调整pojo生成目录
		focList.add(new FileOutConfig("/templates/entity.java.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/pojo/" + tableInfo.getEntityName() + ".java";
			}
		});
		// 调整xml生成目录
		focList.add(new FileOutConfig("/templates/mapper.java.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/mapper/" + tableInfo.getEntityName() + "Mapper.java";
			}
		});
		// 调整xml生成目录
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {

			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/xml/" + tableInfo.getEntityName() + "Mapper.xml";
			}
		});
		// 参数生成
		focList.add(new FileOutConfig("/templates/param.java.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/param/" + tableInfo.getEntityName() + "Param.java";
			}
		});
		
		// 前端控制器
		focList.add(new FileOutConfig("/templates/controller.java.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/controller/" + tableInfo.getEntityName() + "Controller.java";
			}
		});
		//前端界面
		focList.add(new FileOutConfig("/templates/views.vue.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/views/" + tableInfo.getEntityName() + ".vue";
			}
		});
		// 前端api接口
		focList.add(new FileOutConfig("/templates/api.js.vm") {
			
			@Override
			public String outputFile(TableInfo tableInfo) {
				return dir + "/api/" + tableInfo.getEntityName() + ".js";
			}
		});
		
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
		// 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setController(null);
		tc.setEntity(null);
		tc.setMapper(null);
		tc.setXml(null);
		tc.setService(null);
		tc.setServiceImpl(null);
		// 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
		// 打印注入设置
		// System.err.println(mpg.getCfg().getMap().get("abc"));
	}
}
