package com.example.smartterrariummobileclient.ui.moduleslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.moduleslist.ModulesListPresenter
import com.example.smartterrariummobileclient.presentation.moduleslist.ModulesListView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ModulesListActivity : MvpAppCompatActivity(), ModulesListView {

    @InjectPresenter
    lateinit var presenter: ModulesListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modules_list)
    }
}