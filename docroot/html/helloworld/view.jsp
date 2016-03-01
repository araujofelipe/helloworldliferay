<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />
<%

String nome = (String)request.getAttribute("ATTRIBUTE_NAME");

%>
<portlet:actionURL name="vaiComDeus" var="tchauURL" />
<p>Fala ae, <%= nome %></p>
<form action='<portlet:actionURL />' method="post" name='<portlet:namespace/>fm' >
	<input type="text" name='<portlet:namespace/>nome'  />
	<input type="submit" />
	
</form>

