package us.discordian.swilson.simplesimon;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    // huh... apparently funk static is necessary for this outside of other functions
    static {
        Log.d("init", "Starting program");
    }

    // initialize toggle
    // it should be void realy, but wasn't working that way
    static {
        Log.d("init", "initializing toggle");
    }

    boolean junk = mytoggle.setToggle(false);
    // initialize other stuff
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    ToneGenerator tonegen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Main", "in onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);


        // initialize pattern
        Log.d("Main", "initializing pattern");
        mypattern.addOneToPattern();
        Log.d("Main", "initial pattern length: " + mypattern.patternLength());
        Log.d("Main", "initial pattern element: " + mypattern.getOneFromPattern(0));


        tonegen = new ToneGenerator(AudioManager.STREAM_MUSIC, 75);

        //runs without a timer by reposting this handler at the end of the runnable
        Log.d("Main", "setting up timer");
        final Handler timerHandler = new Handler();
        final Runnable timerRunnable = new Runnable() {


            @Override
            public void run() {

                Log.d("timer", "begining timer run");
                // Play next note in pattern, but wait 1 second before doing anything
                // to do this, we just skip 2 on counter
                int tmpcounter = mycounter.getCounter();
                Log.d("timer", "tmpcounter: " + tmpcounter);

                if (tmpcounter >= mypattern.patternLength() ) {
                    // we're done, but reset stuff
                    button1.setBackgroundResource(R.color.button1low);
                    button2.setBackgroundResource(R.color.button2low);
                    button3.setBackgroundResource(R.color.button3low);
                    button4.setBackgroundResource(R.color.button4low);

                    // give the user back the UI
                    mytoggle.setToggle(true);

                    // reset the counter
                    mycounter.resetCounter();
                } else {

                    Log.d("timer", "next pattern element: " + mypattern.getOneFromPattern(tmpcounter));

                    switch (mypattern.getOneFromPattern(tmpcounter)) {
                        case 1:
                            button1.setBackgroundResource(R.color.button1low);
                            button2.setBackgroundResource(R.color.button2low);
                            button3.setBackgroundResource(R.color.button3low);
                            button4.setBackgroundResource(R.color.button4low);

                            button1.setBackgroundResource(R.color.button1high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_1, 450);
                            break;
                        case 2:
                            button1.setBackgroundResource(R.color.button1low);
                            button2.setBackgroundResource(R.color.button2low);
                            button3.setBackgroundResource(R.color.button3low);
                            button4.setBackgroundResource(R.color.button4low);

                            button2.setBackgroundResource(R.color.button2high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_2, 450);
                            break;
                        case 3:
                            button1.setBackgroundResource(R.color.button1low);
                            button2.setBackgroundResource(R.color.button2low);
                            button3.setBackgroundResource(R.color.button3low);
                            button4.setBackgroundResource(R.color.button4low);

                            button3.setBackgroundResource(R.color.button3high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_3, 450);
                            break;
                        case 4:
                            button1.setBackgroundResource(R.color.button1low);
                            button2.setBackgroundResource(R.color.button2low);
                            button3.setBackgroundResource(R.color.button3low);
                            button4.setBackgroundResource(R.color.button4low);

                            button4.setBackgroundResource(R.color.button4high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_A, 450);
                            break;

                    }
                    mycounter.addOneToCounter();
                    timerHandler.postDelayed(this, 500);
                }
            }
        };

        // Starting pattern, first fun
        Log.d("button5", "Starting pattern");
        timerHandler.postDelayed(timerRunnable, 2000);

    }


    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        Button m1 = (Button) findViewById(R.id.button1);
        Button m2 = (Button) findViewById(R.id.button2);
        Button m3 = (Button) findViewById(R.id.button3);
        Button m4 = (Button) findViewById(R.id.button4);

        Log.d("TouchTag", "In onTouch");

        // going with True = User's Turn
        boolean mytog = mytoggle.getToggle();

        // do nothing unless it is the user's turn
        if (mytog) {
            Log.d("TouchTag", "mycounter.getCounter: " + mycounter.getCounter());
            Log.d("TouchTag", "mypattern.patternLength: " + mypattern.patternLength());

            //runs without a timer by reposting this handler at the end of the runnable
            Log.d("TouchTag", "setting up timer");
            final Handler timerHandler = new Handler();
            final Runnable timerRunnable = new Runnable() {

                @Override
                public void run() {

                    Log.d("timer", "begining timer run");
                    // Play next note in pattern
                    int tmpcounter = mycounter.getCounter() ;
                    Log.d("timer", "tmpcounter: " + tmpcounter);

                    if (tmpcounter >= mypattern.patternLength() ) {
                        // we're done, but reset stuff
                        button1.setBackgroundResource(R.color.button1low);
                        button2.setBackgroundResource(R.color.button2low);
                        button3.setBackgroundResource(R.color.button3low);
                        button4.setBackgroundResource(R.color.button4low);

                        // give the user back the UI
                        mytoggle.setToggle(true);

                        // reset the counter
                        mycounter.resetCounter();

                    } else {

                        Log.d("timer", "next pattern element: " + mypattern.getOneFromPattern(tmpcounter));

                        switch (mypattern.getOneFromPattern(tmpcounter)) {
                            case 1:
                                button1.setBackgroundResource(R.color.button1low);
                                button2.setBackgroundResource(R.color.button2low);
                                button3.setBackgroundResource(R.color.button3low);
                                button4.setBackgroundResource(R.color.button4low);

                                button1.setBackgroundResource(R.color.button1high);
                                tonegen.startTone(ToneGenerator.TONE_DTMF_1, 450);
                                break;
                            case 2:
                                button1.setBackgroundResource(R.color.button1low);
                                button2.setBackgroundResource(R.color.button2low);
                                button3.setBackgroundResource(R.color.button3low);
                                button4.setBackgroundResource(R.color.button4low);

                                button2.setBackgroundResource(R.color.button2high);
                                tonegen.startTone(ToneGenerator.TONE_DTMF_2, 450);
                                break;
                            case 3:
                                button1.setBackgroundResource(R.color.button1low);
                                button2.setBackgroundResource(R.color.button2low);
                                button3.setBackgroundResource(R.color.button3low);
                                button4.setBackgroundResource(R.color.button4low);

                                button3.setBackgroundResource(R.color.button3high);
                                tonegen.startTone(ToneGenerator.TONE_DTMF_3, 450);
                                break;
                            case 4:
                                button1.setBackgroundResource(R.color.button1low);
                                button2.setBackgroundResource(R.color.button2low);
                                button3.setBackgroundResource(R.color.button3low);
                                button4.setBackgroundResource(R.color.button4low);

                                button4.setBackgroundResource(R.color.button4high);
                                tonegen.startTone(ToneGenerator.TONE_DTMF_A, 450);
                                break;

                        }
                        mycounter.addOneToCounter();
                        timerHandler.postDelayed(this, 500);
                    }
                }
            };

            switch (v.getId()) {
                case R.id.button1:
                    //do stuff for button 1
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            m1.setBackgroundResource(R.color.button1high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_1);
                            Log.d("TouchTag", "Action was DOWN");
                            break;
                        case MotionEvent.ACTION_UP:
                            m1.setBackgroundResource(R.color.button1low);
                            tonegen.stopTone();

                            // check if button was correct
                            if (mypattern.getOneFromPattern(mycounter.getCounter()) == 1) {
                                if (mycounter.getCounter() == mypattern.patternLength() - 1) {
                                    //win
                                    Log.d("TouchTag", "You win");
                                    tonegen.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);

                                    mycounter.resetCounter();
                                    mypattern.addOneToPattern();

                                    // turn off UI
                                    mytoggle.setToggle(false);
                                    // Play in a second and a half
                                    timerHandler.postDelayed(timerRunnable, 1500);
                                } else {
                                    //more to go
                                    mycounter.addOneToCounter();
                                }

                            } else {
                                // BBZZZT
                                tonegen.startTone(ToneGenerator.TONE_PROP_NACK, 1000);
                                mycounter.resetCounter();
                                // turn off UI
                                mytoggle.setToggle(false);
                                // Play in a second and a half
                                timerHandler.postDelayed(timerRunnable, 1500);
                            }

                            Log.d("TouchTag", "Action was UP");
                            break;
                        default:
                            Log.d("TouchTag", "action was.... something");
                            break;
                    }
                    Log.d("myTag", "button 1");
                    break;
                case R.id.button2:
                    //do stuff for button 2
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            m2.setBackgroundResource(R.color.button2high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_2);
                            Log.d("TouchTag", "Action was DOWN");
                            break;
                        case MotionEvent.ACTION_UP:
                            m2.setBackgroundResource(R.color.button2low);
                            tonegen.stopTone();

                            // check if button was correct
                            if (mypattern.getOneFromPattern(mycounter.getCounter()) == 2) {
                                if (mycounter.getCounter() == mypattern.patternLength() - 1) {
                                    //win
                                    Log.d("TouchTag", "You win");
                                    tonegen.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);

                                    mycounter.resetCounter();
                                    mypattern.addOneToPattern();
                                    // turn off UI
                                    mytoggle.setToggle(false);
                                    // Play in a second and a half
                                    timerHandler.postDelayed(timerRunnable, 1500);
                                } else {
                                    //more to go
                                    mycounter.addOneToCounter();
                                }
                            } else {
                                // BBZZZT
                                tonegen.startTone(ToneGenerator.TONE_PROP_NACK, 1000);
                                mycounter.resetCounter();
                                // turn off UI
                                mytoggle.setToggle(false);
                                // Play in a second and a half
                                timerHandler.postDelayed(timerRunnable, 1500);

                            }
                            Log.d("TouchTag", "Action was UP");
                            break;
                        default:
                            Log.d("TouchTag", "action was.... something");
                            break;
                    }
                    Log.d("myTag", "button 2");
                    break;
                case R.id.button3:
                    //do stuff for button 2
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            m3.setBackgroundResource(R.color.button3high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_3);

                            Log.d("TouchTag", "Action was DOWN");
                            break;
                        case MotionEvent.ACTION_UP:

                            m3.setBackgroundResource(R.color.button3low);
                            tonegen.stopTone();

                            // check if button was correct
                            if (mypattern.getOneFromPattern(mycounter.getCounter()) == 3) {
                                if (mycounter.getCounter() == mypattern.patternLength() - 1) {
                                    //win
                                    Log.d("TouchTag", "You win");
                                    tonegen.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);

                                    mycounter.resetCounter();
                                    mypattern.addOneToPattern();
                                    // turn off UI
                                    mytoggle.setToggle(false);
                                    // Play in a second and a half
                                    timerHandler.postDelayed(timerRunnable, 1500);
                                } else {
                                    //more to go
                                    mycounter.addOneToCounter();
                                }
                            } else {
                                // BBZZZT
                                tonegen.startTone(ToneGenerator.TONE_PROP_NACK, 1000);
                                mycounter.resetCounter();
                                // turn off UI
                                mytoggle.setToggle(false);
                                // Play in a second and a half
                                timerHandler.postDelayed(timerRunnable, 1500);
                            }
                            Log.d("TouchTag", "Action was UP");
                            break;
                        default:
                            Log.d("TouchTag", "action was.... something");
                            break;
                    }
                    Log.d("myTag", "button 3");
                    break;
                case R.id.button4:
                    //do stuff for button 2
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:

                            m4.setBackgroundResource(R.color.button4high);
                            tonegen.startTone(ToneGenerator.TONE_DTMF_A);

                            Log.d("TouchTag", "Action was DOWN");
                            break;
                        case MotionEvent.ACTION_UP:
                            m4.setBackgroundResource(R.color.button4low);
                            tonegen.stopTone();

                            // check if button was correct
                            if (mypattern.getOneFromPattern(mycounter.getCounter()) == 4) {
                                if (mycounter.getCounter() == mypattern.patternLength() - 1) {
                                    //win
                                    Log.d("TouchTag", "You win");
                                    tonegen.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 1000);

                                    mycounter.resetCounter();
                                    mypattern.addOneToPattern();
                                    // turn off UI
                                    mytoggle.setToggle(false);
                                    // Play in a second and a half
                                    timerHandler.postDelayed(timerRunnable, 1500);
                                } else {
                                    //more to go
                                    mycounter.addOneToCounter();
                                }
                            } else {
                                // BBZZZT
                                tonegen.startTone(ToneGenerator.TONE_PROP_NACK, 1000);
                                mycounter.resetCounter();
                                // turn off UI
                                mytoggle.setToggle(false);
                                // Play in a second and a half
                                timerHandler.postDelayed(timerRunnable, 1500);
                            }

                            Log.d("TouchTag", "Action was UP");
                            break;
                        default:
                            Log.d("TouchTag", "action was.... something");
                            break;
                    }
                    Log.d("myTag", "button 4");
                    break;

                default:
                    //error
                    Log.d("myTag", "error, should not be here");
                    Log.d("myTag", Integer.toString(v.getId()));
                    break;
            }
            return true;
        }
        return true;
    }
}