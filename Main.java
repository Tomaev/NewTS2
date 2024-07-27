import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static StringBuilder value1;
    static StringBuilder value2;
    static String valueInt = " ";
    static int valueDefault = 0;
    static char operationExeption = ' ';
    static ArrayList<String> valueString1 = new ArrayList<>();
    static ArrayList<String> valueString2 = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();
        calc(sc);
    }
    static void calc(String sc) throws IOException {

        char operation = ' ';
        for(int i = 0;i < sc.length();i++){
            char val = sc.charAt(i);


            if ((val == '+' || val == '-' || val == '*' || val == '/') && val != operationExeption){
                operation = val;
            }else{
                char valOptional = sc.charAt(i);

                if(value1 == null) {
//                    Код для первого значения
                    if(val == '"') {
                        for (int count = i + 1; count < sc.length(); count++) {
                            valOptional = sc.charAt(count);
                            if (valOptional == '-' || valOptional == '+' || valOptional == '*' || valOptional == '/') {
                                operationExeption = valOptional;
                            }
                            if (valOptional == '"') {
                                break;
                            }
                            valueString1.add(String.valueOf(valOptional));
                        }

                        for (int j = 0; j < valueString1.size(); j++) {
                            if (j == 0) {
                                value1 = new StringBuilder(valueString1.get(j));
                            } else {
                                value1.append(valueString1.get(j));
                            }
                        }
                    }else{
                        throw new IOException();
                    }
                }else if(operation != ' ' && value2 == null && operationExeption != val){
//                    Код для второго значения
                    if(val == '"' ) {
                        for (int count = i + 1; count < sc.length(); count++) {
                            valOptional = sc.charAt(count);

                            if (valOptional == '-' || valOptional == '+' || valOptional == '*' || valOptional == '/') {
                                operationExeption = valOptional;
                            }

                            if (valOptional == '"') {
                                break;
                            }
                            valueString2.add(String.valueOf(valOptional));
                        }
                        for (int a = 0; a < valueString2.size(); a++) {
                            if (a == 0) {
                                value2 = new StringBuilder(valueString2.get(a));
                            } else {
                                value2.append(valueString2.get(a));
                            }
                        }
                    }else if(valOptional == '1' || valOptional == '2' || valOptional == '3' || valOptional == '4' || valOptional == '5' || valOptional == '6' || valOptional == '7' || valOptional == '8' || valOptional == '9' || valOptional == '0'){
                        for (int a = i; a < sc.length(); a++) {
                            if(valueInt.equals(" ")){
                                valueInt = String.valueOf(sc.charAt(a));
                            }else {
                                valueInt += String.valueOf(sc.charAt(a));
                            }
                        }
                        value2 = new StringBuilder(valueInt);
                        valueDefault = Integer.parseInt(valueInt);
                    }else{
                        throw new IOException();
                    }
                }
            }


        }
        if((valueDefault == 0 && valueInt.equals("0")) || (valueDefault > 10)){
            //Тут будет условия про диапазон цифр второго значения от 1 до 10
            throw new IOException();
        }
        if((value1.toString().length() > 10) || (value2.toString().length() > 10)){
            //Тут будет условия про диапазон цифр второго значения от 1 до 10
            throw new IOException();
        }

        switch(operation) {
            case '+':
                if(valueInt.equals(" ")){
                    value1.append(value2);
                    System.out.println(value1);
                }else{
                    System.out.println("Строка и Цифра не могут быть сложены");
                    throw new IOException();
                }

                break;
            case '*':
                if(valueDefault != 0) {
                    String stringDefault;
                    stringDefault = value1.toString();
                    for (int count = 1; count < valueDefault; count++) {
                        value1.append(stringDefault);
                    }
                    if(value1.toString().length() >40){
                        stringDefault = String.valueOf(value1);
                        value1 = new StringBuilder(value1.charAt(0));

                        for (int count = 0; count < 40; count++) {
                            value1.append(stringDefault.charAt(count));
                        }
                        value1.append("...");
                    }
                    System.out.println(value1);
                }else{
                    System.out.println("Вторым значение должна быть цифра");
                    throw new IOException();
                }
                break;
            case '/':
                if(valueDefault != 0){
                    String stringDefault = String.valueOf(value1);
                    value1 = new StringBuilder(value1.charAt(0));

                    for(int count = 0;count < stringDefault.length()/valueDefault; count++){
                        value1.append(stringDefault.charAt(count));
                    }
                    System.out.println(value1);

                }else{
                    throw new IOException();
                }
                break;
            case '-':
                if(valueInt.equals(" ")){
                        String[] stringDefault = value1.toString().split(" ");
                        value1 = new StringBuilder(" ");
                        for (int i = 0; i < stringDefault.length; i++) {
                            if (stringDefault[i].equals(value2.toString())) {

                            } else {
                                if (value1.toString().equals(" ")) {
                                    value1 = new StringBuilder(stringDefault[i]);
                                } else {
                                    value1.append(" ");
                                    value1.append(stringDefault[i]);
                                }
                            }
                        }
                        System.out.println(value1);
                }else{
                    System.out.println("Из строки нельщя вычесть цифру");
                    throw new IOException();
                }

                break;
            default:
                System.out.println("произошла ошибка попробуйте еще раз");
                break;

        }
//            System.out.println(value1);
//        System.out.println(value2);
//        System.out.println(operation);

    }
}