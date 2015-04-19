package world.defined;

import world.Rule;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class ConwayRule implements Rule{
    
    public final static ConwayRule INSTANCE = new ConwayRule();
    
    private ConwayRule(){}

    @Override
    public boolean newValue(boolean value, int neighborCount) {
        return (value && (neighborCount == 3 || neighborCount == 2)) || (!value && neighborCount == 3);
    }

    @Override
    public String toRleCode() {
        return "B3/S23";
    }
    
    
    
}
