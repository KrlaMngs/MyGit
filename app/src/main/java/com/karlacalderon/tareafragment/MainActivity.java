package com.karlacalderon.tareafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapters.RepoAdapter;
import database.AlbumDatabaseHelper;
import models.Repo;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepoAdapter repoAdapter;
    private List<Repo> repositories;

    private FloatingActionButton fabRepoForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabRepoForm = findViewById(R.id.fabNewRepo);
        fabRepoForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RepoForm.class);
                startActivity(intent);

            }
        });
        this.loadRepositories();
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.loadRepositories();
    }
    private void loadRepositories() {
        AlbumDatabaseHelper databaseHelper = new AlbumDatabaseHelper(this);
        repositories = new ArrayList<>();
        repositories = databaseHelper.getAllRepositories();
        repoAdapter = new RepoAdapter(repositories, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(repoAdapter);

    }
}