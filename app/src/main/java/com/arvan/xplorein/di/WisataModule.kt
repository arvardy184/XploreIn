package com.arvan.xplorein.di

import com.arvan.xplorein.data.ViewModel.WisataViewModel
import com.arvan.xplorein.domain.repository.WisataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object WisataModule {
    @Provides
    @ViewModelScoped
    fun provideWisataViewModel(wisataRepository: WisataRepository): WisataViewModel = WisataViewModel(wisataRepository)
}