<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password</title>

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



    </head>
    <body class="body-color">
        <div class="center login-block-color padding">
            <h1 class="center-text">Reset Password</h1>
            <form method="post" onsubmit="event.preventDefault();">
                <div class="center-text top-margin">
                    <input type="text" name="customer_id" id="customer_id"
                        placeholder=" Customer Id" class="input-properties">
                </div>
                <div class="center-text top-margin">
                    <input type="text" name="otp" id="otp"
                        class="input-properties center-textbox"
                        placeholder=" OTP" style="display:none">
                </div>
                <div class="center-text top-margin">
                    <input type="password" name="password" id="password"
                        class="input-properties center-textbox"
                        placeholder=" New Password" style="display:none">
                </div>
                <div class="center-text">
                    <input type="button" class="btn" id="send-button"
                        value="Send password reset mail" onclick="reset()">
                </div>
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

        function reset(){
            alert("reset");
            if(document.getElementById("otp").disabled == true){
                if(validateCustomerId()){
                    var customerId = document.getElementById("customerId").value;
                    let url = "http://localhost:8080/abc-bank/password";
                    let data = {
                        "customer_id": customerId
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
                        }
                        else{
                            alert("error - check your email");
                            document.getElementById("otp").disabled = true;
                            document.getElementById("customer_id").disabled = false;
                        }
                    });
                }
            }else if(validateOTP() && document.getElementById("password").disabled == true){
                let otp = document.getElementById("otp").value;
                let url = "http://localhost:8080/abc-bank/forgot-password/otp";
                let data = {
                    "customer_id": customerId,
                    "otp": otp
                }
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
                    }
                    else{
                        alert("error - check your email");
                        document.getElementById("otp").disabled = true;
                        document.getElementById("customer_id").disabled = false;
                        document.getElementById("password").disabled = true;
                    }
                });
            }else if(validatePassword()){
                let password = document.getElementById("password").value;
                let url = "http://localhost:8080/abc-bank/password";
                let data = {
                    "customer_id": customerId,
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