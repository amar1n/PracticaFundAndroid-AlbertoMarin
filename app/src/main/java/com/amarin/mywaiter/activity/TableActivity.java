package com.amarin.mywaiter.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnShowMenuClickListener;
import com.amarin.mywaiter.fragment.TableFragment;
import com.amarin.mywaiter.model.Table;
import com.amarin.mywaiter.utils.MyWaiterConstants;

public class TableActivity extends AppCompatActivity implements OnShowMenuClickListener {

    protected Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table);

        table = (Table) getIntent().getSerializableExtra(MyWaiterConstants.ARG_TABLE);

        setTitle(getString(R.string.app_name) + getString(R.string.toolbar_title_separator) + table.getCode());

        if (findViewById(R.id.fragment_table) != null) {
            FragmentManager fm = getFragmentManager();
            if (fm.findFragmentById(R.id.fragment_table) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table, TableFragment.newInstance(table))
                        .commit();
            }
        }
    }


    @Override
    public void onShowMenuClick() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MyWaiterConstants.ARG_TABLE, table);
        startActivityForResult(intent, MyWaiterConstants.REQUEST_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyWaiterConstants.REQUEST_MENU) {

            table = (Table) data.getSerializableExtra(MyWaiterConstants.ARG_TABLE);

            if (findViewById(R.id.fragment_table) != null) {
                FragmentManager fm = getFragmentManager();
                TableFragment fragment = (TableFragment) fm.findFragmentById(R.id.fragment_table);

                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.fragment_table, TableFragment.newInstance(table))
                        .commit();
            }
        }
    }
}
