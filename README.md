# zookeeper-driver
## 项目介绍
平时在学习使用Zookeeper时,总是需要登录到虚拟机,进入控制到输入命令操作节点.
    
在命令逐渐熟悉后,其实是一个很耽误时间的过程,由此打算开发一套简单实用的Zookeeper管理平台
    
同时,在开发这套平台的时候,希望他可以做到"开箱配置即可用"的状态,所以在设计上,更多的考虑到一些配置的灵活性和持久性:
    
- 将Zookeeper的连接配置持久化到数据库中,管理员在系统第一次使用的时候可以先初始化信息.未来规划是提供一个安装平台进行首次部署
- 前后分离的模式,可以根据现有API进行功能化开发
        
## 技术架构
- Zookeeper版本:3.4.9
- Zookeeper客户端:curator2.11.1 (该版本使用的zookeeper与服务器版本相同,这里就要注意本地zk客户端的版本号一定要和服务器zk的版本相匹配)
- 构建工具:Gradle3.2.1 (工程集成了gradlew,可以直接使用)
- 数据库:MySQL
- Spring4.3.3 MyBatis3.4.1 

## 快速开始
### war包部署方式(以打development环境的包为例)
- 执行项目根路径db目录的sql文件,会自动创建zk_driver数据库,并导入测试初始数据
- 修改host文件,将zk.sagesource.com映射到自己的zk服务器ip
- 修改zookeeper-driver-webapp/development/development的jdbc-development.properties配置文件.修改数据库连接 用户名 密码等
- 在项目的根路径执行gradle的打包命令
    ./gradlew war -Dprofile=development

## 开发说明
### 工程结构说明

## 未来规划

## 联系作者


-----
zk的版本一定要和服务端一样
