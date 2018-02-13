package bg.carhome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BinGe on 2017/12/29.
 */

public class BGAdapter extends PagerAdapter {

    private List<String> list;

    public BGAdapter(Context context) {
        list = new ArrayList<>();
        list.add("#" + Integer.toHexString(context.getResources().getColor(R.color.blue)));
        try {
            String[] names = context.getAssets().list("bg");
            for (int i = 0; i < names.length; i++) {
                list.add("bg/" + names[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String path = list.get(position);
        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setTag(path);
        container.addView(iv);
        Bitmap bitmap = ImageCache.share().get(path);
        if (bitmap == null) {
            if (path.startsWith("#")) {
                bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(bitmap);
                c.drawColor(Color.parseColor(path));
                bitmap = Util.rsBlur(container.getContext(), bitmap, 25);
                ImageCache.share().put(path, bitmap);
            } else {
                try {
                    bitmap = BitmapFactory.decodeStream(container.getContext().getAssets().open(path));
                    bitmap = Util.rsBlur(container.getContext(), bitmap, 25);
                    ImageCache.share().put(path, bitmap);
                } catch (IOException e) {

                }
            }
        }
        iv.setImageBitmap(bitmap);
        return iv;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(container.findViewWithTag(list.get(position)));
    }
}
