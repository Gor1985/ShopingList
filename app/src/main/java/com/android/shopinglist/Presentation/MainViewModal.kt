package com.android.shopinglist.Presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.shopinglist.Data.ShopClassRepozitoryImpl
import com.android.shopinglist.Domain.*

// если нужен контекст то унаследоваться надо от андроид виеви модел
// если не нужен то просто от виев модел
// c юс кейсами взаимодействует вью модель все знает о бизнес слое но не знает о дата слое
// с мутабле лайв дата может работать два метода setvalue , postvalue  сет только из главного потока пост из любых

class MainViewModal: ViewModel() {
    private val shopListRepository = ShopClassRepozitoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(shopListRepository)
    private val EditShopItemUseCase = EditShopItemUseCase(shopListRepository)

    val shoplist = getShopListUseCase.getShopList()



    fun deleteShopList(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)


        }

fun changeEnable(shopItem: ShopItem){
   val newItem=shopItem.copy(enabled = !shopItem.enabled)
    EditShopItemUseCase.editShopItem(newItem)

}

    }

