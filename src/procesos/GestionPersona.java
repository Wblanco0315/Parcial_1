package procesos;

import interfaz.Menu;
import static interfaz.Menu.txtCodigo;
import static interfaz.Menu.txtNombre;
import modelos.Persona;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;

public class GestionPersona {

    int menu1 = 0, menu2 = 0, menu3 = 0, mayor, menor, igual;
    int lunes1 = 0, martes1 = 0, miercoles1 = 0, jueves1 = 0, viernes1 = 0;
    int lunes2 = 0, martes2 = 0, miercoles2 = 0, jueves2 = 0, viernes2 = 0;
    int lunes3 = 0, martes3 = 0, miercoles3 = 0, jueves3 = 0, viernes3 = 0;
    String menuN;
    String diaN;

    String nombre;
    String codigo;
    int edad;
    String sexo;
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
        carrera = String.valueOf(Menu.comboBoxCarrera.getSelectedItem());
        menu = String.valueOf(Menu.comboBoxMenu.getSelectedItem());
        dia = String.valueOf(Menu.comboBoxDia.getSelectedItem());
        //Crea un objeto de tipo persona y llena los atributos
        persona = new Persona(nombre, codigo, edad, sexo, carrera, menu, dia);
        //Añade el objeto a la lista

        if (nombre.equals("") || codigo.equals("")) {
            JOptionPane.showMessageDialog(null, "Hay campos vacios");
            return;
        } else {
            txtNombre.setText("");
            txtCodigo.setText("");
        }

