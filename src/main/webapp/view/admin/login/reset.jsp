<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>

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
                width:70%;
                margin-left:20px;
            }

            .adjust-btn{
                margin-top: 40px;
                width: 100%;
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
            <h1 class="center-text">Password Reset</h1>
            <form onSubmit="return false;">
                <div class=" top-margin">
                    <input type="text" name="customer_id" id="customer_id"
                        placeholder=" Customer ID" class="input-properties customer_id" required>
                    <div class="btn btn-success btn-lg" onclick="sendEmail()">Verify</div>
                </div>

                <div class=" top-margin" style="display:none" id="otpdiv">
                    <input type="text" name="otp" id="otp"
                        placeholder=" OTP" class="input-properties customer_id" required>
                    <div class="btn btn-success btn-lg" onclick="verifyOTP()">Send</div>
                </div>

                <div class=" top-margin" style="display:none" id="password_div">
                    <input type="password" name="password" id="password"
                        placeholder=" Password" class="input-properties customer_id" required disabled>
                </div>

                <div class=" top-margin" style="display:none" id="confirm_password_div">
                    <input type="password" name="confirm_password" id="confirm_password"
                        placeholder=" Confirm Password" class="input-properties customer_id" required disabled>
                </div>

                <div class="btn btn-success btn-lg adjust-btn" onclick="submit()">Submit</div>
            </form>
            <div>
                <p class="center-text top-margin"><a
                        href="/abc-bank/login">Already Have an account / Login</a></p>
            </div>
            <div>
                <p class="center-text top-margin">Don't have an account? <a
                        href="/abc-bank/register">Register</a></p>
            </div>
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

        function sendEmail() {
            if(!validateCustomerId()) {
                    return;
            }

            var customer_id = document.getElementById("customer_id").value;

            let url = "http://localhost:8080/abc-bank/password";

            let data = {
                "customer_id": customer_id
            }

            let method = "post";

            let response = sendRequest(url, data, method);
            response.then(function(response){
                let result = JSON.parse(response); // promise response
                console.log(result);
                if(result.status == 200){
                    alert("OTP sent to email");
                    document.getElementById("otp").disabled = false;
                    document.getElementById("customer_id").disabled = true;
                    document.getElementById("otpdiv").style.display = "block";
                }
                else{
                    alert("error - check your email");
                    document.getElementById("otp").disabled = true;
                    document.getElementById("customer_id").disabled = false;
                }
            });

        }

        function verifyOTP(){
            if(!validateOTP()) {
                    return;
            }

            let otp = document.getElementById("otp").value;
            let customer_id = document.getElementById("customer_id").value;
            let url = "http://localhost:8080/abc-bank/forgot-password/otp";
            let data = {
                "customer_id":customer_id,
                "otp": otp
            }
            console.log(data);
            let method = "post";
            let response = sendRequest(url, data, method);
            response.then(function(response){
                let result = JSON.parse(response); // promise response
                console.log(result);
                if(result.status == 200){
                    alert("OTP verified");
                    document.getElementById("otp").disabled = true;
                    document.getElementById("customer_id").disabled = true;
                    document.getElementById("password").disabled = false;
                    document.getElementById("confirm_password").disabled = false;
                    document.getElementById("password_div").style.display = "block";
                    document.getElementById("confirm_password_div").style.display = "block";

                }
                else{
                    alert("error - check your otp");
                    document.getElementById("password").disabled = true;
                }
            });
        }

        function submit(){
            if(!validatePassword()) {
                    return;
            }

            if(!validateCustomerId()) {
                    return;
            }

            if(!validateOTP()) {
                    return;
            }

            let customer_id = document.getElementById("customer_id").value;
            let confirm_password = document.getElementById("confirm_password").value;

            let password = document.getElementById("password").value;

            if(password != confirm_password){
                alert("password and confirm password should be same");
                return;
            }

            console.log(password);
            let url = "http://localhost:8080/abc-bank/password";
            let data = {
                "customer_id": customer_id,
                "password": password
            }
            let method = "put";
            let response = sendRequest(url, data, method);
            response.then(function(response){
                let result = JSON.parse(response); // promise response
                console.log(result);
                if(result.status == 200){
                    alert("Password changed");
                    window.location.href = "http://localhost:8080/abc-bank/login";
                }
                else{
                    alert("error - check your email");
                    document.getElementById("otp").disabled = true;
                    document.getElementById("customer_id").disabled = false;
                    document.getElementById("password").disabled = true;
                }
            });
        }





        function validatePassword() {
            var password = document.getElementById("password").value;
            if(password == ""){
                alert("Password cannot be empty");
                return false;
            }
            else if(/^[a-zA-Z0-9-!*$]*$/.test(password) == false) {
                alert("Password can only have alphanumeric characters and special characters like -!*$");
                return false;
            }
            else if(password.length < 8){
                alert("Password must be atleast 8 characters long");
                return false;
            }
            else if(password.length > 20){
                alert("Password cannot be more than 20 characters long");
                return false;
            }
            return true;
        }

        function validateOTP(){
            let otp = document.getElementById("otp").value;
            if(otp.length == 6){
                return true;
            }else if(!(/d{6}/.test(otp))){
                alert("OTP must be 6 digits - only numbers");
                return false;
            }else{
                alert("OTP should be 6 digits");
                return false;
            }
        }

        function validateCustomerId(){
            var customerId = document.getElementById("customer_id").value;
            if(customerId == ""){
                alert("Please enter customer id");
                return false;
            }
            else{
                return /^[1-9]{1}[0-9]{9}$/.test(customerId);
            }
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

    </script>
    </html>