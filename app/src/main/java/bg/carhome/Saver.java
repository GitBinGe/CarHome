package bg.carhome;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by BinGe on 2017/9/13.
 * 轻量级本地化工具，使用SharePrefer
 */

public class Saver {

    /**
     * 保存
     * @param key 保存对应的key，获取时需要用到同一个key
     * @param value 要保存的value
     */
    public static void set(String key, Object value) {
        self().setValue(key, value.toString());
    }

    /**
     * 获取String本地数据
     * @param key 保存时对应的key
     * @param defaultValue 默认值
     * @return
     */
    public static String getString(String key, String defaultValue) {
        return self().getValue(key, defaultValue);
    }

    /**
     * 获取Integer本地数据
     * @param key 保存时对应的key
     * @param defaultValue 默认值
     * @return
     */
    public static int getInteger(String key, int defaultValue) {
        String value = getString(key, null);
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    /**
     * 获取Long本地数据
     * @param key 保存时对应的key
     * @param defaultValue 默认值
     * @return
     */
    public static long getLong(String key, long defaultValue) {
        String value = getString(key, null);
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    /****************************************************************************************
     *                                      内部操作
     ****************************************************************************************/
    private static Saver saver;
    private static Saver self() {
        if (saver == null) {
            saver = new Saver();
        }
        return saver;
    }

    private SharedPreferences mSP;
    private Saver() {
        mSP = App.get().getSharedPreferences("saver", MODE_PRIVATE);
    }

    private void setValue(String key, String value) {
        mSP.edit().putString(key, value).commit();
    }

    private String getValue(String key, String defaultValue) {
        return mSP.getString(key, defaultValue);
    }

}
