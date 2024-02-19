package com.beyzaterzioglu.kriptopara.service

import android.database.Observable
import com.beyzaterzioglu.kriptopara.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")

    fun getData():io.reactivex.Observable<List<CryptoModel>>

//observable: gözleemlenebilen objedir.Ynai veriler geldiğinde alan ve verileri yayın yapan bir objedir.
// Bir değişiklik olduğunda gözlemleyene bilgi verir.

    //RxJava kullandığımız için artık bu kısmı yorum satırı haline getirdik.
   // fun getData(): Call<List<CryptoModel>>//Call,internetten asenkron bir şekilde verileri indirirken (main thread'i bloklamadan
                                          //ya da kullanıcı arayüzünü bloklamadan yapacağımız işlem)
    //Yani, belli bir adresten yapılacak get işlemi sonrası bize gelecek olan veri türü
}