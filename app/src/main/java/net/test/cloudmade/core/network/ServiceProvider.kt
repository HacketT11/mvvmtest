package net.test.cloudmade.core.network

interface ServiceProvider {

    fun <T> createService(serviceClass: Class<T>): T
}