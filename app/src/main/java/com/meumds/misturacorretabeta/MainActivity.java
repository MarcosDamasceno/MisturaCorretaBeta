package com.meumds.misturacorretabeta;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button proximo;
    private Button btn10;
    private Button btn20;
    private Button btn50;
    private Button btn100;
    private Button btncls;
    private TextView valores;
    private SeekBar slidepercent;
    private TextView rotulos;
    private TextView lblperc1;
    private TextView lblperc2;
    private TextView lbldesc1;
    private TextView lbldesc2;
    private TextView lbldesc5;
    private TextView lblcomb1lt;
    private TextView lblcomb2lt;
    private TextView lblcomb1vlr;
    private TextView lblcomb2vlr;
    private TextView lbltotlts;
    private TextView lbltotvlr;
    private Toast backtoast;
    private int etapa;
    private int perfil;
    private double vlrabastec;
    private double vlrpercent;
    private double vlrpercent1;
    private double vlrcomb1;
    private double vlrcomb2;
    private double mValores;
    private double verifica1;
    private String verifica;
    private double proporc1;
    private double proporc2;
    private PieChart chart;
    private PieChart chart1;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupUiViews();
//Recupera dados e inicializa
        if (bundle == null) {
            etapa = 0;
            vlrpercent = 50;
            AvancaUI();
            etapa = 1;
        }else{
            vlrabastec = bundle.getDouble("vlrabastec");
            vlrcomb1 = bundle.getDouble("vlrcomb1");
            vlrcomb2 = bundle.getDouble("vlrcomb2");
            vlrpercent = bundle.getDouble("vlrpercent");
            vlrpercent1 = 100-vlrpercent;
            etapa = bundle.getInt("etapa")-1;

            AvancaUI();
            etapa ++;
        }
//Botão retornar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });
//Botão proximo
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checagem de campos preenchidos
                verifica = valores.getText().toString();
                if (!verifica.isEmpty()) {
                    if (!valores.getText().toString().trim().equals(".")
                            && !valores.getText().toString().trim().equals("0")) {
                        verifica1 = Double.parseDouble(verifica);
                        if (verifica1 > 0 || etapa == 2) {
                            switch (etapa) {
                                case 1:
                                    vlrabastec = Double.parseDouble(valores.getText().toString());
                                    AvancaUI();
                                    etapa = 2;
                                    break;
                                case 2:
                                    vlrpercent = (double) slidepercent.getProgress();
                                    vlrpercent1 = (double) (100 - slidepercent.getProgress());
                                    AvancaUI();
                                    etapa = 3;
                                    break;
                                case 3:
                                    vlrcomb1 = Double.parseDouble(valores.getText().toString());
                                    AvancaUI();
                                    etapa = 4;
                                    break;
                                case 4:
                                    vlrcomb2 = Double.parseDouble(valores.getText().toString());
                                    AvancaUI();
                                    etapa = 5;
                                    break;
                            }
                        }
                    }
                }
            }
        });
//Botão 10
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (etapa) {

                    case 1:
                        if (valores.getText().toString().trim().equals("")){
                            mValores = 10;
                            valores.setText(String.valueOf(mValores));
                        }else{
                            mValores = Double.parseDouble(valores.getText().toString());
                            valores.setText(String.valueOf(mValores + 10));
                        }
                        break;
                    default:
                        if (valores.getText().toString().trim().equals("")){
                            mValores = 0.01;
                            valores.setText(String.valueOf(mValores));
                        }else{
                            mValores = Double.parseDouble(valores.getText().toString());

                            valores.setText(String.format(Locale.US,"%,.2f",(mValores + 0.01)));
                        }
                        break;
                }
            }
        });
//Botão 20
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (etapa) {

                    case 1:
                        if (valores.getText().toString().trim().equals("")){
                            mValores = 20;
                            valores.setText(String.valueOf(mValores));
                        }else{
                            mValores = Double.parseDouble(valores.getText().toString());
                            valores.setText(String.valueOf(mValores + 20));
                        }
                        break;
                    default:
                        if (valores.getText().toString().trim().equals("")){
                            mValores = 0.10;
                            valores.setText(String.valueOf(mValores));
                        }else{
                            mValores = Double.parseDouble(valores.getText().toString());
                            valores.setText(String.format(Locale.US,"%,.2f",(mValores + 0.10)));
                        }
                        break;
                }
            }
        });
