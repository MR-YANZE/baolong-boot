<h1>baolong-boot 脚手架</h1>

## 📖 介绍

`baolong-boot`是一个 SpringBoot 脚手架，里面整合了常用的一些框架，当有新项目的时候可以更快速地搭建一个后台服务。

## 🎈 技术栈说明

| 技术栈                | 版本          | 说明/备注                                                 | 官网                                                                                                                                          |
|--------------------|-------------|-------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| SpringBoot         | 2.7.18      | SpringBoot2 的最后一个版本                                   | [官网](https://docs.spring.io/spring-boot/docs/2.7.18/reference/html/)                                                                        |
| spring-retry       | 2.0.6       | Spring 系列的另一个实用程序模块，可以帮助我们以标准方式处理任何特定操作的重试；**实际上没用到** | [GitHub](https://github.com/spring-projects/spring-retry)                                                                                   |
| MySQL              | 5.7.x / 8.x | 使用 MySQL 稳定版本即可                                       | [官网](https://www.mysql.com/)                                                                                                                |
| Druid              | 1.2.23      | 阿里巴巴开源的数据库连接池                                         | [GitHub](https://github.com/alibaba/druid/wiki/%E9%A6%96%E9%A1%B5)                                                                          |
| MyBatis-Plus       | 3.5.3.2     | MyBatis 增强框架                                          | [官网](https://baomidou.com/)                                                                                                                 |
| dynamic-datasource | 3.5.2       | MyBatis-Plus 官方的多数据源支持                                | [GitHub](https://github.com/baomidou/dynamic-datasource)                                                                                    |
| jedis              | 5.1.0       | 排除 SpringBoot 自带的 Redis 中的 lettuce                    | [GitHub](https://github.com/redis/jedis)                                                                                                    |
| redisson           | 3.30.0      | 基于 Redis 的分布式锁框架                                      | [GitHub](https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95)                                                                      |
| sa-token           | 1.38.0      | 一个轻量级 Java 权限认证框架                                     | [官网](https://sa-token.cc/doc.html#/)                                                                                                        |
| knife4j            | 4.5.0       | 一个集 Swagger2 和 OpenAPI3 为一体的增强解决方案                    | [官网](https://doc.xiaominfo.com/)                                                                                                            |
| jwt                | 4.4.0       | JSON WEB TOKEN; **实际上没用到**                            | [GitHub](https://github.com/auth0/java-jwt/)                                                                                                |
| easy-captcha       | 1.6.2       | Java 图形验证码                                            | [GitHub](https://github.com/ele-admin/EasyCaptcha)                                                                                          |
| mapstruct          | 1.5.5.Final | Java bean映射工具类                                        | [官网](https://mapstruct.org/)                                                                                                                |
| lombok             | 1.18.32     | 一个 Java 库，它可以自动插入编辑器和构建工具，生成 Getter 和 Setter 等方法      | [GitHub](https://github.com/projectlombok/lombok)                                                                                           |
| Hutool             | 5.8.28      | 一个小而全的 Java 工具类库                                      | [官网](https://www.hutool.cn/)                                                                                                                |
| fastjson2          | 2.0.50      | 阿里巴巴开源的一个性能极致并且简单易用的 Java JSON 库                      | [Gitee](https://gitee.com/wenshao/fastjson2)                                                                                                |
| Gson               | 2.11.0      | Google 开源的一个 Java JSON 库                              | [GitHub](https://github.com/google/gson)                                                                                                    | [官网]()                                                                                                                                      |
| Guava              | 33.2.1-jre  | Google 开源的一组核心 Java 库                                 | [GitHub](https://github.com/google/guava)                                                                                                   |

## 📇 模块说明

```
┌── baolong-boot-business   # 业务模块
│   ├── src
│   │   └── main            # 源码目录
│   │       ├── com.baolong.boot.business     # 业务功能相关
│   │       └── com.baolong.boot.component    # 业务组件相关
│   └── pom.xml             # 子模块依赖
├── baolong-boot-common     # 公共模块
│   ├── src
│   │   └── main            # 源码目录
│   │   │   ├── com.baolong.boot.common.annotation      # 注解相关
│   │   │   ├── com.baolong.boot.common.cache           # 缓存相关
│   │   │   ├── com.baolong.boot.common.config          # 配置类相关
│   │   │   ├── com.baolong.boot.common.constants       # 常量类相关
│   │   │   ├── com.baolong.boot.common.entity          # 实体类相关
│   │   │   ├── com.baolong.boot.common.enums           # 枚举类相关
│   │   │   ├── com.baolong.boot.common.exception       # 异常类相关
│   │   │   ├── com.baolong.boot.common.filter          # 过滤器相关
│   │   │   ├── com.baolong.boot.common.function        # 功能相关
│   │   │   ├── com.baolong.boot.common.handler         # 处理器相关
│   │   │   ├── com.baolong.boot.common.interceptor     # 拦截器相关
│   │   │   ├── com.baolong.boot.common.redis           # Redis 相关
│   │   │   └── com.baolong.boot.common.utils           # 工具类相关
│   └── pom.xml             # 子模块依赖
├── baolong-boot-dao        # 数据访问层模块
│   ├── src
│   │   ├── main            # 源码目录
│   │   │   ├── com.baolong.boot.dao.config        # MyBatis-Plus、Druid 等相关配置类
│   │   │   ├── com.baolong.boot.dao.entity        # 业务实体类
│   │   │   ├── com.baolong.boot.dao.mapper        # 业务 Mapper 接口
│   │   │   └── com.baolong.boot.dao.MyBatisPlusGenerator.java    # 代码生成器
│   │   └── resource        # 资源目录
│   │       ├── mapper                        # 业务 Mapper 接口对应 xml 目录
│   │       └── application-dao.yml           # 配置文件, 数据库、MyBatis-Plus、Druid 等相关配置
│   └── pom.xml             # 子模块依赖
├── baolong-boot-web        # 入口模块
│   ├── src
│   │   ├── main            # 源码目录
│   │   │   ├── com.baolong.boot.xxxx        # 业务接口相关，xxxx对应不同的业务名称
│   │   │   └── com.baolong.boot.BaolongApplication.java    # 启动类
│   │   └── resource        # 资源目录
│   │       ├── markdown                      # knife4j 自定义文档目录
│   │       ├── application.yml               # 配置文件
│   │       ├── application-dev.yml           # 配置文件, 开发环境
│   │       ├── application-prod.yml          # 配置文件, 生产环境
│   │       ├── logback-spring.xml            # 日志配置文件
│   │       └── RFLimit.lua                   # Lua 脚本（RFLimit 功能用到）
│   └── pom.xml             # 子模块依赖
├── pom.xml                 # 父模块依赖
├── sql                     # SQL 脚本目录
│   ├── baolong-boot.sql    # 创建数据库
│   ├── t_sys_dept.sql      # 系统部门表
│   ├── t_sys_menu.sql      # 系统菜单表
│   ├── t_sys_role.sql      # 系统角色表
│   └── t_sys_user.sql      # 系统用户表
├── .gitignore              # git 忽略文件
├── LICENSE                 # 证书协议
└── README.md               # README
```

## 🌐 访问地址

- 项目地址: http://localhost:8888/baolong/
- Druid 监控: http://127.0.0.1:8888/baolong/druid/index.html
    - 账号: baolong
    - 密码: baolong
- Swagger文档: http://localhost:8888/baolong/doc.html
    - 账号：baolong
    - 密码：baolong

## 😀 联系我
- QQ: 510132075
- 邮箱: 510132075@qq.com
- 微信: <img src=".\doc\wx510132075.jpg" width="200">

## ⚠️ 注意

如果有问题欢迎一起交流，谢谢！

## 📑 许可证

[Apache © Version 2.0, January 2004, baolong-boot](./LICENSE)
