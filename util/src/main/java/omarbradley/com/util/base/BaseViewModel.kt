package omarbradley.com.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel : ViewModel() {

    val coroutineScope: CoroutineScope by lazy { viewModelScope }



}
