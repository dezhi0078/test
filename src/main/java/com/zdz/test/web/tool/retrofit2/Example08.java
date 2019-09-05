package com.zdz.test.web.tool.retrofit2;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * [Retrofit CallAdapter rxJava]源码
 */
public class Example08 {
    public interface BlogService {
        @GET("/blog")
        Observable<Result<List<Blog>>> getBlogs();
        /*
          「20160608补充」如果需要Header的值，可以把返回值替换为
            Observable<Response<Result<List<Blog>>>>
            Observable<retrofit2.adapter.rxjava.Result<Result<List<Blog>>>> //都叫Result，真是失策
         */

    }

    public static void main(String[] args) {
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd hh:mm:ss")
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        BlogService service = retrofit.create(BlogService.class);
        service.getBlogs()
//                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Result<List<Blog>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("onError");
                    }

                    @Override
                    public void onNext(Result<List<Blog>> blogsResult) {
                        System.out.println(blogsResult);
                    }
                });
    }
}
