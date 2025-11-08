import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Clase base: Material
// Representa un material bibliográfico genérico (libro o revista)

class Material {
    protected int id;
    protected String titulo;
    protected String autor;
    protected boolean disponible;
    protected String usuario;
    protected Date fechaPrestamo;

    // Constructor: inicializa el material como disponible por defecto
    public Material(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
        this.usuario = "";
        this.fechaPrestamo = null;
    }

    // Muestra la información general del material
    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Título: " + titulo + " | Autor: " + autor);
        System.out.println("Estado: " + (disponible ? "Disponible" : "Prestado a " + usuario));
    }

    // Métodos para obtener valores de los atributos
    public int getId() { return id; }
    public boolean isDisponible() { return disponible; }
    public Date getFechaPrestamo() { return fechaPrestamo; }

    // Marca el material como prestado y registra el usuario y la fecha
    public void prestar(String nombreUsuario) {
        this.disponible = false;
        this.usuario = nombreUsuario;
        this.fechaPrestamo = new Date();
    }

    // Marca el material como devuelto y lo deja disponible nuevamente
    public void devolver() {
        this.disponible = true;
        this.usuario = "";
        this.fechaPrestamo = null;
    }
}

// Clase Libro (hereda de Material)
// Representa un material de tipo libro

class Libro extends Material {
    public Libro(int id, String titulo, String autor) {
        super(id, titulo, autor);
    }

    // Sobrescribe el método mostrarInfo para indicar que es un libro
    @Override
    public void mostrarInfo() {
        System.out.print("[LIBRO] ");
        super.mostrarInfo();
    }
}

// Clase Revista (hereda de Material)
// Representa un material de tipo revista

class Revista extends Material {
    public Revista(int id, String titulo, String autor) {
        super(id, titulo, autor);
    }

    // Sobrescribe el método mostrarInfo para indicar que es una revista
    @Override
    public void mostrarInfo() {
        System.out.print("[REVISTA] ");
        super.mostrarInfo();
    }
}


// Clase SistemaBiblioteca
// Contiene las funciones principales del sistema:
// inventario, préstamos, devoluciones y multas

class SistemaBiblioteca {
    private ArrayList<Material> inventario;  // Lista de materiales disponibles en la biblioteca
    private Scanner scanner;                 // Para leer datos ingresados por el usuario
    private static final int DIAS_PRESTAMO = 7;  // Plazo máximo de préstamo (en días)
    private static final int MULTA_POR_DIA = 500; // Valor de la multa por cada día de retraso

    // Constructor: crea el sistema con algunos materiales iniciales
    public SistemaBiblioteca() {
        inventario = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Materiales predeterminados
        inventario.add(new Libro(1, "Programación en Java", "Deitel"));
        inventario.add(new Revista(2, "Revista Sistemas", "Editorial Tech"));
    }

    // Agrega un nuevo material al inventario
    public void agregarMaterial() {
        System.out.println("\n=== AGREGAR MATERIAL ===");

        System.out.print("Tipo (1=Libro, 2=Revista): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        int nuevoId = inventario.size() + 1;

        if (tipo == 1) {
            inventario.add(new Libro(nuevoId, titulo, autor));
            System.out.println("Libro agregado exitosamente");
        } else if (tipo == 2) {
            inventario.add(new Revista(nuevoId, titulo, autor));
            System.out.println("Revista agregada exitosamente");
        } else {
            System.out.println("Tipo inválido");
        }
    }

    // Muestra todos los materiales del inventario
    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO BIBLIOGRÁFICO ===");

        if (inventario.isEmpty()) {
            System.out.println("No hay material en el inventario");
            return;
        }

        for (Material m : inventario) {
            m.mostrarInfo();
            System.out.println("--------------------");
        }
    }

    // Permite realizar un préstamo de material
    public void realizarPrestamo() {
        System.out.println("\n=== PRÉSTAMO DE MATERIAL ===");

        System.out.print("ID del material: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Material material = buscarMaterial(id);

        if (material == null) {
            System.out.println("Material no encontrado");
            return;
        }

        if (!material.isDisponible()) {
            System.out.println("Material no disponible");
            return;
        }

        System.out.print("Nombre del usuario: ");
        String usuaString = scanner.nextLine();

        material.prestar(usuario);
        System.out.println("Préstamo realizado exitosamente");
        System.out.println("Plazo de devolución: " + DIAS_PRESTAMO + " días");
    }

    // Permite devolver un material y calcula la multa si hay retraso
    public void realizarDevolucion() {
        System.out.println("\n=== DEVOLUCIÓN DE MATERIAL ===");

        System.out.print("ID del material: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Material material = buscarMaterial(id);

        if (material == null) {
            System.out.println("Material no encontrado");
            return;
        }

        if (material.isDisponible()) {
            System.out.println("Este material no está prestado");
            return;
        }

        int multa = calcularMulta(material.getFechaPrestamo());
        material.devolver();

        System.out.println("Devolución realizada exitosamente");

        if (multa > 0) {
            System.out.println("MULTA POR RETRASO: $" + multa + " pesos");
        } else {
            System.out.println("Devolución a tiempo - Sin multa");
        }
    }

    // Muestra las multas vigentes para materiales prestados fuera de plazo
    public void consultarMultas() {
        System.out.println("\n=== CONSULTA DE MULTAS ===");
        System.out.println("Multa por día de retraso: $" + MULTA_POR_DIA);
        System.out.println();

        boolean hayMultas = false;

        for (Material m : inventario) {
            if (!m.isDisponible()) {
                int multa = calcularMulta(m.getFechaPrestamo());
                if (multa > 0) {
                    hayMultas = true;
                    m.mostrarInfo();
                    System.out.println("MULTA: $" + multa + " pesos");
                    System.out.println("--------------------");
                }
            }
        }

        if (!hayMultas) {
            System.out.println("No hay multas pendientes");
        }
    }

    // Busca un material en el inventario por su ID
    private Material buscarMaterial(int id) {
        for (Material m : inventario) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // Calcula la multa por retraso en la devolución del material
    private int calcularMulta(Date fechaPrestamo) {
        if (fechaPrestamo == null) return 0;

        long diferencia = new Date().getTime() - fechaPrestamo.getTime();
        long diasTranscurridos = TimeUnit.MILLISECONDS.toDays(diferencia);
        long diasRetraso = diasTranscurridos - DIAS_PRESTAMO;

        if (diasRetraso > 0) {
            return (int) (diasRetraso * MULTA_POR_DIA);
        }
        return 0;
    }

    // Muestra el menú principal del sistema
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║     BIBLIOTECA ETITC               ║");
            System.out.println("║  Sistema de Gestión Bibliotecaria  ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. Inventario (Agregar/Ver)");
            System.out.println("2. Realizar Préstamo");
            System.out.println("3. Realizar Devolución");
            System.out.println("4. Consultar Multas");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            // Estructura switch para controlar las opciones del menú
            switch (opcion) {
                case 1:
                    mostrarInventario();
                    System.out.print("\n¿Desea agregar material? (s/n): ");
                    String resp = scanner.nextLine();
                    if (resp.equalsIgnoreCase("s")) {
                        agregarMaterial();
                    }
                    break;
                case 2:
                    realizarPrestamo();
                    break;
                case 3:
                    realizarDevolucion();
                    break;
                case 4:
                    consultarMultas();
                    break;
                case 5:
                    System.out.println("\n¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("\n✗ Opción inválida");
            }

        } while (opcion != 5);

        scanner.close();
    }
}

// Clase principal (punto de entrada del programa)
public class BibliotecaETITC {
    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        sistema.mostrarMenu();
    }
}