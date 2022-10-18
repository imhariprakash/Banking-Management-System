<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.google.gson.JsonObject, com.google.gson.JsonParser, model.authentication.Login, javax.servlet.http.HttpSession" %>

<%
    try{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if((username != null && password != null) && Login.isVerifiedUser(username, password)){
            session.setAttribute("customer_name", username);
            session.setAttribute("logged_time", System.currentTimeMillis());
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
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">
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

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">ABC Bank</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav nav-fill">
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/home">Home </a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/transaction">Transaction</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/atm">ATM</a>
                    <a class="nav-item nav-link active" href="http://localhost:8080/abc-bank/login">Login</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/register">Register</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/about">Application Status</a>
                </div>
            </div>
        </nav>

        <div class="center login-block-color padding">
            <h1 class="center-text">Account Login</h1>
            <form action="login" method="post">
                <div class="center-text top-margin">
                    <input type="text" name="username" id="username"
                        placeholder=" User ID" class="input-properties" required>
                </div>
                <div class="center-text top-margin">
                    <input type="password" name="password" id="password"
                        class="input-properties"
                        placeholder=" Password" required>
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