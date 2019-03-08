package zjm.com.yuekao.di.presenter;

import java.lang.ref.SoftReference;

import zjm.com.yuekao.di.contract.Contract_Interface;
import zjm.com.yuekao.di.model.Model;

public class Presenter implements Contract_Interface.Presenter_Interface<Contract_Interface.View_Interface> {
    Contract_Interface.View_Interface view_interface;
    private Model model;
    private SoftReference<Model> softReference;

    @Override
    public void attahView(Contract_Interface.View_Interface view_interface) {
        this.view_interface=view_interface;
        //新建M层
        model = new Model();
        //新建软引用
        softReference = new SoftReference<>( model );
    }

    @Override
    public void deathView(Contract_Interface.View_Interface view_interface) {
        //清理内存
        softReference.clear();
    }

    @Override
    public void requestData() {
        model.getJson( new Contract_Interface.Model_Interface.CallBack_Gouwu() {
            @Override
            public void responseData(String message) {
                view_interface.showData(message);
            }
        } );
    }
}
