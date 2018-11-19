<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	
	String rootPath = application.getRealPath( "/" );
	
//	out.write( new ActionEnter( request, rootPath ).exec() );
	String result = new ActionEnter( request, rootPath ).exec();
	String action = request.getParameter("action");
	//图片、附件在线管理中回显时，格式化回显路径，否则无法正常格式化
	if( action!=null && (action.equals("listfile") || action.equals("listimage"))){
	    rootPath = rootPath.replace("\\", "/");
	    result = result.replaceAll(rootPath, "/");
	}
	out.write( result );

%>