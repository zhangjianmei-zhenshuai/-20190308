package zjm.com.yuekao.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zjm.com.yuekao.R;
import zjm.com.yuekao.data.Shop_Bean;

public class Rv_Adapter extends BaseQuickAdapter<Shop_Bean.DataBean,BaseViewHolder> {
    public Rv_Adapter(int layoutResId, @Nullable List<Shop_Bean.DataBean> data) {
        super( layoutResId, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, Shop_Bean.DataBean item) {
        helper.setText( R.id.tv_name,item.getSellerName()+"" );//商家名称赋值
        CheckBox cb_bissnie = helper.getView( R.id.cb_bissnie );
        //cb_bissnie.setChecked( item.getChoice_busine() );
        cb_bissnie.setOnCheckedChangeListener( null );//避免焦点抢占
        RecyclerView rv_good = helper.getView( R.id.rv_good );


        //商品数据源
        List<Shop_Bean.DataBean.ListBean> list = item.getList();
        //设置适配器布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager( mContext, LinearLayoutManager.VERTICAL, false );
        rv_good.setLayoutManager( layoutManager );
        Good_Adapter adapter = new Good_Adapter( R.layout.item_good, list );
        rv_good.setAdapter( adapter );
    }
}
