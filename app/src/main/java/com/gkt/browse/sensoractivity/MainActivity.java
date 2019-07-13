package com.gkt.browse.sensoractivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button motion;
    private ArrayList<String> motionType;
    private ArrayAdapter<String>motionAdapter;
    private SensorManager sensorManager;
    private Sensor sensor;
    ListView motionList;
    private boolean gravit_sensor=false;
    private boolean linear_sensor = false;
    private ArrayList<Float>linear_sens_val;
    private boolean rotation = false;
    private boolean step = false;
    private boolean stepDetect = false;
    private int counter=0;
    private boolean gameRotation = false;
    private ListView postionList;
    private boolean geo_gameRotation = false;
    private boolean magneticSensor = false;
    private boolean deviceOrient = false;
    private boolean proximitySensor = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        motion = (Button)findViewById(R.id.motion);
        motionType = new ArrayList<>();
        linear_sens_val = new ArrayList<>();

        if (sensor==null)
            linearInfo();

        motionType.add("gravity");
        motionType.add("linear");
        motionType.add("rotationVector");
        motionType.add("stepCounter");
        motionType.add("stepDetector");
        motion = (Button)findViewById(R.id.motion);
        motionList = (ListView)findViewById(R.id.motionList);
        postionList=(ListView)findViewById(R.id.positionList);

        motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(counter%2==0)
                {
                    motionList.setVisibility(View.VISIBLE);
                    counter++;
                }
                else
                {
                    motionList.setVisibility(View.INVISIBLE);
                    counter++;
                }

            }
        });

        motionAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,motionType);
        motionList.setAdapter(motionAdapter);

        motionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                   case 0:

                       gravityInfo();
                    break;

                    case 1:
                        linearInfo();
                        Log.i("array",linear_sens_val.toString());
                        break;

                    case 2:
                        rotationInfo();
                        break;

                    case 3:
                        stepCounter();
                        break;

                    case 4:
                        stepDetector();
                        break;
                }
            }
        });

        ArrayList<String> position = new ArrayList<>();
        position.add("game Rotation vector");
        position.add("geometric RotationVect");
        position.add("device orientation");
        position.add("geomagneticFieldSensor");
        position.add("proximitySensor");

        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,position);

        postionList.setAdapter(positionAdapter);

        postionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {

                    case 0:
                        gameRotation();
                        break;

                    case 1:
                        geoMagneticRotation();
                        break;

                    case 2:
                        deviceOrientation();
                        break;

                    case 3:
                        geoFieldSensor();
                        break;

                    case 4:
                        proximitySensor();
                        break;
                }
            }
        });

    }

    private void gravityInfo()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (sensor!=null)
        {

            Log.i("GravitySensor","available");
            gravit_sensor = true;
        }
        else
            Log.i("GravitySensor","Not available");
    }

    private void linearInfo()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensor!=null)
        {
            Log.i("AccelerometerSensor","available");
            linear_sensor = true;

        }
        else
        {
            Log.i("AccelerometerSensor","notAvailable");
        }
    }

    private void rotationInfo()
    {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        if (sensor!=null)
        {
            Log.i("RotationVectorSensor","available");
            rotation = true;
        }
        else
        {
            Log.i("RotationVectorSensor","not Available");
        }
    }

    private void stepCounter()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(sensor!=null)
        {
            Log.i("StepCounterSensor:","avaialbale");
            step = true;
        }
        else
        {
            Log.i("StepCounterSensor","not Available");
        }
    }

    private void stepDetector()
    {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        if (sensor!=null)
        {
            Log.i("StepDetecorSensor","available");
            stepDetect = true;
        }
        else {
            Log.i("StepDetectorSensor","not available");
        }
    }

    public void gameRotation()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);

        if(sensor!=null)
        {
            Log.i("Game_Rotation_Vector","available");
            gameRotation = true;
        }

        else
        {
            Log.i("Game_Rotation_Vector","not available");

        }
    }

    public void geoMagneticRotation()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);

        if(sensor!=null)
        {
            Log.i("GeoGame_Rotation_Vector","available");
            geo_gameRotation = true;
        }
        else
        {
            Log.i("GeoGame_Rotation_Vector","not available");
        }
    }

    public void deviceOrientation()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if(sensor!=null)
        {
            Log.i("DeviceRotation","available");
            deviceOrient = true;
        }
        else
            {

                Log.i("DeviceRotation","not available");
        }
    }

    public void geoFieldSensor()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);

        if (sensor!=null)
        {
            Log.i("Magnetic_fieldSensor","available");
            magneticSensor = true;
        }

        else
        {
            Log.i("Magnetic_fieldSensor","not available");
        }
    }

    public void proximitySensor()
    {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensor!=null)
        {
            Log.i("ProximitySensor","available");
            proximitySensor = true;
        }
        else
        {
            Log.i("ProximitySensor","not available");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


       if(sensor!=null && gravit_sensor )
        {
            float force_x = event.values[0];
            float force_y = event.values[1];
            float force_z = event.values[2];

            linear_sens_val.add(force_x);
            linear_sens_val.add(force_y);
            linear_sens_val.add(force_z);

            Log.i("force_gravity","x:"+force_x+"y:"+force_y+"z:"+force_z+" in m/s2");

        }

        if(sensor!=null && linear_sensor)
        {

            float force_x = event.values[0];
            float force_y = event.values[1];
            float force_z = event.values[2];

            linear_sens_val.add(force_x);
            linear_sens_val.add(force_y);
            linear_sens_val.add(force_z);

            Log.i("linearAccelaration:","x:"+force_x+"y:"+force_y+"z:"+force_z+" in m/s2");
        }

        if (sensor!=null && rotation)
        {

            float rotate_x = event.values[0];
            float rotate_y = event.values[1];
            float rotate_z = event.values[2];
            float scalar = event.values[3];
            Log.i("RotationVector","x:"+rotate_x+" y:"+rotate_y+"z:"+rotate_z+"scalar:"+scalar);
        }

      // Log.i("hello","hello");

        if (sensor!=null && step)
        {

            float dist = event.values[0];
            Log.i("stepCounter"," "+dist);
        }

        if (sensor!=null && stepDetect)
        {
            float dist = event.values[0];
            Log.i("stepDetector",""+dist);
        }

        if (sensor!=null && gameRotation)
        {

            float rotate_x = event.values[0];
            float rotate_y = event.values[1];
            float rotate_z = event.values[2];
           // float scalar = event.values[3];
            Log.i("Game_RotationVector","x:"+rotate_x+" y:"+rotate_y+"z:"+rotate_z+"scalar:");
        }

        if (sensor!=null && geo_gameRotation)
        {
            float rotate_x = event.values[0];
            float rotate_y = event.values[1];
            float rotate_z = event.values[2];
            // float scalar = event.values[3];
            Log.i("GeoGame_RotationVector","x:"+rotate_x+" y:"+rotate_y+"z:"+rotate_z+"scalar:");
        }

        if (sensor!=null && deviceOrient)
        {
            float azimuth = event.values[0];
            float pitch = event.values[1];
            float roll = event.values[2];
            // float scalar = event.values[3];
            Log.i("GeoGame_RotationVector","x:"+azimuth+" y:"+pitch+"z:"+roll+"scalar:");
        }

        if (sensor!=null && magneticSensor)
        {

            float geomagnetic_x = event.values[0];
            float geomagnetic_y = event.values[1];
            float geomagnetic_z = event.values[2];
        //    float iron_x = event.values[3];
          //  float iron_y = event.values[4];
          //  float iron_z = event.values[5];
            // float scalar = event.values[3];
            Log.i("GeoGame_RotationVector","x:"+geomagnetic_x+" y:"+geomagnetic_y+"z:"+geomagnetic_z+"iron_x:");

        }

        if (sensor!=null && proximitySensor)
        {
            float distance = event.values[0];

            Log.i("ProximityDistance","dist="+distance);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        //Log.i("hello","hello");
    }

    @Override
    protected void onResume() {
        super.onResume();
       sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
