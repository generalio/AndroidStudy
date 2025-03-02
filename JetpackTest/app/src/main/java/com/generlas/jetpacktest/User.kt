package com.generlas.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**  
* description ： TODO:类的作用
* date : 2025/2/16 18:35 
*/

@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
