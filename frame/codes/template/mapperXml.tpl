<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="[(${packageName})].mapper.[(${ClassName})]Mapper">
    <resultMap id="BaseResultMap" type="[(${packageName})].bo.[(${ClassName})]">[# th:each="column : ${columns}"]
    [# th:if="${column.orginColumnName==id}"]<id column="[(${column.orginColumnName})]" property="[(${column.columnName})]" jdbcType="[(${column.jdbcDataType})]" />[/][# th:if="${column.orginColumnName!=id}"]<result column="[(${column.orginColumnName})]" property="[(${column.columnName})]" jdbcType="[(${column.jdbcDataType})]" />[/][/]
    </resultMap>
    
    <sql id="Base_Column_List">
        [# th:each="column,columnStat : ${columns}"][(${column.orginColumnName})][# th:if="${columnStat.index}+1 != ${columnStat.size}"],[/][/]
    </sql>
    
    <sql id="searchBy">
        <trim prefix="where" prefixOverrides="and|or">
        [# th:each="column : ${columns}"]
            <if test="[(${column.columnName})] != null and [(${column.columnName})] != ''">
                and [(${column.columnName})] = #{[(${column.columnName})],jdbcType=[(${column.jdbcDataType})]}
            </if>
        [/]
        </trim>
    </sql>
    
    <insert id="insert" parameterType="[(${packageName})].bo.[(${ClassName})]">
        insert into [(${table.tableName})]
        (<include refid="Base_Column_List" />)
        values
        ([# th:each="column,columnStat : ${columns}"]#{[(${column.columnName})],jdbcType=[(${column.jdbcDataType})]}[# th:if="${columnStat.index}+1 != ${columnStat.size}"],[/][/])
    </insert>
    

     <update id="update" parameterType="[(${packageName})].bo.[(${ClassName})]">
        update [(${table.tableName})] set [# th:each="column,columnStat : ${columns}"]
		    [# th:if="${column.orginColumnName!=id}"][(${column.orginColumnName})] = #{[(${column.columnName})],jdbcType=[(${column.jdbcDataType})]}[# th:if="${columnStat.index}+1 != ${columnStat.size}"],[/][/][/]
        where id = #{id ,jdbcType=BIGINT}
    </update>
    
    <select id="findByPrimary" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List" />
        from [(${table.tableName})]
        where id = #{id,jdbcType=BIGINT}
    </select>
    
    <select id="findSelective" resultMap="BaseResultMap" parameterType="java.util.HashMap">
        select
        <include refid="Base_Column_List" />
        from [(${table.tableName})]
        <include refid="searchBy"/> 
        limit 0,1
    </select>
               
    <select id="listSelective" resultMap="BaseResultMap" parameterType="java.util.HashMap">
        select
        <include refid="Base_Column_List" />
        from [(${table.tableName})]
        <include refid="searchBy"/>
    </select>

	<delete id="delete" parameterType="java.lang.Long">
        delete from [(${table.tableName})] where id = #{id,jdbcType=BIGINT}
	</delete>
</mapper>