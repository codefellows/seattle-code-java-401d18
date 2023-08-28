package com.reyjroliva.buystuff;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class BuyStuffAmplifyApplication extends Application {
  public static final String TAG = "buystuffapplication";

  @Override
  public void onCreate() {
    super.onCreate();

    try {
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.addPlugin(new AWSCognitoAuthPlugin());
      Amplify.configure(getApplicationContext());
    } catch(AmplifyException ae) {
      Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
    }
  }
}

// Steps for adding Amplify to your app
// 1. Remove Room from your app
//   1A. Delete the Gradle Room dependencies in app's (lower-level) build.gradle
//   1B. Delete database class
//   1C. Delete DAO class
//   1D. Remove `@Entity` and `@PrimaryKey` annotations from the Product model class
//   1E: Delete the database variables and instantiation from each Activity that uses them
//   1F: Comment out DAO usages in each Activity that uses them
// 2. Make an IAM user (and access keys!)
// 3. Run `amplify configure`
// 4. Add Amplify Gradle dependencies in build.gradle files
// 5. Run `amplify init`
// 6. Run `amplify add api` (or `amplify api update`)
// 7. Run `amplify push`
// 8. Change model in "amplify/backend/api/amplifyDatasource/schema.graphql" to match your app's model
// 9. Run `amplify api update` -> Disable conflict resolution
// 10. Run `amplify push --allow-destructive-graphql-schema-updates`
// 11. Run `amplify codegen models`
// 12A. Add an application class that extends Application and configures Amplify
// 12B. Put the application class name in your AndroidManifest.xml
// 12C. Uninstall the app on your emulator
// 13. Convert every usage of model classes to use Amplify generated models in app/src/main/java/com/amplifyframework/datastore/generated/model
//   13A. Instantiate classes using builder
//   13B. Get data elements via getters (if you aren't already)
// 14. Convert all DAO usages to Amplify.API calls
// 15. Update RecyclerView adapter's collection via runOnUiThread()
// 16. Fix date output in RecyclerView items
