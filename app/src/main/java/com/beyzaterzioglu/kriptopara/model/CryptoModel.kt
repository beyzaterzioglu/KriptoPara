package com.beyzaterzioglu.kriptopara.model

import com.google.gson.annotations.SerializedName

data class CryptoModel (
//@SerializedName("currency") //gelecek değerin parametresi "currency"dir.Onu alıp değişkene ata demektir.
    val currency: String,
//@SerializedName("price")
    val price: String
    ){
//data class'ın amacı içerisine tamamen veri çekeceğimiz ve bu verileri işleyeceğimiz bir yapı oluşturmak
//genelde data class'ların içlerinde genelde çok metot olmaz sadece constructor'unda hangi verilerin çekeceğimizin
// parametreleri olur

}