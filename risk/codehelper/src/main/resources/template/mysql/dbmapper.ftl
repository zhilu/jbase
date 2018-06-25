<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ${tableName}表-->
<mapper namespace="${packageName}.mapper.${className}Mapper">
    <!--返回Map-->        
    <resultMap id="BaseResultMap" type="${packageName}.bo.${className}">
    <#list list as column>
    <#if column.columnName == "id">
        <id column="${column.fieldName}" property="${column.columnName}" jdbcType="${column.jdbcType}" />
    <#else >
        <result column="${column.fieldName}" property="${column.columnName}" jdbcType="${column.jdbcType}" />
    </#if>
    </#list>
    </resultMap>
    
    <!--基本的sql查询字段 公共引用...-->
    <sql id="Base_Column_List">
        <#list list as column>${column.fieldName}<#if column_index+1 != listSize>,<#else> </#if></#list>
    </sql>
    
    <!-- 基本的sql查询条件公共引用 -->
    <sql id="searchBy">
        <trim prefix="where" prefixOverrides="and|or">
        <#list list as column>
            <#if column.columnName == "id">
            <if test="id !=null and id !=''">
                id  = #${leftBraces}id,jdbcType=${column.jdbcType}${rightBraces}
            </if>
            <#elseif column.dataType == "Date">
            <if test="${column.columnName} !=null">
                and ${column.fieldName} = #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces}
            </if>
            <#else >
            <if test="${column.columnName} != null and ${column.columnName} != ''">
                and ${column.fieldName} = #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces}
            </if>
        </#if >
    </#list>
        </trim>
    </sql>
    
    
    <insert id="save" parameterType="${packageName}.bo.${className}">
        insert into ${tableName}(<#list list as column><#if column.columnName == "id"><#else>${column.fieldName}<#if column_index+1 != listSize>,<#else></#if></#if></#list>)values(<#list list as column><#if column.columnName == "id"><#else >#${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces}<#if column_index+1 != listSize>,<#else></#if></#if ></#list>)
    </insert>

    
    <insert id="saveRecord" parameterType="${packageName}.bo.${className}" keyProperty="id">
    	<selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >
	      SELECT LAST_INSERT_ID()
	    </selectKey>
    
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list list as column>
	        <#if column.columnName != "id">
	        <if test="${column.columnName} != null">        
	            ${column.fieldName},
	        </if>
	        </#if>
	     </#list>
        </trim>
        
        <trim prefix="values (" suffix=")" suffixOverrides="," >
      	<#list list as column>
	        <#if column.columnName != "id">
		    <if test="${column.columnName} != null" >
		       #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces},
		    </if>
	        </#if >
        </#list>
        </trim>
    </insert>
    
    
    <update id="update" parameterType="${packageName}.domain.${className}">
        update ${tableName} set 
         <#list list as column>
            <#if column.columnName == "id">
            <#else >
            ${column.fieldName} = #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces}<#if column_index+1 != listSize>,<#else></#if>

            </#if >
        </#list>
        where id = #${leftBraces}id ,jdbcType=INTEGER${rightBraces}
    </update>


    <update id="updateSelective"  parameterType="java.util.HashMap">
        update ${tableName}
          <set>
            <#list list as column>
            <#if column.columnName == "id">
            <#else >
            <if test="${column.columnName} != null and ${column.columnName} != '' ">        
                ${column.fieldName} = #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces}<#if column_index+1 != listSize>,<#else></#if>
                
            </if>
            </#if >
            </#list>
        </set>    
        where id = #${leftBraces}id ,jdbcType=INTEGER${rightBraces}
    </update>


    <select id="findByPrimary" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where id = #${leftBraces}id,jdbcType=INTEGER${rightBraces}
    </select>

	<select id="findSelective" resultMap="BaseResultMap" parameterType="java.util.HashMap">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        <include refid="searchBy"/> 
        limit 0,1
    </select>
               
    <select id="listSelective" resultMap="BaseResultMap" parameterType="java.util.HashMap">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        <include refid="searchBy"/>
    </select>
    
</mapper>
