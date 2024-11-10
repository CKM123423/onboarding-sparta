# 온보딩 과제(스파르타)

## 목차
1. [Filter란 무엇인가? (with intercepter, AOP)](#Filter란-무엇인가? (with intercepter, AOP))
   - [Filter](#Filter)
   - [interceptor](#interceptor)
   - [AOP](#AOP)
3. [Spring Security 란?](#Spring-Security-란?)
4. [JWT란 무엇인가요?](#JWT란-무엇인가요?)
5. [EC2 배포](#EC2-배포)
   
## Filter란 무엇인가? (with interceptor, AOP)
### Filter
보통 일반적으로 Java를 사용한 Web에서는 클라이언트의 요청을 HTTP나 HTTPS프로토콜을 사용해 서버의 **서블릿**에서 처리 합니다.

Spring은 **DispatcherServlet**이 이 서블릿 역할을 담당하고 요청을 처리합니다. 이러한 요청과 응답의 처리중에 중요한 역할을 하는 것이 바로 **Filter**입니다.

보통 **Filter는 서블릿에 요청이 도달하기 전**에 실행되며 여러 필터를 **체인형태**로 연결하여 사용할 수 있습니다. 이때 필요에 의해 HTTP 요청이나 응답을 조건에 따라 차단하거나 수정할 수 있습니다.

주로 **인증 및 권한부여, 로깅, 데이터 변환, CORS처리**에 많이 사용됩니다.

### interceptor
Spring에서의 Interceptor는 **Spring MVC**에서 제공하는 기능이며 **컨트롤러의 요청을 가로채** 처리할 수 있습니다.

보통 **interceptor**는 요청이 **컨트롤러에 도달하기 전**에 실행되며 필터와 마찬가지로 조건에 따라 요청 및 응답에 접근하여 차단 및 수정이 가능합니다.

주로 **인증 및 권한부여, 로깅, 공통 로직, 모니터링, 예외처리**에 많이 사용됩니다.

### AOP
AOP는 **관점 지향 프로그래밍**으로 **공통적인 관심사를 모듈화**해서 코드의 중복을 줄여 유지보수성을 향상시키는 방법입니다.

비지니스 로직과는 별개로 로깅, 보안, 트랜잭션 관리 같은 **공통 기능**에 자주 사용됩니다.

#### 주요개념 

1. **Aspect**: 공통 관심사를 모듈화한 객체를 의미합니다.
  
2. **Join Point**: Aspect가 적용될 수 있는 지점으로 메서드 호출, 객체 생성, 예외 발생 등이 포함됩니다.

3. **Advice**: Join Point에서 실행되는 코드로, Aspect의 동작을 정의합니다.
   - **Before**: Join Point 실행 전에 실행.
   - **After**: Join Point 실행 후에 실행.
   - **After Returning**: Join Point가 정상적으로 실행된 후에 실행.
   - **After Throwing**: Join Point에서 예외가 발생했을 때 실행.
   - **Around**: Join Point의 전후에 실행되며 Join Point의 실행을 제어.

## Spring Security 란?
Spring Security는 Spring 프레임워크의 **보안 기능을 제공하는 모듈**입니다. **인증, 인가, 권한부여**를 처리하는데 주로 사용됩니다.

**OAuth, JWT** 등 다양한 인증 방법을 지원하며 **CSRF(사이트 간 요청 위조)공격 방어** 기능이 있습니다.

보통 사용자가 로그인할 때 인증이 되면 인가를 하고 권한에 따라 접근을 통제하거나 악의적인 요청을 차단합니다.


## JWT란 무엇인가요?

JWT는 정보를 JSON 형식으로 교환하기 위해 사용됩니다. 토큰에 정보를 저장해서 인증과 그외 필요한 정보들을 저장합니다.

JWT의 사용이유는 HTTP는 기본적으로 **무상태(state-less)를 지향**하기 때문에 서버와 클라이언트 구조관계에서 서버가 상태를 가지는 **세션을 사용하지 않기 위해**서 사용합니다.

#### JWT의 구조
1. Header : 토큰의 유형과 서명의 알고리즘 정보 포함.
2. Payload : 사용자의 정보와 클레임(발급자, 수신자, 발급시간, 만료시간등) 포함.
3. Signature : Header와 Payload를 조합하여 비밀 키로 서명한 값

다만 JWT는 누구나 쉽게 정보를 볼 수 있기 때문에 **중요하고 민감한 정보는 담지 않습니다.**


### EC2 배포
![ec2 배포](https://github.com/user-attachments/assets/5c012f96-460c-489d-8621-499ee874acdc)



