public class Citation {
    String format;
    String type;
    String author;
    String workTitle;
    String AthologyTitle;
    String[] extraTitles;
    RolePerson[] otherPersons;
    String publisher;
    int volumeNum;
    int issueNum;
    String date;
    String url;
    boolean dissertation;
    public Citation(String format, String type, String author, String title, String publisher, String date) {
        this.format = format;
        this.type = type;
        this.author = author;
        this.workTitle = title;
        this.publisher = publisher;
        this.date = date;
    }
}
