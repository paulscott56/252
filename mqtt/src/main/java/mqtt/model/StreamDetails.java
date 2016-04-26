package mqtt.model;

import java.util.List;

/**
 * Created by paul on 2016/04/26.
 */
public class StreamDetails {
    private List<Video> video;
    private List<Audio> audio;
    private Subtitle subtitle;

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

    public List<Audio> getAudio() {
        return audio;
    }

    public void setAudio(List<Audio> audio) {
        this.audio = audio;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }
}
