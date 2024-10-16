package unaeif411.delta.petmatch.springwithsecurity

import java.util.*

data class PrivilegeDetails(
    var id: Long? = null,
    var name: String? = null,
)

data class RoleDetails(
    var id: Long? = null,
    var name: String? = null,
    var privileges: List<PrivilegeDetails>? = null,
)

data class UserInput(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var enabled: Boolean? = null,
    var roles: List<RoleDetails>? = null,
)

data class UserLoginInput(
    var username: String = "",
    var password: String = "",
)

data class UserResult(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var enabled: Boolean?,
    var tokenExpired: Boolean?,
    var createDate: Date,
    var roles: List<RoleDetails>,
)

data class UserSignUpInput(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null,
)

data class UserDetails(
    var username: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var createDate: Date? = null,
    var enabled: Boolean,
    var tokenExpired: Boolean? = null,
    var pets: List<AnimalDetails>? = null,
    var likedPets: List<AnimalDetails>? = null,
    var roles: List<RoleDetails>? = null
)

data class AnimalDetails(
    var id: Long? = null,
    var name: String? = null,
    var likes: String? = null,
    var desc: String? = null,
    var user: UserDetails? = null,
    var likedByUsers: List<UserDetails>? = null,
    var speciesBreed: SpeciesDetails? = null
)

data class SpeciesDetails(
    var id: Long? = null,
    var name: String? = null,
    var breeds: List<BreedDetails>? = null
)

data class BreedDetails(
    var id: Long? = null,
    var name: String? = null
)

data class ReportDetails(
    var id: Long? = null,
    var desc: String? = null,
    var animal: AnimalDetails? = null
)

data class AnimalInput(
    var id: Long? = null,
    var name: String? = null,
    var likes: String? = null,
    var desc: String? = null,
    var user: UserDetails? = null,
    var likedByUsers: List<UserDetails>? = null,
    var speciesBreed: SpeciesDetails? = null,
)

data class AnimalResult(
    var id: Long,
    var name: String,
    var likes: String,
    var desc: String,
    var user: UserDetails,
    var speciesBreed: SpeciesDetails,
)

data class ReportInput(
    var id: Long,
    var desc: String,
    var animal: AnimalResult,
)

data class ReportResult(
    var id: Long? = null,
    var desc: String? = null,
    var animal: AnimalInput? = null,
)
