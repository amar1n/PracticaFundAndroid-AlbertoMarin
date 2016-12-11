package com.amarin.mywaiter.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
        // Vamos a comprobar si ya tenemos un pager en nuestra interfaz
//        FragmentManager fm = getFragmentManager();
//        CityPagerFragment cityPagerFragment = (CityPagerFragment) fm.findFragmentById(R.id.fragment_city_pager);
//
//        if (cityPagerFragment != null) {
//            // Tenemos un pager, le decimos que se mueva a otra ciudad
//            cityPagerFragment.showCity(mTablePosition);
//        }
//        else {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(MyWaiterConstants.ARG_TABLE_POSITION, tablePosition);
        startActivity(intent);
//        }
    }
}
