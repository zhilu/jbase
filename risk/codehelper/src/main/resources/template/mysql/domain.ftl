package ${packageName}.bo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体: ${tableComment}
 * 
 * @author ${classAuthor}
 * @date ${classDate}
 */
 
 public class ${className}{
    <#list list as item>
    <#if item.columnName == "id">
    /**
     * 主键Id
     */
    private Long id;
    <#else >
    /**
     * ${item.columnComment}
     */
    private ${item.dataType} ${item.columnName};
    </#if >
    </#list>
    
    <#list list as item>
    <#if item.columnName == "id">
    public Long getId(){
      return id;
    }
    
    public void setId(Long id){
      this.id = id;
    }
    
    <#else >
    public ${item.dataType} get${item.accessColumnName}(){
      return ${item.columnName};
    }
    
    public void set${item.accessColumnName}(${item.dataType} ${item.columnName}){
      this.${item.columnName} = ${item.columnName};
    }
    
    </#if >
    </#list>
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}