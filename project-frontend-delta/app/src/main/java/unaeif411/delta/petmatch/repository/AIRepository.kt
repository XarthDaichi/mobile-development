package unaeif411.delta.petmatch.repository

import android.media.Image
import retrofit2.Response
import retrofit2.http.Body
import unaeif411.delta.petmatch.service.GoogleAIConnector

class AIRepository constructor(
    val aiService: GoogleAIConnector
){
    suspend fun postAIImage(prompt : String) = aiService.postAIImage(prompt)
}