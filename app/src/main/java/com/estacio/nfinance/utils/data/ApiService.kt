import com.estacio.nfinance.models.Transaction
import com.estacio.nfinance.models.api.ListTransactionResponse
import com.estacio.nfinance.models.api.LoginResponse
import com.estacio.nfinance.models.api.LoginRequest
import com.estacio.nfinance.models.api.RegisterRequest
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun postRegister(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("/last/{id}")
    fun getLastTransactions(
        @Path("id") id: String
    ): Call<ListTransactionResponse>

    @Headers("Content-Type: application/json")
    @PUT("/transaction/{id}")
    fun updateTransaction(
        @Path("id") id: String,
        @Body transaction: Transaction
    ): Call<Transaction>

    @GET("/index/{id}/{type}")
    fun getTransactionsByType(
        @Path("id") id: String,
        @Path("type") type: Int
    ): Call<ListTransactionResponse>
}