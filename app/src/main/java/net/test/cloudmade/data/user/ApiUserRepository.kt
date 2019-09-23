package net.test.cloudmade.data.user

import io.reactivex.Single

class ApiUserRepository(private val service: UserService) : UserRepository {

    override fun getUserByLogin(login: String): Single<User> {
        return service.getUserByLogin(login)
    }

    override fun getUsersByQuery(query: String, page: Int): Single<List<User>> {
        return service.getUsersByQuery(query, page).map { it.items }
    }
}