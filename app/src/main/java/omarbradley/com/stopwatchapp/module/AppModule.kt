package omarbradley.com.stopwatchapp.module

import omarbradley.com.domain.usecase.stopwatch.StopwatchLogicUsecase
import omarbradley.com.stopwatchapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // app
    viewModel { MainViewModel(StopwatchLogicUsecase()) }

}
