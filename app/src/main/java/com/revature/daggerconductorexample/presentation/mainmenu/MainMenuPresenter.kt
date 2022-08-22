package com.revature.daggerconductorexample.presentation.mainmenu

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.daggerconductorexample.presentation.core.navigator.NavigationAction
import com.revature.daggerconductorexample.presentation.core.navigator.Navigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainMenuPresenter @Inject constructor(
    private val navigator: Navigator
):MviBasePresenter<MainMenuView,MainMenuViewState>() {
    override fun bindIntents() {

        val enter = intent { it.enterIntent() }
            .doOnNext {
                navigator.navigateTo(NavigationAction.DisplayUsers)
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
