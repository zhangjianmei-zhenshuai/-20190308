package zjm.com.yuekao.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zjm.com.yuekao.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        final ImageView img_toux = findViewById( R.id.img_toux );
        TextView tv_start = findViewById( R.id.tv_start );

        //点击开始
        tv_start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //平移
                final ObjectAnimator translationY = ObjectAnimator.ofFloat( img_toux, "translationY", 0.0f, 300.0f );
                translationY.setDuration( 5000 );
                translationY.start();
                //透明度
                final ObjectAnimator alpha = ObjectAnimator.ofFloat( img_toux, "alpha", 1.0f, 0.3f );
                alpha.setDuration( 5000 );
                alpha.start();
                //监听
                translationY.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Float py = (Float) translationY.getAnimatedValue();
                        Float tm = (Float) alpha.getAnimatedValue();
                        img_toux.setTranslationY( py );
                        img_toux.setAlpha( tm );
                    }
                } );//监听
            }
        } );
    }
}
