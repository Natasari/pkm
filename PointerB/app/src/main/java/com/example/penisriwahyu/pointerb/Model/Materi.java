package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class Materi implements Parcelable {

    int id_materi;
    int id_subbab;
    int nomor_materi;
    String id_file_materi;
    String catatan_materi;

    public Materi(){
        super();
    }

    public Materi(int id_mat, int id_sub, int nomor, String id_file, String catatan_materi){
        super();
        this.id_materi = id_mat;
        this.id_subbab = id_sub;
        this.nomor_materi = nomor;
        this.id_file_materi = id_file;
        this.catatan_materi = catatan_materi;
    }

    public Materi(int nomor, String id_file, String catatan){
        this.nomor_materi = nomor;
        this.id_file_materi = id_file;
        this.catatan_materi = catatan;
    }

    private Materi(Parcel in){
        super();
        this.id_materi = in.readInt();
        this.nomor_materi = in.readInt();
        this.id_file_materi = in.readString();
        this.catatan_materi = in.readString();

    }

    public int getIdMateri(){
        return this.id_materi;
    }

    public void setIdMateri(int id){
        this.id_materi = id;
    }

    public int getIdSubbab(){
        return this.id_subbab;
    }

    public void setIdSubbab(int id){
        this.id_subbab = id;
    }

    public int getNomorMateri(){
        return this.nomor_materi;
    }

    public void setNomorMateri(int id){
        this.nomor_materi = id;
    }
    public String getIdFileMateri(){
        return this.id_file_materi;
    }

    public void setIdFileMateri(String id){
        this.id_file_materi = id;
    }

    public String getCatatanMateri(){
        return this.catatan_materi;
    }

    public void setCatatanMateri(String materi){
        this.catatan_materi = materi;
    }

    @Override
    public String toString(){
        return "id:" + id_materi + ", nomor:" + nomor_materi + ", idFile:" + id_file_materi + ", catatan:" + catatan_materi;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdMateri());
        parcel.writeInt(getNomorMateri());
        parcel.writeString(getIdFileMateri());
        parcel.writeString(getCatatanMateri());
    }

    public static final Creator<Materi> CREATOR = new Creator<Materi>(){
        public  Materi createFromParcel(Parcel in){
            return new Materi(in);
        }

        public Materi[] newArray(int size){
            return new Materi[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_materi;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Materi other = (Materi) obj;
        if (id_materi != other.id_materi)
            return false;
        return true;
    }

}
