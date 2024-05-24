package com.example.fragprac;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container, false);
        TextView textTelp, textNama, textAlamat;
        EditText editNama, editPhone, editAlamat;
        Button saveButton, cancelButton;


        // Inflate the layout for this fragment

        textTelp = view.findViewById(R.id.textTelp);
        textNama = view.findViewById(R.id.textNama);
        textAlamat = view.findViewById(R.id.textAlamat);
        editNama = view.findViewById(R.id.editNama);
        editPhone = view.findViewById(R.id.editPhone);
        editAlamat = view.findViewById(R.id.editAlamat);
        saveButton = view.findViewById(R.id.saveButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(v -> {
            editNama.setText("");
            editPhone.setText("");
            editAlamat.setText("");

        });

        saveButton.setOnClickListener(v -> {
            String nama = editNama.getText().toString();
            String phone = editPhone.getText().toString();
            String alamat = editAlamat.getText().toString();
            if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(alamat))
            {
                Toast.makeText(getContext(), "Tidak ada field yang boleh kosong", Toast.LENGTH_SHORT).show();
            }else{
                masukkanData(nama, phone, alamat);
                editNama.setText("");
                editPhone.setText("");
                editAlamat.setText("");
            }
        });



        return view;
    }

    private void masukkanData(String name, String phone, String alamat){


        HashMap<String, Object> dataHashMap = new HashMap<>();
        dataHashMap.put("name", name);
        dataHashMap.put("phone",phone);
        dataHashMap.put("address", alamat);
        Pelanggan pelanggan = new Pelanggan(name, alamat, phone);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("Pelanggan");

        String key = dataRef.push().getKey();
        dataHashMap.put("key",key);


        assert key != null;
        dataRef.child(key).setValue(pelanggan).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();

            }
        });

    }
}