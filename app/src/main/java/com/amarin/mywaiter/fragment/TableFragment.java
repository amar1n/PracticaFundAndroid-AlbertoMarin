package com.amarin.mywaiter.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnOrderItemSelectedListener;
import com.amarin.mywaiter.facade.OnShowMenuClickListener;
import com.amarin.mywaiter.model.OrderItem;
import com.amarin.mywaiter.model.Restaurant;
import com.amarin.mywaiter.model.Table;
import com.amarin.mywaiter.utils.MyWaiterConstants;

import java.util.LinkedList;

public class TableFragment extends Fragment {

    protected int mPosition;
    protected OnShowMenuClickListener mOnShowMenuClickListener;
    protected OnOrderItemSelectedListener mOnOrderItemSelectedListener;

    public static TableFragment newInstance(int position) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(MyWaiterConstants.ARG_TABLE_POSITION, position);

        TableFragment tableFragment = new TableFragment();
        tableFragment.setArguments(arguments);

        return tableFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPosition = getArguments().getInt(MyWaiterConstants.ARG_TABLE_POSITION);
        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table, container, false);

        LinkedList<OrderItem> orderItems = new LinkedList<>();
        Table table = Restaurant.getInstance().getTableByPosition(mPosition);
        if (table.getOrder() != null && !table.getOrder().getOrderItems().isEmpty()) {
            orderItems = table.getOrder().getOrderItems();
        }
        ListView list = (ListView) root.findViewById(android.R.id.list);
        ArrayAdapter<OrderItem> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                orderItems // Nuestro modelo
        );
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (mOnOrderItemSelectedListener != null) {
                    mOnOrderItemSelectedListener.onOrderItemSelected(position);
                }
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_order_item_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnShowMenuClickListener != null) {
                    mOnShowMenuClickListener.onShowMenuClick();
                }
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_table, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem generateTheBill = menu.findItem(R.id.menu_generate_the_bill);
        generateTheBill.setEnabled(Restaurant.getInstance().getTableByPosition(mPosition).getOrder() != null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_generate_the_bill:

                // Ha habido algún error, se lo notificamos al usuario
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle(String.format(getString(R.string.table_bill_title_msg), Restaurant.getInstance().getTableByPosition(mPosition).getCode()));
                alertDialog.setMessage(String.format(getString(R.string.table_bill_msg), Restaurant.getInstance().getTableByPosition(mPosition).getBill()));
                alertDialog.setPositiveButton(android.R.string.ok, null);
                alertDialog.show();

                return true;
            default:
                return superValue;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnShowMenuClickListener) {
            mOnShowMenuClickListener = (OnShowMenuClickListener) getActivity();
        }
        if (getActivity() instanceof OnOrderItemSelectedListener) {
            mOnOrderItemSelectedListener = (OnOrderItemSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof OnShowMenuClickListener) {
            mOnShowMenuClickListener = (OnShowMenuClickListener) getActivity();
        }
        if (getActivity() instanceof OnOrderItemSelectedListener) {
            mOnOrderItemSelectedListener = (OnOrderItemSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnShowMenuClickListener = null;
        mOnOrderItemSelectedListener = null;
    }
}
