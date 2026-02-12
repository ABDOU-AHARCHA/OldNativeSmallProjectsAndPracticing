package com.example.betwinner;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class NetworkchangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {




            if (Common.IsConnectedTOInternet(context)==false) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                View ld = LayoutInflater.from(context).inflate(R.layout.customdialog, null);
                b.setView(ld);

                AppCompatButton btnRetry = ld.findViewById(R.id.btn_okay);

                //show dialog
                AlertDialog d = b.create();
                d.show();
                d.setCancelable(false);

                d.getWindow().setGravity(Gravity.CENTER);

                btnRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Common.IsConnectedTOInternet(context);
                        d.dismiss();
                        onReceive(context, intent);
                    }
                });

            }






    }
}
