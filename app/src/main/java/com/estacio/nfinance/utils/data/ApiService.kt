import com.estacio.nfinance.models.api.LoginResponse
import com.estacio.nfinance.models.api.RegisterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun callLoginUser(): Call<LoginResponse>

    @POST("register")
    fun callRegisterUser(): Call<RegisterResponse>
}