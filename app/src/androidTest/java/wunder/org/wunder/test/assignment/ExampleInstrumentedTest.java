package wunder.org.wunder.test.assignment;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import wunder.org.wunder.test.assignment.model.Location;
import wunder.org.wunder.test.assignment.service.CarService;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("wunder.org.wunder.test.assignment", appContext.getPackageName());


    }


    @Test
    public void check_JSON_Loaded_Correctly() {

        Context appContext = InstrumentationRegistry.getTargetContext();

        Location location = CarService.getInstance(appContext).getLocations();

        assertNotNull(location);
    }

}
