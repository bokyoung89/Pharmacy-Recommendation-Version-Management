# Recommendation service

외부 API(카카오 주소 검색 API)와 공공 데이터(상비약 판매 편의점 현황 정보)를 활용하여 상비약 판매 편의점을 추천한다.

추천된 약국의 길 안내는 카카오 지도 및 로드뷰 바로가기 URL로 제공된다.

---

## 기술스택
* JDK 11
* Spring Boot 2.6.7
* Spring Data JPA
* Gradle
* Handlebars
* Lombok
* Github
* Docker
* AWS EC2
* Redis
* MariaDB
* Spock
* Testcontainers

---

## 요구사항 분석
* 상비약 판매 편의점 찾기 서비스 요구사항 
  * 상비약 판매 편의점 데이터(공공 데이터)는 편의점명, 주소, 위도, 경도의 위치 정보 데이터를 가지고 있다.
  * 해당 서비스로 주소 정보를 입력하여 요청하면 위치 기준에서 가까운 편의점 3곳을 추출한다.
  * 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다. 
    * Kakao 우편번호 서비스를 사용한다.
  * 주소는 정확한 상세 주소(동, 호수)를 제외한 주소 정보를 이용하여 추천한다. 
  * 입력 받은 주소를 위도, 경도로 변환 하여 기존 편의점 데이터와 비교 및 가까운 편의점을 찾는다.
    * 두 위 경도 좌표 사이의 거리를 haversine formula로 계산한다.
  * 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 편의점만 추천한다.
  * 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공한다.
  * 길안내 URL은 고객에게 제공되기 때문에 가독성을 위해 shorten url로 제공한다.
  * shorten url에 사용 되는 key값은 인코딩하여 제공한다.
    * http://localhost:8080/dir/nqxtX
    * base62를 통한 인코딩
  * shorten url의 유효 기간은 30일로 제한한다.

---

## 시퀀스 다이어그램

![sequenceDiagram](https://github.com/bokyoung89/fastcampus-project-board/assets/58727604/76fedb86-2361-40db-a6b2-601f336526eb)

---

### 결과화면

<img src="https://github.com/bokyoung89/Pharmacy-Recommendation-Version-Management/assets/58727604/38679fa6-671a-43bf-ac12-cfa937a085e4" width="80%" height="80%">

![제목 없는 동영상 - Clipchamp로 제작](https://github.com/bokyoung89/fastcampus-project-board/assets/58727604/346e4f8f-dc67-4043-b432-924b602a324a)