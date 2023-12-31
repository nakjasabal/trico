<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/sbadmin/css/sb-admin-2.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
<div class="text-center">
<c:if test="${param.error != null}" var="result">
    <h1 class="h4 text-gray-900 mb-4">${errorMsg}</h1>	
</c:if>
<c:if test="${not result }">
	<h1 class="h4 text-gray-900 mb-4">관리자모드</h1>
</c:if>
</div>
<script>
function loginValidate(){
	let f = document.adminFrm;
	if(f.admin_id.value==''){
		alert('아이디를 입력하세요.');f.admin_id.focus();return false;
	}
	if(f.admin_pass.value==''){
		alert('패스워드를 입력하세요.');f.admin_pass.focus();return false;
	}
	f.method="post";
	f.action="/admin34/loginAction.do";
	//f.submit();
}
</script>                                    
<form name="adminFrm" class="user" onsubmit="return loginValidate();">
    <div class="form-group">
        <input type="text" class="form-control form-control-user"
            id="exampleInputEmail" aria-describedby="emailHelp"
            placeholder="Enter admin id..." name="admin_id">
    </div>
    <div class="form-group">
        <input type="password" class="form-control form-control-user"
            id="exampleInputPassword" 
            placeholder="Password" name="admin_pass">
    </div>
    <div class="form-group">
        <div class="custom-control custom-checkbox small">
            <input type="checkbox" class="custom-control-input" id="customCheck">
            <label class="custom-control-label" for="customCheck">아이디저장</label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary btn-user btn-block">
        Login
    </button>
    <button type="button" class="btn btn-info btn-user btn-block" onclick="location.href='index.do';">
        바로들어가기
    </button>
    <!-- <hr>
    <a href="index.do" class="btn btn-google btn-user btn-block">
        <i class="fab fa-google fa-fw"></i> Login with Google
    </a>
    <a href="index.do" class="btn btn-facebook btn-user btn-block">
        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
    </a> -->
</form>
                                    <!-- <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.do">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.do">Create an Account!</a>
                                    </div> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
    <script src="/sbadmin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/sbadmin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/sbadmin/js/sb-admin-2.min.js"></script>

</body>

</html>