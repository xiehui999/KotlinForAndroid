package com.code4android.net

import android.content.Context
import android.util.Log
import com.code4android.data.Goods
import io.reactivex.Observable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import java.util.*

/**
 *Created by Code4Android on 2017/5/22.
 */
object NetRequest {
    //模拟网络请求
    fun getGoodsObservable(id: Int): Observable<Goods> {
        return Observable.create {
            source ->
            Thread.sleep(Random().nextInt(1000).toLong())
            var data = Goods(id, Random().nextInt(20), "地址${id}")
            source.onNext(data)
            source.onComplete()
            Log.e("getGoodsObservable:", "${id}")
        }

/*        return Observable.create {
            Thread.sleep(Random().nextInt(1000).toLong())
            var data = Goods(id, Random().nextInt(20), "地址${id}")
            it.onNext(data)
            it.onComplete()
            Log.e("getGoodsObservable:", "${id}")
        }*/
    }
    fun getGoods(ctx:Context,id: Int,callbacks:(goods:Goods)->Unit): Unit {
        ctx.doAsync {
            Thread.sleep(Random().nextInt(1000).toLong())
            var data = Goods(id, Random().nextInt(20), "地址${id}")
            ctx.runOnUiThread {
                callbacks(data)
            }
        }
    }
}