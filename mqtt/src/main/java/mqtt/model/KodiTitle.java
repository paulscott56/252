package mqtt.model;

/**
 * Created by paul on 2016/04/26.
 */
public class KodiTitle {
    private String title;
    private String fanart;
    private String label;
    private String file;
    private String type;
    private String thumbnail;
    private StreamDetails streamdetails;
    private String val;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFanart() {
        return fanart;
    }

    public void setFanart(String fanart) {
        this.fanart = fanart;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public StreamDetails getStreamdetails() {
        return streamdetails;
    }

    public void setStreamdetails(StreamDetails streamdetails) {
        this.streamdetails = streamdetails;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
