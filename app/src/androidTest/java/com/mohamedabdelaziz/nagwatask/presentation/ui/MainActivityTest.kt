package com.mohamedabdelaziz.nagwatask.presentation.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import com.mohamedabdelaziz.nagwatask.R
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun isMainActivityInView() {
       ActivityScenario.launch(MainActivity::class.java)
       onView(ViewMatchers.withId(R.id.mainActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun contentRecyclerView() {
        onView(ViewMatchers.withId(R.id.contentRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun contentSwipeRefresh() {
        onView(ViewMatchers.withId(R.id.contentSwipeRefresh)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun retryButton() {
        onView(ViewMatchers.withId(R.id.retry_btn)).perform(click())
    }

    @Test
    fun shimmerLayout() {
        onView(ViewMatchers.withId(R.id.shimmerLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}