package com.example.wasteclassifier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class BaseActivity extends AppCompatActivity {
    // constants
    public static final int RC_STORAGE_PERMS1 = 101;
    public static final int RC_STORAGE_PERMS2 = 102;
    public static final int RC_SELECT_PICTURE = 103;
    public static final int RC_TAKE_PICTURE = 104;

    public File imageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            // TODO check whether ACTION_BAR_TITLE useful

        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_album_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_gallery:
                checkStoragePermission(RC_STORAGE_PERMS1);
                break;
            case R.id.action_camera:
                checkStoragePermission(RC_STORAGE_PERMS2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RC_STORAGE_PERMS1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectPictureFromAlbum();
                }
                else {
                    needPermission(this, requestCode, R.string.confirm_file_permission);
                }
                break;
            case RC_STORAGE_PERMS2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                else {
                    needPermission(this, requestCode, R.string.confirm_camera_permission);
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void needPermission(final Activity activity, final int requestCode, int msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage(msg);
        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, requestCode);
            }
        });
        alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    private void openCamera() {
        imageFile = createNewImageFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photo = FileProvider.getUriForFile(this, getPackageName() + ".provider", imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photo);
        startActivityForResult(intent, RC_TAKE_PICTURE);
    }

    /* check storage permission
       input: requestCode (check what)
       output: void
    */
    public void checkStoragePermission(int requestCode) {
        switch (requestCode) {
            case RC_STORAGE_PERMS1:
                int hasWriteExternalStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (PackageManager.PERMISSION_GRANTED == hasWriteExternalStoragePermission) {
                    selectPictureFromAlbum();
                }
                else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                }
                break;
            case RC_STORAGE_PERMS2:
                int hasWriteCameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if (PackageManager.PERMISSION_GRANTED == hasWriteCameraPermission) {
                    openCamera();
                }
                else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
                }
                break;
        }
    }

    /* create a temporary image file
       input: file
       output: image file
     */
    private static File createNewImageFile (File file) {
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/com.example.wasteclassifier");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        if (null == file) {
            file = new File(dir, "original.jpg");
        }
        return file;
    }

    /*
       open album and select picture from album
     */
    private void selectPictureFromAlbum () {
        imageFile = createNewImageFile(imageFile);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_SELECT_PICTURE);
    }

}
