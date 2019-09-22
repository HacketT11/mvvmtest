package net.test.cloudmade.data.user

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class ApiUserRepository(private val service: UserService) : UserRepository {

    val querySubject = PublishSubject.create<Pair<String, Int>>()

    override fun getQuerySubject(): Observable<Pair<String, Int>> {
        return querySubject
    }



    override fun getUsersByQuery(query: String, page: Int): Single<List<User>> {
        return service.loadUsers(query, page).map { it.items }
    }
}