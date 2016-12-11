package com.amarin.mywaiter.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnShowMenuClickListener;
import com.amarin.mywaiter.fragment.TableFragment;
import com.amarin.mywaiter.model.Restaurant;
import com.amarin.mywaiter.utils.MyWaiterConstants;

public class TableActivity extends AppCompatActivity implements OnShowMenuClickListener {

    protected int mTablePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table);

        mTablePosition = getIntent().getIntExtra(MyWaiterConstants.ARG_TABLE_POSITION, 0);

        setTitle(getString(R.string.app_name) + getString(R.string.toolbar_title_separator) + Restaurant.getInstance().getTableByPosition(mTablePosition).getCode());

        if (findViewById(R.id.fragment_table) != null) {
            FragmentManager fm = getFragmentManager();
            if (fm.findFragmentById(R.id.fragment_table) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table, TableFragment.newInstance(mTablePosition))
                        .commit();
            }
        }
    }


    @Override
    public void onShowMenuClick() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MyWaiterConstants.ARG_TABLE_POSITION, mTablePosition);
        startActivityForResult(intent, MyWaiterConstants.REQUEST_MENU);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyWaiterConstants.REQUEST_MENU) {

            if (findViewById(R.id.fragment_table) != null) {
                FragmentManager fm = getFragmentManager();
                TableFragment fragment = (TableFragment) fm.findFragmentById(R.id.fragment_table);

                fm.beginTransaction()
                        .remove(fragment)
                        .add(R.id.fragment_table, TableFragment.newInstance(mTablePosition))
                        .commit();
            }
        }
    }
}
