package com.shermanrex.movierex

import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.MovieUiState
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.usecase.GetMovieByNameUseCase
import com.shermanrex.movierex.presention.feature.search.SearchScreenAction
import com.shermanrex.movierex.presention.feature.search.SearchViewModel
import com.shermanrex.movierex.repository.RemoteMovieRepositoryFake
import com.shermanrex.movierex.util.DummyData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    val dispatcher = UnconfinedTestDispatcher()

    val remoteMovieRepository = RemoteMovieRepositoryFake()

    lateinit var getMovieByNameUseCase: GetMovieByNameUseCase
    lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        getMovieByNameUseCase = GetMovieByNameUseCase(
            remoteMovieRepository = remoteMovieRepository,
            dispatcherIO = dispatcher,
        )
        Dispatchers.setMain(dispatcher)
        searchViewModel = SearchViewModel(getMovieByNameUseCase)
    }

    @Test
    fun `search result should not empty`() = runTest {

        assertEquals(MovieUiState.Initial, searchViewModel.uiState.value)


        val searchList = Result.Success(MovieModel(DummyData.movieDataDummyList))
        remoteMovieRepository.setMovieData(searchList)
        searchViewModel.handleAction(SearchScreenAction.GetMovie("Movie Name 1"))

        backgroundScope.launch { searchViewModel.uiState.collect() }

        val result = searchViewModel.uiState.value

        if (result is MovieUiState.Success) {
            assertEquals(1, result.data.size)
        }

    }

    @Test
    fun `search result should empty`() = runTest {

        assertEquals(MovieUiState.Initial, searchViewModel.uiState.value)


        val searchList = Result.Success(MovieModel(DummyData.movieDataDummyList))
        remoteMovieRepository.setMovieData(searchList)
        searchViewModel.handleAction(SearchScreenAction.GetMovie("Movie Name 11"))

        backgroundScope.launch { searchViewModel.uiState.collect() }

        val result = searchViewModel.uiState.value

        if (result is MovieUiState.Success) {
            assertEquals(0, result.data.size)
        }

    }

}