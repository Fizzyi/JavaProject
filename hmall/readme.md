# nacos 启动

```dockerfile
docker run -d \       
--name nacos \
--env-file sql/custom.env \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
--restart=always 
```

1. mysql 如果也是用 docker 启动的，需要创建一个网络组，将两个容器都添加到网络组中。