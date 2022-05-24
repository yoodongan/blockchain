import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("블록체인 거래장부 만들기");

        BlockChain bc = new BlockChain();
        InspectorAndUpdater ia = new InspectorAndUpdater();

        Person person = null;
        Person person1 = null;
        Block b = null;
        int menu = 0;

        while(true) {
            showMenu();
            menu = sc.nextInt();

            switch(menu) {
                case 1 :
                    System.out.println("입금주, 예금주 정보 입력");
                    System.out.print("입금주 : ");
                    String name_send = sc.next();
                    System.out.print("계좌잔고 : ");
                    int balance_send = sc.nextInt();

                    System.out.println();
                    person = new Person(name_send, balance_send);
                    System.out.print("예금주(수신) : ");
                    String name_get = sc.next();
                    System.out.print("계좌잔고 : ");
                    int balance_get = sc.nextInt();
                    person1 = new Person(name_get, balance_get);
                    break;

                case 2 :
                    System.out.println("얼마 보내시겠습니까?");
                    System.out.print("송금 금액 입력 : ");
                    int send_money = sc.nextInt();
                    b = new Block();   // 블록 생성
                    person.send_money(send_money, person, person1, b);
                    bc.connectBlock(b);
                    break;

                case 3 :
                    System.out.println("Inspector AND Updater");
                    if (b == null) {
                        System.out.println("2번을 눌러 송금 금액을 입력하세요!");
                    }
                    else {

                        ia.inspect(b);      // 검사하기.
                    }
                    break;

                case 4 :
                    System.out.println("모든 거래장부 열람하기");
                    bc.openBooks();
                    break;
                case 5 :
                    System.out.println("장부 수정");
                    System.out.print("수정할 내역을 입력하세요 > ");
                    sc.nextLine();
                    String correction = sc.nextLine();
                    person.change_books(b, correction);
                    System.out.println("누군가 거래장부에 접근했습니다!! (3번을 눌러 검사를 반드시 진행하세요)");
                    break;
                case 6 :
                    System.exit(0);
                    sc.close();
            }
        }


    }
    public static void showMenu() {
        System.out.println("============================");
        System.out.println("====     블록체인 장부     ====");
        System.out.println("====                    ====");
        System.out.println("====     1. 거래자 정보   ====");
        System.out.println("====     2. 송금 금액     ====");
        System.out.println("====     3. 장부 검사     ====");
        System.out.println("====     4. 장부 열람     ====");
        System.out.println("====     5. 장부 수정     ====");
        System.out.println("====     6. 종료         ====");
        System.out.println("============================");
        System.out.println("menu > ");
    }



}

