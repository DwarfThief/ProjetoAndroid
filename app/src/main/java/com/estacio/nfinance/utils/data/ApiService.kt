import com.estacio.nfinance.models.api.LoginResponse
import com.estacio.nfinance.models.api.LoginRequest
import com.estacio.nfinance.models.api.RegisterRequest
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun postRegister(@Body request: RegisterRequest): Call<RegisterResponse>
}