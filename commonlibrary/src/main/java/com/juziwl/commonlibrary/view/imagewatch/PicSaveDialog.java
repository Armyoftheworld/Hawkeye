package com.juziwl.commonlibrary.view.imagewatch;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.juziwl.commonlibrary.utils.FileUtils;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;

import java.io.File;



/**
 * 选择更新用的dialog
 */
public class PicSaveDialog  {
    private static final int OPEN_ALBUM = 123;
    private static PicSaveDialog savePicDialogManager = null;
    private EditText tv_title;
    private Button btnSure;
    private TextView tv_content;
    private ImageWatcher vImageWatcher;
    private Activity mcontext;
    private String currentPath;

    public Button getBtnSure() {
        return btnSure;
    }

    public EditText getTv_title() {
        return tv_title;
    }

    public TextView getTv_content() {
        return tv_content;
    }

    public static PicSaveDialog getInstance() {
        if (savePicDialogManager == null) {
            savePicDialogManager = new PicSaveDialog();
        }
        return savePicDialogManager;
    }

    private Dialog savePicDialog;
    private Handler mhandler;

    public Dialog createSavePicDialog(Activity context, ImageWatcher ImageWatcher, String path, Handler handler) {
        mcontext = context;
        vImageWatcher = ImageWatcher;
        currentPath = path;
        mhandler = handler;
        showDialog(context, ImageWatcher);
        return savePicDialog;

    }

    public void showDialog(Context context, ImageWatcher ImageWatcher) {
//        if (savePicDialog == null) {
        View view = LayoutInflater.from(
                context)
                .inflate(R.layout.common_popmenulongbtn, null);
        Button btn_keep = (Button) view.findViewById(R.id.btn_keep);
        Button btn_shutdown = (Button) view.findViewById(R.id.btn_shutdown);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_keep.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonTools.checkPermission( mcontext, null, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, OPEN_ALBUM))
                    return;
                savePic();
            }
        });
        btn_shutdown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                savePicDialog.dismiss();
                vImageWatcher.handleBackPressed(); //返回页面
            }
        });
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                savePicDialog.dismiss();
            }
        });
        savePicDialog = new Dialog(context, R.style.common_chooseDialog);
        savePicDialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        }
        savePicDialog.show();
        //在show调用之后设置宽度铺满
        Window window = savePicDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = CommonTools.getScreenWidth(context);
        window.setAttributes(lp);
    }

    public void cancelsavePicDialog() {
        if (savePicDialog != null) {
            savePicDialog.dismiss();
        }
    }

    public boolean isShowsavePicDialog() {
        return savePicDialog != null && savePicDialog.isShowing();
    }


    private void savePic() {
        downLoadImage(currentPath.replace("/small/", "/normal/"));
    }

    private void downLoadImage(String path) {
        LoadingImgUtil.getImageFile(path, new LoadingImgUtil.OnFileImageLoadingListener() {
            @Override
            public void onFileLoadingComplete(File resource) {
                try {
                    MediaStore.Images.Media.insertImage(mcontext.getContentResolver(), resource.getAbsolutePath(), "orange", null);
                    CommonTools.updateAlbum(mcontext.getApplicationContext());
                   CommonTools.showToast("保存成功");
                    //将文件转移到另外目录
                    FileUtils.movePicToDir(resource, Global.SAVEPICTURE);
                    if (savePicDialog != null) {
                        savePicDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CommonTools.showToast("保存失败");
                    if (savePicDialog != null) {
                        savePicDialog.dismiss();
                    }
                }
            }
            @Override
            public void onLoadingComplete(Bitmap bitmap) {
            }
            @Override
            public void onLoadingFailed() {
            }
        });
    }
}
