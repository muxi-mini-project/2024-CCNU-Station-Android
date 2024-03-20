package com.example.ccnu_station.Reuse;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static File uriToFile(Context context, Uri uri) throws IOException {
        ContentResolver contentResolver = context.getContentResolver();

        File destinationFile = createDestinationFile(context);

        try (InputStream inputStream = contentResolver.openInputStream(uri);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {

            if (inputStream != null) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }

        return destinationFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private static File createDestinationFile(Context context) throws IOException {
        String fileName = "image_file"; // 可以根据实际情况给文件命名
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir == null) {
            throw new IOException("External storage directory not available");
        }

        File destinationFile = new File(storageDir, fileName + ".jpg");
        if (destinationFile.exists()) {
            destinationFile.delete();
        }
        return destinationFile;
    }
}

