
我只写一下retrofit基本模板，此处只写GET和POST相关

没看过[Retrofit2 实战（一、使用详解篇） - 掘金 (juejin.cn)](https://juejin.cn/post/6978777076073660429)的要去看一下

retrofit发送请求需要四块代码

###### 第一块 你的API抽象接口（建议写一个单独的Java文件里）

```java
public interface API {
    @GET("api/user/detail")//这里是相应请求的url，会接在第二块代码retrofit实例化填的域名或ip地址后面连成完整地址
    Call<PersonalDetailData> getPersonalDetail(@Header("Authorization") String token);
    /*上面这行是抽象请求函数，无需自己实例化，retrofit实例化时会自动帮你实例化。
    Call<Type>中的Type是自己写的数据类，里面的字段名和类型由后端传输的json数据中的字段决定
    getPersonalDetail是抽象函数名称，自己随便起 括号里的@Header("Authorization") String token是我带的请求头用于后端身	份验证，具体加不加看后端需求
    GET请求一般可通过@Query传递请求参数给后端,可添加多个，比如这样
    Call<Type> getGETNAME(@Query("name1") String Name1,@Query("name2") String Name2)
    也可以没有参数设置Call<Type> getGETNAME()
   */
    @FormUrlEncoded
    @POST("api/login")//这里是相应请求的url,会接在第二块代码retrofit实例化填的域名或ip地址后面连成完整地址
    Call<LoginData> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
    /*
    POST的其他大部分和GET一样，但是如果通过表单传递数据给后端需要在@POST前面一行加上@FormUrlEncoded
    @Query也改成@Field
    Call<Type> postPOSTNAME(@Field("name1") String Name1,@Field("name2") String Name2)
    也可以没有参数设置
    Call<Type> postPOSTNAME()
    */
    //下面的是传递文件时的函数写法，目前还没试过行不行就先不写了。
    @Multipart
    @POST("api/image")//这里是相应请求的url
    Call<ImageUrlResponse> uploadImage(
            @Header("Authorization") String authorization,
            @Part("image") RequestBody image);
    @GET("qiniutoken")//这里是相应请求的url
    Call<QiniuTokenData> getQiniuToken(@Header("Authorization")String Token);
}
```



###### 第二块 在Application里实例化retrofit和你的API

这是一个单独的Java类，需要将类名“MyApplication”在manifest里声明，类名自己取

这样app启动时会自动调用Application里的onCreate（）函数，执行onCreate里的代码

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <application
        android:name=".MyApplication"//位置在这里，要在名字前加"."
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:networkSecurityConfig="@xml/network_security_config"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.CCNU_STATION"
        tools:targetApi="31">
        <activity
            android:name=".DetailChange"
            android:exported="false" />
        <activity
            android:name=".PersonalPage"
            android:exported="false" />

        <meta-data
            android:name="com.google.android.actions"
            android:resource="@xml/network_security_config" />

        <activity
            android:name=".SchoolDate"
            android:exported="false" />
        <activity
            android:name=".ChatPage"
            android:exported="false" />
        <activity
            android:name=".HomePage"
            android:exported="false" />
        <activity
            android:name=".SetOutLookActivity"
            android:exported="false" />
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```



```java
//此处省略包名和import

public class MyApplication extends Application {
    private static API api;//定义一个api对象，注意，必须是static修饰的，方便外部多次调用
    public void onCreate()//把所有app初始化需要的代码放里面
    {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.131.122.150:8080/")//这个就是后端的服务器ip可以换成域名，末尾必须加上/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);//在这里你的API被实例化了
    }
    
    public static API getApi()
    {
        //get函数返回实例化的api对象，可供外部调用
        return api;
    }
}

```

###### 第三块 你用于接收返回的json数据的数据类（也是单独一个Java文件）

```Java
public class Data
{
    private String name1;//接收数据的字段名字和类型要和json里的字段一样，不然接收不到，具体看后端传的json
    private int name2;
	/*
	这里稍微给一个json数据的例子
	{
	"name1":value1,
	"name2":value2
	}
	*/
    //写get函数方便外部获取数据
    public String getName1() {
        return name1;
    }

    public int getName2() {
        return First;
    }
	//写set函数才能让retrofit（或者是Gson，我也不清楚）自动把json数据传入Data的实例化对象
    //这里有一个小注意点，括号里参数名不能和字段名一样，否则好像会传不进去
    //函数名随便写应该没事，最好规范一点
    public String setName1(String Name1) {
        return msg;
    }

    public int setName2(int Name2) {
        return token;
    }
}
```

###### 第四块 发送请求（你可以写在你需要发送请求的地方，比如按钮的点击事件响应里,这里以POST为例）

注意一下，这个是异步请求，不按照正常的代码顺序，不要把依赖返回数据的代码放在回调函数外面

```java
API api = MyApplication.getApi();
Call<Data> call = api.postPostName(参数1，参数2......);//这个函数名我在上面的API里没写，意思意思得了
call.enqueue(new Callback<Data>() {
                    @Override
    				//请求成功的回调函数
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        Data body = response.body();
                        if(body == null) {
                            Toast.makeText(MainActivity.this,"响应体为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        /*
                        现在body里面就存着返回的数据，如果成功的话
                        通过getName1()和getName2()获取
                        /*
                        这里应该是你自己对数据的处理代码
                        */
                    }
                    @Override
    				//请求失败的回调函数
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }

                });
```

