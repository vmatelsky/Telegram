package org.telegram.techrunch.domain;

/**
 * Created by vlad on 5/20/16.
 */
public class Church {

    private final String mDescription;
    private final String mImageUrl;

    public Church(String description, String imageUrl) {
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public String getDescription() {
        return mDescription;
    }
}
