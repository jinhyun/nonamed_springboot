<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>

<link href="/resources/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/bootstrap/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="/resources/bootstrap/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<body>
    <form id="loginForm" action="/doLogin" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Please Sign In</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="User Id" id="userId" name="userId" type="userId" autofocus value="busBon">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" id="userPassword" name="userPassword" type="userPassword" value="1234">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                        </label>
                                    </div>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <a href="#" id="doLoginBtn" class="btn btn-lg btn-success btn-block">Login</a>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <div id="message"></div>

    <script src="/resources/bootstrap/bower_components/jquery/dist/jquery.min.js"></script>
    <script type="application/javascript">
        $(document).ready(function() {
            $("#doLoginBtn").click(function () {
                var action = $("#loginForm").attr('action');
                var formData = {
                    userId: $("#userId").val(),
                    userPassword: $("#ㅡuserPassword").val()
                };

                $.ajax({
                    type: "POST",
                    url: action,
                    data: formData,
                    success: function (res) {
                        var result = JSON.parse(res);
                        if (result.statusCode == 'success') {
                            $("#loginForm").slideUp('slow');
                            $("#message").html(result.user.userName + "님 로그인");
                            $(location).attr('href',"/main");

                        } else {
                            $("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");
                        }
                    }
                });
            });
        });
    </script>
</body>
</html>
