package com.example.evinochallenge

import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.evinochallenge.view.CategoryAdapter
import com.example.evinochallenge.view.MainActivity
import com.example.evinochallenge.view.SuggestionAdapter
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

    @Test
    fun checarFatoDoTextoDigitado() {
        checarAberturaDaSearchActivity()
        onView(withId(R.id.et_activity_search)).perform(typeText("movie"))
        onView(withId(R.id.et_activity_search)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER));
        Thread.sleep(2500);
        onView(withId(R.id.rv_resultados_activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_resultados_activity_main))
            .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact))));
    }

    @Test
    fun checarFatoDaCategoriaClicado() {
        checarAberturaDaSearchActivity()
        onView(ViewMatchers.withId(R.id.rv_categorias_activity_search))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CategoryAdapter.CategoryViewHolder>(
                    1, click()
                )
            )
        Thread.sleep(2000);
        onView(withId(R.id.rv_resultados_activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_resultados_activity_main))
            .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact))));
    }

    @Test
    fun checarNumeroDeCategorias() {
        checarAberturaDaSearchActivity()
        onView(withId(R.id.rv_categorias_activity_search)).check(matches(withSize(8)))
    }

    @Test
    fun checarFatoDaSugestaoClicada() {
        checarAberturaDaSearchActivity()
        onView(ViewMatchers.withId(R.id.rv_sugestoes_activity_search))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<SuggestionAdapter.SuggestionViewHolder>(
                    0, click()
                )
            )
        Thread.sleep(2000);
        onView(withId(R.id.rv_resultados_activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_resultados_activity_main))
            .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact))));
    }

    @Test
    fun checarTamanhoDoTexto() {
        checarFatoDaCategoriaClicado()
        if (checarTamnhoDoTexto()) {
            onView(withId(R.id.rv_resultados_activity_main))
                .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact).also {
                    matches(
                        withFontSize(20f)
                    )
                })))

        } else {
            onView(withId(R.id.rv_resultados_activity_main))
                .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact).also {
                    matches(
                        withFontSize(30f)
                    )
                })))
        }
    }

    private fun checarTamnhoDoTexto(): Boolean {
        try {
            onView(withId(R.id.rv_resultados_activity_main))
                .check(matches(hasDescendant(withId(R.id.tv_frase_item_chuck_fact).also {
                    matches(
                        withLength(80)
                    )
                })))
            return true
        } catch (e: NoMatchingViewException) {
            return false
        }
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