        try {
            Integer.parseInt(codigo);
        } catch (Exception errors) {
            JOptionPane.showMessageDialog(null, "El codigo debe ser solo numerico",
                    "Error " + errors.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Valida que el codigo sea posible agregarse a la lista si es un valor numerico
        if (almuerzoRep(codigo, dia)) {
            JOptionPane.showMessageDialog(null, "Ya has comprado almuerzo para el dia " + dia,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Valida que una persona no compre mas de 1 almuerzo el mismo dia 

        if ("Menu 1".equals(menu)) { //estos if anidados es para que vaya guardando la cantidad de cada menu
            menu1++;
            switch (dia) {
                case "Lunes" ->
                    lunes1++;
                case "Martes" ->
                    martes1++;
                case "Miercoles" ->
                    miercoles1++;
                case "Jueves" ->
                    jueves1++;
                case "Viernes" ->
                    viernes1++;
            }
        } else if ("Menu 2".equals(menu)) {
            menu2++;
            switch (dia) {
                case "Lunes" ->
                    lunes2++;
                case "Martes" ->
                    martes2++;
                case "Miercoles" ->
                    miercoles2++;
                case "Jueves" ->
                    jueves2++;
                case "Viernes" ->
                    viernes2++;
            }
        } else {
            menu3++;
            switch (dia) {
                case "Lunes" ->
                    lunes3++;
                case "Martes" ->
                    martes3++;
                case "Miercoles" ->
                    miercoles3++;
                case "Jueves" ->
                    jueves3++;
                case "Viernes" ->
                    viernes3++;
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
        }//Elimina el objeto ya que ha sido entregado
        listaPersonas.remove(posicion);
        JOptionPane.showMessageDialog(null, "El almuerzo fue entregado al usuario");
        return;
    }
    //cabaña villa saam barrio el atillo

    public int buscar(String cod) {
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
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

    public void masVendido() {
        /*al tomar los valores de cada menu, lo que hace es validar quien es el mayor, y asi
        guardarlo en una variable para mostrar en pantalla */
        if (menu1 > menu2 && menu1 > menu3) {
            mayor = menu1;
            menuN = "Menu 1";
        } else if (menu2 > menu1 && menu2 > menu3) {
            mayor = menu2;
            menuN = "Menu 2";
        } else {
            mayor = menu3;
            menuN = "Menu 3";
        }
        JOptionPane.showMessageDialog(null, "El Menu mas vendido fue el " + menuN + " con " + mayor + " compras");
    }

    public void menosVendido() {
        /*al tomar los valores de cada menu, lo que hace es validar quien es el menor, y asi
        guardarlo en una variable para mostrar en pantalla */
        if (menu1 < menu2 && menu1 < menu3) {
            menor = menu1;
            menuN = "Menu 1";
        } else if (menu2 < menu1 && menu2 < menu3) {
            menor = menu2;
            menuN = "Menu 2";
        } else {
            menor = menu3;
            menuN = "Menu 3";
        }
        JOptionPane.showMessageDialog(null, "El Menu menos vendido fue el " + menuN + " con " + menor + " compras");
    }

    public void ventas() {
        //tomamos los valores guardados del agregar y lo mostramos en pantalla
        JOptionPane.showMessageDialog(null, "Menu 1: " + menu1 + "\nMenu 2: " + menu2 + "\nMenu3: " + menu3);
    }

    public void total() {
        //sumamos los menus
        int total = menu1 + menu2 + menu3;
        JOptionPane.showMessageDialog(null, "El total vendido fue de " + total + " menus");
    }

    public void promedioSemana() {
        double promedio;
        int n = Integer.parseInt(JOptionPane.showInputDialog("""
                Ingrese el menu que desea conocer el promedio
                1. Menu 1
                2. Menu 2
                3. Menu 3"""));
        //realizamos el calculo de las ventas de los menus entre el numero de dias de la semana
        switch (n) {
            case 1 -> {
                promedio = Double.valueOf(menu1) / 5;
                JOptionPane.showMessageDialog(null, "El promedio de ventas de la semana del Menu 1 es " + promedio);
            }
            case 2 -> {
                promedio = Double.valueOf(menu2) / 5;
                JOptionPane.showMessageDialog(null, "El promedio de ventas de la semana del Menu 2 es " + promedio);
            }
            case 3 -> {
                promedio = Double.valueOf(menu3) / 5;
                JOptionPane.showMessageDialog(null, "El promedio de ventas de la semana del Menu 3 es " + promedio);
            }

            default ->
                JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
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

    /* Hacemos la suma de los dias en cada menu 
    despues hacemos una comparacion entre los totales de la suma 
    y mostramos el mayor y el menor*/
    public void mayorDia_menorDia() {
        int lunesT = lunes1 + lunes2 + lunes3;
        int martesT = martes1 + martes2 + martes3;
        int miercolesT = miercoles1 + miercoles2 + miercoles3;
        int juevesT = jueves1 + jueves2 + jueves3;
        int viernesT = viernes1 + viernes2 + viernes3;

        if (lunesT > martesT && lunesT > miercolesT && lunesT > juevesT && lunesT > viernesT) {
            mayor = lunesT;
            diaN = "Lunes";
        } else if (martesT > miercolesT && martesT > juevesT && martesT > viernesT) {
            mayor = martesT;
            diaN = "Martes";
        } else if (miercolesT > juevesT && miercolesT > viernesT) {
            mayor = miercolesT;
            diaN = "Miercoles";
        } else if (juevesT > viernesT) {
            mayor = juevesT;
            diaN = "Jueves";
        } else {
            mayor = viernesT;
            diaN = "Viernes";
        }
        JOptionPane.showMessageDialog(null, "el dia que mas se vendio fue el " + diaN + " con " + mayor + " menus comprados");
        //sacamos el menor

        if (lunesT < martesT && lunesT < miercolesT && lunesT < juevesT && lunesT < viernesT) {
            menor = lunesT;
            diaN = "Lunes";
        } else if (martesT < miercolesT && martesT < juevesT && martesT < viernesT) {
            menor = martesT;
            diaN = "Martes";
        } else if (miercolesT < juevesT && miercolesT < viernesT) {
            menor = miercolesT;
            diaN = "Miercoles";
        } else if (juevesT < viernesT) {
            menor = juevesT;
            diaN = "Jueves";
        } else {
            menor = viernesT;
            diaN = "Viernes";
        }
        JOptionPane.showMessageDialog(null, "el dia que menos se vendio fue el " + diaN + " con " + menor + " menus comprados");
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia mas vendido del menu seleccionado */
    public void diaMasVendidoDelMenu() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Escoja un menu"
                + "\n1. Menu 1"
                + "\n2. Menu 2"
                + "\n3. Menu 3"));

        switch (op) {
            case 1 -> {
                if (lunes1 == 0 && martes2 == 0 && miercoles1 == 0 && jueves1 == 0 && viernes1 == 0) {
                    diaN = "Lunes, Martes, Miercoles, Jueves, Viernes";
                    JOptionPane.showMessageDialog(null, "son iguales");
                    break;
                }

                if (lunes1 > martes1 && lunes1 > miercoles1 && lunes1 > jueves1 && lunes1 > viernes1) {
                    mayor = lunes1;
                    diaN = "Lunes";
                } else if (martes1 > miercoles1 && martes1 > jueves1 && martes1 > viernes1) {
                    mayor = martes1;
                    diaN = "Martes";
                } else if (miercoles1 > jueves1 && miercoles1 > viernes1) {
                    mayor = miercoles1;
                    diaN = "Miercoles";
                } else if (jueves1 > viernes1) {
                    mayor = jueves1;
                    diaN = "Jueves";
                } else {
                    mayor = viernes1;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido del menu 1 fue el " + diaN + " con " + mayor + " menus vendidos");

            }
            case 2 -> {
                if (lunes2 > martes2 && lunes2 > miercoles2 && lunes2 > jueves2 && lunes2 > viernes2) {
                    mayor = lunes2;
                    diaN = "Lunes";
                } else if (martes2 > miercoles2 && martes2 > jueves2 && martes2 > viernes2) {
                    mayor = martes2;
                    diaN = "Martes";
                } else if (miercoles2 > jueves2 && miercoles2 > viernes2) {
                    mayor = miercoles2;
                    diaN = "Miercoles";
                } else if (jueves2 > viernes2) {
                    mayor = jueves2;
                    diaN = "Jueves";
                } else {
                    mayor = viernes2;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido del menu 2 fue el " + diaN + " con " + mayor + " menus vendidos");
            }
            case 3 -> {
                if (lunes3 > martes3 && lunes3 > miercoles3 && lunes3 > jueves3 && lunes3 > viernes3) {
                    mayor = lunes3;
                    diaN = "Lunes";
                } else if (martes3 > miercoles3 && martes3 > jueves3 && martes3 > viernes3) {
                    mayor = martes3;
                    diaN = "Martes";
                } else if (miercoles3 > jueves3 && miercoles3 > viernes3) {
                    mayor = miercoles3;
                    diaN = "Miercoles";
                } else if (jueves3 > viernes3) {
                    mayor = jueves3;
                    diaN = "Jueves";
                } else {
                    mayor = viernes3;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido del menu 3 fue el " + diaN + " con " + mayor + " menus vendidos");
            }
            default ->
                JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
        }
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia menos vendido del menu seleccionado */
    public void diaMenosVendidoDelMenu() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Escoja un menu"
                + "\n1. Menu 1"
                + "\n2. Menu 2"
                + "\n3. Menu 3"));

        switch (op) {
            case 1 -> {
                //validamos que no esté vacio
                if (lunes1 == 0 && martes2 == 0 && miercoles1 == 0 && jueves1 == 0 && viernes1 == 0) {
                    diaN = "Lunes, Martes, Miercoles, Jueves, Viernes";
                    JOptionPane.showMessageDialog(null, "Los dias " + diaN + " estan vacios");
                    break;
                }

                if (lunes1 < martes1 && lunes1 < miercoles1 && lunes1 < jueves1 && lunes1 < viernes1) {
                    menor = lunes1;
                    diaN = "Lunes";
                } else if (martes1 < miercoles1 && martes1 < jueves1 && martes1 < viernes1) {
                    menor = martes1;
                    diaN = "Martes";
                } else if (miercoles1 < jueves1 && miercoles1 < viernes1) {
                    menor = miercoles1;
                    diaN = "Miercoles";
                } else if (jueves1 < viernes1) {
                    menor = jueves1;
                    diaN = "Jueves";
                } else {
                    menor = viernes1;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido del menu 1 fue el " + diaN + " con " + menor + " menus vendidos");

            }
            case 2 -> {
                //validamos que no esté vacio
                if (lunes2 == 0 && martes2 == 0 && miercoles2 == 0 && jueves2 == 0 && viernes2 == 0) {
                    diaN = "Lunes, Martes, Miercoles, Jueves, Viernes";
                    JOptionPane.showMessageDialog(null, "Los dias " + diaN + " estan vacios");
                    break;
                }

                if (lunes2 < martes2 && lunes2 < miercoles2 && lunes2 < jueves2 && lunes2 < viernes2) {
                    menor = lunes2;
                    diaN = "Lunes";
                } else if (martes2 < miercoles2 && martes2 < jueves2 && martes2 < viernes2) {
                    menor = martes2;
                    diaN = "Martes";
                } else if (miercoles2 < jueves2 && miercoles2 < viernes2) {
                    menor = miercoles2;
                    diaN = "Miercoles";
                } else if (jueves2 < viernes2) {
                    menor = jueves2;
                    diaN = "Jueves";
                } else {
                    menor = viernes2;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido del menu 2 fue el " + diaN + " con " + menor + " menus vendidos");

            }
            case 3 -> {
                //validamos que no esté vacio
                if (lunes3 == 0 && martes3 == 0 && miercoles3 == 0 && jueves3 == 0 && viernes3 == 0) {
                    diaN = "Lunes, Martes, Miercoles, Jueves, Viernes";
                    JOptionPane.showMessageDialog(null, "Los dias " + diaN + " estan vacios");
                    break;
                }

                if (lunes3 < martes3 && lunes3 < miercoles3 && lunes3 < jueves3 && lunes3 < viernes3) {
                    menor = lunes3;
                    diaN = "Lunes";
                } else if (martes3 < miercoles3 && martes3 < jueves3 && martes3 < viernes3) {
                    menor = martes3;
                    diaN = "Martes";
                } else if (miercoles3 < jueves3 && miercoles3 < viernes3) {
                    menor = miercoles3;
                    diaN = "Miercoles";
                } else if (jueves3 < viernes3) {
                    menor = jueves3;
                    diaN = "Jueves";
                } else {
                    menor = viernes3;
                    diaN = "Viernes";
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido del menu 3 fue el " + diaN + " con " + menor + " menus vendidos");

            }
            default ->
                JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
        }

    }
    
    public void imprimirAlfabetico(){
        
    }
    

    public void MayorYMenorEdad() {
        int men=0;
        for (int i = 0; i < listaPersonas.size(); i++) {
            int edad = listaPersonas.get(i).getEdad();
            if (edad<18) {
                men++;
            }
        }
        JOptionPane.showMessageDialog(null, "Hay "+men+" Menores de edad");
    }
}
