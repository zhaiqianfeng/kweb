本项目主要搭建gradle+Spring mvc+servlet3.0(web-fragment)进行web插件式开发.

集成常用插件,便于快速开发:
* 用户插件－集成用户登陆,登出,注册等

插件的架构：
```
kuser-fragment/
└── src
    ├── main
    │   ├── java －java代码
    │   └── resources
    │       ├── META-INF
    │       │   ├── resources
    │       │   │   ├── assets　－插件的js,css,图片等资源
    │       │   │   └── WEB-INF
    │       │   │       └── jsp －插件的jsp文件，当然也可以使用其他模板
    │       │   │           └── user
    │       │   │               └── login.jsp
    │       │   └── web-fragment.xml －插件的部署配置文件
    │       └── spring
    │           └── application-config.xml －插件的spring bean配置文件
    └── test
        ├── java －java单元测试
        └── resources  －java单元测试资源
            ├── log4j.properties
            ├── properties
            └── spring
                ├── application-config.xml
                └── dispatcher-servlet.xml
```
