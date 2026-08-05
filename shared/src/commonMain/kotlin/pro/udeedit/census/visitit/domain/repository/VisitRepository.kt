package pro.udeedit.census.visitit.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.udeedit.census.visitit.domain.model.Visit

/**
 * Defines the contract for fetching and managing [Visit] data.
 *
 * By using an interface, we can easily swap between a mocked implementation
 * for early UI development, and a real SQLDelight/Network implementation later.
 */
interface VisitRepository {

    /**
     * Observes the continuous stream of all visits.
     *
     * @return A [Flow] emitting the latest list of [Visit]s.
     */
    fun getVisits(): Flow<List<Visit>>

    /**
     * Adds a new visit to the data source.
     *
     * @param visit The new [Visit] entity to be saved.
     */
    suspend fun addVisit(visit: Visit)
}