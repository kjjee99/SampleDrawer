package org.techtown.drawer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.techtown.drawer.R;
import org.techtown.drawer.VO.ChallengeItem;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolderPage>{
    private ChallengeItemClickListener listener;
    private ArrayList<ChallengeItem> list;

    public ChallengeAdapter(ArrayList<ChallengeItem> data){
        this.list = data;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolderPage onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.challenge_item, parent, false);
        final ViewHolderPage viewHolderPage = new ViewHolderPage(view);
        return viewHolderPage;
    }
    public void setOnItemClickListener(ChallengeItemClickListener listener){
        this.listener = listener;
    }
    //    public void onItemClick(ViewHolderPage viewHolderPage, View view, int position){
//        if(listner != null)
//            listner.onItemClick(viewHolderPage, view, position);
//    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderPage holder, int position) {
        if(holder instanceof ViewHolderPage){
            ViewHolderPage viewHolderPage = (ViewHolderPage) holder;
            viewHolderPage.onBind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderPage extends RecyclerView.ViewHolder{
        public LinearLayout itemContact;
        private TextView tvTitle;
        private TextView tvContent;
        private ChallengeItem item;
        public ViewHolderPage(@NonNull @NotNull View itemView) {
            super(itemView);
            itemContact = itemView.findViewById(R.id.itemID);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolderPage.this, v, position);
                    }
                }
            });
        }

        public void onBind(ChallengeItem item){
            this.item = item;
            tvTitle.setText(item.getTitle());
            tvContent.setText(item.getContent());
        }
    }
}
