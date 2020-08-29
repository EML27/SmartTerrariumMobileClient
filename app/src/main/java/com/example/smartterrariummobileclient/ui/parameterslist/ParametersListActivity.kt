package com.example.smartterrariummobileclient.ui.parameterslist

import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.parameterslist.ParametersListPresenter
import com.example.smartterrariummobileclient.presentation.parameterslist.ParametersListView
import com.example.smartterrariummobileclient.ui.base.BaseActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ParametersListActivity : BaseActivity(), ParametersListView {

    override val layoutResId = R.layout.activity_parameters_list

    @InjectPresenter
    lateinit var presenter: ParametersListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameters_list)
    }
}