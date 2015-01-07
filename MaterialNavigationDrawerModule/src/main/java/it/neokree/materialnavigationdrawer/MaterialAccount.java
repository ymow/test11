package it.neokree.materialnavigationdrawer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by neokree on 11/12/14.
 */
public class MaterialAccount {
    private Drawable photo;
    private Drawable background;
    private Drawable circularPhoto;
    private String title;
    private String subTitle;
    private int accountNumber;
    private Resources resources;

    public static final int FIRST_ACCOUNT = 0;
    public static final int SECOND_ACCOUNT = 1;
    public static final int THIRD_ACCOUNT = 2;

    public MaterialAccount(Resources res,String title, String subTitle, Drawable photo,Bitmap background) {
        this.photo = photo;
        this.title = title;
        this.subTitle = subTitle;
        this.background = new BitmapDrawable(res,background);
        resources = res;
    }

    public MaterialAccount(Resources res,String title, String subTitle, Drawable photo,Drawable background) {
        this.photo = photo;
        this.title = title;
        this.subTitle = subTitle;
        this.background = background;
        resources = res;
    }

    public MaterialAccount(Resources res,String title, String subTitle, Bitmap photo, Drawable background) {
        this.photo = new BitmapDrawable(res,photo);;
        this.title = title;
        this.subTitle = subTitle;
        this.background = background;
        resources = res;
    }

    public MaterialAccount(Resources res,String title, String subTitle, Bitmap photo, Bitmap background) {
        this.photo = new BitmapDrawable(res,photo);
        this.title = title;
        this.subTitle = subTitle;
        this.background = new BitmapDrawable(res,background);
        resources = res;
    }

    // setter

    public void setPhoto(Drawable photo){
        this.photo = photo;
    }

    public void setPhoto(Resources res,Bitmap photo) {
        this.photo = new BitmapDrawable(res,photo);
    }

    public void setBackground(Resources res,Bitmap background) {
        this.background = new BitmapDrawable(res,background);
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setAccountNumber(int number) {
        this.accountNumber = number;
    }

    // getter

    public Drawable getPhoto() {
        return photo;
    }

    public Drawable getBackground() {
        return background;
    }

    public Drawable getCircularPhoto() {
        if (circularPhoto != null) {
            return circularPhoto;
        }
        circularPhoto = getCroppedBitmapDrawable(photo);
        return circularPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    private Bitmap convertToBitmap(Drawable drawable) {
        Bitmap mutableBitmap;
        if(drawable.getMinimumHeight() == 0 || drawable.getMinimumWidth() == 0)
            mutableBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        else
            mutableBitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable.draw(canvas);

        return mutableBitmap;
    }

    private BitmapDrawable getCroppedBitmapDrawable(Drawable drawable) {
        Bitmap bitmap = convertToBitmap(drawable);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return new BitmapDrawable(resources,output);
    }


}
