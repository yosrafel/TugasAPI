package com.example.tugaskrs.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaskrs.Admin.Adapter.DosenAdapter;
import com.example.tugaskrs.Admin.Model.Dosen;
import com.example.tugaskrs.DataDosenService;
import com.example.tugaskrs.R;
import com.example.tugaskrs.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RecyclerViewDaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenList;
    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu1) {
            Intent intent = new Intent(RecyclerViewDaftarDosen.this, CreateDosenActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_dosen);
        this.setTitle("SI KRS - Hai Admin");
        //tambahData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        DataDosenService service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("721600012");
        call.enqueue(new Callback<ArrayList<Dosen>>(){
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvDosen);
                dosenAdapter = new DosenAdapter(response.body());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarDosen.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarDosen.this,"Coba Lagi",Toast.LENGTH_SHORT);

            }
        });


   /* private void tambahData(){s
        dosenList = new ArrayList<>();
        dosenList.add(new Dosen("123","Jong Jek Siang", "Proffesor","jjs@staff.ukdw.ac.id","YOGYAKARTA",R.drawable.logoku));
        dosenList.add(new Dosen("234","Yetli Oslan", "Proffesor","Yetli@staff.ukdw.ac.id","YOGYAKARTA",R.drawable.logoku));
        dosenList.add(new Dosen("345","Lussy", "Proffesor","Lussy@staff.ukdw.ac.id","YOGYAKARTA",R.drawable.logoku));
    } */

    }
}
