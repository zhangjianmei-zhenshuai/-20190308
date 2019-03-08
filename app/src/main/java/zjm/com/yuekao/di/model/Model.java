package zjm.com.yuekao.di.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zjm.com.yuekao.data.Constant;
import zjm.com.yuekao.di.contract.Contract_Interface;

public class Model implements Contract_Interface.Model_Interface {
    @Override
    public void getJson(final CallBack_Gouwu callBack_gouwu) {
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url( Constant.ZHANSHI_URL )
                .build();
        Call call = build.newCall( request );
        call.enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callBack_gouwu.responseData(message);
            }
        } );
    }
}
