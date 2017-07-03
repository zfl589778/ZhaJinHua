package com.eric.service.cache;

import com.systoon.commons.cache.redis.RedisClient;
import com.systoon.commons.cache.redis.RedisPoolTemplate;
import com.systoon.commons.cache.redis.RedisPoolTemplate.RedisAction;
import com.systoon.commons.cache.redis.RedisPoolTemplate.RedisActionVoid;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

@Component
public class RedisCacheHelper {

	@Resource(name = "redisPoolTemplate")
    private RedisPoolTemplate redisTemplate;

    /**
     * redis 缓存
     *
     * @param cacheKey
     * @param value
     * @param seconds  缓存时间（单位秒）
     */
    public <T> void put(final String cacheKey, final T value, final int seconds) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.setex(cacheKey, seconds, value);
            }
        });
    }

    /**
     * redis 缓存
     *
     * @param cacheKey
     * @param value
     */
    public <T> void put(final String cacheKey, final T value) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.set(cacheKey, value);
            }
        });
    }

    /**
     * 根据缓存KEY自增缓存数据
     *
     * @param cacheKey
     * @return
     */
    public Long incrRedisCacheByKey(final String cacheKey) {
        Long po = null;
        po = redisTemplate.execute(new RedisAction<Long>() {
            @Override
            public Long action(RedisClient client) {
                return client.incr(cacheKey);
            }
        });
        return po;
    }

    /**
     * 从缓存中获取数据
     *
     * @param cacheKey
     * @return
     */
    public <T> T get(final String cacheKey) {
        return redisTemplate.execute(new RedisAction<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T action(RedisClient client) {
                return (T) client.getObjectByKey(cacheKey);
            }
        });
    }

    /**
     * 存对象类型的hash缓存
     * @param cacheKey
     * @param field
     * @param value
     */
    public void hset(final String cacheKey,final Object field,final Object value){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.hset(cacheKey, field, value);
			}
		});
    }

    /**
     * 存hash类型数据
     * redis 缓存
     *
     * @param cacheKey
     * @param value
     */
    public void hset(final String cacheKey, final Object field, final Object value, final int seconds) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.hset(cacheKey, field, value);
                client.expire(cacheKey, seconds);
            }
        });
    }

    /**
     * 取hash类型单个域
     * redis 缓存
     * @param cacheKey
     */
    public <T> T hget(final String cacheKey, final Object field) {
        return redisTemplate.execute(new RedisAction<T>() {
            @SuppressWarnings("unchecked")
			@Override
            public T action(RedisClient client) {
            	return (T) client.hget(cacheKey, field);
            }
        });
    }

    /**
     * 删除hash类型的值
     * redis 缓存
     * @param cacheKey
     */
    public void hdel(final String cacheKey, final Object field) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.hdel(cacheKey, field);
            }
        });
    }

    /**
     * 取hash类型所有值
     * redis 缓存
     *
     * @param cacheKey
     */
    public Map<String, String> hgetAll(final String cacheKey) {
        return redisTemplate.execute(new RedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> action(RedisClient client) {
                return client.hgetAll(cacheKey);
            }
        });
    }
    
    /**
     * 设置redis缓存的时效性
     *
     * @param cacheKey
     * @param seconds
     */
    public void expire(final String cacheKey, final int seconds) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.expire(cacheKey, seconds);
            }
        });
    }

    /**
     * 清除缓存
     *
     * @param cacheKey
     */
    public void removeCache(final String cacheKey) {
        redisTemplate.execute(new RedisActionVoid() {
            @Override
            public void action(RedisClient client) {
                client.del(cacheKey);
            }
        });
    }
    
    /**
     * 添加list类型的缓存
     * @param cacheKey
     * @param value
     */
    public void lpush(final String cacheKey,final String value){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.lpush(cacheKey, value);
			}
		});
    }
    
    /**
     * 反向添加list缓存
     * @param cacheKey
     * @return
     */
    public void rpush(final String cacheKey,final String value){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.rpush(cacheKey, value);
			}
		});
    }
    
    /**
     * 在list中删除target
     * @param cacheKey
     * @param target
     */
    public void lrem(final String cacheKey,final String target){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.lrem(cacheKey, 1L, target);
			}
		});
    }
    
    /**
     * 查找list所有值
     * @param cacheKey
     * @return
     */
    public List<String> lrange(final String cacheKey){
    	return redisTemplate.execute(new RedisAction<List<String>>() {
			@Override
			public List<String> action(RedisClient client) {
				return client.lrange(cacheKey, 0, -1);
			}
		});
    }
    
    /**
     * 添加有序的set类型的集合
     * @param key
     * @param score
     * @param member
     */
    public void zadd(final String key, final double score, final String member){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.zadd(key, score, member);
			}
		});
    }
    
    /**
     * 删除set集合中的某项
     * @param cacheKey
     * @param target
     */
    public void zrem(final String cacheKey,final String target){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				client.zrem(cacheKey, target);
			}
		});
    }
    
    /**
     * 根据key清空set集合
     * @param cacheKey
     */
    public void zrem(final String cacheKey){
    	redisTemplate.execute(new RedisActionVoid() {
			@Override
			public void action(RedisClient client) {
				Set<String> set = client.zrange(cacheKey, 0, -1);
				String[] members = (String[]) set.toArray();
				client.zrem(cacheKey, members);
			}
		});
    }
    
    /**
     * 返回set所有值，按权重排序
     * @param cacheKey
     * @return
     */
    public Set<String> zrange(final String cacheKey){
    	return redisTemplate.execute(new RedisAction<Set<String>>() {
			@Override
			public Set<String> action(RedisClient client) {
				return client.zrange(cacheKey, 0, -1);
			}
		});
    }
    
}
