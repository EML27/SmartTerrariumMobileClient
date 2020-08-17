package com.example.smartterrariummobileclient.ui.parameterslist

import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.parameterslist.ParametersListPresenter
import com.example.smartterrariummobileclient.presentation.parameterslist.ParametersListView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ParametersListActivity : MvpAppCompatActivity(), ParametersListView {

    @InjectPresenter
    lateinit var presenter: ParametersListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameters_list)
    }
}