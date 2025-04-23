package ar.jg.kmp

import androidx.compose.runtime.Composable
import ar.jg.kmp.presentation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        Navigation()
    }
}