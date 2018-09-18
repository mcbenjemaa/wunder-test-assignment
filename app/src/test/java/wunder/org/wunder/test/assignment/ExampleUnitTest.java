package wunder.org.wunder.test.assignment;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import wunder.org.wunder.test.assignment.model.Location;
import wunder.org.wunder.test.assignment.service.CarService;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {


    @Mock
    Context mMockContext;

    @Test
    public void check_JSON_Loaded_Correctly() {

        assertNotNull(mMockContext);

        Location location = CarService.getInstance(mMockContext).getLocations();

        assertNotNull(location);
    }


}