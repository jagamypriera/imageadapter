package com.jagamypriera.android.imageadapter.bundle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jagamypriera on 30/05/17.
 */

public class ImageListBundle implements Serializable{
    public ArrayList<String> images=new ArrayList<>();

    public ImageListBundle(ArrayList<String> images) {
        this.images = images;
    }
}
