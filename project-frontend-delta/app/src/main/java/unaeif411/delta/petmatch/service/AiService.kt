package unaeif411.delta.petmatch.service

import android.media.Image
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import unaeif411.delta.petmatch.BuildConfig

interface AiService {

    suspend fun postAIImage(prompt : String): GenerateContentResponse? {
        val generativeModel = GenerativeModel(
            // The Gemini 1.5 models are versatile and work with both text-only and multimodal prompts
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = BuildConfig.API_KEY
        )
        return generativeModel.generateContent(prompt)
    }

    companion object {
        private var aiService : AiService? = null
        fun getInstance() : AiService {
            if (aiService == null) {
                aiService = ServiceBuilder.buildService(AiService::class.java)
            }
            return aiService!!
        }
    }
}