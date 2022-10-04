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
    int M[] = {0,0,0,0,0};
    int dias[][];
    String menuN;
    String diaN;

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
        dias = new int[2][4];
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
        
        for(int i=0; i<dias.length; i++){
            for(int j=0; j<dias.length; j++){
                dias[i][j]=0;
            }
        }

        if ("Menu 1".equals(menu)) { //estos if anidados es para que vaya guardando la cantidad de cada menu
            menu1++;
            for(int i=0; i<dias.length; i++){
                    if("Lunes".equals(dia)){
                        dias[0][0]++;
                    }else if("Martes".equals(dia)){
                        dias[0][1]++;
                    }else if("Miercoles".equals(dia)){
                        dias[0][2]++;
                    }else if("Jueves".equals(dia)){
                        dias[0][3]++;
                    }else{
                        dias[0][4]++;
                    }
            }
        } else if ("Menu 2".equals(menu)) {
            menu2++;
            for(int i=0; i<dias.length; i++){
                    if("Lunes".equals(dia)){
                        dias[1][0]++;
                    }else if("Martes".equals(dia)){
                        dias[1][1]++;
                    }else if("Miercoles".equals(dia)){
                        dias[1][2]++;
                    }else if("Jueves".equals(dia)){
                        dias[1][3]++;
                    }else{
                        dias[1][4]++;
                    }
            }
        } else {
            menu3++;
            for(int i=0; i<dias.length; i++){
                    if("Lunes".equals(dia)){
                        dias[2][0]++;
                    }else if("Martes".equals(dia)){
                        dias[2][1]++;
                    }else if("Miercoles".equals(dia)){
                        dias[2][2]++;
                    }else if("Jueves".equals(dia)){
                        dias[2][3]++;
                    }else{
                        dias[2][4]++;
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
        if(menu1!=menu2 && menu1!=menu3 && menu2!=menu3){
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
        }else{
            if(menu1==menu2 && menu1==menu3 && menu2==menu3){
                JOptionPane.showMessageDialog(null, "los tres menus tienen el mismo valor");
            }else if(menu1==menu2 || menu1==menu3 || menu2==menu3){
                JOptionPane.showMessageDialog(null, "hay dos menus con el mismo valor");
            }
        }
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
        String x="",y="";
        int X,Y;
        for (int i = 0; i < dias.length; i++) {
                M[0]=M[0]+dias[i][0];
                M[1]=M[1]+dias[i][1];
                M[2]=M[2]+dias[i][2];
                M[3]=M[3]+dias[i][3];
                M[4]=M[4]+dias[i][4];
        }
        for(int i=0; i<M.length; i++){
            if(M[i]<M[i+1]){
                menor=M[i];
                X=i;
                if(X==0){
                    x="Lunes";
                }else if(X==1){
                    x="Martes";
                }else if(X==2){
                    x="Miercoles";
                }else if(X==3){
                    x="Jueves";
                }else{
                    x="Viernes";
                }
            }
            if(M[i]>M[i+1]){
                mayor=M[i];
                Y=i;
                if(Y==0){
                    y="Lunes";
                }else if(Y==1){
                    y="Martes";
                }else if(Y==2){
                    y="Miercoles";
                }else if(Y==3){
                    y="Jueves";
                }else{
                    y="Viernes";
                }
            }
        }
        JOptionPane.showMessageDialog(null, "El dia mas vendido fue el "+y+" con "+mayor+" menus vendidos\n"
                                        + "El dia menos vendido fue el "+x+" con "+menor+" menus vendidos");
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia mas vendido del menu seleccionado */
    public void diaMasVendidoDelMenu() {
        int x;
        int op = Integer.parseInt(JOptionPane.showInputDialog("Escoja un menu"
                + "\n1. Menu 1"
                + "\n2. Menu 2"
                + "\n3. Menu 3"));

        switch (op) {
            case 1 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[0][i]>dias[0][i+1]){
                        mayor = dias[0][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido fue el "+diaN+" con "+mayor+" menus vendidos");
            }
            case 2 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[1][i]>dias[1][i+1]){
                        mayor = dias[1][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido fue el "+diaN+" con "+mayor+" menus vendidos");
            }
            case 3 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[2][i]>dias[2][i+1]){
                        mayor = dias[2][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia mas vendido fue el "+diaN+" con "+mayor+" menus vendidos");
            }
            default ->
                JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
        }
    }

    /* Hacemos una comparacion entre las filas (dias) de cada menu
    y mostramos el dia menos vendido del menu seleccionado */
    public void diaMenosVendidoDelMenu() {
        int x;
        int op = Integer.parseInt(JOptionPane.showInputDialog("Escoja un menu"
                + "\n1. Menu 1"
                + "\n2. Menu 2"
                + "\n3. Menu 3"));

        switch (op) {
            case 1 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[0][i]<dias[0][i+1]){
                        menor = dias[0][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido fue el "+diaN+" con "+menor+" menus vendidos");
            }
            case 2 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[1][i]<dias[1][i+1]){
                        menor = dias[1][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido fue el "+diaN+" con "+menor+" menus vendidos");
            }
            case 3 -> {
                for(int i=0; i<dias.length; i++){
                    if(dias[2][i]<dias[2][i+1]){
                        menor = dias[2][i];
                        x=i;
                        if(x==0){
                            diaN="Lunes";
                        }else if(x==1){
                            diaN="Martes";
                        }else if(x==2){
                            diaN="Miercoles";
                        }else if(x==3){
                            diaN="Jueves";
                        }else{
                            diaN="Viernes";
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "El dia menos vendido fue el "+diaN+" con "+menor+" menus vendidos");              
            }
            default ->
                JOptionPane.showMessageDialog(null, "Ha ingresado una opción incorrecta");
        }

    }

    public void imprimirAlfabetico() {
        Collections.sort(listaPersonas, (Persona p1, Persona p2) -> p1.getNombre().compareTo(p2.getNombre()));
        for (Persona persona : listaPersonas) {
            JOptionPane.showMessageDialog(null, persona.toString());
        }
    }

    public void carrerras() {
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
    }

    public void MenorEdad() {
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
