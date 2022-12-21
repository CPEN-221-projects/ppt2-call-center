package cellphonerecords;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PublicT2A {

    private static final String user1, user2, user3, user4;

    private CallLogger log;

    static {
        user1 = "7782220000";
        user2 = "2364543211";
        user3 = "6040006767";
        user4 = "2362364545";
    }

    @BeforeAll
    public void setup() {
        log = new CallLogger();

        String[] from  = { user1, user2, user1, user1, user3 };
        String[] to    = { user2, user1, user3, user4, user2 };
        int[] start    = { 100, 300, 500, 720, 923 };
        int[] duration = { 67, 45, 10, 29, 127 };

        CallRecord[] crs = new CallRecord[from.length];

        for (int i = 0; i < from.length; i++) {
            crs[i] = new CallRecord(from[i], to[i], start[i], duration[i]);
            log.add(crs[i]);
        }
    }

    @Test
    public void testOverallAvgTalkTime() {
        assertEquals(55.6, log.getAvgCallDuration(), 0.001);
    }

    @Test
    public void testIndividualAvgTalkTime() {
        assertEquals(79.666, log.getAvgCallDuration(user2), 0.001);
    }

    @Test
    public void testFrequentUser() {
        assertEquals(user1, log.getMostFrequentUser());
    }

    @Test
    public void noUser() {
        CallLogger log = new CallLogger();
        String empty = log.getMostFrequentUser();
        System.out.println(empty);
    }
}