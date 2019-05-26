package omarbradley.com.domain.usecase.stopwatch

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.delayEach

class StopwatchLogicUsecase {

    var presentStopwatchState: StopwatchState = Init

    fun startStopwatch(seed: Long, delayTimeMillis: Long) = generateSequence(seed, Long::inc)
        .asFlow()
        .delayEach(delayTimeMillis)

}

enum class StopwatchFunction {
    START, CONTINUE, STOP, RESET
}

sealed class StopwatchState

object Init : StopwatchState()

object Running : StopwatchState()

data class Stop(
    val millisecondsWhenStop: Long
) : StopwatchState()

object Reset : StopwatchState()