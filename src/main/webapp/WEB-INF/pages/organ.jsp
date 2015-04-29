<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <!-- Bootstrap Core CSS -->
    <link href="/resources/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="/resources/bootstrap/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/bootstrap/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/bootstrap/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-color: #ffffff" onload="fnInit();">
    <%--<textarea id="organJson" name="organJson"
              style="display: block">${organJson}</textarea>--%>
    <div class="center-block" style="width: 400px;">
        <div class="navbar-default sidebar" role="navigation">
            <div id="organTree" name="organTree"
                 class="sidebar-nav navbar-collapse"
                 style="width:400px; background-color: #F8F8F8">

                <ul id="ul_root" class="nav"></ul>

                <%--<ul class="nav" id="side-menu">
                    <li>
                        <a href="#"><i class="fa fa-sitemap fa-fw"></i>Nonamed Company<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#"><i class="fa fa-user fa-fw"></i>CEO</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-group fa-fw"></i>개발본부<span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                        <a href="#"><i class="fa fa-user fa-fw"></i>개발본부장</a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-group fa-fw"></i>개발1팀<span class="fa arrow"></span></a>
                                        <ul class="nav nav-fourth-level">
                                            <li>
                                                <a href="#"><i class="fa fa-user fa-fw"></i>1팀장</a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-group fa-fw"></i>운영본부<span class="fa arrow"></span></a>
                            </li>
                        </ul>
                    </li>
                </ul>--%>
            </div>
        </div>
    </div>
    <!-- jQuery -->
    <script src="/resources/bootstrap/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/bootstrap/dist/js/sb-admin-2.js"></script>

    <script type="application/javascript">
        function fnInit() {
//            var organJsonValue = document.getElementById("organJson").value;
            var organJson = $.parseJSON(initData());
            var beforeOrganUpDeptsCnt = 1;

            $.each(organJson, function (key, value) {
                $.each(value, function (i, organInfo) {
                    var organBelongDeptId = organInfo.organBelongDeptId;
                    var organDeptNameUserName = organInfo.organDeptNameUserName;
                    var organUpDeptsCnt = organInfo.organUpDeptsCnt;

                    if ("root" == organBelongDeptId){
                        var li = document.createElement("li");
                        var a = document.createElement("a");
                        var txt = document.createTextNode(organInfo.organDeptNameUserName);

                        a.appendChild(txt);
                        li.appendChild(a);
                        a.id = "a_"+organInfo.organDeptIdUserNo;

                        $("#ul_"+organBelongDeptId).append(li.outerHTML);
                    } else if (beforeOrganUpDeptsCnt != organUpDeptsCnt){
                        // upLevel or downLevel
                        // organUpDeptsCnt값이 다르면 a_id:organBelongDeptId검색후 ul생성(ul_id:organBelongDeptId) li생성(a_id:organDeptIdUserNo)
                        var ul = document.createElement("ul");
                        var li = document.createElement("li");
                        var a = document.createElement("a");
                        var txt = document.createTextNode(organInfo.organDeptNameUserName);

                        a.appendChild(txt);
                        li.appendChild(a);
                        ul.appendChild(li);
                        ul.id = "ul_"+organBelongDeptId;

                        $("#a_"+organBelongDeptId).append(ul.outerHTML);
                    } else if (beforeOrganUpDeptsCnt == organUpDeptsCnt){
                        // sameLevel
                        // organUpDeptsCnt값이 동일하면 ul_id:organBelongDeptId검색후 li생성(a_id:organDeptIdUserNo)
                        var li = document.createElement("li");
                        var a = document.createElement("a");
                        var txt = document.createTextNode(organInfo.organDeptNameUserName);

                        a.appendChild(txt);
                        li.appendChild(a);
                        a.id = "a_"+organInfo.organDeptIdUserNo;

                        $("#ul_"+organBelongDeptId).append(li.outerHTML);
                    } else {
                        console.log("Exception");
                    }
                    beforeOrganUpDeptsCnt = organUpDeptsCnt;
                });
            });
        }
            /*
            organId
            organBelongDeptId
            organDeptIdUserNo
            organDeptNameUserName
            organUpDepts
            organUpDeptsCnt (depth) 차이가 생기면 uplevel or downlevel
                users.userId
                depts.deptId
             */
            /*
            <ul id="ul_organTree">
                <li id="li_organTree>
                    <a id="a_d1000">Nonamed Company</a>     a_organDeptIdUserNo
                    organUpDeptsCnt값이 다르면 a_id:organBelongDeptId검색후 ul생성(ul_id:organBelongDeptId) li생성(a_id:organDeptIdUserNo)
                    organUpDeptsCnt값이 동일하면 ul_id:organBelongDeptId검색후 li생성(a_id:organDeptIdUserNo)
                        <ul id="ul_d1000">
                            <li>
                                <a id="a_u0001">CEO</a>
                            </li>
                            <li>
                                <a id="a_d1001">경영본부</a>
                            </li>
                            <li>
                                <a id="a_d1002">개발본부</a>
                                    <ul id="ul_d1002">
                                        <li>
                                            <a>개발본부장</a>
                                        </li>
                                        <li>
                                            <a>개발1팀</a>
                                        </li>
                                        <li>
                                            <a>개발2팀</a>
                                        </li>
                                    </ul>
                            </li>
                            <li>
                                <a>운영본부</a>
                            </li>
                        </ul>
                </li>
            </ul>
            */
        function initData(){
            var data =
            [
                "{",
                "  \"organJsonList\": [",
                "    {",
                "      \"organId\": 1,",
                "      \"organBelongDeptId\": \"root\",",
                "      \"organDeptIdUserNo\": \"d1000\",",
                "      \"organDeptNameUserName\": \"Nonamed Company\",",
                "      \"organUpDepts\": \"root\",",
                "      \"organUpDeptsCnt\": 1,",
                "      \"organCode\": \"dept\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1000\",",
                "        \"deptName\": \"Nonamed Company\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 2,",
                "      \"organBelongDeptId\": \"d1000\",",
                "      \"organDeptIdUserNo\": \"u1\",",
                "      \"organDeptNameUserName\": \"노미정\",",
                "      \"organUpDepts\": \"d1000, root\",",
                "      \"organUpDeptsCnt\": 2,",
                "      \"organCode\": \"user\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1000\",",
                "        \"deptName\": \"Nonamed Company\"",
                "      },",
                "      \"users\": {",
                "        \"userId\": \"u1\",",
                "        \"userName\": \"노미정\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 3,",
                "      \"organBelongDeptId\": \"d1000\",",
                "      \"organDeptIdUserNo\": \"d1001\",",
                "      \"organDeptNameUserName\": \"경영지원본부\",",
                "      \"organUpDepts\": \"d1000, root\",",
                "      \"organUpDeptsCnt\": 2,",
                "      \"organCode\": \"dept\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1001\",",
                "        \"deptName\": \"경영지원본부\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 4,",
                "      \"organBelongDeptId\": \"d1000\",",
                "      \"organDeptIdUserNo\": \"d1002\",",
                "      \"organDeptNameUserName\": \"개발본부\",",
                "      \"organUpDepts\": \"d1000, root\",",
                "      \"organUpDeptsCnt\": 2,",
                "      \"organCode\": \"dept\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1002\",",
                "        \"deptName\": \"개발본부\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 5,",
                "      \"organBelongDeptId\": \"d1002\",",
                "      \"organDeptIdUserNo\": \"u2\",",
                "      \"organDeptNameUserName\": \"개발본부장\",",
                "      \"organUpDepts\": \"d1002, d1000, root\",",
                "      \"organUpDeptsCnt\": 3,",
                "      \"organCode\": \"user\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1002\",",
                "        \"deptName\": \"개발본부\"",
                "      },",
                "      \"users\": {",
                "        \"userId\": \"u2\",",
                "        \"userName\": \"개발본부장\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 6,",
                "      \"organBelongDeptId\": \"d1002\",",
                "      \"organDeptIdUserNo\": \"d1003\",",
                "      \"organDeptNameUserName\": \"개발1팀\",",
                "      \"organUpDepts\": \"d1002, d1000, root\",",
                "      \"organUpDeptsCnt\": 3,",
                "      \"organCode\": \"dept\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1003\",",
                "        \"deptName\": \"개발1팀\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 7,",
                "      \"organBelongDeptId\": \"d1003\",",
                "      \"organDeptIdUserNo\": \"u3\",",
                "      \"organDeptNameUserName\": \"1팀장\",",
                "      \"organUpDepts\": \"d1003, d1002, d1000, root\",",
                "      \"organUpDeptsCnt\": 4,",
                "      \"organCode\": \"user\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1003\",",
                "        \"deptName\": \"개발1팀\"",
                "      },",
                "      \"user\": {",
                "      	\"userId\": \"u3\",",
                "        \"userName\": \"1팀장\"",
                "      }",
                "    },",
                "    {",
                "      \"organId\": 8,",
                "      \"organBelongDeptId\": \"d1000\",",
                "      \"organDeptIdUserNo\": \"d1006\",",
                "      \"organDeptNameUserName\": \"운영본부\",",
                "      \"organUpDepts\": \"d1000, root\",",
                "      \"organUpDeptsCnt\": 2,",
                "      \"organCode\": \"dept\",",
                "      \"depts\": {",
                "        \"deptId\": \"d1006\",",
                "        \"deptName\": \"운영본부\"",
                "      }",
                "    }",
                "  ]",
                "}"
            ].join('');
            return data;
        }
    </script>
</body>
</html>
