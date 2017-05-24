package com.code4android.java;

import com.code4android.data.Goods;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Code4Android on 2017/5/24.
 */

public class Test {
    void test() {
        Observable.merge(getGoodsObservable(1), getGoodsObservable(2), getGoodsObservable(3))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(new Consumer<List<Goods>>() {
                    @Override
                    public void accept(@NonNull List<Goods> goodses) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });

        //subscribe或者
        new SingleObserver<List<Goods>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Goods> goodses) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }

    void zip() {
        Observable.zip(getGoodsObservable(1), getGoodsObservable(2), getGoodsObservable(3), new Function3<Goods, Goods, Goods, List<Goods>>() {
            @Override
            public List<Goods> apply(@NonNull Goods goods, @NonNull Goods goods2, @NonNull Goods goods3) throws Exception {
                List<Goods> list=new ArrayList<Goods>();
                list.add(goods);
                list.add(goods2);
                list.add(goods3);
                return list;
            }
        }).subscribe(new Consumer<List<Goods>>() {
            @Override
            public void accept(@NonNull List<Goods> goodses) throws Exception {
            }
        });

    }

    private ObservableSource<Goods> getGoodsObservable(int i) {
        return Observable.create(new ObservableOnSubscribe<Goods>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Goods> e) throws Exception {
                Goods g = new Goods(1, 1, "");
                e.onNext(g);
                e.onComplete();
            }
        });
    }
}
