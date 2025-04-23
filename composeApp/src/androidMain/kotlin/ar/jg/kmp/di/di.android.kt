package ar.jg.kmp.di

import ar.jg.kmp.data.local.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder(get()) }
}