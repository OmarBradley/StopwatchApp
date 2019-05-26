package omarbradley.com.domain.usecase.stopwatch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.launch

class StopwatchLogicUsecase {

    var seed = 0L

    lateinit var stopwatchJob: Job

    inline fun startStopwatch(coroutineScope: CoroutineScope, delayTimeMillis: Long, crossinline subscribe: (Long) -> Unit) {
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

    suspend fun stopStopwatch() {
        stopwatchJob.cancelAndJoin()
    }

}

enum class StopwatchFunction {
    START, CONTINUE, STOP, RESET
}
