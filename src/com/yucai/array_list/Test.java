package com.yucai.array_list;

public class Test {

    public static void main(String[] args) {
        _ArrayList<Student> arr = new _ArrayList<>();

        Student s1 = new Student("a", 88);
        Student s2 = new Student("b", 99);
        Student s3 = new Student("c", 100);
        Student s4 = new Student("d", 60);


        arr.addLast(s1);
        arr.addLast(s2);
        arr.addLast(s3);
        arr.addLast(s4);

        System.out.println(arr);
    }
}
