# Java练手项目

## 一、tlias-web_management

b站链接： [【黑马程序员2023新版JavaWeb开发教程，实现javaweb企业开发全流程（涵盖Spring+MyBatis+SpringMVC+SpringBoot等）】](https://www.bilibili.com/video/BV1m84y1w7Tb/?p=172&share_source=copy_web&vd_source=09fb92f030ff4b6839be02ef6ea050a0)


## 二、苍穹外卖
b站链接： [ 黑马程序员2023最新Java项目实战《苍穹外卖》，最适合新手的SpringBoot+SSM的企业级Java项目实战 ](https://www.bilibili.com/video/BV1TP411v7v6/?p=2&share_source=copy_web&vd_source=09fb92f030ff4b6839be02ef6ea050a0)

前端启动： node版本为12.22.1
安装依赖：n
启动：npm run server


## 三、 Java 微服务架构

B站连接: [黑马程序员SpringCloud微服务技术栈实战教程，涵盖springcloud微服务架构+Nacos配置中心+分布](https://www.bilibili.com/video/BV1kH4y1S7wz/?p=2&vd_source=33b55b80505ce4380c4dfa242b2d54d2) 

百度网盘链接： https://pan.baidu.com/s/1Eb9EQbcPMBlbGZHurp5Vtg?pwd=9988

文档链接：https://b11et3un53m.feishu.cn/wiki/R4Sdwvo8Si4kilkSKfscgQX0niB

htmall-nginx 前端项目 mac 启动方式
1. 安装 nginx
2. 修改 conf/nginx.conf 文件
```shell
# 1、查看用户组 
dscl . -list /Groups
# 2、查看指定用户组的详细信息 我用的是 admin，在GroupMembership中查看 username
dscl . -read /Groups/<groupName>
# 3、在 nginx.conf中添加 ;
user <username> <groupName>
```
3. 启动 nginx
```
sudo nginx -c conf/nginx.conf
```