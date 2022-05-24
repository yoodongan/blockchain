# Blockchain
### 블록체인의 특징은 다음과 같습니다.
- ‘블록’이라고 하는 소규모 데이터들이 체인으로 연결되어 있습니다. 
- 모두가 데이터를 공유하고 확인할 수 있기 때문에 위조 및 변조가 힘듭니다.
(만약 데이터 위조 및 변조가 발생한다면, 모두가 공유하는 원본 데이터에 의해 자동으로 수정됩니다.)
- 누구나 거래 내역 데이터에 대한 열람이 가능합니다.

   기존 거래 시스템에서는, A와 B가 거래를 하면 ‘은행’이라는 제  3자가 거래를 중계하는 시스템이었습니다. 그러나 이러한 기존 시스템은 제 3자인 은행의 실수로 금융 거래 정보가 누락되거나, 은행 서버가 해킹을 당할 수 있다는 점에서 치명적인 단점을 지니고 있습니다. 또한, 거래와 직접적 상관이 없는 은행이 수수료를 취한다는 점에서도 한계를 지닙니다. 이를 보완하기 위해 등장한 것이 바로 ‘블록체인’ 기술입니다. 이번에 구현해 본 블록체인 거래 장부 시스템은 거래가 발생할 때마다 거래에 대한 정보를 블록이 가지고, 이를 체인으로 연결해 모두가 공유할 수 있는 하나의 거래 장부를 만들었습니다. 또한, 위조 및 변조가 발생했을 시 이를 검사하여 다시 원상태로 되돌리는 회복기능까지 구현해보았습니다.
   <hr>

### [ Description ]

#### 1. Block 클래스  
(1) 필드 변수
- 거래 정보를 저장하는 변수인 String transaction
- 거래 정보에 대해 변조(손상)가 일어났을 시 원 정보로 되돌리기 위해 필요한 변수인 String transaction_recovery
- 거래 발생 후 Block이 생성되면 자동으로 만들어지는 int secret_code
- 모든 블록들이 공유하는 고정된 암호 값인 int password ( XOR 연산을 통해 암호화하기 위해 필요)
- XOR 연산을 통해 생성된 int encoded_data 
- 다음 블록의 주소를 가리키기 위해 필요한 Block next

#### 2. Person 클래스  
(1) 필드 변수
-   거래 당사자 이름을 받는 String name
- 거래 당사자 계좌를 받는 int balance
(2) 메소드
- void send_money(int money, Person a, Person b, Block data)   
  입금할 돈 액수인 money, 입금주인 Person a, 예금주(수신)인 Person b, 이 거래를 통해 생성되는 Block data를 파라미터로 받습니다.
입금주인 a 입장에서, b에게 돈을 입금함과 동시에 Block 클래스 객체를 받아와 거래 내역에 대한 장부를 기록합니다. 
- void change_books(Block data, String manipulation)   
  누군가 Block에 저장된 거래를 변조할 수 있습니다. 기존 Block에 저장된 거래 내역을 rawData에 저장하고, Block의 setTransaction 메소드를 통해 블록 데이터를 수정합니다. 이후, 원본 데이터와 비교해 똑같다면 그냥 넘어가고, 달라졌다면 Block 생성 시 부여되는 secret_code값을 201로 설정합니다. (1~200까지 부여되는 랜덤값인 secret_code를 201로 바꿔줌으로써 블록 데이터가 수정되었음을 나타내기 위해서입니다.)

#### 3. BlockChain 클래스 ( Block과 구성관계 )
(1) 필드 변수
- Block 클래스를 필드 변수로 가집니다. ( Block 들을 연결해주기 위해 )
- Block 클래스의 객체들을 체인으로 연결해주기 위해 Block head, Block tail을 가집니다.
- 연결된 Block 들이 몇 개 있는지 카운트 하기 위해 int size 를 가집니다.
(2) 메소드 
- void connectBlockFirst(Block a)
  Block 객체를 파라미터로 받아와 아무것도 연결이 안되어있을 때 맨 앞에 블록을 연결합니다.
- void connectBlock(Block a)
  Block 객체를 파라미터로 받아와 Block을 뒤에서부터 하나씩 연결합니다.
- void openBooks( )
  블록체인의 특징인 누구나 장부를 열람할 수 있다는 점을 참고해 그 동안 연결된 모든 Block들의 데이터들을 출력합니다. (거래 내역 출력)

#### 4. InspectorAndUpdater 클래스  
(1) 메소드
- void inspect(Block b) 
  Block 객체를 파라미터로 받아와 Block 객체의 encoded_data 변수와 Block 들이 공유하는 password를 XOR 연산을 통해 Block 생성 시 부여되는 원본 secret_code와 비교합니다. 
만약에 같다면, 원본 장부와 일치하다는 메시지를 출력하고 다르다면 장부가 손상되었음을 알려주는 메시지를 출력합니다. 그리고, 바로 원래 장부의 데이터로 되돌려줍니다. 

- void update(Block b)
  Block 객체를 파라미터로 받아와 변조로 인해 201로 수정되었던 Block의 secret_code 를 원상태로 되돌립니다. 
  
#### [ UML 도식화 ]
![image](https://user-images.githubusercontent.com/90627763/169973673-0af686cb-bc31-4b76-896d-bb5f572db066.png)


  <hr>

#### [참고 자료 ]
분산 컴퓨팅 기술(분산 저장), http://www.100ssd.co.kr/news/articleView.html?idxno=78314, [ Accessed : 2021.06.25 ]
블록체인 기반 시점확인서비스, https://www.hellot.net/news/article.html?no=55466 [ Accessed : 2020.12.09 ]

