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
<style>
#myTable {}
#myTable th{text-align: center;vertical-align: middle;}
</style>
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">
    	롤제단 출고목록
    </h1>
    
    <div class="card shadow mb-4">
		<div class="card-header py-3">
		    <h6 class="m-0 font-weight-bold text-primary">가장 최근에 업로드한 Excel의 내용이 출력됩니다.</h6>
		</div>
		<div class="card-body">     
		
		
	<ul class="pagination justify-content-end" style="margin:0 0 10px 0;">
		<li class="page-item"><a class="page-link" href="#">Prev</a></li>
		<c:forEach items="${flagList}" var="item" varStatus="loop">
			
			<li class="page-item"><a class="page-link" href="?flagNum=${item.flag}">${loop.count}</a></li>	
		</c:forEach>
		<li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul>
<script>
function printOpen(idx) {
	let fm = document.realPost;
	fm.idx.value = idx; 
	fm.etc01.value = eval('document.getElementById("etc01_'+idx+'")').value;
	
	$.ajax({
		url : './directInput.do',  
		type : 'get', 
		dataType : "text", 
		data : {
			idx : idx,
			etc01 : $('#etc01').val()
			/* 필요하다면 순서대로 추가 */
		},
		success : function(resData){
			console.log(resData);
			open('onlyPrint.do?idx='+idx,'print','left=0,top=0,width=1000,height=900');
		},		 
		error : function(errData){ 
			console.log(errData.state, errData.statusText); 
		},
	}); 
	
}
</script>
<form name="realPost">
	<input type="hidden" name="idx" id="idx" /><!-- 일련번호 -->
	<input type="hidden" name="etc01" id="etc01" /><!-- 수량 -->
	<input type="hidden" name="etc02" id="etc02" /><!-- 미정.. -->
	<input type="hidden" name="etc03" id="etc03" /><!-- 미정.. -->
</form>		
    <table class="table table-bordered" id="myTable">
    <colgroup>
    	<col width="*" /><col width="*" /><col width="*" /><col width="*" /><col width="*" />
    	<col width="*" /><col width="*" /><col width="*" /><col width="80px" />
    	<col width="*" />
    </colgroup>
    <tr>
	    <th>날짜</th>
	    <th>번호</th> 
	    <th>상호</th> 
	    <th>지종</th> 
	    <th>규격</th> 
	    <!-- <th>비고</th>  -->
	    <th>톤수</th> 
	    <th>내용</th> 
	    <th>도착지</th> 
	    <th>실출고</th> 
	    <!-- <th>번호</th> 
	    <th>1호</th> 
	    <th>2호</th> 
	    <th>자차</th> 
	    <th>용차</th> 
	    <th>용차번호</th> -->
	    <th>입력</th> 
	    <th>출력</th>
	</tr>	  
    <c:forEach items="${rows}" var="row">
    	<c:if test="${not empty row.col01 and not empty row.col02}">
    	<tr>
    		<td>${row.col00}</td>
    		<td>${row.col01}</td>
    		<td>${row.col02}</td>
    		<td>${row.col03}</td>
    		<td>${row.col04}</td>
    		<%-- <td>${row.col05}</td> --%>
    		<td>${row.col06}</td>
    		<td>${row.col07}</td>
    		<td>${row.col08}</td>
    		<td>${row.col09}</td>
    		<%-- <td>${row.col10}</td>
    		<td>${row.col11}</td>
    		<td>${row.col12}</td>
    		<td>${row.col13}</td>
    		<td>${row.col14}</td>
    		<td>${row.col15}</td> --%>
    		<td>
    			수량: <input type="text" id="etc01_${row.idx}" value="${row.etc01}" style="width:60px;" />
    		</td>
    		<td><button type='button' onclick="printOpen('${row.idx}');" class="btn btn-danger" style="width:60px;">인쇄</button></td>
    	</tr>
    	</c:if>
    </c:forEach>
    </table>
    	</div>
    </div>
</div>
<!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
<%@ include file = "/admin/bottom.jsp" %>