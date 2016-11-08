package com.luodj.androidxx.giftchoose.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luodj.androidxx.giftchoose.R;
import com.luodj.androidxx.giftchoose.been.First_ListViewBeen;
import com.luodj.androidxx.giftchoose.constant.HttpUrl;
import com.luodj.androidxx.giftchoose.presenter.Presenter_FirstFragmengt;
import com.luodj.androidxx.giftchoose.view.ICallBack_Main;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.luodj.androidxx.giftchoose.R.id.firstfragment_normal_item_pic_img;

/**
 * Created by Administrator on 2016/11/7.
 */
public class FirstFragment_Normal extends Fragment {
    private  Context context;
    private ListView mListView;
    private  List<First_ListViewBeen.DataBean.ItemsBean> itemsList = new ArrayList<>();
    private MyAdapter myAdapter;
   private int countNum;
    private int offset = 0 ;
    public static FirstFragment_Normal getInstance(int num){
        FirstFragment_Normal firstFragment_normal = new FirstFragment_Normal();
        Bundle bundle = new Bundle();
        bundle.putInt("id",num);
        firstFragment_normal.setArguments(bundle);
        return firstFragment_normal ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfragment_normal, container, false);
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        countNum = id;
        initView(view);
        initData(id);
        return view;
    }

    private void initData(int num) {
        Presenter_FirstFragmengt presenter_firstFragmengt = new Presenter_FirstFragmengt(new ICallBack_Main() {
            @Override
            public void getMainResult(Object object) {
                First_ListViewBeen first_listViewBeen = (First_ListViewBeen) object;
                First_ListViewBeen.DataBean data = first_listViewBeen.getData();
                offset = offset+20;
                 itemsList.addAll(data.getItems());
                myAdapter.notifyDataSetChanged();
            }
        }).setPath(HttpUrl.PATH_FIRSTFRAGMENT_LISTVIEW_HEAD,num+"",HttpUrl.PATH_FIRSTFRAGMENT_LISTVIEW_END,0+"");
        presenter_firstFragmengt.initFirstFragment_Title_Model(context,4);
    }

    private void initView(View view) {
        mListView = (ListView)view.findViewById(R.id.firstfragment_normal_listview);
        myAdapter = new MyAdapter(context);
        mListView.setAdapter(myAdapter);
        setListener();
    }

    private void setListener() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount  == totalItemCount) {
                    Presenter_FirstFragmengt presenter_firstFragmengt = new Presenter_FirstFragmengt(new ICallBack_Main() {
                        @Override
                        public void getMainResult(Object object) {
                            First_ListViewBeen first_listViewBeen = (First_ListViewBeen) object;
                            First_ListViewBeen.DataBean data = first_listViewBeen.getData();
                            itemsList.addAll(data.getItems());
                            myAdapter.notifyDataSetChanged();
                        }
                    }).setPath(HttpUrl.PATH_FIRSTFRAGMENT_LISTVIEW_HEAD,countNum+"",HttpUrl.PATH_FIRSTFRAGMENT_LISTVIEW_END,offset+"");
                    presenter_firstFragmengt.initFirstFragment_Title_Model(context,4);
                    offset +=20;
                }
            }
        });
    }

    class  MyAdapter extends BaseAdapter{
       private LayoutInflater myInflater;
       private  Context context;
       public MyAdapter(Context context) {
           this.myInflater= LayoutInflater.from(context);
           this.context = context;
       }

       @Override
       public int getCount() {
           return itemsList ==null? 0: itemsList.size();
       }

       @Override
       public Object getItem(int position) {
           return position;
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder = null;
           if (convertView == null) {
               convertView = myInflater.inflate(R.layout.firstfragment_normal_item,parent,false);
               holder = new ViewHolder(convertView);
           }else {
               holder = (ViewHolder) convertView.getTag();
           }
           First_ListViewBeen.DataBean.ItemsBean itemsBean = itemsList.get(position);
           Picasso.with(context).load(itemsBean.getCover_image_url()).into(holder.imageView_pic);
           holder.textView_title.setText(itemsBean.getTitle());
           holder.textView_decrible.setText(itemsBean.getIntroduction());
           holder.textView_type.setText("栏目"+itemsBean.getType());
           holder.textView_collection.setText(itemsBean.getLikes_count()+"");
           return convertView;
       }
       class ViewHolder {
           ImageView imageView_icon;
           TextView textView_one;
           TextView textView_two;
           ImageView imageView_pic;
           TextView textView_title;
           TextView textView_decrible;
           TextView textView_type;
           TextView textView_collection;

           public ViewHolder(View view) {
               imageView_icon = (ImageView) view.findViewById(R.id.firstfragment_normal_item_icon_img);
               textView_one = (TextView)view.findViewById(R.id.firstfragment_normal_item_one_txt);
               textView_two = (TextView)view.findViewById(R.id.firstfragment_normal_item_two_txt);
               imageView_pic = (ImageView)view.findViewById(firstfragment_normal_item_pic_img);
               textView_title = (TextView)view.findViewById(R.id.firstfragment_normal_item_title_txt);
               textView_decrible = (TextView)view.findViewById(R.id.firstfragment_normal_item_decrible_txt);
               textView_type= (TextView)view.findViewById(R.id.firstfragment_normal_item_type_txt);
               textView_collection = (TextView)view.findViewById(R.id.firstfragment_narmal_item_mark__txt);
               view.setTag(this);
           }
       }
   }
}
