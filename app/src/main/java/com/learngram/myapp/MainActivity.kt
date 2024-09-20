package com.learngram.myapp



import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerMenuButton: ImageButton
    private lateinit var navigationView: NavigationView
    private lateinit var drawerUserName: TextView
    private lateinit var drawerEmail: TextView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        drawerMenuButton = findViewById(R.id.drawer_menu_button)
        navigationView = findViewById(R.id.navigationView)

        // Access the NavigationView and its header
        val headerView = navigationView.getHeaderView(0)
        drawerUserName = headerView.findViewById(R.id.drawer_user_name)
        drawerEmail = headerView.findViewById(R.id.drawer_email)

        // Update the drawer content with saved user data
        updateDrawerContent()


        drawerMenuButton.setOnClickListener {
            drawerLayout.open()
        }

        val fragmentManager =
            supportFragmentManager // Use getChildFragmentManager() if inside another fragment

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                    if(fragmentManager.findFragmentById(R.id.fragmentContainerView2)!=HomeFragment()){
                        replaceFragment(HomeFragment())
                    }
                    drawerLayout.close()

                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                    if(fragmentManager.findFragmentById(R.id.fragmentContainerView2)!=ProfileFragment()){
                        replaceFragment(ProfileFragment())
                    }
                    drawerLayout.close()
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                    if(fragmentManager.findFragmentById(R.id.fragmentContainerView2)!=SettingFragment()){
                        replaceFragment(SettingFragment())
                    }
                    drawerLayout.close()
                }
                R.id.nav_aboutus -> {
                    Toast.makeText(this, "About Us Clicked", Toast.LENGTH_SHORT).show()
                    if(fragmentManager.findFragmentById(R.id.fragmentContainerView2)!=AboutusFragment()){
                        replaceFragment(AboutusFragment())
                    }
                    drawerLayout.close()
                }
                R.id.nav_logout -> {
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginSignupActivity::class.java)
                    startActivity(intent)
                }

                else -> {
                    // Handle other items if necessary
                }
            }
            false
        }
    }

    private fun replaceFragment(fragmentR: Fragment) {

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragmentR)
        fragmentTransaction.addToBackStack(null) // Optional, if you want to add the transaction to the back stack
        fragmentTransaction.commit()

    }

    private fun updateDrawerContent() {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedName = sharedPreferences.getString("user_name", "") ?: ""
        val savedEmail = sharedPreferences.getString("user_email", "") ?: ""
        sharedPreferences.edit().putBoolean("dark_theme", false).apply()
        drawerUserName.text = savedName
        drawerEmail.text = savedEmail
    }

//    override fun onBackPressed() {
//        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? AboutusFragment
//        if (fragment != null && fragment.handleBackPress()) {
//            // Back press was handled by the fragment
//            return
//        } else {
//            // Call super to handle the back press as usual
//            super.onBackPressed()
//        }
//    }
override fun onBackPressed() {

        super.onBackPressed()
}

}