package core.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import core.common.Constant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis API 相关操作
 * 
 * @author seamar
 * 
 */
public class RedisUtil {
	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
	/**
	 * Redis服务器IP
	 */
	private static String IP = (String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.ip");
	/**
	 * 密码
	 */
	private static String PASSWORD = (String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH,
			"redis.password");
	/**
	 * Redis的端口号
	 */
	private static Integer PORT = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.port"));
	/**
	 * 可用连接实例的最大数目，默认值为1024；
	 * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	 */
	private static Integer MAX_TOTAL = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.maxTotal"));
	/**
	 * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	 */
	private static Integer MAX_IDLE = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.maxIdle"));
	/**
	 * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	 */
	private static Integer MAX_WAIT_MILLIS = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.maxWaitMillis"));
	/**
	 * 超时时间
	 */
	private static Integer TIMEOUT = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.timeOut"));
	/**
	 * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	 */
	private static Boolean TEST_ON_BORROW = Boolean
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.testOnBorrow"));

	/**
	 * 键的过期时间,现在定为永久有效
	 */
	public static Integer EXPIRE = Integer
			.valueOf((String) CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "redis.expire"));

	private static JedisPool jedisPool = null;

	/**
	 * 获取jedis池，不存在则创建新的
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW);
		config.setTestOnReturn(true);
		config.setBlockWhenExhausted(false);
		if (!StringUtils.isEmpty(PASSWORD)) {
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT, PASSWORD);
		} else {
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
		}
	}

	public synchronized static Jedis getJedis() {
		Jedis jedis = null;
		try {
			if (jedisPool != null) {//
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
		}
		return jedis;
	}

	private static void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public static void set(String key, String value, Integer expire) {
		Jedis jedis = getJedis();
		try {
			if (jedis != null) {
				jedis.set(key, value);
				if (expire != null) {
					jedis.expire(key, expire);
				}
			} else {
				log.error("Jedis is null");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
	}

	public static void updateExpire(String key, Integer expire) {
		Jedis jedis = getJedis();
		try {
			if (jedis != null && jedis.get(key) != null) {
				jedis.expire(key, expire);// TODO 设置过期时间永久有效
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取字符串类型的key对应value值
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jedis = getJedis();
		String value = null;
		try {
			if (jedis != null) {
				value = jedis.get(key);			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return value;
	}

	/**
	 * @功能 jedis队列操作，lpush 左近右出的方式
	 * @功能：向redis存入key和value，并释放连接资源 ，List集合
	 * @参数：key，键
	 * @参数：value，与key对应的值 @返回：成功返回“OK”，失败返回“0”
	 */
	public static long setLpush(String key, String value) {
		Jedis jedis = getJedis();
		long v = 0;
		try {
			if (jedis != null)
				v = jedis.lpush(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * * @功能 jedis队列操作，rpop 左近右出的方式,取出改key值即删除该元素
	 * 
	 * @功能：通过Redis的key获取值， @参数：key，键值 @返回： 成功返回value，失败返回null
	 */
	public static String getRpop(String key) {
		Jedis jedis = getJedis();
		String v = "";
		try {
			if (jedis != null)
				v = jedis.rpop(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * 存储map集合值
	 * 
	 * @功能：向redis存入key和value，并释放连接资源,字符串形式
	 * @参数：key，键
	 * @参数：map，多个字段参数集合 @返回：成功返回“OK”，失败返回“0”
	 */
	public static String setHM(String key, Map<String, String> map) {
		Jedis jedis = getJedis();
		String v = "";
		try {
			if (jedis != null)
				v = jedis.hmset(key, map);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * 返回list集合
	 * 
	 * @功能：通过Redis的key获取值，并释放连接资源
	 * @参数：key，键值
	 * @参数：value，与key对应的值，有多个字段 @返回： 成功返回value，失败返回null
	 */
	public static List<String> getHM(String key, String... field) {
		Jedis jedis = getJedis();
		List<String> v = null;
		try {
			if (jedis != null)
				v = jedis.hmget(key, field);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * 获取哈希表中指定字段的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static String hget(String key, String field) {
		Jedis jedis = getJedis();
		String v = null;
		try {
			if (jedis != null)
				v = jedis.hget(key, field);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * 获取在哈希表中指定 key 的所有字段和值
	 * 
	 * @param key
	 * @return Map
	 */
	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = getJedis();
		Map<String, String> v = null;
		try {
			if (jedis != null)
				v = jedis.hgetAll(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}

	/**
	 * @功能：返回一个区间集合值集合
	 * @参数：key，键
	 * @参数：value，与key对应的值 @返回：成功返回“OK”，失败返回“0”
	 */
	public static List<String> getLrange(String key) {
		Jedis jedis = getJedis();
		List<String> v = null;
		try {
			if (jedis != null)
				v = jedis.lrange(key, 0, -1);// 取第一个到最后一个元素，-2：倒数第二个元素
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeJedis(jedis);
		}
		return v;
	}
}
