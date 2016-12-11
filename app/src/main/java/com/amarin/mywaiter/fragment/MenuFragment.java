package com.amarin.mywaiter.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.amarin.mywaiter.R;
import com.amarin.mywaiter.adapter.MenuRecyclerViewAdapter;
import com.amarin.mywaiter.facade.OnDishSelectedListener;
import com.amarin.mywaiter.model.Allergen;
import com.amarin.mywaiter.model.Dish;
import com.amarin.mywaiter.model.Restaurant;
import com.amarin.mywaiter.model.Table;
import com.amarin.mywaiter.utils.MyWaiterConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class MenuFragment extends Fragment {

    protected ViewSwitcher mViewSwitcher;
    protected RecyclerView mList;

    protected OnDishSelectedListener mOnDishSelectedListener;

    protected Table mTable;

    public static MenuFragment newInstance(Table table) {
        MenuFragment fragment = new MenuFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(MyWaiterConstants.ARG_TABLE, table);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTable = (Table) getArguments().getSerializable(MyWaiterConstants.ARG_TABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        mViewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        mList = (RecyclerView) root.findViewById(android.R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(new MenuRecyclerViewAdapter(getActivity(), mOnDishSelectedListener));

        // Actualizo la interfaz con el modelo
        updateViews();

        return root;
    }

    private void updateViews() {
        if (Restaurant.getInstance().getMenu() == null) {
            downloadMenu();
        } else {
            mViewSwitcher.setDisplayedChild(MyWaiterConstants.MENU_VIEW_INDEX);
            mList.setAdapter(new MenuRecyclerViewAdapter(getActivity(), mOnDishSelectedListener));
        }
    }

    private void downloadMenu() {

        AsyncTask<Void, Integer, LinkedList<Dish>> menuDownloader = new AsyncTask<Void, Integer, LinkedList<Dish>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mViewSwitcher.setDisplayedChild(MyWaiterConstants.LOADING_VIEW_INDEX);
            }

            @Override
            protected LinkedList<Dish> doInBackground(Void... voids) {

                try {
                    URL url = new URL(MyWaiterConstants.URL_API_MENU);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    InputStream input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));
                    }

                    if (isCancelled()) {
                        return null;
                    }

                    JSONObject jsonRoot = new JSONObject(sb.toString());
                    JSONArray jsonDishes = jsonRoot.getJSONArray(MyWaiterConstants.JSON_ATT_DATA);

                    LinkedList<Dish> dishes = new LinkedList<>();

                    for (int i = 0; i < jsonDishes.length(); i++) {
                        JSONObject jsonDish = jsonDishes.getJSONObject(i);
                        String dishName = jsonDish.getString(MyWaiterConstants.JSON_ATT_NAME);
                        String dishDescription = jsonDish.getString(MyWaiterConstants.JSON_ATT_DESCRIPTION);
                        BigDecimal dishPrice = new BigDecimal(jsonDish.getString(MyWaiterConstants.JSON_ATT_PRICE));

                        String dishIcon = jsonDish.getString(MyWaiterConstants.JSON_ATT_ICON);
                        int dishIconResource = -1;
                        if (dishIcon.equals(MyWaiterConstants.CALDO_GALLEGO)) {
                            dishIconResource = R.drawable.caldo_gallego;
                        } else if (dishIcon.equals(MyWaiterConstants.CALLOS)) {
                            dishIconResource = R.drawable.callos;
                        } else if (dishIcon.equals(MyWaiterConstants.COCIDO_GALLEGO)) {
                            dishIconResource = R.drawable.cocido_gallego;
                        } else if (dishIcon.equals(MyWaiterConstants.EMPANADA)) {
                            dishIconResource = R.drawable.empanada;
                        } else if (dishIcon.equals(MyWaiterConstants.LACON_CON_GRELOS)) {
                            dishIconResource = R.drawable.lacon_con_grelos;
                        } else if (dishIcon.equals(MyWaiterConstants.PAN_GALLEGO)) {
                            dishIconResource = R.drawable.pan_gallego;
                        } else if (dishIcon.equals(MyWaiterConstants.PULPO_A_LA_GALLEGA)) {
                            dishIconResource = R.drawable.pulpo_a_la_gallega;
                        }

                        LinkedList<Allergen> allergens = new LinkedList<>();
                        JSONArray jsonAllergens = jsonDish.getJSONArray(MyWaiterConstants.JSON_ATT_ALLERGENS);
                        for (int j = 0; j < jsonAllergens.length(); j++) {
                            JSONObject jsonAllergen = jsonAllergens.getJSONObject(j);
                            String allergenName = jsonAllergen.getString(MyWaiterConstants.JSON_ATT_ALLERGEN_NAME);

                            String allergenIcon = jsonAllergen.getString(MyWaiterConstants.JSON_ATT_ALLERGEN_ICON);
                            int allergenIconResource = -1;
                            if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_ALTRAMUZ)) {
                                allergenIconResource = R.drawable.allergen_altramuz;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_APIO)) {
                                allergenIconResource = R.drawable.allergen_apio;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_CACAHUETES)) {
                                allergenIconResource = R.drawable.allergen_cacahuetes;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_CEREAL_CON_GLUTEN)) {
                                allergenIconResource = R.drawable.allergen_cereal_con_gluten;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_CRUSTACEOS)) {
                                allergenIconResource = R.drawable.allergen_crustaceos;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_FRUTOS_SECOS)) {
                                allergenIconResource = R.drawable.allergen_frutos_secos;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_HUEVOS)) {
                                allergenIconResource = R.drawable.allergen_huevos;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_LACTEOS)) {
                                allergenIconResource = R.drawable.allergen_lacteos;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_MOLUSCOS)) {
                                allergenIconResource = R.drawable.allergen_moluscos;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_MOSTAZA)) {
                                allergenIconResource = R.drawable.allergen_mostaza;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_PESCADO)) {
                                allergenIconResource = R.drawable.allergen_pescado;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_SESAMO)) {
                                allergenIconResource = R.drawable.allergen_sesamo;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_SOJA)) {
                                allergenIconResource = R.drawable.allergen_soja;
                            } else if (allergenIcon.equals(MyWaiterConstants.ALLERGEN_SULFITOS)) {
                                allergenIconResource = R.drawable.allergen_sulfitos;
                            }

                            allergens.add(new Allergen(allergenName, allergenIconResource));
                        }

                        dishes.add(new Dish(dishName, dishDescription, dishIconResource, dishPrice, allergens));
                    }

                    return dishes;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(LinkedList<Dish> dishes) {
                super.onPostExecute(dishes);

                if (dishes != null) {
                    Restaurant.getInstance().setMenu(dishes);
                    // Actualizo la interfaz
                    updateViews();
                } else {
                    // Ha habido algún error, se lo notificamos al usuario
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle(R.string.error);
                    alertDialog.setMessage(R.string.menu_download_error_msg);
                    alertDialog.setPositiveButton(R.string.menu_retry_download_msg, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadMenu();
                        }
                    });

                    alertDialog.show();
                }
            }
        };


        menuDownloader.execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnDishSelectedListener = null;
    }
}
