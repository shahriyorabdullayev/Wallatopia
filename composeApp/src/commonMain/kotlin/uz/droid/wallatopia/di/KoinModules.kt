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
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.data.network.service.impl.MainApiServiceImpl
import uz.droid.wallatopia.data.repository.MainRepositoryImpl
import uz.droid.wallatopia.domain.repository.MainRepository
import uz.droid.wallatopia.presentation.viewmodels.TestViewModel

expect val platformModule: Module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            repositoryModule,
            platformModule,
            httpClientModule,
            apiModule,
            viewModelModule
        )
    }

val apiModule = module {
    single<MainApiService> { MainApiServiceImpl(get()) }
}

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModelOf(::TestViewModel)
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
