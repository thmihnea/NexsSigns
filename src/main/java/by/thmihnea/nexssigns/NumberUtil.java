package by.thmihnea.nexssigns;

public class NumberUtil {

    public static boolean isInteger(String supposedInteger) {
        try {
            Integer.parseInt(supposedInteger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
