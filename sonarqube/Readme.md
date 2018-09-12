#SonarQube maven config demo


SonarQube maven 配置实例


maven 配置文件 settings.xml
    resources/config/settings.xml
    
        <profiles>
            <profile>
                <id>jdk-1.8</id>
                <activation>
                    <activeByDefault>true</activeByDefault>
                    <jdk>1.8</jdk>
                </activation>
                <properties>
                    <maven.compiler.source>1.8</maven.compiler.source>
                    <maven.compiler.target>1.8</maven.compiler.target>
                    <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
                </properties>
            </profile>
    
            <profile>
                <id>sonar</id>
                <properties>
                    <sonar.jdbc.url>jdbc:mysql://192.168.100.28:3306/sonar</sonar.jdbc.url>
                    <sonar.jdbc.driver>com.mysql.jdbc.Driver</sonar.jdbc.driver>
                    <sonar.jdbc.username>root</sonar.jdbc.username>
                    <sonar.jdbc.password>chenkuo123456</sonar.jdbc.password>
                    <sonar.host.url>http://192.168.100.23:9000/sonar</sonar.host.url> <!-- Sonar服务器访问地址 -->
                    <java-module.sonar.sources>src</java-module.sonar.sources>
                    <java-module.sonar.projectBaseDir>.</java-module.sonar.projectBaseDir>
                    <sonar.java.binaries>target/classes</sonar.java.binaries>
                </properties>
            </profile>
        </profiles>
    
        <activeProfiles>
    
            <activeProfile>sonar</activeProfile>
    
        </activeProfiles>