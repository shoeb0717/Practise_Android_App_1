package com.learngram.myapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class SettingFragment : Fragment() {

    private lateinit var themeSwitch: Switch
    private lateinit var countrySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        themeSwitch = view.findViewById(R.id.theme_switch)
        countrySpinner = view.findViewById(R.id.country_spinner)

        // Example: Populate Spinner with a list of countries
        val countries = arrayOf("India","USA", "Canada", "Mexico", "UK", "Germany")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter


        // Load current theme preference
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        themeSwitch.isChecked = isDarkTheme
//

        // Switch change listener
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save theme preference
            sharedPreferences.edit().putBoolean("dark_theme", isChecked).apply()
            // Apply theme
            applyTheme(isChecked)
        }
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        themeSwitch.isChecked = isDarkTheme
    }


    private fun applyTheme(isDarkTheme: Boolean) {
        // Apply the selected theme
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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