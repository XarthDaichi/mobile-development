package unaeif411.delta.petmatch.service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MainService {
    /*// Pets

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

    // Users

    // Users GET
    @GET("api/v1/users/login")
    suspend fun login(@Query("username") username : String, @Query("password") password : String) : Response<User>

    @GET("api/v1/users/{username}/images")
    suspend fun getUserImage(@Path("username") username : String) : Response<Image>

    @GET("api/v1/users/byPet")
    suspend fun getUserByPet(@Query("pet") id : Int) : Response<PartialUser>

    // Uses POST
    @POST("api/v1/users/register")
    suspend fun register(@Body user : User) : Response<User>

    @POST("api/v1/users/images")
    suspend fun postUserImage(@Body image : Image)

    // Reports

    //Reports GET
    @GET("api/v1/reports")
    suspend fun getAllReports() : Response<List<Report>>

    @POST("api/v1/reports")
    suspend fun postReport(@Body report : Report)

    // AI
    @POST("api/v1/ai/images")
    suspend fun postAIImage(@Body image : Image) : Response<String>*/

    companion object {
        var mainService : MainService? = null
        fun getInstance() : MainService {
            if (mainService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://petmatch-v01-b95c9e82995a.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                mainService = retrofit.create(MainService::class.java)
            }
            return mainService!!
        }
    }
}