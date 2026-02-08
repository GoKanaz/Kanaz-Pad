# Kanaz Pad

Aplikasi catatan markdown untuk Android dengan fitur lengkap.

**Developer:** @GoKanaz

## Fitur Utama

### Editor Markdown Real-time (WYSIWYG)
- Preview Side-by-Side
- Syntax Highlighting (untuk blok kode)
- Dukungan Tabel & Diagram (seperti Mermaid.js)

### Sistem Folder & Sub-folder
- Tagging & Labeling
- Pencarian Teks Lengkap (Full-text Search)

### Fitur Fungsional
- ✅ Autosave
- ✅ Ekspor File (PDF, HTML, Docx)
- ✅ Import File (.md, .txt)
- ✅ Riwayat Versi (Version History)
- ✅ Mode Fokus (Distraction-free mode)
- ✅ Penyisipan Gambar & Lampiran

### Fitur Antarmuka & Kustomisasi
- Dark Mode & Custom Themes
- Shortcut Keyboard Kustom
- Word Count & Statistik Membaca
- Floating Table of Contents (TOC)

### Fitur Lanjutan (Opsional)
- Sinkronisasi Cloud (Dropbox, Google Drive, atau S3)
- Enkripsi End-to-End
- Mode Kolaborasi
- Publish ke Web

## Getting started

Aplikasi ini dibuat dengan:
- Kotlin
- Android Jetpack Components
- Room Database untuk penyimpanan lokal
- Material Design 3
- Markdown Parser (Markwon)
- PDF Generator (iText)
- Export DOCX (Apache POI)

## Struktur Project

```
KanazPad/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/gokanaz/kanazpad/
│   │   │   │   ├── data/
│   │   │   │   │   ├── database/
│   │   │   │   │   ├── model/
│   │   │   │   │   └── repository/
│   │   │   │   ├── ui/
│   │   │   │   │   ├── main/
│   │   │   │   │   ├── editor/
│   │   │   │   │   └── settings/
│   │   │   │   ├── utils/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   └── menu/
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle.kts
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## Cara Build

1. Buka project di Android Studio
2. Sync Gradle
3. Run di emulator atau device Android

## Minimum Requirements

- Android 7.0 (API level 24) or higher
- Android Studio Hedgehog or newer

## License

MIT License

## Developer

**@GoKanaz**

