package com.yeminnaing.pagingwithpagingmanager.domainLayer.pagingData

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun rest()
}