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
    	엑셀 파일 업로드
    </h1>
    
	<div class="card shadow mb-4">
		<div class="card-header py-3">
		    <h6 class="m-0 font-weight-bold text-primary">구글 Sheet에서 다운로드 한 파일을 등록해주세요.</h6>
		</div>
		<div class="card-body">
			<form name="fileForm" method="post" enctype="multipart/form-data"
				action="uploadProcess.do" onsubmit="return validateForm(this);"> 		
			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			<colgroup>
				<col width="150px"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th>xlsx파일</th>
				<td>
					<input type="file" name="ofile" class="form-control" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<input type="submit" value="업로드" class="btn btn-success" />
				</td>
			</tr>
			</table>
			</form>   
		</div>
	</div>
    <div class="card shadow mb-4">
		<div class="card-header py-3">
		    <h6 class="m-0 font-weight-bold text-primary">
		    	파일업로드 결과
		    </h6>
		</div>
		<div class="card-body">
		<c:if test="${not empty savedFileName}" var="result">
			<ul>
	    		<li>원본파일명 => ${originalFileName}</li>
	    		<li>저장된파일명 => ${savedFileName}</li>
	    	</ul>
	    	<h4>
	    		업로드 한 파일이 DB에 등록되었습니다. '출고목록'으로 이동해주세요.
	    	</h4>
	    </c:if>
	    <c:if test="${result eq false }">
	    	업로드 준비중입니다.
	    </c:if>
		</div>
    </div>
</div>
<!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
<%@ include file = "/admin/bottom.jsp" %>