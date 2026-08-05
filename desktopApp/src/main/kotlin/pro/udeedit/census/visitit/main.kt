package pro.udeedit.census.visitit

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pro.udeedit.census.visitit.ui.dashboard.DashboardScreen

/**
 * Main entry point for the Desktop application.
 */
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "VisitIt - Desktop"
    ) {
        DashboardScreen()
    }
}