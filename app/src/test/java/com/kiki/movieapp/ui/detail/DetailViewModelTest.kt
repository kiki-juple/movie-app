package com.kiki.movieapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.repository.MovieRepository
import com.kiki.core.domain.usecase.MovieUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val useCase = mockk<MovieUseCase>(relaxed = true)
    private val viewModel = mockk<DetailViewModel>(relaxed = true)
    private val repository = mockk<MovieRepository>(relaxed = true)

    @Test
    fun `Test Set Favorite Movie`() {
        val favoriteMovie = useCase.updateFavoriteMovie(dummyMovie, true)
        every { viewModel.updateFavorite(dummyMovie, true) } returns favoriteMovie
        repository.updateFavoriteMovie(dummyMovie, true)
        verify { repository.updateFavoriteMovie(dummyMovie, true) }
        assertEquals(favoriteMovie, viewModel.updateFavorite(dummyMovie, true))
    }

    companion object {
        val dummyMovie = Movie(id = 1)
    }
}