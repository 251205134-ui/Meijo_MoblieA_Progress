package jp.ac.meijou.android.meijo_mobliea_progress;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.meijo_mobliea_progress.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String yetList = "aaa,bbb,ccc";//おわてないものリスト
    private String finishedList = "fin";//終わったものリスト

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.title1.setOnClickListener(view -> {
            String title = binding.title1.getText().toString();
            var intent = new Intent(this, MainActivity2.class);
            intent.putExtra("title", title);
            intent.putExtra("yet", yetList);
            intent.putExtra("finished", finishedList);
            getActivityResult.launch(intent);
        });
    }

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        yetList = getIntent().getStringExtra("yet");
                        finishedList = getIntent().getStringExtra("finished");
                    }
                    default -> {

                    }
                }
            }
    );
}