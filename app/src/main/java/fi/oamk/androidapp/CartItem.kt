package fi.oamk.androidapp

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class CartItem(val key: String, val name: String, val image: String, val price: String) {
}
//@Entity(tableName = "Cart")
//class CartItem {
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "ItemId")
//    var Itemid: String = ""
//
//    @ColumnInfo(name = "ItemName")
//    var ItemName: String?= null
//
//    @ColumnInfo(name = "ItemPrice")
//    var ItemPrice: Double = 0.0
//
//    @ColumnInfo(name = "ItemQuantity")
//    var ItemQuantity: Int = 0
//
//    @ColumnInfo(name = "email")
//    var email: String?= ""
//}