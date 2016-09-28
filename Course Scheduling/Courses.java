package com.team6.courseschedule2;

/**
 * Created by Orion on 5/6/2015.
 */
public class Courses {
    private String dept;
    private int num;
    private int start;
    private int end;

    public Courses(String department, int number, int s, int e){
        dept = department;
        num = number;
        start = s;
        end = e;
    }

    public int getStartCourse(){
        return start;
    }

    public int getEndCourse(){
        return end;
    }

    public String getDept(){
        return dept;
    }

    public int getNum(){
        return num;
    }

    public boolean compare(Courses cors){
        if((cors.getStartCourse() == start) && (cors.getEndCourse() == end)
                && (cors.getDept() == dept) && (cors.getNum() == num)){
            return true;
        }
        return false;
    }
}
