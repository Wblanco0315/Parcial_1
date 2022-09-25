package modelos;


public class Persona {
    private String nombre;
    private String codigo;
    private String menu;
    private String dia;

    public Persona(String nombre, String codigo, String menu, String dia) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.menu = menu;
        this.dia = dia;
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
    
    
    
}
