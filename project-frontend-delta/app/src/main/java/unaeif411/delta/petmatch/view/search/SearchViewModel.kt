package unaeif411.delta.petmatch.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import unaeif411.delta.petmatch.model.animal.Animal
import unaeif411.delta.petmatch.repository.PetRepository

class SearchViewModel constructor(
    private val repository: PetRepository
) : ViewModel() {

    val searchedPets = MutableLiveData<List<Animal>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler {_, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Search Fragment"
    }
    val text: LiveData<String> = _text

    fun searchPetByOwner(owner : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = repository.getPetsByOwner(owner)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    searchedPets.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }

        }
    }

    fun searchPetBySpecies(species : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = repository.getPetsBySpecies(species)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    searchedPets.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }

        }
    }

    fun searchPetByBreed(breed : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = repository.getPetsByBreed(breed)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    searchedPets.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }

        }
    }
}