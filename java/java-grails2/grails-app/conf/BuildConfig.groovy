grails.tomcat.jvmArgs= ["-Xms256m",  "-Xmx1024m", "-XX:PermSize=512m", "-XX:MaxPermSize=512m"]

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
        excludes 'grails-plugin-log4j'        
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        mavenRepo "https://repo.grails.org/grails/plugins"
        mavenRepo "http://repo.grails.org/grails/repo"

        mavenRepo "http://repo1.maven.org/maven2/"
        mavenRepo "http://repository.ow2.org/nexus/content/repositories/public"
        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "https://oss.sonatype.org/content/groups/public/"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://maven.restlet.org"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        compile 'io.swagger:swagger-core:1.5.3'
//    TODO wonder if we can pull in swagger-ui resources like this
//    compile 'org.webjars:swagger-ui:2.1.0'
        compile 'io.swagger:swagger-jersey2-jaxrs:1.5.3'
        runtime 'ch.qos.logback:logback-classic:1.0.6'
    }

    plugins {

	    runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"
        compile ':jaxrs:0.8'

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.1"

        compile ':cache:1.0.0'
    }
}
