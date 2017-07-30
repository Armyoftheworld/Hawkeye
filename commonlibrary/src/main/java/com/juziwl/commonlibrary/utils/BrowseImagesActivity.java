package com.juziwl.commonlibrary.utils;

import android.Manifest;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.R2;
import com.juziwl.commonlibrary.config.BaseActivity;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.mvp.view.BrowseImagesDelegate;
import com.juziwl.commonlibrary.widget.CustomSureDialog;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月24日
 * @description 浏览图片
 */
public class BrowseImagesActivity extends BaseActivity<BrowseImagesDelegate> {

    private String[] piclist;
    private String pic[] = null;
    private boolean canImageSave = true;
    private boolean isShowHeadIcon = false;
    public static final String IMAGELONGCLICK = "BrowseImagesActivity.imagelongclick",
            ACTIVITYFINISH = "BrowseImagesActivity.activityfinish";

    @Override
    protected void initEventAndData() {
        Bundle b = getIntent().getExtras();
        int id = b.getInt("ID", 0);
        String pics = b.getString("pics");
        isShowHeadIcon = b.getBoolean("isShowHeadIcon", false);
        canImageSave = b.getBoolean("canImageSave", true);
        if (!TextUtils.isEmpty(pics)) {
            pics = pics.replaceAll("/psmg/", "/pimgs/").replaceAll("/small/", "/normal/");
        }
        piclist = pics.split(";");
        if (piclist.length == 0) {
            pic = new String[1];
            pic[0] = "";
        } else {
            pic = new String[piclist.length];
            for (int i = 0; i < piclist.length; i++) {
                pic[i] = StringUtils.isEmpty(piclist[i]) ? "" : piclist[i];
            }

        }
        viewDelegate.initViewPagerData(pic, id, isShowHeadIcon, canImageSave);
    }

    @Override
    public void onInteractive(String action, Bundle data) {
        switch (action) {
            case IMAGELONGCLICK:
                showDialog();
                break;
            case ACTIVITYFINISH:
                onBackPressed();
                break;
        }
    }

    private Dialog dialog;

    private void showDialog() {
        if (dialog == null) {
            View view = LayoutInflater.from(BrowseImagesActivity.this).inflate(R.layout.common_popmenulongbtn, null);
            ButterKnife.bind(this, view);
            dialog = new Dialog(this, R.style.common_chooseDialog);
            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        dialog.show();

        //在show调用之后设置宽度铺满
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DisplayUtils.getScreenWidth();
        window.setAttributes(lp);
    }

    @OnClick(R2.id.btn_shutdown)
    void finishBrowse() {
        if (dialog != null) {
            dialog.dismiss();
        }
        onBackPressed();
    }

    @OnClick(R2.id.btn_keep)
    void save() {
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        savePic();
                    } else {
                        if (dialog != null) {
                            dialog.cancel();
                        }
                        CustomSureDialog.getInstance().createAlertDialog(this,
                                getString(R.string.common_open_external_storage), "知道了",
                                v -> CustomSureDialog.getInstance().cancelAlertDialog()).show();
                    }
                });
    }

    @OnClick(R2.id.btn_cancel)
    void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private void savePic() {
        int i = viewDelegate.getCurrentItem();
        LoadingImgUtil.getImageFile(pic[i], new LoadingImgUtil.OnFileImageLoadingListener() {

            @Override
            public void onFileLoadingComplete(File file) {
                try {
                    File image = new File(Global.SAVEPICTURE + System.currentTimeMillis() + ".png");
                    FileUtils.copyFile(file, image);
                    MediaStore.Images.Media.insertImage(getContentResolver(), image.getAbsolutePath(), image.getName(), "orange");
                    ToastUtils.showToast("保存成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showToast("保存失败");
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onLoadingFailed() {
                ToastUtils.showToast("保存失败");
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onLoadingComplete(Bitmap bitmap) {
            }
        });
    }


    @Override
    protected Class<BrowseImagesDelegate> getDelegateClass() {
        return BrowseImagesDelegate.class;
    }

    @Override
    protected void initCustomTopbar() {
    }

    @Override
    protected void injectActivity() {
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

}
