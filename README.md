# 스프링부트를 이용해 게시판 만들기

![Springboot](https://user-images.githubusercontent.com/112804301/197654563-a3eedff2-68ea-4041-ae26-04c9c6953222.PNG)


### JSP/서블릿 프로젝트때 했던 것처럼 예제와 조금씩 다르게 만들어보기
> 이클립스(STS) 대신 인텔리제이
> > 이번엔 인텔리제이도 써보자

<br/>

> Gradle 대신 Maven
> > 다음 공부해볼 서적에선 Gradle을 사용하는 것 같은데, 둘다 써보자

<br/>

> H2DB 대신 MariaDB

<br/>

> Entity에 Setter 대신 빌드패턴
> > Setter 사용 지양

<br/>

> Entity만 사용하지 않고 DTO도 사용
> > 객체 간 변환에 ModelMapper를 사용해보자 > Setter를 쓰지 못함 > MapStruct로 대체

등등..

+ Validation시, Form*.class를 따로 만들게 아니라 DTO로 하는게 좋을것 같다.
