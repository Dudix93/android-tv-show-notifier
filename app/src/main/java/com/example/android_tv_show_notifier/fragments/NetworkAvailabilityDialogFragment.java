package com.example.android_tv_show_notifier.fragments;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.activities.ActorActivity;

public class NetworkAvailabilityDialogFragment extends DialogFragment {

    public static String TAG = "NetworkAvailabilityDialog";
    private Dialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(R.string.no_internet_connection);
        dialogBuilder.setNeutralButton(R.string.refresh, null);
        this.dialog = dialogBuilder.create();
        this.dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button refreshButton = ((AlertDialog) dialogInterface).getButton(DialogInterface.BUTTON_NEUTRAL);
                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNetworkAvailable(requireContext())) {
                            dialogInterface.dismiss();
                        }
                        else {
                            Toast.makeText(requireContext(), requireContext().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return this.dialog;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void checkNetwofkForNewIntent(Context context, View view, Intent intent) {
        NetworkAvailabilityDialogFragment networkAvailabilityDialogFragment = new NetworkAvailabilityDialogFragment();
        if (!networkAvailabilityDialogFragment.isNetworkAvailable(context)) {
            FragmentManager fm = ((FragmentActivity)view.getContext()).getSupportFragmentManager();
            networkAvailabilityDialogFragment.show(fm, NetworkAvailabilityDialogFragment.TAG);
            fm.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);

                    if (networkAvailabilityDialogFragment.isNetworkAvailable(context)) context.startActivity(intent);

                    fm.unregisterFragmentLifecycleCallbacks(this);
                }
            }, false);
        }
        else {
            context.startActivity(intent);
        }
    }
}