//Botão 50
        btn50.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 switch (etapa) {

                     case 1:
                         if (valores.getText().toString().trim().equals("")){
                             mValores = 50;
                             valores.setText(String.valueOf(mValores));
                         }else{
                             mValores = Double.parseDouble(valores.getText().toString());
                             valores.setText(String.valueOf(mValores + 50));
                         }
                         break;
                     default:
                         if (valores.getText().toString().trim().equals("")){
                             mValores = 0.50;
                             valores.setText(String.valueOf(mValores));
                         }else{
                             mValores = Double.parseDouble(valores.getText().toString());
                             valores.setText(String.format(Locale.US,"%,.2f",(mValores + 0.50)));
                         }
                         break;
                 }
             }
        });
//Botão 100
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (etapa) {

                    case 1:
                        if (valores.getText().toString().trim().equals("")) {
                            mValores = 100;
                            valores.setText(String.valueOf(mValores));
                        } else {
                            mValores = Double.parseDouble(valores.getText().toString());
                            valores.setText(String.valueOf(mValores + 100));
                        }
                        break;
                    default:
                        if (valores.getText().toString().trim().equals("")) {
                            mValores = 1;
                            valores.setText(String.valueOf(mValores));
                        } else {
                            mValores = Double.parseDouble(valores.getText().toString());
                            valores.setText(String.valueOf(mValores + 1));
                        }
                        break;
                }
            }
        });
//Botão Clear
        btncls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!valores.getText().toString().trim().equals("")){
                    valores.setText(null);
                }
            }
        });
