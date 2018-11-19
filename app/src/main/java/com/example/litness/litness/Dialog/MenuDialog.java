package com.example.litness.litness.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.litness.litness.Adapter.ItemAdapter;
import com.example.litness.litness.Bar.Item;
import com.example.litness.litness.Client;
import com.example.litness.litness.R;

public class MenuDialog extends AlertDialog {

    private Context ctx;
    private ItemAdapter foodAdapter;
    private ItemAdapter drinkAdapter;

    public MenuDialog(Context c) {
        super(c);
        ctx = c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_menu);

        RecyclerView rvFood = findViewById(R.id.menudialog_food_rv);
        RecyclerView rvDrink = findViewById(R.id.menudialog_drink_rv);

        foodAdapter = new ItemAdapter(ctx);
        rvFood.setAdapter(foodAdapter);

        drinkAdapter = new ItemAdapter(ctx);
        rvDrink.setAdapter(drinkAdapter);

        //foodAdapter.updateItems(Client.activeBar.menu);

        findViewById(R.id.menudialog_button_close).setOnClickListener(x->
                dismiss());
    }
}
