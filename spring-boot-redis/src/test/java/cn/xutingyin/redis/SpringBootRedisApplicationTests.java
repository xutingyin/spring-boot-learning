package cn.xutingyin.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import cn.xutingyin.redis.entity.User;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootRedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeEach
    void init() {
        // 为了解决redisTemplate默认采用JdkSerializationRedisSerializer进行序列化导致key不对的问题，这里更改redisTemplate的序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
    }

    @Test
    void test() {
        stringRedisTemplate.opsForValue().set("aaa", "111"); // 采用RedisSerializer 类进行序列化，存入的key和用户输入的一样
        redisTemplate.opsForValue().set("bbb", "222"); // 默认采用JdkSerializationRedisSerializer类进行序列化；会在bbb前面有一串东西
                                                       // "\xac\xed\x00\x05t\x00\x03bbb"
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("222", redisTemplate.opsForValue().get("bbb"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User(1L, "aa", "aa@126.com", "aa123456", "aa", "123");
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("cn.xutingyin", JSON.toJSONString(user));
        operations.set("cn.xutingyin.f", JSON.toJSONString(user), 1, TimeUnit.SECONDS);// 1秒后自动删除
        Thread.sleep(1000);
        // redisTemplate.delete("cn.xutingyin.f");
        boolean exists = redisTemplate.hasKey("cn.xutingyin.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        // JSON 字符串 转JavaBean
        User user1 = JSON.parseObject(operations.get("cn.xutingyin"), User.class);
        Assert.assertEquals("aa", user1.getUserName());
    }

}
