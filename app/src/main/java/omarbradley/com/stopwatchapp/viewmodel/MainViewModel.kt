package omarbradley.com.stopwatchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import omarbradley.com.domain.usecase.stopwatch.StopwatchLogicUsecase
import omarbradley.com.stopwatchapp.R
import omarbradley.com.util.base.BaseViewModel
import omarbradley.com.util.date.HHmmssFormatString

const val STOPWATCH_DELAY_TIME = 100L

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

    init {
        _timeText.value = "00:00:00"
        _isEnableRightButton.value = true
        _rightButtonTextRes.value = R.string.label_start
        _leftButtonTextRes.value = R.string.label_stop
    }

    fun onClickRightButton() {
        _isEnableRightButton.value = false
        coroutineScope.launch {
            stopwatchLogicUseCase.startStopwatch(this, STOPWATCH_DELAY_TIME) { milliseconds ->
                _timeText.value = milliseconds.HHmmssFormatString
            }
        }
    }

    fun onClickLeftButton() {
        coroutineScope.launch {
            stopwatchLogicUseCase.stopStopwatch()
        }
        _isEnableRightButton.value = true
    }

}
