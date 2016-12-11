package com.amarin.mywaiter.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnOrderItemResultListener;
import com.amarin.mywaiter.fragment.OrderItemFragment;
import com.amarin.mywaiter.model.OrderItem;
import com.amarin.mywaiter.utils.MyWaiterConstants;

public class OrderItemActivity extends AppCompatActivity implements OnOrderItemResultListener {

    OrderItem mOrderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_item);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mOrderItem = (OrderItem) getIntent().getSerializableExtra(MyWaiterConstants.ARG_ORDER_ITEM);

        setTitle(getString(R.string.app_name) + getString(R.string.toolbar_title_separator) + getString(R.string.toolbar_title_order_item));

        FragmentManager fm = getFragmentManager();
        if (findViewById(R.id.fragment_order_item) != null) {
            if (fm.findFragmentById(R.id.fragment_order_item) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_order_item, OrderItemFragment.newInstance(mOrderItem))
                        .commit();
            }
        }
    }

    @Override
    public void onButtonClick(int result, OrderItem orderItem) {
        if (result == MyWaiterConstants.RESULT_OK) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MyWaiterConstants.ARG_ORDER_ITEM, mOrderItem);
            setResult(RESULT_OK, returnIntent);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
