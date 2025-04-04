<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<link rel="icon" type="image/png" href="favicon.ico">
<!--Google Font link-->
<link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/slick/slick.css"> 
<link rel="stylesheet" href="../assets/css/slick/slick-theme.css">
<link rel="stylesheet" href="../assets/css/animate.css">
<link rel="stylesheet" href="../assets/css/iconfont.css">
<link rel="stylesheet" href="../assets/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/css/bootstrap.css">
<link rel="stylesheet" href="../assets/css/magnific-popup.css">
<link rel="stylesheet" href="../assets/css/bootsnav.css">
<!-- xsslider slider css -->
<!--<link rel="stylesheet" href="../assets/css/xsslider.css">-->
<!--For Plugins external css-->
<!--<link rel="stylesheet" href="../assets/css/plugins.css" />-->
<!--Theme custom css -->
<link rel="stylesheet" href="../assets/css/style.css">
<!--<link rel="stylesheet" href="../assets/css/colors/maron.css">-->
<!--Theme Responsive css-->
<link rel="stylesheet" href="../assets/css/responsive.css" />
<script src="../assets/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>
<body>

	<!-- Preloader --> 
    <div id="loading">
        <div id="loading-center">
            <div id="loading-center-absolute">
                <div class="object" id="object_one"></div>
                <div class="object" id="object_two"></div>
                <div class="object" id="object_three"></div>
                <div class="object" id="object_four"></div>
            </div>
        </div>
    </div>
    <!-- End off Preloader -->

    <div class="culmn">
    	<tiles:insertAttribute name="header"/>
    	<tiles:insertAttribute name="content"/>
    	<tiles:insertAttribute name="footer"/>
    </div>

    <!-- JS includes -->
    <script src="../assets/js/vendor/jquery-1.11.2.min.js"></script>
    <script src="../assets/js/vendor/bootstrap.min.js"></script>
    <script src="../assets/js/jquery.magnific-popup.js"></script>
    <script src="../assets/js/jquery.easing.1.3.js"></script>
    <script src="../assets/css/slick/slick.js"></script>
    <script src="../assets/css/slick/slick.min.js"></script>
    <script src="../assets/js/jquery.collapse.js"></script>
    <script src="../assets/js/bootsnav.js"></script>
    <script src="../assets/js/plugins.js"></script>
    <script src="../assets/js/main.js"></script>
</body>
</html>