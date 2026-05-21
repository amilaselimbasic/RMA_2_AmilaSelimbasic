package ba.moviecatalog;

import java.io.Serializable;

// Klasa koja predstavlja jednog glumca
public class Actor implements Serializable {   // ➝ dodali implements Serializable
    private int image;
    private String name;

    public Actor(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() { return image; }
    public String getName() { return name; }
}
