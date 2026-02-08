# Gradle Wrapper

Folder ini berisi file-file Gradle Wrapper yang diperlukan untuk build project Android.

## File yang Ada:

### ✅ gradle/wrapper/gradle-wrapper.properties
File konfigurasi Gradle Wrapper yang menentukan versi Gradle yang akan digunakan.
- Versi Gradle: 8.4
- Distribution: bin (bukan all, untuk ukuran lebih kecil)

### ⚠️ gradle/wrapper/gradle-wrapper.jar
File binary JAR untuk Gradle Wrapper. File ini TIDAK disertakan karena merupakan file binary.

**Cara mendapatkan gradle-wrapper.jar:**

1. **Otomatis saat membuka di Android Studio:**
   - Buka project di Android Studio
   - Android Studio akan otomatis download file ini
   
2. **Manual via Command Line:**
   ```bash
   # Di terminal/command prompt, jalankan:
   ./gradlew wrapper --gradle-version 8.4
   
   # Atau di Windows:
   gradlew.bat wrapper --gradle-version 8.4
   ```

3. **Download Manual:**
   - Download dari: https://services.gradle.org/distributions/gradle-8.4-bin.zip
   - Extract file zip
   - Copy file: `gradle-8.4/lib/gradle-wrapper-8.4.jar`
   - Paste ke folder `gradle/wrapper/` dan rename menjadi `gradle-wrapper.jar`

## File di Root Project:

### ✅ gradlew (Unix/Linux/Mac)
Script shell untuk menjalankan Gradle di sistem Unix-like.
Sudah diberi permission executable.

### ✅ gradlew.bat (Windows)
Batch script untuk menjalankan Gradle di Windows.

### ✅ gradle.properties
File konfigurasi global untuk Gradle build:
- JVM memory settings
- AndroidX enabled
- Build cache enabled
- Parallel execution enabled

## Cara Menggunakan:

### Di Command Line:

**Unix/Linux/Mac:**
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean project
./gradlew clean

# Run tests
./gradlew test
```

**Windows:**
```cmd
# Build debug APK
gradlew.bat assembleDebug

# Build release APK
gradlew.bat assembleRelease

# Clean project
gradlew.bat clean

# Run tests
gradlew.bat test
```

### Di Android Studio:

Android Studio akan otomatis menggunakan Gradle Wrapper ini untuk build project.
Anda tidak perlu menjalankan command secara manual.

## Troubleshooting:

### Error: "gradle-wrapper.jar not found"

**Solusi:**
1. Buka Android Studio
2. File → Sync Project with Gradle Files
3. Android Studio akan otomatis download file yang diperlukan

Atau jalankan di terminal:
```bash
./gradlew wrapper --gradle-version 8.4
```

### Error: "Permission denied: ./gradlew"

**Solusi untuk Mac/Linux:**
```bash
chmod +x gradlew
./gradlew --version
```

### Error: Gradle version mismatch

**Solusi:**
Edit file `gradle/wrapper/gradle-wrapper.properties` dan update:
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.4-bin.zip
```

## Versi Gradle

Project ini menggunakan:
- **Gradle**: 8.4
- **Android Gradle Plugin**: 8.2.0
- **Kotlin**: 1.9.20

Kompatibel dengan:
- Android Studio Hedgehog (2023.1.1) atau lebih baru
- JDK 17 atau lebih baru

## Notes:

- File `gradle-wrapper.jar` berukuran sekitar 60KB
- Gradle akan otomatis di-download saat pertama kali build (sekitar 100-150MB)
- Cache Gradle disimpan di `~/.gradle/` (Unix) atau `C:\Users\<username>\.gradle\` (Windows)

## Developer

@GoKanaz - Kanaz Pad Project
