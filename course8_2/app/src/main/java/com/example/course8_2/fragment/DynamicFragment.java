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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.course8_2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends Fragment {
    private static final String TAG = "fragment";

    public static DynamicFragment newInstance(int position, int image_id, String desc) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        args.putString("desc", desc);
        fragment.setArguments(args);
        return fragment;
    }

    private int getPosition() {
        return getArguments().getInt("position", 0);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "fragment onAttach position=" + getPosition());
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DynamicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DynamicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DynamicFragment newInstance(String param1, String param2) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "fragment onCreate position=" + getPosition());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ImageView iv_pic = view.findViewById(R.id.iv_pic);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            iv_pic.setImageResource(arguments.getInt("image_id", R.drawable.huawei));
            tv_desc.setText(arguments.getString("desc"));
        }
        Log.d(TAG, "fragment onCreateView position=" + getPosition());
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "fragment onActivityCreated position=" + getPosition());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"Fragment onStart position=" + getPosition());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Fragment onResume position=" + getPosition());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"Fragment onPause position=" + getPosition());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"Fragment onStop position=" + getPosition());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"Fragment onDestroyView position=" + getPosition());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Fragment onDestroy position=" + getPosition());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"Fragment onDetach position=" + getPosition());
    }
}