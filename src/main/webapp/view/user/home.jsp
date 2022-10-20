<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="dao.utility.GetCustomerName, javax.servlet.http.HttpSession" %>

<%
    String customer_name = "";
    try{
        String customer_id = session.getAttribute("customer_id").toString();
        customer_name = GetCustomerName.getCustomerName(customer_id);
    }catch(Exception e){
        response.sendRedirect("/abc-bank/login");
    }

%>

<!doctype html>
<html lang="en">

    <head>
        <title>Home</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,
            shrink-to-fit=no">

        <!-- Bootstrap CSS v5.2.1 -->
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

            .btn{
                margin : 10px;
            }

            #inner {
                width: 50%;
                position:relative;
                top:100px;
                left:270px;
                margin: 0 auto;
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
                    <a class="nav-item nav-link active" href="http://localhost:8080/abc-bank/home">Home </a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/transaction">Transaction</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/atm">ATM</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/login">Login</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/register">Register</a>
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/application-status">Application Status</a>
                </div>
            </div>
        </nav>

        <h1 id="inner">Welcome <%=customer_name%>!</h1>
        <div class="center login-block-color padding">

            <div class="d-flex flex-column">
                <div class="btn btn-success btn-lg center-text" onclick="show_balance()">Show Balance</div>
                <div class="btn btn-success btn-lg center-text" onclick="show_transactions()">Show Transactions</div>
                <div class="btn btn-success btn-lg center-text" onclick="atm()">ATM</div>
                <div class="btn btn-success btn-lg center-text" onclick=change_password()>Change Password</div>
                <div class="btn btn-success btn-lg center-text" onclick="transaction()">Online transaction</div>
                <div class="btn btn-success btn-lg center-text" onclick="block()">Block card</div>
                <div class="btn btn-success btn-lg center-text" onclick="unblock()">Unblock card</div>
                <div class="btn btn-success btn-lg center-text" onclick="logout()">Log out</div>
            </div>

        </div>

        <!-- Bootstrap JavaScript Libraries -->
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous">
        </script>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous">
        </script>
    </body>

    <script>
        function show_balance(){
            window.location.href = "http://localhost:8080/abc-bank/user/balance";
        }

        function show_transactions(){
            window.location.href = "http://localhost:8080/abc-bank/user/transactions";
        }

        function atm(){
            window.location.href = "http://localhost:8080/abc-bank/atm";
        }

        function change_password(){
            window.location.href = "http://localhost:8080/abc-bank/reset/password";
        }

        function transaction(){
            window.location.href = "http://localhost:8080/abc-bank/transaction";
        }

        function logout(){
            window.location.href = "http://localhost:8080/abc-bank/logout";
        }

        function block(){
            alert("Card blocked successfully! - You can activate it from the home page");
            window.location.href = "http://localhost:8080/abc-bank/atm/block";
        }

        function unblock(){
            alert("Card unblocked successfully! - You can use it now");
            window.location.href = "http://localhost:8080/abc-bank/atm/unblock";
        }
    </script>

</html>