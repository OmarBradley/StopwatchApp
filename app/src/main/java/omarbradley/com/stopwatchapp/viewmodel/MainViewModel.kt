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

    private val _isEnableRightButton = MutableLiveData<Boolean>()
    val isEnableRightButton: LiveData<Boolean> = _isEnableRightButton

    private val _leftButtonTextRes = MutableLiveData<Int>()
    val leftButtonTextRes: LiveData<Int> = _leftButtonTextRes

    private var leftButtonAction: LeftButtonAction
            by Delegates.observable(LeftButtonAction.INIT) { _, _, newValue ->
                when (newValue) {
                    LeftButtonAction.INIT -> {
                        _leftButtonTextRes.value = R.string.label_start
                        _timeText.value = "00:00:00.0"
                    }
                    LeftButtonAction.START -> {
                        _leftButtonTextRes.value = R.string.label_stop
                        stopwatchLogicUseCase.runStopwatch(coroutineScope, STOPWATCH_DELAY_TIME) { milliseconds ->
                            _timeText.value = milliseconds.HHmmssFormatString
                        }
                    }
                    LeftButtonAction.STOP -> {
                        _leftButtonTextRes.value = R.string.label_continue
                        _isEnableRightButton.value = true
                        _rightButtonTextRes.value = R.string.label_init
                        stopwatchLogicUseCase.stopStopwatch(coroutineScope)
                    }
                    LeftButtonAction.CONTINUE -> {
                        _leftButtonTextRes.value = R.string.label_stop
                        stopwatchLogicUseCase.runStopwatch(coroutineScope, STOPWATCH_DELAY_TIME) { milliseconds ->
                            _timeText.value = milliseconds.HHmmssFormatString
                        }
                    }
                }
            }

    private var rightButtonAction: RightButtonAction
            by Delegates.observable(RightButtonAction.INIT) { _, _, newValue ->
                when (newValue) {
                    RightButtonAction.INIT -> {
                        _isEnableRightButton.value = false
                        _rightButtonTextRes.value = R.string.label_empty
                    }
                    RightButtonAction.RAP_RECORD -> {
                        _isEnableRightButton.value = true
                        _rightButtonTextRes.value = R.string.label_rap_record
                    }
                    RightButtonAction.RESET -> {
                        _leftButtonTextRes.value = R.string.label_start
                        _isEnableRightButton.value = false
                        _rightButtonTextRes.value = R.string.label_empty
                    }
                }
            }

    init {
        leftButtonAction = LeftButtonAction.INIT
        rightButtonAction = RightButtonAction.INIT
        _timeText.value = "00:00:00.0"
    }

    fun onClickLeftButton() {
        when (leftButtonAction) {
            LeftButtonAction.INIT -> {
                rightButtonAction = RightButtonAction.RAP_RECORD
                leftButtonAction = LeftButtonAction.START
            }
            LeftButtonAction.START -> {
                leftButtonAction = LeftButtonAction.STOP
            }
            LeftButtonAction.STOP -> {
                rightButtonAction = RightButtonAction.RAP_RECORD
                leftButtonAction = LeftButtonAction.CONTINUE
            }
            LeftButtonAction.CONTINUE -> {
                leftButtonAction = LeftButtonAction.STOP
            }
        }
    }

    fun onClickRightButton() {
        if (leftButtonAction == LeftButtonAction.STOP) {
            rightButtonAction = RightButtonAction.INIT
            leftButtonAction = LeftButtonAction.INIT
        }
    }

    fun onStopMainActivity() {

    }

    enum class LeftButtonAction {
        INIT, START, STOP, CONTINUE
    }

    enum class RightButtonAction {
        INIT, RAP_RECORD, RESET
    }

}
