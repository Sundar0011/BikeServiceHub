const login_card=document.getElementById("login-card");
const signup_card=document.getElementById("signup-card");
const otp_card=document.getElementById("otp-card");
const btn=document.getElementById("btn-container");
function login(){
    btn.style.display='none';
    signup_card.style.display='none';
    otp_card.style.display='none';
    login_card.style.display='block';
}
function signup(){
    btn.style.display='none';
    otp_card.style.display='none';
    login_card.style.display='none';
    signup_card.style.display='block';
}
function otp(){
    btn.style.display='none';
    login_card.style.display='none';
    signup_card.style.display='none';
    otp_card.style.display='block';
}