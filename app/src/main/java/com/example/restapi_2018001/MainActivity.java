package com.example.restapi_2018001;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView studentItemView;
    String[] from;
    ArrayList<Map<String, String>> list;
    int[] to = { android.R.id.text1, android.R.id.text2, };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = new String[]{"name", "school"};
        studentItemView = findViewById(R.id.studenItem);
        getStudent();
    }



    private void getStudent() {

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Loading data");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDoalog.show();
        Call<PojoResponse> call = RetrofitClient.getInstance().getMyApi().getStudent();
        call.enqueue(new Callback<PojoResponse>() {
            @Override
            public void onResponse(Call<PojoResponse> call, Response<PojoResponse> response) {
                progressDoalog.dismiss();
                PojoResponse responseData = response.body();
                assert responseData != null;
                List<DataItem> data = responseData.getData();

                list = new ArrayList<Map<String, String>>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(putData(data.get(i).getName(), data.get(i).getSchool(),data.get(i).getPhotoUrl()));
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), list,
                        android.R.layout.simple_list_item_2, from, to);
                studentItemView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PojoResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
    private HashMap<String, String> putData( String name, String school,String photoUrl) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("school", school);
        item.put("photoUrl",photoUrl);
        return item;
    }

}