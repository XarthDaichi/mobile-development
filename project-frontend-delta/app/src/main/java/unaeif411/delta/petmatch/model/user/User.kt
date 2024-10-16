package unaeif411.delta.petmatch.model.user

import unaeif411.delta.petmatch.model.animal.Animal
import unaeif411.delta.petmatch.model.role.Role
import java.sql.Timestamp

data class User(
    var username: String,
    var password: String,
    var createDate: Timestamp,
    var email: String,
    var phone: String,
    var firstName: String,
    var lastName: String,
    var user_image_filename: String,
    var tokenExpired: Boolean,
    var enabled: Boolean,
    var roles: List<Role>,
    var pets: List<Animal>,
    var likedPets: List<Animal>
)
