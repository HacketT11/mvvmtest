package net.test.cloudmade.data.user

import io.reactivex.Single
import net.test.cloudmade.core.network.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("/search/users")
    fun getUsersByQuery(@Query(value = "q") q: String,
                  @Query(value = "page") page: Int): Single<Response<User>>

    @GET("/users/{login}")
    fun getUserByLogin(@Path("login") login : String): Single<User>
}