package procesos;


import interfaz.Menu;
import modelos.Persona;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionPersona {
    String nombre;
    String codigo;
    String menu;
    String dia;
    
    //Menu interfaz;
    Persona persona;
    ArrayList<Persona> listaPersonas;

    public GestionPersona() {
        //Constructor
        persona = null;
        this.listaPersonas = new ArrayList<>();
    }

    public void agregar() {
        //Guarda los datos de los inputs en variables
        nombre =String.valueOf(Menu.txtNombre.getText());
        codigo = String.valueOf(Menu.txtCodigo.getText());
        menu = String.valueOf(Menu.comboBoxMenu.getSelectedItem());
        dia = String.valueOf(Menu.comboBoxDia.getSelectedItem());
        //Crea un objeto de tipo persona y llena los atributos
        persona = new Persona(nombre, codigo, menu, dia);
        //Añade el objeto a la lista
        
        if(nombre.equals("") && codigo.equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacios");
        }
        else{
            listaPersonas.add(persona);
            JOptionPane.showMessageDialog(null,"El "+menu+"\nComprado por: "+nombre+"\nCodigo: "+codigo+"\nPara el dia: "+dia);
        }
    }
    
    public void retirar(){
        int posicion = 0;
        String cod = JOptionPane.showInputDialog("Ingrese su codigo");
        
        if(cod.equals("")){
            JOptionPane.showMessageDialog(null, "No se ingresó el codigo");
        }
        else if(codigo == null){
            JOptionPane.showMessageDialog(null, "no se han agregado almuerzos previamente");
        }
        else if(cod.equals(codigo)){
            listaPersonas.remove(posicion);
            JOptionPane.showMessageDialog(null, "El almuerzo fue entregado al usuario");
            posicion++;
        }
        else{
            JOptionPane.showMessageDialog(null, "los codigos no coinciden");
        }
    }

    public void sumarMenus() {
        
    }
    
    

    public void sumaTotal() {
        //tomamos los resultados de sumaroMenus y los sumamos
    }
}
