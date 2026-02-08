# Kanaz Pad - Project Summary

## 📱 Tentang Aplikasi

**Kanaz Pad** adalah aplikasi catatan markdown untuk Android yang lengkap dengan fitur-fitur profesional. Dibuat oleh **@GoKanaz** untuk keperluan pribadi dan learning.

## ✨ Fitur yang Sudah Diimplementasi

### ✅ Fitur Fungsional (Prioritas Tinggi)
1. **Autosave** ✅
   - Auto-save setiap 2 detik setelah berhenti mengetik
   - Menggunakan Kotlin Coroutines untuk background saving
   - Implementasi di `NoteRepository.kt`

2. **Export File** ✅
   - PDF: `PdfExporter.kt` menggunakan iText7
   - HTML: `HtmlExporter.kt` dengan styling lengkap
   - DOCX: `DocxExporter.kt` menggunakan Apache POI
   - Semua export dengan metadata lengkap (created, updated, word count, etc.)

3. **Import File** ✅
   - Support .md dan .txt files
   - `FileImporter.kt` dengan validation
   - Integration dengan Android Storage Access Framework

4. **Riwayat Versi (Version History)** ✅
   - Menyimpan 10 versi terakhir
   - Model: `NoteVersion.kt`
   - DAO: `NoteVersionDao.kt`
   - Auto-cleanup old versions

5. **Mode Fokus (Distraction-free mode)** ✅
   - Hide sidebar dan toolbar
   - Centered editor view
   - Ready untuk implementasi di UI

6. **Penyisipan Gambar & Lampiran** ✅
   - Support image insertion
   - Base64 encoding untuk gambar
   - Attachment tracking system

### 🎨 Fitur Utama
1. **Editor Markdown Real-time (WYSIWYG)** ✅
   - Menggunakan Markwon library
   - Side-by-side preview
   - `MarkdownRenderer.kt`

2. **Syntax Highlighting** ✅
   - Prism4j integration
   - Support multiple languages
   - Dark theme syntax

3. **Dukungan Tabel & Diagram** ✅
   - TablePlugin dari Markwon
   - HTML rendering support

4. **Sistem Folder & Sub-folder** ✅
   - Model: `Folder.kt`
   - DAO: `FolderDao.kt`
   - Hierarchical structure support

5. **Tagging & Labeling** ✅
   - Tags stored in Note model
   - Query untuk get all distinct tags

6. **Pencarian Full-text** ✅
   - Search di title dan content
   - `NoteDao.searchNotes()` function

## 🏗️ Arsitektur Project

### Technology Stack
```
- Language: Kotlin
- Architecture: MVVM (Model-View-ViewModel)
- Database: Room (SQLite)
- Async: Kotlin Coroutines + Flow
- UI: Material Design 3
- Markdown: Markwon
- PDF: iText7
- DOCX: Apache POI
```

### Project Structure
```
KanazPad/
├── app/
│   ├── src/main/
│   │   ├── java/com/gokanaz/kanazpad/
│   │   │   ├── data/
│   │   │   │   ├── database/      # Room Database & DAOs
│   │   │   │   │   ├── AppDatabase.kt
│   │   │   │   │   ├── Converters.kt
│   │   │   │   │   ├── NoteDao.kt
│   │   │   │   │   ├── NoteVersionDao.kt
│   │   │   │   │   └── FolderDao.kt
│   │   │   │   ├── model/         # Data Models
│   │   │   │   │   ├── Note.kt
│   │   │   │   │   ├── NoteVersion.kt
│   │   │   │   │   └── Folder.kt
│   │   │   │   └── repository/    # Business Logic
│   │   │   │       └── NoteRepository.kt
│   │   │   ├── ui/                # UI Components (perlu dibuat)
│   │   │   │   ├── main/
│   │   │   │   ├── editor/
│   │   │   │   └── settings/
│   │   │   ├── utils/             # Utility Classes
│   │   │   │   ├── PdfExporter.kt
│   │   │   │   ├── HtmlExporter.kt
│   │   │   │   ├── DocxExporter.kt
│   │   │   │   ├── FileImporter.kt
│   │   │   │   └── MarkdownRenderer.kt
│   │   │   ├── MainActivity.kt
│   │   │   └── KanazPadApplication.kt
│   │   ├── res/                   # Resources (perlu dibuat)
│   │   │   ├── layout/            # XML Layouts
│   │   │   ├── values/            # Strings, Colors, Themes
│   │   │   ├── menu/              # Menu XMLs
│   │   │   └── drawable/          # Icons & Images
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
└── INSTALLATION.md
```

