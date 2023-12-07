<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	margin: 0;
	padding: 0;
}
* {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
}
.page {
	width: 21cm;
	min-height: 29.7cm;
	padding: 2cm;
	margin: 0 auto;
	background:#eee;
}
.subpage {
	border: 2px red solid;
	background:#fff;   
	height: 257mm;
}
@page {
	size: A4;
	margin: 0;
}
@media print {
	html, body {
		width: 210mm;
		height: 297mm;
	}
	.page {
		margin: 0;
		border: initial;
		width: initial;
		min-height: initial;
		box-shadow: initial;
		background: initial;
		page-break-after: always;
	}
}

table{border:5px solid black;border-collapse: collapse;width: 100%;}
table td{border:5px solid black;font-size: 2.4em;text-align: center;
  padding: 18px 15px;font-family: 궁서체;}
</style>
</head>
<body>
<!-- ############################################# -->
<%
/**
롤재단 => getSheetAt(4)
날짜 번호 상호 지종 규격 비고 톤수 내용 도착지 실출고 번호 1호 2호 자차 용차 용차번호  
0    1    2    3    4    5    6     7   8       9     10   11  12  13   14   15   
-------------------------------------------------------------------------------------------
길로틴 => getSheetAt(5)
날짜 번호 상호 지종 규격 연수(R) 내용 도착지 번호 자차 용차 용차번호 기사님
0    1    2	   3    4    5       6     7     8     9   10    11       12
*/
%>    
	<div class="page">
		<div class="subpage">			
			<table>
			<colgroup>
				<col width="30%" />
				<col width="*" />
			</colgroup>
			<tr>
				<td>발주처</td>
				<td>${row.col02}</td>
			</tr>
			<tr>
				<td colspan='2' style="margin:0px;padding:0px;height:20px;"></td>
			</tr>
			<tr>
				<td colspan="2">☆출고품목★</td>
			</tr>
			<tr>
				<td>지종</td>
				<td>${row.col03}</td>
			</tr>				
			<tr>
				<td>평량</td>
				<td>${otherInfo.pyeong}</td>
			</tr>
			<tr>
				<td>규격</td>
				<td>${otherInfo.gyugyeog}</td>
			</tr>
			<tr>
				<td>수량</td>
				<td>${row.etc01}</td>
			</tr>
			<tr>
				<td>품명</td>
				<td>???</td>
			</tr>
			<tr>
				<td>입고처</td>
				<td>${row.col08}</td>
			</tr>
			<tr>
				<td>출고일</td>
				<td>${row.col00}</td>
			</tr>
			<tr>
				<td colspan='2'>
					트리코 페이퍼 <br />
					031-267-1246
				</td>
			</tr>
			</table>
		</div>
	</div>
<!-- ############################################# -->
</body>
</html>