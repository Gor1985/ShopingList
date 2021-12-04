package com.android.shopinglist.Domain

class DeleteShopItemUseCase(private  val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){

       shopListRepository.deleteShopItem(shopItem)
    }
}