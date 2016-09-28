package com.team6.courseschedule2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class modifySchedule extends ActionBarActivity {

    private Sched sched;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_schedule);

        //Input serialized schedule
        Intent i = getIntent();
        Sched sch = (Sched)i.getSerializableExtra("Sche");
        setSchedule(sch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_schedule, menu);
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

    public void addCourseButtonOnClick(View v) {
        startActivity(new Intent(modifySchedule.this, AddCourse.class));
    }

    public void deleteCourseButtonOnClick(View v) {
        startActivity(new Intent(modifySchedule.this, DeleteCourse.class));
    }

    public void setSchedule(Sched sch){
        sched = sch;
    }
}
