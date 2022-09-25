package procesos;


import interfaz.Menu;
import modelos.Persona;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionPersona {
    Menu interfaz;
    Persona persona;
    ArrayList<Persona> listaPersonas;

    public GestionPersona() {
        //Constructor
        persona = null;
        this.listaPersonas = new ArrayList<>();
    }

    public void agregar() {
        //Guarda los datos de los inputs en variables
        String nombre =String.valueOf(interfaz.txtNombre.getText());
        String codigo = String.valueOf(interfaz.txtCodigo.getText());
        String menu = String.valueOf(interfaz.comboBoxMenu.getSelectedItem());
        String dia = String.valueOf(interfaz.comboBoxDia.getSelectedItem());
        //Crea un objeto de tipo persona y llena los atributos
        persona = new Persona(nombre, codigo, menu, dia);
        //Añade el objeto a la lista
        listaPersonas.add(persona);
        JOptionPane.showMessageDialog(null,"El "+menu+" comprado por "+nombre+" con codigo "+codigo+" Para el dia "+dia);
    }

    public void sumarMenus() {
        //hacemos la sumatoria de las filas con los almuezos vendidos
    }

    public void sumaTotal() {
        //tomamos los resultados de sumaroMenus y los sumamos
    }
}
