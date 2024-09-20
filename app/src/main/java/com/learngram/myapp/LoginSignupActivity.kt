package com.learngram.myapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_signup)
         val fragmentManager=supportFragmentManager
         val fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.loginSignupFragmentContainer,LoginFragment())
    }
}