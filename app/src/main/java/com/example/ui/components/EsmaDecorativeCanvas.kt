package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EsmaCategory
import com.example.data.EsmaNameModel
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun EsmaDecorativeCanvas(
    item: EsmaNameModel,
    modifier: Modifier = Modifier,
    isPulsing: Boolean = false
) {
    // Dynamic halo color based on category
    val haloColor = when (item.details.category) {
        EsmaCategory.RAHMET -> Color(0xFF10B981)   // Soft green emerald
        EsmaCategory.KUDRET -> Color(0xFFFBBF24)   // Deep golden amber
        EsmaCategory.ILIM -> Color(0xFF3B82F6)     // Divine deep blue/slate
        EsmaCategory.YARATILIS -> Color(0xFF06B6D4) // Bright turquoise cyan
        EsmaCategory.TENZIH -> Color(0xFFEC4899)     // Pure violet obsidian pink
    }

    // Spin animation for background sacred star geometry
    val infiniteTransition = rememberInfiniteTransition(label = "SacredSpin")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Glowing breathe factor
    val breatheFactor by infiniteTransition.animateFloat(
        initialValue = 0.88f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    val multiplier = if (isPulsing) breatheFactor * 1.08f else breatheFactor

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Drawing beautiful circular & octagonal vector geometry
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val baseRadius = size.width.coerceAtMost(size.height) / 2 * 0.82f
            val activeRadius = baseRadius * multiplier

            // 1. Draw dynamic ambient radial glow halo
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(haloColor.copy(alpha = 0.22f), Color.Transparent),
                    center = center,
                    radius = activeRadius * 1.4f
                ),
                center = center,
                radius = activeRadius * 1.4f
            )

            // 2. Draw outer golden margin rings
            drawCircle(
                color = Color(0xFFFBE090).copy(alpha = 0.25f), // Pale Gold
                center = center,
                radius = activeRadius,
                style = Stroke(width = 1.dp.toPx())
            )

            drawCircle(
                color = Color(0xFFE0A96D).copy(alpha = 0.45f), // Warm Gold
                center = center,
                radius = activeRadius - 4.dp.toPx(),
                style = Stroke(
                    width = 1.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 4.dp.toPx()))
                )
            )

            // 3. Draw Arabic octagram Rub el Hizb (8-pointed star symbol)
            rotate(rotationAngle, pivot = center) {
                val starPath1 = Path()
                val starPath2 = Path()
                val innerR = activeRadius - 12.dp.toPx()

                // Create rotated overlapping squares to form clean 8-pointed star
                fun addSquarePath(path: Path, offsetAngle: Double) {
                    for (i in 0..4) {
                        val angle = Math.toRadians(i * 90.0 + offsetAngle)
                        val x = center.x + innerR * cos(angle).toFloat()
                        val y = center.y + innerR * sin(angle).toFloat()
                        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                    }
                    path.close()
                }

                addSquarePath(starPath1, 0.0)
                addSquarePath(starPath2, 45.0)

                // Draw star outlines
                drawPath(
                    path = starPath1,
                    color = Color(0xFFFBE090).copy(alpha = 0.6f),
                    style = Stroke(width = 1.5f.dp.toPx())
                )
                drawPath(
                    path = starPath2,
                    color = Color(0xFFFBE090).copy(alpha = 0.6f),
                    style = Stroke(width = 1.5f.dp.toPx())
                )

                // Draw tiny jewel circles on each point of the star
                for (i in 0 until 8) {
                    val angle = Math.toRadians(i * 45.0)
                    val px = center.x + innerR * cos(angle).toFloat()
                    val py = center.y + innerR * sin(angle).toFloat()
                    drawCircle(
                        color = Color(0xFFE0A96D),
                        center = Offset(px, py),
                        radius = 2.dp.toPx()
                    )
                }
            }

            // 4. Draw inner framing divider
            drawCircle(
                color = Color(0xFFFBE090).copy(alpha = 0.35f),
                center = center,
                radius = activeRadius - 22.dp.toPx(),
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // 5. Present the Arabic Calligraphy inside the frame elegantly
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(26.dp)
        ) {
            androidx.compose.material3.Text(
                text = item.details.arabic,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xFFFBE090), // Gold
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            androidx.compose.material3.Text(
                text = item.details.pronunciation,
                fontWeight = FontWeight.Light,
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        }
    }
}
