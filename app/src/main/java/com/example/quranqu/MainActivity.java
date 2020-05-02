package com.example.quranqu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quranqu.service.JobServices;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sJob, cJob;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sJob = findViewById(R.id.scheduleJob);
        cJob = findViewById(R.id.cancelJob);

        sJob.setOnClickListener(this);
        cJob.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scheduleJob :
                ComponentName componentName = new ComponentName(getApplicationContext().getPackageName(), JobServices.class.getName());
                instanceJob(1,componentName);
                break;
            case R.id.cancelJob :
                cancelJob(1);
                break;
        }
    }

    public void instanceJob(int jobId, ComponentName componentName) {
        JobInfo info = new JobInfo.Builder(jobId, componentName)
                    .setPersisted(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "job Scheduled");
        } else {
            Log.d(TAG, "job scheduling failed");
        }
    }

    public void cancelJob(int jobId){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(jobId);
    }
}
