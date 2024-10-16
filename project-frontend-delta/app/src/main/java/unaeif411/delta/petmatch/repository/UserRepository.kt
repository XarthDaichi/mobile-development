package unaeif411.delta.petmatch.repository

import android.media.Image
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import unaeif411.delta.petmatch.model.user.PartialUser
import unaeif411.delta.petmatch.model.user.User
import unaeif411.delta.petmatch.service.UserService

class UserRepository constructor(
    val userService: UserService
) {
    suspend fun getUserImage(username : String) = userService.getUserImage(username)

    suspend fun getUserByPet(id : Int) = userService.getUserByPet(id)

    // Uses POST
    suspend fun register(user : User) = userService.register(user)

    suspend fun postUserImage(image : Image) = userService.postUserImage(image)
}