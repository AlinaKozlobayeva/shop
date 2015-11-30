/**
 * Created by Alina on 09.11.2015.
 */
function validName(){
    var firstName = document.forms['newUser']['firstName'].value;
    var lastName = document.forms['newUser']['lastName'].value;
    var email = document.forms['newUser']['email'].value;
    var login = document.forms['newUser']['login'].value;
    var pass = document.forms['newUser']['password'].value;
    var confPass = document.forms['newUser']['confirmPassword'].value;
    if(firstName == null || firstName == '' ){
        alert("First name must be filled out");
        return false;
    }else if(firstName.length <= 2){
        alert("Name have to be more than 2 symbols");
        return false;
    }else if(email == null || email == ''){
        alert('Email have to be filled out');
        return false;
    }else if(login == null || login == ''){
        alert("Login must be filled out");
        return false;
    }else if(pass != confPass){
        alert("your password and confirm password don't match");
        return false;
    }
}