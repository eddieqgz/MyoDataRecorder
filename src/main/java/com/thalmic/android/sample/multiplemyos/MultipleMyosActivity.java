/*
 * Copyright (C) 2014 Thalmic Labs Inc.
 * Distributed under the Myo SDK license agreement. See LICENSE.txt for details.
 */

package com.thalmic.android.sample.multiplemyos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// This sample illustrates how to attach to multiple Myo devices and distinguish between them.

public class MultipleMyosActivity extends Activity {
    private static final String TAG = "MultipleMyosActivity";

    // We store each Myo object that we attach to in this list, so that we can keep track of the order we've seen
    // each Myo and give it a unique short identifier (see onAttach() and identifyMyo() below).
    private ArrayList<Myo> mKnownMyos = new ArrayList<Myo>();

    private MyoAdapter mAdapter;

    private Button button;

    private Button button2;

    private TextView text;

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;
    private TextView textView17;
    private TextView textView18;
    private TextView textView19;
    private TextView textView20;
    private TextView textView21;
    private TextView textView22;
    private TextView textView23;
    private TextView textView24;
    private TextView textView25;
    private TextView textView26;
    private TextView textView27;
    private TextView textView28;
    private TextView textView29;
    private TextView textView30;
    private TextView textView31;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;
    private TextView textView35;
    private TextView textView36;
    private TextView textView37;
    private TextView textView38;
    private TextView textView39;
    private TextView textView40;
    private TextView textView41;
    private TextView textView42;
    private TextView textView43;
    private TextView textView44;
    private TextView textView45;
    private TextView textView46;
    private TextView textView47;
    private TextView textView48;

    FileWriter orientationWriter1 = null;

    FileWriter orientationWriter2 = null;

    FileWriter accelerometerWriter1 = null;

    FileWriter accelerometerWriter2 = null;

    FileWriter gyroWriter1 = null;

    FileWriter gyroWriter2 = null;

    File orientation1;

    File orientation2;

    File accelerometer1;

    File accelerometer2;

    File gyro1;

    File gyro2;

    protected int ready = 0;


    private DeviceListener mListener = new AbstractDeviceListener() {

        // Every time the SDK successfully attaches to a Myo armband, this function will be called.
        //
        // You can rely on the following rules:
        //  - onAttach() will only be called once for each Myo device
        //  - no other events will occur involving a given Myo device before onAttach() is called with it
        //
        // If you need to do some kind of per-Myo preparation before handling events, you can safely do it in onAttach().
        @Override
        public void onAttach(Myo myo, long timestamp) {

            // The object for a Myo is unique - in other words, it's safe to compare two Myo references to
            // see if they're referring to the same Myo.

            // Add the Myo object to our list of known Myo devices. This list is used to implement identifyMyo() below so
            // that we can give each Myo a nice short identifier.
            mKnownMyos.add(myo);

            // Now that we've added it to our list, get our short ID for it and print it out.
            Log.i(TAG, "Attached to " + myo.getMacAddress() + ", now known as Myo " + identifyMyo(myo) + ".");
        }

        @Override
        public void onConnect(Myo myo, long timestamp) {
            mAdapter.setMessage(myo, "Myo " + identifyMyo(myo) + " has connected.");
        }

        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
            mAdapter.setMessage(myo, "Myo " + identifyMyo(myo) + " is unsync.");
            if (ready == 1) {
                ready = 0;
                Toast.makeText(MultipleMyosActivity.this, "Myo" + identifyMyo(myo) + "unsync, data recording stop", Toast.LENGTH_SHORT).show();
                text.setText("Push button to record data!");
            }
            try {
                gyroWriter1.close();
                gyroWriter2.close();
                accelerometerWriter1.close();
                accelerometerWriter2.close();
                orientationWriter1.close();
                orientationWriter2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            mAdapter.setMessage(myo, "Myo " + identifyMyo(myo) + " has disconnected.");
            if (ready == 1) {
                ready = 0;
                Toast.makeText(MultipleMyosActivity.this, "Myo" + identifyMyo(myo) + "disconnected, data recording stop", Toast.LENGTH_SHORT).show();
                text.setText("Push button to record data!");
            }
            try {
                gyroWriter1.close();
                gyroWriter2.close();
                accelerometerWriter1.close();
                accelerometerWriter2.close();
                orientationWriter1.close();
                orientationWriter2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            mAdapter.setMessage(myo, "Myo " + identifyMyo(myo) + " switched to pose " + pose.toString() + ".");
        }

        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            if (ready == 1) {
                double x = rotation.x();
                double y = rotation.y();
                double z = rotation.z();
                double w = rotation.w();
                writeOrientationCSV(timestamp, x, y, z, w, MyoConnectWriter(myo));
                if((identifyMyo(myo) == 1) && ((((int)System.currentTimeMillis())%10) == (-5))){
                    textView5.setText(x+"");
                    textView7.setText(y+"");
                    textView9.setText(z+"");
                    textView11.setText(w+"");
                }else if ((identifyMyo(myo) == 2) && ((((int)System.currentTimeMillis())%10) == (-5))){
                    textView17.setText(x+"");
                    textView18.setText(y+"");
                    textView19.setText(z+"");
                    textView20.setText(w+"");
                }
                //rotation.roll(rotation);
            }
        }

        @Override
        public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
            if (ready == 1) {
                double x = accel.x();
                double y = accel.y();
                double z = accel.z();
                writeAccelerometerCSV(timestamp, x, y, z, MyoConnectACCWriter(myo));
                if((identifyMyo(myo) == 1) && ((((int)System.currentTimeMillis())%10) == (-5))){
                    textView37.setText(x+"");
                    textView38.setText(y+"");
                    textView39.setText(z + "");
                }else if ((identifyMyo(myo) == 2) && ((((int)System.currentTimeMillis())%10) == (-5))){
                    textView43.setText(x+"");
                    textView44.setText(y+"");
                    textView45.setText(z+"");
                }
            }
        }

