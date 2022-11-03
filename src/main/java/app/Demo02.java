package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	
	public static void main(String[] args) {
		// obtener la conexión llamoa ala unidad de percistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// Generar el manejador de entidades según la conexión
		EntityManager em = fabrica.createEntityManager();
		
		// Proceso de actualizar de un nuevo usuario --> para realizar una transacción(registrar, actualizar, eliminar)
		em.getTransaction().begin();
		
		// crear objeto usuario
		//Usuario u = new Usuario(20, "Juan Carlos", "Perez Cruz", "jperez", "77777", "2000/10/05", 1, 2);
		Usuario u = new Usuario();
		u.setCodigo(20);
		u.setNombre("Juan Carlos");
		u.setApellido("Perez Cruz");
		u.setUsuario("jperez");
		u.setClave("77777");
		u.setFchnac("2000/10/05");
		u.setTipo(1);
		u.setEstado(2);
		em.merge(u);
		
		// confirmar la transacción
		em.getTransaction().commit();
		
		// cerrar
		em.close();
	}

}
