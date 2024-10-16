package unaeif411.delta.petmatch.service

import android.media.Image
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import unaeif411.delta.petmatch.model.LoginRequest
import unaeif411.delta.petmatch.model.UserLoginResponse
import unaeif411.delta.petmatch.model.user.PartialUser
import unaeif411.delta.petmatch.model.user.User

interface UserService {
    // Users GET
    @POST("api/v1/users/login")
//    suspend fun login(@Query("username") username : String, @Query("password") password : String) : Response<UserLoginResponse>
    suspend fun login(@Body userLogin: LoginRequest) : Response<UserLoginResponse>

    @GET("api/v1/users/{username}/images")
    suspend fun getUserImage(@Path("username") username : String) : Response<Image>

    @GET("api/v1/users/byPet")
    suspend fun getUserByPet(@Query("pet") id : Int) : Response<PartialUser>

    // Uses POST
    @POST("api/v1/users/register")
    suspend fun register(@Body user : User) : Response<User>

    @POST("api/v1/users/images")
    suspend fun postUserImage(@Body image : Image)

    companion object {
        private var userService : UserService? = null
        fun getInstance() : UserService {
            if (userService == null) {
                userService = ServiceBuilder.buildService(UserService::class.java)
            }
            return userService!!
        }
    }
}