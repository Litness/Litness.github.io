package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.example.litness.litness.Bar.Day;

import java.io.File;
import java.util.HashMap;

public class Client extends AppCompatActivity {
    //protected GeoDataClient mGeoDataClient;

    public static String currentUserName = "";
    public static HashMap<String, Bar> barMap;
    public static Bar activeBar;


    //private ArrayList<Bitmap> bitmapArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        barMap = new HashMap<>();

        // Construct a GeoDataClient.
/*        mGeoDataClient = Places.getGeoDataClient(this, null);

        //place ID of rounders
        mGeoDataClient.getPlaceById("ChIJWW5Sc5YChogRm0KRR2FuvDg").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                PlaceBufferResponse places = task.getResult();
                Place place = places.get(0);
                final CharSequence name = place.getName();
                rating = place.getRating();
                populateBarMap();
                launchFirstActivity();
                finish();
                Log.i("Place", "Place found: " + place.getName());
                places.release();
            } else {
                Log.e("Place", "Place not found.");
            }
        });*/

        populateBarMap();
        launchFirstActivity();
        finish();
    }

    private void populateBarMap() {

        //Template for bar info input
        Bar bar = new Bar();
        bar.barName = "Rounders";
        bar.coverOver = "$10";
        bar.coverUnder="$20";
        bar.wait = "10 Minutes";
        bar.litness = "5";
        bar.phone = "(205) 345-4848";
        bar.address = "301 Helen Keller Blvd";
        bar.description = "Very freshman heavy bar. They've got a boom room so great place to dance";
        bar.rating = "3.9";

        Day today = new Day();
        today.events.add("Jon Langston");
        today.events.add("Lil Peep");
        today.specials.add("$5 Tequila Shots");
        today.specials.add("$2 Bud Light");
        //always put at index 0. The adapters just look there now so we don't have to come up with so many specials
        bar.days[0] = today;

        bar.photos.add(R.drawable.img_rounders0);
        bar.photos.add(R.drawable.img_rounders1);
        bar.photos.add(R.drawable.img_rounders2);

        bar.tags.add("Night Clubs");
        bar.tags.add("Under 21");
        bar.tags.add("All Bars");


        barMap.put(bar.barName,bar);



        //Temporary so more are being populated
        bar = new Bar();
        bar.barName = "Calm Bar";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness ="2";

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[0] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Rounders1";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.litness = "3";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.description = "This bar is a chill place with a lot of good beer and hot women";
        bar.tags.add("Night Clubs");
        bar.tags.add("All Bars");

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[0] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Calm Bar1";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness = "1";

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[0] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Rounders2";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.litness = "4";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.description = "This bar is a chill place with a lot of good beer and hot women";
        bar.tags.add("Night Clubs");
        bar.tags.add("All Bars");

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[0] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Calm Bar2";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness = "5";

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[0] = today;

        barMap.put(bar.barName,bar);
    }

    private void launchFirstActivity() {
        if (preferenceFileExist("Login"))
            getLogin();
        //if it doesn't exist you can keep the client username as null
        else
            startActivity(new Intent(this,MainActivity.class));

    }

    public boolean preferenceFileExist(String fileName) {
        return new File(getApplicationContext().getApplicationInfo().dataDir + "/shared_prefs/" + fileName + ".xml").exists();
    }

    private void getLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", MODE_PRIVATE);
        String username = sharedPreferences.getString("Unm",null);
        String password = sharedPreferences.getString("Psw",null);
        checkLogin(username,password);
    }

    private void checkLogin(String username, String password) {
        //if the login is null
        if(username == null && password == null) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else {
            //set the currentUsername
            Client.currentUserName = username;
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

/*    // Request photos and metadata for the specified place.
    private void getPhotos(String placeId) {
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);
        photoMetadataResponse.addOnCompleteListener(task -> {
            // Get the list of photos.
            PlacePhotoMetadataResponse photos = task.getResult();
            // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
            PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
            // Get the first photo in the list.
            PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(0);
            // Get the attribution text.
            CharSequence attribution = photoMetadata.getAttributions();
            // Get a full-size bitmap for the photo.
            Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);
            photoResponse.addOnCompleteListener(task1 -> {
                PlacePhotoResponse photo = task1.getResult();
                Bitmap bitmap = photo.getBitmap();
                bitmapArray.add(bitmap); // Add a bitmap to array
                //handle the new bitmap here
            });
            Bar b = barMap.get("Rounders");
            b.p.add(bitmapArray);
            barMap.put("Rounders",b);
        });
    }*/
}
