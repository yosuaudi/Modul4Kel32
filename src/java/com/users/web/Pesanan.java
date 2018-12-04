/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 *
 * @author Khairuzzain Shofar
 */
@ManagedBean
@RequestScoped
public class Pesanan {
    private String idPemesan;
    public void setidPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }
    public String getidPemesan() {
        return idPemesan;
    }

    private String namaPemesan;
    public void setnamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }
    public String getnamaPemesan() {
        return namaPemesan;
    }
    
    private String jmlPesanan;
    public void setjmlPesanan(String jmlPesanan) {
        this.jmlPesanan = jmlPesanan;
    }
    public String getjmlPesanan() {
        return jmlPesanan;
    }
    
    private String namaSeblak;
    public void setnamaSeblak(String namaSeblak) {
        this.namaSeblak = namaSeblak;
    }
    public String getnamaSeblak() {
        return namaSeblak;
    }
    
    private String harga;
    public void setharga(String harga) {
        this.harga = harga;
    }
    public String getharga() {
        return harga;
    }
    
    private String totalharga;
    public void settotalharga(String totalharga) {
        this.totalharga = totalharga;
    }
    public String gettotalharga() {
        return totalharga;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

    public ArrayList getGet_all_pesanan() throws Exception{
        ArrayList list_of_pesanan=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from pesanan");
            while(rs.next()){
                Pesanan obj_Pesanan = new Pesanan();
                obj_Pesanan.setidPemesan(rs.getString("ID Pemesan"));
                obj_Pesanan.setnamaPemesan(rs.getString("Nama Pemesan"));
                obj_Pesanan.setjmlPesanan(rs.getString("Jumlah Pesanan"));
                obj_Pesanan.setnamaSeblak(rs.getString("Seblak"));
                obj_Pesanan.setharga(rs.getString("Harga Satuan"));
                obj_Pesanan.settotalharga(rs.getString("Total Harga"));
                list_of_pesanan.add(obj_Pesanan);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_pesanan;
}
    /**
     * Creates a new instance of Pesanan
     */
    public Pesanan() {
    }
    
}
