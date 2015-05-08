<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Nonamed</title>

    <link href="/resources/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <link href="/resources/bootstrap/dist/css/timeline.css" rel="stylesheet">
    <link href="/resources/bootstrap/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="/resources/bootstrap/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<body onload="mainInit()">
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div id="header-layout" class="navbar-header"></div>
            <div id="sidebar-layout" class="navbar-default sidebar" role="navigation"></div>
        </nav>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header" id="contents-title"></h1>
                </div>
                <div id="contents-layout"></div>
            </div>
        </div>
    </div>

    <script src="/resources/bootstrap/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/resources/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="/resources/bootstrap/dist/js/sb-admin-2.js"></script>
    <script src="/resources/js/handlebars-v3.0.3.js"></script>

    <jsp:include page="/WEB-INF/pages/layout/sidebar.jsp" flush="true"></jsp:include>
    <jsp:include page="/WEB-INF/pages/layout/header.jsp" flush="true"></jsp:include>

    <script type="application/javascript">
        var mainInit = function(){
            initLayout();
        };

        var initLayout = function(){
            var sidebarTemplate = Handlebars.compile($("#sidebar-template").html());
            $("#sidebar-layout").html(sidebarTemplate);

            var headerTemplate = Handlebars.compile($("#header-template").html());
            $("#header-layout").html(headerTemplate);
        };

        var menuClick = function(menuName){
            if ('organ' == menuName){
                $("#contents-layout").load("/organ", function(data){
                    $("#contents-layout").html(data);
                    $("#contents-title").html("조직도");
                    organInit();
                });
            }
        };
    </script>
</body>
</html>