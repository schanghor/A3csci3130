package com.acme.a3csci3130;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
/**
 * jUnit testing
 * @author Sitanun Changhor
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    /**
     * Creates 2 businesses
     */
    @Test
    public void create(){
        SystemClock.sleep(1000);
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.number)).perform(typeText("123999321"));
        onView(withId(R.id.name)).perform(typeText("Dream Inc"));
        onView(withId(R.id.primaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Distributor"))).perform(click());
        onView(withId(R.id.address)).perform(typeText("123 That Street"));
        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.createButton)).perform(click());

        SystemClock.sleep(1000);
        onView(withId(R.id.createButton)).perform(click());
        onView(withId(R.id.number)).perform(typeText("333555444"));
        onView(withId(R.id.name)).perform(typeText("Nightmare Inc"));
        onView(withId(R.id.primaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fish Monger"))).perform(click());
        onView(withId(R.id.address)).perform(typeText("321 This Street"));
        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("BC"))).perform(click());
        onView(withId(R.id.createButton)).perform(click());
    }

    /**
     * Clicks on the first item in the list view
     */
    @Test
    public void read(){
        SystemClock.sleep(1500);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
    }

    /**
     * Clicks on the first item in the list view and updates it on Firebase
     */
    @Test
    public void update(){
        SystemClock.sleep(2000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.primaryBusiness)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Processor"))).perform(click());
        onView(withId(R.id.updateButton)).perform(click());
    }

    /**
     * Clicks on the first item in the list view and deletes it from Firebase
     */
    @Test
    public void delete(){
        SystemClock.sleep(2000);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }
}
