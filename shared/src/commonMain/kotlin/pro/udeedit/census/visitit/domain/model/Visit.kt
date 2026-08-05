package pro.udeedit.census.visitit.domain.model

/**
 * Represents a single field visit or census record within the system.
 *
 * This is the core domain entity used across the application to pass
 * data safely between the data layer and the UI layer.
 *
 * @property id The unique identifier for this visit.
 * @property title A brief, readable description of the visit's purpose.
 * @property address The physical location where the visit takes place.
 * @property status The current state of the visit (e.g., Pending, Completed).
 * @property date The scheduled or completed date of the visit.
 */
data class Visit(
    val id: String,
    val title: String,
    val address: String,
    val status: String,
    val date: String // Storing as a simple string for now, will migrate to proper Date types later
)