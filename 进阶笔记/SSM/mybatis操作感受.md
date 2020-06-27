#### 一、全局配置+局部配置

就算没有映射器，局部配置文件也是必要注册到mappers标签上的。
映射器：namespace必要绑定 + 局部配置文件要配对上 + openSession + getMapper
无映射器：随便配置一个局部配置文件，用来写sql就可以了

#### 二、作用域分析

SqlSessionFactoryBuilder（局部方法变量）
SqlSesssionFactory(单例)
 ----->
SqlSession(非线程安全，局部变量，确保关闭)
  ----->
mapper(局部方法变量,与sqlSession一起try)

#### 三、定义dataSource

继承UnpooledDataSourceFactory，
构造器直接覆盖dataSouce成员变量为新建的dataSource，并在config.xml中dataSource配置上这个type即可，需要使用<property resource>引入外部文件，使用${字段}
 public class C3P0DatasourceFactory extends UnpooledDataSourceFactory {
     public C3P0DatasourceFactory() {
         dataSource = new ComboPooledDataSource();
     }
 }

```
<properties resource="c3p0.properties"></properties>
<environment id="development">
    <transactionManager type="JDBC"/>
    <dataSource type="cn.cxd.custom_config.C3P0DatasourceFactory">
        <property name="driverClass" value="${driverClass}"/>
        <property name="jdbcUrl" value="${jdbcUrl}"/>
        <property name="user" value="${user}"/>
        <property name="password" value="${password}"/>
        <property name="maxPoolSize" value="${maxPoolSize}"/>
        <property name="initialPoolSize" value="${initialPoolSize}"/>
    </dataSource>
</environment>
```

#### 四、select出来的字段，使用resultType/resultMap-type

1.构造器封装成一个对象，再返回出来，不提供get/set方法的情况
2.无参构造器+set&get方法
  对于2的，就要考虑能不能字段间能不能对应上了，
            可用setting：mapUnderscoreToCamelCase
  对于1的，就是参数位置对应上就可以设置进去

```
<select id="selectById" resultType="cn.cxd.model.Employee">
	select id,name,last_name ,gender,email,dept_id from employee where id = #{id}
</select>
```



#### 五、insert,update可以使用上数据库的自增主键

前提是数据库的主键列打开自增选项，
之后insert的sql语句不要包含主键列，由其自增即可。
如果传入的是对象，那么要使用keyProperty="jobId"【指的是model对象的成员变量】          useGeneratedKeys="true"
     如果仅仅是几个字段，那么不使用keyProperty，useGeneratedKeys

```
public boolean insertUseKey(Employee employee);
```

```
<insert id="insertUseKey" useGeneratedKeys="true" keyProperty="id">
	insert into employee (name,gender,email,last_name,dept_id)
	values (#{name},#{gender},#{email},#{lastName},#{deptId});
</insert>
```



#### 六、resultType=Employee

​    select * from Employee where id >= #{id}
​    方法的返回值是list,collection,array都可以

```
public List<Employee> selectList(int id);
```

```
<select id="selectList" resultType="cn.cxd.model.Employee">
	select id,name,last_name ,gender,email,dept_id from employee where id >= #{id}
</select>
```

#### 七、返回<String,Object>map


​    //string:字段名，object:value,同key对应的value不可以是一个集合，内部是selectOne，结果集为0/1  

```
public Map<String,Object> selectMap(int id);
```

    <select id="selectMap" resultType="map">
        select id,name,last_name ,gender,email,dept_id from employee where id = #{id}
    </select>

#### 八、返回<Integer,Employee>map

​	需要依赖于注解，resultType为Employee

```
@MapKey("id")
public Map<Integer,Employee> selectEmployeeMap(int id);
```

    <select id="selectEmployeeMap" resultType="cn.cxd.model.Employee">
        select id,name,last_name ,gender,email,dept_id from employee where id > #{id}
    </select>

#### 九、多参数:被封装成map

​	可以id = #{parm1}..,也可以在映射器方法入参时@param("gender"),#{gender}

