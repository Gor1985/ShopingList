package com.android.shopinglist.Domain

class GetShopItemUseCaseprivate  (private val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId:Int): ShopItem{
        return shopListRepository.getShopItem(shopItemId)
    }
}