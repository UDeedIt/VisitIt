package pro.udeedit.census.visitit.ui.dashboard

// Compose Foundation & Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

// Material 3 Components
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

// Compose Runtime & State
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

// Compose UI & Tooling
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Domain & Data
import pro.udeedit.census.visitit.data.mock.MockVisitRepository
import pro.udeedit.census.visitit.domain.model.Visit

/**
 * Stateful entry point for the Dashboard screen.
 * Handles repository instantiation and state collection.
 */
@Composable
fun DashboardScreen() {
    val repository = remember { MockVisitRepository() }
    val visits by repository.getVisits().collectAsState(initial = emptyList())

    DashboardContent(visits = visits)
}

/**
 * Stateless UI content component for the Dashboard.
 *
 * @param visits The list of [Visit] items to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(visits: List<Visit>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("VisitIt - Dashboard") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Navigate to Visit Creation screen
                }
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(
                items = visits,
                key = { visit -> visit.id }
            ) { visit ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = visit.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = visit.address,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Status: ${visit.status}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

/**
 * UI Preview using static mock data directly so it renders immediately.
 */
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val sampleVisits = listOf(
        Visit("1", "Downtown Census Check", "123 Main St", "Pending", "2026-08-05"),
        Visit("2", "Suburban Area Survey", "456 Oak Rd", "Completed", "2026-08-04"),
        Visit("3", "Commercial District Audit", "789 Pine Ave", "Pending", "2026-08-03")
    )

    MaterialTheme {
        DashboardContent(visits = sampleVisits)
    }
}