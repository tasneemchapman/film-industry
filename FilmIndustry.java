public class FilmIndustry {

    public enum MovieGenre {
        Horror, SciFi, Romance, Comedy, Drama, Action
    }

    public interface FilmIndustryWorker {
        // abtract methods
        String firstName();
        String lastName();
        int industryYears();
        boolean shootDigitalFilm();
        //returns true only if worker specializes in genre thats passed in
        boolean specializesGenre(MovieGenre genre);
    }

    public record Director(String firstName, String lastName, int industryYears,
                           boolean shootDigitalFilm) implements FilmIndustryWorker {
        @Override
        public boolean specializesGenre(MovieGenre genre) {
            boolean theResult = switch(genre)
                    case Romance, Comedy, Horror, Action, Drama -> true;
                    case SciFi -> shootDigitalFilm;


        }
    }

    public record Producer(String firstName, String lastName, int industryYears)
    implements FilmIndustryWorker {}

    public enum actingStyle {Method, Character, Improv}
    public record Actor(String firstName, String lastName, int industryYears, actingStyle style) implements FilmIndustryWorker {

    }

    public static void main(String[] args) {

    }

}
