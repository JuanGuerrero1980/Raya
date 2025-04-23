package ar.jg.kmp

import androidx.compose.ui.window.ComposeUIViewController
import ar.jg.kmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }