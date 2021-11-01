public class Subject {
    private String name;
    private int mark;
    private boolean flag_last_semester;

    public Subject(String sub, int new_mark, boolean flag) throws Exception{
        if (new_mark < 3 || new_mark > 5) {
            throw new Exception("Incorrect mark");
        }
        this.name = sub;
        this.mark = new_mark;
        this.flag_last_semester = flag;
    }

    public int getMark(){
        return mark;
    }

    public void setMark(int newMark) throws Exception{
        if (newMark < 3 || newMark > 5) {
            throw new Exception("Incorrect mark");
        }
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