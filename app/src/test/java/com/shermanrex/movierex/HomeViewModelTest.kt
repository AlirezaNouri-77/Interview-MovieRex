package com.shermanrex.movierex

import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.usecase.GetMoviesUseCase
import com.shermanrex.movierex.presention.feature.home.HomeViewModel
import com.shermanrex.movierex.repository.LocalMovieRepositoryFake
import com.shermanrex.movierex.repository.RemoteMovieRepositoryFake
import com.shermanrex.movierex.util.DummyData
import com.shermanrex.movierex.util.NetworkConnectivityFake
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    lateinit var homeViewModel: HomeViewModel
    lateinit var getMoviesUseCase: GetMoviesUseCase
    var remoteMovieRepository = RemoteMovieRepositoryFake()
    var networkConnectivity = NetworkConnectivityFake()
    var localMovieRepositoryFake = LocalMovieRepositoryFake()

    val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {

        Dispatchers.setMain(dispatcher)

        getMoviesUseCase = GetMoviesUseCase(
            remoteMovieRepository = remoteMovieRepository,
            localMovieRepository = localMovieRepositoryFake,
            networkConnectivity = networkConnectivity,
            dispatcherIO = dispatcher,
        )

        homeViewModel = HomeViewModel(getMoviesUseCase)
    }

    @Test
    fun `get data from network`() = runTest {

        networkConnectivity.setConnectivityState(true)
        assertEquals(true, networkConnectivity.networkState.first())

        val initialResult = homeViewModel.uiState.value
        // initial value should be Loading
        assertEquals(true, initialResult is MovieUiState.Loading)

        remoteMovieRepository.setMovieData(Result.Success(MovieModel(DummyData.movieDataDummyList)))

        backgroundScope.launch(dispatcher) { homeViewModel.uiState.collect() }

        val result = homeViewModel.uiState.value

        if (result is MovieUiState.Success) {
            assertEquals(10, result.data.size)
        }

    }

    @Test
    fun `get data from database`() = runTest {

        networkConnectivity.setConnectivityState(false)
        assertEquals(false, networkConnectivity.networkState.first())

        val initialResult = homeViewModel.uiState.value
        // initial value should be Loading
        assertEquals(true, initialResult is MovieUiState.Loading)

        localMovieRepositoryFake.insertData(list = DummyData.MovieEntityDummy)

        backgroundScope.launch(dispatcher) { homeViewModel.uiState.collect() }

        val result = homeViewModel.uiState.value

        if (result is MovieUiState.Success) {
            assertEquals(5, result.data.size)
        }

    }

    @Test
    fun `should return error state`() = runTest {

        networkConnectivity.setConnectivityState(true)
        assertEquals(true, networkConnectivity.networkState.first())

        val initialResult = homeViewModel.uiState.value
        // initial value should be Loading
        assertEquals(true, initialResult is MovieUiState.Loading)

        remoteMovieRepository.setMovieData(Result.Failure(NetworkError.INTERNET_CONNECTION))

        backgroundScope.launch(dispatcher) { homeViewModel.uiState.collect() }

        val result = homeViewModel.uiState.value

        assertEquals(true, result !is MovieUiState.Success)

        if (result is MovieUiState.Error) {
            assertEquals(NetworkError.INTERNET_CONNECTION, result.error)
        }

    }

}