/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Cornelia Putri
 */
@ManagedBean
@RequestScoped
public class Seblak {
    
    private String idSeblak;
    public void setidSeblak(String idSeblak) {
        this.idSeblak = idSeblak;
    }
    public String getidSeblak() {
        return idSeblak;
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

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_Seblak(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_idSeblak = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from seblak where idSeblak="+Field_idSeblak);
          Seblak obj_Seblak = new Seblak();
          rs.next();
          obj_Seblak.setidSeblak(rs.getString("idSeblak"));
          obj_Seblak.setnamaSeblak(rs.getString("namaSeblak"));
          obj_Seblak.setharga(rs.getString("harga"));
          sessionMap.put("EditSeblak", obj_Seblak);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/editseblak.xhtml?faces-redirect=true";   
}

public String Delete_Seblak(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_idSeblak = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from seblak where idSeblak=?");
         ps.setString(1, Field_idSeblak);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/seblak.xhtml?faces-redirect=true";   
}

public String Update_Seblak(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_idSeblak= params.get("Update_idSeblak");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update seblak set idSeblak=?, namaSeblak=?, harga=? where idSeblak=?");
            ps.setString(1, idSeblak);
            ps.setString(2, namaSeblak);
            ps.setString(3, harga);
            ps.setString(4, Update_idSeblak);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/seblak.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_seblak() throws Exception{
        ArrayList list_of_seblak=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from seblak");
            while(rs.next()){
                Seblak obj_Seblak = new Seblak();
                obj_Seblak.setidSeblak(rs.getString("idSeblak"));
                obj_Seblak.setnamaSeblak(rs.getString("namaSeblak"));
                obj_Seblak.setharga(rs.getString("harga"));
                list_of_seblak.add(obj_Seblak);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_seblak;
}
    
public String Tambah_Seblak(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into seblak(idSeblak, namaSeblak, harga) value('"+idSeblak+"','"+namaSeblak+"','"+harga+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/seblak.xhtml?faces-redirect=true";
    }
    /**
     * Creates a new instance of Seblak
     */
    public Seblak() {
    }
    
}
