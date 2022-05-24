
public class InspectorAndUpdater {

    public InspectorAndUpdater(){};

    public void inspect(Block b) { // 블록체인 기반 시점확인서비스, https://www.hellot.net/news/article.html?no=55466 [ Accessed : 2020.12.09 ]
        if ((b.getEncoded_data() ^ b.getPassword()) == b.getSecret_code()) {
            System.out.println("원본 장부와 일치합니다!");
        }
        else {
            System.out.println("부적절한 접근! 원본 장부가 손상돼서 다시 복구하겠습니다.");
            update(b);
            b.setTransaction(b.getTransaction_recovery());
        }
    }
    public void update(Block b) {
        int raw_data = (b.getEncoded_data() ^ b.getPassword());
        b.setSecret_code(raw_data);         // 원본 데이터로 복구하기
    }
}
