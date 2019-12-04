package com.example.myapplication;

import android.app.*;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Random;

public class MyService extends IntentService {

    public MyService() {
        super("test");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "start usługi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "koniec usługi", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this){
            try{
                String text = intent.getExtras().getString("text");
                Thread.sleep(3000);
                Random r = new Random();
                int number = r.nextInt();

                updateUI(number, text);
                message(number, text);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


    private void message(int number, String text){
        // TODO dostoswać do swojej lokalizacji
        Intent intent = new Intent("com.example.student.l1_service.MyService");
        intent.putExtra("number", number);
        intent.putExtra("code", Activity.RESULT_OK);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    private void updateUI(int number, String text) {
        Intent intentUI = new Intent(this, MainActivity.class);
        final String NOTIFICATION_CHANNEL_ID = "channel_id";
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intentUI, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        builder.setContentTitle("My Service");
        builder.setContentText("wylosowana liczba " + number);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat menager = NotificationManagerCompat.from(this);
        menager.notify(100, builder.build());
    }
}
