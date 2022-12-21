package cellphonerecords;

import java.util.*;

public class CallLogger {
    Set<CallRecord> callRecords;

    public CallLogger() {
        this.callRecords = new HashSet<>();
    }

    /**
     * Add a call record to the log
     *
     * @param cr the record to add, which is a valid CallRecord
     *           (from and to phone numbers are valid, and so are
     *           the start time and call duration)
     * @return true if the record was added when it did
     * not exist in the log earlier and false if the log
     * contained the record already
     */
    public boolean add(CallRecord cr) {
        if (!callRecords.contains(cr)) {
            callRecords.add(cr);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return all the call records that contain the given phone number
     * as the originating phone number
     *
     * @param phoneNumber is not null and is not empty
     * @return all the call records that contain the given phone number
     * as the originating phone number, and an empty array if phoneNumber is
     * not in this logger
     */
    public CallRecord[] getOriginatingCalls(String phoneNumber) {
        List<CallRecord> recordList = new ArrayList<>();

        for (CallRecord call: callRecords) {
            if (call.fromPhoneNumber.equals(phoneNumber)) {
                recordList.add(call);
            }
        }

        CallRecord[] recordArray = new CallRecord[recordList.size()];
        recordArray = recordList.toArray(recordArray);

        return recordArray;
    }

    /**
     * Return all the call records that contain the given phone number
     * as the destination phone number
     *
     * @param phoneNumber is not null and is not empty
     * @return all the call records that contain the given phone number
     * as the destination phone number, and an empty array if phoneNumber is
     * not in this logger
     */
    public CallRecord[] getReceivedCalls(String phoneNumber) {
        List<CallRecord> recordList = new ArrayList<>();

        for (CallRecord call: callRecords) {
            if (call.toPhoneNumber.equals(phoneNumber)) {
                recordList.add(call);
            }
        }

        CallRecord[] recordArray = new CallRecord[recordList.size()];
        recordArray = recordList.toArray(recordArray);

        return recordArray;
    }

    /**
     * Obtain the user (phone number) that was a participant
     * in the most number of calls.
     *
     * @return the user (phone number) that was a participant
     * in the most number of calls, breaking ties arbitrarily,
     * and returning the empty string if there are no records
     * in this logger
     */
    public String getMostFrequentUser() {
        Map<String, Integer> numberToCalls = new HashMap<>();
        String mostFrequentNumber = "";
        int mostCalls = 0;
        for (CallRecord call: callRecords) {
            int currentCalls = numberToCalls.getOrDefault(call.fromPhoneNumber, 0);
            numberToCalls.put(call.fromPhoneNumber, ++currentCalls);

            if (currentCalls > mostCalls) {
                mostCalls = currentCalls;
                mostFrequentNumber = call.fromPhoneNumber;
            }

            int toCalls = numberToCalls.getOrDefault(call.toPhoneNumber, 0);
            numberToCalls.put(call.toPhoneNumber, ++toCalls);

            if (toCalls > mostCalls) {
                mostCalls = toCalls;
                mostFrequentNumber = call.toPhoneNumber;
            }
        }

        return mostFrequentNumber;
    }

    /**
     * Obtain the average call duration for all calls in the log.
     *
     * @return the average call duration for all calls in the log,
     * and if there are no calls in this logger then the average
     * call duration is 0
     */
    public double getAvgCallDuration() {
        int numCalls = callRecords.size();
        double avgDuration = 0;
        for (CallRecord call: callRecords) {
            avgDuration += (double) call.duration / numCalls;
        }
        return avgDuration;
    }

    /**
     * Obtain the average call duration for all calls that the
     * given user was a participant in.
     *
     * @return the average call duration for all calls that the
     * given user was a participant in, and if there are no calls in this
     * logger involving the given participant then the average call
     * duration is 0
     */
    public double getAvgCallDuration(String phoneNumber) {
        int numUserCalls = 0;
        double durationSum = 0;
        for (CallRecord call: callRecords) {
            if (call.fromPhoneNumber.equals(phoneNumber) || call.toPhoneNumber.equals(phoneNumber)) {
                durationSum += call.duration;
                numUserCalls++;
            }
        }

        double avgDuration = durationSum / numUserCalls;

        return avgDuration;
    }

    /**
     * Obtain the peak usage over time
     *
     * The usage of the phone network is defined as the number of
     * active calls in 10-minute windows starting from 0-time:
     * 0th-9th minute, 10th-19th minute, 20th-29th minute, ...
     * The peak usage is the usage in the 10-minute window that
     * had the maximum number of active calls.
     *
     * @return the peak usage over time
     */
    public int getPeakUsage() {
        Map<Integer, Integer> minuteMap = new HashMap<>();
        int peakUsage = 0;

        for (CallRecord call: callRecords) {
            int lowerLimitWindow = getWindow(call.startTime);
            int upperLimitWindow = getWindow(call.startTime + call.duration - 1);

            for (int i = lowerLimitWindow; i <= upperLimitWindow; i += 10) {
                int currentActiveCalls = minuteMap.getOrDefault(i, 0);
                minuteMap.put(i, ++currentActiveCalls);
                if (currentActiveCalls > peakUsage) {
                    peakUsage = currentActiveCalls;
                }
            }
        }

        return peakUsage;
    }

    private int getWindow(int time) {
        return (time / 600) * 10;
    }

    /**
     * Verify if the provided call record overlaps in time with
     * another call that involves the same participants:
     * true if either the originator or the receiver was on a call
     * that is already in the log and overlaps with the provided call,
     * and false otherwise.
     *
     * @param cr the provided call record, is not null
     * @return true if either the originator or the receiver was on a call
     * that is already in the log and overlaps with the provided call,
     * and false otherwise
     */
    public boolean isOverlappingCall(CallRecord cr) {
        int callStartTime = cr.startTime;
        int callEndTime = callStartTime + cr.duration - 1;

        String caller1 = cr.fromPhoneNumber;
        String caller2 = cr.toPhoneNumber;

        for (CallRecord call: callRecords) {
            if (call.fromPhoneNumber.equals(caller1) || call.toPhoneNumber.equals(caller1) ||
                    call.fromPhoneNumber.equals(caller2) || call.toPhoneNumber.equals(caller2)) {
                System.out.print("Call: ");
                System.out.print(call.startTime + ", ");
                System.out.print(call.startTime + call.duration - 1);
                System.out.println();
//                if ((call.startTime >= callStartTime && call.startTime < callEndTime) ||
//                        (call.startTime + call.duration - 1 <= callEndTime && call.startTime + call.duration - 1 > callStartTime)) {
//                    return true;
//                }
                if ((callStartTime >= call.startTime && callStartTime < call.startTime + call.duration - 1) ||
                        (callEndTime > call.startTime && callEndTime <= call.startTime + call.duration - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

}
