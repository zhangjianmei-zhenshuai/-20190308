package zjm.com.yuekao.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.yuekao.R;
import zjm.com.yuekao.data.Shop_Bean;

public class Good_Adapter extends BaseQuickAdapter<Shop_Bean.DataBean.ListBean,BaseViewHolder> {
    public Good_Adapter(int layoutResId, @Nullable List<Shop_Bean.DataBean.ListBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Shop_Bean.DataBean.ListBean item) {
        CheckBox cb_good = helper.getView( R.id.cb_good );
        cb_good.setOnCheckedChangeListener( null );
       //cb_good.setChecked( item.getChoice_good() );
        helper.setText( R.id.tv_good_title,item.getTitle() );
        helper.setText( R.id.tv_good_price,item.getPrice()+"" );
        ImageView img_pic = helper.getView( R.id.img_pic );
        Glide.with( mContext ).load( item.getDetailUrl() ).into( img_pic );
    }
}
