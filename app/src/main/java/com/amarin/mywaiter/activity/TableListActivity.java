package com.amarin.mywaiter.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnTableSelectedListener;
import com.amarin.mywaiter.fragment.TableListFragment;
import com.amarin.mywaiter.utils.MyWaiterConstants;

public class TableListActivity extends AppCompatActivity implements OnTableSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table_list);

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_table_list) != null) {
            if (fm.findFragmentById(R.id.fragment_table_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_list, new TableListFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onTableSelected(int tablePosition) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(MyWaiterConstants.ARG_TABLE_POSITION, tablePosition);
        startActivityForResult(intent, MyWaiterConstants.REQUEST_TABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_table_list) != null) {
            TableListFragment fragment = (TableListFragment) fm.findFragmentById(R.id.fragment_table_list);
            if (fragment != null) {
                fm.beginTransaction()
                        .detach(fragment)
                        .attach(fragment)
                        .commit();
            }
        }
    }
}
