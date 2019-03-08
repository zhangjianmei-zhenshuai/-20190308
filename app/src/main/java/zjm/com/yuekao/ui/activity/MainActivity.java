package zjm.com.yuekao.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import zjm.com.yuekao.R;
import zjm.com.yuekao.data.Shop_Bean;
import zjm.com.yuekao.di.contract.Contract_Interface;
import zjm.com.yuekao.di.presenter.Presenter;
import zjm.com.yuekao.ui.adapter.Rv_Adapter;

public class MainActivity extends AppCompatActivity implements Contract_Interface.View_Interface {

    private Presenter presenter;
    private RecyclerView rv;
    private CheckBox cb_qx;
    private List<Shop_Bean.DataBean> list_name;
    private TextView tv_jiesuan;
    private Rv_Adapter rv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        rv = findViewById( R.id.rv );
        cb_qx = findViewById( R.id.cb_qx );
        cb_qx.setOnCheckedChangeListener( null );//避免焦点抢占
        tv_jiesuan = findViewById( R.id.tv_jiesuan );

        //新建P层  绑定 销毁解绑 M层
        presenter = new Presenter();
        presenter.attahView( this );
        presenter.requestData();

        //全选按钮
        quanxuan();
        //点击去结算
        tv_jiesuan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity( intent );
            }
        } );
    }




    //全选按钮
    private void quanxuan() {
        cb_qx.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText( MainActivity.this, ""+isChecked, Toast.LENGTH_SHORT ).show();
                for (int i = 0; i <list_name.size() ; i++) {
                    //一级商家cb
                    list_name.get( i ).setChoice_busine( isChecked );
                    for (int j = 0; j < list_name.get( i ).getList().size(); j++) {
                        //二级商品cb
                        list_name.get( i ).getList().get( j ).setChoice_good( isChecked );
                    }
                }
                //rv_adapter.notifyDataSetChanged();//刷新适配器

            }
        } );
    } //全选按钮

    @Override
    public void showData(final String message) {
        runOnUiThread( new Runnable() {

            private Rv_Adapter rv_adapter;

            @Override
            public void run() {
               // Toast.makeText( MainActivity.this, ""+message, Toast.LENGTH_SHORT ).show();
                Gson gson = new Gson();
                Shop_Bean shop_bean = gson.fromJson( message, Shop_Bean.class );
                //商家名称的数据源
                list_name = shop_bean.getData();
                //设置布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager( MainActivity.this, LinearLayoutManager.VERTICAL, false );
                rv.setLayoutManager( layoutManager );

                rv_adapter = new Rv_Adapter( R.layout.item_rv, list_name );
                rv.setAdapter( rv_adapter );
            }
        } );
    }

    //销毁时解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deathView( this );
    }
}
