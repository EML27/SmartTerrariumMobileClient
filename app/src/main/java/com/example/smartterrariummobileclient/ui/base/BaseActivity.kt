package com.example.smartterrariummobileclient.ui.base

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.smartterrariummobileclient.extensions.showToast
import com.example.smartterrariummobileclient.extensions.visible
import com.example.smartterrariummobileclient.presentation.base.BaseView
import moxy.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 333
    }

    abstract val layoutResId: Int
    open var isPortrait: Boolean = false
    open var isFullscreen: Boolean = false

    private var permissionsForRequest: Pair<String, Pair<() -> Unit, () -> Unit>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isPortrait) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (isFullscreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        if (layoutResId != 0) setContentView(layoutResId)
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (permissionsForRequest != null && !grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val granted = permissionsForRequest?.second?.first
                if (granted != null) {
                    granted()
                }
            } else {
                val denied = permissionsForRequest?.second?.second
                if (denied != null) {
                    denied()
                }
            }
        }
    }

    fun withPermission(permission: String, granted: () -> Unit, denied: () -> Unit) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            granted()
        } else {
            permissionsForRequest = Pair(permission, Pair(granted, denied))
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                BaseActivity.REQUEST_CODE_PERMISSIONS
            )
        }
    }

    protected fun showViews(vararg views: View, show: Boolean, hideType: Int = View.GONE) {
        views.forEach { it.visible(show, hideType) }
    }
}