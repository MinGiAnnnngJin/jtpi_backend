# 🚆 Japan Transportation Pass Information (JTPI) - Backend API

> **이 저장소는 [JTPI](https://github.com/Capstone-Project-4-1)의 백엔드 부분만 포크하여 관리하는 저장소입니다.**  
> **Spring Boot 기반으로 구현된 백엔드 API 코드가 포함되어 있으며, 프론트엔드는 포함되지 않습니다.**  
> **팀원들과 함께 진행한 프로젝트로, API 실행 및 테스트를 위해 독립적으로 운영됩니다.**

## 📌 개발 배경 및 필요성 


## 📌 REST API 명세서

| 번호 | 기능                 | 엔드포인트                        | HTTP 메소드 | 요청 예시 | 응답 예시 | 설명 |
|----|----------------|-----------------------------|------------|-----------|-----------|-----------|
| 1  | 신규 슬라이드쇼 패스 | `/passes/slideshow/new` | GET | - | `[{ "id": 1, "title": "논비리 홀리데이 Suica 패스", "imageUrl": "https://seeklogo.com/images/J/JR-East-logo-384C8D5973-seeklogo.com.png"}]` | 최신 등록된 4개의 교통패스를 반환 |
| 2  | 추천 슬라이드쇼 패스 | `/passes/slideshow/recommended` | GET | - | `[{ "id": 1, "title": "논비리 홀리데이 Suica 패스", "imageUrl": "https://seeklogo.com/images/J/JR-East-logo-384C8D5973-seeklogo.com.png"}]` | 추천 알고리즘을 기반으로 4개의 랜덤 교통패스를 반환 |
| 3  | 패스 검색 | `/passes/search` | POST | `{ "searchQuery": "아오모리 홀리데이 패스", "departureCity": "0", "arrivalCity": "0", "transportType": "0", "cityNames": "0", "duration": 0, "minPrice": 0, "maxPrice": 0 }` | `[{ "passID": 2, "imageUrl": "https://www.jreast.co.jp/tickets/mapimg/2480-1.gif", "title": "아오모리 홀리데이 패스", "routeInformation": "아오모리", "price": "2520,1260" }]` | 사용자가 입력한 검색 조건에 맞는 패스 목록을 반환 |
| 4  | 패스 상세 정보 | `/passes/{passId}` | GET | - | `{ "passId": 1, "imageUrl": "https://seeklogo.com/images/J/JR-East-logo-384C8D5973-seeklogo.com.png", "transportType": "전철", "title": "논비리 홀리데이 Suica 패스", "routeInformation": "도쿄", "price": "2670,1330", "period": 1, "productDescription": "패스 설명", "Map_Url": "지도 URL", "stationNames": "도쿄, 신주쿠", "break_even_usage": "3회 사용 시 본전", "benefit_information": "특정 노선 할인 혜택", "reservation_information": "사전 예약 가능", "refund_information": "환불 정책 적용" }` | 특정 패스의 상세 정보를 반환 |
| 5  | 북마크된 패스 정보 | `/passes/bookmark` | POST | `[1, 2, 3]` | `[{ "passID": 1, "imageUrl": "https://seeklogo.com/images/J/JR-East-logo-384C8D5973-seeklogo.com.png", "title": "논비리 홀리데이 Suica 패스", "routeInformation": "도쿄", "price": "2670,1330" }]` | 사용자가 북마크한 패스 정보를 반환 |

