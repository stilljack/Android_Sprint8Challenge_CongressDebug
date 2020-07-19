package com.lambdaschool.congressdata

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnitRunner
import com.lambdaschool.congressdata.activities.DetailsActivity
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
import android.content.Intent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lambdaschool.congressdata.activities.MainActivity





@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ViewdetailsIDTests {
/*
    "id": "Z000018",
                "title": "Representative",
               "short_title": "Rep.",
                  "api_uri":"https://api.propublica.org/congress/v1/members/Z000018.json",
                   "first_name": "Ryan",
                "middle_name": null,
               "suffix": null,
*/



    //params :
    val id = "Z000018"
    val firstname="Ryan"
    val lastname="Zinke"
    val uri ="https://api.propublica.org/congress/v1/members/Z000018.json"


    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(DetailsActivity::class.java)
    @Rule
    var DetailsActivityTestRule: ActivityTestRule<DetailsActivity> =
        object : ActivityTestRule<DetailsActivity>(DetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val intent = Intent(InstrumentationRegistry.getInstrumentation().context, DetailsActivity::class.java)
                intent.putExtra("id", "Value")
                return intent
            }
        }





    @Test
    fun viewTest() {
        val correctDisplay =firstname

        Thread.sleep(3000)
    //    onView(withId(R.id.btn_test))
   //         .perform(click())

        onView(withId(R.id.profile_name))
            .check(matches(withSubstring(correctDisplay)))
    }
}

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
