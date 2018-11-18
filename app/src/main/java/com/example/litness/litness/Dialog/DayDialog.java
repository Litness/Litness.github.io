package com.example.litness.litness.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.litness.litness.Adapter.DayDialogAdapter;
import com.example.litness.litness.Bar.Day;
import com.example.litness.litness.R;

import java.util.List;

public class DayDialog extends AlertDialog {


    private Context ctx;
    private DayDialogAdapter adapter;
    private List<Day> data;


    public DayDialog(Context c, List<Day> d) {
        super(c);
        ctx = c;
        data = d;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_day);

        RecyclerView rv = findViewById(R.id.dialogcard_rv);
        adapter = new DayDialogAdapter(ctx);
        rv.setAdapter(adapter);

    }
}
