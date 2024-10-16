package unaeif411.delta.petmatch.repository

import unaeif411.delta.petmatch.model.LoginRequest
import unaeif411.delta.petmatch.model.UserLoginResponse
import unaeif411.delta.petmatch.service.UserService
import unaeif411.delta.petmatch.utils.MyApplication.Companion.sessionManager
import retrofit2.Response
import unaeif411.delta.petmatch.model.Authority

class LoginRepository constructor (
    private val userService: UserService
){

    // in-memory cache of the loggedInUser object
    private var user: UserLoginResponse? = null

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        sessionManager?.deleteAuthToken()
    }

    suspend fun login(userLogin: LoginRequest)  : Response<UserLoginResponse> {
        val response = userService.login(userLogin)

        if (response.isSuccessful) {
            setLoggedInUser(response.body(), response.headers()["Authorization"].toString())
        }

        return response
    }

    private fun setLoggedInUser(loginRequest: UserLoginResponse?, token:String) {
        this.user = loginRequest
        sessionManager?.saveAuthToken(token)
        user?.username?.let { sessionManager?.saveUsername(it) }
        val authorityRole = if (loginRequest?.authorities?.contains(Authority("ROLE_ADMIN")) == true)
            loginRequest.authorities.find { it -> it.authority == "ROLE_ADMIN" }
                            else Authority("LIKE_PRIVILEGE");
        if (authorityRole != null) {
            sessionManager?.saveUserRole(authorityRole)
        }

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}