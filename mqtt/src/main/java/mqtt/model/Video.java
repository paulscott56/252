package mqtt.model;

/**
 * Created by paul on 2016/04/26.
 */
public class Video {
    private String stereomode;
    private int width;
    private String codec;
    private double aspect;
    private int duration;
    private int height;

    public String getStereomode() {
        return stereomode;
    }

    public void setStereomode(String stereomode) {
        this.stereomode = stereomode;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public double getAspect() {
        return aspect;
    }

    public void setAspect(double aspect) {
        this.aspect = aspect;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
