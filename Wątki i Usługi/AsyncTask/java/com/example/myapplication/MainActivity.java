package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    AsyncTaskTest asyncTest;
    private ProgressBar asynctPreogress;
    private TextView asyncMessage;
    private int zakres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // odwołanie od kontrolek z widoku
        final ProgressBar pTask = (ProgressBar)findViewById(R.id.progressBar_task);
        Button bTask = (Button)findViewById(R.id.button_task);
        Button bCancel = (Button)findViewById(R.id.button_cancel);
        final EditText etTime = (EditText)findViewById(R.id.editText_time_r);

        //asyncTest = (AsyncTaskTest)getLastNonConfigurationInstance();
        if(asyncTest == null){
            asyncTest= new AsyncTaskTest();
            asyncTest.ConnectActivity(this);
        }

        bTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max = (etTime.getText().toString().isEmpty()) ? 0 : Integer.parseInt(etTime.getText().toString());
                if(asyncTest.getStatus().equals(AsyncTask.Status.RUNNING)){
                    Toast.makeText(MainActivity.this, "Pracuje ", Toast.LENGTH_SHORT).show();
                }else{
                    pTask.setMax(max);
                    asyncTest = new AsyncTaskTest();
                    asyncTest.ConnectActivity(MainActivity.this);
                    asyncTest.execute(max);
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asyncTest.getStatus().equals(AsyncTaskTest.Status.RUNNING)){
                    asyncTest.cancel(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTest.DisconnectActivity();
    }

//    @Override
//    public Object onRetainCustomNonConfigurationInstance() {
//        return asyncTest;
//    }

    public class AsyncTaskTest extends AsyncTask<Integer, Integer, Void> {

        private Activity activity;


        public AsyncTaskTest(){

        }

        public void ConnectActivity(Activity conn){
            this.activity = conn;
            asyncMessage = (TextView)activity.findViewById(R.id.textView_task);
            asynctPreogress = (ProgressBar)activity.findViewById(R.id.progressBar_task);
        }

        public void DisconnectActivity(){
            this.activity = null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            asynctPreogress.setProgress(0);
            asyncMessage.setText(getResources().getText(R.string.number) + " 0 / " + zakres );
            //asynctPreogress.setMax(zakres);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            zakres = integers[0];
            try{
                for (int i = 0; i<= zakres; i++){
                    publishProgress(i, zakres);
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            asynctPreogress.setProgress(values[0]);
            asyncMessage.setText(getResources().getText(R.string.number) + " " +values[0] + " / " + values[1]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(activity.getApplicationContext(), "Przerwano pracę wątka", Toast.LENGTH_SHORT).show();
            asynctPreogress.setProgress(0);
            asyncMessage.setText(getResources().getText(R.string.text_progres));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }
}

