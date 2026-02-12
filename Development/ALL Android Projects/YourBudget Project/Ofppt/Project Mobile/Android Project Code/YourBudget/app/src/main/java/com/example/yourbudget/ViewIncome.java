package com.example.yourbudget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;

public class ViewIncome extends AppCompatActivity {
//    ListView lv;
//    DataBaseSqlite db;

    ListView lv;
    DataBaseSqlite db;
    ArrayList<CustomListView> arraylist;
    AdapterListView adapter;
    CustomListView clv;
    FloatingActionButton acrionbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_income);
        acrionbutton=findViewById(R.id.floatingActionButton);
//        lv= (ListView) findViewById(R.id.ListView1);
//        db=new DataBaseSqlite(getApplicationContext(),"Gestion",null,1);
//        ArrayList<String> liste2= db.ViewIncomes(Login.User);
//        ArrayAdapter ad= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,liste2);
//        lv.setAdapter(ad);
        lv=findViewById(R.id.ListView1);
        db=new DataBaseSqlite(getApplicationContext(),"Gestion2",null,1);


        arraylist = new ArrayList<>();
        GetData();
        acrionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ViewIncome.this,Addincome.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ViewIncome.this);
                LayoutInflater inflater=LayoutInflater.from(getApplicationContext());
                View v= inflater.inflate(R.layout.alert_dialog_update_delete,null);
                builder1.setView(v);
                final AlertDialog dialog= builder1.create();
                final EditText ed1=v.findViewById(R.id.editTextTextPersonName8);
                final EditText ed2=v.findViewById(R.id.editTextTextPersonName11);
                final TextView ed3=v.findViewById(R.id.textView17);

                Button btn1=v.findViewById(R.id.button5);
                Button btn2=v.findViewById(R.id.button6);
                Button btn3=v.findViewById(R.id.button4);
                clv=arraylist.get(position);
                ed1.setText(clv.getName());
                ed2.setText(clv.getDate());
                ed3.setText(clv.getAmount());

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ed3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar c= Calendar.getInstance();
                        int day=c.get(Calendar.DAY_OF_MONTH);
                        int mounth = c.get(Calendar.MONTH);
                        int year = c.get(Calendar.YEAR);


                        DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                ed3.setText(i2+"/"+(i1+1)+"/"+i);

                            }
                        };
                        DatePickerDialog dt= new DatePickerDialog(ViewIncome.this,datepicker,year,mounth,day);
                        dt.show();
                    }
                });

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=ed1.getText().toString();
                        String amount=ed2.getText().toString();
                        String date=ed3.getText().toString();
                        if(name.matches("")){
                            ed1.setError("Ce champ est obligatoire");
                        }
                        else if(amount.matches("")){
                            ed2.setError("Ce champ est obligatoire");
                        }
                        else if(date.matches("")){
                            ed3.setError("Ce champ est obligatoire");
                        }
                        else{

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ViewIncome.this);

                            builder1.setTitle("Voulez-vous supprimer cet article ?");

                            builder1.setIcon(R.drawable.deleteicon);
                            builder1.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    boolean DeleteIncome=db.DeleteIncome(clv.getId().toString());
                                    if(DeleteIncome==true){
                                        StyleableToast.makeText(getApplicationContext(), "Supprimé avec succès", Toast.LENGTH_LONG, R.style.Deleted).show();
                                    }
                                    else{
                                        StyleableToast.makeText(getApplicationContext(), "Quelque chose ne va pas", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                                    }
                                    GetData();
                                    dialog.dismiss();

                                }
                            });
                            builder1.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    StyleableToast.makeText(getApplicationContext(), "Suppression annulée", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                                }
                            });
                            builder1.create().show();
                        }



                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            if(ed1.getText().toString().matches("")){
                                ed1.setError("Ce champ est obligatoire");
                            }
                            else if(ed2.getText().toString().matches("")){
                                ed2.setError("Ce champ est obligatoire");
                            }
                            else if(ed3.getText().toString().matches("")){
                                ed3.setError("Ce champ est obligatoire");
                            }
                            else{
                                String name=ed1.getText().toString();
                                float amount=Float.valueOf(ed2.getText().toString());
                                String date=ed3.getText().toString();
                                boolean updateIncome = db.UpdateIncome(name,amount,date,clv.getId().toString());
                                if(updateIncome==true){
                                    StyleableToast.makeText(getApplicationContext(), "Mis à jour avec succés", Toast.LENGTH_LONG, R.style.PasswordUpdate).show();
                                }
                                else{
                                    StyleableToast.makeText(getApplicationContext(), "Quelque chose ne va pas", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                                }
                                GetData();
                                dialog.dismiss();
                            }
                        }catch (Exception ex){
                            StyleableToast.makeText(getApplicationContext(), "le montant doit être un nombre !", Toast.LENGTH_LONG, R.style.OopsUpdate).show();
                        }




                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
//                builder1.setCancelable(true);


//
//                builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(ViewExpense.this, "Good Job", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(ViewExpense.this, "Bad Job", Toast.LENGTH_SHORT).show();
//                    }
//                });


                dialog.show();

            }
        });











    }
    public void GetData(){
        arraylist=db.CustomListView(Login.User);
        adapter= new AdapterListView(ViewIncome.this,arraylist);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}