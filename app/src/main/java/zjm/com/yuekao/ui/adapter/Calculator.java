package zjm.com.yuekao.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import zjm.com.yuekao.R;

public class Calculator extends LinearLayout {//加减器
    private int i=1;
    private final TextView tv_num;

    public Calculator(final Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        View view = View.inflate( context, R.layout.calculator,this );
        Button btn_jian = view.findViewById( R.id.btn_jian );
        Button btn_jia = view.findViewById( R.id.btn_jia );
        tv_num = view.findViewById( R.id.tv_num );
        tv_num.setText( i+"" );

        //点击减号
        btn_jian.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i>1) {
                    i--;
                    tv_num.setText( i+"" );
                }else {
                    Toast.makeText( context, "数量不能小于1", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        //点击加号
        btn_jia.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                tv_num.setText( i+"" );
            }
        } );


    }

}
