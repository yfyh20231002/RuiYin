package com.example.shichang393.ruiyin.widget.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shichang393.ruiyin.ApiService.MineService;
import com.example.shichang393.ruiyin.Bean.mine.NickNameBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.widget.activity.MainActivity;
import com.example.shichang393.ruiyin.widget.activity.mine.AboutusActivity;
import com.example.shichang393.ruiyin.widget.activity.mine.LoginActivity;
import com.example.shichang393.ruiyin.widget.activity.mine.RegisterActivity;
import com.example.shichang393.ruiyin.widget.activity.mine.UpNickNameActivity;
import com.example.shichang393.ruiyin.widget.view.HintDialog;
import com.example.shichang393.ruiyin.widget.view.PhotoDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static com.bumptech.glide.Glide.with;


public class MyFragment extends Fragment {


    @InjectView(R.id.imag_my_touxiang)
    CircleImageView imagMyTouxiang;
    @InjectView(R.id.bt_login)
    Button btLogin;
    @InjectView(R.id.tex_my_nicheng)
    TextView texMyNicheng;
    @InjectView(R.id.btn_my_nicheng)
    LinearLayout btnMyNicheng;
    @InjectView(R.id.btn_my_touxiang)
    LinearLayout btnMyTouxiang;
    @InjectView(R.id.btn_my_mima)
    LinearLayout btnMyMima;
    @InjectView(R.id.btn_my_zhanghao)
    LinearLayout btnMyZhanghao;
    @InjectView(R.id.btn_my_tixing)
    LinearLayout btnMyTixing;
    @InjectView(R.id.my_imag_shezhi)
    ImageView myImagShezhi;
    @InjectView(R.id.checkbtn_set_on)
    ImageButton checkbtnSetOn;
    @InjectView(R.id.checkbtn_set_off)
    ImageButton checkbtnSetOff;
    @InjectView(R.id.btn_my_shezhi)
    RelativeLayout btnMyShezhi;
    @InjectView(R.id.btn_my_haoping)
    LinearLayout btnMyHaoping;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.btn_my_share)
    LinearLayout btnMyShare;
    @InjectView(R.id.btn_my_aboutus)
    LinearLayout btnMyAboutus;
    private Context mContext;
    private Activity activity;
    private String Iconurl, name;
    private PhotoDialog photoDialog = new PhotoDialog(); // 拍照 选择相册
    private HintDialog hintDialog = new HintDialog(); // 提示框
    private static final int IMAGE_REQUEST_CODE = 100;
    private static final int SELECT_PIC_NOUGAT = 101;
    private static final int CAMERA_REQUEST_CODE = 104;
    String path = Environment.getExternalStorageDirectory() + "/Android/data/com.example.shichang393.ruiyin/files/";
    String cameraname = "IMAGE_FILE_NAME.jpg";
    String galleryname = "IMAGE_GALLERY_NAME.jpg";
    File mCameraFile = new File(path, cameraname);//照相机的File对象
    File mGalleryFile = new File(path, galleryname);//相册的File对象
    Bitmap photo;
    String picPath;
    String zhanghao;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Iconurl = SharedPreferencesMgr.getUserIcon();
        name = SharedPreferencesMgr.getUsername();
        zhanghao = SharedPreferencesMgr.getZhanghao();
        if (!TextUtils.isEmpty(Iconurl)) {
            with(mContext).load(ConstanceValue.baseImage + "/" + Iconurl).into(imagMyTouxiang);
        }
        if (!TextUtils.isEmpty(name)) {
            btLogin.setVisibility(View.GONE);
            texMyNicheng.setVisibility(View.VISIBLE);
            texMyNicheng.setText(name);
        } else {
            btLogin.setVisibility(View.VISIBLE);
            texMyNicheng.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.bt_login, R.id.btn_my_nicheng, R.id.btn_my_touxiang, R.id.btn_my_mima, R.id.btn_my_zhanghao, R.id.btn_my_tixing, R.id.checkbtn_set_on, R.id.checkbtn_set_off, R.id.btn_my_haoping, R.id.btn_my_share, R.id.btn_my_aboutus})
    public void onViewClicked(View view) {
        String getuserid = SharedPreferencesMgr.getuserid();
        switch (view.getId()) {
            case R.id.bt_login:
                LoginActivity.startIntent(mContext);
                break;
//            更改昵称
            case R.id.btn_my_nicheng:
                if (TextUtils.isEmpty(getuserid)) {
                    LoginActivity.startIntent(mContext);
                } else {
                    UpNickNameActivity.startIntent(mContext);
                }
                break;
//            修改头像
            case R.id.btn_my_touxiang:
                if (TextUtils.isEmpty(getuserid)) {
                    LoginActivity.startIntent(mContext);
                } else {
                    changeHeadImage();
                }
                break;
//            修改密码
            case R.id.btn_my_mima:
                if (TextUtils.isEmpty(getuserid)) {
                    LoginActivity.startIntent(mContext);
                } else {
                    RegisterActivity.startIntent(mContext, 0);
                }
                break;
//            退出账号
            case R.id.btn_my_zhanghao:
                exit();
                break;
            case R.id.btn_my_tixing:
                break;
            case R.id.checkbtn_set_on:
                checkbtnSetOn.setVisibility(View.GONE);
                checkbtnSetOff.setVisibility(View.VISIBLE);
                break;
            case R.id.checkbtn_set_off:
                checkbtnSetOn.setVisibility(View.VISIBLE);
                checkbtnSetOff.setVisibility(View.GONE);
                break;
            case R.id.btn_my_haoping:
                break;
            case R.id.btn_my_share:

                break;
            case R.id.btn_my_aboutus:
                startActivity(new Intent(mContext, AboutusActivity.class));
                break;
        }
    }

    private void changeHeadImage() {
        // 拍照 选取相册
        photoDialog.setOnCameraClickListener(new PhotoDialog.PhotoCameraCallback() {
            @Override
            public void onClick() {
                takePhoto();
                photoDialog.dismiss();
            }
        });
        photoDialog.setOnChoosePhotoClickListener(new PhotoDialog.ChoosePhotoCallback() {
            @Override
            public void onClick() {
                chooseImage();
                photoDialog.dismiss();
            }
        });
        photoDialog.setOnCancleClickListener(new PhotoDialog.PhoneCancelCallback() {
            @Override
            public void onClick() {
                photoDialog.dismiss();
            }
        });
        photoDialog.show(activity.getFragmentManager(), "");
    }

    //拍照
    private void takePhoto() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(mContext, "com.example.shichang393.ruiyin", mCameraFile);
