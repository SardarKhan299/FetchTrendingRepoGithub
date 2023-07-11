package com.traiden.fetchtrendingrepo.util

object RegistrationUtil {

    private val existingUsers = listOf<String>("peter","carl")


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

        return true
    }

}