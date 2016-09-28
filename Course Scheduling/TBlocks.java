package com.team6.courseschedule2;

/**
 * Created by Orion on 5/6/2015.
 */
public class TBlocks {
    private int start;
    private int end;

    public TBlocks(int s, int e){
        start = s;
        end = e;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public boolean compare(TBlocks tblock){
        if((tblock.getStart() == start) && (tblock.getEnd() == end)){
            return true;
        }
        return false;
    }
}