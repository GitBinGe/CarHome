package bg.carhome;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BinGe on 2017/10/26.
 * 所有弹窗的基类
 */

public class BaseDialog extends Dialog {

    /**
     * 为了管理dialog的列表，比如排队打开dialog等机制
     */
    private static List<Dialog> dialogQueue = new ArrayList<>();

    /**
     * 最后打开的dialog
     */
    private static Dialog sLastDialog;

    /**
     * 默认使用透明弹窗
     * @param context
     */
    public BaseDialog(@NonNull Context context) {
        this(context, false);
    }

    /**
     * @param context
     * @param translucent 是否透明
     */
    public BaseDialog(@NonNull Context context, boolean translucent) {
        super(context, translucent ? R.style.dialog_translucent : R.style.action_sheet);
    }

    /**
     * 获取当前分辨率信息
     * @return
     */
    public DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    /**
     * 获取dp值
     * @param value
     * @return
     */
    public int getDp(int value) {
        return (int) (getDisplayMetrics().scaledDensity * value);
    }

    @Override
    public void show() {
        if (sLastDialog != null) {
            sLastDialog.dismiss();
            sLastDialog = null;
        }
        super.show();
    }

}
