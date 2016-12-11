package com.amarin.mywaiter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.facade.OnDishSelectedListener;
import com.amarin.mywaiter.model.Allergen;
import com.amarin.mywaiter.model.Dish;
import com.amarin.mywaiter.model.Restaurant;

import java.util.LinkedList;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.DishViewHolder> {
    private Context mContext;
    private OnDishSelectedListener mOnDishSelectedListener;

    public MenuRecyclerViewAdapter(Context context, OnDishSelectedListener onDishSelectedListener) {
        super();
        mContext = context;
        mOnDishSelectedListener = onDishSelectedListener;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dish, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, final int position) {
        final Dish dish = Restaurant.getInstance().getDishByPosition(position);
        holder.bindDish(dish, mContext);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnDishSelectedListener != null) {
                    mOnDishSelectedListener.onDishClick(dish, view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Restaurant.getInstance().getMenu().getDishes().size();
    }

    class DishViewHolder extends RecyclerView.ViewHolder {
        TextView mDishName;
        TextView mDishPrice;
        ImageView mDishImage;
        ImageView mDishAllergen1;
        ImageView mDishAllergen2;
        ImageView mDishAllergen3;
        ImageView mDishAllergen4;
        ImageView mDishAllergen5;
        ImageView mDishAllergen6;
        ImageView mDishAllergen7;
        ImageView mDishAllergen8;
        ImageView mDishAllergen9;
        ImageView mDishAllergen10;
        ImageView mDishAllergen11;
        ImageView mDishAllergen12;
        ImageView mDishAllergen13;
        ImageView mDishAllergen14;
        View mView;

        DishViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mDishName = (TextView) itemView.findViewById(R.id.dish_name);
            mDishPrice = (TextView) itemView.findViewById(R.id.dish_price);
            mDishImage = (ImageView) itemView.findViewById(R.id.dish_image);
            mDishAllergen1 = (ImageView) itemView.findViewById(R.id.dish_allergen1);
            mDishAllergen2 = (ImageView) itemView.findViewById(R.id.dish_allergen2);
            mDishAllergen3 = (ImageView) itemView.findViewById(R.id.dish_allergen3);
            mDishAllergen4 = (ImageView) itemView.findViewById(R.id.dish_allergen4);
            mDishAllergen5 = (ImageView) itemView.findViewById(R.id.dish_allergen5);
            mDishAllergen6 = (ImageView) itemView.findViewById(R.id.dish_allergen6);
            mDishAllergen7 = (ImageView) itemView.findViewById(R.id.dish_allergen7);
            mDishAllergen8 = (ImageView) itemView.findViewById(R.id.dish_allergen8);
            mDishAllergen9 = (ImageView) itemView.findViewById(R.id.dish_allergen9);
            mDishAllergen10 = (ImageView) itemView.findViewById(R.id.dish_allergen10);
            mDishAllergen11 = (ImageView) itemView.findViewById(R.id.dish_allergen11);
            mDishAllergen12 = (ImageView) itemView.findViewById(R.id.dish_allergen12);
            mDishAllergen13 = (ImageView) itemView.findViewById(R.id.dish_allergen13);
            mDishAllergen14 = (ImageView) itemView.findViewById(R.id.dish_allergen14);
        }

        void bindDish(Dish dish, Context context) {
            mDishName.setText(dish.getName());
            mDishPrice.setText(String.format(context.getString(R.string.price_format), dish.getPrice().toString()));
            mDishImage.setImageResource(dish.getIcon());

            LinkedList<Allergen> dishAllergens = dish.getAllergens();
            for (int i = 1; i <= 14; i++) {
                int resId = itemView.getResources().getIdentifier("dish_allergen" + i, "id", context.getPackageName());
                ImageView icon = (ImageView) itemView.findViewById(resId);
                if (i <= dishAllergens.size()) {
                    icon.setVisibility(View.VISIBLE);
                    Allergen allergen = dishAllergens.get(i - 1);
                    icon.setImageResource(allergen.getIcon());
                } else {
                    icon.setVisibility(View.INVISIBLE);
                }
            }
        }

        public View getView() {
            return mView;
        }
    }
}
