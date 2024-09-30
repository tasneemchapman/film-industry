public class FilmIndustry {

    public enum MovieGenre {
        Horror, SciFi, Romance, Comedy, Drama, Action
    }

    public interface FilmIndustryWorker {
        // abtract methods
    }
    String firstName();

    public record Director(String firstName, String lastName, int numYearsActive,
                           boolean shootDigitalFilm) {
    }

}
