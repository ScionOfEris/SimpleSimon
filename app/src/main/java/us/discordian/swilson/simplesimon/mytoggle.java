package us.discordian.swilson.simplesimon;

import android.util.Log;

/**
 * Created by swilson on 9/22/2017.
 */

public class mytoggle {

    static boolean toggle = false ;

    public static boolean setToggle (boolean settog ) {

        Log.d("toggle", "Setting toggle to: " + settog );

        toggle = settog;
        return true;
    }

    public static boolean getToggle () {
        return toggle ;
    }


}
