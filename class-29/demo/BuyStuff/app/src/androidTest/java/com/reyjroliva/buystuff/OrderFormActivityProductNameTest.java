package com.reyjroliva.buystuff;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OrderFormActivityProductNameTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
    new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void orderFormActivityProductNameTest() {
    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.MainActivityProductNameInputTextView),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          1),
        isDisplayed()));
    appCompatEditText.perform(click());

    ViewInteraction appCompatEditText2 = onView(
      allOf(withId(R.id.MainActivityProductNameInputTextView),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          1),
        isDisplayed()));
    appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());

    ViewInteraction appCompatEditText3 = onView(
      allOf(withId(R.id.MainActivityProductNameInputTextView), withText("test"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          1),
        isDisplayed()));
    appCompatEditText3.perform(pressImeActionButton());

    ViewInteraction materialButton = onView(
      allOf(withId(R.id.MainActivityMoveToOrderFormButton), withText("Order Form"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          3),
        isDisplayed()));
    materialButton.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(700);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    ViewInteraction textView = onView(
      allOf(withId(R.id.OrderFormProductNameTextView), withText("test"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView.check(matches(withText("test")));

    ViewInteraction textView2 = onView(
      allOf(withId(R.id.OrderFormActivity), withText("Order Form"),
        withParent(withParent(withId(android.R.id.content))),
        isDisplayed()));
    textView2.check(matches(withText("Order Form")));
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
