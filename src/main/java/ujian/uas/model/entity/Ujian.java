/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.uas.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "ujian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ujian.findAll", query = "SELECT u FROM Ujian u"),
    @NamedQuery(name = "Ujian.findById", query = "SELECT u FROM Ujian u WHERE u.id = :id"),
    @NamedQuery(name = "Ujian.findByNama", query = "SELECT u FROM Ujian u WHERE u.nama = :nama"),
    @NamedQuery(name = "Ujian.findByAlamat", query = "SELECT u FROM Ujian u WHERE u.alamat = :alamat")})
public class Ujian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat")
    private String alamat;

    public Ujian() {
    }

    public Ujian(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ujian)) {
            return false;
        }
        Ujian other = (Ujian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ujian.uas.model.entity.Ujian[ id=" + id + " ]";
    }
    
}
