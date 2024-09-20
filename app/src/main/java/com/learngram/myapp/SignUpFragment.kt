package com.learngram.myapp

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
import cn.pedant.SweetAlert.SweetAlertDialog


class SignUpFragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var getStarted : LinearLayout
    private lateinit var signupUserName:EditText
    private lateinit var signupUserPhone:EditText
    private lateinit var signupUserEmail:EditText
    private lateinit var signupUserPassword:EditText
    private lateinit var signupUserConfirmPassword:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton=view.findViewById(R.id.gotoLoginButton)
        getStarted=view.findViewById(R.id.getSignedUp)
        signupUserName=view.findViewById(R.id.sign_up_userName)
        signupUserPhone=view.findViewById(R.id.sign_up_userPhone)
        signupUserEmail=view.findViewById(R.id.sign_up_userEmail)
        signupUserPassword=view.findViewById(R.id.sign_up_userPassword)
        signupUserConfirmPassword=view.findViewById(R.id.sign_up_userConfirmPassword)


        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        loginButton.setOnClickListener{
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginSignupFragmentContainer,LoginFragment())
            fragmentTransaction.commit()
        }

        getStarted.setOnClickListener{
            val userName=signupUserName.text.toString()
            val userEmail=signupUserEmail.text.toString()
            val userPhone=signupUserPhone.text.toString()
            val userPassword=signupUserPassword.text.toString()
            val userConfirmPassword=signupUserConfirmPassword.text.toString()


            if(userName.trim() == ""){
                Toast.makeText(requireContext(), "Name is required", Toast.LENGTH_SHORT).show()
                signupUserName.error = "Name is required !!"
                return@setOnClickListener
            }
            if(userPhone.trim() == ""){
                Toast.makeText(requireContext(), "Phone number is required", Toast.LENGTH_SHORT).show()
                signupUserPhone.error = "Phone number is required !!"
                return@setOnClickListener
            }
            if(userEmail.trim() == ""){
                Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show()
                signupUserEmail.error = "Email is required !!"
                return@setOnClickListener
            }
            if(userPassword.trim() == ""){
                Toast.makeText(requireContext(), "Password is required", Toast.LENGTH_SHORT).show()
                signupUserPassword.error = "Password is required !!"
                return@setOnClickListener
            }
            if(userConfirmPassword.trim() == ""){
                Toast.makeText(requireContext(), "Confirm your password", Toast.LENGTH_SHORT).show()
                signupUserConfirmPassword.error = "Confirm your password !!"
                return@setOnClickListener
            }
            if(userPassword!=userConfirmPassword){
                Toast.makeText(requireContext(), "Password not matched", Toast.LENGTH_SHORT).show()
                signupUserConfirmPassword.error = "Password not matched !!"
                signupUserPassword.error = "Password not matched !!"
                return@setOnClickListener
            }

            with(sharedPreferences.edit()) {
                putString("user_name", userName)
                putString("user_email", userEmail)
                putString("user_phone",userPhone)
                putString("user_password",userPassword)
                apply()
            }

            SweetAlertDialog(requireContext(),SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Registered successfully")
                .setContentText("Now you can login !")
                .show()

            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.loginSignupFragmentContainer,LoginFragment())
            fragmentTransaction.commit()
        }

    }
    companion object {

    }
}