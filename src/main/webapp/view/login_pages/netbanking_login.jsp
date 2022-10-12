<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.google.gson.JsonObject, com.google.gson.JsonParser, model.authentication.Login" %>

<%
    try{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if((username != null && password != null) && Login.isVerifiedUser(username, password)){
            response.sendRedirect("index.jsp");
        }
        else if((username != null || password != null) || (username == "" || password == "")){
            response.sendRedirect("/abc-bank/wrong-login-credentials");
        }
    }catch(Exception e){
       response.sendRedirect("/abc-bank/wrong-login-credentials");
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
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
        .login-block-color{
            background:white;
        }
        .top-margin{
            margin-top: 20px;
        }
        .input-properties{
            margin-top:10px;
            border:1px solid #666;
            height: 50px;
            width:60%;
        }

        a:link{
            text-decoration: none;
        }

        .btn{
            background-color: #4272d7;
            color: white;
            font-family: OpenSans-Regular;
            font-size: 14px;
            text-transform:uppercase;
            align-items:center;
            height:50px;
            padding: 0 15px;
            margin-top:40px;
            width:60%;
        }

    </style>
    </head>
    <body class="body-color">
        <div class="center login-block-color padding">
            <h1 class="center-text">Account Login</h1>
            <form action="login" method="post">
                <div class="center-text top-margin">
                    <input type="text" name="username" id="username"
                        placeholder=" User ID" class="input-properties">
                </div>
                <div class="center-text top-margin">
                    <input type="password" name="password" id="password"
                        class="input-properties"
                        placeholder=" Password">
                </div>
                <div class="center-text">
                    <input type="submit" class="btn">
                </div>
            </form>
            <div>
                <p class="center-text top-margin">Forgot <a
                        href="forgot-password">Username / Password</a></p>
            </div>
            <div>
                <p class="center-text top-margin">Don't have an account? <a
                        href="register">Register</a></p>
            </div>
        </div>
    </body>
</html>