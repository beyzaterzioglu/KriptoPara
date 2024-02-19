package com.beyzaterzioglu.kriptopara.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beyzaterzioglu.kriptopara.R
import com.beyzaterzioglu.kriptopara.adapter.RecyclerViewAdapter
import com.beyzaterzioglu.kriptopara.databinding.ActivityMainBinding
import com.beyzaterzioglu.kriptopara.model.CryptoModel
import com.beyzaterzioglu.kriptopara.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel>?=null // modelleri koyacağımız liste
    private var recyclerViewAdapter : RecyclerViewAdapter?=null
    private lateinit var binding: ActivityMainBinding

    //disposable,tek kullanımlık demek. Her bir call'ı tek kullanımlık değerlendirip, aktivite ya da fragman silindiğinde
    //(yaşam döngüsünden çıkınca) bu call'ları direkt silmemiz gerekir.
    //Composite Disposible: Farklı kullan at'ları birleştirip tek bir çöp haline gtirip aktivite biitnce direkt kurtulabileceğimiz bir obje

private var compositeDisposable:CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable=CompositeDisposable()
        //recyclerView

        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager

        loadData()


    }
    private fun loadData(){
        //retrofitle ilgi bir obje oluşturmak
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // gelen verileri GSON kullanarak alıyoruz.
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

             compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())//gelen veriyi arkaplanda dinler.
            .observeOn(AndroidSchedulers.mainThread())//mainthread'e işler.
                .subscribe(this::handleResponse)) //handler response tarafına aktarır
       // val service= retrofit.create(CryptoAPI::class.java)//API ile retrofiti biribirine bağlıyoruz
      //  val call=service.getData()
/*
        call.enqueue(object : Callback<List<CryptoModel>> // enqueue, hazırladığımız isteği asenkron şekilde yollar.
        {
            override fun onResponse( //cevap varsa
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful) //gelen cevap gerçekten başarılı mı boş mu geldi
                {
                    response.body()?.let {
                        //null değilse
                        cryptoModels= ArrayList(it)
                        cryptoModels?.let {

                            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter=recyclerViewAdapter
                        }

                        /*
                        for (cryptoModel: CryptoModel in cryptoModels!!) {
                            Log.d("CRYPTO_DATA", "Currency: ${cryptoModel.currency}, Price: ${cryptoModel.price}")
                        }
                        */

                    }

                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) { //hata varsa
              //  t.printStackTrace()
                Log.e("CRYPTO_DATA_ERROR", "Veri alınamadı", t)
            }

        })
        */

    }
private fun handleResponse(crytoList: List<CryptoModel>)
{
    cryptoModels= ArrayList(crytoList)
    cryptoModels?.let {

        recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
        binding.recyclerView.adapter=recyclerViewAdapter
    }

}
    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}", Toast.LENGTH_LONG ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}