plugins {
    id 'java'
    id "io.swagger.core.v3.swagger-gradle-plugin" version '2.0.10'
}

group 'io.swagger.test'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()

}

dependencies {
    compile group: 'org.apache.commons', name: 'commons-lang3', version:'3.7'
    compile group: 'javax.ws.rs', name: 'javax.ws.rs-api', version:'2.1'
    compile group: 'javax.servlet', name: 'javax.servlet-api', version:'3.1.0'
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

resolve {

    outputFileName = 'PetStoreAPI'
    //outputFormat = 'JSONANDYAML'
    outputFormat = 'YAML'
    prettyPrint = 'TRUE'
    classpath = sourceSets.main.runtimeClasspath
    resourcePackages = ['io.swagger.v3.plugins.grudle.petstore']
    outputDir = file(project.buildDir)

}
