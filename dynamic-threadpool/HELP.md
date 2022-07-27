# Getting Started

### Reference Documentation


#  问题1：
#  需要开了springBoot 和 nacos版本匹配问题

#  问题2：
#  nacos 的配置不要写到application.yml 配置文件中，会导致连接localhost:8848, 修改连接信息到bootstrap.yml
#  原因：这是nacos读取本身自动配置的优先级高于application文件中的配置时引起的，
#  而nacos本身的自动配置是127.0.0.1:8848端口的nacos服务，所以发生了以上异常，故而需要将配置文件的优先级提升
#  创建一个bootstrap.properties或bootstrap.yml文件配置nacos地址就可以了。
#  这个配置是系统级的，优先级最高，先从这个文件读取nacos地址就不会报错了

#  问题3：
#  动态刷新nacos 配置中心的参数
#  使用@RefreshScope 注解 
#  参考：com.example.dynamicthreadpool.v1.TestController类






