package omarbradley.com.stopwatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import omarbradley.com.domain.entity.RESET_TIME_MILLIS
import omarbradley.com.domain.entity.RapRecord
import omarbradley.com.domain.entity.Reset
import omarbradley.com.domain.entity.Run
import omarbradley.com.domain.usecase.stopwatch.StopwatchControllerUseCase
import omarbradley.com.stopwatchapp.R
import omarbradley.com.stopwatchapp.view.recyclerview.RapTimeItem
import omarbradley.com.stopwatchapp.view.recyclerview.toRapTimeItem
import omarbradley.com.util.base.BaseViewModel
import omarbradley.com.util.date.HHmmssFormatString
import omarbradley.com.util.date.currentTimeMillis
import kotlin.properties.Delegates

class MainViewModel(
    private val stopwatchControllerUseCase: StopwatchControllerUseCase
) : BaseViewModel() {

    private val _timeText = MutableLiveData<String>()
    val timeText: LiveData<String> = _timeText

    private val _rapTimeText = MutableLiveData<String>()
    val rapTimeText: LiveData<String> = _rapTimeText

    private val _rightButtonTextRes = MutableLiveData<Int>()
    val rightButtonTextRes: LiveData<Int> = _rightButtonTextRes

    private val _isEnableRightButton = MutableLiveData<Boolean>()
    val isEnableRightButton: LiveData<Boolean> = _isEnableRightButton

    private val _leftButtonTextRes = MutableLiveData<Int>()
    val leftButtonTextRes: LiveData<Int> = _leftButtonTextRes

    private val _rapTimeItems = MutableLiveData<MutableList<RapTimeItem>>(mutableListOf())
    val rapTimeItems: LiveData<MutableList<RapTimeItem>> = _rapTimeItems

    private var leftButtonAction: LeftButtonAction
            by Delegates.observable(LeftButtonAction.INIT) { _, _, newValue ->
                when (newValue) {
                    LeftButtonAction.INIT -> {
                        _leftButtonTextRes.value = R.string.label_start
                    }
                    LeftButtonAction.START -> {
                        _leftButtonTextRes.value = R.string.label_stop
                        stopwatchControllerUseCase.startStopwatch(coroutineScope)
                    }
                    LeftButtonAction.STOP -> {
                        _leftButtonTextRes.value = R.string.label_continue
                        _isEnableRightButton.value = true
                        _rightButtonTextRes.value = R.string.label_init
                        stopwatchControllerUseCase.stopStopwatch(coroutineScope)
                    }
                    LeftButtonAction.CONTINUE -> {
                        _leftButtonTextRes.value = R.string.label_stop
                        stopwatchControllerUseCase.runStopwatch(coroutineScope, currentTimeMillis)
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

                        stopwatchControllerUseCase.resetStopwatch(coroutineScope)
                        with(_rapTimeItems) {
                            value?.clear()
                            value = value
                        }
                    }
                }
            }

    init {
        leftButtonAction = LeftButtonAction.INIT
        rightButtonAction = RightButtonAction.INIT
        _timeText.value = RESET_TIME_MILLIS.HHmmssFormatString
        _rapTimeText.value = RESET_TIME_MILLIS.HHmmssFormatString

        stopwatchControllerUseCase.subscribeMainRunner(coroutineScope) { action ->
            when (action) {
                is Run -> _timeText.value = action.diffCurrentAndStartTimeMillis.HHmmssFormatString
                is Reset -> _timeText.value = RESET_TIME_MILLIS.HHmmssFormatString
            }
        }

        stopwatchControllerUseCase.subscribeRapTimeRunner(coroutineScope) { action ->
            when (action) {
                is Run -> _rapTimeText.value = action.diffCurrentAndStartTimeMillis.HHmmssFormatString
                is Reset -> _rapTimeText.value = RESET_TIME_MILLIS.HHmmssFormatString
                is RapRecord -> with(_rapTimeItems) {
                    value?.add(0, action.toRapTimeItem(value?.size ?: 0))
                    value = value
                }
            }
        }
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
            rightButtonAction = RightButtonAction.RESET
            leftButtonAction = LeftButtonAction.INIT
        }
        if (rightButtonAction == RightButtonAction.RAP_RECORD) {
            stopwatchControllerUseCase.onClickRapTime(coroutineScope)
        }
    }

    override fun onCleared() {
        stopwatchControllerUseCase.closeStopwatchRunner()
        super.onCleared()
    }


    enum class LeftButtonAction {
        INIT, START, STOP, CONTINUE
    }

    enum class RightButtonAction {
        INIT, RAP_RECORD, RESET
    }

}
