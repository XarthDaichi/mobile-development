package unaeif411.delta.petmatch.repository

import android.media.Image
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import unaeif411.delta.petmatch.model.animal.Animal
import unaeif411.delta.petmatch.service.PetService

class PetRepository constructor(
    private val petService: PetService
) {
    suspend fun getAllPets() = petService.getAllPets()

    suspend fun getPetById(id : Int) = petService.getPetById(id)

    suspend fun getPetImage(id : Int) = petService.getPetImage(id)

    suspend fun getPetsByOwner(owner : String) = petService.getPetsByOwner(owner)

    suspend fun getPetsBySpecies(species : String) = petService.getPetsBySpecies(species)

    suspend fun getPetsByBreed(breed : String) = petService.getPetsByBreed(breed)

    // Pets POSTS
    suspend fun postPet(pet : Animal) = petService.postPet(pet)

    suspend fun postPetLike(pet : Animal) = petService.postPetLike(pet)

    suspend fun postPetImage(id : Int, image: Image) = petService.postPetImage(id, image)
}