package com.example.yo351.foodmonitorysystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private EditText inputBarcode,inputQuality;
    private Button btnSave;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference rootReference = firebaseDatabase.getReference();
    private DatabaseReference barcodeReference = rootReference.child("barcode");
    private DatabaseReference qualityReference = rootReference.child("quality");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBarcode = (EditText) findViewById(R.id.scan_barcode);
        inputQuality = (EditText) findViewById(R.id.quality);
    }

    public void submitContent(View view){
        String barcode = inputBarcode.getText().toString();
        barcodeReference.setValue(barcode);
        inputBarcode.setText("");

        String quality = inputQuality.getText().toString();
        qualityReference.setValue(quality);
        inputQuality.setText("");
    }

    protected void onStart(){
        super.onStart();
        barcodeReference.addValueEventListener(this);
        qualityReference.addValueEventListener(this);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
