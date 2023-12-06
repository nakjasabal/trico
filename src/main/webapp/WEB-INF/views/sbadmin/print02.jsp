<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<%@ include file="/admin/header.jsp" %>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <%@ include file="/admin/sideBar.jsp" %>
        <!-- End of Sidebar -->
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Topbar -->
                <%@ include file="/admin/topBar.jsp" %>
                <!-- End of Topbar -->
<!-- Begin Page Content -->
<script>
function printOpen(idx) { 
	open('onlyPrint.do?idx='+idx,'print','left=0,top=0,width=1000,height=900');
}

</script>
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">
    	엑셀 목록 보기    	
    </h1>
     
    <table class="table table-bordered">
    <tr>
	    <th>날짜</th>
	    <th>번호</th> 
	    <th>상호</th> 
	    <th>지종</th> 
	    <th>규격</th> 
	    <th>비고</th> 
	    <th>톤수</th> 
	    <th>내용</th> 
	    <th>도착지</th> 
	    <th>실출고</th> 
	    <th>번호</th> 
	    <th>1호</th> 
	    <th>2호</th> 
	    <th>자차</th> 
	    <th>용차</th> 
	    <th>용차번호</th>
	    <th>출력</th>
	</tr>	  
    <c:forEach items="${rows}" var="row">
    	<tr>
    		<td>${row.col00}</td>
    		<td>${row.col01}</td>
    		<td>${row.col02}</td>
    		<td>${row.col03}</td>
    		<td>${row.col04}</td>
    		<td>${row.col05}</td>
    		<td>${row.col06}</td>
    		<td>${row.col07}</td>
    		<td>${row.col08}</td>
    		<td>${row.col09}</td>
    		<td>${row.col10}</td>
    		<td>${row.col11}</td>
    		<td>${row.col12}</td>
    		<td>${row.col13}</td>
    		<td>${row.col14}</td>
    		<td>${row.col15}</td>
    		<td><button type='button' onclick="printOpen('${row.idx}');">인쇄</button></td>
    	</tr>
    </c:forEach>
    </table>
</div>
<!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
<%@ include file = "/admin/bottom.jsp" %>