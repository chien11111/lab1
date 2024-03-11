package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Mandangnhap extends AppCompatActivity {
    TextView tv_dangkyngay;
    private FirebaseAuth mAuth;
    private TextInputEditText tip_email_login,tip_password_login;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangnhap);

        tv_dangkyngay= findViewById(R.id.tv_dangkyngay);

        tip_email_login= findViewById(R.id.tip_email_login);
        tip_password_login= findViewById(R.id.tip_password_login);
        btn_login=findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        tv_dangkyngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Mandangnhap.this,Mandangky.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=tip_email_login.getText().toString();
                String password = tip_password_login.getText().toString();
                boolean hasError = Validate();

                if (!hasError) {
                    DangNhap(email, password);
                }

            }
        });
    }

    private void DangNhap(String email,String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(Mandangnhap.this, "Đăng nhập thành công",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(Mandangnhap.this,Mandangxuat.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Mandangnhap.this, "Tài khoản mật khẩu chưa chinh xac",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean Validate() {
        String email = tip_email_login.getText().toString();
        String password = tip_password_login.getText().toString();
        boolean hasError = false;

        if (email.equals("")) {
            Toast.makeText(Mandangnhap.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            hasError = true;
        }else if (email.equals("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+")){
            Toast.makeText(Mandangnhap.this, "Bạn nhập sai định dạng email", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")) {
            Toast.makeText(Mandangnhap.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            hasError = true;
        }

        return hasError;
    }
}