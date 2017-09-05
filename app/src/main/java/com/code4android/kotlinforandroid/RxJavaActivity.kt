package com.code4android.kotlinforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.code4android.data.Goods
import com.code4android.net.NetRequest.getGoods
import com.code4android.net.NetRequest.getGoodsObservable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.*


/**
 * :继承的意思
 * fun：函数定义的关键字
 * 变量名称写在了类型前面
 */
class RxJavaActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "RxJavaActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        setSupportActionBar(toolbar)
        merge.setOnClickListener(this)
        contact.setOnClickListener(this)
        zip.setOnClickListener(this)
        callBack.setOnClickListener(this)
    }

    fun executeMerge() {
        Observable.merge(getGoodsObservable(1).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(2).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(3).subscribeOn(Schedulers.newThread()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe({
                    Log.e(TAG, it.toString())
                }, {
                    Log.e(TAG, it.toString())
                })

/*               //或者
                   .subscribe(object : SingleObserver<List<Goods>> {
                    override fun onSubscribe(d: Disposable?) {
                    }
                    override fun onError(e: Throwable?) {
                    }
                    override fun onSuccess(t: List<Goods>?) {
                    }
                })*/
    }

    fun executeContact() {
        Observable.concat(getGoodsObservable(1).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(2).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(3).subscribeOn(Schedulers.newThread()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe({
                    Log.e(TAG, it.toString())
                }, {
                    Log.e(TAG, it.toString())
                })
    }

    fun executeZip() {
        Observable.zip(getGoodsObservable(1).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(2).subscribeOn(Schedulers.newThread()),
                getGoodsObservable(3).subscribeOn(Schedulers.newThread()),
                Function3<Goods, Goods, Goods, List<Goods>>
                { goods0, goods1, goods2 ->
                    val list = arrayListOf<Goods>()
                    list.add(goods0)
                    list.add(goods1)
                    list.add(goods2)
                    list
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, it.toString())
                }, {
                    Log.e(TAG, it.toString())
                })
    }

    fun executeZipCallBack() {
        Observable.zip(getGoodsCallBack(1).subscribeOn(Schedulers.newThread()),
                getGoodsCallBack(2).subscribeOn(Schedulers.newThread()),
                getGoodsCallBack(3).subscribeOn(Schedulers.newThread()),
                Function3<Goods, Goods, Goods, List<Goods>>
                { goods0, goods1, goods2 ->
                    val list = arrayListOf<Goods>()
                    list.add(goods0)
                    list.add(goods1)
                    list.add(goods2)
                    list
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, it.toString())
                }, {
                    Log.e(TAG, it.toString())
                })
    }

    fun getGoodsCallBack(id: Int): Observable<Goods> {
        var subscrbe: ObservableEmitter<Goods>? = null
        var o = Observable.create<Goods> {
            subscrbe = it
        }
        getGoods(this@RxJavaActivity, id) {
            subscrbe?.onNext(it)
        }
        return o
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.merge -> {
                executeMerge()
            }
            R.id.contact -> {
                executeContact()
            }
            R.id.zip -> {
                executeZip()
            }
            R.id.callBack->{
                executeZipCallBack()
            }
        }
        //或者这样写
        when (v) {
            merge -> {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
