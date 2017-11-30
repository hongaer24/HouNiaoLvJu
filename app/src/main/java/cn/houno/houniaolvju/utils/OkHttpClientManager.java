package cn.houno.houniaolvju.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.houno.houniaolvju.R;


/**
 * okhttp网络请求管理
 * <p/>
 * Created by zcc on 2015/10/25.
 */
public class OkHttpClientManager {
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;

    private OkHttpClientManager() {
        mOkHttpClient = new OkHttpClient();
        // 设置连接超时10s
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        // 设置写超时30s
        //        mOkHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
        // 设置读取超时60s
        //        mOkHttpClient.setReadTimeout(60,TimeUnit.SECONDS);
        // 缓存cookie
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    //***************************************对外开放接口*************************************************

    /**
     * 异步请求get
     *
     * @param url     请求路径
     * @param tag     cancel标签，可以为null
     * @param handler
     * @param whatId  取数据成功时，handler返回的what
     */
    public static void getAsync(String url, String tag, Handler handler, int whatId) {
        getInstance()._getAsync(url, handler, whatId, tag);
    }

    /**
     * 异步请求post
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param tag     cancel标签，可以为null
     * @param handler
     * @param whatId  取数据成功时，handler返回的what
     */
    public static void postAsync(String url, Param[] params, String tag, Handler handler, int whatId) {
        getInstance()._postAsync(url, params, handler, whatId, tag);
    }

    /**
     * 异步请求Post
     *
     * @param url       请求路径
     * @param map       请求参数
     * @param tag       cancel标签，可以为null
     * @param handler
     * @param succeedId 取数据成功时，handler返回的what
     */
    public static void postAsync(String url, Map<String, String> map, String tag, Handler handler, int succeedId) {
        getInstance()._postAsync(url, map, handler, succeedId, tag);
    }

    /**
     * @param url       异步请求Post
     * @param map       请求参数
     * @param tag       cancel标签，可以为null
     * @param handler
     * @param succeedId 取数据成功时，handler返回的what
     * @param failId    取数据失败时，handler返回的what
     */
    public static void postAsync(String url, Map<String, String> map, String tag, Handler handler, int succeedId, int failId) {
        getInstance()._postAsync(url, map, handler, succeedId, failId, tag);
    }

    /**
     * @param url       异步请求Post
     * @param params    请求参数
     * @param tag       cancel标签，可以为null
     * @param handler
     * @param succeedId 取数据成功时，handler返回的what
     * @param failId    取数据失败时，handler返回的what
     */
    public static void postAsync(String url, Param[] params, String tag, Handler handler, int succeedId, int failId) {
        getInstance()._postAsync(url, params, handler, succeedId, failId, tag);
    }

    /**
     * 取消请求
     *
     * @param tag 标签
     */
    public static void cancel(String tag) {
        getInstance()._cancel(tag);
    }
    //**************************************************************************************************


    public void _getAsync(String url, final Handler handler, final int whatId, String tag) {
        Request request = null;
        if (TextUtils.isEmpty(tag)) {
            request = new Request.Builder().url(url).build();
        } else {
            request = new Request.Builder().tag(tag).url(url).build();
        }
        deliveryResult(request, handler, whatId);
    }


    public void _postAsync(String url, Param[] params, Handler handler, int whatId, String tag) {
        Request request = buildMultipartFormRequest(url, params, tag);
        deliveryResult(request, handler, whatId);
    }

    public void _postAsync(String url, Param[] params, Handler handler, int succeedId, int failId, String tag) {
        Request request = buildMultipartFormRequest(url, params, tag);
        deliveryResult(request, handler, succeedId, failId);
    }

    private void _postAsync(String url, Map<String, String> map, Handler handler, int whatId, String tag) {
        Param[] params = mapToParam(map);
        _postAsync(url, params, handler, whatId, tag);
    }

    private void _postAsync(String url, Map<String, String> map, Handler handler, int succeedId, int failId, String tag) {
        Param[] params = mapToParam(map);
        _postAsync(url, params, handler, succeedId, failId, tag);
    }

    private void _cancel(String tag) {
        mOkHttpClient.cancel(tag);
    }

    private Request buildMultipartFormRequest(String url, Param[] params, String tag) {
        if (params == null) {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        Request request = null;
        if (TextUtils.isEmpty(tag)) {
            request = new Request.Builder().url(url).post(builder.build()).build();
        } else {
            request = new Request.Builder().tag(tag).url(url).post(builder.build()).build();
        }
        return request;
    }

    private void deliveryResult(final Request request, final Handler handler, final int whatId) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (handler != null) {
                    Message message = Message.obtain();
                    message.what = R.id.doFail;
                    message.obj = e;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String result = response.body().string();
                if (handler != null) {
                    Message message = Message.obtain();
                    message.what = whatId;
                    message.obj = result;
                    handler.sendMessage(message);
                }
            }
        });
    }

    private void deliveryResult(final Request request, final Handler handler, final int succeedId, final int failId) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (handler != null) {
                    Message message = Message.obtain();
                    message.what = failId;
                    message.obj = e;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String result = response.body().string();
                if (handler != null) {
                    Message message = Message.obtain();
                    message.what = succeedId;
                    message.obj = result;
                    handler.sendMessage(message);
                }
            }
        });
    }

    /**
     * Map集合数据转换成Param
     *
     * @param map
     * @return
     */
    private Param[] mapToParam(Map<String, String> map) {
        if (map == null) {
            return new Param[0];
        }
        int size = map.size();
        Param[] params = new Param[size];
        int num = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Param param = new Param(entry.getKey(), entry.getValue());
            params[num++] = param;
        }
        return params;
    }

    /**
     * 键值对
     */
    public static class Param {
        String key;
        String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }
}
