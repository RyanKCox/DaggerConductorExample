package com.revature.daggerconductorexample.presentation.mainmenu

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.daggerconductorexample.presentation.title.DisplayUsersController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainMenuPresenter @Inject constructor(
    private val router:Router
):MviBasePresenter<MainMenuView,MainMenuViewState>() {
    override fun bindIntents() {

        val enter = intent { it.enterIntent() }
            .doOnNext {
                router.pushController(RouterTransaction.with(DisplayUsersController())
                    .pushChangeHandler(FadeChangeHandler())
                    .popChangeHandler(FadeChangeHandler()))
            }
            .ofType(MainMenuViewState::class.java)

        val data = Observable.just(MainMenuViewState.Display)
            .ofType(MainMenuViewState::class.java)

        val viewState = data
            .mergeWith(enter)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(viewState){view,state->view.render(state)}
    }
}
interface MainMenuView:MvpView{

    fun enterIntent():Observable<Unit>

    fun render(state:MainMenuViewState)
}
sealed class MainMenuViewState{
    object Display:MainMenuViewState()
}
