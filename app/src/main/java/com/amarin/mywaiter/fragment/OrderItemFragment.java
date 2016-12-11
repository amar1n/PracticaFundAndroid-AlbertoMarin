package com.amarin.mywaiter.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnOrderItemResultListener;
import com.amarin.mywaiter.model.Allergen;
import com.amarin.mywaiter.model.OrderItem;
import com.amarin.mywaiter.utils.MyWaiterConstants;

import java.util.LinkedList;

public class OrderItemFragment extends Fragment {

    protected OrderItem mOrderItem;
    protected OnOrderItemResultListener mOnOrderItemResultListener;

    public static OrderItemFragment newInstance(OrderItem orderItem) {
        OrderItemFragment fragment = new OrderItemFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(MyWaiterConstants.ARG_ORDER_ITEM, orderItem);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOrderItem = (OrderItem) getArguments().getSerializable(MyWaiterConstants.ARG_ORDER_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_order_item, container, false);

        TextView dishName = (TextView) root.findViewById(R.id.dish_name);
        TextView dishPrice = (TextView) root.findViewById(R.id.dish_price);
        ImageView dishImage = (ImageView) root.findViewById(R.id.dish_image);
        TextView dishDescription = (TextView) root.findViewById(R.id.dish_description);
        TextView mDishNotes = (TextView) root.findViewById(R.id.dish_notes);

        dishName.setText(mOrderItem.getDish().getName());
        dishPrice.setText(String.format(getString(R.string.price_format), mOrderItem.getDish().getPrice().toString()));
        dishImage.setImageResource(mOrderItem.getDish().getIcon());
        LinkedList<Allergen> dishAllergens = mOrderItem.getDish().getAllergens();
        for (int i = 1; i <= 14; i++) {
            int resId = getResources().getIdentifier("dish_allergen" + i, "id", getActivity().getPackageName());
            ImageView icon = (ImageView) root.findViewById(resId);
            if (i <= dishAllergens.size()) {
                icon.setVisibility(View.VISIBLE);
                Allergen allergen = dishAllergens.get(i - 1);
                icon.setImageResource(allergen.getIcon());
            } else {
                icon.setVisibility(View.INVISIBLE);
            }
        }
        dishDescription.setText(mOrderItem.getDish().getDescription());
        mDishNotes.setText((mOrderItem != null ? mOrderItem.getNote() : null));

        root.findViewById(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnOrderItemResultListener != null) {
                    TextView textView = (TextView) view.getRootView().findViewById(R.id.dish_notes);
                    CharSequence text = textView.getText();
                    String notes = text != null ? (text.length() == 0 ? text.toString() : null) : null;
                    mOrderItem.setNote(notes);
                    mOnOrderItemResultListener.onButtonClick(MyWaiterConstants.RESULT_OK, mOrderItem);
                }
            }
        });

        root.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnOrderItemResultListener != null) {
                    mOnOrderItemResultListener.onButtonClick(MyWaiterConstants.RESULT_CANCEL, null);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnOrderItemResultListener) {
            mOnOrderItemResultListener = (OnOrderItemResultListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof OnOrderItemResultListener) {
            mOnOrderItemResultListener = (OnOrderItemResultListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnOrderItemResultListener = null;
    }
}