//Slide porcentagem
        slidepercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                lblperc1.setText(String.valueOf(progress + "%"));
                lblperc2.setText(String.valueOf((100 - progress) + "%"));
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
// Fim do OnCreate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case(R.id.action_perfil1):
                perfil = 0;
                break;
            case(R.id.action_perfil2):
                perfil = 1;
                break;
            case(R.id.action_perfil3):
                perfil = 2;
                break;
        }
        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        vlrabastec = Double.parseDouble(prefs.getString("pf"+perfil+"vlrabastec","0"));
        vlrpercent = Double.parseDouble(prefs.getString("pf"+perfil+"vlrpercent","0"));
        vlrpercent1 = Double.parseDouble(prefs.getString("pf"+perfil+"vlrpercent1","50"));
        vlrcomb1 = Double.parseDouble(prefs.getString("pf"+perfil+"vlrcomb1","0"));
        vlrcomb2 = Double.parseDouble(prefs.getString("pf"+perfil+"vlrcomb2","0"));
        etapa = 0;
        AvancaUI();
        etapa = 1;
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_perfil1){
            Toast.makeText(getApplicationContext(), "Carregando dados do perfil 1", Toast.LENGTH_LONG).show();
            return true;
        }else if(id == R.id.action_perfil2){
            Toast.makeText(getApplicationContext(), "Carregando dados do perfil 2", Toast.LENGTH_LONG).show();
            return true;
        }else if(id == R.id.action_perfil3){
            Toast.makeText(getApplicationContext(), "Carregando dados do perfil 3", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//Procedimento Inicia Interface
    private void setupUiViews() {

        //controles
        proximo = (Button) findViewById(R.id.btn_prx);
        btn10 = (Button) findViewById(R.id.btn10);
        btn20 = (Button) findViewById(R.id.btn20);
        btn50 = (Button) findViewById(R.id.btn50);
        btn100 = (Button) findViewById(R.id.btn100);
        btncls = (Button) findViewById(R.id.btncls);
        valores = (TextView) findViewById(R.id.txt_valores);
        slidepercent = (SeekBar) findViewById(R.id.sld_percent);
        rotulos = (TextView) findViewById(R.id.lbl_main);
        lblperc1 = (TextView) findViewById(R.id.lbl_perc1);
        lblperc2 = (TextView) findViewById(R.id.lbl_perc2);
        lbldesc1 = (TextView) findViewById(R.id.lbl_desc1);
        lbldesc2 = (TextView) findViewById(R.id.lbl_desc2);
        lbldesc5 = (TextView) findViewById(R.id.lbl_desc5);
        lblcomb1lt = (TextView) findViewById(R.id.lbl_comb1lt);
        lblcomb2lt = (TextView) findViewById(R.id.lbl_comb2lt);
        lblcomb1vlr = (TextView) findViewById(R.id.lbl_comb1vlr);
        lblcomb2vlr = (TextView) findViewById(R.id.lbl_comb2vlr);
        lbltotlts = (TextView) findViewById(R.id.lbl_totlts);
        lbltotvlr = (TextView) findViewById(R.id.lbl_totvlr);
        chart = (PieChart) findViewById(R.id.chart);
        chart1 = (PieChart) findViewById(R.id.chart1);
    }
//Procedimento Avancar
    private void AvancaUI(){
        switch (etapa){
            case 0:
                slidepercent.setVisibility(View.INVISIBLE);
                lblperc1.setVisibility(View.INVISIBLE);
                lblperc2.setVisibility(View.INVISIBLE);
                lbldesc1.setVisibility(View.INVISIBLE);
                lbldesc2.setVisibility(View.INVISIBLE);
                lbldesc5.setVisibility(View.INVISIBLE);
                lblcomb1lt.setVisibility(View.INVISIBLE);
                lblcomb2lt.setVisibility(View.INVISIBLE);
                lblcomb1vlr.setVisibility(View.INVISIBLE);
                lblcomb2vlr.setVisibility(View.INVISIBLE);
                lbltotlts.setVisibility(View.INVISIBLE);
                lbltotvlr.setVisibility(View.INVISIBLE);
                chart.setVisibility(View.INVISIBLE);
                chart1.setVisibility(View.INVISIBLE);

                //Exibe e define controles
                valores.setVisibility(View.VISIBLE);
                proximo.setVisibility(View.VISIBLE);
                btn10.setText(R.string.str_btn_10);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_20);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_50);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_100);
                btn100.setVisibility(View.VISIBLE);
                btncls.setVisibility(View.VISIBLE);

                rotulos.setText(R.string.str_lbl_main1);
                proximo.setText(R.string.str_btn_main1);
                valores.setText(null);
                valores.setText(String.valueOf(vlrabastec));
                break;
            case 1:
                //esconde UI etapa 1
                rotulos.setText(R.string.str_lbl_main2);
                valores.setVisibility(View.INVISIBLE);
                btn10.setVisibility(View.INVISIBLE);
                btn20.setVisibility(View.INVISIBLE);
                btn50.setVisibility(View.INVISIBLE);
                btn100.setVisibility(View.INVISIBLE);
                btncls.setVisibility(View.INVISIBLE);

                //Exibe UI etapa 2
                slidepercent.setVisibility(View.VISIBLE);
                slidepercent.setProgress((int)vlrpercent);
                lblperc1.setText(String.valueOf(slidepercent.getProgress()+"%"));
                lblperc2.setText(String.valueOf(100 - slidepercent.getProgress()+"%"));
                lblperc1.setVisibility(View.VISIBLE);
                lblperc2.setVisibility(View.VISIBLE);
                break;
            case 2:
                //Esconde etapa 2
                valores.setText(null);
                slidepercent.setVisibility(View.INVISIBLE);
                lblperc1.setVisibility(View.INVISIBLE);
                lblperc2.setVisibility(View.INVISIBLE);

                //Exibe etapa 3
                rotulos.setText(R.string.str_lbl_main3);
                valores.setVisibility(View.VISIBLE);
                valores.setText(String.valueOf(vlrcomb1));
                btn10.setText(R.string.str_btn_001);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_010);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_050);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_1);
                btn100.setVisibility(View.VISIBLE);

                btncls.setVisibility(View.VISIBLE);
                break;
            case 3:
                //Esconde etapa 3
                valores.setText(null);

                //Exibe etapa 4
                rotulos.setText(R.string.str_lbl_main4);
                valores.setVisibility(View.VISIBLE);
                valores.setText(String.valueOf(vlrcomb2));

                btn10.setText(R.string.str_btn_001);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_010);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_050);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_1);
                btn100.setVisibility(View.VISIBLE);

                btncls.setVisibility(View.VISIBLE);

                proximo.setText(R.string.str_btn_main2);
                proximo.setVisibility(View.VISIBLE);
                break;

            case 4:
                //Esconde etapa 4
                proximo.setVisibility(View.INVISIBLE);
                valores.setVisibility(View.INVISIBLE);
                btn10.setVisibility(View.INVISIBLE);
                btn20.setVisibility(View.INVISIBLE);
                btn50.setVisibility(View.INVISIBLE);
                btn100.setVisibility(View.INVISIBLE);
                btncls.setVisibility(View.INVISIBLE);

                //Exibe etapa 5
                Calcular();
                rotulos.setText(R.string.str_lbl_main5);
                lbldesc1.setVisibility(View.VISIBLE);
                lbldesc2.setVisibility(View.VISIBLE);
                lbldesc5.setVisibility(View.VISIBLE);
                lblcomb1lt.setVisibility(View.VISIBLE);
                lblcomb2lt.setVisibility(View.VISIBLE);
                lblcomb1vlr.setVisibility(View.VISIBLE);
                lblcomb2vlr.setVisibility(View.VISIBLE);
                lbltotlts.setVisibility(View.VISIBLE);
                lbltotvlr.setVisibility(View.VISIBLE);
                chart.setVisibility(View.VISIBLE);
                chart1.setVisibility(View.VISIBLE);
                break;
        }

    }
