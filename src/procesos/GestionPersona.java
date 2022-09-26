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
        nombre = String.valueOf(Menu.txtNombre.getText());
        codigo = String.valueOf(Menu.txtCodigo.getText());
        menu = String.valueOf(Menu.comboBoxMenu.getSelectedItem());
        dia = String.valueOf(Menu.comboBoxDia.getSelectedItem());
        //Crea un objeto de tipo persona y llena los atributos
        persona = new Persona(nombre, codigo, menu, dia);
        //Añade el objeto a la lista

        if (nombre.equals("") || codigo.equals("")) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios");
            return;
        }
        listaPersonas.add(persona);
        JOptionPane.showMessageDialog(null, "El " + menu + "\nComprado por: " + nombre + "\nCodigo: " + codigo + "\nPara el dia: " + dia);
        return;
    }

    public void retirar() {
        String cod = JOptionPane.showInputDialog("Ingrese su codigo");
        int posicion = buscar(cod);//Nos retorna la posicion
        if (posicion==-1){//si no se encontraron coincidencias nos muestra el mensaje
            JOptionPane.showMessageDialog(null, "Intente de nuevo!");
            return;
        }//Elimina el objeto ya que ha sido entregado
          listaPersonas.remove(posicion);
            JOptionPane.showMessageDialog(null, "El almuerzo fue entregado al usuario");
            return;
    }
    //cabaña villa saam barrio el atillo

    public int buscar(String cod) {
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        int index=0;//inicializamos el index
        int op = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el dia \n1.Lunes\n2.Martes\n3.Miercoles" + "\n4.Jueves\n.5.Viernes\n6.Sabado"));
        for (Persona persona : listaPersonas) {
            //Verifica que la lista no este vacia
            if (listaPersonas.isEmpty()){
                JOptionPane.showMessageDialog(null, "La lista esta vacia!");
                return -1;
            }//Validacion para siempre seleccionar una posicion existente del array de los dias
            if (op<0 || op>6) {
                JOptionPane.showMessageDialog(null, "Opcion invalida");
                return -1;
            }//compara si un elemento de la lista tiene el mismo codigo
            if(!cod.equals(persona.getCodigo())){
                JOptionPane.showMessageDialog(null, "No se ha encontrado el codigo!");
                return -1;
            }//Compara el dia de la pesona con los dias de la semana almacenados en el array
            if (!persona.getDia().equals(diasSemana[op - 1])) {
                JOptionPane.showMessageDialog(null, "El usuario no tiene turno para el dia "+diasSemana[op-1]);
                return -1;
            }
            return index = listaPersonas.indexOf(persona);//retorna la posicion en que esta en la lista
        }
        return -1;
    }

    public void sumarMenus() {

    }

    public void sumaTotal() {
        //tomamos los resultados de sumaroMenus y los sumamos
    }
}
