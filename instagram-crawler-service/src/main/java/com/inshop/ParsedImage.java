package com.inshop;

import java.net.URL;

/**
 * Created by savetisyan on 16/09/15.
 */
public class ParsedImage<T> {
    private T image;
    private URL url;

    public ParsedImage(URL url, T image) {
        this.url = url;
        this.image = image;
    }

    public T getImage() {
        return image;
    }

    public void setImage(T image) {
        this.image = image;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
