package com.example.andro.hackfacebook;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
Button b,n;
Process process;
    String ans;
    EditText t;
    FolderPath x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        n=findViewById(R.id.button2);
        t=findViewById(R.id.editText);
        x=new FolderPath(MainActivity.this) {

            @Override
            void OK() {
                t.setText(x.getpath());ans=x.getpath();
            }
        };
        try {
            process=Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
               b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              x.create();

            }

        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    process.getOutputStream().write(("cp "+ans+" /data/data/com.facebook.lite/shared_prefs\n").getBytes());
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

