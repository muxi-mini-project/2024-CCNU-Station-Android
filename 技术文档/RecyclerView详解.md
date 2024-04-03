#### RecyclerView的使用一共需要四个部分，在xml中添加recyclerview控件，在layout文件夹新建一个item.xml文件（名字自取）用于定义单个item的布局（recyclerview实质就是多个数据不同的item组成的列表），创建对应的Adapter类用于管理recyclerview的数据，在activity中完成数据和布局的连通。

### 一 在xml中添加recyclerview控件
###### 这里很简单没什么好说的，请自行添加依赖
### 二 在layout文件夹下创建一个item.xml文件
###### 下面是我的项目的一个示例，根据实际情况自己写，就和写普通布局一个思路
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:id="@+id/texttime"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:layout_gravity="center"
        android:layout_marginTop="10dp" />

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="350dp"
        android:layout_height="match_parent"
        android:background="@drawable/whiteblock1"
        android:layout_gravity="center"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="0dp">

        <TextView
            android:id="@+id/textcontent"
            android:layout_width="300dp"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:text="TextView"
            app:layout_constraintBottom_toTopOf="@+id/imgpicture"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/texttitle" />

        <TextView
            android:id="@+id/texttitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:text="TextView"
            app:layout_constraintBottom_toTopOf="@+id/textcontent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/imgavatar" />

        <ImageView
            android:id="@+id/imgpicture"
            android:layout_width="300dp"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/textcontent" />

        <ImageView
            android:id="@+id/imgavatar"
            android:layout_width="60dp"
            android:layout_height="60dp"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="16dp"
            app:layout_constraintBottom_toTopOf="@+id/texttitle"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.089"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</LinearLayout>

```
### 三 创建对应的Adapter类用于管理布局资源
###### 这个比较重要，我一一解释
```java
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>
```
###### extends后面<>中的分别是自己Adapter类名和Adapter里面viewholder内部类的类名

```java
private ArrayList<Item> itemList;
```
###### ArrayList是java中的动态数组，itemList用于存放Item对象，Item是自己定义的类，该对象需包含所有Recyclerview中单个Item所需的资源数据，对应的Item示例如下
### Item类

```java
package com.example.ccnu_station.Record;

public class Item {
    private String title;
    private String avatar;
    /**
     * 图片1URL
     */
    private String image1;
    /**
     * 发布人的ID
     */
    private String poster;
    /**
     * 帖子的ID
     */
    private String postID;
    /**
     * 发布的地点
     */
    private String postLocation;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 发布时间
     */
    private String time;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public String getImage1() { return image1; }
    public void setImage1(String value) { this.image1 = value; }
    public String getPoster() { return poster; }
    public void setPoster(String value) { this.poster = value; }

    public String getPostID() { return postID; }
    public void setPostID(String value) { this.postID = value; }

    public String getPostLocation() { return postLocation; }
    public void setPostLocation(String value) { this.postLocation = value; }

    public String getText() { return text; }
    public void setText(String value) { this.text = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }
}

```
### Adapter的构造器
```java
public RecordAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
```
###### 在Adapter实例化的时候就传入数据列表
### viewholder内部类
```java
public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textContent;
        public ImageView avatar;
        public ImageView picture;
        public TextView time;
        public RecordViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.texttime);
            textTitle = itemView.findViewById(R.id.texttitle);
            textContent = itemView.findViewById(R.id.textcontent);
            avatar = itemView.findViewById(R.id.imgavatar);
            picture = itemView.findViewById(R.id.imgpicture);
        }
    }
```
###### 应该很好理解是干什么用的，不需要深究
#### 下面是详细解释
######   这段代码定义了一个静态内部类 `RecordViewHolder`，它用于表示 RecyclerView 中每个列表项的视图容器。在 Android 中，RecyclerView 是用于展示大量数据列表的常用组件。

###### 在 `RecordViewHolder` 类中，定义了一些公共的成员变量，例如 `textTitle`、`textContent`、`avatar`、`picture` 和 `time`，它们分别表示列表项中的标题、内容、用户头像、图片和时间。这些成员变量的类型分别是 TextView、ImageView 和 ImageView。

###### 类中的构造函数 `RecordViewHolder(View itemView)` 接收一个 View 类型的参数 `itemView`，这个参数表示列表项的视图对象。在构造函数中，通过调用父类 `RecyclerView.ViewHolder` 的构造函数来初始化 ViewHolder，并且通过 `itemView.findViewById()` 方法来初始化成员变量，将对应的视图元素与成员变量进行关联。

### 用于创建Viewholder对象的函数
```java
public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new RecordViewHolder(view);
    }
```
###### 不想看一大串的话，简单来说，上面需要根据情况改动的就是viewholder的名字和R.layout.**** 也就是自己创建的item.xml的名字
###### 具体来说，这段代码中的方法 `onCreateViewHolder` 是在创建新的 ViewHolder 实例时被调用的方法。它接收两个参数：`parent` 和 `viewType`。`parent` 是 RecyclerView 的父视图，而 `viewType` 表示视图的类型，可以根据需要进行不同的处理。

###### 在方法内部，首先通过 `LayoutInflater` 从布局文件 `R.layout.record_item` 中创建了一个新的视图对象 `view`。然后使用这个视图对象初始化一个新的 `RecordViewHolder` 对象，并将其返回。这样就完成了一个 ViewHolder 的创建过程，后续该 ViewHolder 就会被用于显示 RecyclerView 中的数据项。

### 绑定Item对象中的数据到视图的函数
```java
    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textContent.setText(item.getText());
        holder.time.setText(item.getTime());
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatar())
                .circleCrop()
                .into(holder.avatar);
        Glide.with(holder.itemView.getContext())
                .load(item.getImage1())
                .into(holder.picture);
    }
```

### 一个返回adapter中item个数的函数
```java
    public int getItemCount() {
        return itemList.size();
    }
```
### 用于外部设置adapter中itemlist的函数
```java
public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
```
### 下面的是全部合起来的示例

```java
package com.example.ccnu_station.Record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.R;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{
    private ArrayList<Item> itemList;

    public RecordAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textTitle.setText(item.getTitle());
        holder.textContent.setText(item.getText());
        holder.time.setText(item.getTime());
        Glide.with(holder.itemView.getContext())
                .load(item.getAvatar())
                .circleCrop()
                .into(holder.avatar);
        Glide.with(holder.itemView.getContext())
                .load(item.getImage1())
                .into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textContent;
        public ImageView avatar;
        public ImageView picture;
        public TextView time;
        public RecordViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.texttime);
            textTitle = itemView.findViewById(R.id.texttitle);
            textContent = itemView.findViewById(R.id.textcontent);
            avatar = itemView.findViewById(R.id.imgavatar);
            picture = itemView.findViewById(R.id.imgpicture);
        }
    }
}

```
### 四 在activity中连接recyclerview和adapter

```java
package com.example.ccnu_station.Record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordAdapter adapter;
    private ArrayList<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);
        itemList = generateItemList();//自己写的函数用于初始化itemList
        recyclerView = findViewById(R.id.recordrecyclerview);//绑定recyclerview
        adapter = new RecordAdapter(itemList);//创建adapter对象
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置layoutmanager
        recyclerView.setAdapter(adapter);//设置adapter为刚创建的adapter
    }
    private ArrayList<Item> generateItemList()
    {
	    //自己定义函数体
    } 
}
```