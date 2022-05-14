package net.jo.pricetest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/v1/aggTrades")
    Call<List<Price>> getAggTrades(@Query("symbol") String symbol, @Query("limit") int limit);

}
