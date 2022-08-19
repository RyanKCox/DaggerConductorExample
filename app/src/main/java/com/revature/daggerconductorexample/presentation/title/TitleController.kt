package com.revature.daggerconductorexample.presentation.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding2.view.clicks
import com.revature.daggerconductorexample.R
import com.revature.daggerconductorexample.data.User
import com.revature.daggerconductorexample.databinding.ControllerTitleBinding
import com.revature.daggerconductorexample.presentation.di.DaggerPresenterComponent
import javax.inject.Inject

class TitleController: MviController<TitleView,TitlePresenter>(),TitleView {

    private lateinit var button: Button
    private lateinit var textTotalUsers:TextView
    private lateinit var textUserList:TextView
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var presenter: TitlePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.controller_title,container,false)

        setup(view)

        return view
    }

    private fun setup(view: View) {
        val binding = ControllerTitleBinding.bind(view)
        button = binding.buttonTitle
        textTotalUsers = binding.textTotalUsers
        textUserList = binding.textUsers
        progressBar = binding.progressBar
        DaggerPresenterComponent.create().inject(this)

//        presenter = TitlePresenter()

    }

    override fun createPresenter() = presenter

    override fun enterIntent() = button.clicks()

    override fun render(state: TitleViewState) {
            when(state){
                is TitleViewState.Loading->{
                    renderLoading()
                }
                is TitleViewState.Display->{
                    renderUsers(state.users)
                }
            }
    }
    private fun renderUsers(users:List<User>){


        if(users.isEmpty())
            textTotalUsers.text = "No Users Found!"
        else {
            textTotalUsers.text = "Total Users: ${users.size}"
            var str = ""
            users.forEach {
                str += "Name: ${it.name}\nEmail: ${it.email}\n\n"
            }
            textUserList.text = str
        }
        progressBar.visibility = View.GONE
        textUserList.visibility = View.VISIBLE
        textTotalUsers.visibility = View.VISIBLE
        button.visibility = View.VISIBLE

    }
    private fun renderLoading(){
        progressBar.visibility = View.VISIBLE
        textUserList.visibility = View.GONE
        textTotalUsers.visibility = View.GONE
        button.visibility = View.GONE

    }
}