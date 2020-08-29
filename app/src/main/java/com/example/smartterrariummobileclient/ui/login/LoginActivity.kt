package com.example.smartterrariummobileclient.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.login.LoginPresenter
import com.example.smartterrariummobileclient.presentation.login.LoginView
import com.example.smartterrariummobileclient.ui.base.BaseActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class LoginActivity : BaseActivity(), LoginView {

    override val layoutResId = R.layout.activity_login

    @InjectPresenter
    lateinit var presenter: LoginPresenter
}
