package com.team6.courseschedule2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class schedule extends ActionBarActivity {

    EditText scheduleName = null;
    private ArrayList<Sched> scheduleList = new ArrayList<Sched>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createScheduleButtonOnClick(View v) {
            Intent modify = new Intent(schedule.this, modifySchedule.class);
            //Have text as new schedule name
            Sched newSched = new Sched("New Schedule");
            scheduleList.add(newSched);
            //serialize and create new Activity
            modify.putExtra("Sche", newSched);
            startActivity(modify);
    }



}
