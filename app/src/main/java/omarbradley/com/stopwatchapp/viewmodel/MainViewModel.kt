package omarbradley.com.stopwatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import omarbradley.com.domain.usecase.stopwatch.StopwatchLogicUsecase
import omarbradley.com.stopwatchapp.R
import omarbradley.com.util.base.BaseViewModel
import omarbradley.com.util.date.HHmmssFormatString
import kotlin.properties.Delegates

const val STOPWATCH_DELAY_TIME = 10L

class MainViewModel(
    private val stopwatchLogicUseCase: StopwatchLogicUsecase
) : BaseViewModel() {

    private val _timeText = MutableLiveData<String>()
    val timeText: LiveData<String> = _timeText

    private val _rightButtonTextRes = MutableLiveData<Int>()
    val rightButtonTextRes: LiveData<Int> = _rightButtonTextRes

    private val _isEnableLeftButton = MutableLiveData<Boolean>()
    val isEnableLeftButton: LiveData<Boolean> = _isEnableLeftButton

    private val _leftButtonTextRes = MutableLiveData<Int>()
    val leftButtonTextRes: LiveData<Int> = _leftButtonTextRes

    private var stopwatchButtonState: StopwatchButtonState
            by Delegates.observable(StopwatchButtonState.INIT) { _, _, newValue ->
                when (newValue) {
                    StopwatchButtonState.INIT -> {
                        _isEnableLeftButton.value = false
                        _timeText.value = "00:00:00.0"
                        _rightButtonTextRes.value = R.string.label_start
                        _leftButtonTextRes.value = R.string.label_init
                    }
                    StopwatchButtonState.START, StopwatchButtonState.CONTINUE -> {
                        _isEnableLeftButton.value = true
                        _rightButtonTextRes.value = R.string.label_stop
                        _leftButtonTextRes.value = R.string.label_init
                        stopwatchLogicUseCase.runStopwatch(coroutineScope, STOPWATCH_DELAY_TIME) { milliseconds ->
                            _timeText.value = milliseconds.HHmmssFormatString
                        }
                    }
                    StopwatchButtonState.RESET -> {
                        _rightButtonTextRes.value = R.string.label_start
                        _leftButtonTextRes.value = R.string.label_init
                        _isEnableLeftButton.value = false
                        stopwatchLogicUseCase.resetStopwatch(coroutineScope) { milliseconds ->
                            _timeText.value = milliseconds.HHmmssFormatString
                        }
                    }
                    StopwatchButtonState.STOP -> {
                        _rightButtonTextRes.value = R.string.label_continue
                        _leftButtonTextRes.value = R.string.label_init
                        _isEnableLeftButton.value = true
                        stopwatchLogicUseCase.stopStopwatch(coroutineScope)
                    }
                }
            }

    init {
        stopwatchButtonState = StopwatchButtonState.INIT
    }

    fun onClickRightButton() {
        stopwatchButtonState = when (stopwatchButtonState) {
            StopwatchButtonState.INIT -> StopwatchButtonState.START
            StopwatchButtonState.START -> StopwatchButtonState.STOP
            StopwatchButtonState.STOP -> StopwatchButtonState.CONTINUE
            StopwatchButtonState.CONTINUE -> StopwatchButtonState.STOP
            StopwatchButtonState.RESET -> StopwatchButtonState.START
        }
    }

    fun onClickLeftButton() {
        stopwatchButtonState = StopwatchButtonState.RESET
    }

    enum class StopwatchButtonState {
        INIT, START, STOP, CONTINUE, RESET
    }


}
