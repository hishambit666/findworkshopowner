package com.example.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
//    private static final int PICK_IMAGE_REQUEST = 1 ;
    private DatabaseReference mDatabase;
    private StorageReference Folder;
    private Button btnsave;
    private Button btnproceed;
    private EditText editTextCompanyName;
    private EditText editTextCompanyAddress;
    private EditText editTextContact;
    private Spinner spin;
    private EditText editTextLongitude;
    private EditText editTextLatitude;
    private ImageView imageButton;
    private Uri mImageUri;
    private ProgressBar mProgressBar;
    private StorageTask mUploadTask;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static final int ImageBack = 1;
    private String imageUri ="";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnproceed = view.findViewById(R.id.button_proceed);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Workshops");
        Folder = FirebaseStorage.getInstance().getReference().child("uploads");
        imageButton = view.findViewById(R.id.image);
        mProgressBar = view.findViewById(R.id.progress_bar);
        spin = view.findViewById(R.id.spin);
        editTextCompanyName = view.findViewById(R.id.txt_company_name);
        editTextCompanyAddress = view.findViewById(R.id.txt_Company_address);
        editTextContact = view.findViewById(R.id.txt_contact_no);
        editTextLongitude = view.findViewById(R.id.txt_longitude);
        editTextLatitude = view.findViewById(R.id.txt_latitude);
        btnsave = view.findViewById(R.id.button_save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view==btnsave){
                    saveWorkshopInformation();
                    editTextCompanyName.getText().clear();
                    editTextCompanyAddress.getText().clear();
                    editTextContact.getText().clear();
                    editTextLatitude.getText().clear();
                    editTextLongitude.getText().clear();
                    spin.getSelectedItem();
                }

            }
        });


     imageButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             UploadImage();
         }
     });

        return view;

    }

    private void UploadImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ImageBack);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null) {
//            mImageUri = data.getData();
//            Picasso.with(getContext()).load(mImageUri).into(imageButton);
//        }
//    }

//    private String getFileExtension(Uri uri) {
//        ContentResolver cR = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
//    }


//    private void saveWorkshopInformation() {
//        if (mImageUri != null) {
//            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
//                    + "." + (mImageUri));
//
//            mUploadTask = fileReference.putFile(mImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgressBar.setProgress(0);
//                                }
//                            }, 500);
//                            double latitude = 0.0;
//                            double longitude = 0.0;
//                            Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_LONG).show();
//                            String name =editTextCompanyName.getText().toString().trim();
//                            String address =editTextCompanyAddress.getText().toString().trim();
//                            String contact =editTextContact.getText().toString().trim();
//                            String spintext = spin.getSelectedItem().toString().trim();
//                            String Image = imageButton.getImageMatrix().toString().trim();
//                            latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
//                            longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
//                            Workshopinformation userinformation= new Workshopinformation(Image,name,address,contact,spintext,latitude,longitude, 2);
//                            mDatabase.child(user.getUid()).setValue(userinformation);
//                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
//                                    taskSnapshot.getDownloadUrl().toString());
//                            String uploadId = mDatabaseRef.push().getKey();
//                            mDatabaseRef.child(uploadId).setValue(upload);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            mProgressBar.setProgress((int) progress);
//                        }
//                    });
//        } else {
//            Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ImageBack){
            if(resultCode == RESULT_OK){
                Uri ImageData = data.getData();

                final StorageReference Imagename = Folder.child(user.getUid());
                Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(final Uri uri) {
//                                DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child("Image").child(user.getUid());

                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("image", String.valueOf(uri));
                                mDatabase.child(user.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Glide.with(getContext()).load(uri).centerCrop().into(imageButton);
                                        imageUri = uri.toString();
                                        Toast.makeText(getContext(), "Finally Completed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

//                        Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
//                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                System.out.println(uri);
//                                Map<String,Object> imageUpdate = new HashMap<>();
//                                imageUpdate.put("image",uri);
//                                mDatabase.child(user.getUid()).setValue(imageUpdate);
//                            }
//                        });


                    }
                });
            }
        }
    }

    private void saveWorkshopInformation(){
        Map<String, Object> workshop = new HashMap<>();
        workshop.put("address", editTextCompanyAddress.getText().toString().trim());
        workshop.put("contact", editTextContact.getText().toString().trim());
        workshop.put("name", editTextCompanyName.getText().toString().trim());
        workshop.put("longitude", Double.parseDouble(editTextLongitude.getText().toString().trim()));
        workshop.put("latitude", Double.parseDouble(editTextLatitude.getText().toString().trim()));
        workshop.put("spintext", spin.getSelectedItem().toString().trim());


        mDatabase.child(user.getUid()).updateChildren(workshop);
        Toast.makeText(getActivity(),"Saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
