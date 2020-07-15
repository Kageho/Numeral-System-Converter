package converter;
 
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            // first range
            int sourceRadix = scanner.nextInt();
            if (scanner.hasNext()) {
                String number = scanner.next();
                if (scanner.hasNextInt()) {
 
//        double numberOne = scanner.nextDouble();
                    // здесь лежит либо дробь либо целое число
                    int targetRadix = scanner.nextInt();
                    String[] isFraction = number.split("[\\.]"); //разделяем на две части
 
//      Если массив имеет длину 1, то это просто целое число, иначе - вещественное
                    if (CheckAlmostEvrething.checkIt(sourceRadix, targetRadix, isFraction)) {
                        if (isFraction.length == 1 || sourceRadix == 1) {
                            System.out.println(NotFraction.getAnswer(sourceRadix, targetRadix, Integer.parseInt(number)));
                        } else if (targetRadix != 1) {
                            int numerator = Integer.parseInt(String.valueOf(isFraction[0]), sourceRadix);
                            System.out.println(numerator);
                            String StringNumerator = Integer.toString(numerator, targetRadix);
                            System.out.println(numerator);
                            double fromUnknownBaseToDec = FromUnknownBaseToDecimal.getValueInDecimal(isFraction[1], sourceRadix);
                            String halfAnswer = FromDecimalToTargetRadix.getPerformance(fromUnknownBaseToDec, targetRadix);
                            System.out.println(StringNumerator + halfAnswer);
                        } else {
                            System.out.println(NotFraction.getAnswer(sourceRadix, targetRadix, Integer.parseInt(isFraction[0])));
                        }
                    } else {
                        System.out.println("error");
                    }
 
                } else {
                    System.out.println("error");
                }
            } else {
                System.out.println("error");
            }
 
        } else {
            System.out.println("error");
        }
 
    }
}

package converter;
 
public class CheckAlmostEvrething {
    //    здесь буду проверять, полученные значения
    public static boolean checkIt(int sourceRadix, int targetRadix, String[] values) {
 
        if (sourceRadix > 36 || sourceRadix < 1 || targetRadix > 36 || targetRadix < 1) {
//            here i check all the bases
 
            return false;
 
        }
 
        for (int i = 0; i < values[0].length(); i++) { // проверяем целую часть
            int num = Character.forDigit(values[0].charAt(i), sourceRadix);
            if (num < 0 || num > sourceRadix - 1) {
                return false;
            }
        }
 
 
        if (values.length > 1) { // проверяем дробную
            for (int i = 0; i < values[1].length(); i++) {
                int num = Character.forDigit(values[1].charAt(i), sourceRadix);
                if (num < 0 || num > sourceRadix - 1) {
                    return false;
                }
            }
        }
 
        return true;
    }
}

package converter;
// класс для перевода из любой системы счилсения в десятичную
public class FromUnknownBaseToDecimal {
    public static double getValueInDecimal(String value, int sourceRadix) {
        double answer = 0;
 
        for (int i = 0; i < value.length(); i++) {
            char digit = value.charAt(i);
            answer += (Character.digit(digit, sourceRadix) / (double) Math.pow(sourceRadix, i + 1));
        }
        return answer;
    }
}

package converter;
// формирование дробной части в заданной системе счисления
public class FromDecimalToTargetRadix {
    public static String getPerformance (double value, int targetRadix){
        StringBuilder result = new StringBuilder(".");
        for(int i = 0; i < 5; i++){
//            все делается по формуле с hyperskill
            double buffer = value * targetRadix;
            int myDigit = (int) buffer;
            char myMan = Character.forDigit(myDigit, targetRadix);
            result.append(myMan);
            value = buffer - myDigit;
        }
        return result.toString();
    }
}

package converter;
 
// класс со статическими методами для перевода целых чисел
public class NotFraction {
    public static String getAnswer(int sourceRadix, int targetRadix, int number) {
        if (sourceRadix == 1) {
            int sum = 0;
            while (number > 0) {
                sum++;
                number /= 10;
            }
            number = sum;
        } else if (sourceRadix != 10) {
            number = Integer.parseInt(String.valueOf(number), sourceRadix);
        }
        if (targetRadix == 1) {
            String answer = "";
            while (number > 0) {
                number--;
                answer += "1";
            }
            return answer;
        } else {
            return Integer.toString(number, targetRadix);
        }
    }
}
