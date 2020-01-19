package com.example.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileFragment extends Fragment {
    private DatabaseReference mDatabase;
    private Button btnupdate;
    private Button btndelete;
    private EditText editTextCompanyName;
    private EditText editTextCompanyAddress;
    private EditText editTextContact;
    private Spinner spin;
    private EditText editTextLongitude;
    private EditText editTextLatitude;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private String name;
    private String address;
    private String contact;
    private String spintext;
    private double latitude;
    private double longitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_update_profile, container, false);



        btnupdate = view.findViewById(R.id.button_update);
        btndelete = view.findViewById(R.id.button_delete);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Workshops");



        mDatabase.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spin = view.findViewById(R.id.spin);
                editTextCompanyName = view.findViewById(R.id.txt_company_name);
                editTextCompanyAddress = view.findViewById(R.id.txt_Company_address);
                editTextContact = view.findViewById(R.id.txt_contact_no);
                editTextLongitude = view.findViewById(R.id.txt_longitude);
                editTextLatitude = view.findViewById(R.id.txt_latitude);
                String name = (String) dataSnapshot.child("name").getValue();
                String address = (String) dataSnapshot.child("address").getValue();
                String contact = (String) dataSnapshot.child("contact").getValue();
                String spin = (String) dataSnapshot.child("spintext").getValue();
                double latitude = (double) dataSnapshot.child("latitude").getValue();
                double longitude = (double) dataSnapshot.child("longitude").getValue();
                editTextCompanyName.setText(name);
                editTextCompanyAddress.setText(address);
                editTextContact.setText(contact);
                editTextLongitude.setText( longitude+"");
                editTextLatitude.setText( latitude+"");
//                spin.setText(spintext);


        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            });





        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UpdateWorkshopInformation();
                    editTextCompanyName.getText().clear();
                    editTextCompanyAddress.getText().clear();
                    editTextContact.getText().clear();
                    editTextLatitude.getText().clear();
                    editTextLongitude.getText().clear();
                    spin.getSelectedItem();


            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    DeleteWorkshopInformation();
                    editTextCompanyName.getText().clear();
                    editTextCompanyAddress.getText().clear();
                    editTextContact.getText().clear();
                    editTextLatitude.getText().clear();
                    editTextLongitude.getText().clear();
                    spin.getSelectedItem();


            }
        });
        return view;
    }
    public void  ReadWorkshopInformation(){

    }

    private void UpdateWorkshopInformation() {
        String name = editTextCompanyName.getText().toString().trim();
        String address = editTextCompanyAddress.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        String spintext = spin.getSelectedItem().toString().trim();
        double latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
        final Workshopinformation userinformation = new Workshopinformation(name, address, contact, spintext, latitude, longitude, 2);
        mDatabase.child(user.getUid()).setValue(userinformation);
        Toast.makeText(getActivity(),"Updated", Toast.LENGTH_LONG).show();
    }


    private void DeleteWorkshopInformation() {
        String name =editTextCompanyName.getText().toString().trim();
        String address =editTextCompanyAddress.getText().toString().trim();
        String contact =editTextContact.getText().toString().trim();
        String spintext = spin.getSelectedItem().toString().trim();
        double latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
        final Workshopinformation userinformation = new Workshopinformation(name, address, contact, spintext, latitude, longitude, 2);
        mDatabase.child(user.getUid()).setValue(null);
        Toast.makeText(getActivity(),"Deleted", Toast.LENGTH_LONG).show();

    }
}