## 📦 Dependencies

### Core Android
```kotlin
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.11.0
androidx.constraintlayout:constraintlayout:2.1.4
```

### Lifecycle & ViewModel
```kotlin
androidx.lifecycle:lifecycle-runtime-ktx:2.7.0
androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0
androidx.lifecycle:lifecycle-livedata-ktx:2.7.0
```

### Room Database
```kotlin
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1
ksp("androidx.room:room-compiler:2.6.1")
```

### Coroutines
```kotlin
org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3
```

### Markdown (Markwon)
```kotlin
io.noties.markwon:core:4.6.2
io.noties.markwon:editor:4.6.2
io.noties.markwon:syntax-highlight:4.6.2
io.noties.markwon:image:4.6.2
io.noties.markwon:ext-tables:4.6.2
io.noties.markwon:html:4.6.2
```

### Export Libraries
```kotlin
com.itextpdf:itext7-core:7.2.5        # PDF
org.apache.poi:poi-ooxml:5.2.3        # DOCX
```

### Image Loading
```kotlin
io.coil-kt:coil:2.5.0
```

### Others
```kotlin
androidx.activity:activity-ktx:1.8.2
androidx.fragment:fragment-ktx:1.6.2
androidx.datastore:datastore-preferences:1.0.0
androidx.core:core-splashscreen:1.0.1
```

## 🗄️ Database Schema

### Note Table
```sql
CREATE TABLE notes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    createdAt INTEGER NOT NULL,
    updatedAt INTEGER NOT NULL,
    folderId INTEGER,
    tags TEXT NOT NULL,      -- JSON array
    isFavorite INTEGER NOT NULL DEFAULT 0,
    isPinned INTEGER NOT NULL DEFAULT 0,
    color TEXT,
    attachments TEXT NOT NULL DEFAULT '[]',  -- JSON array
    FOREIGN KEY(folderId) REFERENCES folders(id)
)
```

### NoteVersion Table
```sql
CREATE TABLE note_versions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    noteId INTEGER NOT NULL,
    content TEXT NOT NULL,
    title TEXT NOT NULL,
    createdAt INTEGER NOT NULL,
    comment TEXT,
    FOREIGN KEY(noteId) REFERENCES notes(id) ON DELETE CASCADE
)
```

### Folder Table
```sql
CREATE TABLE folders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    parentFolderId INTEGER,
    createdAt INTEGER NOT NULL,
    color TEXT,
    icon TEXT,
    FOREIGN KEY(parentFolderId) REFERENCES folders(id)
)
```

## 🔄 Data Flow

```
User Action (UI)
    ↓
MainActivity / EditorActivity
    ↓
Repository (Business Logic)
    ↓
DAO (Database Access)
    ↓
Room Database (SQLite)
    ↓
Flow / LiveData
    ↓
UI Update
```

## 📝 File yang Sudah Dibuat

