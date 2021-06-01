import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.charAt(0) == 'j' || input.charAt(0) == 'J') {
            System.out.println(true);
            return;
        }
        System.out.println(false);
    }
}