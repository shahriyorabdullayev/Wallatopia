package uz.droid.wallatopia.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil3.network.HttpException
import okio.IOException
import uz.droid.wallatopia.data.network.response.PixabayImage
import uz.droid.wallatopia.data.network.response.PixabayResponse
import uz.droid.wallatopia.data.network.service.MainApiService
import uz.droid.wallatopia.domain.repository.MainRepository

class PixabayPagingSource(
    private val mainRepository: MainRepository
): PagingSource<Int, PixabayImage>() {

    override fun getRefreshKey(state: PagingState<Int, PixabayImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayImage> {
        return try {
            val nextPage = params.key ?: 1
            println("NextPage: $nextPage")
            val response = mainRepository.fetchWallpapersFromPixabay(nextPage)
            val result = response.getOrNull()?.hits ?: emptyList()
            println("Result ${result}")
            LoadResult.Page(
                data = result,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (result.isNotEmpty()) nextPage + 1 else null
            )
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}