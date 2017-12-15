package cn.houno.houniaolvju.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

/*import com.example.hongaer.shoppingmall2.app.MyApplication;
import com.example.hongaer.shoppingmall2.user.bean.SPConsigneeAddressBean;
import com.example.hongaer.shoppingmall2.utils.CacheUtils;*/
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.application.MyApplication;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;

/**
 * Created by hongaer on 2017/7/6.
 */

public class PassengerStorage {
    public static final String JSON_CART = "json_cart";
    public static final String JSONBEAN = "jsonbean";
    private static PassengerStorage instance;
    private final   Context context;
  public SparseArray<GetScenicPassengerBean.DataBean> sparseArray;
    private GetScenicPassengerBean.DataBean Passenger;

    public PassengerStorage(Context context){
              this.context=context;
           //把之前的数据读取出来放到sparseArray内存
                sparseArray=new SparseArray<>(100);

            listToSparseArray();
    }
    public static PassengerStorage getInstance() {
        if (instance ==null) {
            instance = new PassengerStorage(MyApplication.getContex());
        }
        return  instance;
    }
     //从本地中读取数据加入SparseArray
    private void listToSparseArray() {

        List<GetScenicPassengerBean.DataBean> spConsigneeAddressBeanList=getAllData();
        //把list数据转换成sparseArray
        if(spConsigneeAddressBeanList!=null) {
            for (int i = 0; i < spConsigneeAddressBeanList.size(); i++) {

                Passenger = spConsigneeAddressBeanList.get(i);
                Log.i("6666","空指针的地方=="+spConsigneeAddressBeanList.get(i));
                if(Passenger!=null){
                    sparseArray.put((int) Long.parseLong(Passenger.getId()), Passenger);

                }
                //sparseArray.put(Integer.parseInt(consignee.getProduct_id()), consignee);

            }
        }
    }
    //获取本地所有的数据
    public List<GetScenicPassengerBean.DataBean> getAllData() {
        List<GetScenicPassengerBean.DataBean> consigneeList=new ArrayList<>();
         //1.从本地从获取
         String json= PrefUtils.getString(context,JSONBEAN,"");
        Log.i("777","空指针的地方=="+json);
         //用gson转换为列表
         //判断不为空时执行
        if (!TextUtils.isEmpty(json)) {
            consigneeList = new Gson().fromJson(json, new TypeToken<List<GetScenicPassengerBean.DataBean>>() {}.getType());
            //Log.i("666","空指针的地方=="+consigneeList);
        }
          return consigneeList;

    }

    public void addData(GetScenicPassengerBean.DataBean consignee) {
        //1.添加到内存中 SparseArray
        Log.i("110110","空指针的地方=="+consignee);
       // consignee tempData = sparseArray.get(Integer.parseInt(consignee.getProduct_id()));
      try {
          GetScenicPassengerBean.DataBean tempData = sparseArray.get((int) Long.parseLong(consignee.getId()));
          if (tempData != null) {
              // 内存中有了这条数据
              //  tempData.setNumber(tempData.getNumber() + 1);
          } else {
              tempData = consignee;
              // tempData.setNumber(1);

          }
          //同步到内存中
          //sparseArray.put(Integer.parseInt(tempData.getProduct_id()), tempData);
          sparseArray.put((int) Long.parseLong(tempData.getId()), tempData);

          //2.同步到本地
          saveLocal();
      }catch (NumberFormatException e){}

        }
    //删除数据
    public void deleteData(GetScenicPassengerBean.DataBean consignee) {
        //1.从内存中删除
     // sparseArray.delete(Integer.parseInt( consignee.getProduct_id()));
        sparseArray.delete((int) Long.parseLong(consignee.getId()));

        //2.把内存数据保存到本地
        saveLocal();
    }
    //修改数据
    public void updataData(GetScenicPassengerBean.DataBean consignee) {

      sparseArray.put((int) Long.parseLong(consignee.getId()), consignee);
       saveLocal();
    }

    private void saveLocal() {
        //把 parseArray 转换成 list
        List<GetScenicPassengerBean.DataBean> spConsigneeAddressBeanList = sparsesToList();
         //把转换成 String
        String json = new Gson().toJson(spConsigneeAddressBeanList);
        Log.i("999","空指针的地方=="+json);
          // 保存
       PrefUtils.setString(context, JSONBEAN, json);

    }


    private List<GetScenicPassengerBean.DataBean> sparsesToList() {
              List<GetScenicPassengerBean.DataBean> consigneeList=new ArrayList<>();
             if(sparseArray!=null&&sparseArray.size()>0) {
            for (int i = 0; i < sparseArray.size(); i++) {
                GetScenicPassengerBean.DataBean consignee = sparseArray.valueAt(i);
                consigneeList.add(consignee);
            }
        }
            return consigneeList;
    }


}
