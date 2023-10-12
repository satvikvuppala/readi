package com.bnpp.appium.config.model.device;

public class IOSConnection extends Connection {
    public static final String AUTOMATION_NAME = "automationName";
    public static final String XCODEORG_ID = "xcodeOrgId";
    public static final String XCODESIGNING_ID = "xcodeSigningId";
    public static final String IGNORE_UNIMPORTANT_VIEWS = "ignoreUnimportantViews";
    public static final String INCLUDE_NON_MODEL_ELEMENTS = "includeNonModalElements";
    public static final String USE_FIRST_MATCH = "useFirstMatch";
    public static final String SNAPSHOT_TIMEOUT = "snapshotTimeout";

    private String xcodeOrgId;
    private String xcodeSigningId;
    private String ignoreUnimportantViews;
    private String includeNonModalElements;
    private String useFirstMatch;
    private String snapshotTimeout;


    public String getIgnoreUnimportantViews() {
        return ignoreUnimportantViews;
    }

    public String getIncludeNonModalElements() {
        return includeNonModalElements;
    }

    public String getUseFirstMatch() {
        return useFirstMatch;
    }

    public String getSnapshotTimeout() {
        return snapshotTimeout;
    }


    public void setIgnoreUnimportantViews(String ignoreUnimportantViews) {
        this.ignoreUnimportantViews = ignoreUnimportantViews;
    }

    public void setIncludeNonModalElements(String includeNonModalElements) {
        this.includeNonModalElements = includeNonModalElements;
    }

    public void setUseFirstMatch(String useFirstMatch) {
        this.useFirstMatch = useFirstMatch;
    }

    public void setSnapshotTimeout(String snapshotTimeout) {
        this.snapshotTimeout = snapshotTimeout;
    }

    public String getXcodeOrgId() {
        return xcodeOrgId;
    }

    public void setXcodeOrgId(String xcodeOrgId) {
        this.xcodeOrgId = xcodeOrgId;
    }

    public String getXcodeSigningId() {
        return xcodeSigningId;
    }

    public void setXcodeSigningId(String xcodeSigningId) {
        this.xcodeSigningId = xcodeSigningId;
    }

    @Override
    public String toString() {
        return "IOSConnection{" +
                ", xcodeOrgId='" + xcodeOrgId + '\'' +
                ", xcodeSigningId='" + xcodeSigningId + '\'' +
                ", ignoreUnimportantViews='" + ignoreUnimportantViews + '\'' +
                ", includeNonModalElements='" + includeNonModalElements + '\'' +
                ", useFirstMatch='" + useFirstMatch + '\'' +
                ", snapshotTimeout='" + snapshotTimeout + '\'' +
                "} " + super.toString();
    }
}
