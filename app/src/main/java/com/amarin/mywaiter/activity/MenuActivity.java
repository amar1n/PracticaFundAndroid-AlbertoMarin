package com.amarin.mywaiter.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnDishSelectedListener;
import com.amarin.mywaiter.fragment.MenuFragment;
import com.amarin.mywaiter.model.Dish;
import com.amarin.mywaiter.model.OrderItem;
import com.amarin.mywaiter.model.Table;
import com.amarin.mywaiter.utils.MyWaiterConstants;

public class MenuActivity extends AppCompatActivity implements OnDishSelectedListener {

    protected Table mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        mTable = (Table) getIntent().getSerializableExtra(MyWaiterConstants.ARG_TABLE);

        setTitle(getString(R.string.app_name) + getString(R.string.toolbar_title_separator) + getString(R.string.toolbar_title_menu));

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_menu) != null) {
            if (fm.findFragmentById(R.id.fragment_menu) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_menu, MenuFragment.newInstance(mTable))
                        .commit();
            }
        }
    }

    @Override
    public void onDishClick(Dish dish, View view) {
        Intent intent = new Intent(this, OrderItemActivity.class);
        OrderItem orderItem = new OrderItem(dish, null);
        intent.putExtra(MyWaiterConstants.ARG_ORDER_ITEM, orderItem);
        startActivityForResult(intent, MyWaiterConstants.REQUEST_ORDER_ITEM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MyWaiterConstants.REQUEST_ORDER_ITEM) {
            if (resultCode == Activity.RESULT_OK) {
                OrderItem orderItem = (OrderItem) data.getSerializableExtra(MyWaiterConstants.ARG_ORDER_ITEM);
                mTable.addOrderItem(orderItem);

                Intent returnIntent = new Intent();
                returnIntent.putExtra(MyWaiterConstants.ARG_TABLE, mTable);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        }
    }
}
