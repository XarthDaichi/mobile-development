package unaeif411.delta.petmatch.repository

import unaeif411.delta.petmatch.model.report.ReportDetails
import unaeif411.delta.petmatch.service.ReportService

class ReportRepository constructor(
    val reportService: ReportService
) {
    suspend fun getAllReports() = reportService.getAllReports()

    suspend fun postReport(reportDetails : ReportDetails) = reportService.postReport(reportDetails)
}