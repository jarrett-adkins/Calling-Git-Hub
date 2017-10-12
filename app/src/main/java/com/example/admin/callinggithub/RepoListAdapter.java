package com.example.admin.callinggithub;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.callinggithub.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/12/2017.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private static final String TAG = "PersonListAdapter";
    List<Repo> repoList = new ArrayList<>();

    public RepoListAdapter(List<Repo> personList) {
        this.repoList = personList;
    }

    @Override
    public RepoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        View view = LayoutInflater.from( parent.getContext() ).inflate(
                R.layout.repo_list_item, parent, false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RepoListAdapter.ViewHolder holder, int position) {
        Repo r = repoList.get( position );
        holder.name.setText( r.getName() );
        holder.created.setText( r.getCreatedAt() );
        holder.description.setText( r.getDescription() );
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, created, description;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById( R.id.tvName );
            created = itemView.findViewById( R.id.tvCreatedAt );
            description = itemView.findViewById( R.id.tvDescription );
        }
    }
}
