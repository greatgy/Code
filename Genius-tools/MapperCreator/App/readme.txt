该程序用来生成Mybatis映射文件

使用说明:
1. 安装 NodeJS环境，并执行npm install
2. 找到 App\src\main\resources\config\ProjTestDBTest.json 文件。
   修改相应配置：
   tables：tables是所要生成的映射文件的文件名。将其改成自己想要生成的文件的文件名，
           要和实体文件名、数据库中的表名一致
		   注意：大小写与实体类一致，驼峰形式，首字母小写
   namespace：修改成数据库中的表对应的实体所在的包
   mysql.conn.password: 修改成自己数据库的密码
   mysql.conn.database： 修改成表所在的数据库名
   mysql.conn.port: 数据库的端口号
   若还有其它要求修改相应配置
3. npm start或node bin/start.js运行项目，会生成 .xml 文件。具体的文件所在路径，查看控制台输出
