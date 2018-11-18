package com.example.litness.litness.Dialog;

import android.app.AlertDialog;
import android.content.Context;

import java.util.List;

public class ImageDialog extends AlertDialog {

    private Context ctx;
    private List<Integer> data;

    public ImageDialog(Context c, List<Integer> d) {
        super(c);
        ctx = c;
        data = d;
    }
}
