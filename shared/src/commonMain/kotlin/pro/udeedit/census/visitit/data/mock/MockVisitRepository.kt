package pro.udeedit.census.visitit.data.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pro.udeedit.census.visitit.domain.model.Visit
import pro.udeedit.census.visitit.domain.repository.VisitRepository

/**
 * An in-memory, mocked implementation of [VisitRepository].
 *
 * Uses a [MutableStateFlow] to hold a hardcoded list of visits,
 * mimicking a reactive database for our initial UI development.
 */
class MockVisitRepository : VisitRepository {

    // Initialize with dummy data so the UI has content
    private val _visits = MutableStateFlow(
        listOf(
            Visit("1", "Downtown Census Check", "123 Main St", "Pending", "2026-08-05"),
            Visit("2", "Suburban Area Survey", "456 Oak Rd", "Completed", "2026-08-04"),
            Visit("3", "Commercial District Audit", "789 Pine Ave", "Pending", "2026-08-03")
        )
    )

    /**
     * Exposes the internal state as a read-only [Flow].
     */
    override fun getVisits(): Flow<List<Visit>> {
        return _visits.asStateFlow()
    }

    /**
     * Adds a new visit to our in-memory list.
     */
    override suspend fun addVisit(visit: Visit) {
        _visits.update { currentList ->
            // Append the new visit to the existing list
            currentList + visit
        }
    }
}