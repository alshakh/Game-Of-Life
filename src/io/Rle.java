package io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import world.defined.ConwayRule;
import world.Rule;

/**
 * DESIGN_PATTERN : Lazy implementation for toWorldState. using cached
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class Rle {

    public static int LINE_WIDTH_LIMIT = 70;
    public static char LIVE = 'o';
    public static char DEAD = 'b';
    public static char EOL = '$'; // end of line.
    public static char EOF = '!';

    private final String rleFileContents;
    private WorldState myCechedWorldState = null;

    public Rle(String contents) {
        this.rleFileContents = contents;
    }

    public String getContents() {
        return rleFileContents;
    }
    // \s*x\s*=\s*(\d+)\s*,\s*y\s*=\s*(\d+)\s*,\s*rule\s*=\s*([\w\/]+)
    static final Pattern RULE_PATTERN = Pattern.compile("\\s*x\\s*=\\s*(\\d+)\\s*,\\s*y\\s*=\\s*(\\d+)\\s*,\\s*rule\\s*=\\s*([\\w\\/]+)");


    /**
     *
     * @return
     */
    public WorldState toWorldState() throws UnsupportedRuleException {
        if (myCechedWorldState != null) {
            return myCechedWorldState;
        }
        boolean[][] data = null;
        boolean inited = false;
        int X = 0, Y = 0; // size of file.
        int x = 0, y = 0;       //  Current position
        int k = 0;       //  Repeat count
        int DIM = 0;
        Rule rule = ConwayRule.INSTANCE;
        for (String line : rleFileContents.split("\\n")) {
            line = line.trim();
            // discard empty lines
            if (line.isEmpty()) {
                continue;
            }
            //  Skip header (starts with #)
            if (line.startsWith("#")) {
                continue;
            }
            // Read header info
            Matcher tmpM = RULE_PATTERN.matcher(line);
            if (tmpM.matches()) {
                X = Integer.parseInt(tmpM.group(1));
                Y = Integer.parseInt(tmpM.group(2));
                rule = parseRule(tmpM.group(3).trim());
                DIM = (X > Y ? X : Y);
                data = new boolean[DIM][DIM];
                inited = true;
                continue;
            }
            // Shouldn't reach this point with inited == false. can add in empty array.
            if (!inited) {
                return null; // Big Problem. !!!!!! no header line.
            }
            // start filling data.
            for (char ch : line.toCharArray()) {
                if (ch == Rle.EOF) {
                    break; // end of file marker.
                }
                //  Live cell(s)
                if (ch == Rle.LIVE) {
                    //  k==0 => one
                    if (k <= 0) {
                        k = 1;
                    }
                    //  Draw k live cells in a row
                    for (int i = 0; i < k; i++) {
                        data[x+i][y] = true;
                    }
                    x += k;
                    k = 0;
                } //  Dead cell(s)
                else if (ch == Rle.DEAD) {
                    x += (k > 0 ? k : 1);
                    k = 0;
                } //  End of Line
                else if (ch == Rle.EOL) {
                    y += (k > 0 ? k : 1);
                    x = 0;
                    k = 0;
                } //  Repeat count
                else if (Character.isDigit(ch)) {
                    k = 10 * k + Integer.parseInt(String.valueOf(ch));
                }
            }
        }
        myCechedWorldState = new WorldState(DIM, rule, data);
        return myCechedWorldState;
    }
    
    
    public final Pattern RULE_CODE_PATTERN = Pattern.compile("B(\\d+)/S(\\d+)");
    public RleRule parseRule(String ruleCode) throws UnsupportedRuleException{
        Matcher matcher = RULE_CODE_PATTERN.matcher(ruleCode);
        if(!matcher.matches()) {
            throw new UnsupportedRuleException(ruleCode + " rules is not supported");
        }
        char[] B = matcher.group(1).toCharArray();
        char[] S = matcher.group(2).toCharArray();
        
        int[] survive = new int[S.length];
        for(int i = 0 ; i < S.length ; i++){
            survive[i] = Integer.parseInt(String.valueOf(S[i]));
        }
        
        int[] live = new int[B.length];
        for(int i=0 ; i< B.length ; i++){
           
            live[i] =Integer.parseInt(String.valueOf(B[i]));
        }
        
        return new RleRule(survive, live);
    }
    
    public static Rle createRle(int dim, Rule rule, boolean[][] data) {

        int i_0 = dim; // max
        int j_0 = dim; // max
        int i_f = 0; // min
        int j_f = 0; // min
        for(int i = 0 ; i < dim; i++){
            for(int j=0 ; j<dim; j++){
                if(data[i][j]) {
                    if(i<i_0) i_0 = i;
                    if(j<j_0) j_0 = j;
                    if(i>i_f) i_f = i;
                    if(j>j_f) j_f = j;
                }
            }
        }
        int X = i_f-i_0;
        int Y = j_f-j_0;
        
        StringBuilder sb = new StringBuilder();
        sb.append("# generated by GameOfLife : github.com/alshakh/GameOfLifeOOp").append("\n");
        // header
        sb.append("x=").append(X).append(", y=").append(Y).append(", rule=").append(rule.toRleCode()).append("\n");
        //
        sb.append(run(i_0,i_f,j_0,j_f,data));
        return new Rle(sb.toString());
    }

    private static String run(int i_0,int i_f,int j_0,int j_f,boolean[][] data) {
        //
        final int X = i_f-i_0;
        final int Y = j_f-j_0;
        
        StringBuilder stringData = new StringBuilder();

        for (int i = 0 ; i < X; i++) {
            for (int j = 0 ; j<Y ; j++) {
                stringData.append((data[i][j] ? Rle.LIVE : Rle.DEAD));
            }
            stringData.append(Rle.EOL);
        }
        stringData.append(Rle.EOF);
        //

        // Compress.
        StringBuilder compressedData = new StringBuilder();
        for (int i = 0; i < stringData.length(); i++) {
            int runLength = 1;
            while (i + 1 < stringData.length() && stringData.charAt(i) == stringData.charAt(i + 1)) {
                runLength++;
                i++;
            }
            if(runLength>1) compressedData.append(runLength); 
            compressedData.append(stringData.charAt(i));
        }
        // fix output line length
        int lineCount = compressedData.length()/Rle.LINE_WIDTH_LIMIT;
        
        for ( int i = 0 ; i < lineCount ; i++) {
            compressedData.insert(Rle.LINE_WIDTH_LIMIT*i+i, '\n');
        }
        
        if(compressedData.charAt(compressedData.length()-1) == '\n') compressedData.deleteCharAt(compressedData.length()-1);
        return compressedData.toString();
    }
}
