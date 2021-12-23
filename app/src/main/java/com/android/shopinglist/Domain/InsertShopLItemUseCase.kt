package com.android.shopinglist.Domain

import androidx.lifecycle.MutableLiveData

class InsertShopItemUseCase(private  val shopListRepository: ShopListRepository) {

    fun insertShopItem(shopItem: ShopItem){
       shopListRepository.insertShopItem(shopItem)
    }
}