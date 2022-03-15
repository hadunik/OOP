import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParallelsStream {

    public static boolean checkerParallels(Integer[] arr){
        List<Integer> list = Arrays.asList(arr);
        Optional<Integer> tmp = list.parallelStream().filter(CheckerOnPrime::isNotPrime).findFirst();
        return tmp.isPresent();
    }
}
