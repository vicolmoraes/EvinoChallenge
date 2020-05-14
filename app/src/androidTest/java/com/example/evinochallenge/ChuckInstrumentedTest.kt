package com.example.evinochallenge

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.evinochallenge.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test


class ChuckInstrumentedTest {
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun checarAberturaDaSearchActivity() {
        onView(withId(R.id.item_pesquisar)).perform(click())
        onView(withId(R.id.cl_activity_search)).check(matches(isDisplayed()))
        Thread.sleep(2000);
    }

    private fun withFontSize(expectedSize: Float): Matcher<View> {
        return object :
            BoundedMatcher<View, View>(View::class.java) {
            override fun matchesSafely(target: View): Boolean {
                if (target !is TextView) {
                    return false
                }
                val pixels = target.textSize
                val actualSize =
                    pixels / target.getResources().displayMetrics.scaledDensity
                return java.lang.Float.compare(actualSize, expectedSize) == 0
            }

            override fun describeTo(description: Description) {
                description.appendText("with fontSize: ")
                description.appendValue(expectedSize)
            }
        }
    }

    private fun withLength(caracteres: Int): TypeSafeMatcher<View?>? {
        return object : TypeSafeMatcher<View?>() {

            override fun describeTo(description: Description) {
                description.appendText("with Length")
            }

            override fun matchesSafely(item: View?): Boolean {
                return (item as TextView).text.length > caracteres
            }
        }
    }

    private fun withSize(itens: Int): TypeSafeMatcher<View?>? {
        return object : TypeSafeMatcher<View?>() {

            override fun describeTo(description: Description) {
                description.appendText("withSize")
            }

            override fun matchesSafely(item: View?): Boolean {
                return (item as RecyclerView).childCount == itens
            }
        }
    }
}

