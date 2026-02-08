# Gradle Setup - Kanaz Pad

## 📁 Struktur Folder Gradle

```
KanazPad/
├── gradle/                              ✅ Folder utama Gradle
│   ├── wrapper/                         ✅ Gradle Wrapper files
│   │   ├── gradle-wrapper.properties   ✅ Konfigurasi wrapper (Gradle 8.4)
│   │   ├── gradle-wrapper.jar          ⚠️  Binary JAR (akan di-generate otomatis)
│   │   └── gradle-wrapper.jar.txt      ℹ️  Instruksi download JAR
│   └── README.md                        ✅ Dokumentasi folder gradle
├── gradlew                              ✅ Wrapper script Unix/Linux/Mac
├── gradlew.bat                          ✅ Wrapper script Windows
├── gradle.properties                    ✅ Konfigurasi global Gradle
├── build.gradle.kts                     ✅ Root build script
└── settings.gradle.kts                  ✅ Settings Gradle
```

## ✅ File yang Sudah Dibuat

### 1. gradle/wrapper/gradle-wrapper.properties
```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.4-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

**Fungsi:** Menentukan versi Gradle yang akan di-download dan digunakan.

### 2. gradle/wrapper/gradle-wrapper.jar.txt
**Fungsi:** Penjelasan cara mendapatkan file gradle-wrapper.jar (binary).

**Catatan:** File JAR yang sebenarnya akan otomatis di-generate saat:
- Membuka project di Android Studio
- Menjalankan `./gradlew wrapper`
- Gradle sync pertama kali

### 3. gradlew
**Fungsi:** Shell script untuk menjalankan Gradle di Unix/Linux/Mac.

**Permission:** Sudah diberi execute permission (`chmod +x`).

**Usage:**
```bash
./gradlew build
./gradlew assembleDebug
./gradlew clean
```

### 4. gradlew.bat
**Fungsi:** Batch script untuk menjalankan Gradle di Windows.

**Usage:**
```cmd
gradlew.bat build
gradlew.bat assembleDebug
gradlew.bat clean
```

### 5. gradle.properties
**Fungsi:** Konfigurasi global untuk Gradle build.

**Isi penting:**
```properties
org.gradle.jvmargs=-Xmx2048m
android.useAndroidX=true
kotlin.code.style=official
android.nonTransitiveRClass=true
org.gradle.caching=true
org.gradle.configuration-cache=true
org.gradle.parallel=true
```

### 6. build.gradle.kts (Root)
**Fungsi:** Build script level project.

**Isi:**
```kotlin
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}
```

### 7. settings.gradle.kts
**Fungsi:** Settings untuk project Gradle.

**Isi:**
```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KanazPad"
include(":app")
```

## 🔧 Cara Setup di Android Studio

### Step 1: Buka Project
1. Buka Android Studio
2. File → Open
3. Pilih folder **KanazPad**
4. Click OK

### Step 2: Gradle Sync
Android Studio akan otomatis:
1. ✅ Detect Gradle wrapper
2. ✅ Download Gradle 8.4 (~100-150MB)
3. ✅ Download gradle-wrapper.jar
4. ✅ Download dependencies dari build.gradle.kts
5. ✅ Build project structure

**Tunggu hingga muncul:** "Gradle sync finished" di status bar.

### Step 3: Verify Setup
Di Terminal Android Studio, jalankan:
```bash
# Check Gradle version
./gradlew --version

# Expected output:
# Gradle 8.4
# Kotlin: 1.9.20
# JVM: 17.x.x
```

## 🐛 Troubleshooting

### Problem 1: "gradle-wrapper.jar not found"

**Cause:** File binary belum di-generate.

**Solution:**
```bash
# Opsi 1: Via Gradle Wrapper
./gradlew wrapper --gradle-version 8.4

# Opsi 2: Via Android Studio
File → Sync Project with Gradle Files

# Opsi 3: Force re-download
./gradlew wrapper --gradle-version 8.4 --distribution-type bin
```

### Problem 2: "Permission denied: ./gradlew"

**Cause:** File gradlew tidak executable.

**Solution (Mac/Linux):**
```bash
chmod +x gradlew
./gradlew --version
```

### Problem 3: Gradle Sync Failed

**Solution:**
```bash
# Clean gradle cache
rm -rf ~/.gradle/caches/
rm -rf .gradle/

