# Panduan Instalasi Kanaz Pad

## Prasyarat

Sebelum memulai, pastikan Anda telah menginstall:

1. **Android Studio Hedgehog (2023.1.1) atau lebih baru**
   - Download dari: https://developer.android.com/studio

2. **JDK 17 atau lebih baru**
   - Android Studio biasanya sudah include JDK

3. **Android SDK**
   - Minimal API 24 (Android 7.0)
   - Target API 34 (Android 14)

## Langkah Instalasi

### 1. Clone atau Download Project

```bash
# Jika menggunakan Git
git clone https://github.com/GoKanaz/kanaz-pad.git

# Atau download ZIP dan extract
```

### 2. Buka Project di Android Studio

1. Buka Android Studio
2. Pilih **File → Open**
3. Navigate ke folder **KanazPad**
4. Klik **OK**

### 3. Sync Gradle

1. Tunggu Android Studio load project
2. Klik **File → Sync Project with Gradle Files**
3. Atau klik tombol "Sync Now" jika muncul notification bar
4. Tunggu hingga proses sync selesai (download dependencies)

### 4. Setup Android Device/Emulator

#### Opsi A: Menggunakan Physical Device

1. Enable **Developer Options** di Android phone:
   - Buka Settings → About Phone
   - Tap 7x pada "Build Number"
   
2. Enable **USB Debugging**:
   - Settings → System → Developer Options
   - Enable "USB Debugging"

3. Connect phone ke komputer via USB
4. Accept USB debugging prompt di phone

#### Opsi B: Menggunakan Emulator

1. Di Android Studio, klik **Tools → Device Manager**
2. Klik **Create Device**
3. Pilih device (contoh: Pixel 6)
4. Pilih System Image (recommend: API 34)
5. Klik **Finish**

### 5. Build dan Run

1. Pastikan device/emulator sudah terpilih di toolbar
2. Klik tombol **Run** (▶️) atau tekan **Shift+F10**
3. Tunggu build process selesai
4. Aplikasi akan ter-install dan run di device

## Troubleshooting

### Problem: Gradle Sync Failed

**Solusi:**
```bash
# Clear cache
File → Invalidate Caches → Invalidate and Restart

# Atau clean dan rebuild
Build → Clean Project
Build → Rebuild Project
```

### Problem: SDK Not Found

**Solusi:**
```bash
File → Settings → Appearance & Behavior → System Settings → Android SDK
# Pastikan Android 14.0 (API 34) ter-install
```

### Problem: Build Failed - Dependency Error

**Solusi:**
```kotlin
// Di app/build.gradle.kts, coba update version:
implementation("androidx.core:core-ktx:1.12.0")
implementation("com.google.android.material:material:1.11.0")
```

### Problem: Emulator Slow

**Solusi:**
1. Enable Hardware Acceleration:
   - Tools → AVD Manager
   - Edit your AVD
   - Show Advanced Settings
   - Set Graphics to "Hardware - GLES 2.0"

2. Allocate more RAM:
   - Edit AVD → Show Advanced Settings
   - Increase RAM to 2048 MB or more

### Problem: App Crashes on Startup

**Solusi:**
```bash
# Check Logcat for error messages:
View → Tool Windows → Logcat

# Common fixes:
1. Bersihkan dan rebuild:
   Build → Clean Project
   Build → Rebuild Project

2. Uninstall app dari device dan run ulang

3. Check permissions di AndroidManifest.xml
```

## File Structure Check

Pastikan struktur file seperti ini:

```
KanazPad/
├── app/
│   ├── build.gradle.kts  ✓
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml  ✓
│           ├── java/com/gokanaz/kanazpad/
│           │   ├── MainActivity.kt  ✓
│           │   ├── KanazPadApplication.kt  ✓
│           │   ├── data/
│           │   │   ├── database/  ✓
│           │   │   ├── model/  ✓
│           │   │   └── repository/  ✓
│           │   ├── ui/  (needs to be created)
│           │   └── utils/  ✓
│           └── res/
│               ├── layout/  (needs XML files)
│               ├── values/  (needs XML files)
│               ├── menu/  (needs XML files)
│               └── drawable/  (needs icons)
├── build.gradle.kts  ✓
└── settings.gradle.kts  ✓
```

## Dependencies yang Diperlukan

Project ini menggunakan:

- **Room Database** (2.6.1) - Local storage
- **Kotlin Coroutines** (1.7.3) - Asynchronous programming
- **Material Design 3** (1.11.0) - UI components
- **Markwon** (4.6.2) - Markdown parsing & rendering
- **iText7** (7.2.5) - PDF generation
- **Apache POI** (5.2.3) - DOCX export
- **Coil** (2.5.0) - Image loading
- **DataStore** (1.0.0) - Preferences storage

Semua dependencies akan otomatis di-download saat Gradle sync.

## Minimum Requirements

- **OS**: Windows 10/11, macOS 10.14+, atau Linux
- **RAM**: Minimal 8GB (16GB recommended)
- **Storage**: 4GB free space untuk Android Studio + SDK
- **Android Device**: Android 7.0 (API 24) atau lebih tinggi

## Setelah Instalasi

1. ✅ App berhasil run
2. ✅ Bisa create new note
3. ✅ Bisa edit dan auto-save
4. ✅ Bisa export PDF/HTML/DOCX
5. ✅ Bisa import .md/.txt files
6. ✅ Version history working
7. ✅ Focus mode working
8. ✅ Image insertion working

## Next Steps

Setelah instalasi berhasil, Anda bisa:

1. Customize UI di `res/layout/` files
2. Tambah fitur baru di `MainActivity.kt` atau `EditorActivity.kt`
3. Modifikasi theme di `res/values/themes.xml`
4. Tambah icon sendiri di `res/drawable/`

## Support

Jika ada masalah:

1. Check **Logcat** untuk error messages
2. Google error message
3. Check Stack Overflow
4. Lihat Android documentation

## Developer

**@GoKanaz**

Aplikasi ini dibuat untuk learning purposes dan open source.
Feel free to modify dan improve!
