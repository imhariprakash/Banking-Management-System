<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    try{
        String username = session.getAttribute("username").toString();
    }catch(Exception e){
        response.sendRedirect("http://localhost:8080/abc-bank/");
    }
%>


<!doctype html>
<html lang="en">

    <head>
        <title>Admin Dashboard</title>
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
                left:340px;
                margin: 0 auto;
            }

        </style>
    </head>

    <body class="body-color">
        <h1 id="inner">Welcome User!</h1>
        <div class="center login-block-color padding">

            <div class="d-flex flex-column">
                <div class="btn btn-success btn-lg center-text" onclick="review_applications()">Review Applications</div>
                <div class="btn btn-success btn-lg center-text">View Applications</div>
                <div class="btn btn-success btn-lg center-text">Review particular application</div>
                <div class="btn btn-success btn-lg center-text">Change Password</div>
                <div class="btn btn-success btn-lg center-text">View Customer Details</div>
                <div class="btn btn-success btn-lg center-text">Log out</div>
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
        function review_applications(){
            window.location.href = "http://localhost:8080/abc-bank/admin/review";
        }
    </script>

</html>