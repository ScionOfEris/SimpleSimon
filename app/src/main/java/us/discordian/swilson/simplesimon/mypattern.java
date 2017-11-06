package us.discordian.swilson.simplesimon;

import android.util.Log;
import android.view.ViewDebug;

import java.util.ArrayList;

/**
 * Created by swilson on 9/22/2017.
 */

public class mypattern {

    static
    {
        Log.d("pattern", "Starting pattern" );
    }


    static ArrayList<Integer> pattern = new ArrayList<Integer>();

    public static Integer getOneFromPattern ( int place ) {

        Log.d("pattern", "pattern length: " + Integer.toString(pattern.size()));
        Log.d("pattern", "pattern place requested: " + place);

        return pattern.get(place) ;
    }

    public static boolean addOneToPattern ( ) {

        Log.d("pattern", "in addone");

        Log.d("pattern", "pattern length: " + Integer.toString(pattern.size()));

        // initialize our temp variables
        Long newnumlong ;
        Integer newnum ;

        if (patternLength() == 0) {

            newnumlong = System.currentTimeMillis() % 4;
            newnum = newnumlong.intValue();
            newnum = newnum + 1;
        } else {
            // Use mod 3 instead of 4, so we can avoid repeats
            newnumlong = System.currentTimeMillis() % 3;
            newnum = newnumlong.intValue();
            // don't want 0-2, but rather 1-3
            newnum = newnum + 1 ;

        // do this to not have repeats.
            if (newnum >= getOneFromPattern(patternLength() - 1 )) {
            newnum = newnum + 1;
            }
        }

        Log.d("pattern", "expanding capacity");
        pattern.ensureCapacity(pattern.size() + 1 );
        Log.d("pattern", "adding to pattern");
        pattern.add(newnum);

        return true ;
    }

    public static boolean clearPattern () {
        pattern.clear();
        return true;
    }

    public static int patternLength(){
        return pattern.size() ;
    }

}
