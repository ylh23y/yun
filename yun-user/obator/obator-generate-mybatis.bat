@echo off
cd /d %~dp0
java -jar G:\raypic\hemiao-user\src\main\webapp\WEB-INF\lib\mybatis-generator-core-1.3.5.jar -configfile %cd%/mybatisGenerator.xml -overwrite

pause