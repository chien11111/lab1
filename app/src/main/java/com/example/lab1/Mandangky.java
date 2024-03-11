package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Mandangky extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText tip_email_signup,tip_password_signup;

    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangky);

        tip_email_signup= findViewById(R.id.tip_email_dangky);
        tip_password_signup= findViewById(R.id.tip_password_dangky);
        btn_signup=findViewById(R.id.btn_dangky);
        mAuth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=tip_email_signup.getText().toString();
                String password = tip_password_signup.getText().toString();
                boolean hasError = Validate();

                if (!hasError) {
                    DangKy(email,password);
                }


            }
        });
    }
    private void DangKy(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Mandangky.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Mandangky.this, Mandangnhap.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Mandangky.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean Validate(){

        String email = tip_email_signup.getText().toString();
        String password = tip_password_signup.getText().toString();
        boolean hasError = false;

        if (email.equals("")) {
            Toast.makeText(Mandangky.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            hasError = true;
        }else if (email.equals("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+")){
            Toast.makeText(Mandangky.this, "Bạn nhập sai định dạng email", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")) {
            Toast.makeText(Mandangky.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            hasError = true;
        }

        return hasError ;
    }
}