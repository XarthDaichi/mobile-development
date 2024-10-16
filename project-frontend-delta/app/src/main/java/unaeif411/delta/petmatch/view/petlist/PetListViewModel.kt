package unaeif411.delta.petmatch.view.petlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import unaeif411.delta.petmatch.model.animal.Animal
import unaeif411.delta.petmatch.repository.PetRepository

class PetListViewModel constructor (
    private val petRepository: PetRepository
) : ViewModel() {

    val listedPets = MutableLiveData<List<Animal>>()
    val selectedPet = MutableLiveData<Animal>()
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
        value = "This is PetList Fragment"
    }
    val text: LiveData<String> = _text

    fun getPetById(id : Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = petRepository.getPetById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    selectedPet.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }

        }
    }

    fun getAllPets() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = petRepository.getAllPets()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    listedPets.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }
}