package com.example.getfamiliarwiththemap;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button ok;
    private Button exit;

    private AlertDialog.Builder msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ok = (Button) findViewById(R.id.go);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity( new Intent(MainActivity.this, MapsActivity.class));

            }
        });

        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg = new AlertDialog.Builder( MainActivity.this);
                msg.setCancelable(false);
                msg.setTitle("Exit");
                msg.setMessage("Are you sure?");
                msg.setIcon(android.R.drawable.btn_dialog);
                msg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                msg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                final AlertDialog msg1 = msg.create();

                msg1.show();

                final Button posBtn = msg1.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout posParent = (LinearLayout) posBtn.getParent();
                posParent.setGravity(Gravity.CENTER_HORIZONTAL);
                View posSpace = posParent.getChildAt(1);
                posSpace.setVisibility(View.GONE);

                final Button negBtn = msg1.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout negParent = (LinearLayout) posBtn.getParent();
                negParent.setGravity(Gravity.CENTER_HORIZONTAL);
                View negSpace = negParent.getChildAt(1);
                negSpace.setVisibility(View.GONE);





            }
        });

    }
}
