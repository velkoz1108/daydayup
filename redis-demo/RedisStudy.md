# redis 数据类型

- String 

- List

- Set

- Hash

- Zset


# 序列化
org.springframework.data.redis.core.RedisTemplate
默认使用的是JdkSerializationRedisSerializer

RedisSerializer keySerializer

RedisSerializer valueSerializer

RedisSerializer hashKeySerializer

RedisSerializer hashValueSerializer

```java
@Override
public void afterPropertiesSet() {
    super.afterPropertiesSet();
    boolean defaultUsed = false;
    if (defaultSerializer == null) {
        defaultSerializer = new JdkSerializationRedisSerializer(
                classLoader != null ? classLoader : this.getClass().getClassLoader());
    }
   //省略
}
```
存储的格式如下:

key : `\xAC\xED\x00\x05t\x00\x07demoStr`

value: `\xAC\xED\x00\x05t\x00\x16this is my redis demo.`


```
127.0.0.1:6379> keys *

1) "\xac\xed\x00\x05t\x00\ademoStr"

```

```
127.0.0.1:6379> get "\xac\xed\x00\x05t\x00\ademoStr"

"\xac\xed\x00\x05t\x00\x16this is my redis demo."

```

### RedisSerializer实现类
- org.springframework.data.redis.serializer.ByteArrayRedisSerializer

- org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer

- org.springframework.data.redis.serializer.GenericToStringSerializer

- org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

- org.springframework.data.redis.serializer.JdkSerializationRedisSerializer

- org.springframework.data.redis.serializer.OxmSerializer

- org.springframework.data.redis.serializer.StringRedisSerializer

- com.alibaba.fastjson.support.spring.FastJsonRedisSerializer

- com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer


