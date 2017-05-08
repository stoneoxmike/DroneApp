package org.pltw.examples.droneapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by MGeyer on 5/8/2017.
 */

public class StartDialogFragment  extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.start_dialog)
                .setPositiveButton(R.string.continue_app, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User continues to the app
                    }
                })
                .setNegativeButton(R.string.exit_app, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog and app
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
