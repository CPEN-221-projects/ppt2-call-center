package cellphonerecords;

public class CallRecord {
    public final String fromPhoneNumber;
    public final String toPhoneNumber;
    public final int startTime;
    public final int duration;

    /**
     * Create a new CallRecord
     *
     * @param from the originating phone number, is not null and is not empty
     * @param to the destination phone number, is not null, is not empty and to != from
     * @param startTime the start time of the call,
     *                  in seconds elapsed since January 1, 2020, 1200 hrs,
     *                  is greater than or equal to 0
     * @param duration the duration of the call,
     *                 is greater than 0
     */
    public CallRecord(String from, String to, int startTime, int duration) {
        this.fromPhoneNumber = from;
        this.toPhoneNumber = to;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CallRecord)) {
            return false;
        }
        CallRecord that = (CallRecord) other;

        return (
                this.fromPhoneNumber == that.fromPhoneNumber
                && this.toPhoneNumber == that.toPhoneNumber
                && this.startTime == that.startTime
                && this.duration == that.duration
        );
    }

    @Override
    public int hashCode() {
        return this.fromPhoneNumber.hashCode() + this.toPhoneNumber.hashCode();
    }
}
