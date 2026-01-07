##踩坑注意点
1. springboot版本和springCloud版本和springCloudAlibaba版本一定要对应上，否则会出现意想不到的问题，排查半天都解决不了，最后发现是版本不兼容
2. spring.cloud.nacos.config需要配置在bootstrap.yml中，这是因为低版本的springCloud采用了旧方式，配置中心的连接信息(如nacos地址)必须在应用启动之前获取。
3. 加载顺序：
> 启动 → 加载bootstrap.yml → 连接Nacos → 获取远程配置 →
加载application.yml → 合并配置 → 启动应用
> 
4. 新旧方式加载的生命周期
> 旧方式（bootstrap）：
> 1. 创建Bootstrap上下文
> 2. 加载bootstrap.yml/bootstrap.properties
> 3. 连接到配置中心（Nacos）
> 4. 获取远程配置
> 5. 创建主Application上下文
> 6. 加载application.yml
> 7. 合并所有配置
> 8. 启动应用

> 新方式（config import）：
> 1. 创建Application上下文
> 2. 加载application.yml，遇到spring.config.import
> 3. 连接到配置中心（Nacos）
> 4. 获取远程配置
> 5. 继续加载application.yml的其他配置
> 6. 合并所有配置
> 7. 启动应用
>
5. 所以需要添加这个依赖：
```
<!-- 需要 bootstrap starter -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```
