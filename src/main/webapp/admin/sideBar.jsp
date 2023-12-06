<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.do">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Trico Paper <!-- <sup>2</sup> --></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.do">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>
<!-- #### 낙자 메뉴 추가s #### -->
	<!-- Divider -->
	<hr class="sidebar-divider">
	<!-- 메뉴 대표 텍스트 -->
    <div class="sidebar-heading">
        메뉴명
    </div>
    <!-- 단독메뉴 -->
	<li class="nav-item">
		<a class="nav-link" href="print01.do">
		<i class="fas fa-fw fa-chart-area"></i><span>엑셀파일업로드</span></a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="print02.do">
		<i class="fas fa-fw fa-chart-area"></i><span>업로드성공목록</span></a>
	</li>
	<!--Collapse 메뉴 -->
	<li class="nav-item">
	    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#YuGyeom01"
	        aria-expanded="true" aria-controls="YuGyeom01">
	        <i class="fas fa-fw fa-cog"></i>
	        <span>펼침메뉴</span>
	    </a>
	    <div id="YuGyeom01" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
	        <div class="bg-white py-2 collapse-inner rounded">
	            <a class="collapse-item" href="#">세부메뉴1</a>
	            <a class="collapse-item" href="#">세부메뉴2</a>
	        </div>
	    </div>
	</li>
<!-- #### 낙자 메뉴 추가e #### -->
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- 메뉴 대표 텍스트 -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!--Collapse 메뉴 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Components</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="buttons.do">Buttons</a>
                        <a class="collapse-item" href="cards.do">Cards</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Utilities</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Utilities:</h6>
                        <a class="collapse-item" href="utilities-color.do">Colors</a>
                        <a class="collapse-item" href="utilities-border.do">Borders</a>
                        <a class="collapse-item" href="utilities-animation.do">Animations</a>
                        <a class="collapse-item" href="utilities-other.do">Other</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="login.do">Login</a>
                        <a class="collapse-item" href="register.do">Register</a>
                        <a class="collapse-item" href="forgot-password.do">Forgot Password</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Other Pages:</h6>
                        <a class="collapse-item" href="404.do">404 Page</a>
                        <a class="collapse-item" href="blank.do">Blank Page</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="charts.do">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Charts</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="tables.do">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Tables</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            <!-- Sidebar Message -->
            <div class="sidebar-card d-none d-lg-flex">
                <img class="sidebar-card-illustration mb-2" src="/sbadmin/img/undraw_rocket.svg" alt="...">
                <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p>
                <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a>
            </div>

        </ul>