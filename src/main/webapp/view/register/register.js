// functions to check file size and format - pdf less than 2mb

// var myFile="";

// $('#file').on('change',function(){

//     myFile = $("#file").val();
//     var upload = myFile.split('.').pop();
//     if(upload=='pdf'){
//         if(document.getElementById("file").files[0].size > 2097152){
//             alert("File is too big!");
//             $("#file").val('');
//         }
//         else{
//             alert("File uploaded successfully");
//         }
//     }else{
//         document.getElementById("file").value = null;
//         alert("Only PDF files are allowed")
//     }

// });


// submit verification - validate fields and captcha 

var mobile_verify = false;
var email_verify = false;


function submit_form(){
    
    if(!check()){  // verify captcha
        generate();
        return;
    }

    if(empty()){
        generate();
        return;
    }

    if(!validate_name("name")){
        generate();
        return;
    }

    if(!validate_name("f_name")){
        generate();
        return;
    }

    if(!validate_email("email")){
        generate();
        return;
    }

    if(email_verify == false){
        alert("Please verify your email");
        generate();
        return;
    }

    if(!validate_phone("phone")){
        generate();
        return;
    }

    if(mobile_verify == false){
        alert("Please verify your mobile number");
        generate();
        return;
    }

    if(!validate_aadhar("aadhar")){
        generate();
        return;
    }

    if(!validate_pan("pan")){
        generate();
        return;
    }

    if(!validate_address("address")){
        generate();
        return;
    }

    if(!validate_pincode("pincode")){
        generate();
        return;
    }

    if(!validate_age("dob")){
        generate();
        return;
    }

    if(!validate_notes("notes")){
        generate();
        return;
    }

    submit();
    
    alert("Submitted!");               
}

function empty(){
    var name = document.getElementById("name").value.trim();
    var f_name = document.getElementById("f_name").value.trim();
    var email = document.getElementById("email").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var aadhar = document.getElementById("aadhar").value.trim();
    var pan = document.getElementById("pan").value.trim();
    var address = document.getElementById("address").value.trim();
    var pincode = document.getElementById("pincode").value.trim();
    var dob = document.getElementById("dob").value.trim();
    var notes = document.getElementById("notes").value.trim();

    if(name == "" || f_name == "" || email == "" || phone == "" || aadhar == "" || pan == "" || address == "" || pincode == "" || dob == ""){
        alert("Please fill all the fields!");
        return true;
    }
    return false;
}


function submit(){
    let url = "http://localhost:8080/abc-bank/create/account";
    let data = {
        name: document.getElementById("name").value.trim(),
        f_name: document.getElementById("f_name").value.trim(),
        email: document.getElementById("email").value.trim(),
        phone: document.getElementById("phone").value.trim(),
        aadhar: document.getElementById("aadhar").value.trim(),
        pan: document.getElementById("pan").value.trim(),
        address: document.getElementById("address").value.trim(),
        pincode: document.getElementById("pincode").value.trim(),
        dob: document.getElementById("dob").value.trim().replace("/", "-"),
        notes: document.getElementById("notes").value.trim()
    };

    console.log(data);

    let response = sendOTPRequest(url, data);
    response.then(function(response){
        let result = JSON.parse(response);
        console.log(result);
        if(result.status == 200){
            alert("Successfully registered! - Verification pending");
            window.location.href = "http://localhost:8080/abc-bank/login";
        }
        else{
            alert("error - check your mobile number");
        }
    });
    
    
}

// disable submit by pressing enter key

$(document).on("keydown", ":input:not(textarea)", function(event) {
    if (event.key == "Enter") {
        event.preventDefault();
    }
});



// captcha generation
let captcha;
let alphaNumerical = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";

generate = () => {
    // console.log(status)
    let first = alphaNumerical[Math.floor(Math.random() * alphaNumerical.length)];
    let second = Math.floor(Math.random() * 10);
    let third = Math.floor(Math.random() * 10);
    let fourth = alphaNumerical[Math.floor(Math.random() * alphaNumerical.length)];
    let fifth = alphaNumerical[Math.floor(Math.random() * alphaNumerical.length)];
    let sixth = Math.floor(Math.random() * 10);
    captcha = first.toString()+second.toString()+third.toString()+fourth.toString()+fifth.toString()+sixth.toString();
    document.getElementById('generated-captcha').value = captcha;
    document.getElementById("entered-captcha").value = '';
}


// validate captcha
check = () => {
    // console.log(status)
    let userValue = document.getElementById("entered-captcha").value.trim();
    if(userValue == ''){
        alert("Please enter captcha");
        return false;
    }
    else if(userValue == captcha){
        return true;
    }else{
        alert("Wrong captcha");
        generate();
        return false;
    }
}


// validations

function show_phoneOTP(){
    var phone = document.getElementById("phone_otp");
    phone.style.display = "inline";
    phone = document.getElementById("phone-otp-btn");
    phone.style.display = "inline";
    if(document.getElementById("phone").disabled == false){
        sendMobileOTP();
    }
}

function show_emailOTP(){
    var email = document.getElementById("email_otp");
    email.style.display = "inline";
    email = document.getElementById("email-otp-btn");
    email.style.display = "inline";
    if(document.getElementById("email").disabled == false){
        sendEmailOTP();
    }
}

// validations


function validate_name(elementId){
    var name = document.getElementById(elementId).value.trim();
    if(name == ""){
        //setTimeout(alert("Name cannot be empty!"),2000);
        return false;
    }
    if (/^[A-Za-z ]*$/.test(name)){
        return true;
    }
    alert("Name must contain only alphabets and spaces!");
    return false;
}


