package omarbradley.com.domain.usecase.stopwatch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import omarbradley.com.domain.entity.StopwatchAction

class StopwatchControllerUseCase(
    private val mainStopwatchRunner: StopwatchRunnerUseCase,
    private val rapTimeStopwatchRunner: StopwatchRunnerUseCase
) {

    fun startStopwatch(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            mainStopwatchRunner.startStopwatch()
        }

        coroutineScope.launch {
            rapTimeStopwatchRunner.startStopwatch()
        }
    }

    fun onClickRapTime(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            rapTimeStopwatchRunner.resetStopwatch()
            rapTimeStopwatchRunner.recordRapTime(mainStopwatchRunner.lastRunTimeMillis)
            rapTimeStopwatchRunner.startStopwatch()
        }
    }

    fun resetStopwatch(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            mainStopwatchRunner.resetStopwatch()
            rapTimeStopwatchRunner.resetStopwatch()
        }
    }

    fun stopStopwatch(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            mainStopwatchRunner.stopStopwatch()
            rapTimeStopwatchRunner.stopStopwatch()
        }
    }

    fun runStopwatch(coroutineScope: CoroutineScope, startTimeMillis: Long) {
        coroutineScope.launch {
            mainStopwatchRunner.runStopwatch(startTimeMillis)
            rapTimeStopwatchRunner.runStopwatch(startTimeMillis)
        }
    }

    fun subscribeMainRunner(coroutineScope: CoroutineScope, subscribe: (StopwatchAction) -> Unit) {
        coroutineScope.launch {
            mainStopwatchRunner.subscribeChannel {
                subscribe(it)
            }
        }
    }

    fun subscribeRapTimeRunner(coroutineScope: CoroutineScope, subscribe: (StopwatchAction) -> Unit) {
        coroutineScope.launch {
            rapTimeStopwatchRunner.subscribeChannel {
                subscribe(it)
            }
        }
    }

    fun closeStopwatchRunner() {
        mainStopwatchRunner.closeChannel()
        rapTimeStopwatchRunner.closeChannel()
    }

}
