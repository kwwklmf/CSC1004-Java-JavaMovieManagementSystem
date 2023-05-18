package dbs;

public class Movie {
    private String poster;
    private String movieName;
    private String director;
    private String actor;
    private String length;
    private String date;
    private String information;

    private String videos;

    public Movie(String poster, String movieName, String director, String actor, String length, String date, String information,String videos) {
        this.poster = poster;
        this.movieName = movieName;
        this.director = director;
        this.actor = actor;
        this.length = length;
        this.date = date;
        this.information = information;
        this.videos= videos;
    }
    public Movie(){}

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
    public String getVideos(){return  videos;}
    public void setVideos(String videos){this.videos=videos;}

}