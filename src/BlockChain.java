
public class BlockChain { // 분산 컴퓨팅 기술(분산 저장), http://www.100ssd.co.kr/news/articleView.html?idxno=78314, [ Accessed : 2021.06.25 ]
    Block block;    // 구성 관계로 블록체인 클래스에 필요한 블록을 받아온다.
    private Block head;
    private Block tail;
    private int size = 0;       // 체인의 개수

    public void connectBlockFirst(Block a) {      // 맨 앞에 블록을 연결한다.
        a.next = head;
        head = a;
        size++;
        if(head.next ==null	) {
            tail = head;
        }
    }

    public void connectBlock(Block a) {    // 블록을 뒤에서부터 하나씩 차례로 연결한다.
        if(size ==0	) {
            connectBlockFirst(a);      // 연결된 것이 아무것도 없으면, 첫 번째로 들어온 블록 셋팅한다.
        }
        else {
            tail.next = a;
            tail = a;
            size++;
        }
    }
    public void openBooks() {   // 블록체인에서 누구나 장부를 열람할 수 있도록 한다.
        if (this.size > 0) {
            Block temp = head;
            for(int i =0; i<this.size; i++) {
                System.out.print(temp.getTransaction() +"\n");
                temp = temp.next;
            }
        }
        else {
            System.out.println("장부에 값이 없습니다!");
        }
    }
}
