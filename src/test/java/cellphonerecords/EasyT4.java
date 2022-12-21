package cellphonerecords;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EasyT4 {

    private CallLogger log;

    private String[] from  = { "1", "2", "3", "4"};
    private String[] to    = { "4", "2", "5", "3"};
    private int[] startTime = {13, 13, 13, 13};
    private int[] duration = {6, 34, 20, 51};

    @BeforeAll
    public void setup() {
        log = new CallLogger();

        for (int i = 0; i < from.length; i++) {
            log.add(new CallRecord(from[i], to[i], startTime[i], duration[i]));
        }
    }

    @Test
    public void testPeakUsage1() {
        assertEquals(4, log.getPeakUsage());
    }

    @Test
    public void testPeakUsage2() {
        assertEquals(0, (new CallLogger()).getPeakUsage());
    }


}