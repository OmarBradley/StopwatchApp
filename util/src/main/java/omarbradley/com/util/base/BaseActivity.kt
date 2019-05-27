package omarbradley.com.util.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

    protected inline fun <T> LiveData<T>.observe(crossinline observer: (T) -> Unit) =
        this.observe(this@BaseActivity, Observer { observer(it) })

}
