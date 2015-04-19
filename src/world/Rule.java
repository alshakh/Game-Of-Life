package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface Rule {
    public boolean newValue(boolean value, int neighborCount);
    public String toRleCode();
}
