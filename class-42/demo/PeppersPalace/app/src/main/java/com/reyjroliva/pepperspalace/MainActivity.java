package com.reyjroliva.pepperspalace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity {
  private static String TAG = "MainActivity";

  private AdRequest bannerAdRequest;
  private AdRequest interstitialAdRequest;
  private AdRequest rewardedAdRequest;
  private InterstitialAd mInterstitialAd;
  private RewardedAd rewardedAd;

  private AdView bannerAdView;
  private Button interstitialAdButton;
  private Button rewardAdButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bannerAdView = findViewById(R.id.bannerAdView);
    interstitialAdButton = findViewById(R.id.MainActivityInterstitialAdButton);
    rewardAdButton = findViewById(R.id.MainActivityRewardAdButton);

    initializeMobileAds();
    setupBannerAd();
    setupInterstitialAd();
    setupInterstitialAdButton();
    setupRewardedAd();
    setupRewardedAdButton();
  }

  private void initializeMobileAds() {
    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });
  }

  private void setupBannerAd() {
    bannerAdRequest = new AdRequest.Builder().build();
    bannerAdView.loadAd(bannerAdRequest);
  }

  private void setupInterstitialAd() {
    interstitialAdRequest = new AdRequest.Builder().build();

    InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", interstitialAdRequest,
      new InterstitialAdLoadCallback() {
        @Override
        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
          // The mInterstitialAd reference will be null until
          // an ad is loaded.
          mInterstitialAd = interstitialAd;
          Log.i(TAG, "interstitial ad loaded");

          // on successful ad load, define the full screen callback
          // define a callback for the ad, if we have one
          mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdClicked() {
              // Called when a click is recorded for an ad.
              Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
              // Called when ad is dismissed.
              // Set the ad reference to null so you don't show the ad a second time.
              Log.d(TAG, "Ad dismissed fullscreen content.");
              mInterstitialAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
              // Called when ad fails to show.
              Log.e(TAG, "Ad failed to show fullscreen content.");
              mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
              // Called when an impression is recorded for an ad.
              Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
              // Called when ad is shown.
              Log.d(TAG, "Ad showed fullscreen content.");
            }
          });
        }

        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
          // Handle the error
          Log.d(TAG, loadAdError.toString());
          mInterstitialAd = null;
        }
      });
  }

  private void setupInterstitialAdButton() {
    interstitialAdButton.setOnClickListener(v -> {
      if (mInterstitialAd != null) {
        mInterstitialAd.show(MainActivity.this);
      } else {
        Log.d("TAG", "The interstitial ad wasn't ready yet.");
      }
    });
  }

  private void setupRewardedAd() {
    rewardedAdRequest = new AdRequest.Builder().build();

    RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
      rewardedAdRequest, new RewardedAdLoadCallback() {
        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
          // Handle the error.
          Log.d(TAG, loadAdError.toString());
          rewardedAd = null;
        }

        @Override
        public void onAdLoaded(@NonNull RewardedAd ad) {
          rewardedAd = ad;
          Log.d(TAG, "Rewarded ad was loaded.");

          // On successful ad load, define the full screen callback
          rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
              // Called when a click is recorded for an ad.
              Log.d(TAG, "Rewarded ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
              // Called when ad is dismissed.
              // Set the ad reference to null so you don't show the ad a second time.
              Log.d(TAG, "Rewarded ad dismissed fullscreen content.");
              rewardedAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
              // Called when ad fails to show.
              Log.e(TAG, "Rewarded ad failed to show fullscreen content.");
              rewardedAd = null;
            }

            @Override
            public void onAdImpression() {
              // Called when an impression is recorded for an ad.
              Log.d(TAG, "Rewarded ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
              // Called when ad is shown.
              Log.d(TAG, "Rewarded ad showed fullscreen content.");
            }
          });
        }
      });
  }

  private void setupRewardedAdButton() {
    rewardAdButton.setOnClickListener(v -> {
      if (rewardedAd != null) {
        Activity activityContext = MainActivity.this;
        rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
          @Override
          public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            // Handle the reward.
            int rewardAmount = rewardItem.getAmount();
            String rewardType = rewardItem.getType();
            Log.d(TAG, "The user earned the reward. Reward Amount: " + rewardAmount + " Reward Type: " + rewardType);
          }
        });
      } else {
        Log.d(TAG, "The rewarded ad wasn't ready yet.");
      }
    });
  }
}
