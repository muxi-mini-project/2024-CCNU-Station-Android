# Achievement文档

## Achievement_Activity类

设置RecyclerView的布局管理器

````
RecyclerView recyclerView = findViewById(R.id.rv);
LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
recyclerView.setLayoutManager(linearLayoutManager);
````

设置RecyclerView的适配器

``````
Achievement_Adapter achievementAdapter = new Achievement_Adapter(data);
recyclerView.setAdapter(achievementAdapter);
``````

调用接口，获取用户的成就记录，传入stdID

```````
Call<AchievementTotalFinishedResponse> AchievementCall = api.getAchievementTotalFinished(stdID);
AchievementCall.enqueue(new Callback<AchievementTotalFinishedResponse>() {
            @Override
            public void onResponse(Call<AchievementTotalFinishedResponse> call, Response<AchievementTotalFinishedResponse> response) {
                Toast.makeText(Achievement_Activity.this, "请求成功", Toast.LENGTH_SHORT).show();
                AchievementTotalFinishedResponse body = response.body();

                if (body == null) {
                    Toast.makeText(Achievement_Activity.this, "响应体为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String finishedData = body.getData().toString();
                }
            }

            @Override
            public void onFailure(Call<AchievementTotalFinishedResponse> call, Throwable t) {
                Toast.makeText(Achievement_Activity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
```````



## Achievement_Ataper

普通的recycleview

``````
public class Achievement_Adapter extends RecyclerView.Adapter<Achievement_Adapter.Achievement_ViewHolder> {

    private List<Achievement> data;

    public Achievement_Adapter(List<Achievement> data) {
        this.data = data;
    }

    @NonNull
    @Override
    ///创建ViewHolder
    public Achievement_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new Achievement_ViewHolder(view);
    }

    //在onBindViewHolder方法中，获取了data 对象，该对象包含有关列表项的信息。
    // 在这个方法内部将这些信息绑定到 ViewHolder 中的视图组件上
    @Override
    public void onBindViewHolder(@NonNull Achievement_ViewHolder holder, int position) {
        Achievement achievement = data.get(position);
        holder.achievement_title.setText(achievement.getTitle());
        holder.achievement_isfinished.setChecked(achievement.isIsfinished());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class Achievement_ViewHolder extends RecyclerView.ViewHolder {
        TextView achievement_title;
        CheckBox achievement_isfinished;

        public Achievement_ViewHolder(@NonNull View itemView) {
            super(itemView);
            achievement_title = itemView.findViewById(R.id.title_textview);
            achievement_isfinished = itemView.findViewById(R.id.isfinished_checkbox);

            achievement_isfinished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }


}
``````



## AchievementClickResponse接口

Reuse/CCNU_API中

需要传入stuid和achid

```````
    @GET("api/user/achievement/update")
    Call<AchievementClickResponse> getAchievementReusult(@Query("stuid") String UserID,@Query("achid") String AchiID);

```````

返回一段“1“”0“组成的字符串

`````
public class AchievementClickResponse {
    public String getFisinishedString() {
        return fisinishedString;
    }

    private String fisinishedString;

}

`````

## AchievementTotalFinishedResponse

Reuse/CCNU_API中

需要传入stuid

``````
    @GET("api/user/achievement/get")
    Call<AchievementTotalFinishedResponse> getAchievementTotalFinished(@Query("stuid") String UserID);
``````

返回当前用户的成就状态

``````
package com.example.ccnu_station.Achievement;

public class AchievementTotalFinishedResponse {
    private String Code;
    private Object data;
    private String msg;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

``````

