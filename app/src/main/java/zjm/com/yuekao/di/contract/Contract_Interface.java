package zjm.com.yuekao.di.contract;

//总的接口
public interface Contract_Interface {
    //V层
    public interface View_Interface{
        //数据展示
        public void showData(String message);
    }

    //P层
    public interface Presenter_Interface<View_Interface>{
        //绑定
        public void attahView(View_Interface view_interface);
        //解绑
        public void deathView(View_Interface view_interface);
        //交给m
        public void requestData();
    }

    //M层
    public interface Model_Interface{
        //网络请求数据
        public void getJson(CallBack_Gouwu callBack_gouwu);

        public interface CallBack_Gouwu{
            public void responseData(String message);
        }
    }
}
