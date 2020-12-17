<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<%@ attribute name="value" type="java.lang.String" required="true" %>
<%
	value = value.replace("\n", "\n<br>");
	value = value.replace("&", "&amp");
	value = value.replace("<", "&lt");
	value = value.replace(" ", "&nbsp");
%>
<%= value%>