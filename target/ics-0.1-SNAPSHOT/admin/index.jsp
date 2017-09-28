<%@ page pageEncoding="UTF-8"%>
<%
    String p = request.getParameter("p");
        if(p == null || p.equals("")){
        p = "Overview";
    }
    pageContext.setAttribute("p",p.toLowerCase());
%>
<%@ include file="_header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="_sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <jsp:include page="${p}.jsp"></jsp:include>
        </div>
    </div>

</div>
<%@ include file="_footer.jsp"%>


