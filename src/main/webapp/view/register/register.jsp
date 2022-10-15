<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <title>Create account</title>
    <style><%@include file="register.css"%></style>

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
                    <small class="form-text text-muted">Please fill your name
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
                        <input type="email" class="form-control reduce-width"
                            id="email"
                            name="email" placeholder="Enter email"
                            onfocusout="validate_email('email')"
                            required />
                        <small class="form-text text-muted">We'll never share
                            your
                            email with anyone else.</small>
                    </div>
                    <button class="verify-email position-verify"
                        onclick="show_emailOTP()">Verify</button>
                    <div class="reduce-area">
                        <input type="text" name="email_otp" class="form-control
                            email-otp"
                            id="email_otp" placeholder="Enter OTP">
                        <button class="verify-email position-verify
                            email-otp-btn" onclick="verify_email_otp()"
                            id="email-otp-btn">Confirm</button>
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
                        <small class="form-text text-muted">We'll never share
                            your
                            phone number with anyone else.</small>
                    </div>
                    <button class="verify-email position-verify"
                        onclick="show_phoneOTP()">Verify</button>
                    <div>
                        <input type="text" class="form-control phone-otp"
                            name="phone_otp" placeholder="Enter OTP"
                            id="phone_otp"/>
                        <button class="verify-email position-verify
                            phone-otp-btn"
                            id="phone-otp-btn" onclick="verify_mobile_otp()">Confirm</button>
                    </div>
                </div>

                <!--Aadhar number-->
                <div class="form-group">
                    <label for="aadhar">Aadhar number <small class="blue">*</small></label>
                    <input type="text" class="form-control" id="aadhar"
                        name="aadhar" placeholder="XXXX XXXX XXXX"
                        onfocusout="validate_aadhar('aadhar')" required />
                    <small class="form-text text-muted">We'll never share your
                        Aadhar number with anyone else</small>
                </div>

                <!--PAN number-->
                <div class="form-group">
                    <label for="pan">PAN number <small class="blue">*</small></label>
                    <input type="text" class="form-control" id="pan"
                        name="pan" placeholder="Your PAN number"
                        onfocusout="validate_pan('pan')" required />
                    <small class="form-text text-muted">We will never share your
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
                        <small class="form-text text-muted">Please choose date
                            of birth.</small>
                    </div>

                    <div class="mb-3">
                        <label for="pdf" class="form-label">Upload pdf</label>
                        <input class="form-control" type="file" id="pdf"
                            name="pdf"
                            accept="application/pdf" required>
                    </div>

                    <div class="form-group">
                        <label for="notes">Notes (optional)</label>
                        <textarea class="form-control" name="notes"
                            id="notes" rows="2"
                            onfocusout="validate_notes('notes')">
                        </textarea>
                    </div>
                </div>
            </div>
        </form>

        <!-- Captcha generator -->
        <div class="wrapper center">
            <h2 id="status" style="color: #ee7e6a;">Generate Captcha</h2>
            <div>
                <input type="text" readonly id="generated-captcha">
            </div>
            <div>
                <input type="text" id="entered-captcha"
                    placeholder="Enter the captcha.." style="margin-left:40px;">
            </div>
            <button type="button" class="center-captcha"
                style="margin-left:60px;" onclick="generate()" id="gen">
                Generate New
            </button>
        </div>

        <button class="btn btn-primary btn-block col-lg-2 ml-450"
            onclick="submit_form()">Submit
        </button>
    </div>



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

    <!--Import javascript-->
    <script><%@include file="register.js"%></script>


    <!-- Start Script for Form -->

    <!-- End Script for Form -->

</body>
</head>