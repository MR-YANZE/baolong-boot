package com.baolong.boot;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Collections;

/**
 * MyBatisPlus代码生成器 - 启动器
 *
 * @author Baolong 2024年06月13 21:52
 * @version 1.0
 * @since 1.8
 */
public class MyBatisPlusGenerator {
	//数据库连接地址
	private static final String URL = "jdbc:mysql://60.xxx.xxx.27:3306/baolong-boot?serverTimezone=Asia/Shanghai";
	//数据库用户名
	private static final String UNAME = "baolong";
	//数据库密码
	private static final String PASSWORD = "xxxxxx";
	//作者
	private static final String AUTHOR = "Baolong";
	//项目路径
	private static final String ROOT_PATH = System.getProperty("user.dir") + "/src/main/java";
	//父包名路径
	private static final String PARENT_PATH = "com.baolong.boot";
	//模块名
	private static final String MODULE_NAME = "dao";
	//实体类名
	private static final String ENTITY_NAME = "entity";
	//mapper包名
	private static final String MAPPER_NAME = "mapper";
	//mapper.xml文件路径
	private static final String MAPPER_URL_PATH = System.getProperty("user.dir") + "/src/main/resources/mapper";
	//service包名
	private static final String SERVICE_NAME = "service";
	//service.impl包名
	private static final String SERVICE_IMPL_NAME = "service.impl";
	//controller包名
	private static final String CONTROLLER_NAME = "controller";
	//包含的表，要生成的表名
	private static final String[] INCLUDE_TABLE = {"t_sys_user", "t_sys_dept", "t_sys_role", "t_sys_menu"};
	//表前缀，要去除的表前缀
	private static final String[] INCLUDE_TABLE_PREFIX = {"t_"};

	public static void main(String[] args) {
		// 数据源配置
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(URL, UNAME, PASSWORD)
				// 自定义类型转换
				.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
					int typeCode = metaInfo.getJdbcType().TYPE_CODE;
					if (typeCode == Types.TINYINT || typeCode == Types.SMALLINT || typeCode == Types.BIT) {
						return DbColumnType.INTEGER;
					} else if (typeCode == Types.INTEGER || typeCode == Types.BIGINT) {
						return DbColumnType.LONG;
					} else if (typeCode == Types.FLOAT) {
						return DbColumnType.FLOAT;
					} else if (typeCode == Types.DOUBLE) {
						return DbColumnType.DOUBLE;
					} else if (typeCode == Types.DECIMAL) {
						return DbColumnType.BIG_DECIMAL;
					} else if (typeCode == Types.BOOLEAN) {
						return DbColumnType.BOOLEAN;
					} else if (typeCode == Types.CHAR || typeCode == Types.VARCHAR || typeCode == Types.LONGVARCHAR) {
						return DbColumnType.STRING;
					} else if (typeCode == Types.DATE || typeCode == Types.TIME || typeCode == Types.TIMESTAMP) {
						return DbColumnType.DATE;
					} else {
						return DbColumnType.STRING;
					}
				});

		// 全局配置
		GlobalConfig.Builder globalConfigBuilder = new GlobalConfig.Builder()
				//代码输出目录
				.outputDir(ROOT_PATH).author(AUTHOR)
				//开启 Swagger，会生成 Swagger 相关注解
				//.enableSwagger()
				//设置日期格式，默认是 LocalDateTime
				.dateType(DateType.ONLY_DATE);

		// 包配置
		PackageConfig.Builder packageConfigBuilder = new PackageConfig.Builder()
				.parent(PARENT_PATH).moduleName(MODULE_NAME).entity(ENTITY_NAME)
				.service(SERVICE_NAME).serviceImpl(SERVICE_IMPL_NAME).controller(CONTROLLER_NAME)
				.mapper(MAPPER_NAME)
				.pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_URL_PATH));

		// ------------------------------ 策略配置
		StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder()
				.addInclude(INCLUDE_TABLE).addTablePrefix(INCLUDE_TABLE_PREFIX);

		// -------------------- Entity 策略配置
		strategyConfigBuilder.entityBuilder()
				//指定生成的主键策略
				.idType(IdType.AUTO)
				//覆盖已生成的文件
				.enableFileOverride()
				//开启 Lombok 模型
				.enableLombok()
				//开启链式模型
				.enableChainModel()
				//指定数据库表映射到实体的命名策略，默认下划线转驼峰
				.naming(NamingStrategy.underline_to_camel)
				//指定数据库表字段映射到实体的命名策略，默认下划线转驼峰
				.columnNaming(NamingStrategy.underline_to_camel)
				//开启生成实体时生成字段注解
				.enableTableFieldAnnotation()
				//Boolean 类型字段移除 is 前缀
				//.enableRemoveIsPrefix()
				//逻辑删除对应的数据库表字段
				.logicDeleteColumnName("is_delete")
				//逻辑删除对应的实体类属性
				.logicDeletePropertyName("isDelete")
				//乐观锁对应的数据库表字段
				//.versionColumnName("version")
				//乐观锁对应的实体类属性
				//.versionPropertyName("version")
				//自动填充的表字段
				.addTableFills(new Column("create_time", FieldFill.INSERT),
						new Column("update_time", FieldFill.INSERT_UPDATE));

		// -------------------- Mapper 策略配置
		strategyConfigBuilder.mapperBuilder()
				//设置 mapper 父类
				.superClass(BaseMapper.class)
				//标记 Mapper 注解，可指定使用那个 Mapper 注解
				.mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)
				//BaseResultMap 生成
				.enableBaseResultMap()
				//BaseColumnList 生成
				.enableBaseColumnList()
				//覆盖已生成的文件
				.enableFileOverride();

		// -------------------- Service 策略配置
		strategyConfigBuilder.serviceBuilder()
				//设置 service 父类
				.superServiceClass(IService.class)
				//格式化 service 文件名
				.formatServiceFileName("%sService")
				//设置 service 实现的父类
				.superServiceImplClass(ServiceImpl.class)
				//格式化 service 实现的文件名
				.formatServiceImplFileName("%sServiceImpl")
				//覆盖已生成的文件
				.enableFileOverride();

		// -------------------- Controller 策略配置
		strategyConfigBuilder.controllerBuilder()
				//开启生成@RestController 控制器
				.enableRestStyle()
				//开启驼峰转连字符
				.enableHyphenStyle()
				//覆盖已生成的文件
				.enableFileOverride();

		// 创建代码生成器对象，加载配置
		AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfigBuilder.build())
				.global(globalConfigBuilder.build())
				.packageInfo(packageConfigBuilder.build())
				.strategy(strategyConfigBuilder.build());
		// 执行
		autoGenerator.execute();
	}
}
