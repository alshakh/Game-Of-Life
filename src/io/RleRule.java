package io;

import world.Rule;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class RleRule implements Rule{
    private final int[] survive;
    private final int[] live;
    public RleRule(int[] survive, int[] live){
        this.survive = survive;
        this.live = live;
    }
    @Override
    public boolean newValue(boolean value, int neighborCount) {
        if(value && inArray(survive,neighborCount)) {
            return true;
        } else if ( !value && inArray(live,neighborCount)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean inArray(int[] array, int num){
        for(int n : array) {
            if(n == num ) return true;
        }
        return false;
    }

    @Override
    public String toRleCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("B");
        for(int i : live) {
            sb.append(i);
        }
        
        sb.append("/");
        
        sb.append("S");
        for(int i : survive) {
            sb.append(i);
        }
        return sb.toString();
    }
}
