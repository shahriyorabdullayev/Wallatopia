package uz.droid.wallatopia.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse


suspend inline fun <reified T : Any> HttpClient.handle(
    httpRequestBuilder: HttpClient.() -> HttpResponse,
): Result<T> {
    return try {
        val response: T = httpRequestBuilder().body()
        Result.success(response)
    } catch (e: ServerResponseException) {
        println("500 error: ${e.message}")
        Result.failure(e)
    } catch (e: ClientRequestException) {
        println("400 error: ${e.message}")
        Result.failure(e)
    } catch (e: RedirectResponseException) {
        println("300 error: ${e.message}")
        Result.failure(e)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        Result.failure(e)
    }
}