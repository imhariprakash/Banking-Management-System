<%@ page contentType="text/html; charset=UTF-8" %>

<%
    response.setHeader("Refresh","1;url=./login");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <script>
            function wrongCredentials() {
                alert("Username or Password wrong!");
            }
        </script>
        <style>
            .center{
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                padding: 60px;
                width:30%;

            }
            .center-text{
                text-align: center;
            }
            .body-color{
                background: #e9faff;
            }
        </style>

    </style>
    </head>
    <body class="body-color" onload="wrongCredentials()">
        <div class="center login-block-color padding">
            <h1 class="center-text">Redirecting to Login page...</h1>
        </div>
    </body>
</html>