//Procedimento Retornar
    private void RetornoUI(){
        switch (etapa){
            case 2:
                // Esconde etapa 2
                rotulos.setText(R.string.str_lbl_main1);
                proximo.setText(R.string.str_btn_main1);
                valores.setText(null);
                valores.setText(String.valueOf(vlrabastec));
                slidepercent.setVisibility(View.INVISIBLE);
                lblperc1.setVisibility(View.INVISIBLE);
                lblperc2.setVisibility(View.INVISIBLE);

                //Exibe etapa 1
                valores.setVisibility(View.VISIBLE);
                btn10.setText(R.string.str_btn_10);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_20);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_50);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_100);
                btn100.setVisibility(View.VISIBLE);
                btncls.setVisibility(View.VISIBLE);

                break;
            case 3:
                //Esconde etapa 3
                rotulos.setText(R.string.str_lbl_main2);
                valores.setVisibility(View.INVISIBLE);
                btn10.setVisibility(View.INVISIBLE);
                btn20.setVisibility(View.INVISIBLE);
                btn50.setVisibility(View.INVISIBLE);
                btn100.setVisibility(View.INVISIBLE);
                btncls.setVisibility(View.INVISIBLE);

                //Exibe etapa 2
                slidepercent.setVisibility(View.VISIBLE);
                slidepercent.setProgress((int)vlrpercent);
                lblperc1.setText(String.valueOf(slidepercent.getProgress()+"%"));
                lblperc2.setText(String.valueOf(100 - slidepercent.getProgress()+"%"));
                lblperc1.setVisibility(View.VISIBLE);
                lblperc2.setVisibility(View.VISIBLE);
                proximo.setVisibility(View.VISIBLE);
                break;
            case 4:
                //Esconde etapa 4
                valores.setVisibility(View.INVISIBLE);
                btn10.setVisibility(View.INVISIBLE);
                btn20.setVisibility(View.INVISIBLE);
                btn50.setVisibility(View.INVISIBLE);
                btn100.setVisibility(View.INVISIBLE);
                btncls.setVisibility(View.INVISIBLE);

                //Exibe etapa 3
                proximo.setText(R.string.str_btn_main1);
                rotulos.setText(R.string.str_lbl_main3);
                valores.setVisibility(View.VISIBLE);
                valores.setText(String.valueOf(vlrcomb1));
                btn10.setText(R.string.str_btn_001);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_010);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_050);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_1);
                btn100.setVisibility(View.VISIBLE);

                btncls.setVisibility(View.VISIBLE);
                break;
            case 5:
                //Esconde etapa 5
                lbldesc1.setVisibility(View.INVISIBLE);
                lbldesc2.setVisibility(View.INVISIBLE);
                lbldesc5.setVisibility(View.INVISIBLE);
                lblcomb1lt.setVisibility(View.INVISIBLE);
                lblcomb2lt.setVisibility(View.INVISIBLE);
                lblcomb1vlr.setVisibility(View.INVISIBLE);
                lblcomb2vlr.setVisibility(View.INVISIBLE);
                lbltotlts.setVisibility(View.INVISIBLE);
                lbltotvlr.setVisibility(View.INVISIBLE);
                chart.setVisibility(View.INVISIBLE);
                chart1.setVisibility(View.INVISIBLE);

                //Exibe etapa 4
                rotulos.setText(R.string.str_lbl_main4);
                proximo.setText(R.string.str_btn_main2);
                proximo.setVisibility(View.VISIBLE);

                valores.setVisibility(View.VISIBLE);
                valores.setText(String.valueOf(vlrcomb2));
                btn10.setText(R.string.str_btn_001);
                btn10.setVisibility(View.VISIBLE);

                btn20.setText(R.string.str_btn_010);
                btn20.setVisibility(View.VISIBLE);

                btn50.setText(R.string.str_btn_050);
                btn50.setVisibility(View.VISIBLE);

                btn100.setText(R.string.str_btn_1);
                btn100.setVisibility(View.VISIBLE);

                btncls.setVisibility(View.VISIBLE);
                break;
        }

    }
