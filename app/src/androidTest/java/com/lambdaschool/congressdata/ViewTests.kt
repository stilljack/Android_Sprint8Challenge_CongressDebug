package com.lambdaschool.congressdata

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.lambdaschool.congressdata.activities.MainActivity
import com.lambdaschool.congressdata.viewmodel.OverviewListAdapter.Companion.dataList
import okhttp3.internal.wait

import org.junit.FixMethodOrder
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters
import java.util.regex.Matcher


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ViewTests {


    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun viewTest() {
        val correctDisplay ="test"

        Thread.sleep(10000)
        onView(withId(R.id.btn_test))
            .perform(click())

        onView(withId(R.id.tv_test))
            .check(matches(withText(correctDisplay)))
    }
}

/*
public class WaitAction(var condition: org.hamcrest.Matcher<View>, var timeout:Long) : ViewAction {

    */
/** The amount of time to allow the main thread to loop between checks. *//*



    override fun getDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConstraints(): org.hamcrest.Matcher<View> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun perform(uiController: UiController?, view: View?) {
        uiController.loopMainThreadUntilIdle();
        var startTime = System.currentTimeMillis();
        var endTime:Long = startTime + timeout

        while (System.currentTimeMillis() < endTime) {
            if (condition.matches(view)) {
                return;
            }

            controller.loopMainThreadForAtLeast(100);
        }

        // Timeout.
        throw new PerformException();

    }

    @Override
    public Matcher<View> getConstraints() {
        return (Matcher) anything();
    }

    @Override
    public String getDescription() {
        return "wait";
    }

    @Override
    public void perform(UiController controller, View view) {
        }

    public fun ViewAction waitFor(Matcher<View> condition, long timeout) {
        return WaitAction(condition, timeout);
    }
}
You can use this as following*/
