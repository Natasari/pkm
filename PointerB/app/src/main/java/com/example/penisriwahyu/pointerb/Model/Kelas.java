package com.example.penisriwahyu.pointerb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Krilido on 3/13/2015.
 */
public class Kelas implements Parcelable {
    int id_kelas;
    int nomor_kelas;
    String nama_kelas;

    public Kelas(){
        super();
    }

    public Kelas(int id, int no, String nama){
        super();
        this.id_kelas = id;
        this.nomor_kelas = no;
        this.nama_kelas = nama;
    }
    public Kelas(int no, String nama){
        this.nomor_kelas = no;
        this.nama_kelas = nama;
    }

    private Kelas(Parcel in){
        super();
        this.id_kelas = in.readInt();
        this.nomor_kelas = in.readInt();
        this.nama_kelas = in.readString();
    }


    public int getIdKelas(){
        return this.id_kelas;
    }

    public void setIdKelas(int id){
        this.id_kelas = id;
    }

    public int getNomorKelas(){
        return this.nomor_kelas;
    }

    public void setNomorKelas(int id){
        this.nomor_kelas = id;
    }

    public String getNamaKelas(){
        return this.nama_kelas;
    }

    public void setNamaKelas(String nama){
        this.nama_kelas = nama;
    }

    @Override
    public String toString(){
        return "id:" + id_kelas + ", nomor Kelas:" + nomor_kelas + ", nama Kelas:" + nama_kelas;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(getIdKelas());
        parcel.writeInt(getNomorKelas());
        parcel.writeString(getNamaKelas());
    }

    public static final Creator<Kelas> CREATOR = new Creator<Kelas>(){
        public  Kelas createFromParcel(Parcel in){
            return new Kelas(in);
        }

        public Kelas[] newArray(int size){
            return new Kelas[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_kelas;
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
        Kelas other = (Kelas) obj;
        if (id_kelas != other.id_kelas)
            return false;
        return true;
    }

}
