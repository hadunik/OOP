import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCreditBook {

    List<Semester> creditBook;
    private int currentSemester;

    public MyCreditBook() {
        creditBook = new ArrayList<>();
    }

    public void addMark(int semester, String sub, int mark, boolean last_semester) throws Exception {
        if (semester <= 0 || semester >= 9) {
            throw new Exception("Incorrect semester");
        }
        if (semester > currentSemester)
            currentSemester = semester;
        if (mark < 3 || mark > 5) {
            throw new Exception("Incorrect mark");
        }
        if (creditBook.stream()
                .noneMatch(i -> i.getNumber() == semester)) {
            Semester temp = new Semester();
            temp.setNumber(semester);
            creditBook.add(temp);
        }
        Subject subject = new Subject();
        subject.setName(sub);
        subject.setMark(mark);
        subject.setLast(last_semester);
        creditBook.get(semester - 1).getSubjects().add(subject);
    }

    public void changeMark(int semester, String sub, int new_mark) throws Exception {
        if (semester <= 0 || semester >= 9) {
            throw new Exception("Incorrect semester");
        }
        if (semester > currentSemester)
            currentSemester = semester;
        if (new_mark < 2 || new_mark > 5) {
            throw new Exception("Incorrect mark");
        }
        if (creditBook.size() < semester) {
            throw new Exception("Not Exist such semester in credit book");
        }
        if(creditBook.get(semester - 1).getSubjects().stream()
                .noneMatch(i -> Objects.equals(i.getName(), sub))){
            throw new Exception("Not Exist such subject in credit book");
        }
        for(Subject t : creditBook.get(semester - 1).getSubjects()){
            if(Objects.equals(t.getName(),sub)){
                t.setMark(new_mark);
                return;
            }
        }
    }

    public float averageMark() {
        float res = 0;
        int cnt = 0;
        for(Semester t1 : creditBook){
            for(Subject t2 : t1.getSubjects()){
                cnt++;
                res += t2.getMark();
            }
        }
        res /= cnt;
        return res;
    }

    public boolean checkRedDiploma() {
        int cnt = 0;
        int cntExc = 0;
        for(Semester t1 : creditBook){
            for(Subject t2 : t1.getSubjects()){
                if(t2.getMark() == 5 && t2.lastSemester()){
                    cntExc++;
                }
                if(t2.lastSemester()){
                    cnt++;
                }
                if(t2.getMark() <= 3){
                    return false;
                }
            }
        }

        if (creditBook.size() < 8) {
            return (cnt * 0.75 <= cntExc);
        }
        else if(creditBook.get(7).getSubjects().stream()
                .noneMatch(i -> Objects.equals(i.getName(), "Diploma"))){
            return (cnt * 0.75 <= cntExc);
        }
        else {
            for (Subject t : creditBook.get(7).getSubjects()) {
                if (Objects.equals(t.getName(), "Diploma")) {
                    if (t.getMark() != 5)
                        return false;
                }
            }
        }

        return (cnt * 0.75 <= cntExc);
    }

    public boolean checkIncreasedScholarship() {
        for(Subject t : creditBook.get(currentSemester - 1).getSubjects()){
            if(t.getMark() != 5){
                return false;
            }
        }
        return true;
    }

}



