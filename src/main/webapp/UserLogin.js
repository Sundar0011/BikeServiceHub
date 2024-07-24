const login_card=document.getElementById("login-card");
const signup_card=document.getElementById("signup-card");

const btn=document.getElementById("btn-container");
const welcomeContent=document.getElementById("welcome-content");
function login(){
    welcomeContent.style.display='none';
    btn.style.display='none';
    signup_card.style.display='none';
    
    login_card.style.display='block';
}
function signup(){
    welcomeContent.style.display='none';
    btn.style.display='none';
   
    login_card.style.display='none';
    signup_card.style.display='block';
}
function cancel(){
	login_card.style.display='none';
    signup_card.style.display='none';
    welcomeContent.style.display='block';
    btn.style.display='block';
    
}