function validate_email(elementId) 
{
    var email = document.getElementById(elementId).value.trim();
    if(email == ""){
        //setTimeout(alert("Email cannot be empty!"),2000);
        return false;
    }
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
    {
        return (true);
    }
    alert("You have entered an invalid email address!")
    return (false);
}

function validate_phone(elementId){
    var phone = document.getElementById(elementId).value.trim();
    if(phone == ""){
        //setTimeout(alert("Phone cannot be empty!"),2000);
        return false;
    }
    if (/^\d{10}$/.test(phone))
    {
        return (true);
    }
    alert("You have entered an invalid phone number! eg : 9999999999");
    return (false);
}


function validate_aadhar(elementId){   
            
    var regexp=/^[2-9]{1}[0-9]{3}\s{1}[0-9]{4}\s{1}[0-9]{4}$/;
           
    var aadhar = document.getElementById(elementId).value.trim();
    if(aadhar === ""){
        //setTimeout(alert("Aadhar cannot be empty!"),2000);
        return false;
    }
    if(regexp.test(aadhar)){
        return true;
    }
    else{ 
        alert("Invalid Aadhar no : type with spaces.");
        return false;
    }
}


function validate_pan(elementId){
    var pan = document.getElementById(elementId).value.trim();
    if(pan == ""){
        //setTimeout(alert("PAN cannot be empty!"),2000);
        return false;
    }
    if (/^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$/.test(pan))
    {
        return (true);
    }
    alert("You have entered an invalid PAN number!")
    return (false);
}


function validate_address(elementId){
    var address = document.getElementById(elementId).value.trim();
    if(address == ""){
        //setTimeout(alert("Address cannot be empty!"),2000);
        return false;
    }
    if(/^[A-Za-z0-9\s,'-]*$/.test(address)){
        return true;
    }
    alert("Only alphabets, numbers, spaces, commas, hyphens and apostrophes are allowed!");
    return false;
}


function validate_pincode(elementId){
    var pincode = document.getElementById(elementId).value.trim();
    if(pincode == ""){
        //setTimeout(alert("Pincode cannot be empty!"),2000);
        return false;
    }
    if (/^[1-9]{1}[0-9]{2}\s{0,1}[0-9]{3}$/.test(pincode))
    {
        return (true);
    }
    alert("You have entered an invalid pincode!")
    return (false);
}

function validate_age(elementId){
    var user_input = document.getElementById(elementId).value.trim();
    var dob = new Date(user_input);
    if(dob === "" || dob === null || dob === NaN || user_input == ''){
        alert("Choose date of birth");
        return false;
    }
    var month_diff = Date.now() - dob.getTime();
    var age_dt = new Date(month_diff);
    var year = age_dt.getUTCFullYear();
    var age = Math.abs(year - 1970);
    if(age < 18){
        alert("Age must be greater than 18");
        return false;
    }
    return true;
}

function validate_notes(elementId){
    var notes = document.getElementById(elementId).value.trim();

    if(notes.length > 250){
        alert("Notes must be less than 250 characters");
        return false;
    }
    return true;
}



// async request calls

async function sendOTPRequest(url, data){
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

function sendEmailOTP(){
    var email = document.getElementById("email").value.trim();
    var data = {
        email : email
    }
    let url = 'http://localhost:8080/abc-bank/otp-email';
    let response = sendOTPRequest(url, data);
    response.then(function(response){
        let result = JSON.parse(response); // promise response
        console.log(result);
        if(result.status == 200){
            alert("OTP sent to email");
            document.getElementById("email").disabled = true;
        }
        else{
            alert("error - check your email");
        }
    });
}

function sendMobileOTP(){
    var phone = document.getElementById("phone").value.trim();
    var data = {
        phone : phone
    }
    let url = 'http://localhost:8080/abc-bank/otp-mobile';
    
    let response = sendOTPRequest(url, data); // promise response
    response.then(function(response){
        let result = JSON.parse(response);
        console.log(result);
        if(result.status == 200){
            alert("OTP sent to mobile");
            document.getElementById("phone").disabled = true;
        }
        else{
            alert("error - check your mobile number");
            document.getElementById("phone").disabled = false;
        }
    });
}


function verify_email_otp(){
    if(document.getElementById("email_otp").disabled == true){
        return;
    }
    var email_otp = document.getElementById("email_otp").value.trim();
    var email = document.getElementById("email").value.trim();
    var data = {
        otp : email_otp, 
        email : email
    }
    let url = 'http://localhost:8080/abc-bank/verify-email-otp';
    let response = sendOTPRequest(url, data); // promise response
    response.then(function(response){
        let result = JSON.parse(response);
        console.log(result);
        if(result.status == 200){
            alert("Email verified");
            document.getElementById("email_otp").disabled = true;
            document.getElementById("email").disabled = true;
            email_verify = true;
        }
        else{
            alert("wrong OTP - generate again!");
            document.getElementById("email").disabled = false;
        }
    });
}

function verify_mobile_otp(){
    if(document.getElementById("phone_otp").disabled == true){
        return;
    }
    var mobile_otp = document.getElementById("phone_otp").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var data = {
        otp : mobile_otp,
        mobile : phone
    }
    let url = 'http://localhost:8080/abc-bank/verify-mobile-otp';
    let response = sendOTPRequest(url, data); // promise response
    response.then(function(response){
        let result = JSON.parse(response);
        console.log(result);
        if(result.status == 200){
            alert("Mobile verified");
            document.getElementById("phone").disabled = true;
            document.getElementById("phone_otp").disabled = true;
            mobile_verify = true;
        }
        else{
            alert("Wrong OTP - generate again!");
            document.getElementById("phone").disabled = false;
        }
    });
}

