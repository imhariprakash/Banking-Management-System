<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                margin-top:30px;
                width:60%;
            }

            .center-textbox{
                margin-left:115px;
            }

        </style>
        <script>
            var url = "http://localhost:8080/abc-bank/"
            function validate(){
                var value = document.getElementById("send-button").value;
                if(value == "Send password reset mail"){
                    alert("Otp has been sent to your registered mail and phone number");
                    document.getElementById("send-button").value = "Enter OTP";
                    document.getElementById("otp").style.display = "block";
                }
                if(value == "Enter OTP"){
                    sendOtp();
                }
            }

            function sendOtp(){
                var otp = document.getElementById("otp").value;
            }
        </script>
    </head>
    <body class="body-color">
        <div class="center login-block-color padding">
            <h1 class="center-text">Reset Password</h1>
            <form action="login" method="post">
                <div class="center-text top-margin">
                    <input type="text" name="customer-id" id="customer-id"
                        placeholder=" Customer Id" class="input-properties">
                </div>
                <div class="center-text top-margin">
                    <input type="text" name="otp" id="otp"
                        class="input-properties center-textbox"
                        placeholder=" OTP" style="display:none">
                </div>
                <div class="center-text">
                    <input type="button" class="btn" id="send-button"
                        value="Send password reset mail" onclick="validate()">
                </div>
            </form>
        </div>
    </body>
</html>