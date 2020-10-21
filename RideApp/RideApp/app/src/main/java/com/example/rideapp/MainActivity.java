package com.example.rideapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.ContextCompat;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import com.example.rideapp.Classes.User;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_home;
    private Button btn_find;
    private Button btn_add;

    public FragmentHome fragmentHome;
    private FragmentFind fragmentFind;
    private FragmentAdd fragmentAdd;

    private User utilizator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        utilizator = intent.getParcelableExtra("utilizator");

        fragmentHome = new FragmentHome();
        fragmentAdd = new FragmentAdd();
        fragmentFind = new FragmentFind();

        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_find = findViewById(R.id.btn_find);
        btn_find.setOnClickListener(this);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, fragmentHome).commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_home:
                unselectedButtons();
                selectedButton(btn_home);

                Intent intent = new Intent(this, FragmentHome.class);
                intent.putExtra("utilizator", utilizator);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame,fragmentHome)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btn_find:
                unselectedButtons();
                selectedButton(btn_find);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame,fragmentFind)
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.btn_add:
                unselectedButtons();
                selectedButton(btn_add);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame,fragmentAdd)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    private void selectedButton(Button btn) {
        btn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.button_pressed));
    }

    private void unselectedButtons() {
        btn_home.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.button_free));
        btn_find.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.button_free));
        btn_add.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.button_free));
    }

}
