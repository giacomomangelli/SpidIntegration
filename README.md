### HOW TO BUILD

1. Run inside SpidIntegration folder: 
    ```bash
       mvn clean package -DskipTests
    ```
2. Run in the current folder:
    ```bash
    docker compose build --no-cache
    ```
3. After build, also in the current folder, run:
    ```bash
    docker compose up -d
    ```

