public class Note {
    private String time;
    private String header;
    private String text;

    public Note() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    Note(String time, String header, String text) {
        this.time = time;
        this.header = header;
        this.text = text;
    }
}
