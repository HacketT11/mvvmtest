package net.test.cloudmade.data.repositories

import io.reactivex.Single

class ApiUserRepoRepository(private val service: UserRepoService): UserRepoRepository{

    override fun getUserReposiroties(login: String): Single<List<UserRepo>> {
        return service.getUserRepositories(login).map { it.items }
    }
}