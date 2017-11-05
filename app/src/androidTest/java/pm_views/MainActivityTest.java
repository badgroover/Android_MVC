package pm_views;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fab2), isDisplayed()));
        floatingActionButton2.perform(click());


        ViewInteraction textView = onView(
                allOf(withText("Second Fragment"),
                        childAtPosition(
                                allOf(withId(R.id.content_main),
                                        childAtPosition(
                                                withId(R.id.fragmentContainer),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Second Fragment")));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab1), isDisplayed()));
        floatingActionButton.perform(click());
        try {
            Thread.sleep(10000);
            textView = onView(
                    allOf(withId(R.id.name), withText("Nikhil Sohoni"),
                            childAtPosition(
                                    allOf(withId(R.id.content_main),
                                            childAtPosition(
                                                    withId(R.id.fragmentContainer),
                                                    0)),
                                    1),
                            isDisplayed()));
            textView.check(matches(withText("Nikhil Sohoni")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        pressBack();
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

    public class WaitThread implements Runnable {

        long waitTime;
        public WaitThread(long milli) {
            waitTime = milli;
        }
        @Override
        public void run() {
            try {
                wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
