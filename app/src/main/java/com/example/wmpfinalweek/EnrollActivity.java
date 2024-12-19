package com.example.wmpfinalweek;



import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnrollActivity extends AppCompatActivity {

    private ListView lvSubjects;
    private TextView tvTotalCredits;
    private Button btnSubmitEnrollment;
    private DatabaseReference subjectRef, studentRef;
    private ArrayList<Subject> subjectList;
    private ArrayList<Subject> enrolledSubjects;
    private int totalCredits = 0;
    private final int MAX_CREDITS = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        lvSubjects = findViewById(R.id.lv_subjects);
        tvTotalCredits = findViewById(R.id.tv_total_credits);
        btnSubmitEnrollment = findViewById(R.id.btn_submit_enrollment);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        subjectRef = FirebaseDatabase.getInstance().getReference("subjects");
        studentRef = FirebaseDatabase.getInstance().getReference("students").child(userId);

        subjectList = new ArrayList<>();
        enrolledSubjects = new ArrayList<>();

        loadSubjects();

        lvSubjects.setOnItemClickListener((parent, view, position, id) -> {
            Subject subject = subjectList.get(position);
            if (totalCredits + subject.getCredits() <= MAX_CREDITS) {
                enrolledSubjects.add(subject);
                totalCredits += subject.getCredits();
                tvTotalCredits.setText("Total Credits: " + totalCredits);
            } else {
                Toast.makeText(this, "Cannot exceed 24 credits", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmitEnrollment.setOnClickListener(v -> submitEnrollment());
    }

    private void loadSubjects() {
        // Create a temporary list of subjects
        subjectList = new ArrayList<>();
        subjectList.add(new Subject("Mathematics", 10));
        subjectList.add(new Subject("Physics", 8));
        subjectList.add(new Subject("Chemistry", 4));
        subjectList.add(new Subject("English Literature", 12));
        subjectList.add(new Subject("Computer Science", 10));
        subjectList.add(new Subject("History", 8));
        subjectList.add(new Subject("Biology", 9));

        // Pass the list to the adapter
        SubjectAdapter adapter = new SubjectAdapter(this, subjectList);
        lvSubjects.setAdapter(adapter);
    }


    private void submitEnrollment() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Data to be submitted
        Map<String, Object> enrollmentData = new HashMap<>();
        enrollmentData.put("enrolledSubjects", enrolledSubjects);
        enrollmentData.put("totalCredits", totalCredits);

        // Store in the Firestore collection "students" with the userId as the document ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.collection("students").document(userId)
                .set(enrollmentData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Enrollment submitted successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to submit enrollment", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
