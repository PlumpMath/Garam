package co.mitsuha.lockscreenlearn;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class PhotoManageActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 252525252;
    SaveHelper saveHelper;
    final String typeText = "image";

    private static final int PICK_IMAGE = 1;
    private Uri imageUri=Uri.EMPTY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveHelper = new SaveHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_manage);
    }

    public void imageChooseBtnClick(View v) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }
    public void addItemClick(View v) {
        String answer = ((EditText)findViewById(R.id.photoEditPassword)).getText().toString().trim();
        if(answer=="") {
            Toast.makeText(getApplicationContext(),"정답란을 채워주세요.",Toast.LENGTH_LONG).show();
            return;
        }
        if(imageUri==Uri.EMPTY) {
            Toast.makeText(getApplicationContext(),"이미지를 선택해주세요.",Toast.LENGTH_LONG).show();
            return;
        }
        saveHelper.addItem(new Memo(typeText,imageUri.toString(),answer));
        List<Memo> memos = saveHelper.getMemos();
        for(int i=0;i<memos.size();i++) {
            Log.d("garamQueryRes",memos.get(i).toString());
        }
        Toast.makeText(getApplicationContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_LONG).show();
        ((EditText)findViewById(R.id.photoEditPassword)).setText("");
        imageUri = Uri.EMPTY;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Log.d("Garam",selectedImage.toString());
            imageUri = selectedImage;
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Cursor c = getContentResolver().query(imageUri,null,null,null,null);
                    c.moveToNext();
                    String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
                    Uri uri = Uri.fromFile(new File(path));
                    Log.d("Garam", uri.toString());
                    c.close();
                    imageUri = uri;

                } else {
                    imageUri = Uri.EMPTY;
                }
                return;
            }
        }
    }
}
