package unaeif411.delta.petmatch.model.user

data class PartialUser(
    var username : String,
    var email : String,
    var phone : String,
    var firstName: String,
    var lastName: String,
    var user_image_filename: String
)
