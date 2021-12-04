package com.android.shopinglist.Domain

class GetShopListUseCase(private  val shopListRepository: ShopListRepository) {
    fun getShopList():List<ShopItem>{
        return shopListRepository.getShopList()
    }
}