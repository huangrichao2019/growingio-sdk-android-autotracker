import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gio.test.R;
import com.gio.test.three.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.
        espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @Classname MyEspressoTest
 * @Description TODO
 * @Date 2020/8/13 19:49
 * @Created by huangrichao
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MyEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickButton() {
        onView(withId(R.id.tab_autotrack)).perform(click());
    }
}
