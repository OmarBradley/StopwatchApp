package omarbradley.com.domain.usecase.stopwatch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.launch

class StopwatchLogicUsecase {

    private var seed = 0L

    private var stopwatchJob: Job? = null

    var stopwatchState: StopwatchState = StopwatchState.STOP
        private set(value) {
            field = value
        }

    fun runStopwatch(coroutineScope: CoroutineScope, delayTimeMillis: Long, subscribe: (Long) -> Unit) {
        stopwatchState = StopwatchState.START
        stopwatchJob = coroutineScope.launch {
            generateSequence(seed, Long::inc)
                .asFlow()
                .delayEach(delayTimeMillis)
                .collect { milliseconds ->
                    seed = milliseconds
                    subscribe(milliseconds.times(delayTimeMillis))
                }
        }
    }

    fun stopStopwatch(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            stopwatchState = StopwatchState.STOP
            stopwatchJob?.cancelAndJoin()
        }
    }

    fun resetStopwatch(coroutineScope: CoroutineScope, subscribe: (Long) -> Unit) {
        coroutineScope.launch {
            stopwatchState = StopwatchState.RESET
            stopwatchJob?.cancelAndJoin()
            seed = 0L
            subscribe(seed)
        }
    }

}

enum class StopwatchState {
    START, STOP, RESET
}
