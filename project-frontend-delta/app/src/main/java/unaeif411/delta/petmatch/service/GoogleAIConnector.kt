package unaeif411.delta.petmatch.service

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import unaeif411.delta.petmatch.BuildConfig

class GoogleAIConnector {
    private val generativeModel = GenerativeModel(modelName = "gemini-1.5-flash", apiKey = BuildConfig.API_KEY)

    suspend fun postAIImage(prompt: String): GenerateContentResponse? {
        return generativeModel.generateContent("Dame información general de la siguiente raza: " + prompt + ". No le des formato al texto, y categoriza las características.")
    }

    companion object {
        private var googleAIConnector : GoogleAIConnector? = null
        fun getInstance() : GoogleAIConnector {
            if (googleAIConnector == null) {
                googleAIConnector = GoogleAIConnector()
            }
            return googleAIConnector!!
        }
    }
}