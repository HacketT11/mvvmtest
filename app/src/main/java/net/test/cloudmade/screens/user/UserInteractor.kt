package net.test.cloudmade.screens.user

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import net.test.cloudmade.core.base.BaseInteractor
import net.test.cloudmade.data.repositories.UserRepo
import net.test.cloudmade.data.repositories.UserRepoRepository
import net.test.cloudmade.data.user.User
import net.test.cloudmade.data.user.UserRepository
import net.test.cloudmade.utils.Workers

class UserInteractor(private val workers: Workers,
                     private val userRepository: UserRepository,
                     private val userRepoRepository: UserRepoRepository) : BaseInteractor(workers) {

    fun loadUserData(login: String, onSuccess: (Pair<User, List<UserRepo>>) -> Unit, onError: (Throwable) -> Unit) {
        val userSingle = userRepository.getUserByLogin(login)
                .subscribeOn(workers.subscribe)
        val userRepoSingle = userRepoRepository.getUserReposiroties(login)
                .subscribeOn(workers.subscribe)

        disposables.add(Single.zip(userSingle, userRepoSingle, UserDataComposer())
                .observeOn(workers.observe)
                .subscribe(onSuccess, onError))
    }


    class UserDataComposer : BiFunction<User, List<UserRepo>, Pair<User, List<UserRepo>>> {
        override fun apply(user: User, repos: List<UserRepo>): Pair<User, List<UserRepo>> {
            return Pair(user, repos)
        }
    }
}