package procesos;

import interfaz.Menu;
import static interfaz.Menu.txtCodigo;
import static interfaz.Menu.txtNombre;
import modelos.Persona;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class GestionPersona {
    private int x = 3;
    private int y = 5;
    int mayor, menor;
    String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
    String nombreMenu[] = {"Menu 1", "Menu 2", "Menu 3"};
    int totalSemanal[] = {0, 0, 0};
    int totalDias[][];
    String nombre;
    String codigo;
    int edad;
    String sexo;
    boolean estado;
    String carrera;
    String menu;
    String dia;
    //Menu interfaz;
    Persona persona;
    ArrayList<Persona> listaPersonas;

    public GestionPersona() {
        //Constructor
        persona = null;
        this.listaPersonas = new ArrayList<>();
        totalDias = new int[3][5];
    }

    public void agregar() {
        //Guarda los datos de los inputs en variables
        nombre = String.valueOf(Menu.txtNombre.getText());
        codigo = String.valueOf(Menu.txtCodigo.getText());
        //Valida si lo ingresado en edad es un numero
        try {
            edad = Integer.parseInt(Menu.txtEdad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un numero");
            return;
        }
        sexo = String.valueOf(Menu.comboBoxSexo.getSelectedItem());
        estado = true;
        carrera = String.valueOf(Menu.comboBoxCarrera.getSelectedItem());
        menu = String.valueOf(Menu.comboBoxMenu.getSelectedItem());
        dia = String.valueOf(Menu.comboBoxDia.getSelectedItem());
        //Crea un objeto de tipo persona y llena los atributos
        persona = new Persona(nombre, codigo, edad, sexo, estado, carrera, menu, dia);
        //Añade el objeto a la lista

        if (nombre.equals("") || codigo.equals("")) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios");
            return;
        } else {
            txtNombre.setText("");
            txtCodigo.setText("");
        }
        //Valida que el codigo sea posible agregarse a la lista si es un valor numerico
        try {
            Integer.parseInt(codigo);
        } catch (Exception errors) {
            JOptionPane.showMessageDialog(null, "El codigo debe ser solo numerico",
                    "Error " + errors.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Valida que una persona no compre mas de 1 almuerzo el mismo dia 
        if (almuerzoRep(codigo, dia)) {
            JOptionPane.showMessageDialog(null, "Ya has comprado almuerzo para el dia " + dia,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < nombreMenu.length; i++) {
            if (nombreMenu[i].equals(menu)) {
                totalSemanal[i] = totalSemanal[i] + 1;
            }
            for (int j = 0; j < diasSemana.length; j++) {
                if (diasSemana.equals(dia)) {
                    totalDias[i][j] = totalDias[i][j] + 1;
                }
            }
        }

        listaPersonas.add(persona);
        JOptionPane.showMessageDialog(null, "El " + menu + "\nComprado por: " + nombre + "\nCodigo: " + codigo + "\nPara el dia: " + dia);
        return;

    }

    public void retirar() {
        String cod = JOptionPane.showInputDialog("Ingrese su codigo");
        int posicion = buscar(cod);//Nos retorna la posicion
        if (posicion == -1) {//si no se encontraron coincidencias nos muestra el mensaje
            JOptionPane.showMessageDialog(null, "Intente de nuevo!");
            return;
        }//Obtenemos el turno que se busco
        Persona temp = listaPersonas.get(posicion);
        //Verifica si el almuerzo ya fue entregado
        if (!temp.isEstado()) {
            JOptionPane.showMessageDialog(null, "El almuerzo ya fue entregado previamente");
        }
        //Marca el estado como entregado
        temp.setEstado(false);
        JOptionPane.showMessageDialog(null, "El almuerzo fue entregado al usuario");
        return;
    }
    //cabaña villa saam barrio el atillo

    public int buscar(String cod) {

        int index = 0;//inicializamos el index
        int op = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el dia \n1.Lunes\n2.Martes\n3.Miercoles" + "\n4.Jueves\n.5.Viernes\n6.Sabado"));
        for (Persona persona : listaPersonas) {
            //Verifica que la lista no este vacia
            if (listaPersonas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La lista esta vacia!");
                return -1;
            }//Validacion para siempre seleccionar una posicion existente del array de los dias
            if (op < 0 || op > 6) {
                JOptionPane.showMessageDialog(null, "Opcion invalida");
                return -1;
            }//compara si un elemento de la lista tiene el mismo codigo
            if (!cod.equals(persona.getCodigo())) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el codigo!");
                return -1;
            }//Compara el dia de la pesona con los dias de la semana almacenados en el array
            if (!persona.getDia().equals(diasSemana[op - 1])) {
                JOptionPane.showMessageDialog(null, "El usuario no tiene turno para el dia " + diasSemana[op - 1]);
                return -1;
            }
            return index = listaPersonas.indexOf(persona);//retorna la posicion en que esta en la lista
        }
        return -1;
    }

    private int validacionMenu() {
        int total = totalSemanal[0] + totalSemanal[1] + totalSemanal[2];
        int index = 0;
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "No se han vendido menus");
            return -1;
        }
        if (totalSemanal[0] == totalSemanal[1] && totalSemanal[1] == totalSemanal[2]) {
            JOptionPane.showMessageDialog(null, "Todas las ventas son iguales");
            return -1;
        }
        return 1;
    }

    public void masVendido() {
        //Se compara valor por valor y el numero mayor se guarda en una variable junto al indice para indicar que menu fue
        mayor = 0;
        int pos = 0;
        int a = 0;
        int b = 0;
        int resp = validacionMenu();
        if (resp != -1) {
            if (totalSemanal[0] != totalSemanal[1] && totalSemanal[1] != totalSemanal[2]) {
                for (int i = 0; i < totalSemanal.length; i++) {
                    if (totalSemanal[i] > mayor) {
                        mayor = totalSemanal[i];
                        pos = i;
                    }
                }
                JOptionPane.showMessageDialog(null, "El Menu mas vendido fue el " + nombreMenu[pos] + " con " + mayor + " compras");
            } else {
                for (int i = 0; i < totalSemanal.length; i++) {
                    for (int j = i + 1; j < totalSemanal.length; j++) {
                        if (totalSemanal[i] == totalSemanal[j]) {
                            a = i;
                            b = j;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Los mas vendidos son " + nombreMenu[a] + " y " + nombreMenu[b] + "\ncon " + totalSemanal[a] + " ventas cada uno");
            }
        }

    }

    public void menosVendido() {
        menor = totalSemanal[0];
        int cont = 0;
        int pos = 0;
        int a = 0;
        int b = 0;
        int resp = validacionMenu();
        if (resp != -1) {
            if (totalSemanal[0] != totalSemanal[1] && totalSemanal[1] != totalSemanal[2]) {
                for (int i = 0; i < totalSemanal.length; i++) {
                    if (totalSemanal[i] < menor) {
                        menor = totalSemanal[i];
                        pos = i;
                    }
                }
                JOptionPane.showMessageDialog(null, "El Menu menos vendido fue el " + nombreMenu[pos] + " con " + menor + " compras");
            } else {
                for (int i = 0; i < totalSemanal.length; i++) {
                    for (int j = i + 1; j < totalSemanal.length; j++) {
                        if (totalSemanal[i] == totalSemanal[j]) {
                            a = i;
                            b = j;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Los menos vendidos son " + nombreMenu[a] + " y " + nombreMenu[b] + "\ncon " + totalSemanal[a] + " ventas cada uno");
            }
        }

    }

    public void ventas() {
        //tomamos los valores guardados del agregar y lo mostramos en pantalla
        JOptionPane.showMessageDialog(null, "Menu 1: " + totalSemanal[0] + "\nMenu 2: " + totalSemanal[1] + "\nMenu3: " + totalSemanal[2]);
    }

    public void total() {
        //sumamos los menus
        int total = totalSemanal[0] + totalSemanal[1] + totalSemanal[2];
        JOptionPane.showMessageDialog(null, "El total vendido fue de " + total + " menus");

    }

    public void promedioSemana() {
        double promedio;
        for (int i = 0; i < totalSemanal.length; i++) {
            promedio = totalSemanal[i] / diasSemana.length;
            JOptionPane.showMessageDialog(null, "El promedio de ventas de la semana del " + nombreMenu[i] + " es " + Math.ceil(promedio));
        }
    }

    private boolean almuerzoRep(String codigo, String dia) {
        for (Persona persona : listaPersonas) {
            if (persona != null) {
                if (persona.getCodigo().equals(codigo) && persona.getDia().equals(dia)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia mas vendido del menu seleccionado */
    public void diaMasVendidoDelMenu() {
       
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia menos vendido del menu seleccionado */
    public void diaMenosVendidoDelMenu() {

    }

    public void imprimirAlfabetico() {
        if (listaPersonas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
            return;
        }
        Collections.sort(listaPersonas, (Persona p1, Persona p2) -> p1.getNombre().compareTo(p2.getNombre()));
        for (Persona persona : listaPersonas) {
            JOptionPane.showMessageDialog(null, persona.toString());
        }
    }

    public void carrerras() {
        if (listaPersonas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
            return;
        }
        String[] carreras = {"Sistemas", "Civil", "Mecanica", "Ambiental"};
        int[] cont = {0, 0, 0, 0};
        for (int i = 0; i < listaPersonas.size(); i++) {
            String carrera = listaPersonas.get(i).getCarrera();
            for (int j = 0; j < carreras.length; j++) {
                if (carrera.equals(carreras[j])) {
                    cont[j] = cont[j] + 1;
                }
            }
        }
        for (int i = 0; i < carreras.length; i++) {
            JOptionPane.showMessageDialog(null, "Total estudiantes de " + carreras[i] + ": " + cont[i]);
        }
    }

    public void MenorEdad() {
        if (listaPersonas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
            return;
        }
        int men = 0;
        int may = 0;
        for (int i = 0; i < listaPersonas.size(); i++) {
            int edad = listaPersonas.get(i).getEdad();
            if (edad < 18) {
                men++;
            }
            if (edad >= 18) {
                may++;
            }
        }
        JOptionPane.showMessageDialog(null, "Hay " + men + " Menores de edad \n Hay " + may + " Mayores de edad");
    }
}
