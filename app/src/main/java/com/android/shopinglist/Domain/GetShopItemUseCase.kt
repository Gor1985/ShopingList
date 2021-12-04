package com.android.shopinglist.Domain

class GetShopItemUseCaseprivate  (val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId:Int): ShopItem{
        return shopListRepository.getShopItem(shopItemId)
    }
}