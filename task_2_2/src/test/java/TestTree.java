import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class TestTree {
    @Test
    public void simpleTest1() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.add("h","java");
        tree.add("tr","java");
        tree.add("t","world");
        boolean flag =  tree.stream().anyMatch(s -> s.length()==5);
        Assertions.assertTrue(flag);
        Assertions.assertFalse(tree.stream().noneMatch(s -> Objects.equals(s, "java")));
        Assertions.assertArrayEquals(new String[]{"hello","world","from","java","t","h","tr"}, tree.toArray());
        tree.stream().filter(s -> s.length() == 4).forEach(System.out::println);
    }

    @Test
    public void simpleTest2() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.add("h","java");
        tree.add("tr","java");
        tree.add("t","world");
        tree.remove("java");
        Assertions.assertArrayEquals(new String[]{"hello","world","from","t"}, tree.toArray());
    }

    @Test
    public void simpleTest() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.add("h","java");

        Assertions.assertArrayEquals(new String[]{"hello","world","from","java","h"}, tree.toArray());
    }

    @Test
    public void deleteTest() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.remove("java");
        Assertions.assertArrayEquals(new String[]{"hello", "world", "from"}, tree.toArray());
    }

    @Test
    public void deleteAllTest() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.removeAll(List.of("world", "java"));
        Assertions.assertArrayEquals(new String[]{"hello", "from"}, tree.toArray());
    }

    @Test
    public void containsAllTest() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.containsAll(List.of("world", "java"));
        Assertions.assertArrayEquals(new String[]{"hello", "world", "from", "java"}, tree.toArray());
    }

    @Test
    public void retainAllTest() {
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        tree.add("from");
        tree.add("java");
        tree.retainAll(List.of("world", "java"));
        Assertions.assertArrayEquals(new String[]{"world", "java"}, tree.toArray());
    }

    @Test
    public void toArrayTest(){
        Tree<String> tree = new Tree<>();
        tree.add("hello");
        tree.add("world");
        var ans = tree.toArray(new String[2]);
        Assertions.assertArrayEquals(new String[]{"hello", "world"}, ans);
    }
}
