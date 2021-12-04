package com.android.shopinglist.Domain

class InsertShopItemUseCase(private  val shopListRepository: ShopListRepository) {
    fun insertShopItem(shopItem: ShopItem){
       shopListRepository.insertShopItem(shopItem)
    }
}