package com.day.webview;

import okhttp3.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OkHttp3Util {

  
    /**
     * 懒汉 安全 加同步
     * 私有的静态成员变量 只声明不创建
     * 私有的构造方法
     * 提供返回实例的静态方法
     */
    private static OkHttpClient okHttpClient = null;

    private OkHttp3Util() {
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            //加同步安全
            synchronized (OkHttp3Util.class) {
                if (okHttpClient == null) {
                    //okhttp可以缓存数据....指定缓存路径
                    //指定缓存大小
                    int cacheSize = 10 * 1024 * 1024;
                    okHttpClient = new OkHttpClient.Builder()//构建器
                            .connectTimeout(15 * 1000, TimeUnit.MILLISECONDS)//连接超时
                            .writeTimeout(15 * 1000, TimeUnit.MILLISECONDS)//写入超时
                            .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)//读取超时
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * get请求
     * 参数1 url
     * 参数2 回调Callback
     */
    public static String doGet(String url) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //创建Request
        Request request = new Request.Builder().url(url).build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行同步请求
        try{
            Response response=call.execute();
            if(response.code()==200){
                String json=response.body().string();
                return json;
            }
        }catch (Exception e){

        }
        return null;
    }

    public static void doGet(String url, Map<String, String> params, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //创建Request
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url)
                .newBuilder();
        if (params != null && params.size() > 0) {
            Set<String> strings = params.keySet();
            for (String key : strings) {
                urlBuilder.addQueryParameter(key, params.get(key));
            }
        }
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);
    }

    /**
     * post请求
     * 参数1 url
     * 参数2 Map<String, String> params post请求的时候给服务器传的数据
     * add..("","")
     * add()
     */
    public static String doPost(String url, Map<String, String> params) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        //创建Request
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        try{
            Response response=call.execute();
            if(response.code()==200){
                String json=response.body().string();
                return json;
            }
        }catch (Exception e){

        }
        return "";
    }

    public static String doPostSync(String url, Map<String, String> params) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        //创建Request
        Request request = new Request.Builder().url(url)
                .post(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            return response.isSuccessful() ? response.body().string() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求上传文件....包括图片....流的形式传任意文件...
     * 参数1 url
     * file表示上传的文件
     * fileName....文件的名字,,例如aaa.jpg
     * params ....传递除了file文件 其他的参数放到map集合
     */
    public static void uploadFile(String url, File file, String fileName, Map<String, String> params) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        //文件...参数name指的是请求路径中所接受的参数...如果路径接收参数键值是fileeeee,此处应该改变
        builder.addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), file));
        //构建
        MultipartBody multipartBody = builder.build();
        //创建Request
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        //得到Call
        Call call = okHttpClient.newCall(request);
        //执行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //上传成功回调 目前不需要处理
                if (response.isSuccessful()) {
                    String s = response.body().string();
                }
            }
        });
    }


    /**
     * 上传多张图片及参数
     *
     * @param url     URL地址
     * @param params  参数
     * @param pic_key 上传图片的关键字
     * @param files   图片路径
     */
    public static void sendMultipart(String url, Map<String, String> params, String pic_key, List<File> files) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        if (files != null) {
            for (File file : files) {
                builder.addFormDataPart(pic_key, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }
        //构建
        MultipartBody multipartBody = builder.build();
        //创建Request
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        //得到Call
        Call call = okHttpClient.newCall(request);
        //执行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //上传成功回调 目前不需要处理
                if (response.isSuccessful()) {
                    String s = response.body().string();
                    System.out.println("上传---------------" + s);
                }
            }
        });
    }

    /**
     * 上传多张图片及参数
     *
     * @param url     URL地址
     * @param params  参数
     * @param pic_key 上传图片的关键字
     * @param files   图片路径
     */
    public static void sendMultipart2(String url, Map<String, String> params, String pic_key, List<File> files, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        if (files != null) {
            for (File file : files) {
                builder.addFormDataPart(pic_key, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }
        //构建
        MultipartBody multipartBody = builder.build();
        //创建Request
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        //得到Call
        Call call = okHttpClient.newCall(request);
        //执行请求
        call.enqueue(callback);
    }

    /**
     * 上传多张图片及参数
     *
     * @param url     URL地址
     * @param params  参数
     * @param pic_key 上传图片的关键字
     * @param files   图片路径
     */
    public static void sendSolaMultipart(String url, Map<String, String> params, String pic_key, File files, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
        if (files != null) {
            builder.addFormDataPart(pic_key, files.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), files));
        }
        //构建
        MultipartBody multipartBody = builder.build();
        //创建Request
        Request request = new Request.Builder().url(url).post(multipartBody).build();
        //得到Call
        Call call = okHttpClient.newCall(request);
        //执行请求
        call.enqueue(callback);
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static void doPostJson(String url, String jsonParams, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody)
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    public static void doPostJsonLocal(String url, String jsonParams, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody)
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .build();
        Call call = getInstance().newBuilder()
                .connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)//连接超时
                .writeTimeout(15 * 1000, TimeUnit.MILLISECONDS)//写入超时
                .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)//读取超时
                .build()
                .newCall(request);
        call.enqueue(callback);
    }


    public static Integer downloadSync(final String url,final String saveDir){
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        try{
            Response response=call.execute();
            if(response!=null&&response.code()!=200){
                return response.code();
            }
            InputStream is = null;
            byte[] buf = new byte[2048];
            int len = 0;
            FileOutputStream fos = null;
            try {
                is = response.body().byteStream();//以字节流的形式拿回响应实体内容
                //apk保存路径
                //文件
                File file = new File(saveDir, getNameFromUrl(url));
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
        }catch (Exception e){

        }
        return  200;
    }
    /**
     * 下载文件 以流的形式把apk写入的指定文件 得到file后进行安装
     * 参数er：请求Url
     * 参数san：保存文件的文件夹....download
     */
    public static void download( final String url, final String saveDir) {
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();//以字节流的形式拿回响应实体内容
                    //apk保存路径
                    //文件
                    File file = new File(saveDir, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                }
            }
        });
    }



    /**
     * 下载文件 以流的形式把apk写入的指定文件 得到file后进行安装
     * 参数er：请求Url
     * 参数san：保存文件的文件夹....download
     */
    public static void download2(final String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }



    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
