redis基础知识：

    1.定义：非关系型数据库，Not only sql ,与关系型数据库互补，
         存于内存中，用于缓存数据
    2.数据类型
    3.通用命令
    4.持久化



数据类型：按照键值对存放
-

    1.字符串 string
    2.哈希类型 hash ： map  (key对应的value中可以再存在key_value)
    3.列表类型 list ： linkedlist (key对应的value中可以再存在value)
    4.集合类型 set 
    5.有序集合类型 sortedset


1.字符串：string

    
    set同key会被覆盖，get不存在即是nil,del不存在即影响的数据行数为0
    存储：set key value：新建/修改value
    获取：get key
    删除：del key

2.哈希类型：hash

    存储：
        hset hashname subkey subvalue　：新建/修改subvalue
    获取：
        hget hashname subkey (获取hash中某一个键对应的值)
        hgetall hashname (获取hash中所有键值对)
    删除：
        hdel hashname subkey

3.列表类型: list

    存储：  
          lpush listname value　　　：新建
          rpush listname value
    获取：
          lrange listname start end
    删除:会返回出pop出来的value
          lpop listname 
          rpop listname

4.集合类型: set 

    存储：sadd setname value    ：新建
    获取：smembers setname （获取set中所有的元素）
    删除：srem setname value （删除set中特定的元素）

5.有序集合类型：sortset(从小到大排序)

    存储：zadd sortname value score　　：新建／修改score
    获取：zrange sortname start end [withscores]
    删除：zrem sortname value

通用命令：
-

    keys *: 查看所有的键
    type key: 查看key对应的value的类型
    del key : 删除指定的key-value
    注意：当内部容器被清空时，该key也就删除了


持久化：
-
    1.默认的RDB：
        0.修改redis.windows.conf ：
            save 900 1  //900s有1次操作就持久化
            save 300 10 //300s有10次操作就持久化
            save 10 2   //10s有2次操作就持久化
        
      1.使用cmd:
          D:\Redis\Redis-x64-3.0.504>redis-server.exe redis.windows.conf
      2.打开redis-cl，存入数据后：
            set username xxx 
            set password xxx
      3.可见有dump.rdb文件，关闭redis-server.exe
      4.再使用cmd打开
          D:\Redis\Redis-x64-3.0.504>redis-server.exe redis.windows.conf
          输入：keys *命令 仍能查看到存入的键值对

    2.效率不高的AOF：
      0.修改redis.windows.conf 内容修改为 appendonly yes
        注意：
            # appendfsync always   //这代表每一次操作都写入磁盘
            appendfsync everysec  //这代表每一秒都写入磁盘
            # appendfsync no      //关闭
        
      1.使用cmd:
          D:\Redis\Redis-x64-3.0.504>redis-server.exe redis.windows.conf
      2.打开redis-cl，存入数据后：
            set username xxx 
            set password xxx
      3.可见有appendonly.aof文件，关闭redis-server.exe
      4.再使用cmd打开
          D:\Redis\Redis-x64-3.0.504>redis-server.exe redis.windows.conf
          输入：keys *命令 仍能查看到存入的键值对
            
