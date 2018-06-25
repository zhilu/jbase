<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
$(function()
		{
	
	$("#testJson").click(function()
			{
		var url=this.href;
		var args={};
		
		$.post(url,args,function(data){
			var jsonObj=eval(data);
			
			for(var i=0,l=jsonObj.length;i<l;i++){
				var id=jsonObj[i].id;
				var lastName=jsonObj[i].lastName;
				
				alert(id+":"+lastName);
			 }
		});
		
		return false;
			});
		});
</script>
</head>
<body>
文件上传
<form action="testFileloadUpdate" method="post"  enctype="multipart/form-data">
	file:<input type="file" name="file">
	desc:<input type="text" name="desc">
	<input type="submit" name="submit">
</form>
<br><br>

<a href="emps">all Employee</a>
<br><br>
<a href="testJson" id="testJson">test Json</a>
<br><br>

<form action="testHttpMessageConverter" method="post"  enctype="multipart/form-data">
	file:<input type="file" name="file">
	desc:<input type="text" name="desc">
	<input type="submit" name="submit">
</form>
<br><br>
<a href="testResposeEntity">test ResposeEntity</a>
<br><br>
<!-- 
关于国际化
1.在页面上根据浏览器语言设置的情况对文本（内容），时间，数值进行本地化处理
2.可以在bean中获取国际化资源文件Locale对应的消息
3.可以通过超链接切换Locale，而不依赖于浏览器的语言设置情况

解决：
1.使用JSTL的fmt标签
2.在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法即可
3.需要配置LocalResolver和LocaleChangeInterceptor
 -->
 <a href="i18n">i18n Page</a>
 <br><br>
 
  <a href="testExceptionHandlerExceptionResolver?i=10">Test ExceptionHandlerExceptionResolver</a>
  <br><br>
   <a href="testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>
  <br><br>
   <a href="testDefaulHandlerExceptionResolver">Test DefaulHandlerExceptionResolver</a>
 
   <br><br>
   <a href="testDefaulHandlerExceptionResolver">Test DefaulHandlerExceptionResolver</a>
  <br><br>
   <a href="testSimpleMappingExceptionResolver?i=1">Test SimpleMappingExceptionResolver</a>
 
 
 
 
</body>
</html>