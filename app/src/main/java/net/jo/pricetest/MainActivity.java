package net.jo.pricetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PriceAdapter mAdapter;
    List<Price> priceList;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressDialog = new ProgressDialog(this);


        if (checkConnection()) {

            // Show dialog
            progressDialog.setTitle("Connecting");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            // Creating an object of our api interface
            ApiService myApi = RetroClient.getApiService();

            // Calling JSON
            Call<List<Price>> call = myApi.getAggTrades("BTCUSDT", 40);

            // Enqueue Callback will be call when get response...
            call.enqueue(new Callback<List<Price>>() {
                @Override
                public void onResponse(Call<List<Price>> call, retrofit2.Response<List<Price>> response) {

                    Log.v("Response Code", "Response Code is : " + response.code());
                    // Dismiss dialog
                    progressDialog.dismiss();

                    if (response.isSuccessful()) {

                        priceList = new ArrayList<Price>();
                        priceList = response.body();

                        mAdapter = new PriceAdapter(priceList, getApplicationContext());

                        // Set layout manager
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);

                        mRecyclerView.setAdapter(mAdapter);

                    } else {

                        Toast.makeText(getApplicationContext(), "Something work wrong", Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(Call<List<Price>> call, Throwable t) {
                    Log.e("_", t.getMessage());

                    Toast.makeText(getApplicationContext(), "On Failure", Toast.LENGTH_LONG).show();

                }
            });

        } else {

            Toast.makeText(getApplicationContext(), "Check Your Internet Connection", Toast.LENGTH_LONG).show();

        }
    }


    // Check internet Connection
    public boolean checkConnection() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {

            return false;

        }
    }
}
