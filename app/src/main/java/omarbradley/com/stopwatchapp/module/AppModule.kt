package omarbradley.com.stopwatchapp.module

import kotlinx.coroutines.channels.Channel
import omarbradley.com.domain.usecase.stopwatch.StopwatchControllerUseCase
import omarbradley.com.domain.usecase.stopwatch.StopwatchRunnerUseCase
import omarbradley.com.stopwatchapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // app
    viewModel { MainViewModel(get()) }

    // domain
    factory { StopwatchControllerUseCase(get(), get()) }
    factory { StopwatchRunnerUseCase(Channel()) }

}
