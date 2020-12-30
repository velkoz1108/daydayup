package com.eden.aopdemo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RedisDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Qualifier("myRedisTemplate")
    @Autowired
    private RedisTemplate myRedisTemplate;

    @Bean
    public RedisTemplate myRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        GenericJackson2JsonRedisSerializer objRedisSerializer = new GenericJackson2JsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        RedisTemplate<Object, Object> myRedisTemplate = new RedisTemplate<>();

        myRedisTemplate.setKeySerializer(stringRedisSerializer);
        myRedisTemplate.setValueSerializer(objRedisSerializer);

        myRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        myRedisTemplate.setHashValueSerializer(objRedisSerializer);

        myRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return myRedisTemplate;
    }

    @GetMapping("/test/redisString")
    public String redisString(@RequestParam(required = false) String username) {
        System.out.println("username = " + username);
        String key = "demoStr";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "this is my redis demo.");
        return String.valueOf(valueOperations.get(key));
    }

    /**
     * @return
     */
    @GetMapping("/redisObj")
    public String redisObj() {
        String key = "demoObj";
        User user = new User("eden", 30, false, "上海");

        ValueOperations valueOperations = myRedisTemplate.opsForValue();
        valueOperations.set(key, user);
        Object eden = valueOperations.get(key);
        return JSON.toJSONString(eden);
    }

    @GetMapping("/redisList")
    public String redisList() {
        String key = "users";
        User user = new User("eden", 30, false, "上海");
        User user2 = new User("eden2", 32, true, "上海2");

        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, user);
        listOperations.leftPush(key, user2);

        Long size = listOperations.size(key);
        return key + " : " + size;
    }

    @GetMapping("/redisList2")
    public String redisList2() {
        String key = "users2";
        User user = new User("eden", 30, false, "上海");
        User user2 = new User("eden2", 32, true, "上海2");

        ListOperations listOperations = myRedisTemplate.opsForList();
        listOperations.leftPush(key, user);
        listOperations.leftPush(key, user2);

        Long size = listOperations.size(key);
        return key + " : " + size;
    }

    @GetMapping("/redisHash")
    public String redisHash() {
        String key = "demoHash";

        User hashKey = new User("edenKey", 30, false, "上海");
        User hashObj = new User("edenVal", 30, false, "上海");

        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, hashKey, hashObj);

        for (int i = 0; i < 3; i++) {
            hashOperations.put(key, key + "-" + i, hashObj);
        }

        Long size = hashOperations.size(key);
        return key + " : " + size;
    }

}
