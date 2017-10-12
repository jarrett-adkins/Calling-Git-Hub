package com.example.admin.callinggithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.callinggithub.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/12/2017.
 */

public class RepoActivity extends AppCompatActivity {

    public static final String TAG = "RepoActivityTag";

    private RecyclerView rvRepoList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        rvRepoList = findViewById( R.id.rvRepoList );
        layoutManager = new LinearLayoutManager( this );
        itemAnimator = new DefaultItemAnimator();
        rvRepoList.setLayoutManager( layoutManager );
        rvRepoList.setItemAnimator( itemAnimator );

        RepoRunnable runnable = new RepoRunnable( rvRepoList );
        Thread t = new Thread( runnable );
        t.start();
    }
}
