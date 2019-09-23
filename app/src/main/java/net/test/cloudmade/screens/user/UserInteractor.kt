package net.test.cloudmade.screens.user

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import net.test.cloudmade.core.base.BaseInteractor
import net.test.cloudmade.data.repositories.UserRepo
import net.test.cloudmade.data.repositories.UserRepoRepository
import net.test.cloudmade.data.user.User
import net.test.cloudmade.data.user.UserRepository
import net.test.cloudmade.utils.Workers

class UserInteractor(workers: Workers,
                     private val userRepository: UserRepository,
                     private val userRepoRepository: UserRepoRepository) : BaseInteractor(workers) {

    fun loadUserData(login: String, onSuccess: (Pair<User, List<UserRepo>>) -> Unit, onError: (Throwable) -> Unit) {
        disposables.add(Single.zip(userRepository.getUserByLogin(login),
                userRepoRepository.getUserReposiroties(login),
                userDataComposer())
                .schedule()
                .subscribe(onSuccess, onError))
    }

    class userDataComposer : BiFunction<User, List<UserRepo>, Pair<User, List<UserRepo>>> {
        override fun apply(user: User, repos: List<UserRepo>): Pair<User, List<UserRepo>> {
            return Pair(user, repos)
        }
    }
}