# Re-sync
./gradlew clean
./gradlew build --refresh-dependencies
```

### Problem 4: "Unable to load class 'org.gradle.wrapper.GradleWrapperMain'"

**Cause:** gradle-wrapper.jar corrupted atau tidak ada.

**Solution:**
```bash
# Download fresh Gradle
rm -rf gradle/wrapper/gradle-wrapper.jar
./gradlew wrapper --gradle-version 8.4

# Atau via Android Studio
File → Invalidate Caches → Invalidate and Restart
```

### Problem 5: Gradle Version Mismatch

**Solution:**
Edit `gradle/wrapper/gradle-wrapper.properties`:
```properties
# Update to latest stable
distributionUrl=https\://services.gradle.org/distributions/gradle-8.4-bin.zip
```

## 📊 Gradle Performance Settings

Sudah dikonfigurasi di `gradle.properties`:

### Memory Settings
```properties
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
kotlin.daemon.jvmargs=-Xmx2048m
```
- Alokasi 2GB RAM untuk Gradle daemon
- Cukup untuk build project sedang

### Build Optimization
```properties
org.gradle.caching=true          # Enable build cache
org.gradle.parallel=true         # Parallel execution
org.gradle.configuration-cache=true  # Configuration cache
```
- Build lebih cepat (30-50% faster)
- Cache hasil build sebelumnya

## 🎯 Gradle Commands Berguna

### Build Commands
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Build semua variants
./gradlew assemble

# Install ke device
./gradlew installDebug
```

### Clean Commands
```bash
# Clean build folder
./gradlew clean

# Clean + build
./gradlew clean assembleDebug

# Clean everything
./gradlew cleanBuildCache
```

### Dependency Commands
```bash
# Show dependencies
./gradlew app:dependencies

# Update dependencies
./gradlew build --refresh-dependencies

# Check for updates
./gradlew dependencyUpdates
```

### Info Commands
```bash
# Gradle version
./gradlew --version

# Project tasks
./gradlew tasks

# Project properties
./gradlew properties

# Build scan
./gradlew build --scan
```

## 📦 Dependencies Auto-Download

Saat pertama kali sync, Gradle akan download:

### Android Libraries (~200MB)
- AndroidX libraries
- Material Design components
- Lifecycle components
- Room database

### Kotlin Libraries (~50MB)
- Kotlin stdlib
- Kotlin coroutines
- KSP processor

### Third-party Libraries (~150MB)
- Markwon (Markdown parser)
- iText7 (PDF generation)
- Apache POI (DOCX export)
- Coil (Image loading)

**Total download:** ~400-500MB (first time only)

## 🔐 Security Notes

### gradle-wrapper.jar
- File ini adalah **binary executable**
- Verifikasi checksum dari official Gradle:
  ```bash
  sha256sum gradle/wrapper/gradle-wrapper.jar
  ```
- Compare dengan: https://gradle.org/release-checksums/

### gradle.properties
- **JANGAN** commit credentials
- **JANGAN** commit signing keys
- Use `local.properties` untuk sensitive data

## 📱 Minimum Requirements

- **Gradle:** 8.4
- **JDK:** 17 or newer
- **Android Studio:** Hedgehog (2023.1.1) or newer
- **Disk Space:** 2GB free (untuk Gradle cache)
- **RAM:** 8GB (recommended 16GB)

## 🚀 Next Steps

Setelah Gradle setup berhasil:

1. ✅ Gradle sync completed
2. ✅ Dependencies downloaded
3. ✅ Project structure built
4. ➡️ Continue with UI development (XML layouts)
5. ➡️ Run app on device/emulator

## 📚 References

- [Gradle Documentation](https://docs.gradle.org/)
- [Android Gradle Plugin](https://developer.android.com/build)
- [Gradle Wrapper Guide](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

## 👨‍💻 Developer

**@GoKanaz** - Kanaz Pad Project

---

**Status:** ✅ Gradle setup COMPLETE
**Last Updated:** February 2026