//Procedimento Calculo
    private void Calcular(){
        //Definir variáveis
        double MedVlrLt;
        double PercC1RltMed;
        double MedAjust;
        double MedLts;
        double VlrPercRltC1;
        double VlrPercRltC2;
        double PercC1RltMedLt;
        double PercC2RltMedLt;
        double DifPercC1C2;
        double VlrPercC1;
        double VlrPercC2;
        float resultado;

        //Cálculo
        MedVlrLt = (vlrcomb1+vlrcomb2)/2;
        PercC1RltMed = vlrcomb1/MedVlrLt;
        MedAjust = MedVlrLt + PercC1RltMed -1;
        MedLts = vlrabastec/MedAjust;
        VlrPercRltC1 = MedLts * (((vlrpercent)/100)*vlrcomb1);
        VlrPercRltC2 = MedLts * ((vlrpercent1/100)*vlrcomb2);
        if (100 - vlrpercent == 0) {
            PercC1RltMedLt = 0;
        }else{
            PercC1RltMedLt = (vlrpercent/100)*(PercC1RltMed - 1);
        }

        PercC2RltMedLt = (vlrpercent/100)+PercC1RltMedLt;
        DifPercC1C2 = vlrabastec - (VlrPercRltC1 + VlrPercRltC2);
        VlrPercC1 = DifPercC1C2 * PercC2RltMedLt;
        VlrPercC2 = DifPercC1C2 - VlrPercC1;

        proporc1 = (VlrPercRltC1 + VlrPercC1);
        proporc2 = (VlrPercRltC2 + VlrPercC2);
        //Atualiza os Texts
        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US, "%,.2f",(VlrPercRltC1 + VlrPercC1))));
        animateTextView(0,resultado,lblcomb1vlr, "R$ ");

        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US,"%,.2f",(VlrPercRltC2 + VlrPercC2))));
        animateTextView(0,resultado,lblcomb2vlr, "R$ ");

        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US,"%,.2f",((VlrPercRltC1 + VlrPercC1)/vlrcomb1))));
        animateTextView(0,resultado,lblcomb1lt, "Lt(s) ");

        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US,"%,.2f",((VlrPercRltC2 + VlrPercC2)/vlrcomb2))));
        animateTextView(0,resultado,lblcomb2lt, "Lt(s) ");

        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US,"%,.2f",((VlrPercRltC1 + VlrPercC1)/vlrcomb1)+(VlrPercRltC2 + VlrPercC2)/vlrcomb2)));
        animateTextView(0,resultado,lbltotlts, "Lt(s) ");

        resultado = Float.parseFloat(String.valueOf(String.format(Locale.US,"%,.2f",((VlrPercRltC1 + VlrPercC1)+(VlrPercRltC2 + VlrPercC2)))));
        animateTextView(0,resultado,lbltotvlr, "R$ ");

        popGraf();
    }
