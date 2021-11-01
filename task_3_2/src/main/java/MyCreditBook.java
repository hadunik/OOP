import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCreditBook {

    List<Semester> creditBook;
    private int currentSemester;

    public MyCreditBook() {
        creditBook = new ArrayList<>();
    }

    public void changeMark(int semester, String sub, int new_mark) throws Exception {
        if (semester > currentSemester)
            currentSemester = semester;
        if (creditBook.size() < semester) {
            throw new Exception("Not Exist such semester in credit book");
        }
        if (creditBook.get(semester - 1).getSubjectsStream()
                .noneMatch(i -> Objects.equals(i.getName(), sub))) {
            throw new Exception("Not Exist such subject in credit book");
        }
        creditBook.get(semester - 1).getSubjects()
                .stream()
                .filter(i -> Objects.equals(i.getName(), sub))
                .findFirst()
                .map(i -> {
                    try {
                        i.setMark(new_mark);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return i;
                });
    }

    public void addMark(int semester, String sub, int mark, boolean last_semester) throws Exception {
        if (semester > currentSemester)
            currentSemester = semester;
        if (creditBook.stream()
                .noneMatch(i -> i.getNumber() == semester)) {
            Semester temp = new Semester();
            temp.setNumber(semester);
            creditBook.add(temp);
        }
        if (creditBook.get(semester - 1).getSubjectsStream()
                .noneMatch(i -> Objects.equals(i.getName(), sub))) {
            Subject subject = new Subject(sub, mark, last_semester);
            creditBook.get(semester - 1).addSubject(subject);
        } else {
            throw new Exception("This subject already exists");
        }
    }

    public float averageMark() {
        float res = 0;
        int cnt = 0;
        for (Semester t1 : creditBook) {
            for (Subject t2 : t1.getSubjects()) {
                cnt++;
                res += t2.getMark();
            }
        }
        return res / cnt;
    }

    public boolean checkRedDiploma() {
        int cnt = 0;
        int cntExc = 0;
        for (Semester t1 : creditBook) {
            for (Subject t2 : t1.getSubjects()) {
                if (t2.getMark() <= 3) {
                    return false;
                }
                if (t2.lastSemester()) {
                    if (t2.getMark() == 5) {
                        cntExc++;
                    }
                    cnt++;
                }
            }
        }

        if (creditBook.size() < 8) {
            return (cnt * 0.75 <= cntExc);
        } else if (creditBook.get(7).getSubjectsStream()
                .noneMatch(i -> Objects.equals(i.getName(), "Diploma"))) {
            return (cnt * 0.75 <= cntExc);
        }
        return creditBook.get(7)
                .getSubjectsStream()
                .filter(i -> Objects.equals(i.getName(), "Diploma"))
                .anyMatch(i -> i.getMark() == 5);

    }

    public boolean checkIncreasedScholarship() {
        return creditBook.get(currentSemester - 1).checkIncSolar();
    }
}