package cn.yanglj65.www.ecloth_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import cn.yanglj65.www.ecloth_app.Service.HttpService;


public class AddActivity extends AppCompatActivity {
    private TextView typeText;
    private TextView colorText;
    private TextView sizeText;
    private Toolbar toolbar;
    private String type;
    private String color;
    private String size;
    private boolean usability = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        typeText = findViewById(R.id.CLOTHTYPE);
        colorText = findViewById(R.id.CLOTHCOLOR);
        sizeText = findViewById(R.id.CLOTHSIZE);
        Spinner typeSpinner = findViewById(R.id.SPINNERTYPE);
        Spinner colorSpinner = findViewById(R.id.SPINNERCOLOR);
        Spinner sizeSpinner = findViewById(R.id.SPINNERSIZE);
        toolbar = findViewById(R.id.toolbar);
        RadioGroup usabilityGroup = findViewById(R.id.USABILITYGROUP);
        Intent intent = getIntent();
        final String addClothType = intent.getStringExtra("addClothType");
        final ArrayList<String> clothType = new ArrayList<>();
        switch (addClothType) {
            case "top":
                initTopTypeList(clothType);
                break;
            case "pants":
                initPantsTypeList(clothType);
                break;
            case "shoes":
                initShoesTypeList(clothType);
                break;
            default:
                initHatTypeList(clothType);
                break;
        }
        setSupportActionBar(toolbar);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clothType);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = clothType.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = getResources().getStringArray(R.array.color)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = getResources().getStringArray(R.array.size)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        usabilityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_true:
                        usability = true;
                        break;
                    case R.id.rb_false:
                        usability = false;
                        break;
                    default:
                        usability = true;
                        break;

                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type != null && color != null && size != null) {
                    HttpService.okHttpAddCloth(AddActivity.this, color, type, size, usability, addClothType, view);
                } else {
                    Snackbar.make(view, "请填写完整的衣物信息", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void initTopTypeList(ArrayList<String> clothType) {
        clothType.add("短袖");
        clothType.add("衬衫");
        clothType.add("卫衣");
        clothType.add("大衣");
        clothType.add("裙子");
    }

    private void initPantsTypeList(ArrayList<String> clothType) {
        clothType.add("短裤");
        clothType.add("长裤");
        clothType.add("运动裤");
        clothType.add("牛仔裤");
        clothType.add("短裙");
        toolbar.setTitle("添加裤子至我的衣橱");
        typeText.setText("请选择裤子的类型");
        colorText.setText("请选择裤子的颜色");
        sizeText.setText("请选择裤子的大小");
    }

    private void initShoesTypeList(ArrayList<String> clothType) {
        clothType.add("拖鞋");
        clothType.add("凉鞋");
        clothType.add("运动鞋");
        clothType.add("高跟鞋");
        toolbar.setTitle("添加鞋子至我的衣橱");
        typeText.setText("请选择鞋子的类型");
        colorText.setText("请选择鞋子的颜色");
        sizeText.setText("请选择鞋子的大小");
    }

    private void initHatTypeList(ArrayList<String> clothType) {
        clothType.add("鸭舌帽");
        clothType.add("遮阳帽");
        toolbar.setTitle("添加帽子至我的衣橱");
        typeText.setText("请选择帽子的类型");
        colorText.setText("请选择帽子的颜色");
        sizeText.setText("请选择帽子的大小");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("fragment", "cloth");
        startActivity(intent);
    }
}
