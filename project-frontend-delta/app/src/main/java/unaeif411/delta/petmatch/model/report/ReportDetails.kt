package unaeif411.delta.petmatch.model.report

import unaeif411.delta.petmatch.model.animal.Animal

data class ReportDetails(
    var id: Int,
    var description: String,
    var animal: Animal
)
