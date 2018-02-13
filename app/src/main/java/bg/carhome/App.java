package bg.carhome;

import android.app.Application;



/**
 * Created by BinGe on 2017/12/28.
 */

public class App extends Application {

    private static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Application get() {
        return app;
    }

}
