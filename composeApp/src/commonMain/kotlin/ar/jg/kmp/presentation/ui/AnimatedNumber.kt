package ar.jg.kmp.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedNumber(
    number: String,
    modifier: Modifier = Modifier,
    durationMillis: Int = 300
) {
    var previousNumber by remember { mutableStateOf(number) }
    val rotation by animateFloatAsState(
        targetValue = if (previousNumber != number) 180f else 0f,
        animationSpec = tween(durationMillis = durationMillis),
        label = "Number Rotation"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = previousNumber.toString(),
            fontSize = 26.sp,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer {
                    rotationX = rotation
                    cameraDistance = 8 * density
                    alpha = if (rotation < 90f) 1f else 0f // Control de opacidad
                }
        )
        Text(
            text = number.toString(),
            fontSize = 26.sp,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer {
                    rotationX = -180f + rotation // Rotación inversa para el nuevo número
                    cameraDistance = 8 * density
                    alpha = if (rotation >= 90f) 1f else 0f // Control de opacidad
                }
        )
    }

    LaunchedEffect(number) {
        if (previousNumber != number) {
            // Small delay to ensure the first half of the animation completes visually
            kotlinx.coroutines.delay(durationMillis / 2L)
            previousNumber = number
        }
    }
}