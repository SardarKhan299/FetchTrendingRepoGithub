package com.traiden.fetchtrendingrepo.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty User Name Return False`(){
        val result = RegistrationUtil.validateRegistrationInput("","123","123")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid userName and Correctly Password Return True`(){
        val result = RegistrationUtil.validateRegistrationInput("Corp","123","123")
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exist return False`(){
        val result = RegistrationUtil.validateRegistrationInput("Carl","123","123")
        assertThat(result).isFalse()
    }


    @Test
    fun `empty password return False`(){
        val result = RegistrationUtil.validateRegistrationInput("John","","123")
        assertThat(result).isFalse()
    }


    @Test
    fun `confirm password not matched return false`(){
        val result = RegistrationUtil.validateRegistrationInput("John","1234","123")
        assertThat(result).isFalse()
    }

    @Test
    fun `password length less than 2 return false`(){
        val result = RegistrationUtil.validateRegistrationInput("John","1","1")
        assertThat(result).isFalse()
    }
}