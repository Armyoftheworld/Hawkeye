package com.juziwl.commonlibrary.view.easycommonadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * 项目名称: CommonAdapter
 * 包 名 称: com.classic.adapter
 *
 * 类 描 述: 网络图片加载接口规范
 * 创 建 人: 续写经典
 * 创建时间: 2016/1/21 17:54.
 */
public interface ImageLoad {

    void load(@NonNull Context context, @NonNull ImageView imageView, @NonNull String imageUrl);
}
