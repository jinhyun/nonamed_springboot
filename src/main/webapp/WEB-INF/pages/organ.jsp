<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <!-- Bootstrap Core CSS -->
    <link href="/resources/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

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
            <div id="organTree" name="organTree" class="sidebar-nav navbar-collapse"
                 style="width:400px; background-color: #F8F8F8">
                <ul class="nav" id="ul_root"></ul>
            </div>
        </div>
    </div>
    <!-- jQuery -->
    <script src="/resources/bootstrap/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/bootstrap/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <script type="application/javascript">
        var isRootLevel = function(organInfo){
            return (organInfo.organBelongDeptId == "root") ? true:false;
        };

        var isSameLevel = function(organInfo, beforeOrganUpDeptsCnt){
            return (beforeOrganUpDeptsCnt == organInfo.organUpDeptsCnt) ? true:false;
        };

        var isUpLevelOrDownLevel = function(organInfo, beforeOrganUpDeptsCnt){
            return (beforeOrganUpDeptsCnt != organInfo.organUpDeptsCnt) ? true:false;
        };

        var createRootLevel = function(organInfo){
            var li = document.createElement("li");
            var a = document.createElement("a");
            var i = document.createElement("i");
            i.className = "fa fa-sitemap fa-fw";
            var txt = document.createTextNode(organInfo.organDeptNameUserName);
            var span = document.createElement("span");
            span.className = "fa arrow";

            a.href = "#";
            a.appendChild(i);
            a.appendChild(txt);
            li.appendChild(a);
            $(span).insertAfter(txt);
            li.id = "li_"+organInfo.organDeptIdUserNo;
            li.className="active";

            $("#ul_"+organInfo.organBelongDeptId).append(li.outerHTML);
        };

        var createSameLevel = function(organInfo){
            // organUpDeptsCnt값이 동일하면 ul_id:organBelongDeptId검색후 li생성(a_id:organDeptIdUserNo)
            var li = document.createElement("li");
            var a = document.createElement("a");
            var i = document.createElement("i");
            if (organInfo.organCode == "user"){
                i.className = "fa fa-user fa-fw";
            } else if (organInfo.organCode == "dept"){
                i.className = "fa fa-users fa-fw";
            }
            var txt = document.createTextNode(organInfo.organDeptNameUserName);
            var span = document.createElement("span");
            span.className = "fa arrow";

            a.href = "#";
            a.appendChild(i);
            a.appendChild(txt);
            li.appendChild(a);
            li.id = "li_"+organInfo.organDeptIdUserNo;
            if (organInfo.organCode == "dept"){
                $(span).insertAfter(txt);
            }

            $("#ul_"+organInfo.organBelongDeptId).append(li.outerHTML);
        };

        var createUpLevelOrDownLevel = function(organInfo){
            // organUpDeptsCnt값이 다르면 li_id:organBelongDeptId검색후 ul생성(ul_id:organBelongDeptId) li생성(a_id:organDeptIdUserNo)
            var ul = document.createElement("ul");
            ul.className = "nav nav-" + organInfo.organUpDeptsCnt + "-level";
            var li = document.createElement("li");
            var a = document.createElement("a");
            var i = document.createElement("i");
            if (organInfo.organCode == "user"){
                i.className = "fa fa-user fa-fw";
            } else if (organInfo.organCode == "dept"){
                i.className = "fa fa-users fa-fw";
            }
            var txt = document.createTextNode(organInfo.organDeptNameUserName);
            var span = document.createElement("span");
            span.className = "fa arrow";

            a.href = "#";
            a.appendChild(i);
            a.appendChild(txt);
            li.appendChild(a);
            ul.appendChild(li);
            ul.id = "ul_"+organInfo.organBelongDeptId;
            if (organInfo.organCode == "dept"){
                $(span).insertAfter(txt);
            }
            li.id = "li_"+organInfo.organDeptIdUserNo;

            $("#li_"+organInfo.organBelongDeptId).append(ul.outerHTML);
        };

        function fnInit() {
//            var organJsonValue = document.getElementById("organJson").value;
            var organJson = $.parseJSON(initData());
            var beforeOrganUpDeptsCnt = 1;

            $.each(organJson, function (key, value) {
                $.each(value, function (i, organInfo) {
                    if (isRootLevel(organInfo)){
                        createRootLevel(organInfo);

                    } else if (isUpLevelOrDownLevel(organInfo, beforeOrganUpDeptsCnt)){
                        createUpLevelOrDownLevel(organInfo);

                    } else if (isSameLevel(organInfo, beforeOrganUpDeptsCnt)){
                        createSameLevel(organInfo);

                    } else {
                        console.log("Exception");
                    }
                    beforeOrganUpDeptsCnt = organInfo.organUpDeptsCnt;
                });
            });

            $('#ul_root').metisMenu();
        }
        
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
                    "      \"organDeptNameUserName\": \"CEO\",",
                    "      \"organUpDepts\": \"d1000, root\",",
                    "      \"organUpDeptsCnt\": 2,",
                    "      \"organCode\": \"user\",",
                    "      \"depts\": {",
                    "        \"deptId\": \"d1000\",",
                    "        \"deptName\": \"Nonamed Company\"",
                    "      },",
                    "      \"users\": {",
                    "        \"userId\": \"u1\",",
                    "        \"userName\": \"CEO\"",
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
                    "      \"organBelongDeptId\": \"d1001\",",
                    "      \"organDeptIdUserNo\": \"u2\",",
                    "      \"organDeptNameUserName\": \"경영지원본부장\",",
                    "      \"organUpDepts\": \"d1001, d1000, root\",",
                    "      \"organUpDeptsCnt\": 3,",
                    "      \"organCode\": \"user\",",
                    "      \"depts\": {",
                    "        \"deptId\": \"d1001\",",
                    "        \"deptName\": \"경영본부\"",
                    "      },",
                    "      \"users\": {",
                    "        \"userId\": \"u2\",",
                    "        \"userName\": \"경영지원본부장\"",
                    "      }",
                    "    },",
                    "    {",
                    "      \"organId\": 5,",
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
                    "      \"organId\": 6,",
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
                    "        \"userId\": \"u3\",",
                    "        \"userName\": \"개발본부장\"",
                    "      }",
                    "    },",
                    "    {",
                    "      \"organId\": 7,",
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
                    "      \"organId\": 8,",
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
                    "      	\"userId\": \"u4\",",
                    "        \"userName\": \"1팀장\"",
                    "      }",
                    "    },",
                    "    {",
                    "      \"organId\": 9,",
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
                    "    },",
                    "    {",
                    "      \"organId\": 10,",
                    "      \"organBelongDeptId\": \"d1006\",",
                    "      \"organDeptIdUserNo\": \"u5\",",
                    "      \"organDeptNameUserName\": \"운영본부장\",",
                    "      \"organUpDepts\": \"d1006, d1000, root\",",
                    "      \"organUpDeptsCnt\": 3,",
                    "      \"organCode\": \"user\",",
                    "      \"depts\": {",
                    "        \"deptId\": \"d1006\",",
                    "        \"deptName\": \"운영본부\"",
                    "      },",
                    "      \"user\": {",
                    "      	\"userId\": \"u5\",",
                    "        \"userName\": \"운영본부장\"",
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
