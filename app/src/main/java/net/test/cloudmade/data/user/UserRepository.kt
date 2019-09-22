package net.test.cloudmade.data.user

import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository{

    fun getQuerySubject(): Observable<Pair<String, Int>>

    fun getUsersByQuery(query: String, page: Int): Single<List<User>>
}