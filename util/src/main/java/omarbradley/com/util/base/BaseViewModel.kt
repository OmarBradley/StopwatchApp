package omarbradley.com.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val coroutineScope: CoroutineScope by lazy { viewModelScope }

}
