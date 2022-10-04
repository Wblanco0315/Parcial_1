package modelos;

public class Persona {

    private String nombre;
    private String codigo;
    private int edad;
    private String sexo;
    private boolean estado;
    private String carrera;
    private String menu;
    private String dia;

    public Persona(String nombre, String codigo, int edad, String sexo, boolean estado, String carrera, String menu, String dia) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.edad = edad;
        this.sexo = sexo;
        this.carrera = carrera;
        this.menu = menu;
        this.dia = dia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCodigo: " + codigo + "\nEdad: " + edad + "\nSexo: " + sexo + "\nCarrera: " + carrera + "\nMenu: " + menu + "\nDia: " + dia;
    }

}
