package unaeif411.delta.petmatch.view.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import unaeif411.delta.petmatch.model.report.ReportDetails
import unaeif411.delta.petmatch.repository.ReportRepository

class ReportsViewModel constructor(
    private val reportRepository: ReportRepository
): ViewModel(){
    val listedReports = MutableLiveData<List<ReportDetails>>()
    val selectedReportDetails = MutableLiveData<ReportDetails>()
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
        value = "This is the Reports fragment"
    }
    val text: LiveData<String> = _text

    fun getAllReports() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.getAllReports()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    listedReports.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }
}