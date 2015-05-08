<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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
</head>
<body>
    <form id="loginForm" action="/doLogin" method="post">
        <input type="text" id="userId" name="userId" value="busBon">
        <input type="text" id="userPassword" name="userPassword" value="1234">
        <input type="button" id="doLoginBtn" name="doLoginBtn" value="로그인"/>
        <input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session['userSession']" />
    </form>

    <div id="message"></div>
</body>
</html>
