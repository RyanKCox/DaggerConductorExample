package com.revature.daggerconductorexample.presentation.title

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.daggerconductorexample.data.User
import com.revature.daggerconductorexample.domain.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TitlePresenter @Inject constructor(
    val userRepo:UserRepository
): MviBasePresenter<TitleView,TitleViewState>() {


    override fun bindIntents() {

        //Fake loading of screen
        val loading = Observable.just(TitleViewState.Loading)
            .ofType(TitleViewState::class.java)

        val enter = intent { it.enterIntent() }
            .map {
                createNewUser()

                TitleViewState.Display(userRepo.getUsers())
            }
            .ofType(TitleViewState::class.java)

        val data = Observable
            .just(TitleViewState.Display(emptyList()))
            .delay(2,TimeUnit.SECONDS)
            .ofType(TitleViewState::class.java)

        val viewState = data
            .mergeWith(loading)
            .mergeWith(enter)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state-> view.render(state)}

    }
    private fun createNewUser(){
        val count = userRepo.getUsers().size
        var name = "User"

        name+=count.toString()
        userRepo.createUser(name,"$name@fakeEmail.com")

    }

}
interface TitleView:MvpView{
    fun enterIntent():Observable<Unit>
    fun render(state:TitleViewState)
}
sealed class TitleViewState{
    object Loading:TitleViewState()
    data class Display(val users:List<User>):TitleViewState()
}
