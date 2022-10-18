<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.gson.JsonObject, model.admin.review.GetReviewForm" %>

<%
    String username = (String) session.getAttribute("username");
    JsonObject json = GetReviewForm.getReviewForm(username);

    String aadhar = "'" + json.get("aadhar").getAsString() + "'";
    String address = "'" + json.get("address").getAsString() + "'";
    String cust_name = "'" + json.get("cust_name").getAsString() + "'";
    String dob = "'" + json.get("dob").getAsString() + "'";
    String email = "'" + json.get("email").getAsString() + "'";
    String father_name = "'" + json.get("father_name").getAsString() + "'";
    String notes = "'" + json.get("notes").getAsString() + "'";
    String pan = "'" + json.get("pan").getAsString() + "'";
    String phone = "'" + json.get("phone").getAsString() + "'";
    String pincode = "'" + json.get("pincode").getAsString() + "'";
%>


<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1,
            shrink-to-fit=no" />

        <!-- Bootstrap CSS -->
        <link rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous" />

        <title>Review account</title>
        <style><%@include file="review.css"%></style>

    </head>

    <body class="container body-color">
        <!-- Start Header form -->
        <div class="text-center pt-5">
            <h2>ABC bank - Online Account Opening</h2>
            <br><br>
        </div>
        <!-- End Header form -->

        <div class="card">
            <div class="card-body">

                <!-- register form-->
                <form action="register" method="post" id="register"
                    onSubmit="return false;">

                    <!-- Full name-->
                    <div class="form-group">
                        <label for="name">Full Name <small class="blue">*</small></label>
                        <input type="text" class="form-control" id="name"
                            name="name" placeholder="Your name"
                            onfocusout="validate_name('name')" required />
                        <small class="form-text text-muted">Please fill your
                            name
                            with initial at last with space</small>
                    </div>

                    <!-- Father's name-->
                    <div class="form-group">
                        <label for="f_name">Father's Name <small class="blue">*</small></label>
                        <input type="text" class="form-control" id="f_name"
                            name="f_name" placeholder="Your father name"
                            onfocusout="validate_name('f_name')" required />
                        <small class="form-text text-muted">Please fill your
                            father's name with initial at last with space</small>
                    </div>

                    <!--Email-->
                    <div class="form-group reduce-width d-flex flex-row">
                        <div style="width:500px;">
                            <label for="email">Email <small class="blue">*</small></label>
                            <input type="email" class="form-control
                                reduce-width"
                                id="email"
                                name="email" placeholder="Enter email"
                                onfocusout="validate_email('email')"
                                required />
                            <small class="form-text text-muted">We'll never
                                share
                                your
                                email with anyone else.</small>
                        </div>
                    </div>

                    <!--Phone number-->
                    <div class="form-group reduce-width d-flex flex-row">
                        <div style="width:500px;">
                            <label for="phone">Phone <small class="blue">*</small></label>
                            <input type="tel" class="form-control reduce-width"
                                id="phone" name="phone"
                                name="phone" placeholder="+91xxxxxxxxxx"
                                onfocusout="validate_phone('phone')" required />
                            <small class="form-text text-muted">We'll never
                                share
                                your
                                phone number with anyone else.</small>
                        </div>
                    </div>

                    <!--Aadhar number-->
                    <div class="form-group">
                        <label for="aadhar">Aadhar number <small class="blue">*</small></label>
                        <input type="text" class="form-control" id="aadhar"
                            name="aadhar" placeholder="XXXX XXXX XXXX"
                            onfocusout="validate_aadhar('aadhar')" required />
                        <small class="form-text text-muted">We'll never share
                            your
                            Aadhar number with anyone else</small>
                    </div>

                    <!--PAN number-->
                    <div class="form-group">
                        <label for="pan">PAN number <small class="blue">*</small></label>
                        <input type="text" class="form-control" id="pan"
                            name="pan" placeholder="Your PAN number"
                            onfocusout="validate_pan('pan')" required />
                        <small class="form-text text-muted">We will never share
                            your
                            PAN number with anyone else</small>
                    </div>

                    <!--Residential address-->
                    <div class="form-group">
                        <label for="address">Residential address <small
                                class="blue">*</small></label>
                        <input type="text" class="form-control" id="address"
                            name="address" placeholder="Enter address"
                            onfocusout="validate_address('address')" required />
                        <small class="form-text text-muted">Please fill your
                            residential
                            address</small>
                    </div>

                    <!--Pin code-->
                    <div class="form-group">
                        <label for="pincode">Pin code <small
                                class="blue">*</small></label>
                        <input type="text" class="form-control" id="pincode"
                            name="pincode" placeholder="Enter pincode"
                            onfocusout="validate_pincode('pincode')" required />
                        <small class="form-text text-muted">Please fill your pin
                            code</small>
                    </div>


                    <!--Date of birth, File upload, Textbox for notes-->
                    <div class="form-row change-to-block">

                        <div class="form-group col-md-4">
                            <label for="dob">Date of birth <small
                                    class="blue">*</small></label>
                            <input type="date" class="form-control" id="dob"
                                name="dob" style="display:block;"
                                onfocusout="validate_age('dob')" required />
                            <small class="form-text text-muted">Please choose
                                date
                                of birth.</small>
                        </div>
                </div>
                <div>
                    <label for="notes">Notes<small>  (optional)</small></label>
                    <textarea id="notes" class="form-control" name="notes"
                        rows="3" placeholder="Share with us any other information"></textarea>
                </div>
                <div>
                    <label for="review">Review message</label>
                    <textarea id="review" class="form-control" name="review"
                        rows="3" placeholder="Review message"></textarea>
                </div>

                <br><br>

                <div class="d-flex flex-column justify-content-between">
                    <button class="btn btn-large btn-success btn-primary btn-lg" name="approve-btn" id="approve-btn" onclick="approve()">Approve</button>
                    <br>
                    <button class="btn btn-large btn-danger btn-primary btn-lg" name="reject-btn" id="reject-btn" onclick="reject()">Reject</button>
                </div>
            </div>

        </form>



        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script
            src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
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

        <!--Import javascript-->
        <script><%@include file="review.js"%></script>


        <!-- Start Script for Form -->

        <!-- End Script for Form -->

    </body>

    <script>
        document.getElementById("name").value =  <%=cust_name%>;
        document.getElementById("f_name").value = <%=father_name%>;
        document.getElementById("email").value = <%=email%>;
        document.getElementById("phone").value = <%=phone%>;
        document.getElementById("aadhar").value = <%=aadhar%>;
        document.getElementById("pan").value = <%=pan%>;
        document.getElementById("address").value = <%=address%>;
        document.getElementById("pincode").value = <%=pincode%>;
        document.getElementById("dob").value = <%=dob%>;
        document.getElementById("notes").value = <%=notes%>;
    </script>

</html>