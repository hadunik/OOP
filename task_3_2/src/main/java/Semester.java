import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Semester {
    private int number;
    @Getter
    @Setter
    private List<Subject> subjects;

    public void setNumber(int val) throws Exception{
        if (val <= 0 || val >= 9) {
            throw new Exception("Incorrect semester");
        }
        number = val;
    }

    public int getNumber(){
        return number;
    }

    public Semester(){
        subjects = new ArrayList<>();
    }

    public Stream<Subject> getSubjectsStream(){
        return subjects.stream();
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    public boolean checkIncSolar(){
        return subjects.stream()
                .noneMatch(i -> i.getMark() != 5);
    }
}
