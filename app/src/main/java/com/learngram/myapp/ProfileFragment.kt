package com.learngram.myapp


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.navigation.NavigationView


class ProfileFragment : Fragment() {

    private lateinit var profileButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        profileButton = view.findViewById(R.id.profile_button)
        nameEditText = view.findViewById(R.id.name_edit_text)
        emailEditText = view.findViewById(R.id.email_edit_text)
        phoneEditText=view.findViewById(R.id.phone_edit_text)
        passwordEditText=view.findViewById(R.id.password_edit_text)


        // Access SharedPreferences
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val savedName = sharedPreferences.getString("user_name", "")
        val savedEmail = sharedPreferences.getString("user_email", "")
        val savedPhone=  sharedPreferences.getString("user_phone", "")
        val savedPassword= sharedPreferences.getString("user_password", "")

        // Set the loaded data to EditText fields
        nameEditText.setText(savedName)
        emailEditText.setText(savedEmail)
        phoneEditText.setText(savedPhone)
        passwordEditText.setText(savedPassword)

        // Access the NavigationView and its header
        val navigationView: NavigationView = requireActivity().findViewById(R.id.navigationView)
        val headerView: View = navigationView.getHeaderView(0)
        val drawerUserName: TextView = headerView.findViewById(R.id.drawer_user_name)
        val drawerEmail: TextView = headerView.findViewById(R.id.drawer_email)

        // Update the drawer header with saved data
        drawerUserName.text = savedName
        drawerEmail.text = savedEmail

        // Set up click listener
        profileButton.setOnClickListener {
            // Save the updated data to SharedPreferences
            val userName = nameEditText.text.toString()
            val userEmail = emailEditText.text.toString()
            val userPhone=phoneEditText.text.toString()
            val userPassword=passwordEditText.text.toString()

            with(sharedPreferences.edit()) {
                putString("user_name", userName)
                putString("user_email", userEmail)
                putString("user_phone",userPhone)
                putString("user_password",userPassword)
                apply()
            }

            // Update the drawer header with new data
            drawerUserName.text = userName
            drawerEmail.text = userEmail

            SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Profile updated successfully")
                .show()
        }
    }

    private fun handleBackPress(): () -> Boolean {
        return {
            navigateToHomeFragment()
            true
        }
    }
    private fun navigateToHomeFragment() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2, HomeFragment())
        fragmentTransaction.addToBackStack(null) // Optional, if you want to add the transaction to the back stack
        fragmentTransaction.commit()
    }

    companion object {

    }
}