        @Override
        public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
            if (ready == 1) {
                double x = gyro.x();
                double y = gyro.y();
                double z = gyro.z();
                writeGyroCSV(timestamp, gyro.x(), gyro.y(), gyro.z(), MyoConnectGYROWriter(myo));
                if((identifyMyo(myo) == 1) && ((((int)System.currentTimeMillis())%2) == (-5))){
                    textView40.setText(x+"");
                    textView41.setText(y+"");
                    textView42.setText(z+"");
                }else if ((identifyMyo(myo) == 2) && ((((int)System.currentTimeMillis())%10) == (-5))){
                    textView46.setText(x+"");
                    textView47.setText(y+"");
                    textView48.setText(z+"");
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_myos);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        text = (TextView) findViewById(R.id.text);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView17 = (TextView) findViewById(R.id.textView17);
        textView18 = (TextView) findViewById(R.id.textView18);
        textView19 = (TextView) findViewById(R.id.textView19);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView21 = (TextView) findViewById(R.id.textView21);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView23 = (TextView) findViewById(R.id.textView23);
        textView24 = (TextView) findViewById(R.id.textView24);
        textView25 = (TextView) findViewById(R.id.textView25);
        textView26 = (TextView) findViewById(R.id.textView26);
        textView27 = (TextView) findViewById(R.id.textView27);
        textView28 = (TextView) findViewById(R.id.textView28);
        textView29 = (TextView) findViewById(R.id.textView29);
        textView30 = (TextView) findViewById(R.id.textView30);
        textView31 = (TextView) findViewById(R.id.textView31);
        textView32 = (TextView) findViewById(R.id.textView32);
        textView33 = (TextView) findViewById(R.id.textView33);
        textView34 = (TextView) findViewById(R.id.textView34);
        textView35 = (TextView) findViewById(R.id.textView35);
        textView36 = (TextView) findViewById(R.id.textView36);
        textView37 = (TextView) findViewById(R.id.textView37);
        textView38 = (TextView) findViewById(R.id.textView38);
        textView39 = (TextView) findViewById(R.id.textView39);
        textView40 = (TextView) findViewById(R.id.textView40);
        textView41 = (TextView) findViewById(R.id.textView41);
        textView42 = (TextView) findViewById(R.id.textView42);
        textView43 = (TextView) findViewById(R.id.textView43);
        textView44 = (TextView) findViewById(R.id.textView44);
        textView45 = (TextView) findViewById(R.id.textView45);
        textView46 = (TextView) findViewById(R.id.textView46);
        textView47 = (TextView) findViewById(R.id.textView47);
        textView48 = (TextView) findViewById(R.id.textView48);

        // First, we initialize the Hub singleton.
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Disable standard Myo locking policy. All poses will be delivered.
        hub.setLockingPolicy(Hub.LockingPolicy.NONE);

        final int attachingCount = 2;

        // Set the maximum number of simultaneously attached Myos to 2.
        hub.setMyoAttachAllowance(attachingCount);

        Log.i(TAG, "Attaching to " + attachingCount + " Myo armbands.");

        // attachToAdjacentMyos() attaches to Myo devices that are physically very near to the Bluetooth radio
        // until it has attached to the provided count.
        // DeviceListeners attached to the hub will receive onAttach() events once attaching has completed.
        hub.attachToAdjacentMyos(attachingCount);

        // Next, register for DeviceListener callbacks.
        hub.addListener(mListener);

        // Attach an adapter to the ListView for showing the state of each Myo.
        mAdapter = new MyoAdapter(this, attachingCount);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          //initialize all file here in order to reduce delay
                                          //orientation left and right
                                         long time = System.currentTimeMillis();
                                          orientation1 = new File(Environment.getExternalStorageDirectory(), "/myodata/orientation1_" + time + ".csv");
                                          try {
                                              orientationWriter1 = new FileWriter(orientation1);
                                              orientationWriter1.append("Timestamp");
                                              orientationWriter1.append(",");
                                              orientationWriter1.append("x");
                                              orientationWriter1.append(",");
                                              orientationWriter1.append("y");
                                              orientationWriter1.append(",");
                                              orientationWriter1.append("z");
                                              orientationWriter1.append(",");
                                              orientationWriter1.append("w");
                                              orientationWriter1.append('\n');
                                              orientationWriter1.flush();//??????
                                          } catch (IOException e) {
                                              Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                          }

                                          orientation2 = new File(Environment.getExternalStorageDirectory(), "/myodata/orientation2_" + time + ".csv");
                                              try {
                                                  orientationWriter2 = new FileWriter(orientation2);
                                                  orientationWriter2.append("Timestamp");
                                                  orientationWriter2.append(",");
                                                  orientationWriter2.append("x");
                                                  orientationWriter2.append(",");
                                                  orientationWriter2.append("y");
                                                  orientationWriter2.append(",");
                                                  orientationWriter2.append("z");
                                                  orientationWriter2.append(",");
                                                  orientationWriter2.append("w");
                                                  orientationWriter2.append('\n');
                                                  orientationWriter2.flush();
                                              } catch (IOException e) {
                                                  Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                              }

                                          //accelerometer left and right
                                          accelerometer1 = new File(Environment.getExternalStorageDirectory(), "/myodata/acce1_" + time + ".csv");
                                              try {
                                                  accelerometerWriter1 = new FileWriter(accelerometer1);
                                                  accelerometerWriter1.append("Timestamp");
                                                  accelerometerWriter1.append(",");
                                                  accelerometerWriter1.append("x");
                                                  accelerometerWriter1.append(",");
                                                  accelerometerWriter1.append("y");
                                                  accelerometerWriter1.append(",");
                                                  accelerometerWriter1.append("z");
                                                  accelerometerWriter1.append('\n');
                                                  accelerometerWriter1.flush();
                                              } catch (IOException e) {
                                                  Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                              }

                                          accelerometer2 = new File(Environment.getExternalStorageDirectory(), "/myodata/acce2_" + time + ".csv");
                                              try {
                                                  accelerometerWriter2 = new FileWriter(accelerometer2);
                                                  accelerometerWriter2.append("Timestamp");
                                                  accelerometerWriter2.append(",");
                                                  accelerometerWriter2.append("x");
                                                  accelerometerWriter2.append(",");
                                                  accelerometerWriter2.append("y");
                                                  accelerometerWriter2.append(",");
                                                  accelerometerWriter2.append("z");
                                                  accelerometerWriter2.append('\n');
                                                  accelerometerWriter2.flush();
                                              } catch (IOException e) {
                                                  Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                              }

                                          //gyroScope lefr and right
                                          gyro1 = new File(Environment.getExternalStorageDirectory(), "/myodata/gyro1_" + time + ".csv");
                                              try {
                                                  gyroWriter1 = new FileWriter(gyro1);
                                                  gyroWriter1.append("Timestamp");
                                                  gyroWriter1.append(",");
                                                  gyroWriter1.append("x");
                                                  gyroWriter1.append(",");
                                                  gyroWriter1.append("y");
                                                  gyroWriter1.append(",");
                                                  gyroWriter1.append("z");
                                                  gyroWriter1.append('\n');
                                                  gyroWriter1.flush();
                                              } catch (IOException e) {
                                                  Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                              }

                                          gyro2 = new File(Environment.getExternalStorageDirectory(), "/myodata/gyro2_" + time + ".csv");
                                              try {
                                                  gyroWriter2 = new FileWriter(gyro2);
                                                  gyroWriter2.append("Timestamp");
                                                  gyroWriter2.append(",");
                                                  gyroWriter2.append("x");
                                                  gyroWriter2.append(",");
                                                  gyroWriter2.append("y");
                                                  gyroWriter2.append(",");
                                                  gyroWriter2.append("z");
                                                  gyroWriter2.append('\n');
                                                  gyroWriter2.flush();
                                              } catch (IOException e) {
                                                  Toast.makeText(MultipleMyosActivity.this, "IOexception", Toast.LENGTH_LONG).show();
                                              }

                                          //After initialzing all, trigger the int to begin record data
                                          ready = 1;
                                          text.setText("Data is recording....");
                                      }
                                  }

        );
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ready = 0;
                text.setText("Push button to record data!");
                Toast.makeText(MultipleMyosActivity.this, "File stored successfully under myodata folder", Toast.LENGTH_SHORT).show();
                try {
                    gyroWriter1.close();
                    gyroWriter2.close();
                    accelerometerWriter1.close();
                    accelerometerWriter2.close();
                    orientationWriter1.close();
                    orientationWriter2.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            gyroWriter1.flush();
            gyroWriter1.close();
            gyroWriter2.flush();
            gyroWriter2.close();
            accelerometerWriter1.flush();
            accelerometerWriter1.close();
            accelerometerWriter2.flush();
            accelerometerWriter2.close();
            orientationWriter1.flush();
            orientationWriter1.close();
            orientationWriter2.flush();
            orientationWriter2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // We don't want any callbacks when the Activity is gone, so unregister the listener.
        Hub.getInstance().removeListener(mListener);

        // Shutdown the Hub. This will disconnect any Myo devices that are connected.
        Hub.getInstance().shutdown();
    }

    // This is a utility function implemented for this sample that maps a Myo to a unique ID starting at 1.
    // It does so by looking for the Myo object in mKnownMyos, which onAttach() adds each Myo into as it is attached.
    private int identifyMyo(Myo myo) {
        return mKnownMyos.indexOf(myo) + 1;
    }

    private class MyoAdapter extends ArrayAdapter<String> {

        public MyoAdapter(Context context, int count) {
            super(context, android.R.layout.simple_list_item_1);

            // Initialize adapter with items for each expected Myo.
            for (int i = 0; i < count; i++) {
                add(getString(R.string.waiting_message));
            }
        }

        public void setMessage(Myo myo, String message) {
            // identifyMyo returns IDs starting at 1, but the adapter indices start at 0.
            int index = identifyMyo(myo) - 1;

            // Replace the message.
            remove(getItem(index));
            insert(message, index);
        }
    }


    protected FileWriter MyoConnectWriter(Myo myo) {
        int indexMyo = identifyMyo(myo);
        if (indexMyo == 1) {
            return orientationWriter1;
        } else return orientationWriter2;
    }

    protected FileWriter MyoConnectACCWriter(Myo myo) {
        int indexMyo = identifyMyo(myo);
        if (indexMyo == 1) {
            return accelerometerWriter1;
        } else return accelerometerWriter2;
    }

    protected FileWriter MyoConnectGYROWriter(Myo myo) {
        int indexMyo = identifyMyo(myo);
        if (indexMyo == 1) {
            return gyroWriter1;
        } else return gyroWriter2;
    }

    // specific method for writing CSV file.
    protected void writeOrientationCSV(long timestamp, double x, double y, double z, double w, FileWriter orientationWriter) {
        try {
            orientationWriter.append(new Long(timestamp).toString());
            orientationWriter.append(",");
            orientationWriter.append(new Double(x).toString());
            orientationWriter.append(",");
            orientationWriter.append(new Double(y).toString());
            orientationWriter.append(",");
            orientationWriter.append(new Double(z).toString());
            orientationWriter.append(",");
            orientationWriter.append(new Double(w).toString());
            orientationWriter.append('\n');
            orientationWriter.flush();
        } catch (IOException e) {
            Toast.makeText(this, "IOexception", Toast.LENGTH_LONG).show();
        }
    }

    protected void writeAccelerometerCSV(long timestamp, double x, double y, double z, FileWriter accelerometerWriter) {
        try {
            accelerometerWriter.append(new Long(timestamp).toString());
            accelerometerWriter.append(",");
            accelerometerWriter.append(new Double(x).toString());
            accelerometerWriter.append(",");
            accelerometerWriter.append(new Double(y).toString());
            accelerometerWriter.append(",");
            accelerometerWriter.append(new Double(z).toString());
            accelerometerWriter.append('\n');
            accelerometerWriter.flush();
        } catch (IOException e) {
            Toast.makeText(this, "IOexception", Toast.LENGTH_LONG).show();
        }
    }

    protected void writeGyroCSV(long timestamp, double x, double y, double z, FileWriter gyroWriter) {
        try {
            gyroWriter.append(new Long(timestamp).toString());
            gyroWriter.append(",");
            gyroWriter.append(new Double(x).toString());
            gyroWriter.append(",");
            gyroWriter.append(new Double(y).toString());
            gyroWriter.append(",");
            gyroWriter.append(new Double(z).toString());
            gyroWriter.append('\n');
            gyroWriter.flush();
        } catch (IOException e) {
            Toast.makeText(this, "IOexception", Toast.LENGTH_LONG).show();
        }
    }
}


