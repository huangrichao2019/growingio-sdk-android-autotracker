import android.os.Build;

import com.gio.test.three.MainActivity;
import com.gio.test.three.ThreeVersionApplication;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.LooperMode;
import org.robolectric.shadows.ShadowLooper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Classname ExampleTest
 * @Description TODO
 * @Date 2020-01-14 17:14
 * @Created by huangrichao
 */
@RunWith(RobolectricTestRunner.class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(application = ThreeVersionApplication.class ,sdk = Build.VERSION_CODES.P)
public class ExampleTest {

    private ShadowLooper mShadowLooper;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    public static String ASSERT_MESSAGE = "{\"splashs\":[],\"popupWindows\":[],\"banners\":[],\"idMappings\":{\"bu\":0,\"bcs\":0}}";

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testActivity(){

        MainActivity loginActivity = Robolectric.setupActivity(MainActivity.class);
//        shadowOf(Looper.getMainLooper()).idle();
        Assert.assertNotNull(loginActivity);
    }
    //一个很关键的地方，网络请求一定要同步，不要异步，异步会没有结果的
    @Test
    public void testApi() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://messages.growingio.com/v1/0a1b4118dd954ec3bcc69da5138bdb96/notifications?url_scheme=growing.638b527108671871c&u=GtouchDevice&cs=lisi").build();
        Call call = okHttpClient.newCall(request);
        try {
            String result = call.execute().body().string();
            Assert.assertEquals(ASSERT_MESSAGE,result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// final class, final method 不受Mockito待见
//    @Test
//    public void testNonFinalClassWithFinalMethod() {
//        NonFinalClassWithFinalMethod testMe = Mockito.mock(NonFinalClassWithFinalMethod.class);
//        when(testMe.finalMethod()).thenReturn("hello");
//        assertThat(testMe.finalMethod(), CoreMatchers.equalTo("hello"));
//    }




}

