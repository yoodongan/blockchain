
public class Person {
    private String name;
    private int balance;  // 계좌 잔고


    Person(String name, int balance){    // 이름과 계좌 잔고로 생성자 만듦.
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void send_money(int money, Person a, Person b, Block data) {   // 송금 기능(a가 b한테 money 만큼 송금하는 기능)
        if(money > a.getBalance()) {
            System.out.println("계좌 잔고 부족! 송금 불가능합니다.");
        }
        else {
            a.setBalance(a.getBalance() - money);
            b.setBalance(b.getBalance() + money);
            data.setTransaction(a.getName() +"님이 " + b.getName() +"님께 " + money +"원 만큼 송금했습니다. ("
                    +a.getName()+"님의 잔고 : " + a.getBalance()+"원, "+b.getName()+"님의 잔고 : "+b.getBalance()+"원)" ); // 블록에 거래 내역 저장하기
            data.setTransaction_recovery(a.getName() +"님이 " + b.getName() +"님께 " + money +"원 만큼 송금했습니다. ("
                    +a.getName()+"님의 잔고 : " + a.getBalance()+"원, "+b.getName()+"님의 잔고 : "+b.getBalance()+"원)" ); // 복구데이터에 거래 내역 저장하기

        }
    }
    public void change_books(Block data, String manipulation) {
        String rawData = data.getTransaction(); // 기존 거래 내역 받아옴.
        data.setTransaction(manipulation); // 새로 거래 내역 조작
        if(rawData.equals(data.getTransaction( ))){
        }
        else {
            data.setSecret_code(201);   // 원본 데이터가 수정되면, secret_code를 바꿔버린다.
        }

    }


}
