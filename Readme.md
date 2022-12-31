Nebula resource
=============
52hertz 메타버스 플랫폼의 리소스를 제어/관리하는 서버입니다<br>
RestAPI 로 Unity Client 와 데이터를 송수신하며 플랫폼 자원의 모든 데이터를 관리합니다<br>

### 아키텍처
![](https://cdn.discordapp.com/attachments/912599924036735019/1058704626582556722/infra_.png)
- AWS 상의 다중 가용영역에 배포되어 있습니다.
- github hooks / Jenkins를 이용해 개발 서버 CI/CD
- codeBuilder / codeDeploy를 이용해 배포 서버 CI/CD

### 구현 목표
<b>서버측 유효성 검사</b><br>
- 클라이언트측에서는 API 호출을 통해서만 자원을 생산할 수 있다.
- 슬롯 변경이나 건물의 설치 등 멱등성이 성립하는 데이터의 경우, 멱등성 성립을 검사한 후 데이터를 변경할 수 있도록 허용한다.
- 유효자원의 생산시에는 반드시 로그데이터를 남겨 클라이언트 해킹을 방지
- 유효자원에 대한 프론트와의 공유 사항은 미로를 통해 지속적 공유
  - https://miro.com/app/board/uXjVPFnAK0s=/?share_link_id=237294923320
  - https://miro.com/app/board/uXjVPNYTnjs=/?share_link_id=279239958536
<br><br>

<b>클라우드 네이티브 배포</b>
- 오토스케일링과 로드밸런싱 환경에서 설정정보를 통합적으로 관리하기 위해 Configuration Server를 분리
  - spring cloud config client 이용
- 설정 파일에 대한 변경은 actuator/refresh 를 통해 서버별 갱신
  - [ ] spring cloud bus를 통해 갱신 자동화
- 운영환경과 테스트 환경을 분리해서 배포
  - 운영환경의 경우 main 브랜치를 ubuntu 상에서 가동
  - 개발환경의 경우 dev 브랜치를 ubuntu 상의 docker machine에서 가동
<br><br>

<b>성능 최적화</b>
- 데이터베이스에서 읽기요청이 많을 것으로 예상됨
  - [ ] CQRS 패턴 적용
  - [ ] 읽기 전용 데이터베이스를 분리하여 읽기 로직처리만 담당
- 인덱스와 쿼리 튜닝 활용
- [ ] JPA N+1 쿼리 문제 해결
<br><br>

<b>코딩 컨벤션</b>
- Google Java Style Guide
- https://github.com/google/styleguide
- xml 파일을 인텔리제이 code style schema에 적용
<br><br>

<b>지속 고려 사항</b>
- 중복코드 최소화
- 성능 개선

### CI
Jenkins : PR/Merge 시 테스트 자동화 <br>
CodeBuilder(AWS) : Build파일 패키징 시 테스트 자동화<br>
### CD
<b>개발 서버</b> <br>
Jenkins : Merge시 도커 이미지를 만들어 배포 <br>
보안 상 도커 허브에는 올라가지 않음 <br>
Build 성공 시 해당 이미지 파일로 서버 대체 실행 <br>
<b>배포 서버</b> <br>
CodeBuilder : github 레파지토리의 파일을 clone 하여 maven 빌드
CodeDeploy : Build에 성공할 경우 오토 스케일링 대상 그룹에서 Blue/Green 무중단 배포


### 테스트
- 테스트 자동화 구현
  - github PR시 테스트 자동화
  - 메인에 머지 시 테스트 자동화
  - maven package 시 테스트 자동화
- [ ] 테스트 코드 작성 예정

### Database
- PostgreSQL<br>
AmazonRDS 이용하여 배포
private Subnet을 이용하여 외부 접속 제한
![image](https://cdn.discordapp.com/attachments/1020207271692738623/1049585147109969930/image.png)

### 개발환경
- Java8
- Spring boot
- Maven
- JPA
- Docker
- PostgreSQL
- Jenkins
- AWS
  - Route53
  - LoadBalancer
  - AutoScaling
  - Code Deploy / Code Builder
  - EC2
  - VPC / Subnet
  - etc ...