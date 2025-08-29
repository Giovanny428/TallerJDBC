import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/colegio";
    static String userName = "root";
    static String password = "acme23";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             Scanner sc = new Scanner(System.in)) {

            estudiantesServices estudiantesServices = new estudiantesServices();
            int opcion;

            do {
                System.out.println("\n************************************");
                System.out.println("*   Creado por Giovanny            *");
                System.out.println("************************************");
                System.out.println("\n-----MENÚ------");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los estudiantes");
                System.out.println("5. Consultar estudiante por email");
                System.out.println("6. Salir");
                System.out.print("Elija una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        estudiantesServices.insertarestudianteConValores(conn);
                        break;
                    case 2:
                        estudiantesServices.actualizarestudianteConValores(conn);
                        break;
                    case 3:
                        estudiantesServices.eliminarestudianteConValores(conn);
                        break;
                    case 4:
                        estudiantesServices.obtenerestudianteConValores(conn);
                        break;
                    case 5:
                        System.out.print("Digite el email del estudiante: ");
                        String email = sc.nextLine();
                        estudiantesServices.consultarEstudiantePorEmail(conn, email);
                        break;
                    case 6:
                        System.out.println("Gracias por utilizar códigos de GioVaTech. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 6);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
