package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.ImageUtils;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class HeadImageActivity extends Activity implements OnItemClickListener {

    private ImageView tvback;
    private ImageView ivheadimg;
    private ImageView ivview;

    public static final int HEADER_RESULT_CODE = 301;
    private AlertView mAlertView;

    private boolean isReset = false;
    /**
     * 选择图片的返回码
     */
    private static final int CAMERA_REQUEST_CODE = 201;

    private static final int GALLERY_REQUEST_CODE = 202;

    private static final int CROP_REQUEST_CODE = 203;
    private Uri finalUri;
    private String userid;
    private String message;
    private ViewGroup.LayoutParams mLp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_head_image);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(HeadImageActivity.this, R.color.app_theme_green);
        initView();
        initData();

    }

    private void initData() {
        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivheadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturePopupWindow();
            }
        });
    }

    /**
     * 拍照或从图库选择图片(PopupWindow形式)
     */
    public void showPicturePopupWindow() {
        mAlertView = new AlertView("请选择您的照片", null, "取消", null,
                new String[]{"拍照", "相册"},
                HeadImageActivity.this, AlertView.Style.ActionSheet, this);
        mAlertView.setCancelable(true);
        mAlertView.show();
    }

    private void initView() {
        userid = PrefUtils.getString(getApplicationContext(), "userid", "");
        tvback = (ImageView) findViewById(R.id.iv_back);
        ivheadimg = (ImageView) findViewById(R.id.iv_head_img);


        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        int mWidth = dm.widthPixels;

        ivview = (ImageView) findViewById(R.id.iv_photo_head);
        mLp = ivview.getLayoutParams();
        mLp.width = mWidth;
        mLp.height = mWidth;
        ivview.setLayoutParams(mLp);

        String headImge = PrefUtils.getString(HeadImageActivity.this, "headimg", "");
        System.out.println("headImge====" + headImge);
        x.image().bind(ivview, headImge);

    }

    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
            case 0:
                takePhoto();
                break;
            case 1:
                takeGallery();
                break;
            case -1:
                break;
        }
    }


    /*
    * 拍照或从相册选取图片之后对图片处理然后上传
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap;
            if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    finalUri = data.getData();
                    Log.e("图片路径？？", data.getData() + "");
                    crop(finalUri);
                }

            } else if (requestCode == CAMERA_REQUEST_CODE) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    crop(finalUri);
                } else {
                    Toast.makeText(HeadImageActivity.this, "未找到存储卡，无法存储照片！",
                            Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == CROP_REQUEST_CODE) {
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(finalUri));
                    ivview.setImageBitmap(bitmap);
                    isReset = true;
                    uploadImage(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }

    }

    /**
     * 传回值
     */
    private void passParamsUri(String url) {
        Intent headIntent = new Intent();
        headIntent.putExtra("isReset", isReset);
        headIntent.putExtra("url", url);
        HeadImageActivity.this.setResult(HEADER_RESULT_CODE, headIntent);
    }

    private void uploadImage(Bitmap bitmap) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在上传...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        String image = null;
        try {
            image = ImageUtils.encodeHeader(bitmap);
            Log.e("imge？？", image + "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Constants.SETPERSONAL_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("img", image);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject json = new JSONObject(result);
                    message = json.getString("msg");
                    String headimg = json.getString("img");
                    PrefUtils.setString(HeadImageActivity.this, "headimg", headimg);
                    Toast.makeText(HeadImageActivity.this, message, Toast.LENGTH_SHORT)
                            .show();
                    passParamsUri(headimg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(HeadImageActivity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(HeadImageActivity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFinished() {
                progressDialog.dismiss();
            }
        });

    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            finalUri = getOutputMediaFileUri();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, finalUri);
            Log.e("Uri", finalUri.toString());
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(HeadImageActivity.this, "未找到存储卡，无法存储照片！",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", mLp.width);
        intent.putExtra("outputY", mLp.width);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, finalUri);
        intent.putExtra("outputFormat", "PNG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    /***
     * 从相册中取图片
     */
    private void takeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }


    private String fileName;
    private String filePath;
    private File imageFile;

    /**
     * 创建一个文件来保存图片
     */
    private Uri getOutputMediaFileUri() {
        //获取手机保存图片地址
        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //获取当前事件
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        fileName = "IMAGE_" + timeStamp;

        filePath = picDir.getPath() + File.separator + fileName + ".png";

        imageFile = new File(filePath);

        return Uri.fromFile(imageFile);
    }
}
