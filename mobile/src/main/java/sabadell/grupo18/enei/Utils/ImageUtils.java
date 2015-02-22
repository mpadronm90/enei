package sabadell.grupo18.enei.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import sabadell.grupo18.enei.R;

/**
 * Created by Javier on 21/02/2015.
 */
public class ImageUtils {

    private Context contexto;

    public ImageUtils(Context contexto){
        this.contexto=contexto;
    }

    private int dpTopx(int dp){
        float densidad = contexto.getResources().getDisplayMetrics().density;
        return Math.round(((float)dp) * densidad);
    }


    public Drawable resize(Drawable imagen){
        Drawable drawning = imagen;
        if(drawning==null){
            return null;
        }
        Bitmap bitmap = ((BitmapDrawable)drawning).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = dpTopx(250);
        float xScale = ((float)bounding) / width;
        float yScale = ((float)bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        Bitmap bitmap_a_escala = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,false);
        width = bitmap_a_escala.getWidth();
        height = bitmap_a_escala.getHeight();
        BitmapDrawable resultante = new BitmapDrawable(bitmap_a_escala);

        return resultante;
    }

}
