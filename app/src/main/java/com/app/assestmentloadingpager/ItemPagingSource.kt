import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.assestmentloadingpager.ApiService
import com.app.assestmentloadingpager.Item
import retrofit2.HttpException
import java.io.IOException

class ItemPagingSource(private val apiService: ApiService) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getItems(nextPageNumber)

            if (response.isSuccessful) {
                val items = response.body() ?: emptyList()
                LoadResult.Page(
                    data = items,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (items.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to fetch items: ${response.code()}"))
            }
        } catch (e: IOException) {
            // IOException for network failures
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for non-2xx HTTP status codes
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        // Return null to indicate that the refresh key is unknown.
        return null
    }
}
