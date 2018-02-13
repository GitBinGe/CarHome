package bg.carhome;


import android.util.Log;

public class LogUtils {

    public static boolean isTempDebugOn = true;

    public static void setLogSwitch(boolean logSwitch) {
        isTempDebugOn = logSwitch;
    }

    public static void d(Object log) {
        if (isLogDebugSwitchOn()) {
            log("log_debug", log);
        }
    }

    public static void e(Object log) {
        if (isLogDebugSwitchOn()) {
            error("log_error", log);
        }
    }

    public static void log(String tag, Object log) {
        Log.d(tag, log != null ? log.toString() : "null");
    }

    public static void error(String tag, Object log) {
        Log.e(tag, log != null ? log.toString() : "null");
    }


    public static boolean isLogDebugSwitchOn() {
        return isTempDebugOn;
    }

}
