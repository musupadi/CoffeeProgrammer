package com.destinyapp.coffeeprogrammer.Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.coffeeprogrammer.Activity.Login.LoginActivity;
import com.destinyapp.coffeeprogrammer.Activity.Main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Musupadi {

    public void loaduserProfile(AccessToken newAccessToken, final TextView nama, final ImageView image,final Context ctx){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String Email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com"+id+"/picture?type=normal";
                    nama.setText(first_name+" "+last_name);
                    Glide.with(ctx)
                            .load(image_url)
                            .into(image);


//                    Toast.makeText(getActivity(), Email, Toast.LENGTH_SHORT).show();
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters  = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }
    public String Base64(String base){
//        String username = "faba_admin_db";
//        String password = "WhiteList_admin_FABA2019";
//
//        String base = username+":"+password;

        String authHeader = Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        return authHeader;
    }
    public String md5(final String s){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    public String MagicRP(double nilai){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        BigDecimal bd1 = new BigDecimal(nilai).setScale(0, RoundingMode.HALF_UP);
        String Format = formatRupiah.format(bd1);
        String MAGIC1 = Format.replace("Rp","Rp.");
        return MAGIC1;
    }
    public String MagicChange(String magic){
        String MAGIC1 = magic.replace("Rp","");
        String MAGIC2 = MAGIC1.replace(",","");
        return MAGIC2.replace(".","");
    }
    public String MagicDate(String magic){
        String Magic = magic;
        if (magic.equals("01")){
            Magic = "1";
        }else if (magic.equals("02")){
            Magic = "2";
        }else if(magic.equals("03")){
            Magic = "3";
        }else if(magic.equals("04")){
            Magic = "4";
        }else if(magic.equals("05")){
            Magic = "5";
        }else if(magic.equals("06")){
            Magic = "6";
        }else if(magic.equals("07")){
            Magic = "7";
        }else if(magic.equals("08")){
            Magic = "8";
        }else if(magic.equals("09")){
            Magic = "9";
        }

        return Magic;
    }
    public static String dayName(String inputDate, String format){
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }
    public String formatTanggal(String tanggal){
        String Tanggal = "";
        String tahun = tanggal.substring(0,3);
        String bulan = tanggal.substring(5,6);
        String hari = tanggal.substring(8,9);
        Tanggal = hari+"/"+bulan+"/"+tahun;
        return Tanggal;
    }
    public String getDataTanggal(String tanggal){
        String today = dayName(tanggal,"dd/MM/yyyy");
        String HariIni = "Senin";
        if(today.equals("Monday")){
            HariIni = "Senin";
        }else if(today.equals("Tuesday")){
            HariIni = "Selasa";
        }else if(today.equals("Wednesday")){
            HariIni = "Rabu";
        }else if(today.equals("Thursday")){
            HariIni = "Kamis";
        }else if(today.equals("Friday")){
            HariIni = "Jumat";
        }else if(today.equals("Saturday")){
            HariIni = "Sabtu";
        }else if(today.equals("Sunday")){
            HariIni = "Minggu";
        }
        return HariIni;
    }
    public String getToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String thisDay = dateFormat.format(date);
        String today = dayName(thisDay,"dd/MM/yyyy");
        String HariIni = "Senin";
        if(today.equals("Monday")){
            HariIni = "Senin";
        }else if(today.equals("Tuesday")){
            HariIni = "Selasa";
        }else if(today.equals("Wednesday")){
            HariIni = "Rabu";
        }else if(today.equals("Thursday")){
            HariIni = "Kamis";
        }else if(today.equals("Friday")){
            HariIni = "Jumat";
        }else if(today.equals("Saturday")){
            HariIni = "Sabtu";
        }else if(today.equals("Sunday")){
            HariIni = "Minggu";
        }
        return HariIni;
    }
    public String Tanggals(String tanggal){
        String tgl = tanggal.substring(8,10);
        String bulan = tanggal.substring(4,7);
        String tahun = tanggal.substring(30,34);
        String Bulan = "01";
        if (bulan.equals("Jan")){
            Bulan = "01";
        }else if(bulan.equals("Feb")){
            Bulan = "02";
        }else if(bulan.equals("Mar")){
            Bulan = "03";
        }else if(bulan.equals("Apr")){
            Bulan = "04";
        }else if(bulan.equals("May")){
            Bulan = "05";
        }else if(bulan.equals("Jun")){
            Bulan = "06";
        }else if(bulan.equals("Jul")){
            Bulan = "07";
        }else if(bulan.equals("Aug")){
            Bulan = "08";
        }else if(bulan.equals("Sep")){
            Bulan = "09";
        }else if(bulan.equals("Oct")){
            Bulan = "10";
        }else if(bulan.equals("Nov")){
            Bulan = "11";
        }else if(bulan.equals("Dec")){
            Bulan = "12";
        }
        return tahun+"-"+Bulan+"-"+tgl;
    }
    public String Tanggal(String tanggal){
        String tgl = tanggal.substring(8,10);
        String bulan = tanggal.substring(4,7);
        String tahun = tanggal.substring(30,34);
        String Bulan = "Januari";
        if (bulan.equals("Jan")){
            Bulan = "Januari";
        }else if(bulan.equals("Feb")){
            Bulan = "Februari";
        }else if(bulan.equals("Mar")){
            Bulan = "Maret";
        }else if(bulan.equals("Apr")){
            Bulan = "April";
        }else if(bulan.equals("May")){
            Bulan = "Mei";
        }else if(bulan.equals("Jun")){
            Bulan = "Juni";
        }else if(bulan.equals("Jul")){
            Bulan = "Juli";
        }else if(bulan.equals("Aug")){
            Bulan = "Agustus";
        }else if(bulan.equals("Sep")){
            Bulan = "September";
        }else if(bulan.equals("Oct")){
            Bulan = "Oktober";
        }else if(bulan.equals("Nov")){
            Bulan = "November";
        }else if(bulan.equals("Dec")){
            Bulan = "Desember";
        }
        return tgl+" "+Bulan+" "+tahun;
    }
    public String MiniDescription(String string){
        String data = string;
        if (string.length()>=70){
            data = string.substring(0,70);
        }
        return data+"...";
    }
}

