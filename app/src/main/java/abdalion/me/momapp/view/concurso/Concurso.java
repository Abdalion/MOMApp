package abdalion.me.momapp.view.concurso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.internal.ObjectConstructor;

import java.io.ByteArrayOutputStream;
import java.io.File;

import abdalion.me.momapp.R;
import abdalion.me.momapp.view.login.LoginActivity;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class Concurso extends AppCompatActivity {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static boolean IMAGE_IS_VALID = false;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userDatabase = ref.child("users");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurso);

        Button takePhoto = (Button) findViewById(R.id.activity_detalle_takePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userIsLogged()) {
                    EasyImage.openChooserWithGallery(Concurso.this, "Take or pick an image", 123);
                }
                else {
                    startActivity(new Intent(Concurso.this, LoginActivity.class));
                }
            }
        });

        Button sendPhoto = (Button) findViewById(R.id.activity_concurso_sendPhoto);
        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userIsLogged()) {
                    startActivity(new Intent(Concurso.this, LoginActivity.class));
                }
                else if(isNot(IMAGE_IS_VALID)) {
                    Toast.makeText(Concurso.this, "Take a valid photo!", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveUserToDatabase();
                    saveFotoToStorage();
                }
            }
        });
    }

    private void saveUserToDatabase() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String identifier = firebaseUser.getUid();
        userDatabase.child(identifier).setValue(new User(firebaseUser.getDisplayName(), firebaseUser.getUid()));
    }

    private void saveFotoToStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference ref = storage.getReference();
        StorageReference imageRef = ref.child(user.getUid());

        ImageView imageView = (ImageView)findViewById(R.id.activity_concurso_imagen);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Concurso.this, "Upload failed.", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Concurso.this, "Uploaded image!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Toast.makeText(Concurso.this, "Error picking image", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                IMAGE_IS_VALID = true;
                Toast.makeText(Concurso.this, "Image taken correctly!", Toast.LENGTH_SHORT).show();
                ImageView imagen = (ImageView)findViewById(R.id.activity_concurso_imagen);
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imagen.setImageBitmap(myBitmap);
            }

        });
    }

    private boolean userIsLogged() {
        return user != null;
    }

    private boolean isNot(Boolean bool) {
        return !bool;
    }


}
