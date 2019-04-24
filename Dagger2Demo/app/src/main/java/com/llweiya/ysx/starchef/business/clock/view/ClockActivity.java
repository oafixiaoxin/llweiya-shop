package com.llweiya.ysx.starchef.business.clock.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.clock.injection.DaggerClockComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityClockBinding;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ClockActivity extends BaseActivity<ActivityClockBinding> {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int dayInWeek;
    private int dayOfMonth;//每月天数
    private int[] day31 = {1, 3, 5, 7, 8, 10, 12};
    private boolean is31 = false;

    private ClockHandler clockHandler;

    private Timer clockTimber = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            clockHandler.sendMessage(message);
        }
    };

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {

        clockHandler = new ClockHandler(this);

        getCurrentTime();
        is31 = funcIs31();

        viewBinding.txtDate.setText(year + "年" + month + "月" + day + "       " + getWeekName(dayInWeek));
        viewBinding.txtClock.setText(hour + ":" + minute + ":" + second);

        clockTimber.schedule(timerTask, 1000, 1000);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_clock;
    }

    @Override
    public void injectComponent() {
        DaggerClockComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        clockTimber.cancel();
        clockTimber = null;
        clockHandler.removeCallbacksAndMessages(null);
    }

    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        dayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String getWeekName(int number) {
        String weekName = "";
        switch (number) {
            case 1:
                weekName = "周日";
                break;
            case 2:
                weekName = "周一";
                break;
            case 3:
                weekName = "周二";
                break;
            case 4:
                weekName = "周三";
                break;
            case 5:
                weekName = "周四";
                break;
            case 6:
                weekName = "周五";
                break;
            case 7:
                weekName = "周六";
                break;
            default:
                break;
        }
        return weekName;
    }

    static class ClockHandler extends Handler {
        private WeakReference<ClockActivity> activity;

        ClockHandler(ClockActivity clockActivity) {
            activity = new WeakReference<ClockActivity>(clockActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ClockActivity clockActivity = activity.get();
            switch (msg.what) {
                case 1:
                    if (clockActivity != null) {
                        clockActivity.refreshClock();
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void refreshClock() {
        if (second < 59) {
            second++;
        } else {
            second = 1;
            if (minute < 59) {
                minute++;
            } else {
                minute = 1;
                if (hour < 23) {
                    hour++;
                } else {
                    hour = 0;
                    dayInWeek++;
                    if (month == 2) {
                        if (judgeFeburary(year)) {
                            if (day < 28) {
                                day++;
                            } else {
                                day = 1;
                                refreshMonthAndYear();
                            }
                        } else {
                            if (day < 27) {
                                day++;
                            } else {
                                day = 1;
                                refreshMonthAndYear();
                            }
                        }
                    } else {
                        if (is31) {
                            if (day < 30) {
                                day++;
                            } else {
                                day = 1;
                                refreshMonthAndYear();
                            }
                        } else {
                            if (day < 29) {
                                day++;
                            } else {
                                day = 1;
                                refreshMonthAndYear();
                            }
                        }
                    }
                }
            }
        }
        viewBinding.txtClock.setText(checkValue(hour) + ":" + checkValue(minute) + ":" + checkValue(second));
        viewBinding.txtDate.setText(checkValue(year) + "年" + checkValue(month) + "月" + checkValue(day) + "       " + getWeekName(dayInWeek));
    }

    //长度为1，前面补0
    private String checkValue(int value) {
        String newValue = String.valueOf(value);
        if (newValue.length() == 1) {
            newValue = "0" + newValue;
        }
        return newValue;
    }

    //判断闰年
    private boolean judgeFeburary(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }

    private boolean funcIs31 () {
        for (Integer integer : day31) {
            if (month == integer)
                return true;
        }
        return false;
    }

    private void refreshMonthAndYear() {
        if (month < 11) {
            month++;
        } else {
            month = 1;
            year++;
        }
    }

}
