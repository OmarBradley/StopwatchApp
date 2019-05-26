package omarbradley.com.stopwatchapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayEach
import omarbradley.com.util.date.convertToHHmmssFormat

class MainActivity : AppCompatActivity() {

    private val job: Job by lazy { SupervisorJob() }

    private val scope: CoroutineScope by lazy {
        CoroutineScope(job + Dispatchers.Main)
    }

    private lateinit var stopwatchJob: Job

    private var seed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start.setOnClickListener {
            button_start.isEnabled = false
            scope.launch {
                stopwatchJob = launch {
                    startStopwatch(seed)
                        .collect {
                            seed = it
                            textView_time.text = it.times(100).convertToHHmmssFormat()
                        }
                }
            }
        }

        button_stop.setOnClickListener {
            scope.launch {
                stopwatchJob.cancel()
            }
            button_start.isEnabled = true
        }
    }

    private fun startStopwatch(seed: Long) =
        generateSequence(seed, Long::inc)
            .asFlow()
            .delayEach(100)

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }


}
