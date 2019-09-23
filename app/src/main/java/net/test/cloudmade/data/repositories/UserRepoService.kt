package net.test.cloudmade.data.repositories

import io.reactivex.Single
import net.test.cloudmade.core.network.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRepoService {

    @GET("users/{login}/repos")
    fun getUserRepositories(@Path("login") login: String): Single<List<UserRepo>>
}