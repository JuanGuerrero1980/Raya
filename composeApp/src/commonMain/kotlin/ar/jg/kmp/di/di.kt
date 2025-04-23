package ar.jg.kmp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import ar.jg.kmp.BuildConfig
import ar.jg.kmp.data.CoinRepository
import ar.jg.kmp.data.local.RayaDao
import ar.jg.kmp.data.local.RayaDatabase
import ar.jg.kmp.data.remote.CoinService
import ar.jg.kmp.domain.GetBalanceUseCase
import ar.jg.kmp.domain.GetCoinPriceUseCase
import ar.jg.kmp.domain.GetSimpleTransactionsUseCase
import ar.jg.kmp.domain.SaveBalanceUseCase
import ar.jg.kmp.presentation.conversion.ConversionViewModel
import ar.jg.kmp.presentation.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            provideHttpClient,
            provideViewModel,
            provideRepository,
            provideUseCase,
            nativeModule,
        )
    }

val appModule = module {
    single(named("apiKey")) { BuildConfig.API_KEY }
    single<RayaDao> {
        val dbBuilder = get<RoomDatabase.Builder<RayaDatabase>>()
        dbBuilder.setDriver(BundledSQLiteDriver()).build().rayaDao()
    }
}

val provideHttpClient = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            install(DefaultRequest) {
                url {
                    contentType(ContentType.Application.Json)
                    protocol = URLProtocol.HTTPS
                    host = "api.coingecko.com/api/v3"
                    parameters.append("x-cg-pro-api-key", get(named("apiKey")))
                }
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }

    factoryOf(::CoinService)
}

val provideRepository = module {
    factoryOf(::CoinRepository)
}

val provideViewModel = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ConversionViewModel)
}

val provideUseCase = module {
    singleOf(::GetCoinPriceUseCase)
    singleOf(::GetBalanceUseCase)
    singleOf(::SaveBalanceUseCase)
    singleOf(::GetSimpleTransactionsUseCase)
}

expect val nativeModule: Module