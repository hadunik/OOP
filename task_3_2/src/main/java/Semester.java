import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private int number;
    @Getter
    @Setter
    private List<Subject> subjects;

    public void setNumber(int val){
        number = val;
    }

    public int getNumber(){
        return number;
    }

    public Semester(){
        subjects = new ArrayList<>();
    }

}
