plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("java")
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.functionaljava:functionaljava:5.0")
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

/* --- commons --- */
project(":appkit:application-basic") {
    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.0")
    }
}

project (":axon:application") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
    }
}

/* --- order --- */
project (":order-service:order-api") {
    dependencies {
        implementation(project(":order-service:order-shared"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":warehouse-service:warehouse-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
    }
}

project (":order-service:order-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":order-service:order-service") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":order-service:order-api"))
        implementation(project(":order-service:order-shared"))
        implementation(project(":payment-service:payment-api"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":warehouse-service:warehouse-api"))
        implementation(project(":warehouse-service:warehouse-shared"))

        // Default
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")

        implementation("io.arrow-kt:arrow-core:1.1.2")
        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:4.5.4")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:4.5")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

        runtimeOnly("com.h2database:h2:2.1.214")
        runtimeOnly("mysql:mysql-connector-java")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

/* --- payment --- */
project (":payment-service:payment-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":order-service:order-shared"))
        implementation(project(":payment-service:payment-shared"))
        implementation(project(":warehouse-service:warehouse-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
    }
}

project (":payment-service:payment-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":payment-service:payment-service") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":order-service:order-api"))
        implementation(project(":order-service:order-shared"))
        implementation(project(":payment-service:payment-api"))
        implementation(project(":payment-service:payment-shared"))

        // Default
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")

        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:4.5.4")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:4.5")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

        runtimeOnly("com.h2database:h2:2.1.214")
        runtimeOnly("mysql:mysql-connector-java")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

/* --- warehouse --- */
project (":warehouse-service:warehouse-api") {
    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":order-service:order-shared"))
        implementation(project(":warehouse-service:warehouse-shared"))

        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
    }
}

project (":warehouse-service:warehouse-shared") {
    dependencies {
        implementation(project(":appkit:application-basic"))
    }
}

project (":warehouse-service:warehouse-service") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":appkit:application-basic"))
        implementation(project(":axon:application"))
        implementation(project(":order-service:order-api"))
        implementation(project(":order-service:order-shared"))
        implementation(project(":warehouse-service:warehouse-api"))
        implementation(project(":warehouse-service:warehouse-shared"))

        // Default
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")

        implementation("org.axonframework:axon-spring-boot-starter:4.5.15")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:4.5.4")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:4.5")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

        runtimeOnly("com.h2database:h2:2.1.214")
        runtimeOnly("mysql:mysql-connector-java")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}