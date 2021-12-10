package com.android.shopinglist.Domain

import androidx.lifecycle.LiveData


interface ShopListRepository {


    // domian нужна для разработки бизнес логики приложений которая не зависит ни от кого и чего
    // в нашем случае мы описываем что есть в нашем приложении создаем репозиторий для того что бы остальная часть приложения могла легко
    // извлекать данные. Используем принцип один класс одна задача Каждый класс в данном случае называется юскейсом
    // домейн слой работает с интерфейсом репозиторий так как он не должен знать как происходит обработка данных

    fun insertShopItem(shopItem: ShopItem)

    fun getShopList():LiveData<List<ShopItem>>



    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId:Int): ShopItem

    fun deleteShopItem(shopItem: ShopItem)


}