package com.ncarignan.buycheapthings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.CheapItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CheapThingAdapter.ClickOnCheapThingListener {

    Database database;

    @Override
    public void onResume() { // this is probably the correct place for ALL rendered info

        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView address = findViewById(R.id.preferencesAddress);
        address.setText(preferences.getString("addressPotato", "Go to Settings to set an address for shipping"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {//{6, 92, 10, 30}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            // Log === system.out.println
            // easily searchable by key
            Log.i("MainActivityAmplify", "Initialized Amplify");

            // TODO: super important: grab the new CheapThing;
            CheapItem cheapThing; // make a var for cheapThing
            cheapThing = CheapItem.builder()
                    .thingName("dog collar") // use a builder to add the key value pairs to our cheap thing
                    .price(3.29f)
                    .quantity(5)
                    .build();

            Amplify.API.mutate( // run a mutation (in REST, Add, Delete, Update, or Patch)
                    ModelMutation.create(cheapThing), // choose the JAVA create mutation (graphql isnt looking at this as create, its just a method name)
                    response -> Log.i("MainActivityAmplify", "Dog collar saved successfully"),
                    error -> {
                        Log.e("MainActivityAmplify", error.getMessage());
                        Log.e("MainActivityAmplify", Arrays.toString(error.getStackTrace() ));
//                        Log.e("MainActivityAmplify", error.toString());
                        System.out.println(error);
                        Log.e("MainActivityAmplify", "Dog collar not saved successfully");
                    }
            );

        } catch (AmplifyException error) {
            Log.e("MainActivityAmplify", "Could not initialize Amplify", error);
        }















        database = Room.databaseBuilder(getApplicationContext(), Database.class, "ncarignan_cheap_things")
                .allowMainThreadQueries()
                .build();

        NotificationChannel channel = new NotificationChannel("basic", "basic", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("basic notifications");
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);


        // ===================== RecyclerView

        ArrayList<CheapThing> cheapThings = (ArrayList<CheapThing>) database.cheapThingDao().getAllCheapThingsReversed();

        RecyclerView recyclerView = findViewById(R.id.cart_preview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CheapThingAdapter(cheapThings, this));
        // ====================





        // element by id
        // click/type
        // callback
        Button buyButton = MainActivity.this.findViewById(R.id.buybutton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText itemNameInput = MainActivity.this.findViewById(R.id.editItemName);
                EditText itemQuantityInput = MainActivity.this.findViewById(R.id.editTextQuantity);
                EditText itemPriceInput = MainActivity.this.findViewById(R.id.editTextPriceDecimal);
                String itemName = itemNameInput.getText().toString();
                int quantity = Integer.parseInt(itemQuantityInput.getText().toString());
                float price = Float.parseFloat(itemPriceInput.getText().toString());
                System.out.println(String.format("We are going to buy %d %ss for $%f", quantity, itemName, price));

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "basic")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(itemName)
                        .setContentText("Buying " + quantity + " " + itemName)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_MAX);

                notificationManager.notify(89898989, builder.build());

                // TODO: save the item to the database
                CheapThing cheapThing = new CheapThing(itemName, quantity, price);
                database.cheapThingDao().saveTheThing(cheapThing);
                cheapThings.add(0, cheapThing);
                recyclerView.getAdapter().notifyItemInserted(0);
//                recyclerView.getLayoutManager().smoothScrollToPosition(0);
                recyclerView.smoothScrollToPosition(0);



//                Intent goToPaymentConfirmation = new Intent(MainActivity.this, PaymentConfirmation.class);
//                goToPaymentConfirmation.putExtra("name", itemName);
//                goToPaymentConfirmation.putExtra("quantity", quantity);
//                goToPaymentConfirmation.putExtra("price", price);
//                MainActivity.this.startActivity(goToPaymentConfirmation);
            }
        });

        Button goToCartButton = MainActivity.this.findViewById(R.id.visit_cart);
        goToCartButton.setOnClickListener((view) -> {
            System.out.println("going to cart");
            // going to another app is called an intent
            // its generic, it gets handled by something else
            // explicit (internal to the app or the domain (send to a specific page we control)
            // implicit (goes anywhere, tells android the things we want to do, android opens a choice box of everything registered that can do it)

//            Intent shareToPhoneIntent = new Intent(Intent.ACTION_SEND);
//            shareToPhoneIntent.putExtra(Intent.EXTRA_TEXT, "Sharing this to the phone y'all!");
//            // I forgot this last time
//            shareToPhoneIntent.setType("text/plain");
//            MainActivity.this.startActivity(Intent.createChooser(shareToPhoneIntent, "where do you want to send this message,huh?"));

            Intent goToCartIntent = new Intent(MainActivity.this, Cart.class);
            MainActivity.this.startActivity(goToCartIntent);
        });

        ImageButton goToSettingsButton = findViewById(R.id.settingsButton);
        goToSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsPage();
            }
        });

        Button goToOrderHistory = findViewById(R.id.order_history_nav);
        goToOrderHistory.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, OrderHistory.class);
            MainActivity.this.startActivity(intent);
        });
    }

    public void openSettingsPage(){
        Intent intent = new Intent(this, UserSettings.class);
        startActivity(intent);
    }

    @Override
    public void clickOnCheapThing(CheapThing cheapThing) {

    }

    // onCreate is one of six LifeCycle methods we can override.
    // onCreate gets called at a specific time:
    // when we first view the home page (when the app opens)
}