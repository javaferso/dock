package com.smu.vision;

import static java.lang.System.out;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Persistence;
import logica.Balanza;
import logica.Controladora;
import logica.Tienda;

public class BalanzaService {

    private final EntityManagerFactory emb;

    public BalanzaService(EntityManagerFactory emb) {
        this.emb = emb;
    }
    Controladora controladora = new Controladora();

    // Método para borrar todos los datos de la tabla zbalanza
    public void borrarDatosBalanza() {
        EntityManager em = Persistence.createEntityManagerFactory("TiOperacionesPU").createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("DELETE FROM zbalanza");
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para llenar la tabla Balanza con datos de los servidores
    public void llenarDatosBalanza() {
        EntityManager em = Persistence.createEntityManagerFactory("TiOperacionesPU").createEntityManager();
        try {
            out.println("Balanzas.Service called");
            List<Balanza> balanzas = obtenerBalanzas();
            out.println("Numero de Balanzas encontradas: " + balanzas.size());
            // Obtener la lista de tiendas
            List<Tienda> tiendas = obtenerTiendas();
            out.println("Número de tiendas: " + tiendas.size());

            for (Tienda tienda : tiendas) {
                // Ejecutar la consulta en cada servidor
                List<Object[]> resultados = obtenerDatosDeTienda(tienda);

                // Insertar los datos en la tabla Balanza
                em.getTransaction().begin();
                for (Object[] resultado : resultados) {
                    Balanza balanza = new Balanza();
                    // Asignar los valores de resultado a la entidad Balanza
                    // Ejemplo: balanza.setCampo(resultado[0]);
                    out.println("Campo 1: " + resultado[0]);
                    out.println("Campo 2: " + resultado[1]);
                    out.println("Campo 3: " + resultado[2]);
                    out.println("Campo 4: " + resultado[3]);
                    out.println("Campo 5: " + resultado[4]);

                    em.persist(balanza);
                }
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes.
        } finally {
            em.close();
        }
    }

    // Método para obtener la lista de tiendas
    private List<Tienda> obtenerTiendas() {
        EntityManager em = Persistence.createEntityManagerFactory("MonitorTIPU").createEntityManager();
        List<Tienda> tiendas = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT t FROM Tienda t");
            tiendas = query.getResultList();
            System.out.println("Número de tiendas encontradas en obtener Tiendas (): " + tiendas.size());
            for (Tienda tienda : tiendas) {
                System.out.println("Tienda encontrada en obtenerTiendas(): " + tienda.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return tiendas;
    }

    // Método para ejecutar la consulta en los servidores Informix específicos para cada tienda
    private List<Object[]> obtenerDatosDeTienda(Tienda tienda) throws SQLException {
        List<Object[]> resultados = new ArrayList<>();
        String url = "jdbc:informix-sqli://" + tienda.getDireccionIp() + ":1527/INFORMIXSERVER=" + tienda.getInstanciaInformix();
        out.println(url);
        String user = "usuariosopbd";
        String password = "Cji$R9kwA";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM zbalanza";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                resultados.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener datos de la tienda: " + tienda.getNombre(), e);
        }

        return resultados;
    }

    private List<Balanza> obtenerBalanzas() {
        EntityManager em = Persistence.createEntityManagerFactory("TiOperacionesPU").createEntityManager();
        List<Balanza> balanzas = new ArrayList<>();
        try {
            balanzas = controladora.obtenerBalanzas();

            System.out.println("Número de tiendas encontradas en obtenerBalanzas()");
            for (Balanza balanza : balanzas) {
                System.out.println("Balanza encontradas: ");
            }
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return balanzas;
    }
}
