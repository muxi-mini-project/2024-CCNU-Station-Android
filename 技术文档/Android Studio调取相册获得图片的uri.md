###### 因为这个我也不是很明白，简略写一下，会套就够了
###### API 31以下可用
### 封装好的类用于通过文件uri来获取文件
###### 直接用就行

```java
public class FileUtil {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static File uriToFile(Context context, Uri uri) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();

        File destinationFile = createDestinationFile(context);

        try (InputStream inputStream = contentResolver.openInputStream(uri);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {

            if (inputStream != null) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }

        return destinationFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static File createDestinationFile(Context context) throws IOException {
        String fileName = "image_file"; // 可以根据实际情况给文件命名
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir == null) {
            throw new IOException("External storage directory not available");
        }

        File destinationFile = new File(storageDir, fileName + ".jpg");
        if (destinationFile.exists()) {
            destinationFile.delete();
        }
        return destinationFile;
    }
}


```
### 通过相册调取uri并获取文件
###### uri应该是代表文件独特的身份信息，可以通过uri来获取文件
```java
public class MyAcctivity extends AppCompatActivity{
	private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
	            new ActivityResultContracts.GetContent(),
	            uri -> {
	                if (uri != null) {
	                /*这里就已经获取到给图片的uri了
	                在这里写和uri有关的处理代码
	                比如通过uri获取图片文件，上传图床什么的
	                */
	                //调用上面封装好的类方法获取文件
	                File avatarFile;
                    UriAvatar = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(SetOutLookActivity.this,UriAvatar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //因为这个操作有可能报出异常，编译器要求用try和catch捕获可能的异常
                    //现在avatarFile就是用户选择的图片文件，可以在这里直接将他上传到图床
                    //上传到七牛云图床部分之前有写过
	                }
	            }
	);
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_myactivity);
	        getContentLauncher.launch("image/*");
	        //上面这一句可以写在点击事件里，这句会调取相册让用户选择图片
	        //需要注意的是，这个也是异步方法，数据相关的代码要处理好
	}
}
```
###### 这里再贴一个上传图片到七牛云并返回Key的链接
###### http://t.csdnimg.cn/LzKRo
