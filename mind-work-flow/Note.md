用户权限控制需完善

用户密码数据库加密

把用户登录后的认证信息存储到redis等缓存服务器

提权需要人工审核, 因此可能需要使用消息队列实现



技术选型

1. 使用 spring-session 替换 http session 用于多服务器共享信息
2. 使用 spring-security 进行权限验证及控制
3. 使用 mybatis 连接操作数据库
4. 使用 redis 作为缓存服务器存储数据以及与 spring-session 共同作用
5. 使用 log4j2 作为日志记录并使用 ELK 技术栈作为分析记录工具