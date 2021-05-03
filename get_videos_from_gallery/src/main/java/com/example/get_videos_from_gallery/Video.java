package com.example.get_videos_from_gallery;

public class Video {
    // Url của video
    private String url;
    // Thumbnail của video
    private String thumb;

    public Video(String url, String thumb) {
        this.url = url;
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
