# 学习方法

https://db-engines.com/en/ 数据库排名网站

数据库选型的时候可以来这里对比，基本就可以确定采用哪种数据库。

## 基础知识

1. 2个基础设施

2. 1、冯诺依曼体系的硬件

3. 2、以太网 tcp/ip的网络

   所有的程序都是以这两个为基础

   ```
   磁盘：
   1、寻址：ms 读取速度是毫秒级
   2、带宽：G/M 和带宽有关 毫米级
   内存：
   1、寻址：ns 纳秒级
   秒>毫秒>微秒>纳秒
   磁盘寻址比内存慢10W倍
   IO buffer：成本问题
   磁盘有磁道和扇区，扇区：一扇区512Byte，如果太小索引太多成本变大，所以默认格式化磁盘 扇区大小为4K，所以索引会少，寻址成本会降低
   所以从磁盘读取数据默认一次都是读取4K
   数据存在磁盘和内存占用的空间是不一样的，内存中要小一点
   ```

## 为什么需要内存数据库

1. 最早数据存在文件里：数据存在文件里随着文件变大，读取效率会越来越低磁盘I/O成为瓶颈
2. mysql关系型数据：数据存储在磁盘里，然后通过索引，B+Tree 来管理索引，再通过索引查找数据，数据在存储时需要先声明schema，这样数据在存储时会开辟对应的空间，减少磁盘寻址
3. 为了提高读取速度，把数据存储在内存中更为划算，但是专业的内存数据库费用是非昂贵，例如 SAP HANA为纯内存数据库，但是费用十分昂贵（2T的内存数据库+服务 2亿）。
4. 所以有了一个折中的解决方式就是缓存（memcached、redis）

## 查看官网

```
https://redis.io/
http://www.redis.cn/
阅读简介
redis是一个内存数据库
```

## 下载程序

```
https://download.redis.io/releases/redis-5.0.10.tar.gz
linux下的命令
wget https://download.redis.io/releases/redis-5.0.10.tar.gz
解压
tar xf redis-5.0.10.tar.gz
进入解压后的目录
cd /root/soft/redis-5.0.10
查看 README.md
```

## 阅读README.md

```
make 编译文件
make报错的话安装gcc
yum install gcc
再次 make ，还会报错，是因为第一make没有成功有错误的缓存文件
make distclean 清理缓存
再次 make 成功
然后进入src目录
cd /root/soft/redis-5.0.10/src
./redis-server 启动 redis 此时启动的redis 重启后不会开机启动 且端口为默认端口6379
cd /root/soft/redis-5.0.10 
make install PRXFIX=/XXX  安装服务 PRXFIX=/XXX 为日志，持久化数据，分配数据存储目录
cd /root/soft/redis-5.0.10/utils 进入 redis工具目录
vim /etc/profile
配置环境变量 在文件末尾追加
export REDIS_HOME=/opt/shenke/redis5
export PATH=$PATH:$REDIS_HOME/bin
. /etc/profile 重新加载环境变量
启动服务 cd /root/soft/redis-5.0.10/utils
./install_server.sh 根据提示设置redis服务端口
cd /etc/init.d/ 进入init.d 目录
发现新的服务已经添加好
redis_6379
redis_6380
service start redis_6379  启动服务，此服务占用的端口为6379
redis-cli 连接redis
keys * 查看全部key
set k1 hello
get k1
hello
至此redis安装并简单测试成功
```

## 不管什么技术都要先阅读README.md

不管什么技术都要先阅读README.md

不管什么技术都要先阅读README.md

不管什么技术都要先阅读README.md

# redis

### 数据类型

#### string

1. 字符类型
2. 数值类型
3. bitmaps

#### hashes

#### lists

#### sets

#### sorted sets

### 命令help 

redis 的命令 可以通过 help @指令 的方法学习

redis 的命令 可以通过 help @指令 的方法学习

redis 的命令 可以通过 help @指令 的方法学习

### flushdb|flushall

清空数据库

### list相关

list 不能排序，能控制插入和取出的顺序，不能去重

