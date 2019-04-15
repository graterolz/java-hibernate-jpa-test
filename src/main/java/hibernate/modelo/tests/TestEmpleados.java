package hibernate.modelo.tests;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hibernate.modelo.Direccion;
import hibernate.modelo.Empleado;


public class TestEmpleados {
	//
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	//
	public static void main(String[] args) {
		EntityManager man = emf.createEntityManager();
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1979, 6, 6));
		e.setDireccion(new Direccion(15L, "Calle Falsa, 123","Caracas","Caracas","Venezuela"));
		//
		man.getTransaction().begin();
		man.persist(e);
		man.getTransaction().commit();
		man.close();
		//
		imprimirTodo();
		/*
		man = emf.createEntityManager();
		man.getTransaction().begin();
		e = man.merge(e); 
		e.setNombre("Dani");
		man.remove(e);
		man.getTransaction().commit();
		man.close();
		//
		imprimirTodo();
		*/
	}
	//
	public static void main2() {
		// Crear el gestor de persistencia (EM)
		insertInicial();
		imprimirTodo();
		//
		EntityManager man = emf.createEntityManager();
		man.getTransaction().begin();
		Empleado e = man.find(Empleado.class, 10L);
		e.setNombre("David");
		e.setApellidos("Lopez");
		man.getTransaction().commit();
		//
		imprimirTodo();
		man.close();
	}	
	//
	private static void insertInicial() {
		EntityManager man = emf.createEntityManager();
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1979, 6, 6));
		Empleado e2 = new Empleado(25L, "Martinez", "Jose", LocalDate.of(1984, 10, 10));		
		//
		man.getTransaction().begin();
		man.persist(e);
		man.persist(e2);
		man.getTransaction().commit();
		man.close();
	}
	//
	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager man = emf.createEntityManager();		
		List<Empleado> empleados = (List<Empleado>) man.createQuery("FROM Empleado").getResultList();
		System.out.println("En esta base de datos hay " + empleados.size() + " empleados.");
		for (Empleado emp : empleados) {
			System.out.println(emp.toString());
		}
		System.out.println("---");
		man.close();		
	}
}