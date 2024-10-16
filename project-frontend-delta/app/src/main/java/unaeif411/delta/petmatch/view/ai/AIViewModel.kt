package unaeif411.delta.petmatch.view.ai

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import unaeif411.delta.petmatch.repository.AIRepository

class AIViewModel constructor(
    private val repository: AIRepository
) : ViewModel() {

    val Desc = MutableLiveData<String>()
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
        value = "This is AI Fragment"
    }
    val text: LiveData<String> = _text

    fun getAnimalDesc(prompt : String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            loading.postValue(true)
            try {
                val response = repository.postAIImage(prompt)
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        Desc.postValue(response.text)
                        loading.value = false
                    } else {
                        onError("Error : AI API not responding")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Exception: ${e.message}")
                }
            }
        }
    }
}