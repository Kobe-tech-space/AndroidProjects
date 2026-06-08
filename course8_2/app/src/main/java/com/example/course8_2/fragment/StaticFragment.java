package com.example.course8_2.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.course8_2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {
    private static final String TAG = "fragment";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"Fragment onAttach");
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StaticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaticFragment newInstance(String param1, String param2) {
        StaticFragment fragment = new StaticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Fragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"Fragment onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_static, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"Fragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"Fragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Fragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"Fragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"Fragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"Fragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"Fragment onDetach");
    }
}