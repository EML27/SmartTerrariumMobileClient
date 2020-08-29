package com.example.smartterrariummobileclient.presentation.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface BaseView : MvpView {
    @Skip
    fun showMessage(msg: String)
}