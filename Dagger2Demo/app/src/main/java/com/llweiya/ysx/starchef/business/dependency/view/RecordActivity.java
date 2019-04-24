package com.llweiya.ysx.starchef.business.dependency.view;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.injection.DaggerDependencyComponent;
import com.llweiya.ysx.starchef.business.dependency.model.ITest;
import com.llweiya.ysx.starchef.business.dependency.model.ListItemViewModel;
import com.llweiya.ysx.starchef.business.dependency.model.TagItemViewModel;
import com.llweiya.ysx.starchef.business.dependency.model.TestModel;
import com.llweiya.ysx.starchef.business.dependency.view.adapter.ListAdapter;
import com.llweiya.ysx.starchef.business.dependency.view.adapter.TestAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityRecordBinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;
import omrecorder.WriteAction;

public class RecordActivity extends BaseActivity<ActivityRecordBinding> {

    private Recorder recorder;

    private TestAdapter testAdapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle("record");

        initRecord();

        viewBinding.btnRecord.setOnClickListener(v -> {
            recorder.startRecording();
        });

        viewBinding.btnStop.setOnClickListener(v -> {
            try {
                recorder.stopRecording();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            viewBinding.btnRecord.post(new Runnable() {
                @Override
                public void run() {
                    animateVoice(0);
                }
            });
        });

//        List<ITest> list = initData();
//
//        viewBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
//        testAdapter = new TestAdapter(new ArrayList<>());
//        testAdapter.setNewData(list);
//        viewBinding.recycler.setAdapter(testAdapter);
//
//        testAdapter.setOnCheckMoreListener(new TestAdapter.OnCheckMoreListener() {
//            @Override
//            public void onCheckMore(ListAdapter listAdapter) {
//                ListItemViewModel listItemViewModel = new ListItemViewModel();
//                List<TestModel> list1 = new ArrayList<>();
//                TestModel testModel = new TestModel();
//                testModel.id = 99;
//                testModel.name = "yanshuxin";
//                list1.add(testModel);
//                listItemViewModel.setContent(list1);
//
//                testAdapter.updateData(listItemViewModel, listAdapter);
//            }
//        });

//        LinearLayoutManager manager = (LinearLayoutManager)viewBinding.recycler.getLayoutManager();
//        manager.scrollToPositionWithOffset(55, 0);
//        manager.setStackFromEnd(true);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    public void injectComponent() {
        DaggerDependencyComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    private List<ITest> initData() {
        List<ITest> list = new ArrayList<>();

        TagItemViewModel tagItemViewModel = new TagItemViewModel();
        List<TestModel> tagList = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag" + (i+1);
            tagList.add(testModel);
        }
        tagItemViewModel.setContent(tagList);

        //ADD TAG
        list.add(tagItemViewModel);

        ListItemViewModel listItemViewModel1 = new ListItemViewModel();
        List<TestModel> tagList1 = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag1 - " + (i+1);
            tagList1.add(testModel);
        }
        listItemViewModel1.setContent(tagList1);

        list.add(listItemViewModel1);

        ListItemViewModel listItemViewModel2 = new ListItemViewModel();
        List<TestModel> tagList2 = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag2 - " + (i+1);
            tagList2.add(testModel);
        }
        listItemViewModel2.setContent(tagList2);

        list.add(listItemViewModel2);

        ListItemViewModel listItemViewModel3 = new ListItemViewModel();
        List<TestModel> tagList3 = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag3 - " + (i+1);
            tagList3.add(testModel);
        }
        listItemViewModel3.setContent(tagList3);

        list.add(listItemViewModel3);

        ListItemViewModel listItemViewModel4 = new ListItemViewModel();
        List<TestModel> tagList4 = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag4 - " + (i+1);
            tagList4.add(testModel);
        }
        listItemViewModel4.setContent(tagList4);

        list.add(listItemViewModel4);

        ListItemViewModel listItemViewModel5 = new ListItemViewModel();
        List<TestModel> tagList5 = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            TestModel testModel = new TestModel();
            testModel.id = i;
            testModel.name = "tag5 - " + (i+1);
            tagList5.add(testModel);
        }
        listItemViewModel5.setContent(tagList5);

        list.add(listItemViewModel5);

        return list;
    }

    private void initRecord() {
        recorder = OmRecorder.wav(
                new PullTransport.Noise(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) {
                        animateVoice((float) (audioChunk.maxAmplitude() / 200.0));
                    }
                },
                        new WriteAction.Default(),
                        new Recorder.OnSilenceListener() {
                            @Override public void onSilence(long silenceTime) {
                                Log.e("silenceTime", String.valueOf(silenceTime));
                                Toast.makeText(RecordActivity.this, "silence of " + silenceTime + " detected",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, 200
                ), file());
    }

    private void animateVoice(final float maxPeak) {
        viewBinding.btnRecord.animate().scaleX(1 + maxPeak).scaleY(1 + maxPeak).setDuration(10).start();
    }

    private PullableSource mic() {
        return new PullableSource.Default(
                new AudioRecordConfig.Default(
                        MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                        AudioFormat.CHANNEL_IN_MONO, 44100
                )
        );
    }

    @NonNull
    private File file() {
        return new File(Environment.getExternalStorageDirectory(), "kailashdabhi.wav");
    }
}
