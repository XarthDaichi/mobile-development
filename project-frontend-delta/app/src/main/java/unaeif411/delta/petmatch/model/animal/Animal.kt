package unaeif411.delta.petmatch.model.animal

import unaeif411.delta.petmatch.model.species.Species
import unaeif411.delta.petmatch.model.user.User
import java.util.Date

data class Animal (
    var id: Long,
    var name: String,
    var likes: String,
    var description: String,
    var speciesBreed: Species,
    //var animal_date_birth: Date?,
    //var animal_image_filename: String?
)