package com.jm.newvista.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jm.newvista.R;

public class DescriptionDialogFragment extends DialogFragment {
    private DescriptionFragmentCallbackListener mListener;

    public void show(FragmentManager manager) {
        show(manager, "DescriptionDialogFragment");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_description, null);
        TextView description = view.findViewById(R.id.description);
        String descriptionStr = mListener.onGetDescription();
        description.setText(descriptionStr);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton(getString(R.string.back), null);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DescriptionFragmentCallbackListener) {
            mListener = (DescriptionFragmentCallbackListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DescriptionFragmentCallbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface DescriptionFragmentCallbackListener {
        String onGetDescription();
    }
}
