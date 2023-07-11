package com.traiden.fetchtrendingrepo.util

object RegistrationUtil {

    private val existingUsers = listOf<String>("Peter","Carl")


    // input is not valid
    // if username is empty or password is empty
    // or username is already taken...//
    // if password doesn't matched...//
    // password contains less than 2 digits.

    fun validateRegistrationInput(
        username:String,
        password:String,
        confirmPassword:String
    ):Boolean{
        if(username.isEmpty() || password.isEmpty()) return false
        if(username in existingUsers) return false
        if(password != confirmPassword) return false
        if(password.count { it.isDigit() }<2) return false
        return true
    }

}