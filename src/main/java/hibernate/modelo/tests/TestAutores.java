package hibernate.modelo.tests;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hibernate.modelo.Autor;
import hibernate.modelo.Libro;

public class TestAutores {
	//
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	//
	public static void main(String[] args) {
		//crearDatos();
		crearDatos2();
		imprimirDatos();
	}
	//
	static void crearDatos() {
		EntityManager man = emf.createEntityManager();
		//
		man.getTransaction().begin();
		//
		Autor autor1 = new Autor(1L, "Pablo Perez", "Española");
		Autor autor2 = new Autor(2L, "Elena Gomez", "Mexicana");
		Autor autor3 = new Autor(3L, "Miguel Lopez", "Chilena");
		//
		man.persist(autor1);
		man.persist(autor2);
		man.persist(autor3);		
		//
		man.persist(new Libro(1L, "Programar en Java es facil", autor2));
		man.persist(new Libro(2L, "Como vestirse con estilo", autor3));
		man.persist(new Libro(3L, "Como cocinar sin quemar la cocina", autor1));
		man.persist(new Libro(4L, "Programar en Cobol es divertido", autor2));
		man.persist(new Libro(5L, "Programar en Cobol no es divertido", autor2));
		//
		man.getTransaction().commit();
		man.close();
	}
	//
	static void crearDatos2() {
		EntityManager man = emf.createEntityManager();
		//
		man.getTransaction().begin();
		//
		Libro l1 = new Libro();
		l1.setId(1L);
		l1.setTitulo("JPA e Hibernate");
		man.persist(l1);
		//
		Autor a1 = new Autor(1L, "Dani", "Española");
		a1.addLibro(l1);
		System.out.println("Libros escritos (pre-save): " + a1.getLibros().size());
		man.persist(a1);
		//
		man.getTransaction().commit();
		man.close();
	}
	//
	static void imprimirDatos() {
		EntityManager man = emf.createEntityManager();
		Autor autor = man.find(Autor.class, 1L);
		List<Libro> libros = autor.getLibros();
		//libros.size();//Hack
		//man.close();
		//
		//System.out.println(autor);
		System.out.println("Libros escritos (post-save): " + libros.size());
		for (Libro libro : libros) {
			System.out.println("* " + libro.toString());
		}
		
		man.close();
	}
}
