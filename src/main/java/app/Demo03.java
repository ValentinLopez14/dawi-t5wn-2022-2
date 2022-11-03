package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	public static void main(String[] args) {
		// obtener la conexión llamoa ala unidad de percistencia
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				
				// Generar el manejador de entidades según la conexión
				EntityManager em = fabrica.createEntityManager();
				
				// Proceso de eliminar de un nuevo usuario --> para realizar una transacción(registrar, actualizar, eliminar)
				em.getTransaction().begin();
				
				// crear objeto usuario
				Usuario u = new Usuario();
				u.setCodigo(20);
				
				// Forma 1 borrado físico --> eliminado definitivamente --> delete
				//em.remove(u); //necesita toda la información del usuario --> buscar y devolver toda la información del usuario
				
				//Fomra 2 borrado lógico --> cambio de estado
				u.setEstado(2); //necesario toda la información del usuario
				em.merge(u);
				
				// confirmar la transacción
				em.getTransaction().commit();
				
				// cerrar
				em.close();
	}

}
