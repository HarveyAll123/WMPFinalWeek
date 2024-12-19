package com.example.wmpfinalweek;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SubjectAdapter extends ArrayAdapter<Subject> {

    public SubjectAdapter(Context context, List<Subject> subjects) {
        super(context, 0, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_subject_adapter, parent, false);
        }
        Subject subject = getItem(position);

        TextView tvSubjectName = convertView.findViewById(R.id.tv_subject_name);
        TextView tvSubjectCredits = convertView.findViewById(R.id.tv_subject_credits);

        tvSubjectName.setText(subject.getName());
        tvSubjectCredits.setText("Credits: " + subject.getCredits());

        return convertView;
    }
}