```
@MapKey("id")
public Map<Integer,Employee> selectMultiParm(@Param("id") int id, @Param("gender") int gender);
```

    <select id="selectMultiParm" resultType="cn.cxd.model.Employee">
        select id,name,last_name ,gender,email,dept_id from employee where id >= #{id} and gender = #{gender}
    </select>

#### 十、$拼接符号 [表名、列名、关键字]

​	sql语句中要使用到参数传入做sql的表名列名关键字的情况下，使用$拼接符号${column}

```
 @MapKey("id")
public Map<Integer,Employee> selectOrderBy(@Param("id") int id, @Param("column") String column);
```

    <select id="selectOrderBy" resultType="cn.cxd.model.Employee">
        select id,name,last_name,gender,email,dept_id from employee where id >= #{id,jdbcType=NUMERIC,numericScale=2} order by ${column}
    </select>

#### 十一、笛卡尔积，一条sql找出EmployeeWithDept

```
<resultMap id="withDeptMap" type="cn.cxd.vo.EmployeeWithDept">
    <id property="id" column="id"></id>
    <result property="lastName" column="last_name"></result>
    <result property="gender" column="gender"></result>
    <result property="deptId" column="dept_id"></result>
    <result property="name" column="name"></result>
    <result property="email" column="email"></result>
    <association property="department" javaType="cn.cxd.model.Department">
        <id property="deptId" column="dept_id"></id>
        <result property="deptName" column="dept_name"></result>
    </association>
</resultMap>
```

```
public EmployeeWithDept selectEmployeeWithDept(int id);
```

    <select id="selectEmployeeWithDept" resultMap="withDeptMap">
        select e.id,last_name,gender,e.name,email,d.id dept_id,d.name dept_name
        from employee e, department d
        where e.dept_id = d.id and e.id = #{id}
    </select>

#### 十二、分步查询:两条sql找出EmployeeWithDept

​    先写出 根据部门id 查询并封装 部门的select标签，作为被调用的select语句
​    再写出resultMap,
​    重点是association的department属性是由上面的 被调用的select语句 返回出来的对象，
​    而这个select语句的输入参数就是column属性，
​    column属性是调用者select语句获取的，bean中并非一定要有这个property
​    再写出调用者select语句
​    

    <--被调用的select，封装成department-->
    <select id="selectStep" resultType="cn.cxd.model.Department">
        select id dept_id,name dept_name from department where id = #{id};
    </select>
    
    <-- resultMap的association传入调用者select出来的column,使用被调用的select来封装department-->
    <resultMap id="stepResultMap" type="cn.cxd.vo.EmployeeWithDept">
        <id column="id" property="id"></id>
        <result column="name" property="name"></result>
        <result column="email" property="email"></result>
        <result column="last_name" property="lastName"></result>
        <result column="gender" property="gender"></result>
        <association property="department" javaType="cn.cxd.model.Department"
                     select="cn.cxd.mapper.DepartmentMapper.selectStep" column="dept_id"/>
    </resultMap>

```
 public EmployeeWithDept selectEmployeeStep(int id);
```

    <--调用者select,主要是要获取出作为中间键的dept_id,这个dept_id必须是数据库的某一列-->
    <select id="selectEmployeeStep" resultMap="stepResultMap">
        select * from employee where id = #{id}
    </select>

#### 十三、笛卡尔积，一条sql找出DepartmentWithEmployee

```
<resultMap id="withEmployeeMap" type="cn.cxd.vo.DepartmentWithEmployee">
    <id column="dept_id" property="deptId"></id>
    <result column="dept_name" property="deptName"></result>
    <collection property="employees" ofType="cn.cxd.model.Employee">
        <id column="e_id" property="id"></id>
        <result column="e_name" property="name"></result>
        <result column="gender" property="gender"></result>
        <result column="email" property="email"></result>
        <result column="last_name" property="lastName"></result>
        <result column="dept_id" property="deptId"></result>
    </collection>
</resultMap>
```

