<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
    try{
        String customer_id = session.getAttribute("customer_id").toString();
    }catch(Exception e){
        response.sendRedirect("/abc-bank/login");
    }

%>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Deposit Self</title>

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
                width:90%;
            }

            a:link{
                text-decoration: none;
            }

            .customer_id{
                display:inline;
                width:90%;
                margin-left:20px;
                margin-top:30px;
            }

            .adjust-btn{
                margin-top: 40px;
                width: 100%;
            }

            .reduce-width{
                width: 70%;
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
                    <a class="nav-item nav-link" href="http://localhost:8080/abc-bank/about">Application Status</a>
                </div>
            </div>
        </nav>

        <div class="center login-block-color padding">
            <h1 class="center-text">Self Deposit</h1>
            <form onSubmit="return false;">
                <div class=" top-margin">
                    <input type="text" name="amount" id="amount"
                        placeholder=" Amount" class="input-properties customer_id" required>
                </div>

                <div class="btn btn-success btn-lg adjust-btn" id="submit" onclick="submit()">Deposit</div>
            </form>
        </div>
    </body>


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





            function submit(){
                if(!validateAmount()){
                    return;
                }

                var amount = document.getElementById("amount").value.trim();

                var data = {
                    "amount": amount
                };


                let url = "http://localhost:8080/abc-bank/atm/deposit/self";

                let method = "post";

                    let response = sendRequest(url, data, method);
                    response.then(function(response){
                        let result = JSON.parse(response); // promise response
                        console.log(result);
                        if(result.status == 200){
                            alert("Successfully deposited " + amount + " to your account");
                            window.location.href = "http://localhost:8080/abc-bank/atm";
                        }
                        else{
                            alert(result.message);
                        }
                    });

            }



            function sendRequest(url, data, method){
                var myHeaders = new Headers();
                myHeaders.append("Accept", "application/json");
                var raw = JSON.stringify(data);

                var requestOptions = {
                    method: method,
                    headers: myHeaders,
                    body: raw,
                    redirect: 'follow'
                };

                return fetch(url, requestOptions)
                .then(response => {return response.text()})
                .catch(error => console.log('error', error));
            }

            function validateAmount(){
                var amount = document.getElementById("amount").value.trim();
                if(amount == ""){
                    alert("Amount cannot be empty");
                    return false;
                }
                if(isNaN(amount)){
                    alert("Amount should be a number");
                    return false;
                }
                if(amount < 0){
                    alert("Amount cannot be negative");
                    return false;
                }
                return true;
            }
        </script>

</html>