package com.example.historyapp_st10443445

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testValidInput() {
        val validYear = "1990"

        onView(withId(R.id.edtYear))
            .perform(typeText(validYear), closeSoftKeyboard())

        onView(withId(R.id.btnResults))
            .perform(click())

        onView(withId(R.id.txtResults))
            .check(matches(withText("In $validYear: ")))
    }

    @Test
    fun testInvalidInput() {
        val invalidYear = "abcd"

        onView(withId(R.id.edtYear))
            .perform(typeText(invalidYear), closeSoftKeyboard())

        onView(withId(R.id.btnResults))
            .perform(click())

        onView(withId(R.id.txtResults))
            .check(matches(withText("No event has been found from input of your age.")))
    }
}