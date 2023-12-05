<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
table td{border:5px solid black;font-size: 2.5em;text-align: center;
  padding: 18px 15px;font-family: 궁서체;}
</style>
</head>
<body>
<!-- ############################################# -->    
	<div class="page">
		<div class="subpage">			
			<table>
				<tr>
					<td>발주처</td>
					<td>진우인쇄</td>
				</tr>
				<tr>
					<td colspan='2'></td>
				</tr>
				<tr>
					<td colspan="2">☆출고품목★</td>
				</tr>
				<tr>
					<td>지종</td>
					<td>SC</td>
				</tr>
				<tr>
					<td>평량</td>
					<td>240</td>
				</tr>
				<tr>
					<td>규격</td>
					<td>720*650</td>
				</tr>
				<tr>
					<td>수량</td>
					<td>1R</td>
				</tr>
				<tr>
					<td>품명</td>
					<td>클림슬라임</td>
				</tr>
				<tr>
					<td>입고처</td>
					<td>09월 14일</td>
				</tr>
				<tr>
					<td>출고일</td>
					<td>진우인쇄(031-908-1479)</td>
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