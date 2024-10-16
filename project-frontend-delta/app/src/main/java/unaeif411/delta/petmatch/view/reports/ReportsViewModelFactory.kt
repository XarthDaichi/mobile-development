package unaeif411.delta.petmatch.view.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import unaeif411.delta.petmatch.repository.ReportRepository
import unaeif411.delta.petmatch.service.ReportService

@Suppress("UNCHECKED_CAST")
class ReportsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReportsViewModel::class.java)) {
            ReportsViewModel(
                reportRepository = ReportRepository(
                    reportService = ReportService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}