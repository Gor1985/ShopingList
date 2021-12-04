package com.android.shopinglist.Domain

class EditShopItemUseCase(private  val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
 shopListRepository.editShopItem(shopItem)
    }
}