```
public DepartmentWithEmployee selectDepartmentWithEmployee(int id);
```

    <select id="selectDepartmentWithEmployee" resultMap="withEmployeeMap">
        select d.id dept_id,d.name dept_name,e.id e_id,e.name e_name,gender,email,
                last_name,dept_id
        from department d,employee e
        where d.id = e.dept_id and e.dept_id = #{dept_id}
    </select>

#### 十四、分步查找，两条sql找出DepartmentWithEmployee

​    先写出根据部门id 找出员工的被调用select语句
​    再写出resultMap,其中的collection标签就是通过传入column【数据库中要有的字段】通过select语句返回出来
​    再写出调用者select语句，将部门select出来
​    

    <select id="selectDepartmentStep" resultType="cn.cxd.model.Employee">
        select id,gender,name,email,last_name lastName,dept_id deptId from employee where dept_id = #{dept_id}
    </select>
    
    <resultMap id="stepResultMap" type="cn.cxd.vo.DepartmentWithEmployee">
        <id column="id" property="deptId"></id>
        <result column="name" property="deptName"></result>
        <collection property="employees" column="id"
                    ofType="cn.cxd.model.Employee"
                    select="cn.cxd.mapper.EmployeeMapper.selectDepartmentStep"/>
    </resultMap>
    
    <select id="selectDepartmentWithEmployeeStep" resultMap="stepResultMap">
        select * from department where id = #{id}
    </select>

```
public DepartmentWithEmployee selectDepartmentWithEmployeeStep(int id);
```

#### 十五、鉴别器discriminator标签

​	column 指定了 MyBatis 查询被比较值的地方，而 javaType 用来确保使用正确的相等测试。
​    直接写成拓展形式，就会先对discriminator标签外的属性进行赋值，而后再根据case项，再拓展赋值进行覆盖。
​    如果是case-resultMap，那么是选择式的，仅使用resultMap里面的column赋值，其余的才是使用外部的
​    

    <resultMap id="boyEmployeeMap" type="cn.cxd.model.Employee">
        <result column="last_name" property="lastName"></result>
        <result column="last_name" property="email"></result>
    </resultMap>
    
    <resultMap id="discriminatorMap" type="cn.cxd.model.Employee">
        <id column="id" property="id"></id>
        <result column="name" property="name"></result>
        <result column="last_name" property="lastName"></result>
        <result column="email" property="email"></result>
        <result column="dept_id" property="deptId"></result>
        <result column="gender" property="gender"></result>
        <discriminator javaType="int" column="gender">
            <case value="1" resultMap="boyEmployeeMap"></case>
            <case value="2" resultType="cn.cxd.model.Employee">
                <result column="email" property="lastName"></result>
            </case>
        </discriminator>
    </resultMap>
    
    <select id="selectEmployeeDiscriminator" resultMap="discriminatorMap">
    	select id,gender,name,email,last_name,dept_id from employee where id = #{id};
    </select>
    

```
public Employee selectEmployeeDiscriminator(int id);
```

#### 十六、条件语句

##### 	第一种、where 1 = 1

​    

```
<select id="selectByWhereStat" resultType="cn.cxd.model.Employee">
    select * from employee where 1 = 1
    <if test="deptId != null">
        and dept_id = #{deptId}
    </if>
    <if test="gender != null and gender != ''">
        and gender = #{gender}
    </if>
</select>
```

##### 	第二种、使用< where>

​	< where>...若where子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。

    
    <select id="selectByWhereStat2" resultType="cn.cxd.model.Employee">
        select * from employee
        <where>
            <if test="deptId != null">
                and dept_id = #{deptId}
            </if>
            <if test="gender != null and gender != ''">
                and gender = #{gender}
            </if>
        </where>
    </select>



#### 十七、使用< trim>标签定制< where>示例

​	trim定制where：prefix前缀，suffix后缀，prefixOverrides忽略的子句前缀
	<select id="selectByTrimStat" resultType="cn.cxd.model.Employee">
	    select * from employee
	    <trim prefix="where" prefixOverrides="and |or ">
	        <if test="deptId != null">
	            and dept_id = #{deptId}
	        </if>
	        <if test="gender != null and gender != ''">
	            and gender = #{gender}
	        </if>
	    </trim>
	</select>
