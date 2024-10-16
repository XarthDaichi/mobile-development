package unaeif411.delta.petmatch.model

import unaeif411.delta.petmatch.model.role.Role

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)

data class LoginRequest(
    var username: String,
    var password: String,
)

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val username: String,
    val roles: List<Authority>,
    //... other data fields that may be accessible to the UI
)

data class UserLoginResponse(
    var username: String,
    var password: String,
    var authorities: List<Authority>,
    var accountNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var credentialsNonExpired: Boolean,
    var enabled: Boolean,
    val roles: List<Role>?
)

data class Authority(
    var authority: String,
)