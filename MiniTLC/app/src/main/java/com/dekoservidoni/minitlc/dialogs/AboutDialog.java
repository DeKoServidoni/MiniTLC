package com.dekoservidoni.minitlc.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dekoservidoni.minitlc.R;

/**
 * Class responsible to show the about us dialog for the user
 *
 * Created by DeKoServidoni on 2/28/16.
 */
public class AboutDialog extends DialogFragment {

    /**
     * Get a new instance from this dialog
     *
     * @return dialog instance
     */
    public static AboutDialog newInstance() {
        return new AboutDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View dialogView = inflater.inflate(R.layout.fragment_dialog_about, container, false);

        Button button = (Button) dialogView.findViewById(R.id.about_dialog_ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialogView;
    }
}
