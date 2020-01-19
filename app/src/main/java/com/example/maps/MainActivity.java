//package com.example.maps;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private DatabaseReference mDatabase;
//    private Button btnsave;
//    private Button btnproceed;
//    private EditText editTextCompanyName;
//    private EditText editTextCompanyAddress;
//    private EditText editTextEmail;
//    private EditText editTextContact;
//    private EditText editTextLongitude;
//    private EditText editTextLatitude;
//    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
// @Override
//    protected void onCreate(Bundle savedInstanceState) {
//       super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_main);
//        btnproceed=(Button)findViewById(R.id.button_proceed);
//        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
//        editTextCompanyName= (EditText)findViewById(R.id.txt_company_name);
//        editTextCompanyAddress= (EditText)findViewById(R.id.txt_Company_address);
//        editTextEmail= (EditText)findViewById(R.id.txt_email);
//        editTextContact= (EditText)findViewById(R.id.txt_contact_no);
//        editTextLongitude= (EditText)findViewById(R.id.txt_longitude);
//        editTextLatitude= (EditText)findViewById (R.id.txt_latitude);
//        btnsave= (Button)findViewById(R.id.button_save);
//        btnsave.setOnClickListener(this);
//        btnproceed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(MainActivity.this,MapsActivity.class);
//                startActivity(i);
//
//            }
//        });
//
//   }
//        private void saveUserInformation(){
//        String name =editTextCompanyName.getText().toString().trim();
//        String address =editTextCompanyAddress.getText().toString().trim();
//        String email =editTextEmail.getText().toString().trim();
//        String contact =editTextContact.getText().toString().trim();
//        double latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
//        double longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
//        Userinformation userinformation= new Userinformation(name,address,email,contact,latitude,longitude);
//        mDatabase.child(user.getUid()).setValue(userinformation);
//        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
//}
//    @Override
//    public void onClick(View view) {
//        if(view==btnproceed){
//            finish();
//        }
//if (view==btnsave){
//    saveUserInformation();
//    editTextCompanyName.getText().clear();
//    editTextCompanyAddress.getText().clear();
//    editTextEmail.getText().clear();
//    editTextContact.getText().clear();
//    editTextLatitude.getText().clear();
//    editTextLongitude.getText().clear();
//}
//    }
//}
//private void SearchGroup(String searchGroupName) {
//
//        searchResultDisplay.setVisibility(View.VISIBLE);
//
//        //  Query asal
//        //  RootRef.child("Groups").orderByKey().equalTo(searchGroupName)
//        FirebaseRecyclerOptions<SearchGroup> options = new FirebaseRecyclerOptions.Builder<SearchGroup>()
//        .setQuery(RootRef.child("Groups").orderByKey().startAt(searchGroupName).endAt(searchGroupName + "\uf8ff"), new SnapshotParser<SearchGroup>() {
//@NonNull
//@Override
//public SearchGroup parseSnapshot(@NonNull DataSnapshot snapshot) {
//final SearchGroup searchGroup = new SearchGroup();
//
//        RootRef.child("Groups").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//        for (DataSnapshot groupDetailSnapshot: dataSnapshot.getChildren()) {
//
//        SearchGroup group = dataSnapshot.getValue(SearchGroup.class);
//
//        String groupName = groupDetailSnapshot.getKey().toString();
//
//        searchGroup.setGroupName(groupName);
////                                    Log.d("Get Data name", groupName);
////                                    Log.d("Get Data", group.getGroupName());
//        //  Log.d("mylog", "DataSnapshot (using set): " + dataSnapshot);
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//        });
//
//        return searchGroup;
//        }
//        })
//        .build();
//
//        FirebaseRecyclerAdapter<SearchGroup, SearchGroupViewHolder> adapter
//        = new FirebaseRecyclerAdapter<SearchGroup, SearchGroupViewHolder>(options) {
//@Override
//protected void onBindViewHolder(@NonNull final SearchGroupViewHolder holder, int position, @NonNull SearchGroup model) {
//
//        holder.txtGroupName.setText(getRef(position).getKey().toString());
//
//        RootRef.child("Groups").child(getRef(position).getKey().toString()).addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//        String retGroupCreatedBy = dataSnapshot.child("groupCreatedBy").getValue().toString();
//
//        //  Find username of the group creator
//        RootRef.child("Users").child(retGroupCreatedBy).child("profilename").addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//        if(dataSnapshot.exists()) {
//        holder.txtGroupCreatedBy.setText("Group created by: " + dataSnapshot.getValue().toString());
//        } else {
//        holder.txtGroupCreatedBy.setText("Group created by: (Account no longer exist)");
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//        });
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//        });
//
//        holder.joinGroupButton.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//
//        spinner.setVisibility(View.VISIBLE);
//
//final String groupName = holder.txtGroupName.getText().toString();
//
//        RootRef.child("Groups").child(groupName).child("Users")
//        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//        .setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        if(task.isSuccessful()) {
//
//        RootRef.child("UserGroups")
//        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//        .child(groupName).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        if(task.isSuccessful()) {
//        spinner.setVisibility(View.GONE);
//        Toast.makeText(FindGroupsActivity.this, "Successfully joined " + groupName + "!", Toast.LENGTH_LONG).show();
//        finish();
//        }
//        else {
//        spinner.setVisibility(View.GONE);
//        }
//        }
//        });
//        }
//        else {
//        spinner.setVisibility(View.GONE);
//        Toast.makeText(FindGroupsActivity.this, "Error joining group! Pls try again later", Toast.LENGTH_LONG).show();
//        }
//        }
//        });
//
//        }
//        });
//        }
//
//@NonNull
//@Override
//public SearchGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_search_display_layout, parent, false);
//        return new SearchGroupViewHolder(view);
//        }
//        };
//
//        GroupSearchRecylerList.setAdapter(adapter);
//        adapter.startListening();
//        }
//
//public static class SearchGroupViewHolder extends RecyclerView.ViewHolder {
//
//    TextView txtGroupName, txtGroupCreatedBy;
//    Button joinGroupButton;
//
//    public SearchGroupViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        txtGroupName = itemView.findViewById(R.id.display_group_name);
//        txtGroupCreatedBy = itemView.findViewById(R.id.display_group_createdby);
//        joinGroupButton = itemView.findViewById(R.id.join_group_button);
//    }
//}
