import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int rowNumber = 0;

        String[] strArr = new String[rows];

        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                sb.append(sc.nextInt());
            }
            strArr[i] = sb.toString();
        }

        int seatsNum = sc.nextInt();

        for (int i = 0; i < strArr.length; i++) {
            if (hasNearSeats(strArr[i], seatsNum)) {
                rowNumber = ++i;
                break;
            }
        }

        System.out.println(rowNumber);
    }

    private static boolean hasNearSeats(String str, int seatsNum) {
        int acc = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                acc++;
            } else {
                acc = 0;
            }
            if (acc == seatsNum) {
                return true;
            }
        }
        return false;
    }
}