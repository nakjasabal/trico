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
$(function(){
	$('#printBtn').click(function(){
		open('onlyPrint.do','print','left=0,top=0');
	});
});
function validateForm(form) { 
    if (form.ofile.value == "") {
        alert("엑셀 파일은 필수 입력입니다.");
        return false;
    }
}
</script>
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">
    	프린트 테스트
    	<button type='button' id="printBtn">인쇄하기</button>
    </h1>
    
    <div>
	    <form name="fileForm" method="post" enctype="multipart/form-data"
	    	action="uploadProcess.do" onsubmit="return validateForm(this);"> 
	    	엑셀파일 : <input type="file" name="ofile" />
	    	<input type="submit" value="업로드" />
	    </form>
	    <div>
	    	<h3>파일업로드결과</h3>
	    	<ul>
	    		<li>원본파일명:${originalFileName}</li>
	    		<li>저장된파일명:${savedFileName}</li>
	    	</ul>
	    	<p>${sb}</p>
	    </div>
	    <div>
	    <c:forEach items="${rows}" var="row">
	    	<li>${row.idx} : ${row.name}</li>
	    </c:forEach>
	    </div>
    </div>

</div>
<!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
<%@ include file = "/admin/bottom.jsp" %>
