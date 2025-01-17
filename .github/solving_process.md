# 🧐 미션 - 페어매칭관리 애플리케이션

[우아한테크코스](https://github.com/woowacourse) precourse 문제
중 [미션 - 페어매칭관리 애플리케이션](https://github.com/woowacourse/java-pairmatching-precourse) 풀이 기록하기.

계층형 아키텍처 구조와 MVC 패턴을 적용하여 TDD(테스트 주도 개발) 방식으로 개발하고, 입출력 및 프로그래밍 요구사항을 부합하도록 풀어 볼 예정.

## 0. 설계

### infrastructure

| 클래스  | 기능               |
|:----:|:-----------------|
| Game | - 애플리케이션 실행 및 종료 |

### view

|      클래스       | 기능                  |
|:--------------:|:--------------------|
|      View      | - 화면 기본 동작 정의       |
|    TextView    | - 화면 출력 동작 정의       |
|    FormView    | - 화면 입력 동작 정의       |
| InputFormView  | - 화면 텍스트 입력 동작 정의   |
| SelectFormView | - 화면 선택 입력 동작 정의    |
|   FrameView    | - 화면 템플릿 구조 동작 정의   |
|   RepeatView   | - 화면 반복 구조 동작 정의    |
| SerializeView  | - 화면 연속 시행 구조 동작 정의 |

### controller

|      클래스       | 기능                        |
|:--------------:|:--------------------------|
|   Controller   | - parameter 유효성 체크 및 가공   |
| ViewController | - View 생성<br/> - 화면 동작 처리 |

### service

|   클래스   | 기능        |
|:-------:|:----------|
| Service | - 비즈니스 처리 |

### repository

|    클래스     | 기능            |
|:----------:|:--------------|
| Repository | - CRUD 데이터 처리 |

### domain

|   클래스   | 기능   |
|:-------:|:-----|
| Course  | - 과정 |
|  Crew   | - 크루 |
|  Level  | - 레벨 |
| Mission | - 미션 |
|  Pair   | - 페어 |

### ui

|     클래스      | 기능                |
|:------------:|:------------------|
|    Input     | - 기본 입력 정의        |
| InputHelper  | - 기본 활용 가능한 입력 정의 |
|    Output    | - 기본 출력 정의        |
| OutputHelper | - 기본 활용 가능한 출력 정의 |

### exception

|               클래스               | 기능                   |
|:-------------------------------:|:---------------------|
|  IllegalArgumentBeanException   | - 아무 기능 없는 Exception |
| IllegalArgumentServiceException | - 서비스 관련 Exception   |
|  IllegalArgumentViewException   | - 입력 관련 Exception    |