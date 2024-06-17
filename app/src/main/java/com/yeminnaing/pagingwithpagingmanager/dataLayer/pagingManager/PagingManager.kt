package com.yeminnaing.pagingwithpagingmanager.dataLayer.pagingManager

import coil.util.CoilUtils.result
import com.yeminnaing.pagingwithpagingmanager.domainLayer.Resources
import com.yeminnaing.pagingwithpagingmanager.domainLayer.pagingData.Paginator


class PagingManager<Key, Item>(
    private val initialKey: Key ,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (Key) -> Resources<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)

        isMakingRequest = false
        when (result) {
            is Resources.Success -> {
                val items = result.data
                currentKey= getNextKey(items)
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }
            is Resources.Error -> {
                onError(result.error)
                onLoadUpdated(false)
            }
        }
    }

    override fun rest() {
        currentKey = initialKey
    }


}