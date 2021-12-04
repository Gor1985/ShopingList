package com.android.shopinglist.Data

import com.android.shopinglist.Domain.ShopItem
import com.android.shopinglist.Domain.ShopListRepository

object ShopClassRepozitoryImpl:ShopListRepository {
    // дата слой зависит от бизнес слоя и реализует интерфейс репозитроия  предоставляет конкретную его реализацию.
    // дата слой работает с данными и знает о бизнес слое все но бизнгес слой не знает ничего о дпата слое

    private val sholist= mutableListOf<ShopItem>()
 private var autoIncrementId=0


    override fun insertShopItem(shopItem: ShopItem) {
        if (shopItem.id==ShopItem.UNDEFINDEND_ID) {


            shopItem.id = autoIncrementId++
        }
       sholist.add(shopItem)
    }

    override fun getShopList(): List<ShopItem> {
      return sholist.toList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldelement= getShopItem(shopItem.id)
        sholist.remove(oldelement)
        insertShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        // find принимает в качесте параметре тру или фолз
     return sholist.find { it.id==shopItemId
     }?:throw RuntimeException("Элемент с этим айди: $shopItemId нерабочий")
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        sholist.remove(shopItem)
    }
}