package com.example.smartterrariummobileclient.ui.terrariumslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartterrariummobileclient.R
import com.example.smartterrariummobileclient.presentation.terrariumslist.TerrariumsListPresenter
import com.example.smartterrariummobileclient.presentation.terrariumslist.TerrariumsListView
import com.example.smartterrariummobileclient.ui.base.BaseActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class TerrariumsListActivity : BaseActivity(), TerrariumsListView {

    override val layoutResId = R.layout.activity_terrariums_list

    @InjectPresenter
    lateinit var presenter: TerrariumsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terrariums_list)
    }
}
