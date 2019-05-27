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

    fun runStopwatch(coroutineScope: CoroutineScope, delayTimeMillis: Long, subscribe: (Long) -> Unit) {
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
            stopwatchJob?.cancelAndJoin()
        }
    }

    fun resetStopwatch(coroutineScope: CoroutineScope, subscribe: (Long) -> Unit) {
        coroutineScope.launch {
            stopwatchJob?.cancelAndJoin()
            seed = 0L
            subscribe(seed)
        }
    }

}
