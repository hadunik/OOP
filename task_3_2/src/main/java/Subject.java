public class Subject {
    private String name;
    private int mark;
    private boolean flag_last_semester;

    public int getMark(){
        return mark;
    }

    public void setMark(int newMark){
        this.mark = newMark;
    }

    public boolean lastSemester(){
        return flag_last_semester;
    }

    public void setLast(boolean val){
        this.flag_last_semester = val;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}