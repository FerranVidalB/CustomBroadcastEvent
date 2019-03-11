package org.ieselcaminas.pmdm.custombroadcastevent;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bbroad = (Button) findViewById(R.id.buttonbroadcast);
        Button benable = (Button) findViewById(R.id.buttonenable);
        Button bdisable = (Button) findViewById(R.id.buttondisable);



        final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);




        bbroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // broadcast a custom intent.
                /*Intent intent = new Intent();
                intent.setAction("net.victoralonso.CUSTOM_INTENT");
                sendBroadcast(intent);*/
                // Create intent with action

                Intent localIntent = new Intent("CUSTOM_ACTION");

                // Send local broadcast

                localBroadcastManager.sendBroadcast(localIntent);
            }
        });
        benable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // broadcast a custom intent.
                /*IntentFilter filter = new IntentFilter("net.victoralonso.CUSTOM_INTENT");
                receiver = new MyReceiver();
                registerReceiver(receiver, filter);*/
                IntentFilter filter = new IntentFilter("CUSTOM_ACTION");
                receiver = new MyReceiver();
                localBroadcastManager.registerReceiver(receiver, filter);
            }
        });
        bdisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // broadcast a custom intent.

               //unregisterReceiver(receiver);
                localBroadcastManager.unregisterReceiver(receiver);
            }
        });

    }
    private BroadcastReceiver listener = new BroadcastReceiver() {

        @Override

        public void onReceive(Context context, Intent intent ) {

            String data = intent.getStringExtra("DATA");

            Log.d( "Received data : ", data);

        }

    };
}
