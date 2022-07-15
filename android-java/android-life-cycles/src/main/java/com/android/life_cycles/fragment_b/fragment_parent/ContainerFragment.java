package com.android.life_cycles.fragment_b.fragment_parent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.life_cycles.R;
import com.android.life_cycles.container.ContainerActivity;
import com.android.life_cycles.databinding.FragmentContainerBinding;
import com.android.life_cycles.fragment_b.fragment_child.BottomFragment;
import com.android.life_cycles.fragment_b.fragment_child.TopFragment;
import com.android.life_cycles.utils.Constants;

public class ContainerFragment extends Fragment {

    private FragmentContainerBinding binding;

    private TopFragment topFragment;
    private BottomFragment bottomFragment;

    private int valueTop;
    private int valueBottom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment", "onCreate()...ContainerFragment");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Fragment", "onSaveInstanceState()...ContainerFragment");
        outState.putInt("saveValueTop", valueTop);
        outState.putInt("saveValueBottom", valueBottom);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentContainerBinding.inflate(inflater, container, false);
        Log.d("Fragment", "onCreateView()...ContainerFragment");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Fragment", "onViewCreated()...ContainerFragment");

        showTopFragment();
        showBottomFragment();

        if (savedInstanceState != null) {
            valueTop = savedInstanceState.getInt("saveValueTop");
            valueBottom = savedInstanceState.getInt("saveValueBottom");
        }

        listenerEvent();
        registerEventChild();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume()...ContainerFragment");
        Log.d("test3", "ValueTop of ContainerFragment: " + String.valueOf(valueTop));
        Log.d("test3", "ValueBottom of ContainerFragment: " + String.valueOf(valueBottom));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment", "onDestroyView()...ContainerFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment", "onDestroy()...ContainerFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment", "onDetach()...ContainerFragment");
    }

    //----------------------------------------------------------------------------------------------

    private void listenerEvent() {
        binding.buttonResult.setOnClickListener(view -> openResultFragment());

        binding.buttonBack.setOnClickListener(view -> getParentFragmentManager().popBackStack());
    }

    private void registerEventChild() {
        getChildFragmentManager().setFragmentResultListener(
                Constants.REQUEST_TOP,
                this,
                (requestKey, result) -> valueTop = result.getInt(Constants.KEY_VALUE_TOP));
        getChildFragmentManager().setFragmentResultListener(
                Constants.REQUEST_BOTTOM,
                this,
                (requestKey, result) -> valueBottom = result.getInt(Constants.KEY_VALUE_BOTTOM));
    }

    private void showTopFragment() {
        topFragment = (TopFragment) getChildFragmentManager().findFragmentByTag(TopFragment.class.getName());

        if (topFragment == null) {
            topFragment = new TopFragment();
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerTop, topFragment, TopFragment.class.getName())
                    .addToBackStack(TopFragment.class.getName())
                    .commit();
        }
    }

    private void showBottomFragment() {
        bottomFragment = (BottomFragment) getChildFragmentManager().findFragmentByTag(BottomFragment.class.getName());

        if (bottomFragment == null) {
            bottomFragment = new BottomFragment();
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerBottom, bottomFragment, BottomFragment.class.getName())
                    .addToBackStack(BottomFragment.class.getName())
                    .commit();
        }
    }

    private void openResultFragment() {
        ((ContainerActivity) requireActivity()).showResultFragment();
        int result = sumResult(valueTop, valueBottom);
        Bundle bundleResult = new Bundle();
        bundleResult.putInt(Constants.KEY_RESULT, result);
        getParentFragmentManager().setFragmentResult(Constants.REQUEST_RESULT, bundleResult);
    }

    private int sumResult(int a, int b) {
        return a + b;
    }
}
