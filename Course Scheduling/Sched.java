package com.team6.courseschedule2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Orion on 5/6/2015.
 */
@SuppressWarnings("serial")
public class Sched implements Serializable {
    private ArrayList<TBlocks> timeblocks = new ArrayList<TBlocks>();
    private ArrayList<Courses> courses = new ArrayList<Courses>();
    private String name;
    //initialize a new schedule
    public Sched(String n){
        name = n;
    }
    //populate schedule with existing data
    public Sched(ArrayList<TBlocks> tb, ArrayList<Courses> course, String n){
        timeblocks = tb;
        courses = course;
        name = n;
    }

    public void addTimeBlocks(TBlocks timeblock){
        timeblocks.add(timeblock);
    }

    public void addCourses(Courses cors){
        courses.add(cors);
    }

    public void setName(String nam){
        name = nam;
    }

    public ArrayList<Courses> getCourses(){
        ArrayList<Courses> courseList = new ArrayList<Courses>();
        for(Courses c : courses){
            courseList.add(c);
        }
        return courseList;
    }

    public ArrayList<TBlocks> getTimeBlocks(){
        ArrayList<TBlocks> TBList = new ArrayList<TBlocks>();
        for(TBlocks t : timeblocks){
            TBList.add(t);
        }
        return TBList;
    }

    public String getName(){
        return name;
    }

    public void deleteTimeBlocks(TBlocks timeblock){
        for(int i = 0; i<timeblocks.size(); i++){
            if(timeblocks.get(i).compare(timeblock) == true)
                timeblocks.remove(i);
        }
    }

    public void deleteCourses(Courses cors){
        for(int i = 0; i<courses.size(); i++){
            if(courses.get(i).compare(cors) == true)
                courses.remove(i);
        }
    }
}
