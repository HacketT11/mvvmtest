package net.test.cloudmade.data.repositories

import io.reactivex.Single

interface UserRepoRepository{

    fun getUserReposiroties(login: String): Single<List<UserRepo>>
}