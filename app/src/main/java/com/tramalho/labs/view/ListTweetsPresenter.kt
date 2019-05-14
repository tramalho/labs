package com.tramalho.labs.view

import com.tramalho.labs.data.entity.SentimentType
import com.tramalho.labs.data.infra.Result
import com.tramalho.labs.data.infra.UI
import com.tramalho.labs.domain.TweeterAnalizeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ListTweetsPresenter(
    private val contract: TweeterContract.ListView,
    private val useCase: TweeterAnalizeUseCase,
    private val scope: CoroutineScope = UI
) {

    private enum class States { LOADING, SUCCESS, ERROR }

    fun analizeSentimentByContent(content: String) {
        scope.launch {
            configState(StateData(States.LOADING, content))
        }
    }

    private suspend fun configState(stateData: StateData) {

        when (stateData.states) {
            States.LOADING -> retrieveData(stateData.content)
            States.SUCCESS -> configSuccess(stateData)
            else -> configError()
        }
    }

    private suspend fun retrieveData(content: String) {
        contract.showLoading()
        val result = useCase.getAnalizeFromTweet(content)

        when (result) {
            is Result.Success -> configSuccess(
                StateData(States.SUCCESS, sentiment = result.data)
            )
            is Result.Failure -> configState(StateData(States.ERROR))
        }
    }

    private fun configError() {
        contract.hideLoading()
        contract.showError()
    }

    private fun configSuccess(stateData: StateData) {
        contract.hideLoading()
        contract.receiveData(stateData.sentiment)
    }

    private data class StateData(
        val states: States,
        val content: String = "",
        val sentiment: SentimentType = SentimentType.NEUTRAL
    )
}