//            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intentFromCapture.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
//            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
        }
        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
    }

    //选择照片
    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile
                    (mContext, "com.example.shichang393.ruiyin", mGalleryFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, SELECT_PIC_NOUGAT);
        } else {
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mGalleryFile));
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {//照相后返回
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Uri inputUri = FileProvider.getUriForFile(mContext, "com.example.shichang393.ruiyin", mCameraFile);//通过FileProvider创建一个content类型的Uri
                    try {
                        photo = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(inputUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    jiexi(cameraname);
                } else {
                    photo = (Bitmap) data.getExtras().get("data");
                    jiexi(cameraname);
                }
                break;
            }
            case IMAGE_REQUEST_CODE: {//版本<7.0  图库后返回
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    try {
                        photo = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    jiexi(galleryname);
                }
                break;
            }
            case SELECT_PIC_NOUGAT://版本>= 7.0
                Uri dataUri = FileProvider.getUriForFile
                        (mContext, "com.example.shichang393.ruiyin", mGalleryFile);
                try {
                    photo = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(dataUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                jiexi(galleryname);
                break;

        }
    }

    private void jiexi(String name) {

        if (photo == null) {
            ToastUtils.showToast(mContext, "失败");
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            // 获取 SD 卡根目录
            String saveDir = Environment.getExternalStorageDirectory() + "/meitian_photos";
            // 新建目录
            File dir = new File(saveDir);
            if (!dir.exists()) dir.mkdir();
            // 生成文件名
            SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
            String filename = "MT" + (t.format(new Date())) + ".jpg";
            // 新建文件
            File file = new File(saveDir, filename);
            // 打开文件输出流
            fileOutputStream = new FileOutputStream(file);
            // 生成图片文件
            this.photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            // 相片的完整路径
            this.picPath = file.getPath();

            update(name, picPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void update(String fileName, String path) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.baseImage);
        MineService mineService = retrofit.create(MineService.class);
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("image/*");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", fileName, RequestBody.create(MEDIA_TYPE_MARKDOWN, new File(path))).addFormDataPart("userPhone", zhanghao).build();
        Call<NickNameBean> loginBeanCall = mineService.updateHeadImg(body);
        loginBeanCall.enqueue(new Callback<NickNameBean>() {
            @Override
            public void onResponse(Call<NickNameBean> call, Response<NickNameBean> response) {
                NickNameBean body1 = response.body();
                if (body1 != null) {
                    String yonghutouxiang = body1.getUsers().getYonghutouxiang();
                    if (!TextUtils.isEmpty(yonghutouxiang)) {
                        SharedPreferencesMgr.saveUserIcon(yonghutouxiang);
                        imagMyTouxiang.setImageBitmap(photo);
                    }
                }
            }

            @Override
            public void onFailure(Call<NickNameBean> call, Throwable t) {

            }
        });
    }

    private void exit() {
        hintDialog.setContent("确定要离开吗？");
        hintDialog.setOnConfirmClickListener(new HintDialog.HintConfirmCallback() {
            @Override
            public void onClick() {
                SharedPreferencesMgr.clearAll();
                Intent intent = new Intent();
                intent.putExtra("change", true);
                intent.setClass(mContext, MainActivity.class);
                startActivity(intent);
                activity.finish();
//                // 重启应用
//                Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                popupWindow.dismiss();
                hintDialog.dismiss();
            }
        });
        hintDialog.setOnCancelClickListener(new HintDialog.HintCancelCallback() {
            @Override
            public void onClick() {
                hintDialog.dismiss();
            }
        });
        hintDialog.show(activity.getFragmentManager(), "hintDialog");
    }
}
