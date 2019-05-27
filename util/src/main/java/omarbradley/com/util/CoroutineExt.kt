package omarbradley.com.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> withIoContext(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.IO) {
        block()
    }
