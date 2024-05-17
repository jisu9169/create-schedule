# create-schedule
## 목차
[1. 필수 요구 사항]( #1-필수-요구-사항 )

[2. Use Case Diagram]( #2-use-case-diagram )

[3. API 명세서]( #3-api-명세서 )

[3. ERD]( #-4-erd)


## 1. 필수 요구 사항
### 공통 조건
1. 일정 작성,수정,조회 시 반환 받은 일정 정보에 비밀번호는 제외 되어야 한다.
2. 일정 수정, 삭제 시 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능

#### 1단계 기능: 일정 작성
* 할일 제목, 할일 내용, 담당자, 비밀번호, 작성일 을 저장 할 수 있다. 
  * 저장된 일정 정보를 반환 받아 확인할 수 있다.

#### 2단계 기능: 선택한 일정 조회
* 선택한 일정의 정보를 조회할 수 있다.

#### 3단계 기능: 일정 목록 조회
* 등록된 일정 전체를 조회 할 수 있다.
* 조회된 일정 목록은 작성일 기준 내림차순으로 정렬 되어있다.

#### 4단계 기능: 선택한 일정 수정
* 선택한 일정의 할일 제목, 할일 내용, 담당자를 수정 할 수 있습다.
   * 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달된다.
* 수정된 일정의 정보를 반환 받아 확인 할 수 있다.

#### 5단계 기능: 선택한 일정 삭제
* 선택한 일정을 삭제 할 수 있다.
  * 서버에 일정 삭제를 요청할 때 비밀번호를 함께 전달한다.

### 추가 구현 기능

### 6단계 기능: 예외 발생 처리
* 수정, 삭제 시 요청할 때 보내는 비밀번호가 일치하지 않을 때
* 선택한 일정 정보가 이미 삭제되어 조회할 수 없을 때
* 삭제하려는 일정 정보가 이미 삭제 상태일 때

### 7단계 기능: Swagger 활용 & 파라미터 유효성 검사, null 체크 및 특정 패턴에 대한 검증 수행
* Swagger
    * wagger UI를 통해 API 목록을 확인할 수 있다.
    * Swagger UI를 통해 API 테스트를 할 수 있다.
* 유효성 검사
  * 할일 제목은 최대 200자 이내로 제한, 필수값 처리
  * 비밀번호는 필수값 처리
  * 담당자는 이메일 형식을 갖도록 처리
  
### 8단계 기능: 파일 업로드 & 다운로드
* 사용자는 파일을 업로드 할 수 있습니다. 업로드된 파일은 개인 PC에 저장한다.
    * 특정 파일 형식만을 업로드할 수 있도록 구현할 수 있다.
    * JPG, PNG, JPEG 등 이미지

### 9단계 기능: 테스트 코드 작성
* 주어진 요구사항에 따라 JUnit과 Mockito를 사용하여 테스트 코드를 작성
    * 이를 통해 각 기능이 의도한 대로 동작하는지 검증
* 코드 커버리지를 높이기 위해 Jacoco를 활용하여 테스트 코드가 대상 코드를 80% 이상 커버하도록 목표를 설정
* 소프트웨어의 품질을 높이고 안정성을 보장한다.

---

## 2. Use Case Diagram

---

## 3. API 명세서

---

![img.png](img.png)

## 4. ERD
---
![img_1.png](img_1.png)

