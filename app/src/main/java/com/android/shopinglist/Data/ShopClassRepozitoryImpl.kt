package com.android.shopinglist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.shopinglist.Domain.ShopItem
import com.android.shopinglist.Domain.ShopListRepository
import kotlin.random.Random

object ShopClassRepozitoryImpl:ShopListRepository {
    // дата слой зависит от бизнес слоя и реализует интерфейс репозитроия  предоставляет конкретную его реализацию.
    // дата слой работает с данными и знает о бизнес слое все но бизнгес слой не знает ничего о дпата слое


    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 ->
       o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            insertShopItem(item)
        }
    }




    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
       insertShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun insertShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINDEND_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }


    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}
