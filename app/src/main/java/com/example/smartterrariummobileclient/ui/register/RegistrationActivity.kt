package com.example.smartterrariummobileclient.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.register.RegistrationPresenter
import com.example.smartterrariummobileclient.presentation.register.RegistrationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class RegistrationActivity : MvpAppCompatActivity(), RegistrationView {

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

}
