package uz.droid.wallatopia.di

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import uz.droid.wallatopia.data.local.WallatopiaDatabase
import uz.droid.wallatopia.data.local.getRoomDatabase
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.data.network.service.impl.MainApiServiceImpl
import uz.droid.wallatopia.data.repository.FavoritesRepositoryImpl
import uz.droid.wallatopia.data.repository.MainRepositoryImpl
import uz.droid.wallatopia.domain.repository.FavoritesRepository
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.viewmodels.CategoryDetailsViewModel
import uz.droid.wallatopia.presentation.viewmodels.CategoryViewModel
import uz.droid.wallatopia.presentation.viewmodels.FavoritesViewModel
import uz.droid.wallatopia.presentation.viewmodels.HomeViewModel
import uz.droid.wallatopia.presentation.viewmodels.ImageGenerateViewModel
import uz.droid.wallatopia.presentation.viewmodels.SearchViewModel

expect val platformModule: Module

val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { get<WallatopiaDatabase>().favoriteImagesDao() }
}

val apiModule = module {
    single<MainApiService> { MainApiServiceImpl(get()) }
}

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
}

val coroutinesModule = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }
}

val viewModelModule = module {
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::CategoryDetailsViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ImageGenerateViewModel)
}

val httpClientModule = module {
    single {
        HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "HTTP Client", message = message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    json = Json { ignoreUnknownKeys = true },
                    contentType = ContentType.Any
                )
            }
        }
    }
}
