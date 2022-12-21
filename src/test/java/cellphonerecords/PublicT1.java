package cellphonerecords;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicT1 {

    private static final String user1, user2, user3, user4;

    static {
        user1 = "7782220000";
        user2 = "2364543211";
        user3 = "6040006767";
        user4 = "2362364545";
    }

    @Test
    public void testAddAndGetCalls1() {
        CallLogger log = new CallLogger();

        CallRecord cr1 = new CallRecord(user1, user2, 100, 67);
        CallRecord cr2 = new CallRecord(user2, user1, 300, 45);

        log.add(cr1);
        log.add(cr2);

        CallRecord[] user1OutgoingCalls = log.getOriginatingCalls(user1);
        CallRecord[] user1incomingCalls = log.getReceivedCalls(user1);

        assertArrayEquals(new CallRecord[] {cr1}, user1OutgoingCalls);
        assertArrayEquals(new CallRecord[] {cr2}, user1incomingCalls);
    }

    @Test
    public void testAddAndGetCalls2() {
        CallLogger log = new CallLogger();

        String[] from  = { user1, user2, user1, user1, user3 };
        String[] to    = { user2, user1, user3, user4, user2 };
        int[] start    = { 100, 300, 500, 720, 923 };
        int[] duration = { 67, 45, 10, 29, 127 };

        CallRecord[] crs = new CallRecord[from.length];

        for (int i = 0; i < from.length; i++) {
            crs[i] = new CallRecord(from[i], to[i], start[i], duration[i]);
            log.add(crs[i]);
        }

        CallRecord[] user1OutgoingCalls = log.getOriginatingCalls(user1);
        CallRecord[] user2incomingCalls = log.getReceivedCalls(user2);

        Set<CallRecord> expected1 = Set.of(crs[0], crs[2], crs[3]);
        assertEquals(expected1.size(), user1OutgoingCalls.length);
        assertEquals(expected1, Set.of(user1OutgoingCalls));

        Set<CallRecord> expected2 = Set.of(crs[0], crs[4]);
        assertEquals(expected2.size(), user2incomingCalls.length);
        assertEquals(expected2, Set.of(user2incomingCalls));
    }

    @Test
    public void testNoCall() {
        CallLogger log = new CallLogger();

        String[] from  = { user1, user2, user1, user1, user3 };
        String[] to    = { user2, user1, user3, user4, user2 };
        int[] start    = { 100, 300, 500, 720, 923 };
        int[] duration = { 67, 45, 10, 29, 127 };

        CallRecord[] crs = new CallRecord[from.length];

        for (int i = 0; i < from.length; i++) {
            crs[i] = new CallRecord(from[i], to[i], start[i], duration[i]);
            log.add(crs[i]);
        }

        CallRecord[] user1OutgoingCalls = log.getReceivedCalls("6767");
        System.out.println(user1OutgoingCalls);
    }
}
