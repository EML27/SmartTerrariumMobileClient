package com.example.smartterrariummobileclient.presentation.login

import com.example.smartterrariummobileclient.entities.LoginDto
import moxy.InjectViewState
import moxy.MvpPresenter

/**
 * Project SmartTerrariumMobileClient
 * Package com.example.smartterrariummobileclient.presentation.login
 *
 *
 *
 * Created by Emil Zamaldinov (aka piligrim) 31.07.2020
 * Copyright © 2020 TKO-Inform. All rights reserved.
 */
@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun onLoginButtonPressed(loginDto: LoginDto) {}

    fun onRegistrationButtonPressed() {}
}