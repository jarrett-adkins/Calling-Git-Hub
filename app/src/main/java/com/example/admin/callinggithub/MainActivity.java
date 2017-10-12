package com.example.admin.callinggithub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTag";
    ImageView avatar;
    TextView nameTextView, URLTextView, repoCountTextView;
    Button repoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = findViewById(R.id.ivAvatar);
        nameTextView = findViewById( R.id.tvProfileName );
        URLTextView = findViewById( R.id.tvProfileURL );
        repoCountTextView = findViewById( R.id.tvProfileRepoCount );
        repoButton = findViewById( R.id.btnGoToRepo );

        ProfileRunnable runnable = new ProfileRunnable( avatar, nameTextView, URLTextView, repoCountTextView );
        Thread t = new Thread( runnable );
        t.start();
    }

    public void goToRepos(View view) {
        Intent intent = new Intent( this, RepoActivity.class );
        startActivity( intent );
    }
}

/*
        client = new OkHttpClient();

        request = new Request.Builder()
                .url( url )
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = null;

                try {
                    response = client.newCall(request)
                            .execute()
                            .body()
                            .string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    JSONObject mainObject = new JSONObject( response );

                    loginName = mainObject.get( "login" ).toString();
                    avatarUrl = mainObject.get( "avatar_url" ).toString();
                    profileUrl = mainObject.get( "url" ).toString();
                    repoCount = mainObject.get( "public_repos" ).toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //repo call
                url = "https://api.github.com/users/jarrett-adkins/repos";
                request = new Request.Builder()
                        .url( url )
                        .build();

                try {
                    response = client.newCall(request)
                            .execute()
                            .body()
                            .string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //serialize the response to objects with GSON
                Gson gson = new Gson();
                MyResponse[] myResponse = gson.fromJson( response, MyResponse[].class );
                List<Repo> repos = new ArrayList<>();
                //Log.d(TAG, "run: " + myResponse[0].getName() );

                for (int i = 0; i < myResponse.length; i++) {
                    repos.add( new Repo( myResponse[i].getName(),
                                         myResponse[i].getCreated(),
                                         myResponse[i].getDescription())
                             );
                }

                //bind views

                try {
                    URL newurl = new URL( avatarUrl );
                    bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    //avatar.setImageBitmap( bitmap );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                initViews();
            }
        }).start();
        //*/