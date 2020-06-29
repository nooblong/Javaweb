import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    @Test
    public void test1(){
        //47.115.156.226
        Jedis jedis = new Jedis("localhost",6379);

        jedis.set("username", "zhangsan");

        System.out.println(jedis.get("username"));

        jedis.hset("user","name" ,"lisi");
        jedis.hset("user","age" ,"23");
        jedis.hset("user","gender" ,"male");

        System.out.println(jedis.hget("user", "name"));

        Map<String,String> map = jedis.hgetAll("user");
        for (String user : map.keySet()){
            System.out.println(user + ":" + map.get(user));
        }

        jedis.close();
    }
}
