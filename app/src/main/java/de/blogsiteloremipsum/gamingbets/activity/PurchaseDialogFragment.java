package de.blogsiteloremipsum.gamingbets.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import de.blogsiteloremipsum.gamingbets.R;

/**
 * Created by Felix on 24.05.2016.
 */
public class PurchaseDialogFragment extends DialogFragment {

        public static PurchaseDialogFragment newInstance(int score, int item, String locks) {
        PurchaseDialogFragment f = new PurchaseDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("score", score);
        args.putInt("item", item);
            args.putString("locks", locks);
        f.setArguments(args);

        return f;
    }


    public interface NoticeDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int score = getArguments().getInt("score");
        int item = getArguments().getInt("item");
        String unlocks = getArguments().getString("locks");

        int profile_to_unlock;
        profile_to_unlock = -1;

        int i = 0;
        int position_counter = 0;

        for (char each : unlocks.toCharArray()) {
            if (each == '0') {
                if (position_counter == item) {
                    profile_to_unlock = i;
                    break;
                } else {
                    position_counter++;
                }

            }
            i++;
        }



        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you really want spent 50pts on this stupid Pic "+(profile_to_unlock+1)+"? You´re Score will be "+score+" afterwards!")
                .setPositiveButton("Sure, you dumb ass!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("PositiveClick in PurchaseDialogFragment!");
                        mListener.onDialogPositiveClick(PurchaseDialogFragment.this);
                    }
                })
                .setNegativeButton("I´m not that stupid!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("NegativeClick in PurchaseDialogFragment!");
                        mListener.onDialogNegativeClick(PurchaseDialogFragment.this);
                        // User cancelled the dialog
                    }
                });

        AlertDialog dialog = builder.create();
        // Create the AlertDialog object and return it
        return dialog;
    }
}

