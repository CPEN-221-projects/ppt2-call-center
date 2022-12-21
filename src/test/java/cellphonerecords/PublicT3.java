package cellphonerecords;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PublicT3 {

    private CallLogger log;

    private String[] from  = { "2178989899", "6048270905", "2375431266", "7326593244", "5307658901", "2365554444", "2365554444", "7326593244", "2375431266", "2365554444", "7782223333", "2178989899", "9742906784", "6048270905", "6048270905", "5307658901", "2375431266", "2375431266", "6045678901", "2365554444", "7326593244", "2375431266", "7782223333", "6048270905", "7782223333", "6048248432", "6048248432", "2375431266", "2178989899", "2365554444", "2178989899", "5307658901", "2365554444", "7326593244", "7326593244", "6048270905", "6048248432", "6045678901", "2178989899", "6048270905", "9742906784", "6045678901", "6048270905", "2365554444", "2365554444", "2365554444", "6048270905", "2365554444", "7326593244", "6048270905" };

    private String[] to    = { "5307658901", "7782223333", "9742906784", "2178989899", "9742906784", "7326593244", "9742906784", "9742906784", "6048248432", "9742906784", "6048248432", "2375431266", "2178989899", "2375431266", "2178989899", "7782223333", "7782223333", "6045678901", "6048248432", "7782223333", "6045678901", "9742906784", "2178989899", "5307658901", "2365554444", "2375431266", "2375431266", "6045678901", "7782223333", "2178989899", "9742906784", "7782223333", "5307658901", "6048248432", "2375431266", "7326593244", "7782223333", "5307658901", "6045678901", "2178989899", "7782223333", "6048270905", "7782223333", "5307658901", "2375431266", "2375431266", "7326593244", "7782223333", "2365554444", "2365554444" };

    private int[] startTime = { 348633, 384201, 248733, 335795, 555543, 376457, 338383, 294286, 449695, 460685, 423605, 288246, 436851, 425957, 134433, 397537, 112531, 350490, 311413, 165437, 9845, 212992, 540195, 442994, 596187, 281883, 340799, 65848, 431989, 171260, 528865, 286770, 69118, 287267, 302085, 97712, 41896, 519143, 175123, 1385, 44875, 375719, 76563, 153752, 3070, 368280, 327678, 118921, 205867, 112021 };

    private int[] duration = { 1421, 991, 1089, 553, 108, 160, 415, 1591, 783, 3172, 1586, 1022, 1596, 2336, 3126, 3177, 2787, 2418, 2339, 2985, 2028, 1977, 1159, 932, 302, 3214, 2047, 1165, 2344, 391, 921, 1038, 662, 3025, 1982, 2356, 2619, 423, 769, 1348, 3169, 144, 356, 1481, 364, 647, 2199, 2892, 2173, 3030 };

    @BeforeAll
    public void setup() {
        log = new CallLogger();

        for (int i = 0; i < from.length; i++) {
            log.add(new CallRecord(from[i], to[i], startTime[i], duration[i]));
        }
    }

    @Test
    public void testOverlap1() {
        assertTrue(log.isOverlappingCall(new CallRecord(from[0], to[0], 348833, 100)));
    }

    @Test
    public void testOverlap2() {
        assertTrue(log.isOverlappingCall(new CallRecord(from[3], to[3], 348833, 100)));
    }


    @Test
    public void testNoOverlap1() {
        assertFalse(log.isOverlappingCall(new CallRecord(from[2], to[2], 1276, 100)));
    }

    @Test
    public void testNoOverlap2() {
        assertFalse(log.isOverlappingCall(new CallRecord(from[5], to[5], 348833, 101)));
    }

}