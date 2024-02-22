###### 非常简单，没什么好多说的
### 先写一个ViewModel类
```java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel<DataType> extends ViewModel {
    private MutableLiveData<DataType> data = new MutableLiveData<>();
    //获取数据的方法，方便响应的时候获取最新数据
    public LiveData<DataType> getData() {
        return data;
    }
    //更新数据函数，数据更新的时候调用这个方法来更新数据就可以触发响应
    public void updateData(DataType newData) {
        data.setValue(newData);
    }
}
```
### 在Activity中实现此功能
```java
public class MyActivity extends AppCompatActivity {
	private MyViewModel<YourData> viewModel;
	//在此处声明viewModel为全局变量，方便调用
	//<YourData>中的YourData是一个自写的类，包含你要更新的UI所需的数据
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_myactivity);
	        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
	        viewModel.getData().observe(this, newData -> {
	            // 数据发生变化，刷新界面
	            updateUI(newData);
	    });
		    //上面两句初始化ViewModel
	}
	//写一个更新UI函数
	public updataUI(Datatype newData)
	{
		//自己在这里更新xml布局的数据资源
	}
}
//之后只要在可能产生数据更新的地方调用ViewModle的updateData()函数 就会自动更新UI
```