package com.revature.daggerconductorexample.presentation.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding2.view.clicks
import com.revature.daggerconductorexample.R
import com.revature.daggerconductorexample.databinding.ControllerMainmenuBinding
import com.revature.daggerconductorexample.presentation.di.DaggerPresenterComponent
import javax.inject.Inject

class MainMenuController:MviController<MainMenuView,MainMenuPresenter>(),MainMenuView {

    @Inject
    lateinit var presenter: MainMenuPresenter

    private lateinit var enterButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_mainmenu,container,false)

        setup(view)

        return view
    }
    private fun setup(view:View){
        val binder = ControllerMainmenuBinding.bind(view)
        enterButton = binder.btnEnter

        DaggerPresenterComponent.builder().provideRouter(router).build().inject(this)
    }

    override fun createPresenter() = presenter

    override fun enterIntent() = enterButton.clicks()

    override fun render(state: MainMenuViewState) {
        when(state){
            is MainMenuViewState.Display->{
                enterButton.visibility = View.VISIBLE
            }
            else->{
                enterButton.visibility = View.GONE
            }
        }
    }
}