#### LPOP

获取 list左 侧第一个数据，并从list中移除

#### RPOP

获取 list右 侧第一个数据，并从list中移除

#### LPUSH

从左侧添加

```
lpush k1 a b c d e f g
lpop k1
“g"
后进先出
rpop k1
先进先出
```

#### RPUSH

从右侧添加

```
rpush  k2  a b c d e f g
lpop k2
“a"
```

#### LRANGE

查看list 

```
LRANGE k1 0 -1
```

#### LINDEX

查看 索引位置的数据

```
LINDEX k1 3
"d"
```

#### LSET

修改索引位置数据的值

```
LSET k1 3 "xxx"
LINDEX k1 3
"xxx"
```

#### LINSERT

在list中插入数据

```
linsert k1 after "e" ooo
linsert k1 before "e" ooo
```

#### LREM

从list中移除数据

```
正向移除2个
LREM k1 2 ooo
反向移除2个
LREM k1 -2 ooo
```

#### LLEN

```
获取list长度
LLEN k1
```

#### BLPOP | BRPOP

```
阻塞的方法
BLPOP key [key ...] timeout
BLPOP k1 0 (0即一直阻塞直到有数据)
通过这个可以实现阻塞单播的订阅服务
```

….

### HASH

```
stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
```

help @hash

```
查看文档 类似 String
```

### 	SET

无序、去重

#### 集合操作

并集|差集

```
SADD key member [member ...]
summary: Add one or more members to a set

SCARD key
summary: Get the number of members in a set

SDIFF key [key ...] 差集
summary: Subtract multiple sets

SDIFFSTORE destination key [key ...]
summary: Subtract multiple sets and store the resulting set in a key

SINTER key [key ...] 并集
summary: Intersect multiple sets

SINTERSTORE destination key [key ...] 并集并存到出新的set
summary: Intersect multiple sets and store the resulting set in a key

SISMEMBER key member
summary: Determine if a given value is a member of a set

SMEMBERS key
summary: Get all the members in a set

SMOVE source destination member
summary: Move a member from one set to another

SPOP key [count]
summary: Remove and return one or multiple random members from a set

SRANDMEMBER key [count]
summary: Get one or multiple random members from a set

SREM key member [member ...]
summary: Remove one or more members from a set

SSCAN key cursor [MATCH pattern] [COUNT count]
summary: Incrementally iterate Set elements

SUNION key [key ...]
summary: Add multiple sets

SUNIONSTORE destination key [key ...]
summary: Add multiple sets and store the resulting set in a key
```

## 过期时间

-1 默认不过期 -2已经过期

```
set k1 hello ex 10
ttl k1 查看key过期时间
set k1 helloword 重新设置会重写过期时间
```

### EXPIRE

设置过期时间

```
EXPIRE k1 10
```

### EXPIREAT

设置定点过期

```
EXPIREAT k1 xxxx
```

## 数据存储层的基本问题

数据的安全性

```
快照|副本 	在redis中是RDB
日志				在redis中是AOF
```

### RDB

保存数据有延时

## 持久化

数据持久化到磁盘

```

```

## 主从复制

```

```



## 集群

```

```

# springboot集成redis

## RedisTemplate

默认会采用 JdkSerializationRedisSerializer 数据存储到redis中会有乱码

```
redisTemplate.opsForValue().set("k2","hello");
k1 -->"\xac\xed\x00\x05t\x00\x02k1"
hello -->"\xac\xed\x00\x05t\x00\x05hello"
```

如果想去掉乱码需要

```
StringRedisTemplate
stringRedisTemplate.opsForValue().set("k2","heoll");
key 和 value 都正常
```

StringRedisTemplate存储hash数据时会默认采用  new StringRedisSerializer(StandardCharsets.UTF_8) 序列化

指定 HashValueSerializer 序列化方法采用  new Jackson2JsonRedisSerializer<>(Object.class)

```
@Configuration
public class ConfigRedisTemplate {

	@Bean
	public StringRedisTemplate configStringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
		stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return stringRedisTemplate;
	}
}
```

