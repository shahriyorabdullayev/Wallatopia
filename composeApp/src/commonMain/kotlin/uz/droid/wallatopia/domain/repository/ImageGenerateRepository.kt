package uz.droid.wallatopia.domain.repository

interface ImageGenerateRepository {
    suspend fun generateImage(prompt: String) : Result<Any>
}