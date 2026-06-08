package com.example.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class HideTextWatcher implements TextWatcher {
    private final EditText mView;
    private final int mMaxLength;

    public HideTextWatcher(EditText view, int maxLength) {
        this.mView = view;
        this.mMaxLength = maxLength;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        if (s != null && s.length() == mMaxLength) {
            InputMethodManager imm = (InputMethodManager)
                    mView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
            }
        }
    }
}
