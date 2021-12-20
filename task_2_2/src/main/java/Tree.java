import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

public class Tree<T extends Comparable<T>> implements Collection<T> {
    private Node<T> root;
    private int sizeOfTree;
    private boolean change = false;

    /**
     *
     * @return size of tree
     */
    @Override
    public int size() {
        return sizeOfTree;
    }

    /**
     *
     * @return true if tree is empty
     */
    @Override
    public boolean isEmpty() {
        return sizeOfTree == 0;
    }

    /**
     * @param o find this in tree
     * @return true if tree contains object
     */
    @Override
    public boolean contains(Object o) {
        if (sizeOfTree == 0)
            return false;
        for(var i : this){
            if(i.equals(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * iterator by method DFS
     * @return
     */
    public Iterator<T> DFS() {
        return new Iterator<T>() {
            private boolean haveRoot = false;
            Stack<Node<T>> stack = new Stack<>();

            @Override
            public boolean hasNext() {
                if (!haveRoot) {
                    haveRoot = true;
                    stack.push(root);
                    change = false;
                }
                if(change)
                    throw new ConcurrentModificationException();
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                Node<T> tmp = stack.pop();
                for(int i = 0 ; i < tmp.cnt_sons; i++){
                    stack.push(tmp.sons.get(i));
                }
                return tmp.getData();
            }
        };
    }

    /**
     * iterator by method BFS
     * @return
     */
    public Iterator<T> BFS() {
        return new Iterator<T>() {
            private boolean haveRoot = false;
            Queue<Node<T>> queue = new LinkedList<>();

            @Override
            public boolean hasNext() {
                if (!haveRoot) {
                    haveRoot = true;
                    queue.add(root);
                    change = false;
                }
                if(change)
                    throw new ConcurrentModificationException();
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                Node<T> tmp = queue.poll();
                for(int i = 0 ; i < tmp.cnt_sons; i++){
                    queue.add(tmp.sons.get(i));
                }
                return tmp.getData();
            }
        };
    }

    /**
     * defines an iterator
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return BFS();
    }

    /**
     *
     * @return converts a tree to an array
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[sizeOfTree];
        if(sizeOfTree == 0){
            return arr;
        }
        int i = 0;
        for (var temp : this) {
            arr[i] = temp;
            i++;
        }
        return arr;
    }

    /**
     *
     * @param a
     * @param <T1>
     * @return
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < sizeOfTree)
            a = Arrays.copyOf(a, sizeOfTree);
        int i = 0;
        for (var tmp : this) {
            a[i] = (T1) tmp;
            i++;
        }
        return a;
    }

    /**
     *
     * @param t what we add to the root tree
     * @return true if can add to tree
     */
    @Override
    public boolean add(T t) {
        if(root == null){
            root = new Node<>(t);
            sizeOfTree++;
            return true;
        }
        Node<T> tmp = new Node<>(t);
        root.sons.add(tmp);
        root.cnt_sons_nodes++;
        tmp.parents.add(root);
        tmp.cnt_parents++;
        root.cnt_sons++;
        sizeOfTree++;
        return true;
    }

    /**
     *
     * @param t what we add to the root tree
     * @param o after that we add to the tree
     * @return true if can add to tree
     */
    public boolean add(T t, Object o){
        if(!contains(o))
            return false;
        Node<T> for_add = finder(o);
        if(for_add == null)
            return false;
        sizeOfTree++;
        Node<T> tmp = new Node<>(t);
        for_add.cnt_sons++;
        for_add.cnt_parents++;
        for_add.sons.add(tmp);
        tmp.parents.add(for_add);
        Node<T> parent = for_add;
        while(parent != null){
            parent.cnt_sons_nodes++;
            if(parent == root){
                return true;
            }
            parent = parent.parents.get(0);
        }
        return true;
    }

    /**
     * deletes a vertex and its entire subtree
     * @param o what we are deleting
     * @return true if can delete
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        if(root.getData().equals(o)){
            sizeOfTree = 0;
            root = null;
            return true;
        }
        Node<T> for_del = finder(o);
        if(for_del == null)
            return false;
        Node<T> par = for_del.parents.get(0);
        par.cnt_sons--;
        par.sons.remove(for_del);
        sizeOfTree -= for_del.cnt_sons_nodes + 1;
        Node<T> parent = par;
        while(parent != null){
            parent.cnt_sons_nodes -= for_del.cnt_sons_nodes + 1;
            if(parent == root){
                return true;
            }
            parent = parent.parents.get(0);
        }
        return true;
    }

    /**
     * private func for find node
     * @param o what we are find
     * @return Node with data = o
     */
    private Node<T> finder(Object o){
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node<T> tmp = queue.poll();
            if(tmp.getData().equals(o)){
                return tmp;
            }
            for(int i = 0 ; i < tmp.cnt_sons; i++){
                queue.add(tmp.sons.get(i));
            }
        }
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var tmp : c) {
            if (!contains(tmp))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (var tmp : c) {
            if (!add(tmp))
                return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (var tmp : c) {
            if (!remove(tmp))
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Tree<T> newTree = new Tree<>();
        for (var tmp : this) {
            if (c.contains(tmp)) {
                if (!newTree.add(tmp))
                    return false;
            }
            else
                sizeOfTree--;

        }
        this.root = newTree.root;
        change = true;
        return true;
    }

    @Override
    public void clear() {
        change = true;
        root = null;
    }

    @Data
    @RequiredArgsConstructor
    private class Node<T> {
        final T data;
        int cnt_parents;
        ArrayList<Node<T>> parents = new ArrayList<>();
        int cnt_sons;
        int cnt_sons_nodes;
        ArrayList<Node<T>> sons = new ArrayList<>();
    }
}
