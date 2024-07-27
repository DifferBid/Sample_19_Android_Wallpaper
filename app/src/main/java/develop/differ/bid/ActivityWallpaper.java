package develop.differ.bid;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ActivityWallpaper extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        Button setWallpaperButton = findViewById(R.id.setWallpaperButton);

        setWallpaperButton.setOnClickListener(view -> {
            setWallpaper(this); // Устанавливает обои только на рабочий стол
            // setWallpaper(this, true, false); // Устанавливает обои только на рабочий стол
            // setWallpaper(this, false, true); // Устанавливает обои только на экран блокировки
            // setWallpaper(this, true, true); // Установить обои и на рабочий стол, и на экран блокировки
        });
    }

    //  В указанном методе setWallpaper, обои устанавливаются на рабочий стол (Home Screen),
    //  Метод setBitmap() класса WallpaperManager, который используется коде,
    //  по умолчанию настраивает обои для рабочего стола.
    public void setWallpaper(Context context) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            Bitmap wallpaper = BitmapFactory.decodeResource(context.getResources(), R.drawable.your_wallpaper);
            wallpaperManager.setBitmap(wallpaper);

            Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("MyAppLog","IOException: " + e);
            Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
        }
    }


    public void setWallpaper(Context context, boolean setForHomeScreen, boolean setForLockScreen) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            Bitmap wallpaper = BitmapFactory.decodeResource(context.getResources(), R.drawable.your_wallpaper);

            // Флаги для определения, куда устанавливать обои
            int flags = 0;
            if (setForHomeScreen) {
                flags |= WallpaperManager.FLAG_SYSTEM;
            }
            if (setForLockScreen) {
                flags |= WallpaperManager.FLAG_LOCK;
            }

            wallpaperManager.setBitmap(wallpaper, null, true, flags);

            Toast.makeText(context, "Wallpaper Set", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("MyAppLog","IOException: " + e);

            Toast.makeText(context, "Failed to set wallpaper: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
    В Android, параметр Rect visibleCropHint в методе setBitmap() класса WallpaperManager используется для
    определения области изображения, которая будет видима после установки обоев. Это позволяет точнее контролировать,
    какая часть изображения будет показана на экране устройства. Вот что важно знать о параметре Rect visibleCropHint:

    Что такое Rect?
    Rect представляет собой прямоугольник, который определяется четырьмя координатами: left, top, right, bottom.
    Эти значения задают позицию и размеры прямоугольника в пикселях.

    Как это работает?
    left и top указывают верхний левый угол прямоугольника.
    right и bottom указывают нижний правый угол прямоугольника.
    Эти координаты определяют, какая часть изображения будет отображаться после установки обоев. Если Rect установлен в null, то будет использована вся площадь изображения.
    Пример использования:
    Если вы хотите обрезать изображение так, чтобы показывалась только центральная часть размером 200x200
    пикселей начиная с позиции (50, 50) на изображении, вы создадите Rect следующим образом:
    Rect cropHint = new Rect(50, 50, 250, 250);

    Применение в setBitmap():
    При использовании в setBitmap() этот Rect указывает WallpaperManager,
    какую часть изображения следует отобразить как обои.
    Это особенно полезно, если изображение имеет большое разрешение или
    если нужно акцентировать внимание на определённой части изображения.

    Использование Rect visibleCropHint дает больше контроля над тем, как изображение будет выглядеть
    на экране устройства в качестве обоев, позволяя создать более кастомизированное и точное отображение.
    */

}
