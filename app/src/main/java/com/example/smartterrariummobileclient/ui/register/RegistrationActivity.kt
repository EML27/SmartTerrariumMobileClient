package com.example.smartterrariummobileclient.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.register.RegistrationPresenter
import com.example.smartterrariummobileclient.presentation.register.RegistrationView
import com.example.smartterrariummobileclient.ui.base.BaseActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class RegistrationActivity : BaseActivity(), RegistrationView {

    override val layoutResId = R.layout.activity_registration

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter
}
