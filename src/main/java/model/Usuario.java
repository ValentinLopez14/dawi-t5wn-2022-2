package model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name = "tb_usuarios")
@Data

public class Usuario {
	
	@Id
	@Column(name = "cod_usua")
	private Integer codigo; //cod_usua  int auto_increment,
	@Column(name = "nom_usua", length = 15)
	private String nombre; //nom_usua varchar(15),
	@Column(name = "ape_usua", length = 25)
	private String apellido; //ape_usua varchar(25),
	@Column(name = "usr_usua", unique = true, length = 45)
	private String usuario; // usr_usua char(45) NOT NULL unique,
	@Column(name = "cla_usua")
	private String clave; //cla_usua char(100),
	@Column(name = "fna_usua")
	private String fchnac; //fna_usua  date  null,
	@Column(name = "idtipo")
	private Integer tipo; //idtipo    int DEFAULT 2 CHECK (idtipo IN (1, 2)), 
	@Column(name = "est_usua")
	private Integer estado; //est_usua  int DEFAULT 1 check (est_usua IN (1,2)),
	
	//sirve para hacer Inner Join
	@ManyToOne
	@JoinColumn(name = "idtipo", insertable = false, updatable = false)
	private Tipo objTipo;
	
}


