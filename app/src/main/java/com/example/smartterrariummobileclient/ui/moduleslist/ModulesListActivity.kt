package com.example.smartterrariummobileclient.ui.moduleslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.moduleslist.ModulesListPresenter
import com.example.smartterrariummobileclient.presentation.moduleslist.ModulesListView
import com.example.smartterrariummobileclient.ui.base.BaseActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ModulesListActivity : BaseActivity(), ModulesListView {

    override val layoutResId = R.layout.activity_modules_list

    @InjectPresenter
    lateinit var presenter: ModulesListPresenter

}