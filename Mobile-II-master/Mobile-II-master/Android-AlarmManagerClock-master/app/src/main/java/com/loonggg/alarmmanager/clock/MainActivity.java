package com.loonggg.alarmmanager.clock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.loonggg.alarmmanager.clock.view.SelectRemindCyclePopup;
import com.loonggg.alarmmanager.clock.view.SelectRemindWayPopup;
import com.loonggg.lib.alarmmanager.clock.AlarmManagerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView date_tv;
    private TimePickerView pvTime;
    private RelativeLayout repeat_rl, ring_rl;
    private TextView tv_repeat_value, tv_ring_value;
    private LinearLayout allLayout;
    private Button set_btn;
    private String time;
    private int cycle;
    private int ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allLayout = (LinearLayout) findViewById(R.id.all_layout);
        set_btn = (Button) findViewById(R.id.set_btn);
        set_btn.setOnClickListener(this);
        date_tv = (TextView) findViewById(R.id.date_tv);
        repeat_rl = (RelativeLayout) findViewById(R.id.repeat_rl);
        repeat_rl.setOnClickListener(this);
        ring_rl = (RelativeLayout) findViewById(R.id.ring_rl);
        ring_rl.setOnClickListener(this);
        tv_repeat_value = (TextView) findViewById(R.id.tv_repeat_value);
        tv_ring_value = (TextView) findViewById(R.id.tv_ring_value);
        pvTime = new TimePickerView(this, TimePickerView.Type.HOURS_MINS);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                time = getTime(date);
                date_tv.setText(time);
            }
        });

        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvTime.show();
            }
        });

    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repeat_rl:
                selectRemindCycle();
                break;
            case R.id.ring_rl:
                selectRingWay();
                break;
            case R.id.set_btn:
                setClock();
                break;
            default:
                break;
        }
    }

    private void setClock() {
        if (time != null && time.length() > 0) {
            String[] times = time.split(":");
            if (cycle == 0) {//Apakah jam alarm harian
                AlarmManagerUtil.setAlarm(this, 0, Integer.parseInt(times[0]), Integer.parseInt
                        (times[1]), 0, 0, "Jam alarm berdering\n", ring);
            } if(cycle == -1){//Adalah alarm yang hanya berdering satu kali
                AlarmManagerUtil.setAlarm(this, 1, Integer.parseInt(times[0]), Integer.parseInt
                        (times[1]), 0, 0, "Jam alarm berdering\n", ring);
            }else {//Jam alarm multi-pilihan di hari kerja
                String weeksStr = parseRepeat(cycle, 1);
                String[] weeks = weeksStr.split(",");
                for (int i = 0; i < weeks.length; i++) {
                    AlarmManagerUtil.setAlarm(this, 2, Integer.parseInt(times[0]), Integer
                            .parseInt(times[1]), i, Integer.parseInt(weeks[i]), "Jam alarm berdering\n", ring);
                }
            }
            Toast.makeText(this, "Alarm disetel dengan sukses\n", Toast.LENGTH_LONG).show();
        }

    }


    public void selectRemindCycle() {
        final SelectRemindCyclePopup fp = new SelectRemindCyclePopup(this);
        fp.showPopup(allLayout);
        fp.setOnSelectRemindCyclePopupListener(new SelectRemindCyclePopup
                .SelectRemindCyclePopupOnClickListener() {

            @Override
            public void obtainMessage(int flag, String ret) {
                switch (flag) {
                    // senin
                    case 0:

                        break;
                    // selasa
                    case 1:

                        break;
                    // rabu
                    case 2:

                        break;
                    // kamis
                    case 3:

                        break;
                    // jumat
                    case 4:

                        break;
                    // sabtu
                    case 5:

                        break;
                    // minggu
                    case 6:

                        break;
                    //Tentukan
                    case 7:
                        int repeat = Integer.valueOf(ret);
                        tv_repeat_value.setText(parseRepeat(repeat, 0));
                        cycle = repeat;
                        fp.dismiss();
                        break;
                    case 8:
                        tv_repeat_value.setText("Setiap hari\n");
                        cycle = 0;
                        fp.dismiss();
                        break;
                    case 9:
                        tv_repeat_value.setText("Hanya sekali saja\n");
                        cycle = -1;
                        fp.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void selectRingWay() {
        SelectRemindWayPopup fp = new SelectRemindWayPopup(this);
        fp.showPopup(allLayout);
        fp.setOnSelectRemindWayPopupListener(new SelectRemindWayPopup
                .SelectRemindWayPopupOnClickListener() {

            @Override
            public void obtainMessage(int flag) {
                switch (flag) {
                    //getaran
                    case 0:
                        tv_ring_value.setText("Getaran");
                        ring = 0;
                        break;
                    // nada dering
                    case 1:
                        tv_ring_value.setText("Nada dering\n");
                        ring = 1;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * @param repeat Mem-parsing siklus jam alarm biner

     * @param flag   flag=    0 mengembalikan Senin dengan karakter Cina, siklus pada hari Selasa, dll, bendera = 1, mengembalikan manfaat (1,2,3)
     * @return
     */
    public static String parseRepeat(int repeat, int flag) {
        String cycle = "";
        String weeks = "";
        if (repeat == 0) {
            repeat = 127;
        }
        if (repeat % 2 == 1) {
            cycle = "Senin";
            weeks = "1";
        }
        if (repeat % 4 >= 2) {
            if ("".equals(cycle)) {
                cycle = "Selasa";
                weeks = "2";
            } else {
                cycle = cycle + "," + "Selasa";
                weeks = weeks + "," + "2";
            }
        }
        if (repeat % 8 >= 4) {
            if ("".equals(cycle)) {
                cycle = "rabu";
                weeks = "3";
            } else {
                cycle = cycle + "," + "rabu";
                weeks = weeks + "," + "3";
            }
        }
        if (repeat % 16 >= 8) {
            if ("".equals(cycle)) {
                cycle = "Kamis";
                weeks = "4";
            } else {
                cycle = cycle + "," + "Kamis";
                weeks = weeks + "," + "4";
            }
        }
        if (repeat % 32 >= 16) {
            if ("".equals(cycle)) {
                cycle = "Jumat";
                weeks = "5";
            } else {
                cycle = cycle + "," + "Jumat";
                weeks = weeks + "," + "5";
            }
        }
        if (repeat % 64 >= 32) {
            if ("".equals(cycle)) {
                cycle = "Sabtu";
                weeks = "6";
            } else {
                cycle = cycle + "," + "Sabtu";
                weeks = weeks + "," + "6";
            }
        }
        if (repeat / 64 == 1) {
            if ("".equals(cycle)) {
                cycle = "Minggu";
                weeks = "7";
            } else {
                cycle = cycle + "," + "Minggu";
                weeks = weeks + "," + "7";
            }
        }

        return flag == 0 ? cycle : weeks;
    }

}
