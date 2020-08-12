package com.destinyapp.coffeeprogrammer.Activity.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Akun.AkunFragment;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Home.HomeFragment;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Linimasa.LinimasaFragment;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran.PelajaranFragment;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pencapaian.PencapaianFragment;
import com.destinyapp.coffeeprogrammer.R;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    TextView item;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spaceNavigationView=findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        fragment = new LinimasaFragment();
        ChangeFragment(fragment);
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_linimasa));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_pelajaran));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_achivement));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_account));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                fragment = new HomeFragment();
                ChangeFragment(fragment);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                SelectedBottomNavigation(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                SelectedBottomNavigation(itemIndex);
            }
        });
    }
    private void SelectedBottomNavigation(int itemIndex){
        if (itemIndex==0){
            fragment = new LinimasaFragment();
        }else if(itemIndex==1){
            fragment = new PelajaranFragment();
        }else if(itemIndex==2){
            fragment = new PencapaianFragment();
        }else if(itemIndex==3){
            fragment = new AkunFragment();
        }
        ChangeFragment(fragment);
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }
}