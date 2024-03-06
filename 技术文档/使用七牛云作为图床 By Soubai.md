
## 上传图片并获取图片URL

这里贴一个官方开发文档先

[Android SDK_SDK 下载_对象存储 - 七牛开发者中心 (qiniu.com)](https://developer.qiniu.com/kodo/1236/android)

注意，官方文档涉及内容更详细更全面，但是对于初学者有些地方容易引起混淆和疑惑，我只是用自己的理解重写一遍，只写其中一种最简单可用的方法，并且以下内容不涉及DNS服务器配置（没有特殊需求可以不用管这个）

##### 在".gradle"文件中添加环境依赖

我这里是直接用了第一种，没有指定okhttp版本的第一种就够了

```
implementation("com.qiniu:qiniu-android-sdk:8.7.+")
```

##### 在Application类（自己创建的Java类，用于初始化app所需的一些实例）中的onCreate方法中初始化

具体有关Application类我在retrofit模板中有提到，贴一个链接在这里

http://t.csdnimg.cn/THYk2

注意，一个Application类就可以承担所有（大概吧）初始化，如果你之前已经有了，不需要新建一个

官方的技术文档中有出现recorder和keyGen这两个变量，疑似用于记录一些信息，默认填null问题不大

```java
public class MyApplication extends Application {
    private static UploadManager uploadManager;//声明uploadManager为static修饰的变量，方便不生成类对象就可调用
    public void onCreate()
    {
        super.onCreate();
        
        // AutoZone：自动根据 bucket 去查询相应 Zone，Zone 信息会被缓存
        Zone zone = new AutoZone();
        // 根据区域 ID 创建 Zone，无需查询，强烈推荐使用 AutoZone
        //zone = FixedZone.createWithRegionId("z0");
        Configuration config = new Configuration.Builder()
                .connectTimeout(90)              // 链接超时。默认90秒
                .useHttps(true)                  // 是否使用https上传域名
                .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .resumeUploadVersion(Configuration.RESUME_UPLOAD_VERSION_V2) // 使用新版分片上传
                .concurrentTaskCount(3)          // 并发上传线程数量为3
                .responseTimeout(90)             // 服务器响应超时。默认90秒
                .recorder(null)              // recorder分片上传时，已上传片记录器。默认null
                .recorder(null,null)      // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(zone)                      // 设置区域，不指定会默认使用 AutoZone；指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
    }
    //这里是一个重复调用uploadManager的函数
    public static UploadManager getUploadManager() {
        return uploadManager;
    }
}

```

##### 调用uploadmanager上传图片

注意，下面的函数是异步请求

```java
data = <File 对象 或 文件路径 或 字节数组 或 数据流 或 Uri 资源>//自己要上传的文件
String key = <指定七牛服务上的文件名，或 null>;//默认null就行
String token = <从服务端 SDK 获取>;//这个要问后端要，token是注册了七牛云账户后可生成的，具体不清楚，可以去看官方文档
uploadManager.put(data, key, token,
    new UpCompletionHandler() {
        @Override
        public void complete(String key, ResponseInfo info, JSONObject res) {
            //res 包含 hash、key 等信息，具体字段取决于上传策略的设置
             if(info.isOK()) {
                Log.i("qiniu", "Upload Success");//上传成功
                String uploadedKey = response.optString("key");
                 //这个uploadedkey可以和你手里的七牛云私有域名拼接，得到的就是图片的URL
             } else {
                Log.i("qiniu", "Upload Fail");
                //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
             }
             //这里也可以加一句log：Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
            }
        }, null);

```

