import java.util.Arrays;

public class JUnitLauncher {

    public static void main(String[] args) {

        // Basic parameters' presence check
        if (args.length == 0) {
            throw new NullPointerException("Params are missing!!! Please check the command!!!");
        } else {
            System.out.println("The Following parameters were passed >>> " + Arrays.toString(args));
        }

    }

}
