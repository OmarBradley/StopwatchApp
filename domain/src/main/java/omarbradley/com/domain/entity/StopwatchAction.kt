package omarbradley.com.domain.entity

sealed class StopwatchAction

data class Run(
    val diffCurrentAndStartTimeMillis: Long
) : StopwatchAction()

object Stop : StopwatchAction()

object Reset : StopwatchAction()

data class RapRecord(
    val totalTimeMillis: Long,
    val recordTimeMillis: Long
) : StopwatchAction()

val RESET_TIME_MILLIS get() = 0L
