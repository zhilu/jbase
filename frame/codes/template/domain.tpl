package [(${packageName})].bo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体:[(${table.tableComment})]
 * 
 * @author [(${classAuthor})]
 */
public class [(${ClassName})]{
[# th:each="column : ${columns}"]
    /**
     * [(${column.columnComment})]
     */
    private [(${column.javaDataType})] [(${column.columnName})];
[/]
[# th:each="column : ${columns}"]
    public [(${column.javaDataType})] get[(${#strings.capitalize(column.columnName)})](){
        return [(${column.columnName})];
    }
    
    public void set[(${#strings.capitalize(column.columnName)})]([(${column.javaDataType})] [(${column.columnName})]){
        this.[(${column.columnName})] = [(${column.columnName})];
    }
    
[/] 
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}