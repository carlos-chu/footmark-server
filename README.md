# footmark
脚印服务端，易扩展，框架通用<br>
项目构建：maven<br>
框架：spring, mybatis, slf4j, junit...<br>
数据库：mysql<br>
结构目录介绍：<br>
	&ensp;api:所有的对外接口，主要包含http协议的访问接口<br>
	&ensp;common:顾名思义的包含公共model, enum, util工具类, 统一exception<br>
	&ensp;core:主要业务承载工程，包含大部分的业务逻辑<br>
	&ensp;framework:框架封装项目，整个项目的主体框架封装提供者，通俗讲就是数据怎么进入系统怎么返回到客户端，使用怎样的数据库管理方式等等<br>
	&ensp;gateway:网关工程，里面包含了与百度的推送，地图SDK，阿里云SDK的网关交互逻辑<br>
	&ensp;web:可以理解为deploy工程，主要的配置部署<br>
sql目录下要使用最新的sql<br>
Tips:<br>
	&ensp;需要执行gateway项目下libs下的build.sh，这样就会把推送的所需的jar包纳入maven的管理下
<br>
TODO：<br>
	&ensp;1.封装一个cache工程，包含与缓存相关的逻辑，目前来看项目的承受量没有必要加一层缓存，待业务上需要时在封装<br>
	&ensp;2.封装出一个多线程池框架，基于concurrent包，使多线程可控，尽量守护new出来的线程，满足并发需求，降低阿里云成本<br>
	&ensp;3.独立搭建一套基于quartz的任务框架，要简洁，易于维护，易于添加，集群，且可监控<br>
	&ensp;4.增加测试覆盖率<br>
	&ensp;5.继续开发未完成的业务功能<br>
