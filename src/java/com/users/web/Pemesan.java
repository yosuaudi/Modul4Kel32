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
public class Pemesan {
    
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
    
    private String idSeblak;
    public void setidSeblak(String idSeblak) {
        this.idSeblak = idSeblak;
    }
    public String getidSeblak() {
        return idSeblak;
    }

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_Pemesan(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_idPemesan = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from pemesan where idPemesan="+Field_idPemesan);
          Pemesan obj_Pemesan = new Pemesan();
          rs.next();
          obj_Pemesan.setidPemesan(rs.getString("idPemesan"));
          obj_Pemesan.setnamaPemesan(rs.getString("namaPemesan"));
          obj_Pemesan.setjmlPesanan(rs.getString("jmlPesanan"));
          obj_Pemesan.setidSeblak(rs.getString("idSeblak"));
          sessionMap.put("EditPemesan", obj_Pemesan);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/editpemesan.xhtml?faces-redirect=true";   
}

public String Delete_Pemesan(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_idPemesan = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from pemesan where idPemesan=?");
         ps.setString(1, Field_idPemesan);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/pemesan.xhtml?faces-redirect=true";   
}

public String Update_Pemesan(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_idPemesan= params.get("Update_idPemesan");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update pemesan set idPemesan=?, namaPemesan=?, jmlPesanan=?, idSeblak=? where idPemesan=?");
            ps.setString(1, idPemesan);
            ps.setString(2, namaPemesan);
            ps.setString(3, jmlPesanan);
            ps.setString(4, idSeblak);
            ps.setString(5, Update_idPemesan);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/pemesan.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_pemesan() throws Exception{
        ArrayList list_of_pemesan=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from pemesan");
            while(rs.next()){
                Pemesan obj_Pemesan = new Pemesan();
                obj_Pemesan.setidPemesan(rs.getString("idPemesan"));
                obj_Pemesan.setnamaPemesan(rs.getString("namaPemesan"));
                obj_Pemesan.setjmlPesanan(rs.getString("jmlPesanan"));
                obj_Pemesan.setidSeblak(rs.getString("idSeblak"));
                list_of_pemesan.add(obj_Pemesan);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_pemesan;
}
    
public String Tambah_Pemesan(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into pemesan(idPemesan, namaPemesan, jmlPesanan, idSeblak) value('"+idPemesan+"','"+namaPemesan+"','"+jmlPesanan+"','"+idSeblak+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/pemesan.xhtml?faces-redirect=true";
    }
    public Pemesan() {
    }
    
}
