package unaeif411.delta.petmatch.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import unaeif411.delta.petmatch.model.report.ReportDetails

interface ReportService {
    @GET("api/v1/reports")
    suspend fun getAllReports() : Response<List<ReportDetails>>

    @POST("api/v1/reports")
    suspend fun postReport(@Body reportDetails : ReportDetails)

    companion object {
        private var reportService : ReportService? = null
        fun getInstance() : ReportService {
            if (reportService == null) {
                reportService = ServiceBuilder.buildService(ReportService::class.java)
            }
            return reportService!!
        }
    }
}