package net.test.cloudmade.data.user

import io.reactivex.Single

interface UserRepository {

    fun getUsersByQuery(query: String, page: Int): Single<List<User>>

    fun getUserByLogin(login: String): Single<User>
}