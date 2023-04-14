package com.example.vehicledrivergui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vehicledrivergui.fragments.ControlsFragment;
import com.example.vehicledrivergui.fragments.SensorsFragment;
import com.example.vehicledrivergui.fragments.SettingsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1 -> {
                return new SensorsFragment();
            }
            case 2 -> {
                return new SettingsFragment();
            }
            default -> {
                return new ControlsFragment();
            }
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
