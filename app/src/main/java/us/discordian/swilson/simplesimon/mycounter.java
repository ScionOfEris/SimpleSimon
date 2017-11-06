package us.discordian.swilson.simplesimon;

/**
 * Created by swilson on 9/22/2017.
 */

public class mycounter {

    static int counter = 0 ;

    public static boolean setCounter (int setcount ) {
        counter = setcount;
        return true;
    }

    public static int getCounter () {
        return counter ;
    }


    public static boolean resetCounter() {
        counter = 0;
        return true;
    }

    public static boolean addOneToCounter() {
        counter = counter + 1 ;
        return true;
    }

}
