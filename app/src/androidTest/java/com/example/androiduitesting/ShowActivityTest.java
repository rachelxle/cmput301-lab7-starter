package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Adds a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Opens ShowActivity activity upon clicking
        onView(withText("Edmonton")).perform(click());

        // Verifies the activity has switched
        onView(withId(R.id.text_cityName)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameDisplayedCorrectly() {
        // Adds a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Tokyo"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Opens the ShowActivity activity
        onView(withText("Tokyo")).perform(click());

        // Verifies the correct name is shown
        onView(withId(R.id.text_cityName)).check(matches(withText("Tokyo")));
    }

    @Test
    public void testBackButton() {
        // Adds a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Berlin"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Opens the ShowActivity activity
        onView(withText("Berlin")).perform(click());

        // Presses the back button
        onView(withId(R.id.button_back)).perform(click());

        // Verifies it returns to the naim activity
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}