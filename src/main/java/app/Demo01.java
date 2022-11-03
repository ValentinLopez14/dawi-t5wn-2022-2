package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	public static void main(String[] args) {
		// obtener la conexión llamoa ala unidad de percistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// Generar el manejador de entidades según la conexión
		EntityManager em = fabrica.createEntityManager();
		
		// Proceso de registro de un nuevo usuario --> para realizar una transacción(registrar, actualizar, eliminar)
		em.getTransaction().begin();
		
		// crear objeto usuario
		
		//Este es un constructoir con todos los datos
		//Usuario u = new Usuario(20, "Juan", "Perez", "jperez", "55555", "2000/10/05", 1, 1);
		
		//Pero cuando se usa con Lombok se tiene que usar un constructor vacío y setiar los datos
		Usuario u = new Usuario();
		u.setCodigo(20);
		u.setNombre("Juan");
		u.setApellido("Perez");
		u.setUsuario("jperez");
		u.setClave("55555");
		u.setFchnac("2000/10/05");
		u.setTipo(1);
		u.setEstado(1);
		em.persist(u);
		
		// confirmar la transacción
		em.getTransaction().commit();
		
		// cerrar
		em.close();
	}

}
