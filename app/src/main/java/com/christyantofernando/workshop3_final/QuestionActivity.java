package com.christyantofernando.workshop3_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private static String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static String EXTRA_FEEDBACK = "EXTRA_FEEDBACK";
    
    Button btnSubmit;
    EditText etFeedback;
    TextView tvUsername;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        
        btnSubmit = findViewById(R.id.btn_submit);
        etFeedback = findViewById(R.id.et_feedback);
        tvUsername = findViewById(R.id.tv_username);
        btnSubmit.setOnClickListener(this);
        
        Intent intent = getIntent();
        tvUsername.setText(intent.getStringExtra(EXTRA_USERNAME));
    }
    
    public static Intent newIntent(Context packageContext, String username){
        Intent intent = new Intent(packageContext, QuestionActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }
    
    @Override
    public void onClick (View view) {
        String feedback = etFeedback.getText().toString();
        if(feedback.isEmpty()){
            Toast.makeText(this, "Feedback is still empty", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FEEDBACK, feedback);
        setResult(RESULT_OK, intent);
        finish();
    }
}
