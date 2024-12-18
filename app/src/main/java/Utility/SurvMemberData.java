package Utility;

public class SurvMemberData {
    private String evType;
    private String round;
    private String memID;

    public SurvMemberData(String evType, String round, String memID) {
        this.evType = evType;
        this.round = round;
        this.memID = memID;
    }

    public String getEvType() {
        return evType;
    }

    public String getRound() {
        return round;
    }

    public String getMemID() {
        return memID;
    }
}
