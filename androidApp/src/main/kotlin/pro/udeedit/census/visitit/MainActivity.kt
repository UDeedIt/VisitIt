package pro.udeedit.census.visitit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pro.udeedit.census.visitit.ui.dashboard.DashboardScreen

/**
 * Main entry point activity for the Android application.
 *
 * Sets up the Compose UI content view using the shared [DashboardScreen].
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardScreen()
            }
        }
    }
}

/**
 * Preview for MainActivity, directly exercising [DashboardScreen].
 */
@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MaterialTheme {
        DashboardScreen()
    }
}