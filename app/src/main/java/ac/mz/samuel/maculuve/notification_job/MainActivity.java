package ac.mz.samuel.maculuve.notification_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);
        sendOnChannel1();
        sendOnChannel2();
    }

    public void sendOnChannel1() {
        String title = "Amo Yonisse";
        String message ="Ela vai amar-me um dia";

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2() {
        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.download);


        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_looks_two_black_24dp)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_sentiment_dissatisfied_black_24dp, "Dislike", null)
                .addAction(R.drawable.ic_skip_previous_black_24dp, "Previous", null)
                .addAction(R.drawable.ic_history_black_24dp, "Pause", null)
                .addAction(R.drawable.ic_skip_next_black_24dp, "Next", null)
                .addAction(R.drawable.ic_done_all_black_24dp, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3))
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);
    }
}
