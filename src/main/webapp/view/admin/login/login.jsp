<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,
            initial-scale=1.0">
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
            margin-top:20px;
            width:60%;
        }

    </style>
    </head>
    <body class="body-color">
        <div class="center login-block-color padding">
            <h1 class="center-text">Admin Login</h1>
            <form method="post" onsubmit = "event.preventDefault();">
                <div class="center-text top-margin">
                    <input type="text" name="admin_id" id="admin_id"
                        placeholder=" Admin ID"
                        class="input-properties" required>
                </div>
                <div class="center-text top-margin">
                    <input type="password" name="password"
                        id="password"
                        class="input-properties"
                        placeholder=" Password" required>
                </div>

                <div class="center-text top-margin">
                    <input type="button" value="Login"
                        class="btn" onclick="login()">
                </div>
            </form>

            <div>
                <p class="center-text top-margin">Forgot <a
                        href="forgot-password">Username / Password</a></p>
            </div>
        </div>
    </body>

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


        function login(){
            let admin_id = document.getElementById("admin_id").value;
            let password = document.getElementById("password").value;
            let data = {
                "username": admin_id,
                "password": password
            }

            console.log(data);

            let url = "http://localhost:8080/abc-bank/admin/login";

            let response = sendRequest(url, data);
            response.then (function (response){
                let result = JSON.parse(response);
                console.log(result);
                if(result.status == "200"){
                    alert("Login Successful");
                    window.location.replace("http://localhost:8080/abc-bank/admin/dashboard");
                }else{
                    alert(result.message);
                }
            });

        }


        function sendRequest(url, data){
            var myHeaders = new Headers();
            myHeaders.append("Accept", "application/json");
            var raw = JSON.stringify(data);

            var requestOptions = {
                method: 'POST',
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