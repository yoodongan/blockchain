public class Block {
    private String transaction;
    private String transaction_recovery;      // 복구 데이터 저장해두기
    private int secret_code;      // 블록 생성 시 부여되는 랜덤 시크릿 코드
    private int password = 0b00100100;  // 모든 블록들이 공유하는 고정된 암호 값.
    private int encoded_data;           // 고정 상수 password와 XOR 연산을 통해 만들어진 enconded_data
    Block next;       // 블록의 다음 주소 가리킴

    public String getTransaction() {
        return transaction;
    }
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
    public String getTransaction_recovery() {
        return transaction_recovery;
    }
    public void setTransaction_recovery(String transaction_recovery) {
        this.transaction_recovery = transaction_recovery;
    }
    public int getSecret_code() {
        return secret_code;
    }
    public void setSecret_code(int secret_code) {
        this.secret_code = secret_code;
    }

    public int getEncoded_data() {
        return encoded_data;
    }
    public void setEncoded_data(byte encoded_data) {
        this.encoded_data = encoded_data;
    }
    public int getPassword() {
        return password;
    }

    Block(){      // 거래 장부 생성
        this.transaction = transaction;
        secret_code = (int)(Math.random()*200 + 1);  // 1~200 까지의 숫자 랜덤 부여.
        this.next = null;
        encoded_data = (secret_code ^ password);  // 거래 장부를 암호화한 데이터 (랜덤 생성된 secret_code 와 고정 상수 password를 XOR 연산해서 구함)

    }
}
