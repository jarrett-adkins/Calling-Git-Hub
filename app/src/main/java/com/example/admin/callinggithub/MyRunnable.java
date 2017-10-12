package com.example.admin.callinggithub;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.example.admin.callinggithub.model.MyResponse;
import com.example.admin.callinggithub.model.Repo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Admin on 10/12/2017.
 */

public class MyRunnable extends Thread {

    private static final String TAG = "MyRunnable";
    Handler handler = new Handler( Looper.getMainLooper() );
    private OkHttpClient client;
    private Request request;
    ImageView imageView;
    Bitmap bitmap;
    String url = "https://api.github.com/users/jarrett-adkins";
    String loginName, avatarUrl, profileUrl, repoCount;

    public MyRunnable(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void run() {

        client = new OkHttpClient();

        request = new Request.Builder()
                .url( url )
                .build();

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

        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap( bitmap );
            }
        });
    }
}
