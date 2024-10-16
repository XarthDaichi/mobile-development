package unaeif411.delta.petmatch.service

import android.media.Image
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import unaeif411.delta.petmatch.model.animal.Animal

interface PetService {
    // Pets GETS
    @GET("api/v1/pets")
    suspend fun getAllPets() : Response<List<Animal>>

    @GET("api/v1/pets/{id}")
    suspend fun getPetById(@Path("id") id : Int) : Response<Animal>

    @GET("api/v1/pets/{id}/images")
    suspend fun getPetImage(@Path("id") id : Int) : Response<Image>

    @GET("api/v1/pets/byOwner")
    suspend fun getPetsByOwner(@Query("owner") owner : String) : Response<List<Animal>>

    @GET("api/v1/pets/bySpecies")
    suspend fun getPetsBySpecies(@Query("species") species : String) : Response<List<Animal>>

    @GET("api/v1/pets/byBreed")
    suspend fun getPetsByBreed(@Query("breed") breed : String) : Response<List<Animal>>

    // Pets POSTS
    @POST("api/v1/pets")
    suspend fun postPet(@Body pet : Animal)

    @POST("api/v1/pets/likes")
    suspend fun postPetLike(@Body pet : Animal)

    @POST("api/v1/pets/{id}/images")
    suspend fun postPetImage(@Path("id") id : Int, @Body image: Image)

    companion object {
        private var petService : PetService? = null
        fun getInstance() : PetService {
            if (petService == null) {
                petService = ServiceBuilder.buildService(PetService::class.java)
            }
            return petService!!
        }
    }
}