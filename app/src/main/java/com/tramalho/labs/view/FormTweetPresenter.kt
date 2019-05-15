package com.tramalho.labs.view

import com.tramalho.labs.data.entity.Tweet
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.data.infra.UI
import com.tramalho.labs.domain.TwitterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FormTweetPresenter(
    private val contract: TweeterContract.FormView,
    private val useCase: TwitterUseCase,
    private val scope: CoroutineScope = UI
) {

    private enum class States { VALIDATE, LOADING, SUCCESS, ERROR }

    fun loadTweetsByNick(nick: String?) {
        scope.launch {
            configState(StateData(States.VALIDATE, nick = nick))
        }
    }

    private suspend fun configState(stateData: StateData) {

        when (stateData.states) {
            States.VALIDATE -> verifyNick(stateData.nick)
            States.LOADING -> retrieveData(stateData.nick ?: "")
            States.SUCCESS -> configSuccess(stateData)
            else -> configError()
        }
    }

    private fun configError() {
        contract.hideLoading()
        contract.showError()
    }

    private suspend fun verifyNick(nick: String?) {
        when {
            nick.isNullOrEmpty() -> contract.showValidationError()
            else -> configState(StateData(States.LOADING, nick = nick))
        }
    }

    private fun configSuccess(stateData: StateData) {
        contract.hideLoading()
        contract.receiveData(stateData.tweets)
    }

    private suspend fun retrieveData(nick: String) {
        contract.showLoading()
        val result = useCase.getTweetersByNick(nick)

        when (result) {
            is Result.Success -> configState(StateData(States.SUCCESS, tweets = result.data))
            is Result.Failure -> configState(StateData(States.ERROR))
        }
    }

    private data class StateData(
        val states: States,
        val nick: String? = "",
        val tweets: ArrayList<Tweet> = ArrayList()
    )
}