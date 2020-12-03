/**
 * Class that sets up DialogFragment for Instructions button for ASL Sink or Sign.
 */

package aslfingerspellingbee;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SinkOrSignInstructions extends DialogFragment {

    /**
     * Creates and sets up DialogFragment for Sink or Sign Instructions.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Click the handsigns to solve the word before the shark attacks!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}