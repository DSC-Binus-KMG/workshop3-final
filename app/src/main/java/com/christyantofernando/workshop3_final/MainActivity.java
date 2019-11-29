package com.christyantofernando.workshop3_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername;
    TextView tvFeedback;
    Button btnEnter;
    private static int FEEDBACK_REQUEST = 1;
    private static String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etUsername = findViewById(R.id.et_username);
        btnEnter = findViewById(R.id.btn_enter);
        tvFeedback = findViewById(R.id.tv_feedback);
        
        btnEnter.setOnClickListener(this);
    }
    
    @Override
    public void onClick (View view) {
        String username = etUsername.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = QuestionActivity.newIntent(MainActivity.this, username);
        startActivityForResult(intent, FEEDBACK_REQUEST);
    }
    
    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "Result canceled");
        } else if (requestCode == FEEDBACK_REQUEST) {
            if(data == null) return;
            
            String feedback = data.getStringExtra(QuestionActivity.EXTRA_FEEDBACK);
            if(feedback == null || feedback.isEmpty()) return;
            
            tvFeedback.setText(feedback);
        }
    }
}
