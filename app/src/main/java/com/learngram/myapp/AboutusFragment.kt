package com.learngram.myapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction



class AboutusFragment : Fragment() {
    private lateinit var webview: WebView
    private lateinit var pgBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aboutus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview=view.findViewById(R.id.webView)
        pgBar=view.findViewById(R.id.pgBar)
        webview.loadUrl("https://www.mothersontechnology.com/")
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                pgBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pgBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }



    }

    private fun handleBackPress():() -> Boolean {
        return {
            if(webview.canGoBack()){
                webview.goBack()
                true
            }
            else{
                navigateToHomeFragment()
                true
            }

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