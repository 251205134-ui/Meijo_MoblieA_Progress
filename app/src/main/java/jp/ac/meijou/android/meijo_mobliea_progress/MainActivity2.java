package jp.ac.meijou.android.meijo_mobliea_progress;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Optional;

import jp.ac.meijou.android.meijo_mobliea_progress.databinding.ActivityMain2Binding;

//import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    static class ProgressItem{
        public String name;
        public boolean isFinished;
        public ProgressItem(String name,boolean isFinished){
            this.name=name;
            this.isFinished=isFinished;
        }
    }
    static class ProgressViewItem{
        public TextView nameView;
        public Button toggleProgress;

        public ProgressViewItem(TextView text,Button toggleButton){
            nameView=text;
            toggleProgress=toggleButton;
        }
    }
    private ActivityMain2Binding binding;
    private ArrayList<ProgressItem> items=new ArrayList<ProgressItem>();

    private String title;
    private String detail="とっても細かな説明!"; // test

    private ArrayList<ProgressViewItem> view=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main2);

        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Intent受け取り.
//        Optional<String> titleIntent= Optional.ofNullable(getIntent().getStringExtra("title"));
//        if(titleIntent.isPresent()){
//            title=titleIntent.get();
//        }
//        else {
//            title="Error: Title was not sent";
//        }
        String[] finishedItems={},yetItems={};
//        var finishedIntent= Optional.ofNullable(getIntent().getStringExtra("finished"));
//        var yetIntent= Optional.ofNullable(getIntent().getStringExtra("yet"));
//        if(finishedIntent.isPresent()){
//            finishedItems=finishedIntent.get().split(",");
//        }
//        if(yetIntent.isPresent()){
//            yetItems=yetIntent.get().split(",");
//        }

        // debug
        finishedItems=new String[]{"f1","f2"};
        yetItems=new String[]{"y1","y2"};

        // Itemsの配列保持.
        for(int i=0;i<finishedItems.length;++i){
            items.add(new ProgressItem(finishedItems[i],true));
        }
        for(int i=0;i<yetItems.length;++i){
            items.add(new ProgressItem(yetItems[i],false));
        }

        // ビューアイテムのリスト保持.
        view.add(new ProgressViewItem(binding.proView1,binding.proButton1));
        view.add(new ProgressViewItem(binding.proView2,binding.proButton2));
        view.add(new ProgressViewItem(binding.proView3,binding.proButton3));
        view.add(new ProgressViewItem(binding.proView4,binding.proButton4));

        for(int i=0;i<view.size();++i){
            if(i>=items.size())break;
            var v=view.get(i);
            var item=items.get(i);
            v.nameView.setText((item.isFinished?"> ":"")+ item.name);
            v.toggleProgress.setOnClickListener(view->{
                item.isFinished=!item.isFinished;
            });
        }



    }




}