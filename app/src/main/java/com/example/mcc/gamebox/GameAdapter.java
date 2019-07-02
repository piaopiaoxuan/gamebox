package com.example.mcc.gamebox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private Context mContext;
    private List<Game> mGameList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView gameImage, startImage;
        TextView gameName;

        /* 内部类ViewHolder的构造函数中传入的View参数就是RecycleView子项的最外层布局。
            然后就可以通过findViewById()方法来获取到布局中的各控件的实例了。
         */
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            startImage = view.findViewById(R.id.start_image);
            gameImage = view.findViewById(R.id.game_image);
            gameName = view.findViewById(R.id.game_name);
        }
    }

    // 该构造函数用于把要展示的数据源传进来，并赋值给mGameList
    public GameAdapter(List<Game> gameList) {
        mGameList = gameList;
    }

    // 该方法创建了ViewHolder实例，并在这个方法中将game_item布局加载进来，再将其传入到构造函数当中。
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.game_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        // ↓ 为各控件添加响应事件
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Game game = mGameList.get(position);
                Toast.makeText(v.getContext(), "点击了"+ game.getName()+"Card", Toast.LENGTH_SHORT).show();
            }
        });
        holder.startImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Game game = mGameList.get(position);
                Toast.makeText(v.getContext(), "点击了"+ game.getName()+"BTN", Toast.LENGTH_SHORT).show();
                if(position==1){
                    Intent intent = new Intent(v.getContext(),Main2Activity.class);
                    v.getContext().startActivity(intent);
                }
            }
        });
        return holder;
    }

    // 该方法对RecyclerView子项的数据进行赋值（当该子项位于屏幕内时执行）
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = mGameList.get(position);
        holder.gameName.setText(game.getName());
        Glide.with(mContext).load(game.getImageId()).into(holder.gameImage);
    }

    // 该方法较为简单，用于告明RecycleView一共有多少子项。
    @Override
    public int getItemCount() {
        return mGameList.size();
    }
}
