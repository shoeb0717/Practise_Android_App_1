package com.learngram.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class LoginFragment : Fragment() {
    private lateinit var signupButton: Button
    private lateinit var getStarted : LinearLayout
    private lateinit var loginUserName:EditText
    private lateinit var loginUserPassword:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signupButton= view.findViewById(R.id.gotoSigninButton)
        getStarted =view.findViewById(R.id.getLoggedin)
        loginUserName=view.findViewById(R.id.login_UserName)
        loginUserPassword=view.findViewById(R.id.login_UserPassword)

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val savedName = sharedPreferences.getString("user_name", "")
        val savedPassword= sharedPreferences.getString("user_password", "")

        signupButton.setOnClickListener{
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginSignupFragmentContainer,SignUpFragment())
            fragmentTransaction.commit()
        }

        getStarted.setOnClickListener{

            val userName=loginUserName.text.toString()
            val userPassword=loginUserPassword.text.toString()

            if(userName.trim() == ""){
                Toast.makeText(requireContext(), "Name is required", Toast.LENGTH_SHORT).show()
                loginUserName.error = "Name is required !!"
                return@setOnClickListener
            }
            if(userPassword.trim() == ""){
                Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_SHORT).show()
                loginUserPassword.error = "Password is required !!"
                return@setOnClickListener
            }

            if(userName==savedName && userPassword==savedPassword){
                Toast.makeText(requireContext(), "Logged in!", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    requireContext(),
                    MainActivity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else{
                Toast.makeText(requireContext(), "Not registered, Please register", Toast.LENGTH_SHORT).show()
            }


        }
    }
    companion object {

    }
}