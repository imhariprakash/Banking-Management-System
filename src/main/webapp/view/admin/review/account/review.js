

function approve(){

    if(empty()){
        return;
    }

    if(!validate_name("name")){
        return;
    }

    if(!validate_name("f_name")){
        return;
    }

    if(!validate_email("email")){
        return;
    }

    if(!validate_phone("phone")){
        return;
    }

    if(!validate_aadhar("aadhar")){
        return;
    }

    if(!validate_pan("pan")){
        return;
    }

    if(!validate_address("address")){
        return;
    }

    if(!validate_pincode("pincode")){
        return;
    }

    if(!validate_age("dob")){
        return;
    }

    if(!validate_notes("notes")){
        return;
    }

    submit();
    
    alert("Account created successfully and notified client!");               
}

function reject(){
    var email = document.getElementById("email").value.trim();
    var name = document.getElementById("name").value.trim();
    var review = document.getElementById("review").value.trim();
    
    let url = "http://localhost:8080/abc-bank/admin/account/reject";

    let data = {
        "email": email,
        "name": name,
        "review": review
    };

    if(review == ""){
        alert("Please enter a review");
        return;
    }

    let response = sendRequest(url, data, "DELETE");
    response.then(function(response){
        let result = JSON.parse(response);
        if(result.status == 200){
            alert("Account rejected successfully and notified client!");
        }else{
            alert(result.message);
        }
    });


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

    if(name == "" || f_name == "" || email == "" || phone == "" || aadhar == "" || pan == "" || address == "" || pincode == "" || dob == ""){
        alert("Please fill all the fields!");
        return true;
    }
    return false;
}



function submit(){
    let url = "http://localhost:8080/abc-bank/admin/account";
    let data = {
        name: document.getElementById("name").value.trim(),
        f_name: document.getElementById("f_name").value.trim(),
        email: document.getElementById("email").value.trim(),
        phone: document.getElementById("phone").value.trim(),
        aadhar: document.getElementById("aadhar").value.trim(),
        pan: document.getElementById("pan").value.trim(),
        address: document.getElementById("address").value.trim(),
        pincode: document.getElementById("pincode").value.trim(),
        dob: document.getElementById("dob").value.trim().replace("/", "-")
    };

    console.log(data);

    let response = sendRequest(url, data, "POST");
    response.then(function(response){
        let result = JSON.parse(response);
        console.log(result);
        if(result.status == 200){
            alert("Account created successfully and notified client!");
            window.location.href = "http://localhost:8080/abc-bank/login";
        }
        else{
            alert(result.message);
        }
    });
    
    
}

// disable submit by pressing enter key

$(document).on("keydown", ":input:not(textarea)", function(event) {
    if (event.key == "Enter") {
        event.preventDefault();
    }
});




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
        return false;
    }
    if (/^[1-9]{1}[0-9]{5}$/.test(pincode))
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



// async request calls

async function sendRequest(url, data, method){
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







