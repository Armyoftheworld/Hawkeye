package com.juziwl.commonlibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.view.View;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.widget.CustomSureDialog;

public  class SoundMeter {
	static final private double EMA_FILTER = 0.6;

	private MediaRecorder mRecorder = null;
	private double mEMA = 0.0;

	@SuppressWarnings("deprecation")
	public void start(String name, Context context) {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return;
		}
		if (CommonTools.checkPermission((Activity) context, null, new String[]{Manifest.permission.RECORD_AUDIO}, 40))
			return;
		if (mRecorder == null) {
			try {
				mRecorder = new MediaRecorder();
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//RAW_AMR
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mRecorder.setAudioEncodingBitRate(5150);
				mRecorder.setOutputFile(Global.VOICEPATH + name);
				mRecorder.prepare();
				mRecorder.start();
				mEMA = 0.0;
			} catch (Exception e) {
				if (!CustomSureDialog.getInstance().isAlertDialog()) {
					showUpdateDialog(context);
				}
			}
		}
	}

	public void showUpdateDialog(Context context) {
		try {
			CustomSureDialog.getInstance().createAlertDialog(context, context.getString(R.string.common_open_record_audio), "知道了", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CustomSureDialog.getInstance().cancelAlertDialog();
				}
			}).show();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void stop(Context context) {
		if (CommonTools.checkPermission((Activity) context, null, new String[]{Manifest.permission.RECORD_AUDIO}, 40))
			return;
		if (mRecorder != null) {
			try{
				mRecorder.stop();
			}catch(Exception e){
				if (!CustomSureDialog.getInstance().isAlertDialog()) {
					showUpdateDialog(context);
				}
				e.printStackTrace();
			}
			mRecorder.release();
			mRecorder = null;
		}
	}

	public void pause() {
		if (mRecorder != null) {
			mRecorder.stop();
		}
	}

	public void start() {
		if (mRecorder != null) {
			mRecorder.start();
		}
	}

	public double getAmplitude() {
		if (mRecorder != null)
			return (mRecorder.getMaxAmplitude() / 2700.0);
		else
			return 0;
	}

	public double getAmplitudeEMA() {
		double amp = getAmplitude();
		mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
		return mEMA;
	}
}
