package sg.edu.rp.c346.id20006092.ndpsongs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song( int id, String title, String singers, int year, int stars  ) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSingers() {
        return singers;
    }

    public Song setSingers(String singers) {
        this.singers = singers;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Song setYear(int year) {
        this.year = year;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Song setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++ ) {
            starsString += "*";
        }
        return title + "\n" + singers + " - " + year + "\n" + starsString;
    }

}
