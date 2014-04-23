package marimon.monad;

import java.util.Arrays;

/**
 * Created by ignasi on 22/04/14.
 */
public class Lists {
    public static <T> List<T> asList(T[] t) {
        if(t.length>0) return Lists.list(t) ;
        else return Lists.nil();
    }

    static <T> List<T> list(T[] t){
return new Const(Arrays.asList(t));
    }
    static <T> List<T> nil(){
        return new Nil();
    }
}