#### 十八、< choose>标签的使用

choose的case选择，仅进入一个

```
<where>
    <choose>
    	<when test></when>
   		<otherwise></otherwise>
    </choose>
</where>
```

    <select id="selectByChooseStat" resultType="cn.cxd.model.Employee">
        select * from employee
        <where>
            <choose>
                <when test="gender == 1">
                    id = 2
                </when>
                <when test="gender == 2">
                    id = 3
                </when>
                <otherwise>
                    id = 1
                </otherwise>
            </choose>
        </where>
    </select>

#### 十九、< set>标签 + 对应的< trim>标签

< set>标签，对于逗号在同前同后都能解决

```
<set>
   <if test></if>
</set>
```

    <update id="updateBySetStat">
        update employee
        <set>
            <if test="deptId != null">
                dept_id = #{deptId},
            </if>
            <if test="gender != null and gender != ''">
                gender = #{gender},
            </if>
        </set>
        <where>
            <if test="id != null">
                id = #{id}
            </if>
        </where>
    </update>

与之对应的可使用trim：

```
<update id="updateByTrimStat">
update employee
    <trim prefix="set" prefixOverrides=",">
        <if test="deptId != null">
        	dept_id = #{deptId}
        </if>
        <if test="gender != null">
        	,gender = #{gender}
        </if>
    </trim>
    <where>
        <if test="id != null">
       		id = #{id}
        </if>
    </where>
</update>
```

#### 二十、使用foreach两种批量插入，以及集合查询

foreach 
        collection:list,collection,map,hashmap,arraylist,array
        open,close,separator,item
          其中 item相当于是    for(Object item : collection)
          collection如果是单参数输入:list,collection,map,hashmap,arraylist,array
          如果是一个map包含一个list 则取key
          如果是多参数，则使用自己的注解@Param，或是根据位置的param1,param2
    

##### 第一种，批量插入一

