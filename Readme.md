# 실행 전 준비사항
1. 로컬 컴퓨터에 Mysql을 설치하셔야 합니다.   
설치한 이후 유저 이름은 "root", 비밀번호는 비워두신 상태로 mysql 사용자를 만드셔야 합니다.   
   만약 별도로 유저 이름과 비밀번호를 설정하셨다면 src/main/resources/application.properties 파일에서 username, password를 새로 설정해 주셔야 합니다.
2. api에 대한 정보는 "본 프로젝트 어플리케이션을 실행한 후" http://localhost:8080/swagger-ui/index.html
에 접속하시면 Swagger UI를 통해 작성된 API 문서를 확인하실 수 있습니다.   
   혹은 첨부된 swagger.json 파일의 내용을 복사하여 https://editor-next.swagger.io/ 사이트에 붙여넣기 하여 내용을 확인하실 수도 있습니다.