//Popular grafico
    private void popGraf(){
    // Configurar gráfico 1
        //Dados
        Description description = new Description();
        description.setText("");
        description.setTextSize(8);

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((int)vlrpercent,"Combust 1"));
        pieEntries.add(new PieEntry((int)vlrpercent1,"Combust 2"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        PieData data = new PieData(dataSet);

        //Cores e aparência
        chart.setCenterText("Proporção escolhida");
        chart.setCenterTextSize(10);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setSliceSpace(1);
        chart.setDescription(description);
        dataSet.setValueTextSize(12);
        chart.setTransparentCircleRadius(56f);
        chart.setDrawEntryLabels(false);

        //Animação
        chart.animateXY(1000,2000);

    //Popular gráfico 1
        chart.setData(data);
        chart.invalidate();
        chart.setNoDataText("Gráfico não pode ser gerado");

    // Configurar gráfico 2
        //Dados
        Description description1 = new Description();
        description1.setText("");
        description1.setTextSize(8);


        List<PieEntry> pieEntries1 = new ArrayList<>();
        Double calc1 =  (proporc1 /vlrabastec)*100;
        Double calc2 =  (proporc2 /vlrabastec)*100;
        pieEntries1.add(new PieEntry(calc1.intValue(),"Combust 1"));
        pieEntries1.add(new PieEntry(calc2.intValue(),"Combust 2"));
        PieDataSet dataSet1 = new PieDataSet(pieEntries1, "");
        PieData data1 = new PieData(dataSet1);

        //Cores e aparência
        chart1.setCenterText("Proporção calculada");
        chart1.setCenterTextSize(10);
        dataSet1.setSliceSpace(1);
        dataSet1.setColors(ColorTemplate.MATERIAL_COLORS);
        chart1.setDescription(description1);
        dataSet1.setValueTextSize(12);
        chart1.setTransparentCircleRadius(56f);
        chart1.setDrawEntryLabels(false);

        //Animação
        chart1.animateXY(1000,2000);

        //Popular gráfico 2
        chart1.setData(data1);
        chart1.invalidate();
        chart1.setNoDataText("Gráfico não pode ser gerado");
    }
//Procedimento voltar
    private void voltar(){
        switch (etapa){
            case 1:
                if(backtoast!=null&&backtoast.getView().getWindowToken()!=null) {
                    finish();
                } else {
                    backtoast = Toast.makeText(MainActivity.this, "Aperte novamente para fechar a aplicação.", Toast.LENGTH_SHORT);
                    backtoast.show();
                }
                break;
            case 5:
                dialogevent();
                RetornoUI();
                etapa  = 4;
                break;
            default:
                RetornoUI();
                etapa  --;
                break;
        }
    }
//Caixa de mensagem
    public void dialogevent() {
        AlertDialog.Builder altDiag = new AlertDialog.Builder(MainActivity.this);
        altDiag.setMessage("Deseja salvar os dados em algum perfil?")
                .setTitle("Salvar")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final CharSequence perfis[] = new CharSequence[] {"Perfil 1", "Perfil 2", "Perfil 3"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Selecione o perfil");
                        builder.setItems(perfis, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                salvaPerfil(which);
                            }
                        });
                        builder.show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert  = altDiag.create();
        alert.show();
    }
//Salvar perfis
    private void salvaPerfil(Integer pf) {
        SharedPreferences prefs = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("pf"+pf+"vlrabastec", String.valueOf(vlrabastec));
        ed.putString("pf"+pf+"vlrpercent", String.valueOf(vlrpercent));
        ed.putString("pf"+pf+"vlrpercent1", String.valueOf(vlrpercent1));
        ed.putString("pf"+pf+"vlrcomb1", String.valueOf(vlrcomb1));
        ed.putString("pf"+pf+"vlrcomb2", String.valueOf(vlrcomb2));
        ed.apply();
        switch (pf){
            case 0:
                Toast.makeText(getApplicationContext(), "Dados salvos no perfil 1", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Dados salvos no perfil 2", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Dados salvos no perfil 3", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
//Salvar dados ao mudar orientação
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putDouble("vlrabastec",vlrabastec);
        bundle.putDouble("vlrcomb1",vlrcomb1);
        bundle.putDouble("vlrcomb2",vlrcomb2);
        bundle.putDouble("vlrpercent",vlrpercent);
        bundle.putInt("etapa",etapa);
    }
//Botão físico android voltar
    public void onBackPressed() {
            voltar();
    }
//Animar resultados
    public void animateTextView(float initialValue, float finalValue, final TextView  textview, final String texto) {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue);
    valueAnimator.setDuration(2000);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            textview.setText(String.valueOf(texto + valueAnimator.getAnimatedValue().toString()));
        }
    });
    valueAnimator.start();
}
}