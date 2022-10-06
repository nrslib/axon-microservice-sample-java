rootProject.name = "axon-microservice-sample"

include(
    "appkit:application-basic",
    "axon:application",
    "order-service:order-api",
    "order-service:order-service",
    "order-service:order-shared",
    "payment-service:payment-api",
    "payment-service:payment-service",
    "payment-service:payment-shared",
    "warehouse-service:warehouse-api",
    "warehouse-service:warehouse-service",
    "warehouse-service:warehouse-shared",
)