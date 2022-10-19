<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if(session.getAttribute("customer_name") == null){
        response.sendRedirect("/login");
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Application Status</title>
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
                margin-top:30px;
                width:60%;
            }

            .center-textbox{
                margin-left:115px;
            }

        </style>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

        <script>
            $(document).ready(function() {
                $(window).keydown(function(event){
                    if(event.keyCode == 13) {
                        event.preventDefault();
                        return false;
                    }
                });
            });

            function getStatus(){
                if(!validateEmail()){
                    return;
                }

                var email = document.getElementById("email").value;
                var url = "http://localhost:8080/abc-bank/application/status" + "?email=" + email;


                var response = sendRequest(url, "GET");
                response.then(function(response){
                    let result = JSON.parse(response);
                    alert(result.message);
                    document.getElementById("email").value = "";
                    if(result.message == "Approved - please login to access your account"){
                        window.location.href = "http://localhost:8080/abc-bank/login";
                    }
                });
            }

            function validateEmail(){
                var email = document.getElementById("email").value;
                if(email == ""){
                    alert("Please enter email");
                    return false;
                }
                else if(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(email)){
                    return true;
                }
                else{
                    alert("Please enter valid email");
                }
                return false;
            }

            function sendRequest(url, method){
                var myHeaders = new Headers();
                myHeaders.append("Accept", "application/json");

                var requestOptions = {
                    method: method,
                    headers: myHeaders,
                    redirect: 'follow'
                };

                return fetch(url, requestOptions)
                .then(response => {return response.text()})
                .catch(error => console.log('error', error));
            }

        </script>
    </head>
    <body class="body-color">

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="http://localhost:8080/abc-bank">ABC
                Bank</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav nav-fill">
                    <a class="nav-item nav-link"
                        href="http://localhost:8080/abc-bank/home">Home </a>
                    <a class="nav-item nav-link"
                        href="http://localhost:8080/abc-bank/transaction">Transaction</a>
                    <a class="nav-item nav-link"
                        href="http://localhost:8080/abc-bank/atm">ATM</a>
                    <a class="nav-item nav-link"
                        href="http://localhost:8080/abc-bank/login">Login</a>
                    <a class="nav-item nav-link"
                        href="http://localhost:8080/abc-bank/register">Register</a>
                    <a class="nav-item nav-link active"
                        href="http://localhost:8080/abc-bank/application-status">Application Status</a>
                </div>
            </div>
        </nav>

        <div class="center login-block-color padding">
            <h1 class="center-text">Application Status</h1>
            <form method="post" onsubmit="event.preventDefault();">
                <div class="center-text top-margin">
                    <input type="email" name="email" id="email"
                        placeholder="Email Id" class="input-properties">
                </div>
                <div class="center-text">
                    <input type="button" class="btn" id="send-button"
                        value="Submit" onclick="getStatus()">
                </div>
            </form>
        </div>
    </body>

</html>