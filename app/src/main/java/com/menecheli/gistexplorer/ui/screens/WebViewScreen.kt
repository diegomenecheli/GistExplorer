package com.menecheli.gistexplorer.ui.screens

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.menecheli.gistexplorer.ui.views.LoadingView

@Composable
fun WebViewScreen(navController: NavController, url: String?) {
    var webView: WebView? by remember { mutableStateOf(null) }
    var backEnabled by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        isLoading = true
                        backEnabled = view?.canGoBack() ?: false
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        isLoading = false
                    }
                }
                loadUrl(url!!)
                webView = this
            }
        },
        update = {
            webView = it
        }
    )

    if (isLoading) {
        LoadingView()
    }

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
        if (!backEnabled) {
            navController.popBackStack()
        }
    }
}
