package omarbradley.com.stopwatchapp.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import omarbradley.com.stopwatchapp.R
import omarbradley.com.stopwatchapp.databinding.ActivityMainBinding
import omarbradley.com.stopwatchapp.viewmodel.MainViewModel
import omarbradley.com.util.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)) {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

    }

}
