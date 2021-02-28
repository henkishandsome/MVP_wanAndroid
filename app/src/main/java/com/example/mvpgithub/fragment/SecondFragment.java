package com.example.mvpgithub.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvpgithub.R;
import com.example.mvpgithub.contract.BannerContract;
import com.example.mvpgithub.util.MyApplication;
import com.example.mvpgithub.util.StatusBarUtils;

import java.util.LinkedList;

public class SecondFragment extends Fragment  {
//    private StreamLiveCameraView liveCameraView;
//    boolean isFilter = false;
//    boolean isMirror = false;
//
//    private StreamAVOption streamAVOption;
//    private String rtmpUrl = "rtmp://ossrs.net/" + StatusBarUtils.getRandomAlphaString(3) + '/' + StatusBarUtils.getRandomAlphaDigitString(5);
//
//    private Button btnStartStreaming;
//    private Button btnStopStreaming;
//    private Button btnStartRecord;
//    private Button btnStopRecord;
//    private Button btnFliter;
//    private Button btnSwapCamera;
//    private Button btnScreenshot;
//    private Button btnMirror;
//
//    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
//        initLiveConfig(view);
//        initView(view);

        return view;
    }

//    private void initView(View view) {
//        btnStartStreaming = view.findViewById(R.id.btn_startStreaming);
//        btnStartStreaming.setOnClickListener(this);
//
//        btnStopStreaming = view.findViewById(R.id.btn_stopStreaming);
//        btnStopStreaming.setOnClickListener(this);
//
//        btnStartRecord = view.findViewById(R.id.btn_startRecord);
//        btnStartRecord.setOnClickListener(this);
//
//        btnStopRecord = view.findViewById(R.id.btn_stopRecord);
//        btnStopRecord.setOnClickListener(this);
//
//        btnFliter = view.findViewById(R.id.btn_filter);
//        btnFliter.setOnClickListener(this);
//
//        btnSwapCamera = view.findViewById(R.id.btn_swapCamera);
//        btnSwapCamera.setOnClickListener(this);
//
//        btnScreenshot = view.findViewById(R.id.btn_screenshot);
//        btnScreenshot.setOnClickListener(this);
//
//        btnMirror = view.findViewById(R.id.btn_mirror);
//        btnMirror.setOnClickListener(this);
//
//        imageView = view.findViewById(R.id.iv_image);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imageView.setVisibility(View.GONE);
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_startStreaming://开始推流
//                if(!liveCameraView.isStreaming()){
//                    liveCameraView.startStreaming(rtmpUrl);
//                }
//                break;
//            case R.id.btn_stopStreaming://停止推流
//                if(liveCameraView.isStreaming()){
//                    liveCameraView.stopStreaming();
//                }
//                break;
//            case R.id.btn_startRecord://开始录制
//                if(!liveCameraView.isRecord()){
//                    Toast.makeText(getActivity(),"开始录制视频",Toast.LENGTH_SHORT).show();
//                    liveCameraView.startRecord();
//                }
//                break;
//            case R.id.btn_stopRecord://停止录制
//                if(liveCameraView.isRecord()){
//                    liveCameraView.stopRecord();
//                    Toast.makeText(getActivity(),"视频已成功保存至系统根目录的 Movies/WSLive文件夹中",Toast.LENGTH_LONG).show();
//                }
//                break;
//            case R.id.btn_filter://切换滤镜
//                BaseHardVideoFilter baseHardVideoFilter = null;
//                if(isFilter){
//                    baseHardVideoFilter = new GPUImageCompatibleFilter(new GPUImageBeautyFilter());
//                }else {
//                    baseHardVideoFilter = new FishEyeFilterHard();
//                }
//                liveCameraView.setHardVideoFilter(baseHardVideoFilter);
//                isFilter = !isFilter;
//                break;
//            case R.id.btn_swapCamera://切换摄像头
//                liveCameraView.swapCamera();
//                break;
//            case R.id.btn_screenshot://截帧
//                liveCameraView.takeScreenShot(new RESScreenShotListener() {
//                    @Override
//                    public void onScreenShotResult(Bitmap bitmap) {
//                        if(bitmap != null){
//                            imageView.setVisibility(View.VISIBLE);
//                            imageView.setImageBitmap(bitmap);
//                        }
//
//                    }
//                });
//                break;
//            case R.id.btn_mirror://镜像
//                if(isMirror){
//                    liveCameraView.setMirror(true,false,false);
//                }else {
//                    liveCameraView.setMirror(true,true,true);
//                }
//                isMirror = !isMirror;
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * 设置推流参数
//     */
//    public void initLiveConfig(View view) {
//        liveCameraView = (StreamLiveCameraView) view.findViewById(R.id.stream_previewView);
//
//        //参数配置 start
//        streamAVOption = new StreamAVOption();
//        streamAVOption.streamUrl = rtmpUrl;
//        //参数配置 end
//
//        liveCameraView.init(MyApplication.context, streamAVOption);
//        liveCameraView.addStreamStateListener(resConnectionListener);
//        LinkedList<BaseHardVideoFilter> files = new LinkedList<>();
//        files.add(new GPUImageCompatibleFilter(new GPUImageBeautyFilter()));
//        files.add(new WatermarkFilter(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),new Rect(100,100,200,200)));
//        liveCameraView.setHardVideoFilter(new HardVideoGroupFilter(files));
//    }
//
//    RESConnectionListener resConnectionListener = new RESConnectionListener() {
//        @Override
//        public void onOpenConnectionResult(int result) {
//            //result 0成功  1 失败
//            Toast.makeText(getActivity(),"打开推流连接 状态："+result+ " 推流地址："+rtmpUrl,Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onWriteError(int errno) {
//            Toast.makeText(getActivity(),"推流出错,请尝试重连",Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onCloseConnectionResult(int result) {
//            //result 0成功  1 失败
//            Toast.makeText(getActivity(),"关闭推流连接 状态："+result,Toast.LENGTH_LONG).show();
//        }
//    };
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        liveCameraView.destroy();
//    }
}