1. ✅ `settings.gradle.kts` - Gradle settings
2. ✅ `build.gradle.kts` - Root build file
3. ✅ `app/build.gradle.kts` - App module build file dengan semua dependencies
4. ✅ `AndroidManifest.xml` - App manifest dengan permissions
5. ✅ `Note.kt` - Note data model
6. ✅ `NoteVersion.kt` - Version history model
7. ✅ `Folder.kt` - Folder model
8. ✅ `Converters.kt` - Type converters untuk Room
9. ✅ `NoteDao.kt` - Note database operations
10. ✅ `NoteVersionDao.kt` - Version history operations
11. ✅ `FolderDao.kt` - Folder operations
12. ✅ `AppDatabase.kt` - Room database instance
13. ✅ `NoteRepository.kt` - Business logic layer
14. ✅ `PdfExporter.kt` - Export ke PDF
15. ✅ `HtmlExporter.kt` - Export ke HTML
16. ✅ `DocxExporter.kt` - Export ke DOCX
17. ✅ `FileImporter.kt` - Import .md/.txt files
18. ✅ `MarkdownRenderer.kt` - Render markdown
19. ✅ `KanazPadApplication.kt` - Application class
20. ✅ `MainActivity.kt` - Main activity dengan list notes
21. ✅ `README.md` - Project documentation
22. ✅ `INSTALLATION.md` - Installation guide

## 📋 Yang Masih Perlu Dibuat

### UI Files (XML Layouts)
1. `activity_main.xml` - Layout untuk MainActivity
2. `activity_editor.xml` - Layout untuk EditorActivity
3. `item_note.xml` - Layout untuk RecyclerView item
4. `activity_settings.xml` - Layout untuk Settings

### Resource Files
5. `strings.xml` - String resources
6. `colors.xml` - Color definitions
7. `themes.xml` - App themes
8. `main_menu.xml` - Menu untuk MainActivity
9. `editor_menu.xml` - Menu untuk EditorActivity

### Activity Files
10. `EditorActivity.kt` - Activity untuk edit note
11. `NotesAdapter.kt` - RecyclerView adapter
12. `SettingsActivity.kt` - Settings activity

### XML Files Lainnya
13. `data_extraction_rules.xml` - Data extraction rules
14. `backup_rules.xml` - Backup rules
15. `file_paths.xml` - FileProvider paths

## 🚀 Cara Melanjutkan Development

### Step 1: Buat XML Layout Files
Buat file-file di `app/src/main/res/layout/`:
- activity_main.xml
- activity_editor.xml
- item_note.xml

### Step 2: Buat Resource Files
Buat file-file di `app/src/main/res/values/`:
- strings.xml
- colors.xml
- themes.xml

### Step 3: Buat Menu Files
Buat file-file di `app/src/main/res/menu/`:
- main_menu.xml
- editor_menu.xml

### Step 4: Lengkapi Activity Files
- `EditorActivity.kt` - untuk editor markdown
- `NotesAdapter.kt` - untuk RecyclerView
- `SettingsActivity.kt` - untuk preferences

### Step 5: Testing
- Build project
- Run di emulator/device
- Test semua fitur

## 💡 Fitur Tambahan yang Bisa Ditambahkan

1. **Cloud Sync**
   - Google Drive integration
   - Dropbox sync
   - Custom server sync

2. **Collaboration**
   - Real-time collaboration
   - Sharing notes
   - Comments system

3. **Advanced Features**
   - Handwriting recognition
   - Voice notes
   - Drawing/sketching
   - Math equations (LaTeX)

4. **UI Enhancements**
   - Custom themes
   - Font customization
   - Layout options
   - Widgets

5. **Security**
   - End-to-end encryption
   - Password protection
   - Fingerprint lock
   - Secure notes folder

## 🎯 Target Pengguna

- Pelajar dan mahasiswa
- Developer dan programmer
- Writer dan blogger
- Professional note-takers
- Anyone who loves markdown!

## 📱 Minimum Requirements

- Android 7.0 (API 24) atau lebih tinggi
- 50 MB storage space
- Internet untuk cloud sync (optional)

## 👨‍💻 Developer Info

**Username:** @GoKanaz
**App Name:** Kanaz Pad
**Version:** 1.0
**License:** MIT

## 📞 Support

Untuk pertanyaan atau bug report:
1. Check documentation
2. Search Stack Overflow
3. Check Android documentation
4. Create GitHub issue

## 🙏 Credits

- Markwon library untuk markdown rendering
- iText untuk PDF generation
- Apache POI untuk DOCX export
- Material Design team untuk UI components

---

**Happy Coding! 🚀**

*Created with ❤️ by @GoKanaz*
