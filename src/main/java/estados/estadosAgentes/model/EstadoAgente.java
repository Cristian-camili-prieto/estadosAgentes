package estados.estadosAgentes.model;

public class EstadoAgente {
    private Long id;
    private String nombre;
    private String estado; // Ejemplo: "Disponible", "En llamada"
    private int tiempoEspera; // En segundos o minutos

    public EstadoAgente() {}

    public EstadoAgente(Long id, String nombre, String estado, int tiempoEspera) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.tiempoEspera = tiempoEspera;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}
