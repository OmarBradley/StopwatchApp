package omarbradley.com.domain.usecase.stopwatch

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.yield
import omarbradley.com.domain.entity.*
import omarbradley.com.util.date.currentTimeMillis

class StopwatchRunnerUseCase(
    private val chanel: Channel<StopwatchAction>
) {

    private var isRunningStopwatch = false

    private var lastRun: Run = Run(0)

    val lastRunTimeMillis: Long
        get() = lastRun.diffCurrentAndStartTimeMillis

    suspend fun runStopwatch(startTimeMillis: Long) {
        isRunningStopwatch = true
        val lastRunTime = lastRun.diffCurrentAndStartTimeMillis
        sendRunStopwatchTimeMillis(startTimeMillis, lastRunTime)
    }

    suspend fun startStopwatch() {
        isRunningStopwatch = true
        sendRunStopwatchTimeMillis(currentTimeMillis)
    }

    private suspend fun sendRunStopwatchTimeMillis(startTimeMillis: Long, additionalTimeMillis: Long = 0) {
        while (isRunningStopwatch) {
            val currentTime = System.currentTimeMillis()
            lastRun = Run((currentTime - startTimeMillis) + additionalTimeMillis)
            chanel.send(lastRun)
            yield()
        }
    }

    suspend fun stopStopwatch() {
        isRunningStopwatch = false
        chanel.send(Stop)
    }

    suspend fun resetStopwatch() {
        isRunningStopwatch = false
        chanel.send(Reset)
    }

    suspend fun recordRapTime(totalTimeMillis: Long) {
        isRunningStopwatch = false
        chanel.send(RapRecord(totalTimeMillis, lastRunTimeMillis))
    }

    suspend fun subscribeChannel(subscribe: (StopwatchAction) -> Unit) {
        chanel.consumeEach {
            subscribe(it)
        }
    }

    fun closeChannel() {
        chanel.close()
    }

}
