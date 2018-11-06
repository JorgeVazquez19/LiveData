package com.jv.livedataexample;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Switch swt_work;
    public Switch swt_grade;
    private TextView txt_work;
    private TextView txt_grade;
    private EditText et_age;
    private TextView txt_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swt_work = findViewById(R.id.swt_work);
        swt_grade = findViewById(R.id.swt_grade);
        txt_work = findViewById(R.id.txt_work);
        txt_grade = findViewById(R.id.txt_grade);
        txt_age = findViewById(R.id.txt_age);
        et_age = findViewById(R.id.et_age);


        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<Boolean>();
        mutableLiveData.observe(this, observer1);



        swt_work.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mutableLiveData.postValue(true);

                } else {
                    mutableLiveData.postValue(false);
                }
            }
        });

        final MutableLiveData<Boolean> MutableLiveData2 = new MutableLiveData<Boolean>();
        MutableLiveData2.observe(this, observer2);
        swt_grade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MutableLiveData2.postValue(true);

                } else {
                    MutableLiveData2.postValue(false);
                }
            }
        });

        final MutableLiveData<String> MutableLiveData3 = new MutableLiveData<String>();
        MutableLiveData3.observe(this, observer3);

        et_age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                MutableLiveData3.postValue(et_age.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MutableLiveData3.postValue(et_age.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                MutableLiveData3.postValue(et_age.getText().toString());
            }
        });

    }

    final Observer<Boolean> observer1 = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable final Boolean bool1) {
            if (bool1) txt_work.setText("Si");
            else txt_work.setText("No");

        }
    };


    final Observer<Boolean> observer2 = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable final Boolean bool2) {
            if (bool2) txt_grade.setText("Si");
            else txt_grade.setText("No");

        }
    };
    final Observer<String> observer3 = new Observer<String>() {
        @Override
        public void onChanged(@Nullable final String num) {
            txt_age.setText("Edad: " + num);

        }
    };

}