​	使用自增主键,批量插入，需要数据库支持才行，mysql支持，
​    如果传入的是对象，那么要使用keyProperty="jobId"【指的是model对象的成员变量】          useGeneratedKeys="true"
​    如果仅仅是几个字段，那么不使用keyProperty，useGeneratedKeys
​    insert into xx (a,b,c) values (a,b,c),(d,e,f),..

    <insert id="insertForeachStat1" keyProperty="id" useGeneratedKeys="true">
        insert into employee
        <foreach collection="list" item="e" separator=","
                 open="(email,gender,last_name,dept_id,name) values">
            (#{e.email},#{e.gender},#{e.lastName},#{e.deptId},#{e.name})
        </foreach>
    </insert>
    

##### 第二种，批量插入二

使用自增主键,批量插入，需要mysql连接属性allowMultiQueries=true

insert into xx (a,b,c) values (a,b,c);insert into ..

```
<insert id="insertForeachStat2" keyProperty="id" useGeneratedKeys="true">
    <foreach collection="collection" item="e" separator=";">
        insert into employee (email,gender,last_name,dept_id,name) values (#{e.email},#{e.gender},#{e.lastName},#{e.deptId},#{e.name})
    </foreach>
</insert>
```

##### 集合查询：

```
<select id="selectForeachStat" resultType="cn.cxd.model.Employee">
    select id,name,email,gender,last_name lastName,dept_id deptId from employee
    <where>
        <foreach collection="list" item="id" separator="," open="id in (" close=")">
            #{id}
        </foreach>
    </where>
</select>
```

#### 二十一、两个内置参数 _ parameter , _ databaseid，用于< if  test>

#### 二十二、模糊查询

##### 1.like #{name}  ，name传入时为%target%

```
<select id="selectLike2" resultType="cn.cxd.model.Employee">
    select id,name,email,gender,last_name lastName,dept_id deptId from employee
    <where>
        name like "%${name}%"
    </where>
</select>
```



##### 2.like '%${name}%'  name传入时为target，字符串拼接，不安全

```

```



##### 3.< bind name="bindName" value="'%' + name + '%'">

​    在select中定义变量  like #{bindName}

```
<select id="selectLike3" resultType="cn.cxd.model.Employee">
    <bind name="partName" value="'%'+name+'%'"/>
    select id,name,email,gender,last_name lastName,dept_id deptId from employee
    <where>
        name like #{partName}
    </where>
</select>
```

#### 二十三、sql标签的重用代码

 需要用 include-refid，来引用sql-id


    <sql id="selectItems">
        id,name,email,gender,last_name lastName,dept_id deptId
    </sql>
    
    <sql id="fromWhereIdCondition">
        from employee where id = #{id}
    </sql>
    
    <select id="selectSqlStat" resultType="cn.cxd.model.Employee">
        select
        <include refid="selectItems"></include>
        <include refid="fromWhereIdCondition"></include>
    </select>

#### 二十四、一级缓存和二级缓存

​    **一级缓存是sqlSession域的，**

​	**二级缓存是namespace域的，但是可以被引用< cache-ref>以共享同一份缓存。**
​    **当sqlSession关闭或是提交时，会将缓存存入二级缓存。**

    默认语句的设置：
    useCache使用二级缓存，一级缓存是默认使用的
    flushCache清空一级二级缓存
    sqlSession.clearCache():清空一级缓存
    <select ... flushCache="false" useCache="true"/>
    <insert ... flushCache="true"/>
    <update ... flushCache="true"/>
    <delete ... flushCache="true"/>
    
    使用Ehcache: mybatis-ehcache,ehcache-core,slf4j-api,slf4j-core
    mapper.xml下配置：
    <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
    sqlConfig.xml下配置：
    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

#### 二十五、调用存储过程

​	一个插入的表操作示例：

```
 <insert id="callYearBonus" statementType="CALLABLE">
 	{CALL calu_monthly(#{id,jdbcType=VARCHAR,mode=IN})}
 </insert>
```

​	存储过程：

```
DELIMITER //
CREATE PROCEDURE calu_monthly (IN tmpDate VARCHAR(20))
BEGIN	
	DECLARE temp_money DOUBLE;
	DECLARE temp_count INT;
	DECLARE temp_count2 INT;
	DECLARE temp_deptId INT;
	DECLARE temp_jobId INT;
	DECLARE temp_dayly_salary DOUBLE;
	
	DECLARE temp_tarMoney DOUBLE;
	
	DECLARE temp_id INT;
	DECLARE finish INT DEFAULT 0;
	DECLARE id_list CURSOR FOR SELECT id FROM employeeinfo;
	# 为下面while循环建立一个退出标志，当游标遍历完后将flag的值设置为1
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finish=1;
	OPEN id_list;  # 打开游标
	# 将游标中的值赋给定义好的变量，实现for循环的要点
	FETCH id_list INTO temp_id;
		WHILE finish <> 1 DO
		# sql提供了字符串的切分，有left、right、substring、substring_index
		# 在T-SQL中，局部变量必须以@作为前缀，声明方式set，select还有点差别
		SELECT job_salary INTO temp_money FROM job NATURAL JOIN  employeeinfo WHERE id = temp_id;
		SELECT COUNT(*) INTO temp_count FROM attendance WHERE e_id = temp_id AND DATE LIKE CONCAT(tmpDate,'%') AND flag = 1 ;
		SELECT COUNT(*) INTO temp_count2 FROM attendance WHERE e_id = temp_id AND DATE LIKE CONCAT(tmpDate,'%') AND flag = 0 ;
		SELECT dept_id INTO temp_deptId FROM employeeinfo WHERE id = temp_id;
		
		SELECT job_id INTO temp_jobId FROM employeeinfo WHERE id = temp_id;
		SELECT job_salary/30 INTO temp_dayly_salary FROM job WHERE job_id = temp_jobId;
		
		SET temp_tarMoney = temp_money - temp_count * 40 - temp_dayly_salary * temp_count2;
		INSERT INTO monthlywages (e_id,dept_id,DATE,salary) VALUES (temp_id,temp_deptId,tmpDate,temp_tarMoney);
		FETCH id_list INTO temp_id;
		END WHILE;
		#select * from temp_table;
	CLOSE id_list;  # 关闭游标
END

```

