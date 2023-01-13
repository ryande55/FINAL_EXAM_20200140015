/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.uas.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ujian.uas.model.entity.Ujian;
import ujian.uas.model.jpa.UjianJpaController;
/**
 * 
 * 
 * @author Ryanda Pratama Putra Wibisono 20200140015
 */
@RestController 
@CrossOrigin
@RequestMapping("/liatnilai")
public class LatihanUasController {
    Ujian nil = new Ujian(); //buat manggil entity
    UjianJpaController njc = new UjianJpaController(); //manggil jpa
    
    @GetMapping
    public List<Ujian> getData(){
        List<Ujian> data = new ArrayList<>(); //buat object baru
        try{
            data = njc.findUjianEntities(); //get alldata
            
        }catch(Exception e){} 
        return data; // return objek data
    }
    
    @GetMapping("/{id}") // get data by id 
    public List<Ujian> getNilaiEntities (@PathVariable("id") int id){ 
    List<Ujian> dataa = new ArrayList<>(); //create objek baru
    try{
        nil = njc.findUjian(id); //get data dari id
        dataa.add(nil); // nambahin data
    }catch(Exception e){}
        return dataa;
    }
    
    @PostMapping //untuk menambahkan data
    public String insertData(HttpEntity<String> requestdata){ //nerima input data dari user
        String message = "Selamat anda berhasil"; // memberi message
        Ujian ujian = null;
        try{
            String json_receive = requestdata.getBody(); 
            ObjectMapper map = new ObjectMapper(); //ngambil dr json dikumpulin 
            ujian = map.readValue(json_receive, Ujian.class);
            njc.create(ujian);
            
        }catch(Exception e){
        message = "gagal menambahkan";
        }
        return message;
    }
        @PutMapping //untuk edit
        public String updateData(HttpEntity<String> requestdata){
        String message = "Sukses MengUpdate";
        Ujian nilai = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            nilai = map.readValue(json_receive, Ujian.class);
            njc.edit(nilai);
            
        }catch(Exception e){
        message = "gagal mengupdate";
        }
        return message;
    }
        @DeleteMapping
        public String deleteData(HttpEntity<String> requestdata){
        String message = "Sukses Menghapus";
        Ujian ujian = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            ujian = map.readValue(json_receive, Ujian.class);
            njc.destroy(ujian.getId());
            
        }catch(Exception e){
        message = "gagal menghapus";
        }
        return message;
    }
    
}
