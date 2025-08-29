import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class estudiantesServices {
    public void obtenerNumeroestudiantes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        try (var stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            int cont = 0;
            while (rs.next()) {
                System.out.print(".");
                cont++;
            }
            System.out.println("Cantidad de registros encontrados " + cont);
        }
    }

    public void insertarestudiante(Connection conn) throws SQLException {
        String sql = "INSERT INTO estudiantes (Nombre, Apellido, Edad, Correo, EstadoCivil) VALUES ('Jhonny','Depp','48','jhonnyd@tecnocomfenalco.edu.co','Soltero')";
        try (var stm = conn.prepareStatement(sql)) {
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Registro insertado de forma correcta");
            } else {
                System.out.println("Error en insercion");
            }
        }
    }

    public void actualizarestudiante(Connection conn) throws SQLException {
        String sql = "UPDATE estudiantes SET EstadoCivil='VIUDO' WHERE ID=3";
        try (var stm = conn.prepareStatement(sql)) {
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Registro actualizado de forma correcta");
            } else {
                System.out.println("Error en la actualizacion");
            }
        }
    }

    public void eliminarestudiante(Connection conn) throws SQLException {
        String sql = "DELETE FROM estudiantes WHERE ID=4";
        try (var stm = conn.prepareStatement(sql)) {
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Registro eliminado de forma correcta");
            } else {
                System.out.println("Error en la eliminacion");
            }
        }
    }

    public void insertarestudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = in.nextLine();
        System.out.print("Apellido: ");
        String apellido = in.nextLine();
        System.out.print("Edad: ");
        int edad = in.nextInt();
        in.nextLine(); // Limpiar buffer
        System.out.print("Correo: ");
        String correo = in.nextLine();
        System.out.print("Estado civil: ");
        String estadoCivil = in.nextLine();

        String sql = "INSERT INTO estudiantes (Nombre, Apellido, Edad, Correo, EstadoCivil) VALUES (?,?,?,?,?)";
        try (var stm = conn.prepareStatement(sql)) {
            stm.setString(1, nombre);
            stm.setString(2, apellido);
            stm.setInt(3, edad);
            stm.setString(4, correo);
            stm.setString(5, estadoCivil);
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Estudiante insertado correctamente.");
            } else {
                System.out.println("Error al insertar estudiante.");
            }
        }
    }

    public void actualizarestudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("ID del estudiante a actualizar: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = in.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = in.nextLine();
        System.out.print("Nueva edad: ");
        int edad = in.nextInt();
        in.nextLine();
        System.out.print("Nuevo estado civil: ");
        String estadoCivil = in.nextLine();

        String sql = "UPDATE estudiantes SET Nombre=?, Apellido=?, Edad=?, EstadoCivil=? WHERE ID=?";
        try (var stm = conn.prepareStatement(sql)) {
            stm.setString(1, nombre);
            stm.setString(2, apellido);
            stm.setInt(3, edad);
            stm.setString(4, estadoCivil);
            stm.setInt(5, id);
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Estudiante actualizado correctamente.");
            } else {
                System.out.println("No se encontró el estudiante.");
            }
        }
    }

    public void eliminarestudianteConValores(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("ID del estudiante a eliminar: ");
        int id = in.nextInt();
        in.nextLine();

        String sql = "DELETE FROM estudiantes WHERE ID=?";
        try (var stm = conn.prepareStatement(sql)) {
            stm.setInt(1, id);
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Estudiante eliminado correctamente.");
            } else {
                System.out.println("No se encontró el estudiante.");
            }
        }
    }

    public void obtenerestudianteConValores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        try (var stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                int edad = rs.getInt("Edad");
                String correo = rs.getString("Correo");
                String estadoCivil = rs.getString("EstadoCivil");
                System.out.println(String.format(
                    "ID: %d, Nombre: %s, Apellido: %s, Edad: %d, Correo: %s, Estado Civil: %s",
                    id, nombre, apellido, edad, correo, estadoCivil));
            }
        }
    }

    public void consultarEstudiantePorEmail(Connection conn, String email) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE Correo = ?";
        try (var stm = conn.prepareStatement(sql)) {
            stm.setString(1, email);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido");
                    int edad = rs.getInt("Edad");
                    String correo = rs.getString("Correo");
                    String estadoCivil = rs.getString("EstadoCivil");
                    System.out.println(String.format(
                        "ID: %d, Nombre: %s, Apellido: %s, Edad: %d, Correo: %s, Estado Civil: %s",
                        id, nombre, apellido, edad, correo, estadoCivil));
                } else {
                    System.out.println("No se encontró ningún estudiante con ese email.");
                }
            }
        }
    }
}
